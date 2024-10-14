package com.dbp.backendtourplus.tourcategory.domain;

import com.dbp.backendtourplus.tour.domain.Tour;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tour_categories")
public class TourCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TourCategoryStatus name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "tourCategory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tour> tours;
}
