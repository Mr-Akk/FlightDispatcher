package com.AU.FlightDispatcher.service;

import java.util.Optional;


public interface TableRepoService {

	Optional<Object> getDataByTime(Integer time);

	void getDatabyOrigin(String origin);

	void getDatabyDestination(String destination);

	void getDatabyOriginDestination(String origin, String destination);

	Optional<Object> getDataInitial();

}
