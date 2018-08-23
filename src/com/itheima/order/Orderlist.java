package com.itheima.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;

import com.itheima.utils.DataSourceUtils;

/**
 * Servlet implementation class Orderlist
 */
public class Orderlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = request.getParameter("address");
		String getter =  request.getParameter("getter");
		HttpSession session = request.getSession();
		ArrayList<PreOrder> preorders = (ArrayList<PreOrder>) session.getAttribute("PreOrderList");
		Iterator<PreOrder> iter = preorders.iterator();
		while(iter.hasNext())
		{
			PreOrder tmp_preorder = iter.next();
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			
			String sql = "insert into Olist values(?,?,?,?,?,?)";
			try {
				runner.update(sql,tmp_preorder.getPid(),tmp_preorder.getUid(),address,getter,tmp_preorder.getId(),tmp_preorder.getQuantity());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("/Shop_Sys/deleteAll");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
