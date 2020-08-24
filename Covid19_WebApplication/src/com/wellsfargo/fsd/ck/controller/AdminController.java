package com.wellsfargo.fsd.ck.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wellsfargo.fsd.ck.entity.AdminUser;
import com.wellsfargo.fsd.ck.entity.ProductMaster;
import com.wellsfargo.fsd.ck.exception.CkException;
import com.wellsfargo.fsd.ck.service.ProductMasterService;
import com.wellsfargo.fsd.ck.service.ProductMasterServiceImpl;



@WebServlet({"/admin","/newproduct","/insertproduct","/deleteproduct","/editproduct","/updateproduct","/list","/logout"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductMasterService pms;
	
	/*
	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}
	*/

	public void init(ServletConfig config) {
		/*
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		*/

		this.pms = new ProductMasterServiceImpl();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String action =  request.getParameter("action");
		String action =  request.getServletPath();
		String viewName = "";
		try {
			switch (action) {
			case "/admin" : 
				viewName = adminLogin(request, response);
				break;
			case "/newproduct":
				viewName = showNewProductForm(request, response);
				break;
			case "/insertproduct":
				viewName = insertProduct(request, response);
				break;
			case "/deleteproduct":
				viewName = deleteProduct(request, response);
				break;
			case "/editproduct":
				viewName = showEditProductForm(request, response);
				break;
			case "/updateproduct":
				viewName = updateProduct(request, response);
				break;
			case "/list":
				viewName = listAllProducts(request, response);
				break;	
			case "/logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
			
				
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
	/*	RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);*/
		
		request.getRequestDispatcher(viewName).forward(request, response);
		
		
	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		return "index.jsp";
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) {
        
        String view="";
        
        try {
            List<ProductMaster> pm = pms.getAllItems();
            request.setAttribute("items", pm);
            view="listproducts.jsp";
        } catch (CkException e) {
            request.setAttribute("errMsg", e.getMessage());
            view="errPage.jsp";
        }
        
        return view;
    }

	private String updateProduct(HttpServletRequest request, HttpServletResponse response) {

        
        ProductMaster pm = new ProductMaster();
        
        pm.setId(Integer.parseInt(request.getParameter("pid")));
        pm.setProductName(request.getParameter("pname"));
        pm.setCost(Double.parseDouble(request.getParameter("pcost")));
        pm.setProductDescription(request.getParameter("pdescription"));
        
        
        String view="";
        
        try {
            pms.validateAndSave(pm);
            request.setAttribute("msg", "Item Got Added!");
            List<ProductMaster> items = pms.getAllItems();
			request.setAttribute("items", items);
            view="listproducts.jsp";
        } catch (CkException e) {
            request.setAttribute("errMsg", e.getMessage());
            view="errorPage.jsp";
        }
        return view;
    
	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String view="";
                        
        try {
        	
            ProductMaster pm = pms.getItemById(id);
            request.setAttribute("item", pm);
            view="editproduct.jsp";
        } catch (CkException e) {
            request.setAttribute("errMsg", e.getMessage());
            view="errPage.jsp";
        }
        return view;
    }

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String view="";
        
        try {
            pms.deleteItem(id);
            request.setAttribute("msg", "Item Got Deleted!");
            List<ProductMaster> items = pms.getAllItems();
			request.setAttribute("items", items);
            view="listproducts.jsp";
        } catch (CkException e) {
            request.setAttribute("errMsg", e.getMessage());
            view="errPage.jsp";
        }
        return view;
    }

	private String insertProduct(HttpServletRequest request, HttpServletResponse response) {
            
        ProductMaster pm = new ProductMaster();
        
        pm.setId(Integer.parseInt(request.getParameter("pid")));
        pm.setProductName(request.getParameter("pname"));
        pm.setCost(Double.parseDouble(request.getParameter("pcost")));
        pm.setProductDescription(request.getParameter("pdescription"));
        
        
        String view="";
        
        try {
            pms.validateAndAdd(pm);
            request.setAttribute("msg", "Item Got Added!");
            List<ProductMaster> items = pms.getAllItems();
			request.setAttribute("items", items);
            view="listproducts.jsp";
        } catch (CkException e) {
            request.setAttribute("errMsg", e.getMessage());
            view="errPage.jsp";
        }
        return view;
    
    }

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
		ProductMaster pm = new ProductMaster();
        request.setAttribute("item", pm);
        
        return "newproduct.jsp";
	}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response) {
		 AdminUser user = new AdminUser();
		
	     user.setUsername(request.getParameter("loginid"));
	     user.setPassword(request.getParameter("password"));
	     
	     String view="";		    
	     if (user.isValid())
	     {
		        
	        //  HttpSession session = request.getSession(true);
	          //session.setAttribute("currentSessionUser", user); 
	          
			try {
				List<ProductMaster> items = pms.getAllItems();
				request.setAttribute("items", items);
		         view="listproducts.jsp";
		         
			} catch (CkException e) {
				request.setAttribute("errMsg", e.getMessage());
	            view="errorPage.jsp";
			}
	            
	        //  session.setAttribute("currentSessionUser",user); 
	          //response.sendRedirect("userLogged.jsp"); //logged-in page      		
	     }
		        
	     else 
	          view="invalidLogon.jsp"; //error page 
		
		return view;
	}

	
}