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

@WebServlet("/member/logout")
public class LogoutController extends HttpServlet {

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") != null) {
			session.invalidate();
			if(request.getHeader("Referer") == null || request.getHeader("Referer").indexOf(request.getRequestURI()) > 0)
				response.sendRedirect("../index");
			else {
				
				response.sendRedirect(request.getHeader("Referer"));
			}
		}
		else
			response.sendRedirect("login");
		
	}
	

}
