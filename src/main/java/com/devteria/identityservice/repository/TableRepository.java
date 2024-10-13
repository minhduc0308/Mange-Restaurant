package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TableRepository extends JpaRepository<Table, Long> {
    @Query("SELECT t FROM Table t WHERE t.isAvailable = :isAvailable")
    List<Table> findTableIsAvailable(@Param("isAvailable") boolean isAvailable);
}

