package com.itheima.delete;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.cart.CartProductPro;
import com.itheima.order.PreOrder;
import com.itheima.register.User;
import com.itheima.utils.DataSourceUtils;

/**
 * Servlet implementation class ClearCart
 */
public class ClearCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String uid = user.getUid();
		String sql = "select * from cart where uid=?";
		ArrayList<PreOrder> preorders=null;
		try {
			preorders = (ArrayList<PreOrder>) runner.query(sql, new BeanListHandler<PreOrder>(PreOrder.class),uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListIterator<PreOrder> iter = preorders.listIterator();
		while(iter.hasNext())
		{
			PreOrder tmp_preorder = iter.next();
			String pid = tmp_preorder.getPid();
			QueryRunner runner2 = new QueryRunner(DataSourceUtils.getDataSource());
			String sql2 = "delete from cart where pid=?";
			try {
				runner2.update(sql2,pid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("/Shop_Sys/cartShow");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
