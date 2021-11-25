package com.webrixtec.buildandbeautify.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webrixtec.buildandbeautify.model.loginCleintModel;


@Repository
public interface LoginClientRepo extends JpaRepository<loginCleintModel, Long>{

}
