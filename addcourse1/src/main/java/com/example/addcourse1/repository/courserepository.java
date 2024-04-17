package com.example.addcourse1.repository;
import com.example.addcourse1.entity.course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface courserepository extends JpaRepository<course,Long>
{

}
