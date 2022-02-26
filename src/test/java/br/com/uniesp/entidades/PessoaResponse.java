package br.com.uniesp.entidades;

public class PessoaResponse {

	private String nome;
	private String job;
	private String id;
	private String createdAt;
	private String updatedAt;
	private String data;
	
	public PessoaResponse(String nome, String job, String id, String createdAt, String updatedAt, String data ) {
		super();
		this.nome = nome;
		this.job = job;
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt() {
		return this.updatedAt;
	}
	public PessoaResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData(){
		return this.data;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	
}
