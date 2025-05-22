package com.kk.dtos;

import com.kk.entity.Address;

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
	@NotNull(message = "First name must be given")
	@Pattern(regexp = "[A-Za-z\s]{3,}",message = "Last Name can contain only alphabets")
	private String firstName;
	@NotNull(message = "Last name must be given")
	@Pattern(regexp = "[A-Za-z\s]{3,}",message = "Last Name can contain only alphabets")
	private String lastName;
	@Pattern(regexp = "\\d{10}",message = "Phone number must contain only numbers and of 10 digits")
	private String phone;
	@Pattern(regexp = "[A-Za-z0-9\s]{0,256}",message = "Length must be 256 at Max")
	private String notes;
	@Valid
	private AddressDto permenantAdd;
	@Valid
	private AddressDto communicationAdd;
	@Valid
	private TourDto tour;
}
