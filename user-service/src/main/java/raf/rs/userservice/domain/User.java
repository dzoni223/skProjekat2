package raf.rs.userservice.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private Boolean active;

    @ManyToOne(optional = false)
    private Role role;

    //Client data
    private String clanskaKarta;
    private String passportNo;
    private Integer reservationDays;

    //Manager data
    private String companyName;
    private LocalDate hireDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getReservationDays() {
        return reservationDays;
    }

    public void setReservationDays(Integer reservationDays) {
        this.reservationDays = reservationDays;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
       return this.role;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
    public void setClanskaKarta(String clanskaKarta1){
        this.clanskaKarta = clanskaKarta1;
    }

    public String getClanskaKarta() {
        return clanskaKarta;
    }
}
