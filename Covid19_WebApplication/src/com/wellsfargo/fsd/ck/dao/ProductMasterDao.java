package com.wellsfargo.fsd.ck.dao;

import java.util.List;

import com.wellsfargo.fsd.ck.entity.ProductMaster;
import com.wellsfargo.fsd.ck.exception.CkException;



public interface ProductMasterDao {
	ProductMaster add(ProductMaster pm) throws CkException;
	ProductMaster save(ProductMaster pm) throws CkException;
	boolean deleteById(Integer id) throws CkException;  
	ProductMaster getById(Integer id) throws CkException;
	List<ProductMaster> getAll() throws CkException;

}
