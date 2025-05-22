package com.kk.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {
	private int id;
	@NotNull(message = "House number can't be empty")
	private String houseNo;
	@NotNull(message = "Street can't be empty")
	private String street;
	private String landMark;
	@NotNull(message = "City can't be empty")
	private String city;
	@NotNull(message = "State can't be empty")
	private String State;
	@Digits(integer = 6,fraction = 0,message = "Pincode must be of 6 digit")
	private long pinCode;
}
