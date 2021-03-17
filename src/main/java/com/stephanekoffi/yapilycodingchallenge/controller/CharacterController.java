package com.stephanekoffi.yapilycodingchallenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stephanekoffi.yapilycodingchallenge.domain.Characters;
import com.stephanekoffi.yapilycodingchallenge.service.CharacterService;
import com.stephanekoffi.yapilycodingchallenge.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private Util util;

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<Integer>> getCharactersIds() {
        try {
            return new ResponseEntity<>(characterService.getCharactersIds(), HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Characters>> getCharacterById(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(characterService.getCharacterById(id),HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
