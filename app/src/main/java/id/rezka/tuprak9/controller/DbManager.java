package id.rezka.tuprak9.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String[]> loadData() { // load data seluruhan
        List<String[]> dataList = new ArrayList<>();
        String sql = "SELECT * FROM Pengingat";
        try (Connection connection = DatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                String[] row = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = resultSet.getString(i + 1);
                }
                dataList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }
    public static List<String[]> loadDataForDate(LocalDate tanggal) { //load data menurut tgl
        List<String[]> dataList = new ArrayList<>();
        String sql = "SELECT * FROM Pengingat WHERE tanggal = ?";
        try (Connection connection = DatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tanggal.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                while (resultSet.next()) {
                    String[] row = new String[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        row[i] = resultSet.getString(i + 1);
                    }
                    dataList.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public static void removeData(int id) {
        String sql = "DELETE FROM Pengingat WHERE id = ?";
        try (Connection connection = DatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<String[]> cariData(String keyword){
    List<String[]> resultList = new ArrayList<>();
    String sql = "SELECT * FROM Pengingat WHERE judul LIKE? OR deskripsi LIKE ? OR jenis_Prioritas LIKE ?";
    try (Connection connection = DatabaseConnection.connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            String cariKeyword = "%" + keyword + "%";
            statement.setString(1, cariKeyword);
            statement.setString(2, cariKeyword);
            statement.setString(3, cariKeyword);
            ResultSet resultSet = statement.executeQuery();

            int cekColumn =  resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                String[] baris = new String[cekColumn];
                for (int i = 0; i < cekColumn; i++){
                    baris[i] = resultSet.getString(i + 1);
                }
                resultList.add(baris);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return resultList;
    }
    
}