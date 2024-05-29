package id.rezka.tuprak9.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Deklarasi kelas DatabaseConnection dengan access modifier public.
public class DatabaseConnection {

	//Deklarasi variabel URL dengan nilai string yang menentukan lokasi dan nama database SQLite yang akan digunakan.
	private static final String URL = "jdbc:sqlite:Pengingat.db";

	//Deklarasi metode connect() dengan return type Connection dan access modifier public. 
	//Metode ini digunakan untuk membuat koneksi ke database SQLite.
	public static Connection connect() {

		//Deklarasi variabel connection dengan tipe data Connection dan nilai awal null.
		Connection connection= null;
		try {

			//Membuat koneksi ke database SQLite dengan menggunakan kelas
			connection= DriverManager.getConnection(URL);
			//Menampilkan pesan sukses koneksi ke SQLite.
			System.out.println("Koneksi berhasil ke SQLite.");
		} catch (SQLException e) { //Blok kode yang akan dieksekusi jika terjadi pengecualian SQLException.
			System.out.println(e.getMessage());
        }

		//Penutup kelas DatabaseConnection.
        return connection;
	}
}