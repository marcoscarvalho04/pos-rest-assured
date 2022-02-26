package br.com.uniesp.entidades;
public class PessoaRequestBuilder {

    private String nome;
    private String job;

    public PessoaRequestBuilder() {

    }
    public PessoaRequestBuilder(String nome, String job) {
        this.nome = nome;
        this.job = job;
    }

    public PessoaRequestBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public PessoaRequestBuilder job(String job) {
        this.job = job;
        return this;
    }

    public PessoaRequest build() {
        PessoaRequest pessoaRequest = new PessoaRequest(this);
        return pessoaRequest;
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

    public static PessoaRequestBuilder builder() {
        return new PessoaRequestBuilder();
    }


}