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

public class chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "chapter_id")
    private long id;
    private String Chapter_Title;
    private String Chapter_Description;
    private String Chapter_Video;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonIgnore
    private lesson lesson;

}
