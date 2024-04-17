package com.example.addcourse1.service;

import com.example.addcourse1.entity.lesson;
import com.example.addcourse1.repository.lessonrepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class lessonservice
{
    private lessonrepository lessonrepository;

    public List<lesson> getlessons()
    {
        return lessonrepository.findAll();
    }
    public Optional<lesson> getlessonById(Long id)
    {
        return lessonrepository.findById(id);
    }
    public lesson save(lesson lessonn)
    {
        return lessonrepository.saveAndFlush(lessonn);
    }
    public boolean existsById(Long id)
    {
        return lessonrepository.existsById(id);
    }
    public void deletelesson(Long id)
    {
        lessonrepository.deleteById(id);
    }
}

