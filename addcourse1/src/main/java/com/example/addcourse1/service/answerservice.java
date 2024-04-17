package com.example.addcourse1.service;


import com.example.addcourse1.entity.answer;
import com.example.addcourse1.repository.answerrepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class answerservice {

    private answerrepository answerrepository;

    public List<answer> getanswers()
    {
        return answerrepository.findAll();
    }
    public Optional<answer> getanswerById(Long id)
    {
        return answerrepository.findById(id);
    }
    public answer save(answer answerr)
    {
        return answerrepository.saveAndFlush(answerr);
    }
    public boolean existsById(Long id)
    {
        return answerrepository.existsById(id);
    }
    public void deleteanswer(Long id)
    {
        answerrepository.deleteById(id);
    }








}
