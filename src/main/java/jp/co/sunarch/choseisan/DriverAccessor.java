package jp.co.sunarch.choseisan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverAccessor {

    private final static String DRIVER_URL="jdbc:h2:file:~/choseiSan";
    private final static String DRIVER_NAME="org.h2.Driver";
    private final static String USER_NAME="sa";
    private final static String PASSWORD="";

	public Connection createConnection(){
		try{
			Class.forName(DRIVER_NAME);
			Connection con=DriverManager.getConnection(DRIVER_URL,USER_NAME,PASSWORD);
			return con;
		}catch(ClassNotFoundException e){
			System.out.println("Can't Find H2 Driver.\n");
		}catch(SQLException e){
			System.out.println("Connection Error.\n");
		}
		return null;
	}

	public void closeConnection(Connection con){
		try{
			con.close();
		}catch(Exception ex){

		}
	}
}