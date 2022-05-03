package com.example.mycompiler.util;


import org.python.util.PythonInterpreter;

import java.io.*;

public class DockerBuild {

    public String execute(String sourceCode) throws IOException, InterruptedException {
        exportPythonFile(sourceCode);
        ProcessBuilder builder = new ProcessBuilder("docker", "build", "-t", "demo01", "/Users/emma/PycharmProjects/pythonProject4");
        Process result = builder.start();

        int exitCode = result.waitFor();
        if (exitCode != 0) {
            System.exit(-1);
        }
        else{
            builder = new ProcessBuilder("docker", "run", "demo01");
            result = builder.start();
        }

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(result.getInputStream()));

        return readOutput(stdInput);

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


}