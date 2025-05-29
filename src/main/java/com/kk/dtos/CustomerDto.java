package com.kk.dtos;

import com.kk.validations.Create;
import com.kk.validations.Update;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
	private int id;
	@NotNull(message = "First name must be given",groups = Create.class)
	@Pattern(regexp = "[A-Za-z\s]{2,}", message = "First Name can contain only alphabets",groups = {Create.class,Update.class} )
	private String firstName;
	@NotNull(message = "Last name must be given",groups = Create.class)
	@Pattern(regexp = "[A-Za-z\s]{2,}", message = "Last Name can contain only alphabets",groups = {Create.class,Update.class})
	private String lastName;
	@NotNull(message = "Phone must be given",groups = Create.class)
	@Pattern(regexp = "\\d{10}", message = "Phone number must contain only numbers and of 10 digits",groups = {Create.class,Update.class} )
	private String phone;
	@NotNull(message = "Notes must be given",groups = Create.class)
	@Pattern(regexp = "[A-Za-z0-9\s]{0,256}", message = "Length must be 256 at Max",groups = {Create.class,Update.class})
	private String notes;
	@Valid
	@NotNull(message = "Permanent Address must be given",groups = Create.class)
	private AddressDto permenantAdd;
	@Valid
	@NotNull(message = "Communication Address must be given",groups = Create.class)
	private AddressDto communicationAdd;
	@Valid
	@NotNull(message = "Tour Address must be given",groups = Create.class)
	private TourDto tour;
}
