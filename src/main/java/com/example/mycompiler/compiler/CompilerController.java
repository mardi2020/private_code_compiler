package com.example.mycompiler.compiler;

import com.example.mycompiler.input.Input;
import com.example.mycompiler.output.Output;
import com.example.mycompiler.util.DockerBuild;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/compile")
public class CompilerController {

    @PostMapping
    ResponseEntity<Output> compile(@RequestBody Input input) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatedNow = sdf.format(new Date());

        try{
            DockerBuild dockerBuild = new DockerBuild();
            String results = dockerBuild.execute(input.getSourceCode(), input.getInputValue());
            Output output = new Output("200", results, formatedNow);
            return new ResponseEntity<>(output, HttpStatus.OK);

        }catch (Exception e) {
            Output output = new Output("400", e.getMessage(), formatedNow);
            return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);
        }
    }
}
