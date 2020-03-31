package com.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GameApiApplication implements CommandLineRunner{

	private Map<String, String> footballWcWinner = new HashMap<String, String>();
	
	private Map<String, String> cricketWcWinner = new HashMap<String, String>();
	
	public static void main(String[] args) {
		SpringApplication.run(GameApiApplication.class, args);
	}

	@GetMapping("/")
	public String getDefault() {
		return "Home Page";
	}
	
	@GetMapping("/footballwc/{team}")
	public String getFootballChampionshipCount(@PathVariable String team) {
		return footballWcWinner.getOrDefault(team.toUpperCase(), "Never Won");
	}
	
	@GetMapping("/cricketwc/{team}")
	public String getCricketChampionshipCount(@PathVariable String team) {
		return cricketWcWinner.getOrDefault(team.toUpperCase(), "Never Won");
	}

	@Override
	public void run(String... args) throws Exception {
		footballWcWinner.put("BRAZIL", "5 Times");
		footballWcWinner.put("GERMANY", "4 Times");
		footballWcWinner.put("ITALY", "4 Times");
		footballWcWinner.put("FRANCE", "2 Times");
		footballWcWinner.put("ARGENTINA", "2 Times");
		footballWcWinner.put("URUGUAY", "2 Times");
		footballWcWinner.put("SPAIN", "1 Time");
		footballWcWinner.put("ENGLAND", "1 Time");
		
		cricketWcWinner.put("AUSTRALIA", "5 Times");
		cricketWcWinner.put("INDIA", "2 Times");
		cricketWcWinner.put("WEST INDIES", "2 Times");
		cricketWcWinner.put("ENGLAND", "1 Time");		
	}	
}
