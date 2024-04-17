package com.example.addcourse1.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    private long id;
    private String Title;
    private String Category;
    private String Description;
    private byte Photo;
    private int Duration;
    private int Level;
    private boolean Premium;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<lesson> lessons;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "recommended_courses",
//            joinColumns = @JoinColumn(name = "course_id"),
//            inverseJoinColumns = @JoinColumn(name = "recommended_course_id")
//    )
//    @JsonIgnoreProperties("recommendedCourses")
//    private List<course> recommendedCourses;
}
