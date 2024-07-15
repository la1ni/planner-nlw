package com.rocketseat.planner.domain.model.trip;

import com.rocketseat.planner.dto.trip.TripRequestPayload;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false, name = "starts_at")
    private LocalDateTime startsAt;

    @Column(nullable = false, name = "ends_at")
    private LocalDateTime endsAt;

    @Column(nullable = false, name = "is_confirmed")
    private Boolean isConfirmed;

    @Column(nullable = false, name = "owner_name")
    private String ownerName;

    @Column(nullable = false, name = "owner_email")
    private String ownerEmail;

    public Trip(TripRequestPayload data){
        this.destination = data.destination();
        this.isConfirmed = false;
        this.ownerEmail = data.owner_email();
        this.ownerName = data.owner_name();
        this.startsAt = LocalDateTime.parse(data.starts_at(), DateTimeFormatter.ISO_DATE_TIME);
        this.endsAt = LocalDateTime.parse(data.ends_at(), DateTimeFormatter.ISO_DATE_TIME);
    }

}
