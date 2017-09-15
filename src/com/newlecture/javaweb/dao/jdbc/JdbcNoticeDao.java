package com.newlecture.javaweb.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.entity.Notice;
import com.newlecture.javaweb.entity.NoticeView;

public class JdbcNoticeDao implements NoticeDao {

	public List<NoticeView> getList(int page, String query) {
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		
		List<NoticeView> list = null;
		int offset = ((page-1)*10); 
		
		// JDBC ����̹� �ε�
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String sql = "SELECT * FROM NoticeView where title like ? order by regDate desc limit ?, 10";
			
			Connection con = DriverManager.getConnection(url, "sist", "cclass");
			/* Statement st = con.createStatement(); */
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, String.format("%%%s%%", query));
			st.setInt(2, offset);
			/*st.setString(1, "%"+title+"%");*/
			
			// ��� ��������
			ResultSet rs = st.executeQuery();
			
			

			// model
			list = new ArrayList<>();

			// ��� ���
			while (rs.next()) {
				NoticeView n = new NoticeView();
				n.setId(rs.getString("id"));
				n.setTitle(rs.getString("title"));
				n.setContent(rs.getString("content"));
				n.setWriterId(rs.getString("writerId"));
				n.setWriterName(rs.getString("writerName"));
				n.setRegDate(rs.getDate("regDate"));
				n.setHit(rs.getInt("hit"));
				n.setCountCmt(rs.getInt("countCmt"));
				list.add(n);
			}
			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public int getCount() {
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		int count = 0;
		// JDBC ����̹� �ε�
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String sqlCount = "SELECT count(id) as count FROM Notice";
			
			Connection con = DriverManager.getConnection(url, "sist", "cclass");
			/* Statement st = con.createStatement(); */

			/*st.setString(1, "%"+title+"%");*/

			Statement stCount = con.createStatement();
			ResultSet rsCount = stCount.executeQuery(sqlCount);
			
			if(rsCount.next())
				count = rsCount.getInt("count");
			
			// ��� ��������
			
			


			rsCount.close();
			stCount.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public NoticeView get(String no) {
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		NoticeView n = null;
		// JDBC ����̹� �ε�
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String sql = "SELECT * FROM Notice where id = ?";
			Connection con = DriverManager.getConnection(url, "sist", "cclass");
			/* Statement st = con.createStatement(); */
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, no);
			/*st.setString(1, "%"+title+"%");*/

			// ��� ��������
			ResultSet rs = st.executeQuery();

			// model
			// ��� ���
			if (rs.next()) {
				n = new NoticeView();
				n.setId(rs.getString("id"));
				n.setTitle(rs.getString("title"));
				n.setContent(rs.getString("content"));
				n.setWriterId(rs.getString("writerId"));
				n.setFileName(rs.getString("fileName"));
				n.setRegDate(rs.getDate("regDate"));
				n.setHit(rs.getInt("hit"));
			}

			

			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int update(String id, String title, String content, String fileName) {
		int result = 0;
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		// JDBC ����̹� �ε�
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String sql = "update Notice set title  = ?, content  = ?, fileName = ? where id = ?";
			Connection con = DriverManager.getConnection(url, "sist", "cclass");
			/* Statement st = con.createStatement(); */
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, id);
			st.setString(4, fileName);

			/*st.setString(1, "%"+title+"%");*/

			// ��� ��������
			result = st.executeUpdate();
			//������Ʈ�� row ���� �˷���
			
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int insert(String title, String content, String fileName) {
		int result=0;
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		// JDBC ����̹� �ε�
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String sql = "INSERT INTO Notice(id, title, content, writerId, fileName) VALUES ((select IFNULL(max(cast(id as unsigned)), 0)+1 from Notice n), ?, ?, ?, ?)";
			Connection con = DriverManager.getConnection(url, "sist", "cclass");
			/* Statement st = con.createStatement(); */
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, "newlec");
			st.setString(4, fileName);

			/*st.setString(1, "%"+title+"%");*/

			// ��� ��������
			result = st.executeUpdate();
			//������Ʈ�� row ���� �˷���
			
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
