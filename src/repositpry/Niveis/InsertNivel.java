package repositpry.Niveis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import StringSQL.NivelSql;
import db.DB;
import db.Exceptions.DbException;
import db.closes.CloseConection;
import db.closes.ClosePrepareStatement;
import entities.Nivel;

public class InsertNivel {

	public static Integer inserNivel(String novoName, String nameTabela, String nameColuna, Connection conn) {

		//Connection conn = null;
		PreparedStatement ps = null;
		Nivel novoNivel = new Nivel(novoName);
		String tableName = nameTabela;
		String nameColunaNivel = nameColuna;
		

		try {

			//conn = DB.getConectComProperties();
			//conn = DB.getConectComPropertiesVariaveis();
			ps = conn.prepareStatement(NivelSql.sqlNivelInsert(tableName, nameColunaNivel),
					PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, novoNivel.getNomeNivel());

			Integer rowAffected = ps.executeUpdate();

			System.out.println("Linhas Afetadas " + rowAffected);

			if (rowAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					Integer id = rs.getInt(1);	
					System.out.println(id);
					return id;
					
				}

			}

		} catch (SQLException e) {

			e.getMessage();
			throw new DbException("Já exiete o mesmo nome no banco de dados ou o Banco de dados não Exiete");

		} finally {
			//ClosePrepareStatement.close(ps);
			//CloseConection.close(conn);
		}
		 return (Integer) null;
	}

}
