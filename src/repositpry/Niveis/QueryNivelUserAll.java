package repositpry.Niveis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import StringSQL.NivelSql;
import db.DB;
import db.Exceptions.DbException;
import db.closes.CloseConection;
import db.closes.CloseResultSet;
import db.closes.CloseStatement;
import entities.Nivel;

public class QueryNivelUserAll {

	public static List<Nivel> queryUserAll(String nameTAble, Connection conn) {
		//Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Nivel> niveis = new ArrayList<Nivel>();

		try {
			//conn = DB.getConectComProperties();
			st = conn.createStatement();
			rs = st.executeQuery(NivelSql.sqlNivelAll(nameTAble));

			while (rs.next()) {
				niveis.add(new Nivel(rs.getInt("idNivel"), rs.getString("nomeNivel")));
			}

		} catch (SQLException e) {
			e.getMessage();
			throw new DbException("Verifique se o parâmetros de idNIvel e nomeNive estão corretos 32-QueryNivelUserAll");
		} finally {

			CloseResultSet.close(rs);

			CloseStatement.close(st);

			CloseConection.close(conn);

		}
		return niveis;
	}
}
