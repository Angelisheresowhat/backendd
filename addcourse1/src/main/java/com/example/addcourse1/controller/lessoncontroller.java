package com.example.addcourse1.controller;

import java.util.HashMap;
import java.util.List;
import com.example.addcourse1.entity.lesson;
import com.example.addcourse1.service.lessonservice;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")

public class lessoncontroller {
    private lessonservice lessonservice;


    @GetMapping("/lessons")
    public List<lesson> getlessons() {
        return lessonservice.getlessons();
    }
@GetMapping("/lessons/{id}")
public ResponseEntity<?> getlessonById(@PathVariable Long id) {
    try {
        lesson foundLesson = lessonservice.getlessonById(id)
                .orElseThrow(() -> new EntityNotFoundException("Requested lesson not found"));
        return ResponseEntity.ok().body(foundLesson);
    } catch (EntityNotFoundException ex) {
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Lesson not found for id: " + id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
    @PostMapping("/lessons")
    public lesson addlesson(@RequestBody lesson lesson)
    {
        return lessonservice.save(lesson);
    }
    @PutMapping("lessons/{id}")

    public ResponseEntity<?> addlesson(@RequestBody lesson lesson,@PathVariable Long id)
    {
        if (lessonservice.existsById(id))
        {
            lesson lesson1 = lessonservice.getlessonById(id).
                    orElseThrow(
                            ()->new EntityNotFoundException("Request lesson not found")
                    );
            lesson1.setChapters(lesson.getChapters());
            lesson1.setQuizzes(lesson.getQuizzes());
            lesson1.setLesson_Title(lesson.getLesson_Title());
            lesson1.setLesson_Description(lesson.getLesson_Description());
            lessonservice.save(lesson);
            //returned type course
            return ResponseEntity.ok().body(lesson1);
        }
        else
        {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id +"lesson not found or matched");
            //returned type hashmap
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @DeleteMapping("lesson/{id}")
    public ResponseEntity<?> deletelesson(@PathVariable Long id)
    {
        if (lessonservice.existsById(id))
        {
            lessonservice.deletelesson(id);
            HashMap<String,String>message =new HashMap<>();
            message.put("message","lesson with id" + id +"was deleted successfully.");
            //returned type hashmap
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }
        else
        {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id +"lesson not found or matched");
            //returned type hashmap
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
