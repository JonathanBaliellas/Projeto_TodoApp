package model;

import java.util.Date;

public class Tarefas {
    private int id;
    private int proj_id;
    private String nome;
    private String descricao;
    private Boolean concluido;
    private String observacoes;
    private Date prazo;
    private Date dataCriacao;
    private Date dataAtualizacao;

    public Tarefas(int id, int proj_id, String nome, String descricao, Boolean concluido, String observacoes, Date prazo, Date dataCriacao, Date dataAtualizacao) {
        this.id = id;
        this.proj_id = proj_id;
        this.nome = nome;
        this.descricao = descricao;
        this.concluido = concluido;
        this.observacoes = observacoes;
        this.prazo = prazo;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Tarefas(){
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProj_id() {
        return proj_id;
    }

    public void setProj_id(int proj_id) {
        this.proj_id = proj_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
