package com.wellsfargo.fsd.ck.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.wellsfargo.fsd.ck.entity.ProductMaster;
import com.wellsfargo.fsd.ck.dao.ConnectionFactory;
import com.wellsfargo.fsd.ck.exception.CkException;




public class ProductMasterDaoImpl implements ProductMasterDao{
	
	public static final String INS_ITEM_QRY = "INSERT INTO productMaster(id,productName,costPrice,productDescription)VALUES(?,?,?,?)";
	public static final String UPD_ITEM_QRY = "UPDATE productMaster SET productName=?,costPrice=?,productDescription=? WHERE id=?";
	public static final String DEL_ITEM_QRY = "DELETE FROM productMaster WHERE id=?";
	public static final String SEL_ITEM_QRY_BY_ID = "SELECT id,productName,costPrice,productDescription FROM productMaster WHERE id=?";
	public static final String SEL_ALL_ITEMS_QRY  = "SELECT id,productName,costPrice,productDescription FROM productMaster";


	@Override
	public ProductMaster add(ProductMaster pm) throws CkException {
		if (pm != null) {

			try (Connection con = ConnectionFactory.getConnection();

					PreparedStatement pst = con.prepareStatement(INS_ITEM_QRY)) {

				pst.setInt(1, pm.getId());
				pst.setString(2, pm.getProductName());
				pst.setDouble(3, pm.getCost());
				pst.setString(4, pm.getProductDescription());
				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new CkException("Saving the product failed!");

			}

		}

		return pm;
		

	}

	@Override
	public ProductMaster save(ProductMaster pm) throws CkException {

		if (pm != null) {

			try (Connection con = ConnectionFactory.getConnection();

					PreparedStatement pst = con.prepareStatement(UPD_ITEM_QRY)) {

				
				pst.setInt(4, pm.getId());
				pst.setString(1, pm.getProductName());
				pst.setDouble(2, pm.getCost());
				pst.setString(3, pm.getProductDescription());
				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();

				throw new CkException("Saving the item failed!");

			}

		}

		return pm;
	}

	@Override
	public boolean deleteById(Integer id) throws CkException {

		boolean isDeleted=false;
		

			try (Connection con = ConnectionFactory.getConnection();

				PreparedStatement pst = con.prepareStatement(DEL_ITEM_QRY)) {
				pst.setInt(1,id);
				
				int rowsCount= pst.executeUpdate();
				pst.executeUpdate();
				isDeleted=rowsCount>0; 

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();

				throw new CkException("Deleting the item failed!");

			}

	return isDeleted;
	
	}

	@Override
	public ProductMaster getById(Integer id) throws CkException {


		ProductMaster pm=null;
			try (Connection con = ConnectionFactory.getConnection();

				PreparedStatement pst = con.prepareStatement(SEL_ITEM_QRY_BY_ID)) {
				pst.setInt(1,id);
				ResultSet rs=pst.executeQuery();
				if(rs.next()){
					pm = new ProductMaster();                 
					pm.setId(rs.getInt(1));                 
					pm.setProductName(rs.getString(2));                 
					pm.setCost(rs.getDouble(3));                 
					pm.setProductDescription(rs.getString(4));                  
					
				}
				

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();

				throw new CkException("Fetching the item failed!");

			}

	return pm;
	
	
	}

	@Override
	public List<ProductMaster> getAll() throws CkException {

		List<ProductMaster> pms = new ArrayList<>(); 
			try (Connection con = ConnectionFactory.getConnection();
					
				PreparedStatement pst = con.prepareStatement(SEL_ALL_ITEMS_QRY)) {
				ResultSet rs=pst.executeQuery();
				while(rs.next()){
					ProductMaster pm = new ProductMaster();                 
					pm.setId(rs.getInt(1));                 
					pm.setProductName(rs.getString(2));                 
					pm.setCost(rs.getDouble(3));                 
					pm.setProductDescription(rs.getString(4));
					
					pms.add(pm); 
					
				}
					 
			if(pms.isEmpty()) { 
				pms=null;             
				} 
			}catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new CkException("Fetching the item failed!");

			}

	return pms;
	
	}
	
	

}
