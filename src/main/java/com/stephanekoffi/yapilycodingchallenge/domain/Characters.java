package com.stephanekoffi.yapilycodingchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Characters {

    private Integer id;
    private String name;
    private String description;
    private Thumbnail thumbnail;

}

