package com.webrixtec.buildandbeautify.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webrixtec.buildandbeautify.model.clientModel;

@Repository
public interface clientRepo extends JpaRepository<clientModel, Long> {

//	boolean existsByCode(String productCode);
//
//	boolean existsByName(String productName);
//
//	@Query(value="select * from tbl_products where cate_id=?1",nativeQuery=true)
//	List<clientModel> findByCategory(Long categoryId);

}
