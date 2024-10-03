package com.dbp.backendtourplus.tour.domain;

import com.dbp.backendtourplus.review.domain.Review;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
@Entity
@Data
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private String iterary;

    private Double price;

    private TourCategory tourCategory;

    @OneToMany
    private List<Review> reviews;
}
