package com.openclassrooms.mddapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;

    @Size(max = 300)
    @Column(name="content")
    private String content;

    @UpdateTimestamp
    @Column(name="created_at")
    private Timestamp created_at;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Timestamp updated_at;
}