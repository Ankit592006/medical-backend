package com.medical.repository;

import com.medical.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    // LOW STOCK (< 10)
    List<Medicine> findByQuantityLessThan(int quantity);

    // EXPIRING SOON
    List<Medicine> findByExpiryDateBefore(LocalDate date);
}