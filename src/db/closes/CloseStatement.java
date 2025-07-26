package db.closes;

import java.sql.SQLException;
import java.sql.Statement;

import db.Exceptions.DbException;

public class CloseStatement {

	public static void close(Statement st) {
		if (st != null) {
			try {
				st.close();
				System.out.println("Statement Desconectado");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DbException(e.getMessage());
			}

		}
	}
}
