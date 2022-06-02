package com.webrixtec.buildandbeautify.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webrixtec.buildandbeautify.model.ServiceRequest;

@Repository
public interface clientServiceRepo extends JpaRepository<ServiceRequest, Long> {

}
