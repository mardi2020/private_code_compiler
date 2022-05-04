package com.example.mycompiler.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Input {

    private String sourceCode;

    private String inputValue;

    private String expectedOutput;

    private String timeLimit;

    private String memoryLimit;
}
