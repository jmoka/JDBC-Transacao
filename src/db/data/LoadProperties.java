package db.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import db.Exceptions.DbException;

public class LoadProperties {
	
	// criar um método que retorna um PROPERTIES
		public static Properties dbProperties() {

			// LER OS ARQUIVO DB.PROPERTIES
			String pathProperties = "properties/db.properties";
			try (FileInputStream fs = new FileInputStream(pathProperties)) {

				// INSTANCIA UMA OBJETO PROPERTIES
				Properties props = new Properties();

				// NO OBJETO PROPERTIES TEM UM MÉTODO QUE LER O CONTEUDO DO ARQUIVO
				props.load(fs); // ARMAZENA EM PROPERTIES O CONTEUDO DO ARQUIVO
				
				// RETURNA UM PROPERTIES
				return props;
			} catch (IOException e) {
				throw new DbException(e.getMessage());
			}

		}

}
