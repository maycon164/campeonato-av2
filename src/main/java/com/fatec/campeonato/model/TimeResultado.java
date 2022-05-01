package com.fatec.campeonato.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeResultado {
	
	private String nome;
	private String situacao;
	private int jogosDisputados;
	private int vitorias;
	private int empates;
	private int derrotas;
	private int golsMarcados;
	private int golsSofridos;
	private int saldoGols;
	private int pontos;
	private String grupo;

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public int getJogosDisputados() {
		return jogosDisputados;
	}

	public void setJogosDisputados(int jogosDisputados) {
		this.jogosDisputados = jogosDisputados;
	}

	public int getVitorias() {
		return vitorias;
	}

	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}

	public int getEmpates() {
		return empates;
	}

	public void setEmpates(int empates) {
		this.empates = empates;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	public int getGolsMarcados() {
		return golsMarcados;
	}

	public void setGolsMarcados(int golsMarcados) {
		this.golsMarcados = golsMarcados;
	}

	public int getGolsSofridos() {
		return golsSofridos;
	}

	public void setGolsSofridos(int golsSofridos) {
		this.golsSofridos = golsSofridos;
	}

	public int getSaldoGols() {
		return saldoGols;
	}

	public void setSaldoGols(int saldoGols) {
		this.saldoGols = saldoGols;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	

	public static TimeResultado InstantiateTimeResultadoFromResulteSet(ResultSet rs) throws SQLException {
		TimeResultado tr = new TimeResultado();
		tr.setNome(rs.getString("nome_time"));
		tr.setSituacao(rs.getString("situacao"));
		tr.setJogosDisputados(rs.getInt("jogos_disputados"));
		tr.setVitorias(rs.getInt("vitorias"));
		tr.setEmpates(rs.getInt("empates"));
		tr.setDerrotas(rs.getInt("derrotas"));
		tr.setGolsMarcados(rs.getInt("gols_marcados"));
		tr.setGolsSofridos(rs.getInt("gols_sofridos"));
		tr.setSaldoGols(rs.getInt("saldo_gols"));
		tr.setPontos(rs.getInt("pontos"));
		tr.setGrupo(rs.getString("grupo"));
		
		return tr;
	}

	@Override
	public String toString() {
		return "TimeResultado [nome=" + nome + ", situacao=" + situacao + ", jogosDisputados=" + jogosDisputados
				+ ", vitorias=" + vitorias + ", empates=" + empates + ", derrotas=" + derrotas + ", golsMarcados="
				+ golsMarcados + ", golsSofridos=" + golsSofridos + ", saldoGols=" + saldoGols + ", pontos=" + pontos
				+ ", grupo=" + grupo + "]";
	}


}
