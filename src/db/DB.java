package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import db.Exceptions.DbException;
import db.data.LoadCpanelProperties;
import db.data.LoadProperties;
import db.data.LoadVariaveisAmbiente;

public class DB {

	// 1) estaciar uma variável do tipo Conection
	// 2) criar um metodo statico que retorna um Properties. Para ler o os dados do
	// arquivo properties
	// 3) criar um metodo statico que retorna um Conection (conn) e armazena a
	// conexao na variavel conn
	// usando o arquivo properties
	// usando as variáveis de ambiante
	// 4) criar um metodo para fechar o banco

	// variavel de conexão
	

	// Criar a Conexção como Banco Usando o Properties
	public static Connection getConectComProperties() {
	
			try {

				Properties props = LoadProperties.dbProperties(); // ler os dados do arquivo properties
				String url = props.getProperty("dburl"); // pegar a url do arquivo properties
				System.out.println("Conectando ao banco de dados: " + url);
				return DriverManager.getConnection(url, props); // armazena a conexão na conn - Conection

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}

		

	
	public static Connection getCPNELConectComProperties() {

		
			try {

				Properties props = LoadCpanelProperties.dbProperties(); // ler os dados do arquivo properties
				String url = props.getProperty("dburl"); // pegar a url do arquivo properties
				System.out.println("Conectando ao banco de dados: " + url);
				return DriverManager.getConnection(url, props); // armazena a conexão na conn - Conection

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}

	// Criar a Conexção como Banco Usando o Properties
	public static Connection getConectComPropertiesVariaveis() {

	
			try {
				Properties props = LoadVariaveisAmbiente.carregarPropertiesDoAmbiente();
				String url = props.getProperty("dburl");

				System.out.println("Conectando ao banco de dados: " + url);
				return DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException("Erro ao conectar: " + e.getMessage());
			}


		
	}

		
}