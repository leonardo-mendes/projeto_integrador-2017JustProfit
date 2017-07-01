package br.com.justprofit.model.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexoes {

    public static Connection getConnection() throws SQLException {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return DriverManager.getConnection("jdbc:mysql://localhost/justprofittestefim", "root", "");
    }

    public static void main(String[] args) {
        try {
            FabricaDeConexoes.getConnection();
            System.out.println("CONEXAO ESTABELECIDA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
