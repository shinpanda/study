package com.newlecture.javaweb.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.javaweb.dao.MemberDao;
import com.newlecture.javaweb.dao.jdbc.JdbcMemberDao;
import com.newlecture.javaweb.entity.Member;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") != null) {
			response.sendRedirect(request.getHeader("Referer"));
		}
		else
			request.getSession().setAttribute("returnURI", request.getHeader("Referer"));
			request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		MemberDao memberDao = new JdbcMemberDao();
		
		Member member = memberDao.get(id);
		if(member == null)
			response.sendRedirect("login?error");
		else if(!member.getPwd().equals(pwd))
			response.sendRedirect("login?error");
		else
		{
			HttpSession session = request.getSession(); 
			Object uri = session.getAttribute("returnURI");
			
			session.setAttribute("id", id);
			if(uri==null)	
				response.sendRedirect("../index");
			else {
				session.removeAttribute("returnURI");
				response.sendRedirect(uri.toString());
			}
		}
		
		
	}
}
