package com.example.addcourse1.controller;


import com.example.addcourse1.entity.quiz;
import com.example.addcourse1.service.quizservice;
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

public class quizcontroller {
    private quizservice quizservice;

    @GetMapping("/quizzes")
    public List<quiz> getquizzes() {
        return quizservice.getquizzes();
    }
    @GetMapping("/quizzes/{id}")
    public ResponseEntity<?> getquizById(@PathVariable Long id) {
        try {
            quiz foundQuiz = quizservice.getquizById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Requested quiz not found"));
            return ResponseEntity.ok().body(foundQuiz);
        } catch (EntityNotFoundException ex) {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Quiz not found for id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PostMapping("/quizzes")
    public quiz addquiz(@RequestBody quiz quiz)
    {
        return quizservice.save(quiz);
    }
    @PutMapping("quizzes/{id}")

    public ResponseEntity<?> addquiz(@RequestBody quiz quiz, @PathVariable Long id)
    {
        if (quizservice.existsById(id))
        {
            quiz quiz1 = quizservice.getquizById(id).
                    orElseThrow(
                            ()->new EntityNotFoundException("Request quiz not found")
                    );
            quiz1.setTitle(quiz.getTitle());
            quiz1.setQuiz_description(quiz.getQuiz_description());
            quiz1.setPassing_score(quiz.getPassing_score());
            quiz1.setQuestions(quiz.getQuestions());
            quizservice.save(quiz);
            //returned type course
            return ResponseEntity.ok().body(quiz1);
        }
        else
        {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id +"quiz not found or matched");
            //returned type hashmap
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @DeleteMapping("quiz/{id}")
    public ResponseEntity<?> deletequiz(@PathVariable Long id)
    {
        if (quizservice.existsById(id))
        {
            quizservice.deletequiz(id);
            HashMap<String,String>message =new HashMap<>();
            message.put("message","quiz with id" + id +"was deleted successfully.");
            //returned type hashmap
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }
        else
        {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id +"quiz not found or matched");
            //returned type hashmap
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

}
