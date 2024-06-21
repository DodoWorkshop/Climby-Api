package com.dodoworkshop.climbyapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sessions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column
    private LocalDateTime startedAt;

    @Column
    private LocalDateTime endedAt;

    @OneToMany(mappedBy = "session")
    private List<SessionEntry> entries;

    public boolean isDone(){
        return endedAt != null;
    }
}
