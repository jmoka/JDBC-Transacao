package db.closes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Exceptions.DbException;

public class ClosePrepareStatement {

	public static void close(PreparedStatement ps) {

		if (ps != null) {
			try {
				ps.close();
				System.out.println("PreparedStement Desconectado");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DbException(e.getMessage());
			}
		}

	}
}
