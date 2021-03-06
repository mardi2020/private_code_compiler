package com.example.mycompiler.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Input {

    private String language;

    private String sourceCode;

    private String inputValue;

    private String expectedOutput;

    private String timeLimit;

    private String memoryLimit;
}
