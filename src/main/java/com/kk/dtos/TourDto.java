package com.kk.dtos;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TourDto {
	private int id;
	@NotNull(message = "Staring Location cannot be Empty")
	private String startLoc;
	@NotNull(message = "Package Cost cannot be Empty")
	@Positive(message = "Package Cost cannot be Negative")
	private double cost;
	@NotNull(message = "Destination Location cannot be Empty")
	private String destinationLoc;
	private List<String> locationsCovered;
	@NotNull(message = "Package name cannot be Empty")
	private String packageName;

}
