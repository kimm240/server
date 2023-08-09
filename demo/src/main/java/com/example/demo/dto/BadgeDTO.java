package com.example.demo.dto;

import com.example.demo.model.BadgeEntity;
import com.example.demo.model.ParticipatingChallengeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;  

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BadgeDTO {
	private String id;
	private String name;
	private String imagePath;
	
	public BadgeDTO(final BadgeEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.imagePath = entity.getImagePath();
	}
}


