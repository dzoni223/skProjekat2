package raf.rs.userservice.domain;

import jakarta.persistence.*;

@Entity
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer minimumReservationDays;
    private Integer maximumReservationDays;
    private Double discount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinimumReservationDays() {
        return minimumReservationDays;
    }

    public void setMinimumReservationDays(Integer minimumReservationDays) {
        this.minimumReservationDays = minimumReservationDays;
    }

    public Integer getMaximumReservationDays() {
        return maximumReservationDays;
    }

    public void setMaximumReservationDays(Integer maximumReservationDays) {
        this.maximumReservationDays = maximumReservationDays;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
