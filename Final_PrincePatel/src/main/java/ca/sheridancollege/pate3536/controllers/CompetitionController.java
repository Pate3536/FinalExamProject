package ca.sheridancollege.pate3536.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.pate3536.beans.Competition;

import ca.sheridancollege.pate3536.database.DatabaseAccess;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/competitions")
public class CompetitionController {

	private DatabaseAccess da;
	
	@GetMapping
	public List<Competition> getCompetitionCollection() {
	return da.findAll();
	}
	
	@GetMapping(value = "/{id}") // "value" only here to illustrate our Mappings can do more!
	public Competition getIndividualCompetition(@PathVariable Long id) {
	return da.findById(id);
	}
	
	@PostMapping(consumes = "application/json")
	public String postStudent(@RequestBody Competition competition) {
	return "http://localhost:8080/competitions/" + da.save(competition);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteIndividualCompetition(@PathVariable Long id) {
		System.out.println(id);
		da.deleteById(id);
		}
}
