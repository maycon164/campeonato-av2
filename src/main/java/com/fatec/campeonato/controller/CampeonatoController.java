package com.fatec.campeonato.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fatec.campeonato.model.AtualizarResultadoPartida;
import com.fatec.campeonato.model.Jogo;
import com.fatec.campeonato.model.Time;
import com.fatec.campeonato.model.TimeResultado;
import com.fatec.campeonato.persistence.TorneioDao;

@Controller
public class CampeonatoController {

	@Autowired
	private TorneioDao torneioDao;

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World", required = true) String name,
			Model model) {
		model.addAttribute("name", name);
		return "hello";

	}

	@GetMapping("/campeonato")
	public String campeonato(@RequestParam(value = "action", defaultValue = "none", required = false) String action,
			Model model) {
		try {

			if (action.equalsIgnoreCase("getTimes")) {

				List<Time> times = torneioDao.getTimes();
				model.addAttribute("times", times);

			} else if (action.equalsIgnoreCase("getTimesResultado")) {

				List<TimeResultado> timesResultados = torneioDao.getResultadoGeral();
				model.addAttribute("timesResultados", timesResultados);

			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return "index";
	}

	@PostMapping("/campeonatoData")
	public String campeonatoData(@RequestParam("dataRodada") String dataRodada, Model model) {

		try {
			if (!dataRodada.isBlank()) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date data = formatter.parse(dataRodada);
				List<Jogo> jogos = torneioDao.getJogos(data);
				model.addAttribute("jogos", jogos);
			}

		} catch (ParseException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return "index";
	}

	@GetMapping("/quartas")
	public String getQuartasDeFinal(Model model) {

		try {
			List<Jogo> jogos = torneioDao.getQuartasDeFinal();
			model.addAttribute("jogos", jogos);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "index";
	}

	@PostMapping("/atualizarResultado")
	public String atualizarResultado(@RequestParam Map<String, String> allRequestParam, ModelMap model) {

		List<AtualizarResultadoPartida> resultados = new ArrayList<>();
		AtualizarResultadoPartida atz = new AtualizarResultadoPartida();

		for (String key : allRequestParam.keySet()) {

			if (key.contains("codigoJogo")) {
				atz.setIdJogo(Integer.parseInt(allRequestParam.get(key)));
			}

			if (key.contains("golsCasa")) {
				atz.setGolsCasa(Integer.parseInt(allRequestParam.get(key)));
			}

			if (key.contains("golsFora")) {
				atz.setGolsFora(Integer.parseInt(allRequestParam.get(key)));

				resultados.add(AtualizarResultadoPartida.deepCopy(atz));
			}
		}

		for (AtualizarResultadoPartida novoResultado : resultados) {
			torneioDao.updatePartida(novoResultado);
		}

		return "index";
	}

}
