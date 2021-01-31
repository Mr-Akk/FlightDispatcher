package com.AU.FlightDispatcher.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AU.FlightDispatcher.dao.CardContentSchema;
import com.AU.FlightDispatcher.service.CardRepoService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/card")
public class CardController {
	
	@Autowired
	CardRepoService CardRepoServiceImpl;
	
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private Logger logger = LoggerFactory.getLogger(CardController.class);
	
	@GetMapping("/getDomesticRelease")
	public ResponseEntity<Object> getDomesticRelease() {
		Integer domesticRelease = CardRepoServiceImpl.getDomesticRelease();
		CardContentSchema cardContent = new CardContentSchema(domesticRelease+"");
		logger.info("Domestic Release count was sent");
		return ResponseEntity.ok(cardContent);
	}
	
	@GetMapping("/getInternationalRelease")
	public ResponseEntity<Object> getInternationalRelease() {
		Integer internationalRelease = CardRepoServiceImpl.getInternationalRelease();
		CardContentSchema cardContent = new CardContentSchema(internationalRelease+"");
		logger.info("International Release count was sent");
		return ResponseEntity.ok(cardContent);
	}
		
	@GetMapping("/getTotalIterations")
	public ResponseEntity<Object> getTotalIterations(){
		Integer totalIterations = CardRepoServiceImpl.getTotalIterations();
		CardContentSchema cardContent = new CardContentSchema(totalIterations+"");
		logger.info("Total iteration count was sent");
		return ResponseEntity.ok(cardContent);
	}	
	

}


