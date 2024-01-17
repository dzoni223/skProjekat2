package com.example.ServisZakazivanje.client.userservice.dto;

public class ClientDto extends UserDto {

    private String passportNo;
    private Integer reservationDays;
    private String clanksaKarta;

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

    public String getClanksaKarta() {
        return clanksaKarta;
    }

    public void setClanksaKarta(String clanksaKarta) {
        this.clanksaKarta = clanksaKarta;
    }
}
