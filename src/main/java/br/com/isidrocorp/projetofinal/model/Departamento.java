package br.com.isidrocorp.projetofinal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity          
@Table(name="tbl_departamento") 
	public class Departamento {
		
	@Id           
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		
	@Column(name="id")
	private int id;

	@Column(name="nomedepto", length=100)
	private String nome;

	@Column(name="unidade", length=80, unique=true)
	private String unidade;

	@OneToMany(mappedBy = "depto", cascade=CascadeType.ALL)
	private List<Usuario> listaUsuarios;
	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

}
