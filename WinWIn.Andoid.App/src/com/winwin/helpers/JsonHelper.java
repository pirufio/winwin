package com.winwin.helpers;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonHelper {

	private static JsonHelper instance = null;
	private static ObjectMapper mapper;
	protected JsonHelper() {
	      // Exists only to defeat instantiation.
	   }
	
	public static JsonHelper getInstance() {
		 if(instance == null) {
	         instance = new JsonHelper();
	      }
		 return instance;
	}
	
	private static ObjectMapper getMapper(){
		if(mapper==null)
			mapper = new ObjectMapper();
		return mapper;
	}
	

	public String objectToJsonString(Object object){

		String jsonString = null;
		try {
			mapper =  getMapper();
			jsonString = mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public <T> T readString(String result, Class<T> c){
		Object readValue = null;
		mapper =  getMapper();
		try {
			readValue = mapper.readValue(result,c);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T) readValue;
	}
	
}
