package com.itheima.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.domain.Product;
import com.itheima.register.User;
import com.itheima.utils.DataSourceUtils;

/**
 * Servlet implementation class ShowList
 */
public class ShowList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<OrderId> IdList = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String uid = user.getUid();
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select id from Olist group by id";
		try {
			IdList = (List<OrderId>) runner.query(sql,new BeanListHandler<OrderId>(OrderId.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<OrderId> iter = IdList.iterator();
		List<ArrayList<AfterOrder>> AllOrders = new ArrayList<ArrayList<AfterOrder>>();
		while(iter.hasNext())
		{
			OrderId tmp_orderId = iter.next();
			ArrayList<AfterOrder> afterOrders = null;
			QueryRunner runner2 = new QueryRunner(DataSourceUtils.getDataSource());
			String sql2 = "select * from Olist where id = ? and uid=?";
			try {
				afterOrders = (ArrayList<AfterOrder>) runner2.query(sql2,new BeanListHandler<AfterOrder>(AfterOrder.class),tmp_orderId.getId(),uid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ListIterator<AfterOrder> iter2 = afterOrders.listIterator();
			while(iter2.hasNext())
			{
				AfterOrder tmp_afterorder = iter2.next();
				String pid2 = tmp_afterorder.getPid();
				QueryRunner runner3 = new QueryRunner(DataSourceUtils.getDataSource());
				String sql3 = "select * from product where pid=?";
				Product tmp_product=null;
				try {
					tmp_product = runner3.query(sql3, new BeanHandler<Product>(Product.class),pid2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tmp_afterorder.setProduct(tmp_product);
				tmp_afterorder.setSubtotal((int) (Integer.parseInt(tmp_afterorder.getQuantity())*tmp_afterorder.getProduct().getShop_price()));
				iter2.set(tmp_afterorder);
			}
			AllOrders.add(afterOrders);
		}
		session.setAttribute("AllOrder", AllOrders);
		response.sendRedirect("/Shop_Sys/order_list.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
