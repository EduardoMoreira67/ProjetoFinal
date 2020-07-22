package br.com.isidrocorp.projetofinal.dto;

public class VolumeAlarmes {
	private int idAlarme;
	private String nomeAlarme;
	private long qtde;
	  
	
	public VolumeAlarmes(int idAlarme, long qtde) {
		this.idAlarme = idAlarme;
		this.qtde = qtde;
	}
	
	public VolumeAlarmes(int idAlarme, String nomeAlarme, long qtde) {
		this.idAlarme = idAlarme;
		this.nomeAlarme = nomeAlarme;
		this.qtde = qtde;
	}
	
	public int getIdAlarme() {
		return idAlarme;
	}
	public void setIdAlarme(int idAlarme) {
		this.idAlarme = idAlarme;
	}
	public long getQtde() {
		return qtde;
	}
	public void setQtde(long qtde) {
		this.qtde = qtde;
	}

	public String getNomeAlarme() {
		return nomeAlarme;
	}

	public void setNomeAlarme(String nomeAlarme) {
		this.nomeAlarme = nomeAlarme;
	}
	
	

}
