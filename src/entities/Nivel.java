package entities;

import java.io.Serializable;
import java.util.Objects;

public class Nivel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idNivel;
	private String nomeNivel;
	
	public Nivel() {
		
	}
	public Nivel(String nomeNivel) {
		
		this.nomeNivel = nomeNivel;
	}

	public Nivel(Integer idNivel, String nomeNivel) {
		this.idNivel = idNivel;
		this.nomeNivel = nomeNivel;
	}

	public Integer getIdNivel() {
		return idNivel;
	}

	public void setIdNivel(Integer idNivel) {
		this.idNivel = idNivel;
	}

	public String getNomeNivel() {
		return nomeNivel;
	}

	public void setNomeNivel(String nomeNivel) {
		this.nomeNivel = nomeNivel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idNivel, nomeNivel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nivel other = (Nivel) obj;
		return Objects.equals(idNivel, other.idNivel) && Objects.equals(nomeNivel, other.nomeNivel);
	}

	@Override
	public String toString() {
		return "Nivel [idNivel=" + idNivel + ", nomeNivel=" + nomeNivel + "]";
	}
	
	

}
