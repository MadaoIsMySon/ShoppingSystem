package com.itheima.cart;

import java.io.IOException;
import java.sql.SQLException;
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

import com.itheima.domain.Product;
import com.itheima.utils.DataSourceUtils;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String quantity = request.getParameter("quantity");
		if(quantity==null)
		{
			response.sendRedirect("/Shop_Sys/order_info.jsp");
			return;
		}
		String uid = request.getParameter("uid");
		QueryRunner runner1 = new QueryRunner(DataSourceUtils.getDataSource());
		String sql1 = "select * from cart where uid=? and pid=?";
		CartProduct cartproduct = null;
		try {
			cartproduct = runner1.query(sql1, new BeanHandler<CartProduct>(CartProduct.class),uid,pid);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(cartproduct==null)
		{
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "insert into cart values(?,?,?)";
			try {
				runner.update(sql,pid,quantity,uid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			String currrent_quantity = cartproduct.getQuantity();
			int quantity_number = Integer.parseInt(currrent_quantity);
			String sql2 = "UPDATE cart SET quantity = ? where uid = ? and pid = ?";
			QueryRunner runner2 = new QueryRunner(DataSourceUtils.getDataSource());
			try {
				runner2.update(sql2,Integer.toString(quantity_number+Integer.parseInt(quantity)),uid,pid);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		List<CartProduct> cartProductList = null;
		QueryRunner runner3 = new QueryRunner(DataSourceUtils.getDataSource());
		String sql3 = "select * from cart where uid = ?";
		try {
			cartProductList = runner3.query(sql3, new BeanListHandler<CartProduct>(CartProduct.class),uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<CartProductPro> cartProductProList = new LinkedList<CartProductPro>();
		Iterator<CartProduct> iter = cartProductList.iterator();
		int total = 0;
		while(iter.hasNext())
		{
			CartProduct cartProduct = iter.next();
			String m_pid = cartProduct.getPid();
			String m_quantity = cartProduct.getQuantity();
			CartProductPro m_cartproductpro = new CartProductPro();
			QueryRunner runner4 = new QueryRunner(DataSourceUtils.getDataSource());
			String sql4 = "select * from product where pid = ?";
			Product m_product = null;
			try {
				m_product = runner4.query(sql4, new BeanHandler<Product>(Product.class),m_pid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int shop_price = (int) m_product.getShop_price();
			int subtotal = shop_price * Integer.parseInt(m_quantity);
			total = total + subtotal;
			m_cartproductpro.setPid(m_product.getPid());
			m_cartproductpro.setPname(m_product.getPname());
			m_cartproductpro.setMarket_price(m_product.getMarket_price());
			m_cartproductpro.setShop_price(m_product.getShop_price());
			m_cartproductpro.setPimage(m_product.getPimage());
			m_cartproductpro.setPdate(m_product.getPdate());
			m_cartproductpro.setIs_hot(m_product.getIs_hot());
			m_cartproductpro.setQuantity(m_quantity);
			m_cartproductpro.setUid(uid);
			m_cartproductpro.setSubtotal(Integer.toString(subtotal));
			cartProductProList.add(m_cartproductpro);
		}
		HttpSession session = request.getSession();
		session.setAttribute("cart", cartProductProList);
		request.setAttribute("total", total);
		request.setAttribute("cartList", cartProductProList);
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
