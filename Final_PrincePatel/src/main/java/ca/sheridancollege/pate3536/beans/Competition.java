package ca.sheridancollege.pate3536.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Competition {

	private Long id;
	@NonNull
	private String projectName;
	private String description;
	private String developer;
	private Integer rate;
}
