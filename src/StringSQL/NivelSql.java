package StringSQL;

public class NivelSql {

	public static String sqlNivelAll(String nameTable) {
		return "SELECT * FROM " + nameTable;
	};

	public static String sqlNivelInsert(String nameTable, String nomeNivel) {

		return "INSERT INTO " + nameTable + " (" + nomeNivel + ") VALUES (?)";

	}
	
	public static String sqlUpdateNivelId(String nameTable) {
	    return "UPDATE " + nameTable + " SET nomeNivel = ? WHERE idNivel = ?";
	}
	
	public static String sqlDeleteNivelId(String nameTable) {
	    return "DELETE FROM " + nameTable + " WHERE idNivel = ?";
	}

}
