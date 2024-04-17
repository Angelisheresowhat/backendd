package com.example.addcourse1.controller;

import com.example.addcourse1.entity.answer;
import com.example.addcourse1.service.answerservice;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;






@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class answercontroller {
    private answerservice answerservice ;


    @GetMapping("/answers")
    public List<answer> getanswers() {
        return answerservice.getanswers();
    }
    @GetMapping("answers/{id}")
    public ResponseEntity<?> getquestionById(@PathVariable Long id) {
        try {
            answer foundQuestion = answerservice.getanswerById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Requested answer not found"));
            return ResponseEntity.ok().body(foundQuestion);
        } catch (EntityNotFoundException ex) {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "answer not found for id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @PostMapping("/answers")
    public answer addanswer(@RequestBody answer answer)
    {
        return answerservice.save(answer);
    }
    @PutMapping("answers/{id}")

    public ResponseEntity<?> addanswer(@RequestBody answer answer, @PathVariable Long id)
    {
        if (answerservice.existsById(id))
        {
            answer answer1 = answerservice.getanswerById(id).
                    orElseThrow(
                            ()->new EntityNotFoundException("Request answer not found")
                    );
            answer1.setAnswers(answer.getAnswers());
            answer1.setCorrect(answer.isCorrect());
            answerservice.save(answer);
            //returned type course
            return ResponseEntity.ok().body(answer1);
        }
        else
        {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id +"answer not found or matched");
            //returned type hashmap
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @DeleteMapping("answer/{id}")
    public ResponseEntity<?> deletequestion(@PathVariable Long id)
    {
        if (answerservice.existsById(id))
        {
            answerservice.deleteanswer(id);
            HashMap<String,String>message =new HashMap<>();
            message.put("message","answer with id" + id +"was deleted successfully.");
            //returned type hashmap
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }
        else
        {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id +"answer not found or matched");
            //returned type hashmap
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
