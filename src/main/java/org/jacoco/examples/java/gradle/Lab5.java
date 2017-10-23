package org.jacoco.examples.java.gradle;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;



public class Lab5 {
    private String jsonString;        
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Gson gson = new Gson();
    
    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public SerializingObject gsonDeSerialize(String targetJsonString){

         try{
           if(targetJsonString.length() == 0)return null;
           SerializingObject serObj = gson.fromJson(targetJsonString, SerializingObject.class);
           System.out.println(serObj);
           return serObj;
         }
          catch (Exception e) { e.printStackTrace();}
          return null;

     }   
    public String gsonSerialize(SerializingObject serObj){

         try{
           return gson.toJson(serObj);
         }
          catch (JsonIOException e) { e.printStackTrace();}
          return null;

     }

    public SerializingObject jacksonDeSerialize(String targetJsonString){

       try{
           if(targetJsonString.length() == 0)return null;
           //SerializingObject serObj = new SerializingObject("wadup", 2);
           SerializingObject serObj = mapper.readValue(targetJsonString, SerializingObject.class);
           System.out.println(serObj);
           return serObj;
             //System.out.println(targetJsonString);
          }
          catch (JsonParseException e) { e.printStackTrace();}
          catch (JsonMappingException e) { e.printStackTrace(); }
          catch (IOException e) { e.printStackTrace(); }
          return null;

    }
    public String jacksonSerialize(SerializingObject serObj){
       try{
           String res = mapper.writeValueAsString(serObj.toString());
        return serObj.toString();
       }
         catch (JsonParseException e) { e.printStackTrace();}
         catch (JsonMappingException e) { e.printStackTrace(); }
         catch (IOException e) { e.printStackTrace(); }       
         return null;
   }
    
    public String orgJsonSerialize(SerializingObject serObj){
        try {
             Map<String, Object> objectDataMap = new HashMap<String, Object>();
             objectDataMap.put("field_number", serObj.getFieldNumber());
             objectDataMap.put("field_string", serObj.getFieldString());
             objectDataMap.put("field_object", serObj.getFieldObject());
             objectDataMap.put("field_map", serObj.getFieldMap());
             JSONObject root = new JSONObject(objectDataMap);

             String json = root.toString();
             System.out.println(json);
             return json;  
         }
         catch (JSONException ex) {
             ex.printStackTrace();
         }
         return null;
    } 
    public SerializingObject orgJsonDeserialize(String targetJsonString){
        try {
             if(targetJsonString.length() == 0)return null;
             JSONObject jsonObj = new JSONObject(targetJsonString);
             SerializingObject jsonParsed = new SerializingObject(
                     jsonObj.getString("field_string"),
                     jsonObj.getInt("field_number"));

             System.out.println(jsonParsed);
             return jsonParsed;
         }
         catch (JSONException ex) {
             ex.printStackTrace();
         }
         return null; 
    } 
}


