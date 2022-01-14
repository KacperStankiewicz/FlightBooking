package com.example.FlightBooking.model;

import javax.persistence.*;

@Entity
@Table(name = "airplane")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airplane_id", nullable = false)
    private Integer id;

    @Column(name = "model", nullable = false, length = 30)
    private String model;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    @Column(name = "plane_status", length = 30)
    private String planeStatus;

    public String getPlaneStatus() {
        return planeStatus;
    }

    public void setPlaneStatus(String planeStatus) {
        this.planeStatus = planeStatus;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}