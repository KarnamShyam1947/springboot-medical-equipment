package com.shyam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shyam.entities.MedicineEntity;

@Repository
public interface MedicineRepository extends JpaRepository<MedicineEntity, Integer> {
    MedicineEntity findByName(String name);
    MedicineEntity findById(int id);

}
