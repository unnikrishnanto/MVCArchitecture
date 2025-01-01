import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelComponent {
	private static ModelComponent modelcomp;

	private String dp = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/kodenest_db?user=root&password=11%4022y%40M0%21.";
	private String query1 = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";
	private String query2 = "SELECT * FROM users WHERE username=? and password=?";

	private Connection con;
	private ResultSet res;

	private ModelComponent() {
		try {
			Class.forName(dp);
			con = DriverManager.getConnection(url);
			System.out.println("Connection sucessfull");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ModelComponent getModelComponent() {
		if (modelcomp == null) {
			modelcomp = new ModelComponent();
		}
		return modelcomp;
	}

	public int storeUser(String userName, String password, String name, String emial, String phone) {
		int nora = 0;
		try (PreparedStatement pst1 = con.prepareStatement(query1)) {
			pst1.setString(1, userName);
			pst1.setString(2, password);
			pst1.setString(3, name);
			pst1.setString(4, emial);
			pst1.setString(5, phone);

			nora = pst1.executeUpdate();
			return nora;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nora;
	}

	public boolean loginUser(String username, String password) {
		try (PreparedStatement pst2 = con.prepareStatement(query2)) {
			pst2.setString(1, username);
			pst2.setString(2, password);

			res = pst2.executeQuery();
			if (res.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void closeConnection() {
		if (res != null) {
			try {
				res.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
