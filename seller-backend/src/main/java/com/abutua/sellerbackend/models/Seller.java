package com.abutua.sellerbackend.models;

import java.io.Serializable;

import com.abutua.sellerbackend.dto.SellerResponse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TBL_SELLER")
public class Seller implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1024)
    private String name;

    @Column(nullable = false)
    private Double salary;

    @Column(nullable = false)
    private Double bonus;

    @Column(nullable = false)
    private Integer gender;

    public Seller() {

    }

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

    public SellerResponse toDTO() {
        SellerResponse sellerResponse = new SellerResponse();
        sellerResponse.setId(id);
        sellerResponse.setName(name);
        sellerResponse.setSalary(salary);
        sellerResponse.setBonus(bonus);
        sellerResponse.setGender(gender);

        return sellerResponse;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Seller other = (Seller) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Seller [id=" + id + ", name=" + name + ", salary=" + salary + ", bonus=" + bonus + ", gender=" + gender
                + "]";
    }

}
