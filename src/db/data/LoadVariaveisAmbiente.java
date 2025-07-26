package db.data;

import java.util.Properties;

public class LoadVariaveisAmbiente {


	public static Properties carregarPropertiesDoAmbiente() {
		Properties props = new Properties();

		String host = System.getenv("DB_HOST");
		String port = System.getenv("DB_PORT");
		String user = System.getenv("DB_USER");
		String password = System.getenv("DB_PASSWORD");
		String dbName = System.getenv("DB_NAME");

		String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

		props.setProperty("dburl", url);
		props.setProperty("user", user);
		props.setProperty("password", password);

		return props;
	}

	
}