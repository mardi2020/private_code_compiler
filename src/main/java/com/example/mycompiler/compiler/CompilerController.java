package com.example.mycompiler.compiler;

import com.example.mycompiler.input.Input;
import com.example.mycompiler.util.DockerBuild;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compile")
public class CompilerController {

    @PostMapping
    ResponseEntity<String> compile(@RequestBody Input input) {
        try{
            DockerBuild dockerBuild = new DockerBuild();
            String results = dockerBuild.execute(input.getSourceCode());
            return new ResponseEntity(results, HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return new ResponseEntity("컴파일할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
