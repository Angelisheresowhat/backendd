package com.example.addcourse1.service;


import com.example.addcourse1.entity.chapter;
import com.example.addcourse1.repository.chapterrepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class chapterservice {

    private chapterrepository chapterrepository;

    public List<chapter> getchapters() {return chapterrepository.findAll();}
    public Optional<chapter> getchapterById(Long id)
    {
        return chapterrepository.findById(id);
    }
    public chapter save(chapter chapterr)
    {
        return chapterrepository.saveAndFlush(chapterr);
    }
    public boolean existsById(Long id)
    {
        return chapterrepository.existsById(id);
    }
    public void deletechapter(Long id)
    {
        chapterrepository.deleteById(id);
    }
}
