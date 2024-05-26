package id.rezka.tuprak9.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:sqlite:Pengingat.db";

	public static Connection connect() {
		Connection connection= null;
		try {
			connection= DriverManager.getConnection(URL);
			System.out.println("Koneksi berhasil ke SQLite.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
        }
        return connection;
	}
}