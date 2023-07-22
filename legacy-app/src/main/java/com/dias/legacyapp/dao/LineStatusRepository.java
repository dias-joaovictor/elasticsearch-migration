package com.dias.legacyapp.dao;

import com.dias.legacyapp.model.LineStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineStatusRepository extends JpaRepository<LineStatus, Long> {
}
