package com.example.addcourse1.controller;

import java.util.HashMap;
import java.util.List;
import com.example.addcourse1.entity.course;
import com.example.addcourse1.service.courseservice;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class coursecontroller
{
    private courseservice courseservice;


        @GetMapping("/courses")
        public List<course> getcourses() {
            return courseservice.getcourses();
        }
@GetMapping("/courses/{id}")
public ResponseEntity<?> getcourseById(@PathVariable Long id) {
    try {
        course foundCourse = courseservice.getcourseById(id)
                .orElseThrow(() -> new EntityNotFoundException("Requested course not found"));
        return ResponseEntity.ok().body(foundCourse);
    } catch (EntityNotFoundException ex) {
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Course not found for id: " + id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}

    @PostMapping("/courses")
        public course addcourse(@RequestBody course course)
        {
            return courseservice.save(course);
        }
        @PutMapping("courses/{id}")

    public ResponseEntity<?> addcourse(@RequestBody course course,@PathVariable Long id)
    {
        if (courseservice.existsById(id))
        {
            course course1 = courseservice.getcourseById(id).
                    orElseThrow(
                            ()->new EntityNotFoundException("Request course not found")
                    );
                    course1.setTitle(course.getTitle());
                    course1.setCategory(course.getCategory());
                    course1.setDescription(course.getDescription());
                    course1.setDuration(course.getDuration());
                    course1.setLevel(course.getLevel());
                    course1.setPremium(course.isPremium());
                    course1.setPhoto(course.getPhoto());
                    course1.setLessons(course.getLessons());
                    courseservice.save(course);
                    //returned type course
                    return ResponseEntity.ok().body(course1);
        }
        else
        {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id +"course not found or matched");
            //returned type hashmap
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
        @DeleteMapping("courses/{id}")
        public ResponseEntity<?> deletecourse(@PathVariable Long id)
        {
            if (courseservice.existsById(id))
            {
                courseservice.deletecourse(id);
                HashMap<String,String>message =new HashMap<>();
                message.put("message","course with id" + id +"was deleted successfully.");
                //returned type hashmap
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

            }
            else
            {
                HashMap<String,String> message = new HashMap<>();
                message.put("message", id +"course not found or matched");
                //returned type hashmap
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        }

}

