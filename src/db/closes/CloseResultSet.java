package db.closes;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.Exceptions.DbException;

public class CloseResultSet {

	public static void close(ResultSet rs) {
		
		if(rs!=null) {		

		try {
			rs.close();
			 System.out.println("ResultSet Desconectado");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		}
		}
	}

}
