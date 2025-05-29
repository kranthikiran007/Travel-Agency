package com.kk.dtos;

import java.util.List;

import com.kk.validations.Create;
import com.kk.validations.Update;

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
	@NotNull(message = "Staring Location cannot be Empty",groups = Create.class)
	private String startLoc;
	@NotNull(message = "Package Cost cannot be Empty",groups = Create.class)
	@Positive(message = "Package Cost cannot be Negative",groups = {Create.class,Update.class})
	private double cost;
	@NotNull(message = "Destination Location cannot be Empty",groups = Create.class)
	private String destinationLoc;
	@NotNull(message = "Locations Covered cannot be Empty",groups = Create.class)
	private List<String> locationsCovered;
	@NotNull(message = "Package name cannot be Empty",groups = Create.class)
	private String packageName;

}
