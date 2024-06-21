package com.dodoworkshop.climbyapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "difficulty_levels")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DifficultyLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column
    private long color;

    @Column
    private int score;

    @Column(name = "`order`")
    private int order;
}
