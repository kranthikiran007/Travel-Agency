package com.kk.dtos;

import com.kk.validations.Create;
import com.kk.validations.Update;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {
	private int id;
	@NotNull(message = "House number can't be empty",groups = Create.class)
	private String houseNo;
	@NotNull(message = "Street can't be empty",groups = Create.class)
	private String street;
	private String landMark;
	@NotNull(message = "City can't be empty",groups = Create.class)
	private String city;
	@NotNull(message = "State can't be empty",groups = Create.class)
	private String State;
	@NotNull(message = "Pin Code can't empty",groups = Create.class)
	@Max(value = 1000000,message = "Pin code must be 6 digit number",groups = {Create.class,Update.class})
	@Min(value = 100001,message = "Pin code must be 6 digit number",groups = {Create.class,Update.class})
	@Digits(integer = 6,fraction = 0,message = "Pincode must be of 6 digit",groups = {Create.class,Update.class})
	private long pinCode;
}
