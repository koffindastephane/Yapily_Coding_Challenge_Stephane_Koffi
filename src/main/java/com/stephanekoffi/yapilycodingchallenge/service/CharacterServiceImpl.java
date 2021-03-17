package com.stephanekoffi.yapilycodingchallenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stephanekoffi.yapilycodingchallenge.domain.Characters;
import com.stephanekoffi.yapilycodingchallenge.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@CacheConfig(cacheNames={"characters"})
@Service
public class CharacterServiceImpl implements CharacterService {

    private final String CHARACTER_RESOURCE_BASE_URI = "https://gateway.marvel.com/v1/public/characters";

    @Autowired
    private Util util;

    @Override
    public List<Integer> getCharactersIds() throws JsonProcessingException {

        // Get characters list
        List<Characters> charactersList = util.getCharactersList(CHARACTER_RESOURCE_BASE_URI);

        // Convert the characters list into a ids list
        List<Integer> charactersIdsList = charactersList.stream()
                .map(character -> character.getId())
                .collect(Collectors.toList());

        return charactersIdsList;
    }

    @Override
    public List<Characters> getCharacterById(Integer id) throws JsonProcessingException {
        return util.getCharactersList(CHARACTER_RESOURCE_BASE_URI + "/" + id);
    }

}