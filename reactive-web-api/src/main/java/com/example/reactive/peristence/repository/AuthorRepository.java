package com.example.reactive.peristence.repository;

import com.example.reactive.peristence.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,String> {
}
