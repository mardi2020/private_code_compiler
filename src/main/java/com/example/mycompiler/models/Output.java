package com.example.mycompiler.models;


import lombok.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

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

    public Output(){}

    public void mapper(String usage) {
        String[] newStr = usage.split("\\s+");
        this.runtime = newStr[2];
    }
}
