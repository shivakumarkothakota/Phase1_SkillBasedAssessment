package com.wellsfargo.fsd.ck.service;

import java.util.List;

import com.wellsfargo.fsd.ck.entity.ProductMaster;
import com.wellsfargo.fsd.ck.exception.CkException;


public interface ProductMasterService {
	
	ProductMaster validateAndAdd(ProductMaster pm) throws CkException;     
	ProductMaster validateAndSave(ProductMaster pm) throws CkException;     
	boolean deleteItem(int id) throws CkException;     
	ProductMaster getItemById(int id) throws CkException;     
	List<ProductMaster> getAllItems() throws CkException;

}
