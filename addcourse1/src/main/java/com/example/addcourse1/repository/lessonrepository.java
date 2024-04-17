package com.example.addcourse1.repository;
import com.example.addcourse1.entity.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface lessonrepository extends JpaRepository<lesson,Long>
{
@Query("select lesson from lesson lesson left join fetch lesson.Chapters where lesson.id = :lesson_id")
   Optional<lesson> findByIdWithChapters(@Param(("lesson_id")) long lesson_id);


}
