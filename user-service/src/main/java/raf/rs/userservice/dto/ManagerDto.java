package raf.rs.userservice.dto;

import java.time.LocalDate;

public class ManagerDto extends UserDto{

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
