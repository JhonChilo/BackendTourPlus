package com.dbp.backendtourplus.review.domain;

import com.dbp.backendtourplus.tour.domain.Tour;
import com.dbp.backendtourplus.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;
}
