package com.fatec.campeonato.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fatec.campeonato.model.Jogo;
import com.fatec.campeonato.model.Time;
import com.fatec.campeonato.persistence.TorneioDao;

@Controller
public class CampeonatoController {

	@Autowired
	private TorneioDao torneioDao;

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World", required = true) String name,
			Model model) {
		System.out.println("Helllo Man");
		model.addAttribute("name", name);
		return "hello";

	}

	@GetMapping("/campeonato")
	public String campeonato(@RequestParam(value = "action", defaultValue = "none", required = false) String action,
			Model model) {

		if (action.equalsIgnoreCase("getTimes")) {
			List<Time> times;
			try {
				times = torneioDao.getTimes();
				model.addAttribute("times", times);
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

		return "index";
	}

	@PostMapping("/campeonatoData")
	public String campeonatoData(@RequestParam("dataRodada") String dataRodada, Model model) {

		Date data;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			data = formatter.parse(dataRodada);
			List<Jogo> jogos = torneioDao.getJogos(data);
			model.addAttribute("jogos", jogos);
		} catch (ParseException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
		return "index";
	}

}
