package com.pandeys.resultextractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class StudentAddressGroupResultSetExtractor implements ResultSetExtractor<Map<String, List<String>>> {

	public Map<String, List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<String, List<String>> addressStudentsMap = new HashMap<String, List<String>>();
		while(rs.next()) {
			String name = rs.getString("Name");
			String address = rs.getString("Address");
			if(addressStudentsMap.containsKey(address)) {
				addressStudentsMap.get(address).add(name);
			} else {
				addressStudentsMap.put(address, new ArrayList<String>(Arrays.asList(name)) );
			}
		}
		return addressStudentsMap;
	}

}
