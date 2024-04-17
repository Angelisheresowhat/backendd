package com.example.addcourse1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "question_id")
    private long id;
    private String questions;
    private float points;
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<answer> Answers;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    private quiz quiz;
}
