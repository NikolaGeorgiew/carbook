package com.example.carbook.repo;

import com.example.carbook.model.entity.CarEntity;
import com.example.carbook.model.enums.CarTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity,Long> {

    @Query("SELECT c FROM CarEntity c WHERE c.type = :type AND c.id <> :excludeId")
    Page<CarEntity> findAllByCarEnumAndIdNot(@Param("type") CarTypeEnum type, @Param("excludeId") Long excludeId, Pageable pageable);
}

