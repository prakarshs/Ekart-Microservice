package com.project.UserService.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "SESSION_DETAILS")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long usersId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Column(name = "SESSION_TIME")
    private Instant startTime;
}
