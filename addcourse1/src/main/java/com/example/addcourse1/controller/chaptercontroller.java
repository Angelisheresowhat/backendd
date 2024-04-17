package com.example.addcourse1.controller;


import com.example.addcourse1.entity.chapter;
import com.example.addcourse1.service.chapterservice;
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
public class chaptercontroller {
    private chapterservice chapterservice;


    @GetMapping("/chapters")
    public List<chapter> getchapters() {
        return chapterservice.getchapters();
    }
    @GetMapping("chapters/{id}")
    public ResponseEntity<?> getchapterById(@PathVariable Long id) {
        try {
            chapter foundChapter = chapterservice.getchapterById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Requested chapter not found"));
            return ResponseEntity.ok().body(foundChapter);
        } catch (EntityNotFoundException ex) {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Chapter not found for id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @PostMapping("/chapters")
    public chapter addchapter(@RequestBody chapter chapter) {return chapterservice.save(chapter);
    }
    @PutMapping("chapters/{id}")

    public ResponseEntity<?> addchapter(@RequestBody chapter chapter, @PathVariable Long id)
    {
        if (chapterservice.existsById(id))
        {
            chapter chapter1 = chapterservice.getchapterById(id).
                    orElseThrow(
                            ()->new EntityNotFoundException("Request chapter not found")
                    );
            chapter1.setChapter_Title(chapter.getChapter_Title());
            chapter1.setChapter_Description(chapter.getChapter_Description());
            chapter1.setChapter_Video(chapter.getChapter_Video());
            chapter1.setId(chapter.getId());
            chapter1.setLesson(chapter.getLesson());
            chapterservice.save(chapter);
            //returned type course
            return ResponseEntity.ok().body(chapter1);
        }
        else
        {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id +"chapter not found or matched");
            //returned type hashmap
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @DeleteMapping("chapter/{id}")
    public ResponseEntity<?> deletechapter(@PathVariable Long id)
    {
        if (chapterservice.existsById(id))
        {
            chapterservice.deletechapter(id);
            HashMap<String,String>message =new HashMap<>();
            message.put("message","chapter with id" + id +"was deleted successfully.");
            //returned type hashmap
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }
        else
        {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id +"chapter not found or matched");
            //returned type hashmap
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
