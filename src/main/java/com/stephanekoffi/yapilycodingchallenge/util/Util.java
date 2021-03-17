package com.stephanekoffi.yapilycodingchallenge.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stephanekoffi.yapilycodingchallenge.domain.Characters;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Util {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${my_public_key_for_marvel_api}")
    private String publicKey;

    @Value("${my_private_key_for_marvel_api}")
    private String privateKey;

    // This is the only method to be called from outside of this class
    public List<Characters> getCharactersList(String requestUrlWithoutQueryParameters) throws JsonProcessingException {

        // Calculate hash
        String ts = Timestamp.valueOf(LocalDateTime.now()).toString();
        String hash = calculateHash(ts, privateKey, publicKey);

        // Determine request full url to get character list
        String fullUrl = determineRequestUrl(requestUrlWithoutQueryParameters, ts, publicKey, hash);


        try {
            String charactersJsonString = getJsonStringForGivenUrl(fullUrl);
            // If the charactersList is not empty
            return getCharactersListFromJsonString(charactersJsonString);
        } catch (WebClientResponseException ex) {
            // The charactersList is empty so we return an empty list
            return new ArrayList<>();
        }

    }


    private String calculateHash(String ts, String privateKey, String publicKey) {
        return DigestUtils.md5Hex(ts + privateKey + publicKey);
    }

    private String determineRequestUrl(String requestUrlWithoutQueryParameters, String ts, String publicKey, String hash) {
        return requestUrlWithoutQueryParameters
                + "?" + "ts=" + ts + "&apikey=" + publicKey + "&hash=" + hash;
    }

    private String getJsonStringForGivenUrl(String url) {
        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private List<Characters> getCharactersListFromJsonString(String charactersJsonString) throws JsonProcessingException {

        // Create JSonNode to read JS
        JsonNode jsonNode = objectMapper.readTree(charactersJsonString);

        // Read the JSON tree and get to results node (containing characters with all fields)
        String charactersJsonNode = jsonNode.get("data").get("results").toString();

        // Get characters array with wanted fields only
        Characters[] charactersArray = objectMapper.readValue(charactersJsonNode, Characters[].class);

        // convert characters array to list and return the given list
        return Arrays.asList(charactersArray);
    }

}
