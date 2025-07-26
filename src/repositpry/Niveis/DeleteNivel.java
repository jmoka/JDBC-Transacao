package repositpry.Niveis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import StringSQL.NivelSql;
import db.DB;
import db.Exceptions.DbException;
import db.closes.CloseConection;
import db.closes.ClosePrepareStatement;

public class DeleteNivel {

	public static Boolean id (String nameTabela, Integer id, Connection conn) {

		//Connection conn = null;
		PreparedStatement ps = null;
		Boolean resultado = false;
		

		try {

			//conn = DB.getConectComProperties();
			ps = conn.prepareStatement(NivelSql.sqlDeleteNivelId(nameTabela), 
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);

			Integer rowAffected = ps.executeUpdate();

			System.out.println("Linhas Afetadas " + rowAffected);	
			
			if(rowAffected > 0 ) {
				resultado = true;
			}
			

		} catch (SQLException e) {

			e.getMessage();
			throw new DbException("Id Inexistente, não consta no banco de dados" );

		} finally {
			ClosePrepareStatement.close(ps);
			CloseConection.close(conn);
		}
		return resultado;
		
	} 

}
