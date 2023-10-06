package com.villa.park.api.estacionamento.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode(of="id")
@Entity(name = "User")
@Table(name="users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user",nullable = false,unique = true,length = 100)
    private String user;
    @Column(name="password",nullable = false,length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "page",nullable = false,length = 30)
    private Page page;

    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name="date_modification")
    private LocalDateTime dateOfModification;
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    public enum Page{
        ADM_PAGE,
        USER_PAGE
    }
}
