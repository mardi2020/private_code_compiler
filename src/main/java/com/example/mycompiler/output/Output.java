package com.example.mycompiler.output;


import lombok.*;

@Getter
@Setter
public class Output {

    private String statusCode;

    private String outputValue;

    private String timeSubmitted;

    private String runtime;

    private String memory;

    public Output(String s, String results, String formatedNow) {
        statusCode = s;
        outputValue = results;
        timeSubmitted = formatedNow;
    }
}
