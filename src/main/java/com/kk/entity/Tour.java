package com.kk.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
	@Table(name="tours")
	public class Tour {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String startLoc;
		private double cost;
		private String destinationLoc;
		@ElementCollection
		@CollectionTable(name = "tour_locations", joinColumns = @JoinColumn(name = "location_id"))
		@Column(name = "location")
		private List<String> locationsCovered;
		private String packageName;
	
	}
