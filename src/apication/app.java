package apication;

import java.sql.Connection;
import java.sql.SQLException;

import db.DB;
import db.Exceptions.DbException;
import repositpry.RepositoryNivelUser;

public class app {

	public static void main(String[] args) {

		RepositoryNivelUser nivelUser = new RepositoryNivelUser();

		/* INSERIR */
		// nivelUser.insert("ZECA", DB.getConectComPropertiesVariaveis());
		// nivelUser.insert("LEPRA", DB.getConectComProperties());
		// nivelUser.insert("luiz", DB.getCPNELConectComProperties());

		/* DELETAR */
		// nivelUser.daleteNivelId(1, DB.getConectComPropertiesVariaveis());
		// nivelUser.daleteNivelId(2, DB.getConectComProperties());
		// nivelUser.daleteNivelId(69, DB.getCPNELConectComProperties());

		/* ATUALIZAR */
		// nivelUser.updateNivelId(3, "hhh", DB.getConectComPropertiesVariaveis());
		// nivelUser.updateNivelId(4, "lhhhhl", DB.getConectComProperties());
		// nivelUser.updateNivelId(67, "ke", DB.getCPNELConectComProperties());

		/* CONSULTAR */
		// List<Nivel> list = nivelUser.queryAll(DB.getConectComPropertiesVariaveis());
		// List<Nivel> list = nivelUser.queryAll(DB.getConectComProperties());
		// List<Nivel> list = nivelUser.queryAll(DB.getCPNELConectComProperties());

		/* LISTAR */
		/*
		System.out.println("=============");
		for(Nivel n : list) {
			System.out.println("Codigo : " + n.getIdNivel() );
			System.out.println("Nome : " + n.getNomeNivel());
			System.out.println("=============");
		}
		*/

		/*
		 * Exemplo de Transação:
		 * Uma transação é um conjunto de operações que devem ser executadas todas com
		 * sucesso ou nenhuma delas. Exemplo:
		 * Transferência bancária: subtrair de uma conta e adicionar em outra.
		 * Ambas as ações devem acontecer, ou nenhuma delas.
		 */

		Boolean erro = true;

		try (Connection conn = DB.getConectComProperties()) {

			conn.setAutoCommit(false); // Desativa autocommit para iniciar a transação

			nivelUser.insert("meu", conn);

			if (erro) {
				throw new DbException("ERRO");
			}

			nivelUser.insert("teu", conn);

			conn.commit(); // Finaliza a transação
			System.out.println("Transação REALIZADA");

		} catch (Exception e) {
			System.out.println("Erro durante transação: " + e.getMessage());
			try {
				Connection conn = DB.getConectComProperties();
				conn.rollback();
				System.out.println("Rollback executado!");
			} catch (SQLException ex) {
				System.out.println("Erro no rollback: " + ex.getMessage());
			}
			throw new DbException("Transação falhou: " + e.getMessage());

		}
	}
}
