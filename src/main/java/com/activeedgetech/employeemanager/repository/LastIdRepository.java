package com.activeedgetech.employeemanager.repository;

import com.activeedgetech.employeemanager.model.LastId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface LastIdRepository extends JpaRepository<LastId, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<LastId> findById(Long id);
}
