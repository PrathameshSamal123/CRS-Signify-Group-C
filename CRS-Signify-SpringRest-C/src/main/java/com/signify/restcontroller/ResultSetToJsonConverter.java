/**
 * 
 */
package com.signify.restcontroller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author Acer
 *
 */
public class ResultSetToJsonConverter {
	
	public static String convertResultSetToJson(ResultSet resultSet) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode resultJsonArray = objectMapper.createArrayNode();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(i);
                objectNode.put(columnName, String.valueOf(columnValue));
            }
            resultJsonArray.add(objectNode);
        }

        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultJsonArray);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
	}

}
