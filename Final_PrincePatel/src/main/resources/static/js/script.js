function getCompetition(id) {
	if (document.getElementById("competition" + id).innerHTML == "") {
		document.getElementById("competition" + id).innerHTML = "hello!";

		fetch('http://localhost:8080/getCompetition/' + id) // use HomeController to fetch from our service
		//fetch('http://localhost:8080/competitions/' + id) // use HomeController to fetch from our service
			.then(competition => competition.json()) // JSONify the data returned
			.then(function(competition) { // with the JSON data
				// modify textToDisplay below here!
				// finally, change our relevant div to display the var
				var textToDisplay = ""; // create and append to a blank var
				// textToDisplay += "ID: " + student.id + "<br>";
				textToDisplay += "Description: " + competition.description+ "<br>";
				textToDisplay += "Developer: " + competition.developer + "<br>";
				textToDisplay += "Rate: " + competition.rate + "<br>";
				


				document.getElementById("competition" + id).innerHTML = textToDisplay;
			});

	} else {
		document.getElementById("competition" + id).innerHTML = "";
	}
}
