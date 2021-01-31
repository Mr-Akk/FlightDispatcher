package com.AU.FlightDispatcher.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AU.FlightDispatcher.service.TableRepoService;

@RestController
@RequestMapping("/table")
@CrossOrigin(origins = "http://localhost:4200")
public class TableController {
	
	@Autowired
	TableRepoService tableRepoServiceImpl;
	
	private Logger logger = LoggerFactory.getLogger(TableController.class);
	
	@GetMapping("/getDataInitial")
	public ResponseEntity<Object> getTableData(){
		Optional<Object> tableData = tableRepoServiceImpl.getDataInitial();
		if (tableData.isPresent()) {
			logger.info("Table data was sent");
			return ResponseEntity.ok(tableData);
		}
		logger.info("No table data present hence empty array was sent");
		return ResponseEntity.ok(new ArrayList<>());

	}
	
	@GetMapping("/getData/{time}")
	public ResponseEntity<Object> getTableDataByTime(@PathVariable("time") Integer time) {
		Optional<Object> tableData= tableRepoServiceImpl.getDataByTime(time);
		if(tableData.isPresent()) {
			logger.info("Table data was sent");
			return ResponseEntity.ok(tableData);
		}
		logger.info("No table data present hence empty array was sent");
		return ResponseEntity.ok(new ArrayList<>());
	}
	
	@GetMapping("/origin/{origin}")
	public void getTableDataByOrigin(@PathVariable("origin") String origin) {
		tableRepoServiceImpl.getDatabyOrigin(origin);
	}
	
	@GetMapping("/destination/{destination}")
	public void getTableDataByDestination(@PathVariable("destination") String destination) {
		tableRepoServiceImpl.getDatabyDestination(destination);
	}
	
	@GetMapping("/orgtodest/{origin}/{destination}")
	public void getTableDataByOriginDestination(@PathVariable("origin") String origin
			,@PathVariable("destination") String destination) {
		tableRepoServiceImpl.getDatabyOriginDestination(origin , destination);
	}
}
