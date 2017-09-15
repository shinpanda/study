package com.newlecture.javaweb.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Notice;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/admin/notice/reg")
public class NoticeRegController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/upload";
		ServletContext context = request.getServletContext();
		path = context.getRealPath(path);
		System.out.println(path);
		
		MultipartRequest req = new MultipartRequest(request, path, 1024*1024*10, "UTF-8", new DefaultFileRenamePolicy());
		request.setCharacterEncoding("UTF-8");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String fileName = req.getFilesystemName("file");
		
		/*Enumeration en = req.getFileNames();
		while(en.hasMoreElements())
			fName.getFilesystemName(en.nextElement);
		파일 여러개라면 이런 식으로 해야함 
		*/ 
		/*String fileName = (String)req.getFileNames().nextElement();
		System.out.println(fileName); // 결과 값 : file 키값을 얻어옴 키를 모르고 나열할 때 사용
*/		
		NoticeDao dao = new JdbcNoticeDao();
		int result = dao.insert(title, content, fileName);

		response.sendRedirect("list");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();

		if (session.getAttribute("id") == null) {
			out.write("<script> alert('로그인이 필요한 요청입니다.'); location.href='../../member/login'; </script>");
		} else {
			/* response.sendRedirect("notice.jsp"); */
			request.getRequestDispatcher("/WEB-INF/views/admin/notice/reg.jsp").forward(request, response);
		}
	}
}

