package ca.sheridancollege.pate3536.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.pate3536.beans.Competition;
import ca.sheridancollege.pate3536.database.DatabaseAccess;

@Controller
public class HomeController {

	@Autowired
	private DatabaseAccess da;
	
	final String REST_URL = "http://localhost:8080/competitions/";
	
	@GetMapping("/")
	public String index(Model model, RestTemplate restTemplate) {
	ResponseEntity<Competition[]> responseEntity = restTemplate.getForEntity
	("http://localhost:8080/competitions/", Competition[].class);
	model.addAttribute("competitionList", responseEntity.getBody());
	model.addAttribute("competition", new Competition());
	model.addAttribute("competitionList", da.findAll());
	return "index";
	}
	
	@GetMapping(value="/getCompetition/{id}", produces="application/json")
	@ResponseBody
	public Competition getCompetition(@PathVariable int id, RestTemplate restTemplate) {
	ResponseEntity<Competition> responseEntity = restTemplate.getForEntity
	(REST_URL + id, Competition.class);
	return responseEntity.getBody();
	}
	
	@PostMapping("/insertCompetition")
    public String insertCalendar(Model model, @ModelAttribute Competition competition) {
		da.save(competition);
		model.addAttribute("competition", new Competition());
		model.addAttribute("competitionList", da.findAll());
		return "index";
}
	@GetMapping("/deleteById/{id}")
	public String deleteCompetitionById(Model model, @PathVariable Long id) {
		
		da.deleteById(id);
		model.addAttribute("competition", new Competition());
		model.addAttribute("competitionList", da.findAll());
		
		return "index";
	}

	
}

