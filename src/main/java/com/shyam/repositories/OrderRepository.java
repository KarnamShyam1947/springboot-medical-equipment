package com.shyam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shyam.dto.MedicineOrderDTO;
import com.shyam.entities.OrderEntity;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findById(int id);
    List<OrderEntity> findByUserId(int userId);

    @Query(value = 
        """
        SELECT
            new com.shyam.dto.MedicineOrderDTO(m.name, m.price, o.qty, o.totalPrice, o.status, o.id, o.address, o.phone)
        FROM
            OrderEntity o
        JOIN
            MedicineEntity m
        ON
            o.medicineId = m.id
        AND
            o.userId = :userId""")
    public List<MedicineOrderDTO> getUserMedicineOrder(@Param("userId") int userId);

}
