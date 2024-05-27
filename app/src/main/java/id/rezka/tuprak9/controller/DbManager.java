package id.rezka.tuprak9.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

import id.rezka.tuprak9.config.DatabaseConnection;

public class DbManager {

    public static void buatTabel(){
        String tablesql = "CREATE TABLE IF NOT EXISTS Pengingat ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " judul TEXT NOT NULL,"
                + " jenis_prioritas TEXT NOT NULL,"
                + " tanggal TEXT NOT NULL,"
                + " waktu TEXT NOT NULL,"
                + " deskripsi TEXT"
                + ");";
        try (Connection connection = DatabaseConnection.connect();
			Statement stmt = connection.createStatement()) {
			stmt.execute(tablesql);
			System.out.println("Tabel berhasil dibuat.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    }
    
    public static void saveData(String judul, String jenisPrioritas, LocalDate tanggal, LocalTime waktu, String deskripsi) {
        buatTabel();
        String sql = "INSERT INTO Pengingat (judul, jenis_prioritas, tanggal, waktu, deskripsi) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, judul);
            statement.setString(2, jenisPrioritas);
            statement.setString(3, tanggal.toString());
            statement.setString(4, waktu.toString());
            statement.setString(5, deskripsi);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeData(){
    }

    public static void cariData(String judul){
        
    }
}