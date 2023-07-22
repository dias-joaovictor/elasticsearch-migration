package com.dias.legacyapp.dao;

import com.dias.legacyapp.model.Address;
import com.dias.legacyapp.model.LineStatus;
import com.dias.legacyapp.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {}

