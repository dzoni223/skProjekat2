package raf.rs.userservice.dto;

public class DiscountDto {

    Double discount;

    public DiscountDto() {

    }

    public DiscountDto(Double discount) {
        this.discount = discount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
