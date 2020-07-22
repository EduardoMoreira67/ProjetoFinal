package br.com.isidrocorp.projetofinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity          // vou tornar a classe  uma entidade armazenável 
@Table(name="tbl_usuario") //Especifiquei que a classe Usuário será mapeada para a tabela "tbl_usuario"
public class Usuario {
	
	@Id            //indicando que o atributo  é chave primária 
	@GeneratedValue(strategy = GenerationType.IDENTITY) //signifca que ele é um valor gerado pelo banco e auto_increment
	
	@Column(name="id")
	private int id;

	@Column(name="nome", length=100)
	private String nome;

	@Column(name="email", length=100, unique=true)
	private String email;

	@Column(name="racf", length=7)
	private String racf;

	@Column(name="senha", length=50)
	private String senha;

	@Column(name="link_foto", length=200)
	private String linkFoto;
	
	@JsonIgnoreProperties("listaUsuarios")
	@ManyToOne
	private Departamento depto;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRacf() {
		return racf;
	}
	public void setRacf(String racf) {
		this.racf = racf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getLinkFoto() {
		return linkFoto;
	}
	public void setLinkFoto(String linkFoto) {
		this.linkFoto = linkFoto;
	}
	public Departamento getDepto() {
		return depto;
	}
	public void setDepto(Departamento depto) {
		this.depto = depto;
	}
}
