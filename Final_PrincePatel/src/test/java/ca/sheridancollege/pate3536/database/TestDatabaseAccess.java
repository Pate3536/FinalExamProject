package ca.sheridancollege.pate3536.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import ca.sheridancollege.pate3536.beans.Competition;

@SpringBootTest
@AutoConfigureTestDatabase
public class TestDatabaseAccess {

	@Autowired
	private DatabaseAccess da;
	

	@Test
	public void whenInsertCompetition_getCompetitionList() {

		// setup
		int size = da.findAll().size();
		Competition competition = new Competition();
		competition.setProjectName("Food Delivery system");
		competition.setDescription("Very nice");
		competition.setDeveloper("Rahul");
		competition.setRate(6);
		// when
		da.save(competition);

		// then (the actual test!)
		assertEquals(da.findAll().size(), ++size);

	}
	
	

}
