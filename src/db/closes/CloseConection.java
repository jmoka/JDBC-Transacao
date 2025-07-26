package db.closes;

import java.sql.Connection;
import java.sql.SQLException;

import db.Exceptions.DbException;

public class CloseConection {

	// metodo para fechart a conexão

	public static void close(Connection conn) {

		// verifico se a conexão esta instanciada ou ativa
		if (conn != null) {

			// fecha a conexão
			try {
				conn.close();
				System.out.println("Banco de Dados Desconectado");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DbException(e.getMessage());
			}
		}
		

	}

}
