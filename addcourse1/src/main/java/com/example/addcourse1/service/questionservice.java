package com.example.addcourse1.service;


import com.example.addcourse1.entity.question;
import com.example.addcourse1.repository.questionrepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor

public class questionservice {
    private questionrepository questionrepository;

    public List<question> getquestions() {return questionrepository.findAll();}
    public Optional<question> getquestionById(Long id)
    {
        return questionrepository.findById(id);
    }
    public question save(question questionn)
    {
        return questionrepository.saveAndFlush(questionn);
    }
    public boolean existsById(Long id)
    {
        return questionrepository.existsById(id);
    }
    public void deletequestion(Long id)
    {
        questionrepository.deleteById(id);
    }
}
