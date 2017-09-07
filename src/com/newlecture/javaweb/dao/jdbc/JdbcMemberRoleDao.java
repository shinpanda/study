package com.newlecture.javaweb.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.newlecture.javaweb.dao.MemberRoleDao;
import com.newlecture.javaweb.entity.Member;

public class JdbcMemberRoleDao implements MemberRoleDao {


	@Override
	public String getDefaultRole(String memberId) {
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		String roleId = "";
		// JDBC 드라이버 로드
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String sql = "select roleId from MemberRole where memberId = ? and defaultRole = 1;";
			Connection con = DriverManager.getConnection(url, "sist", "cclass");
			/* Statement st = con.createStatement(); */
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, memberId);
			/*st.setString(1, "%"+title+"%");*/

			// 결과 가져오기
			ResultSet rs = st.executeQuery();

			// model
			// 결과 사용
			if (rs.next()) {
				roleId = rs.getString("roleId");
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
		return roleId;
	}

}
