package repositpry;

import java.sql.Connection;
import java.util.List;

import entities.Nivel;
import repositpry.Niveis.DeleteNivel;
import repositpry.Niveis.InsertNivel;
import repositpry.Niveis.QueryNivelUserAll;
import repositpry.Niveis.UpdateNivel;
import repositpry.interfaces.Crud;

public class RepositoryNivelUser implements Crud {
	
	String table = "Tbl_Nivel";
	String nomeNivel = "nomeNivel";

	
	@Override
	public Integer insert(String novoName, Connection conn) {
		return InsertNivel.inserNivel(novoName, table, nomeNivel, conn);

	}

	@Override
	public Boolean daleteNivelId(Integer id, Connection conn) {
		return DeleteNivel.id(table, id, conn);
	}

	@Override
	public Nivel updateNivelId(Integer id, String novoNome, Connection conn) {
		return UpdateNivel.id(table, novoNome, id, conn);
	}

	@Override
	public List<Nivel> queryAll(Connection conn) {
		
		return QueryNivelUserAll.queryUserAll(table, conn);
	}
}
