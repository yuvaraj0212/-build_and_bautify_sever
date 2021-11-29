package com.webrixtec.buildandbeautify.repo;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webrixtec.buildandbeautify.model.UserModel;



@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {

	UserModel findByEmail(String email);

	boolean existsByEmail(String email);



	

}
