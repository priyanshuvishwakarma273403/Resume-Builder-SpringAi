package com.springAi.ResumeBuilder.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resume_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider;
    private String fullName;
    private String targetRole;

    @Column(columnDefinition = "LONGTEXT")
    private String requestPayload;

    @Column(columnDefinition = "LONGTEXT")
    private String generatedContent;

    private String selectedTemplate;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
