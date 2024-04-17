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
public class quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "quiz_id")
    private long id;
    private String title;
    private String quiz_description;
    private int passing_score;
    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<question> Questions;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonIgnore
    private lesson lesson;

}
