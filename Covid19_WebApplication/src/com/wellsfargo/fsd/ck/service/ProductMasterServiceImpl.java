package com.wellsfargo.fsd.ck.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.wellsfargo.fsd.ck.dao.ProductMasterDao;
import com.wellsfargo.fsd.ck.dao.ProductMasterDaoImpl;
import com.wellsfargo.fsd.ck.entity.ProductMaster;
import com.wellsfargo.fsd.ck.exception.CkException;


public class ProductMasterServiceImpl implements ProductMasterService{
	ProductMasterDao pmDao;
	
	private boolean isIdValid(Integer id) {
		return id>0;	
	}
	
	private boolean isProductNameValid(String name) {
		return name != null && (name.length() >= 3 || name.length() <= 20);
		
	}
	
	private boolean isPriceValid(Double price) {
		return price >= 0;
	}
	
	private boolean isProductDescValid(String desc) {
		return desc != null && (desc.length() >= 3 || desc.length() <= 20);
		
	}
	
	private boolean isValidItem(ProductMaster pm) throws CkException {
		List<String> errMsg = new ArrayList<>();

		boolean isValid = true;

		if (!isIdValid(pm.getId())) {
			isValid = false;
			errMsg.add("ID can not be null or negative or zero");
		}

		if (!isProductNameValid(pm.getProductName())) {
			isValid = false;
			errMsg.add("Product Name can not be blank, Name must be of 3 to 20 chars in length");
		}

		if (!isPriceValid(pm.getCost())) {
			isValid = false;
			errMsg.add("Price can nt be blank");
		}

		if (!isProductDescValid(pm.getProductDescription())) {
			isValid = false;
			errMsg.add("Product Description can not be null");
		}

		
		if (!isValid) {
			throw new CkException(errMsg.toString());
		}

		return isValid;
	}

	
	
	public ProductMasterServiceImpl() {
		pmDao=new ProductMasterDaoImpl();
	}
	
	@Override
	public ProductMaster validateAndAdd(ProductMaster pm) throws CkException {
		if(pm!=null) {            
			if(isValidItem(pm)) {                
				pmDao.add(pm);             
				}         
			}         
		return pm;
	}

	@Override
	public ProductMaster validateAndSave(ProductMaster pm) throws CkException {
		if(pm!=null) {             
			if(isValidItem(pm)) {                 
				pmDao.save(pm);             
				}         
			}         
		return pm;
	}

	@Override
	public boolean deleteItem(int id) throws CkException {
		return pmDao.deleteById(id);
	}

	@Override
	public ProductMaster getItemById(int id) throws CkException {
		return pmDao.getById(id);
	}

	@Override
	public List<ProductMaster> getAllItems() throws CkException {
		return pmDao.getAll();
	}
	

}
