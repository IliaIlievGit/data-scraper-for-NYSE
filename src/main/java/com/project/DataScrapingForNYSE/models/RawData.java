package com.project.DataScrapingForNYSE.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "raw_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raw_data_id")
    private int id;
    @Column(name = "data")
    private String data;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
