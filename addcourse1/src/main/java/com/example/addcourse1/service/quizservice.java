package com.example.addcourse1.service;


import com.example.addcourse1.entity.quiz;
import com.example.addcourse1.repository.quizrepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class quizservice {
    private quizrepository quizrepository;

    public List<quiz> getquizzes()
    {
        return quizrepository.findAll();
    }
    public Optional<quiz> getquizById(Long id)
    {
        return quizrepository.findById(id);
    }
    public quiz save(quiz quizz)
    {
        return quizrepository.saveAndFlush(quizz);
    }
    public boolean existsById(Long id)
    {
        return quizrepository.existsById(id);
    }
    public void deletequiz(Long id)
    {
        quizrepository.deleteById(id);
    }
}
