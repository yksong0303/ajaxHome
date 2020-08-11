package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conn {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection open() {
		try {
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","test","test");
			conn.setAutoCommit(false);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static void close(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void close(PreparedStatement ps) {
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void close(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet rs, Connection con) {
		close(rs);
		close(con);
	}
	public static void close(PreparedStatement ps,Connection con) {
		close(ps);
		close(con);
	}
	public static void close(ResultSet rs,PreparedStatement ps,Connection con) {
		close(rs);
		close(ps);
		close(con);
	}
	

}