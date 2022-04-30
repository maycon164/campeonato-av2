package com.fatec.campeonato.persistence;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.fatec.campeonato.model.Jogo;
import com.fatec.campeonato.model.Time;
import com.fatec.campeonato.model.TimeResultado;

public interface ITorneioDao {

	public List<Time> getTimes() throws SQLException, ClassNotFoundException;

	public List<Jogo> getJogos(Date dataRodada) throws SQLException, ClassNotFoundException;
	
	public List<TimeResultado> getResultadoGeral() throws SQLException, ClassNotFoundException;
 
}