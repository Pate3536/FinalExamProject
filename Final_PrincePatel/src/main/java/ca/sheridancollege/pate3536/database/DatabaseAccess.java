package ca.sheridancollege.pate3536.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.pate3536.beans.Competition;




@Repository
public class DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public List<Competition> findAll() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM competition ORDER BY rate DESC";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Competition>(Competition.class));
		}

	public Competition findById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM competition  WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Competition>(Competition.class)).get(0);
		}
	
	public Long save(Competition competition) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		String query = "INSERT INTO competition(projectName,description,developer,rate)"
				+ "VALUES(:projectName, :description, :developer, :rate)";
				namedParameters.addValue("projectName", competition.getProjectName());
				namedParameters.addValue("description", competition.getDescription());
				namedParameters.addValue("developer", competition.getDeveloper()); 
				namedParameters.addValue("rate",competition.getRate());
				

		jdbc.update(query, namedParameters, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKey();
		}
	
	public void deleteById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM competition  WHERE id = :id";
		namedParameters.addValue("id", id);
		 jdbc.update(query, namedParameters);
		
		}
	
}
