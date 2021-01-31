package com.AU.FlightDispatcher.impl;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.AU.FlightDispatcher.service.CardRepoService;

@Service
public class CardRepoServiceImpl implements CardRepoService {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public Integer getDomesticRelease() {
		String sql = "select count(*) from plan_request req\n"
				+ "join plan_output_xfx output\n"
				+ "on req.oidval = output.oidval and req.iterationnumber = output.iteration_number\n"
				+ "where output.release_number > 0 and output.time_dispatcher_signed is not null\n"
				+ "and req.timeofrequest >= date_sub(now(),interval 1 hour)";
		Integer domesticReleaseCount = namedParameterJdbcTemplate.query(sql , new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				Integer count = 0;
				while (rs.next()) {
					count = rs.getInt(1);		
				}
				return count;
			}
		});
		return domesticReleaseCount;
}
	

	@Override
	public Integer getInternationalRelease() {
		String sql = "select count(*) from plan_request req\n"
				+ "join plan_output_xfx output\n"
				+ "on req.oidval = output.oidval and req.iterationnumber = output.iteration_number\n"
				+ "where output.release_number > 0 and output.time_dispatcher_signed is not null\n"
				+ "and req.timeofrequest >= date_sub(now(),interval 1 hour) and \n"
				+ "(substr(req.origin,1,1) not in ('K','k') or \n"
				+ "substr(req.destination,1,1) not in ('K','k'))";
		Integer internationalReleaseCount = namedParameterJdbcTemplate.query(sql , new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				Integer count = 0;
				while (rs.next()) {
					count = rs.getInt(1);		
				}
				return count;
			}
		});
		return internationalReleaseCount;
	}

	@Override
	public Integer getTotalIterations() {
		
		String sql = "select sum(iterationnumber ) from plan_request\n"
				+ "where timeofrequest>=date_sub(now(),interval 1 hour)";
		Integer totalIterations = namedParameterJdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				Integer totalIterations = 0;
				while (rs.next()) {
					totalIterations = rs.getInt(1);
				}
				return totalIterations;
			}	
		});
		return totalIterations;
	}
	
	
	
	

}
