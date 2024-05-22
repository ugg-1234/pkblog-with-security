package com.pkblog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pkposts")
public class PkPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String pkName;
    private String pkSurname;
    private String email;
    private String mobile;

   @OneToMany(mappedBy = "pkPost",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment>comments;
}
