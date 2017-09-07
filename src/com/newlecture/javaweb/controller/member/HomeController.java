package com.newlecture.javaweb.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.javaweb.dao.MemberDao;
import com.newlecture.javaweb.dao.MemberRoleDao;
import com.newlecture.javaweb.dao.jdbc.JdbcMemberDao;
import com.newlecture.javaweb.dao.jdbc.JdbcMemberRoleDao;
import com.newlecture.javaweb.entity.Member;

@WebServlet("/member/home")
public class HomeController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		Object _memberId = session.getAttribute("id");

		if (_memberId == null) {
			out.write("<script> alert('로그인이 필요한 요청입니다.'); location.href='login'; </script>");
		} else {
			String memberId = _memberId.toString();
			MemberRoleDao dao = new JdbcMemberRoleDao();
			String defaultRole = dao.getDefaultRole(memberId);
			
			if(defaultRole.equals("ROLE_ADMIN"))
				response.sendRedirect("../admin/index");
			else if(defaultRole.equals("ROLE_TEACHER"))
				response.sendRedirect("../teacher/index");
			else if (defaultRole.equals("ROLE_STUDENT"))
				response.sendRedirect("../student/index");
			else
				request.getRequestDispatcher("/WEB-INF/views/student/index.jsp").forward(request, response);	
		}
	}
}
