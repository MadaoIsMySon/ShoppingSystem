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
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.cart.CartProduct;
import com.itheima.cart.CartProductPro;
import com.itheima.domain.Product;
import com.itheima.register.User;
import com.itheima.utils.DataSourceUtils;

/**
 * Servlet implementation class OrderInfo
 */
public class OrderInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String my_uid = user.getUid();
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from Olist where id=(select max(id) from Olist)";
		Order id = null;
		try {
			id = runner.query(sql, new BeanHandler<Order>(Order.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int id_num = id.getId()+1;
		QueryRunner runner2 = new QueryRunner(DataSourceUtils.getDataSource());
		String sql2 = "select * from cart where uid = ?";
		List<CartProduct> cartproduct=null;
		try {
			cartproduct = (List<CartProduct>) runner.query(sql2, new BeanListHandler<CartProduct>(CartProduct.class),my_uid);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		List<PreOrder> OrderList = new ArrayList<PreOrder>();
		Iterator<CartProduct> iter = cartproduct.iterator();
		int total = 0;
		
		while(iter.hasNext())
		{
			CartProduct tmp_cartproduct = iter.next();
			String pid = tmp_cartproduct.getPid();
			session = request.getSession();
			User current_USER = (User) session.getAttribute("user");
			String uid = current_USER.getUid();
			QueryRunner runner3 = new QueryRunner(DataSourceUtils.getDataSource());
			String sql3 = "select * from product where pid=?";
			Product tmp_product=null;
			try {
				tmp_product = runner3.query(sql3, new BeanHandler<Product>(Product.class), pid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PreOrder preorder = new PreOrder();
			preorder.setPid(pid);
			preorder.setId(id_num);
			preorder.setProduct(tmp_product);
			preorder.setUid(uid);
			preorder.setQuantity(Integer.parseInt(tmp_cartproduct.getQuantity()));
			preorder.setSubtotal((int) (preorder.getQuantity()*preorder.getProduct().getShop_price()));
			total = total + preorder.getSubtotal();
			OrderList.add(preorder);
		}
		session.setAttribute("PreOrderList", OrderList);
		session.setAttribute("PreOrderTotal", total);
		response.sendRedirect("/Shop_Sys/order_info.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
