package com.fatec.campeonato.model;

public class AtualizarResultadoPartida {
	private int idJogo;
	private int golsCasa;
	private int golsFora;

	public AtualizarResultadoPartida() {
		super();
	}

	public AtualizarResultadoPartida(int idJogo, int golsCasa, int golsFora) {
		super();
		this.idJogo = idJogo;
		this.golsCasa = golsCasa;
		this.golsFora = golsFora;
	}

	public int getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
	}

	public int getGolsCasa() {
		return golsCasa;
	}

	public void setGolsCasa(int golsCasa) {
		this.golsCasa = golsCasa;
	}

	public int getGolsFora() {
		return golsFora;
	}

	public void setGolsFora(int golsFora) {
		this.golsFora = golsFora;
	}

	public static AtualizarResultadoPartida deepCopy(AtualizarResultadoPartida that) {
		AtualizarResultadoPartida atz = new AtualizarResultadoPartida(that.getIdJogo(), that.getGolsCasa(),
				that.getGolsFora());
		return atz;
	}

	@Override
	public String toString() {
		return "AtualizarResultadoPartida [idJogo=" + idJogo + ", golsCasa=" + golsCasa + ", golsFora=" + golsFora
				+ "]";
	}

}
