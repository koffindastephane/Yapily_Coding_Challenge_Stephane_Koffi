package com.stephanekoffi.yapilycodingchallenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stephanekoffi.yapilycodingchallenge.domain.Characters;

import java.util.List;
import java.util.Optional;

public interface CharacterService {

    public List<Integer> getCharactersIds() throws JsonProcessingException;
    public List<Characters> getCharacterById(Integer id) throws JsonProcessingException;

}
