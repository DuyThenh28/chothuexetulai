package com.thuexe.thuexetulai.repository;

import com.thuexe.thuexetulai.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    /**
     * Xe đang được giữ chỗ / đã duyệt / đã thanh toán và chưa hết hạn trả xe (endDate &gt;= today).
     */
    boolean existsByCarIdAndStatusInAndEndDateGreaterThanEqual(
            Long carId, Collection<String> statuses, LocalDate today);

    @Query("SELECT DISTINCT b.carId FROM Booking b WHERE b.status IN :statuses AND b.endDate >= :today")
    List<Long> findDistinctCarIdsWithActiveBooking(
            @Param("statuses") Collection<String> statuses, @Param("today") LocalDate today);
}