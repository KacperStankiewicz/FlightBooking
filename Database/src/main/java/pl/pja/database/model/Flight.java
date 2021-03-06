package pl.pja.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id", nullable = false)
    private Integer id;

    @Column(name = "origin", nullable = false, length = 30)
    private String origin;

    @Column(name = "destination", nullable = false, length = 30)
    private String destination;

    @Column(name = "departure_time", nullable = false)
    private Timestamp departureTime;

    @Column(name = "arrival_time", nullable = false)
    private Timestamp arrivalTime;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airplane_id", nullable = false)
    private Airplane airplane;

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}