package com.example.reactive.peristence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Author")
@Table(name = "authors")
public class Author {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;
}
