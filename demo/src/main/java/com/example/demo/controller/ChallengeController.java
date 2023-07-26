package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.ChallengeDTO;
import com.example.demo.model.ChallengeEntity;
import com.example.demo.service.ChallengeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("challenge")
public class ChallengeController {
	
	@Autowired
	private ChallengeService service;
	
	//Create
	@PostMapping
	public ResponseEntity<?> createChallenge(
			@AuthenticationPrincipal String userId,
			@RequestBody ChallengeDTO dto) {
		try {
			// (1) ChallengeEntity로 변환한다.
			ChallengeEntity entity = ChallengeDTO.toEntity(dto);

			// (2) id를 null로 초기화 한다. 생성 당시에는 id가 없어야 하기 때문이다.
			entity.setId(null);

			// (3) 유저 아이디를 설정 해 준다.
			entity.setUserId(userId);
			
			// (4) 서비스를 이용해 Challenge엔티티를 생성한다.
			List<ChallengeEntity> entities = service.createWithRelation(entity);

			// (5) 자바 스트림을 이용해 리턴된 엔티티 리스트를 ChallengeDTO리스트로 변환한다.
			List<ChallengeDTO> dtos = entities.stream().map(ChallengeDTO::new).collect(Collectors.toList());

			// (6) 변환된 ChallengeDTO리스트를 이용해 ResponseDTO를 초기화한다.
			ResponseDTO<ChallengeDTO> response = ResponseDTO.<ChallengeDTO>builder().data(dtos).build();

			// (7) ResponseDTO를 리턴한다.
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			// (8) 혹시 예외가 나는 경우 dto대신 error에 메시지를 넣어 리턴한다.
			String error = e.getMessage();
			ResponseDTO<ChallengeDTO> response = ResponseDTO.<ChallengeDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	// Retrieve
	@GetMapping
	public ResponseEntity<?> retrieveChallengeList(
			@AuthenticationPrincipal String userId) {
		// (1) 서비스 메서드의 retrieve메서드를 사용해 Challenge리스트를 가져온다
		List<ChallengeEntity> entities = service.retrieve(userId);

		// (2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 ChallengeDTO리스트로 변환한다.
		List<ChallengeDTO> dtos = entities.stream().map(ChallengeDTO::new).collect(Collectors.toList());

		// (3) 변환된 ChallengeDTO리스트를 이용해ResponseDTO를 초기화한다.
		ResponseDTO<ChallengeDTO> response = ResponseDTO.<ChallengeDTO>builder().data(dtos).build();

		// (4) ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	}
	
	// Update
	
	// Delete
	
	// Retrieve All Challenge
	@GetMapping("/all")
	public ResponseEntity<?> retrieveAllChallengeList(
			@AuthenticationPrincipal String userId) {
		// (1) 서비스 메서드의 retrieveAll메서드를 사용해 모든 Challenge 리스트를 가져온다
		List<ChallengeEntity> entities = service.retrieveAll();

		// (2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 ChallengeDTO리스트로 변환한다.
		List<ChallengeDTO> dtos = entities.stream().map(ChallengeDTO::new).collect(Collectors.toList());

		// (3) 변환된 ChallengeDTO리스트를 이용해ResponseDTO를 초기화한다.
		ResponseDTO<ChallengeDTO> response = ResponseDTO.<ChallengeDTO>builder().data(dtos).build();

		// (4) ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	}
	
	// Retrieve All Challenge Sorted By Added Date
	@GetMapping("/all/date")
	public ResponseEntity<?> retrieveAllChallengeListSortedByDate(
				@AuthenticationPrincipal String userId) {
		// (1) 서비스 메서드의 retrieveAll메서드를 사용해 모든 Challenge 리스트를 가져온다
		List<ChallengeEntity> entities = service.retrieveAllSortedByDate();

		// (2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 ChallengeDTO리스트로 변환한다.
		List<ChallengeDTO> dtos = entities.stream().map(ChallengeDTO::new).collect(Collectors.toList());

		// (3) 변환된 ChallengeDTO리스트를 이용해ResponseDTO를 초기화한다.
		ResponseDTO<ChallengeDTO> response = ResponseDTO.<ChallengeDTO>builder().data(dtos).build();

		// (4) ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	}
	
	// Retrieve All Challenge Sorted By Participant Count
	@GetMapping("/all/count")
	public ResponseEntity<?> retrieveAllChallengeListSortedByParticipantCount(
					@AuthenticationPrincipal String userId) {
		// (1) 서비스 메서드의 retrieveAll메서드를 사용해 모든 Challenge 리스트를 가져온다
		List<ChallengeEntity> entities = service.retrieveAllSortedByParticipantCount();

		// (2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 ChallengeDTO리스트로 변환한다.
		List<ChallengeDTO> dtos = entities.stream().map(ChallengeDTO::new).collect(Collectors.toList());

		// (3) 변환된 ChallengeDTO리스트를 이용해ResponseDTO를 초기화한다.
		ResponseDTO<ChallengeDTO> response = ResponseDTO.<ChallengeDTO>builder().data(dtos).build();

		// (4) ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	}
	
}

