package com.example.mycompiler.util;

import com.example.mycompiler.models.Output;

import java.io.*;

public class DockerBuild {

    public Output execute(String sourceCode, String inputValue, String language) throws IOException, InterruptedException {
        Output output = new Output();

        input(inputValue, language);
        exportFile(sourceCode, language);

        ProcessBuilder builder = new ProcessBuilder("docker", "build", "-t", "demo01", "/Users/emma/Downloads/MyCompiler/src/main/resources/languages/" + language);
        Process result = builder.start();

        int exitCode = result.waitFor();
        if (exitCode == 0) {
            builder = new ProcessBuilder("docker", "run", "--rm", "demo01");
            result = builder.start();
        }

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(result.getInputStream()));
        String answer = readOutput(stdInput);
        if (answer.length() > 0) {
            BufferedReader errInput = new BufferedReader(new InputStreamReader(result.getErrorStream()));
            output.setOutputValue(answer);
            String error = readOutput(errInput);
            output.mapper(error);
            return output;
        }
        else {
            BufferedReader errInput = new BufferedReader(new InputStreamReader(result.getErrorStream()));
//            throw new IllegalArgumentException(readOutput(errInput));
            output.setOutputValue(readOutput(errInput));
            return output;
        }
    }

    public static String readOutput(BufferedReader reader) throws IOException {
        String line;
        StringBuilder builder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }

        return builder.toString();
    }

    public void exportFile(String sourceCode, String language) {
        String extension = "";
        if(language.equals("python"))
            extension = ".py";
        else if(language.equals("cpp"))
            extension = ".cpp";
        else if(language.equals("java"))
            extension = ".java";

        File file = new File("/Users/emma/Downloads/MyCompiler/src/main/resources/languages/"+language+"/main"+extension);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(sourceCode);
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void input(String inputValue, String language){
        File file = new File("/Users/emma/Downloads/MyCompiler/src/main/resources/languages/"+language+"/input.txt");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(inputValue);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}