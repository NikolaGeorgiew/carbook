package com.example.carbook.repo;

import com.example.carbook.model.dto.BlogSummaryDTO;
import com.example.carbook.model.entity.BlogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity,Long> {
    Page<BlogEntity> findAllByIdNot(Pageable pageable, Long id);
}
