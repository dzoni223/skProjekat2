package raf.rs.userservice.dto;

import raf.rs.userservice.domain.Role;

import java.time.LocalDate;

public class ManagerCreateDto extends UserCreateDto {

    private String companyName;
    private LocalDate hireDate;

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
}
