package com.project.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.entities.Book; 


public interface BookRepository  extends MongoRepository<Book, String> 
{ 
    Book findByTitle(String title); 
    void delete(String title); 
}
