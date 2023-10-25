package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class H2Connection {

	public Connection getConnection() {

		Connection conn = null;

		try {
			Class.forName("org.h2.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:h2:file:./data/database;DB_CLOSE_ON_EXIT=FALSE", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;

	}

	public static void main(String[] args) {

		String sql = "INSERT INTO thing (name, description, status) VALUES (?, ?, ?)";
		PreparedStatement pstm = null;
		Connection conn = null;

		try {

			conn = new H2Connection().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "Bagulho");
			pstm.setString(2, "Caixote");
			pstm.setString(3, "on");
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null)
					pstm.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

}
