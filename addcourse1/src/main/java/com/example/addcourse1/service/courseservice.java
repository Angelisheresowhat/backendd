package com.example.addcourse1.service;
import com.example.addcourse1.entity.course;

import com.example.addcourse1.repository.courserepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class courseservice
{
    private courserepository courserepository;

    public List<course> getcourses()
    {
        return courserepository.findAll();
    }
    public Optional<course> getcourseById(Long id)
    {
        return courserepository.findById(id);
    }
    public course save(course coursee)
    {
     return courserepository.saveAndFlush(coursee);
    }
    public boolean existsById(Long id)
    {
        return courserepository.existsById(id);
    }
    public void deletecourse(Long id)
    {
        courserepository.deleteById(id);
    }
}
