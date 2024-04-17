package com.example.addcourse1.controller;


import com.example.addcourse1.entity.question;
import com.example.addcourse1.service.questionservice;
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
public class questioncontroller {

    private questionservice questionservice ;


    @GetMapping("/questions")
    public List<question> getquestions() {
        return questionservice.getquestions();
    }
    @GetMapping("questions/{id}")
    public ResponseEntity<?> getquestionById(@PathVariable Long id) {
        try {
            question foundQuestion = questionservice.getquestionById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Requested question not found"));
            return ResponseEntity.ok().body(foundQuestion);
        } catch (EntityNotFoundException ex) {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Question not found for id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @PostMapping("/questions")
    public question addquestion(@RequestBody question question)
    {
        return questionservice.save(question);
    }
    @PutMapping("questions/{id}")

    public ResponseEntity<?> addquestion(@RequestBody question question, @PathVariable Long id)
    {
        if (questionservice.existsById(id))
        {
            question question1 = questionservice.getquestionById(id).
                    orElseThrow(
                            ()->new EntityNotFoundException("Request question not found")
                    );
            question1.setQuestions(question.getQuestions());
            question1.setPoints(question.getPoints());
            questionservice.save(question);
            //returned type course
            return ResponseEntity.ok().body(question1);
        }
        else
        {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id +"question not found or matched");
            //returned type hashmap
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @DeleteMapping("question/{id}")
    public ResponseEntity<?> deletequestion(@PathVariable Long id)
    {
        if (questionservice.existsById(id))
        {
            questionservice.deletequestion(id);
            HashMap<String,String>message =new HashMap<>();
            message.put("message","question with id" + id +"was deleted successfully.");
            //returned type hashmap
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }
        else
        {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id +"question not found or matched");
            //returned type hashmap
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}



















