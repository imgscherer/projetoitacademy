package entities;

import java.io.Serializable;

public class Bolsista implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cpf;
	private String universidade;
	private Integer mesReferencia;
	private Integer anoReferencia;
	private String sgDiretoria;
	private String sgSistema;
	private String cdModalidade;
	private String dsPagamento;
	private String moeda;
	private Integer valorBolsa;
	
	public Bolsista() {
	}

	public Bolsista(String nome, String cpf, String universidade, Integer mesReferencia, Integer anoReferencia,
			String sgDiretoria, String sgSistema, String cdModalidade, String dsPagamento, String moeda,
			Integer valorBolsa) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.universidade = universidade;
		this.mesReferencia = mesReferencia;
		this.anoReferencia = anoReferencia;
		this.sgDiretoria = sgDiretoria;
		this.sgSistema = sgSistema;
		this.cdModalidade = cdModalidade;
		this.dsPagamento = dsPagamento;
		this.moeda = moeda;
		this.valorBolsa = valorBolsa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getUniversidade() {
		return universidade;
	}

	public void setUniversidade(String universidade) {
		this.universidade = universidade;
	}

	public Integer getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(Integer mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	public Integer getAnoReferencia() {
		return anoReferencia;
	}

	public void setAnoReferencia(Integer anoReferencia) {
		this.anoReferencia = anoReferencia;
	}

	public String getSgDiretoria() {
		return sgDiretoria;
	}

	public void setSgDiretoria(String sgDiretoria) {
		this.sgDiretoria = sgDiretoria;
	}

	public String getSgSistema() {
		return sgSistema;
	}

	public void setSgSistema(String sgSistema) {
		this.sgSistema = sgSistema;
	}

	public String getCdModalidade() {
		return cdModalidade;
	}

	public void setCdModalidade(String cdModalidade) {
		this.cdModalidade = cdModalidade;
	}

	public String getDsPagamento() {
		return dsPagamento;
	}

	public void setDsPagamento(String dsPagamento) {
		this.dsPagamento = dsPagamento;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public Integer getValorBolsa() {
		return valorBolsa;
	}

	public void setValorBolsa(Integer valorBolsa) {
		this.valorBolsa = valorBolsa;
	}

	public String toString() {
		return "Bolsista [nome=" + nome + ", cpf=" + cpf + ", universidade=" + universidade + ", mesReferencia="
				+ mesReferencia + ", anoReferencia=" + anoReferencia + ", sgDiretoria=" + sgDiretoria + ", sgSistema="
				+ sgSistema + ", cdModalidade=" + cdModalidade + ", dsPagamento=" + dsPagamento + ", moeda=" + moeda
				+ ", valorBolsa=" + valorBolsa + "]";
	}
}
