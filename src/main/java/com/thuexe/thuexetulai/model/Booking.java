package com.thuexe.thuexetulai.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long carId;

    private LocalDate startDate;
    private LocalDate endDate;

    private String status;

    /** Tỷ lệ đặt cọc: 20, 50 hoặc 70 (% tổng tiền thuê). */
    @Column(name = "deposit_percent")
    private Integer depositPercent;

    /** CASH hoặc TRANSFER — cách thanh toán khoản cọc. */
    @Column(name = "deposit_method", length = 20)
    private String depositMethod;

    // getter setter
    public Long getId() { return id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCarId() { return carId; }
    public void setCarId(Long carId) { this.carId = carId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getDepositPercent() { return depositPercent; }
    public void setDepositPercent(Integer depositPercent) { this.depositPercent = depositPercent; }

    public String getDepositMethod() { return depositMethod; }
    public void setDepositMethod(String depositMethod) { this.depositMethod = depositMethod; }
}