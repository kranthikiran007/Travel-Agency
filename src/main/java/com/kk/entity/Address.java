	package com.kk.entity;
	
	
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;
	import jakarta.validation.constraints.Max;
	import jakarta.validation.constraints.Min;
	import jakarta.validation.constraints.NotNull;
	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.NoArgsConstructor;
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	@Entity
	@Table(name = "address")
	public class Address{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String houseNo;
		private String street;
		private String landMark;
		private String city;
		private String State;
		private long pinCode;
	}
