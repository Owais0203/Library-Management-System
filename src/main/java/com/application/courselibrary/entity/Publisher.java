package com.application.courselibrary.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    public Publisher(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "publishers", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<Book>();
}
