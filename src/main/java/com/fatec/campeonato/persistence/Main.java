package com.fatec.campeonato.persistence;

import java.sql.SQLException;
import java.util.List;

import com.fatec.campeonato.model.TimeResultado;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		TorneioDao td = new TorneioDao();
		
		List<TimeResultado> lista = td.getResultadoGeral();
		System.out.println(lista);
	}

}
