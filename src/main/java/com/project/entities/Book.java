package com.project.entities;

import org.springframework.data.annotation.Id; 
import org.springframework.data.mongodb.core.index.Indexed; 
import lombok.Data; 
@Data 
public class Book { 
    @Id 
    private String id; 
    @Indexed 
    private String title; 
    private String author; 
    private String description; 
}