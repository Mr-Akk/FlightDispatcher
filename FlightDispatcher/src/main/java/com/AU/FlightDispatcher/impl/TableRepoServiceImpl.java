package com.AU.FlightDispatcher.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.AU.FlightDispatcher.dao.TableDataSchema;
import com.AU.FlightDispatcher.service.TableRepoService;

@Service
public class TableRepoServiceImpl implements TableRepoService {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public Optional<Object> getDataInitial() {
		
		String sql = "select req.flightNo,req.flightZdate,req.legNo,req.origin,\n"
				+ "req.destination,output.release_number,req.timeofrequest \n"
				+ " from plan_request req\n"
				+ "join plan_output_xfx output\n"
				+ "on req.oidval = output.oidval and req.iterationnumber = output.iteration_number\n"
				+ "where output.release_number > 0 and output.time_dispatcher_signed is not null";
		
		List<TableDataSchema> allTableData = namedParameterJdbcTemplate.query(sql,new ResultSetExtractor<List<TableDataSchema>>() {
			@Override
			public List<TableDataSchema> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<TableDataSchema> tableData = new ArrayList<TableDataSchema>();
				while (rs.next()) {
					TableDataSchema eachData = new TableDataSchema(
							rs.getInt(1),rs.getDate(2),rs.getInt(3),
							rs.getString(4),rs.getString(5),rs.getInt(6),
							rs.getTimestamp(7)
							); 
						tableData.add(eachData);
				}
			if(tableData.size() > 0) {
				return tableData;
			}
			return null;
			}
		});
		if (allTableData == null) {
			return Optional.empty();
		}
		return Optional.of(allTableData);
	}
	
	@Override
	public Optional<Object> getDataByTime(Integer time) {
		
		String sql = "select req.flightNo,req.flightZdate,req.legNo,req.origin,\n"
				+ "req.destination,output.release_number,req.timeofrequest \n"
				+ "from plan_request req\n"
				+ "join plan_output_xfx output\n"
				+ "on req.oidval = output.oidval \n"
				+ "where req.timeofrequest>=date_sub(now(),interval :time hour)";
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("time", time);
		List<TableDataSchema> allTableData = namedParameterJdbcTemplate.query(sql,map ,new ResultSetExtractor<List<TableDataSchema>>() {
			@Override
			public List<TableDataSchema> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<TableDataSchema> tableData = new ArrayList<TableDataSchema>();
				while (rs.next()) {
					TableDataSchema eachData = new TableDataSchema(
							rs.getInt(1),rs.getDate(2),rs.getInt(3),
							rs.getString(4),rs.getString(5),rs.getInt(6),
							rs.getTimestamp(7)
							); 
						tableData.add(eachData);
				}
			if(tableData.size() > 0) {
				return tableData;
			}
			return null;
			}
		});
		if(allTableData==null) {
			return Optional.empty();
		}
		return Optional.of(allTableData);
	}

	@Override
	public void getDatabyOrigin(String origin) {
		String sql = "select req.flightNo,req.flightZdate,req.legNo,req.origin,\n"
				+ "req.destination,output.release_number,req.timeofrequest \n"
				+ " from plan_request req\n"
				+ "join plan_output_xfx output\n"
				+ "on req.oidval = output.oidval and req.iterationnumber = output.iteration_number\n"
				+ "where output.release_number > 0 and output.time_dispatcher_signed is not null\n"
				+ "and req.origin= :origin";
		Map<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put("origin", origin);
		List<TableDataSchema> queryList = namedParameterJdbcTemplate.query(sql,sqlMap, new ResultSetExtractor<List<TableDataSchema>>() {
			@Override
			public List<TableDataSchema> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<TableDataSchema> queryList = new ArrayList<TableDataSchema>();
				while (rs.next()) {
					TableDataSchema eachData = new TableDataSchema(
							rs.getInt(1),rs.getDate(2),rs.getInt(3),
							rs.getString(4),rs.getString(5),rs.getInt(6),
							rs.getTimestamp(7)
							); 
						queryList.add(eachData);
				}
				return queryList;
			}
		});
		System.out.println(queryList);
	}

	@Override
	public void getDatabyDestination(String destination) {
		String sql = "select req.flightNo,req.flightZdate,req.legNo,req.origin,\n"
				+ "req.destination,output.release_number,req.timeofrequest \n"
				+ " from plan_request req\n"
				+ "join plan_output_xfx output\n"
				+ "on req.oidval = output.oidval and req.iterationnumber = output.iteration_number\n"
				+ "where output.release_number > 0 and output.time_dispatcher_signed is not null\n"
				+ "and req.destination= :destination";
		Map<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put("destination", destination);
		List<TableDataSchema> queryList = namedParameterJdbcTemplate.query(sql,sqlMap, new ResultSetExtractor<List<TableDataSchema>>() {
			@Override
			public List<TableDataSchema> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<TableDataSchema> queryList = new ArrayList<TableDataSchema>();
				while (rs.next()) {
					TableDataSchema eachData = new TableDataSchema(
							rs.getInt(1),rs.getDate(2),rs.getInt(3),
							rs.getString(4),rs.getString(5),rs.getInt(6),
							rs.getTimestamp(7)
							); 
						queryList.add(eachData);
				}
				return queryList;
			}
		});
		System.out.println(queryList);
	}

	@Override
	public void getDatabyOriginDestination(String origin, String destination) {
		String sql = "select req.flightNo,req.flightZdate,req.legNo,req.origin,\n"
				+ "req.destination,output.release_number,req.timeofrequest \n"
				+ " from plan_request req\n"
				+ "join plan_output_xfx output\n"
				+ "on req.oidval = output.oidval and req.iterationnumber = output.iteration_number\n"
				+ "where output.release_number > 0 and output.time_dispatcher_signed is not null\n"
				+ "and req.origin= :origin and req.destination= :destination";
		Map<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put("origin", origin);
		sqlMap.put("destination", destination);
		List<TableDataSchema> queryList = namedParameterJdbcTemplate.query(sql,sqlMap, new ResultSetExtractor<List<TableDataSchema>>() {
			@Override
			public List<TableDataSchema> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<TableDataSchema> queryList = new ArrayList<TableDataSchema>();
				while (rs.next()) {
					TableDataSchema eachData = new TableDataSchema(rs.getInt(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getTimestamp(7)); 
						queryList.add(eachData);
				}
				return queryList;
			}
		});
		System.out.println(queryList);
	}
}
