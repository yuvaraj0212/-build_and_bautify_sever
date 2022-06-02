package com.webrixtec.buildandbeautify.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webrixtec.buildandbeautify.model.Role;

@Repository
public interface Rolerepo extends JpaRepository<Role, Long>{

//	Set<Role> findByRolename(String string);

	Set<Role> findByRoleName(String string);

	

	

}
