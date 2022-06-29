package com.ediceapp.eloCalculator.demo.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/multiplayer", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class MultiplayerEloCalculator {

	@GetMapping("")
	public List<Double> calculateElo(@RequestBody List<Double> standings) {
		List<Double> newStandings = new ArrayList<>();
		int playerNumber = standings.size();
		int i=0;
		for(Double player: standings) {
			int j=0;
			Double newElo = standings.get(i);
			for(Double player2: standings) {
				if(i!=j) {
					Double ea= 1/(1+Math.pow(10, -(player-player2)/400));
					Double variacionElo;
					if(i<j) {
						variacionElo = 40*(1-ea)/(playerNumber-1);
					}
					else {
						variacionElo = 40*(-ea)/(playerNumber-1);
					}
					newElo+=variacionElo;
				}
				j++;
			}
			newStandings.add(newElo);
			i++;
		}
		return newStandings;
	}

}
