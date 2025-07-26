package repositpry.Niveis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import StringSQL.NivelSql;
import db.DB;
import db.Exceptions.DbException;
import db.closes.CloseConection;
import db.closes.ClosePrepareStatement;
import entities.Nivel;

public class UpdateNivel {

	public static Nivel id (String nameTabela, String novoNome, Integer id, Connection conn) {

		//Connection conn = null;
		PreparedStatement ps = null;
		Nivel nivel = new Nivel();

		try {

			//conn = DB.getConectComProperties();
			ps = conn.prepareStatement(NivelSql.sqlUpdateNivelId(nameTabela), 
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, novoNome);
			ps.setInt(2, id);

			Integer rowAffected = ps.executeUpdate();

			System.out.println("Linhas Afetadas " + rowAffected);
			
			nivel.setIdNivel(id);
			nivel.setNomeNivel(novoNome);

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			throw new DbException("Id Inexistente, não consta no banco de dados");

		} finally {
			ClosePrepareStatement.close(ps);
			CloseConection.close(conn);
		}
		return nivel;
		
	} 

}
