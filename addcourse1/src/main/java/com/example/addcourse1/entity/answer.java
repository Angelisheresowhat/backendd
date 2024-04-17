package com.example.addcourse1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "answer_id")
    private long id;
    private String answers;
    private boolean correct;
    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private question question;
}
