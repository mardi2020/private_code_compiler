package com.example.mycompiler.util;

import java.io.*;

public class DockerBuild {

    public String execute(String sourceCode, String inputValue) throws IOException, InterruptedException {

        input(inputValue);

        exportPythonFile(sourceCode);
        ProcessBuilder builder = new ProcessBuilder("docker", "build", "-t", "demo01", "/Users/emma/PycharmProjects/pythonProject4");
        Process result = builder.start();

        int exitCode = result.waitFor();
        if (exitCode == 0) {
            builder = new ProcessBuilder("docker", "run", "demo01");
            result = builder.start();
        }

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(result.getInputStream()));
        String answer = readOutput(stdInput);
        if (answer.length() > 0) {
            return answer;
        }
        else {
            BufferedReader errInput = new BufferedReader(new InputStreamReader(result.getErrorStream()));
//            throw new IllegalArgumentException(readOutput(errInput));
            return readOutput(errInput);
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

    public void exportPythonFile(String sourceCode) {
        File file = new File("/Users/emma/PycharmProjects/pythonProject4/main.py");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(sourceCode);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void input(String inputValue){
        File file = new File("/Users/emma/PycharmProjects/pythonProject4/input.txt");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(inputValue);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}