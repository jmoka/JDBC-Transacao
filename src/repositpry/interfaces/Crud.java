package repositpry.interfaces;

import java.sql.Connection;
import java.util.List;

import entities.Nivel;

public interface Crud {
	
	List<?> queryAll (Connection conn); // consulta todos os elementos
	Integer insert(String novoName, Connection conn); // insere um elemento
	Nivel updateNivelId(Integer id, String novoNome, Connection conn); // atualiza a parir do id
	Boolean daleteNivelId(Integer id, Connection conn); // deleta a partir do id
	//Boolean daleteNivelId(Integer id)
	//Integer insert(String novoName)
	//Nivel updateNivelId(Integer id, String novoNome)
	

}
