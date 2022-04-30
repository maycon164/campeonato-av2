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

import com.fatec.campeonato.model.Jogo;
import com.fatec.campeonato.model.Time;

@Component
public class TorneioDao implements ITorneioDao {

	@Autowired
	private GenericDao gDao;
	

	@Override
	public List<Time> getTimes() throws SQLException, ClassNotFoundException {
		Connection conn = gDao.getConnection();
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
		Connection conn = gDao.getConnection();

		List<Jogo> listaJogos = new ArrayList<Jogo>();
		String sql = "select * from jogos e WHERE data = ?";

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(dataRodada.getTime()));
			ResultSet rs = ps.executeQuery();	
			while (rs.next()) {
				Jogo jogo = Jogo.instantiateJogoFromResultSet(rs);
				listaJogos.add(jogo);
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaJogos;
	}

}