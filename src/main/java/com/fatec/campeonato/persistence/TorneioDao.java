package com.fatec.campeonato.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fatec.campeonato.model.AtualizarResultadoPartida;
import com.fatec.campeonato.model.Jogo;
import com.fatec.campeonato.model.Time;
import com.fatec.campeonato.model.TimeResultado;

@Component
public class TorneioDao implements ITorneioDao {

	@Autowired
	private GenericDao gDao;

	private Connection conn;

	@Autowired
	private void setConnection() {
		this.conn = gDao.getConnection();
	}

	@Override
	public List<Time> getTimes() throws SQLException, ClassNotFoundException {
		List<Time> listaTimes = new ArrayList<Time>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM vw_time_grupo order by sigla;");

		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Time time = Time.instantiateTimeFromResultSet(rs);
			listaTimes.add(time);

		}

		rs.close();
		ps.close();

		return listaTimes;
	}

	@Override
	public List<Jogo> getJogos(Date dataRodada) throws ClassNotFoundException, SQLException {

		try {
			List<Jogo> listaJogos = new ArrayList<Jogo>();
			String sql = "select * from jogos e WHERE data = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(dataRodada.getTime()));
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Jogo jogo = Jogo.instantiateJogoFromResultSet(rs);
				listaJogos.add(jogo);
			}

			rs.close();
			ps.close();

			return listaJogos;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<TimeResultado> getResultadoGeral() throws SQLException, ClassNotFoundException {

		gerarResultadoCampeonato();

		try {
			List<TimeResultado> listaTimesResultados = new ArrayList<TimeResultado>();

			String sql = "select * from campeonato";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TimeResultado timeResultado = TimeResultado.InstantiateTimeResultadoFromResulteSet(rs);
				listaTimesResultados.add(timeResultado);
			}

			rs.close();
			ps.close();

			return listaTimesResultados;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Jogo> getQuartasDeFinal() throws ClassNotFoundException, SQLException {
		gerarResultadoCampeonato();
		try {
			List<Jogo> listaJogos = new ArrayList<Jogo>();
			String sql = "SELECT * FROM vw_quartas_de_final";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Jogo jogo = Jogo.instantiateJogoQuartDeFinalFromResultSet(rs);
				listaJogos.add(jogo);
			}

			rs.close();
			ps.close();

			return listaJogos;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void updatePartida(AtualizarResultadoPartida atz) {

		try {
			String sql = "UPDATE jogos SET golsCasa = ?, golsFora = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, atz.getGolsCasa());
			ps.setInt(2, atz.getGolsFora());
			ps.setInt(3, atz.getIdJogo());

			int rs = ps.executeUpdate();

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void gerarResultadoCampeonato() throws ClassNotFoundException, SQLException {

		try {
			String sql = "EXEC resultado_geral";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}