package apication;

/*
 * Exemplo de Transação:
 * Uma transação é um conjunto de operações que devem ser executadas todas com
 * sucesso ou nenhuma delas. Exemplo:
 * Transferência bancária: subtrair de uma conta e adicionar em outra.
 * Ambas as ações devem acontecer, ou nenhuma delas.
 */


import java.sql.Connection;
import java.sql.SQLException;

import db.DB;
import db.Exceptions.DbException;
import repositpry.RepositoryNivelUser;

public class app {

	public static void main(String[] args) {

		RepositoryNivelUser nivelUser = new RepositoryNivelUser();
	
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
