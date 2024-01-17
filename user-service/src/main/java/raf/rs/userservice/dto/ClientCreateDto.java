package raf.rs.userservice.dto;

import raf.rs.userservice.domain.Role;

import java.time.LocalDate;

public class ClientCreateDto extends UserCreateDto {

    private String passportNo;
    private Integer reservationDays;
    private String clanskaKarta;

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public Integer getReservationDays() {
        return reservationDays;
    }

    public void setReservationDays(Integer reservationDays) {
        this.reservationDays = reservationDays;
    }

    public String getClanskaKarta() {
        return clanskaKarta;
    }

    public void setClanskaKarta(String clanskaKarta) {
        this.clanskaKarta = clanskaKarta;
    }
}
