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

    @Column(name="email")
    private String email;

    @Column(name="name")
    private String name;

    @Column (name="password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "subscriptions", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private List<Topic> topics;

    @UpdateTimestamp
    @Column(name="created_at")
    private Timestamp created_at;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Timestamp updated_at;
}
