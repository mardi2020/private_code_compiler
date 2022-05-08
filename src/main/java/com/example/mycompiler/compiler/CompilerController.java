package com.example.mycompiler.compiler;

import com.example.mycompiler.models.Input;
import com.example.mycompiler.models.Output;
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
            Output results = dockerBuild.execute(input.getSourceCode(), input.getInputValue(), input.getLanguage());
//            Output output = new Output("200", results, formatedNow);
            results.setTimeSubmitted(formatedNow);
            results.setStatusCode("200");
            return new ResponseEntity<>(results, HttpStatus.OK);

        }catch (Exception e) {
            Output output = new Output("400", e.getMessage(), formatedNow);
            return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);
        }
    }
}
