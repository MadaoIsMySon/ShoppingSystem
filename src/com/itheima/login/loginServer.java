package com.itheima.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.register.User;
import com.itheima.utils.DataSourceUtils;

public class loginServer extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//��֤��У��
		//���ҳ���������֤
		String checkCode_client = request.getParameter("checkCode");
		//�������ͼƬ�����ֵ���֤��
		String checkCode_session = (String) request.getSession().getAttribute("checkcode_session");
		//�ȶ�ҳ��ĺ�����ͼƬ�����ֵ���֤���Ƿ�һ��
		if(!checkCode_session.equals(checkCode_client)){
			request.setAttribute("loginInfo", "������֤�벻��ȷ");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		
		//���ҳ����û���������������ݿ��У��
		//......
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username=? and password=?";
		User user = null;
		try {
			user = runner.query(sql, new BeanHandler<User>(User.class), username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(user==null) {
			request.setAttribute("loginInfo", "�����˺Ż����벻��ȷ");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("/Shop_Sys/index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}