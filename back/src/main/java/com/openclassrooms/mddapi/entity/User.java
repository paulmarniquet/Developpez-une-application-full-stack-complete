package com.openclassrooms.mddapi.entity;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name="email")
    private String email;

    @NonNull
    @Column(name="name")
    private String name;

    @NonNull
    @Column (name="password")
    private String password;

    @ManyToMany
    @JoinTable(name = "subscriptions", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private List<Topic> topics;

    @Column(name="created_at")
    private Timestamp created_at;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Timestamp updated_at;
}
