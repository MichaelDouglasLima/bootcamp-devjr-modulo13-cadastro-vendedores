package com.abutua.sellerbackend.dto;

import com.abutua.sellerbackend.models.Seller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SellerRequest {

    @NotBlank(message = "Name can not be blank")
    @Size(min=5, max = 255, message = "Name length min=5 and max=255")
    private String name;

    @NotNull(message = "Salary can not be null")
    @Min(value=0, message = "Salary min value = 0")
    private Double salary;

    @NotNull(message = "Bonus can not be null")
    @Min(value=0, message = "Bonus min value = 0")
    @Max(value=100, message = "Bonus max value = 100")
    private Double bonus;

    @NotNull(message = "Gender can not be null")
    @Min(value=1, message = "Gender min value = 1")
    @Max(value=2, message = "Gender max value = 2")
    private Integer gender;

    public SellerRequest() {
   
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Seller toEntity() {
        Seller seller = new Seller();
        seller.setName(name);
        seller.setSalary(salary);
        seller.setBonus(bonus);
        seller.setGender(gender);
        
        return seller;
    }
    
}
