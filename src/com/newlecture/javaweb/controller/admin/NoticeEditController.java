package com.newlecture.javaweb.controller.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Notice;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/admin/notice/edit")
public class NoticeEditController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = "/upload";
		ServletContext context = request.getServletContext();
		path = context.getRealPath(path);
		System.out.println(path);
		
		MultipartRequest req = new MultipartRequest(request, path, 1024*1024*10, "UTF-8", new DefaultFileRenamePolicy());
		request.setCharacterEncoding("UTF-8");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String id = req.getParameter("id");
		String fileName = req.getFilesystemName("file");
		
		
		NoticeDao dao = new JdbcNoticeDao();
		int result = dao.update(id, title, content, fileName);
		
		response.sendRedirect("detail?no="+id);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		
		NoticeDao dao = new JdbcNoticeDao();
		request.setAttribute("n", dao.get(no));
		
		/*response.sendRedirect("notice.jsp");*/
		request.getRequestDispatcher("/WEB-INF/views/admin/notice/edit.jsp").forward(request, response);
	}	
}
