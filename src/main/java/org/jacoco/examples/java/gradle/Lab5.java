package org.jacoco.examples.java.gradle;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.examples.java.gradle.SerializingObject.FieldObject;
import org.json.JSONException;
import org.json.JSONObject;




public class Lab5 {
    private String jsonString;        
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public SerializingObject gsonDeSerialize(String targetJsonString){

         try{
           if(targetJsonString.length() == 0)return null;                   
           Map<String, Object> responseObj = new HashMap<>();
           responseObj = gson.fromJson(targetJsonString, HashMap.class);           
           JSONObject jsonObject = new JSONObject(responseObj);
           responseObj = jsonObject.getJSONObject("field_map").toMap();
           responseObj.replace("field_number",((Double)responseObj.get("field_number")).intValue());

           SerializingObject serObj = new SerializingObject(
                   jsonObject.getString("field_string"),
                   jsonObject.getInt("field_number"),
                   new FieldObject(
                    jsonObject.getJSONObject("field_object").getString("field_string"),
                    jsonObject.getJSONObject("field_object").getInt("field_number")),
                    (HashMap<String, Object>)(responseObj));
           System.out.println(serObj);
           return serObj;
         }
          catch (JsonSyntaxException | JSONException e) { e.printStackTrace();}
          return null;

     }   
    public String gsonSerialize(SerializingObject serObj){

         try{
           System.out.println(gson.toJson(serObj));
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
           
        System.out.println(mapper.writeValueAsString(serObj.toString()));
        return mapper.writeValueAsString(serObj.toString());
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

             System.out.println(root.toString());
             return root.toString();  
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
             jsonParsed.setFieldObject(new FieldObject(
                     jsonObj.getJSONObject("field_object").getString("field_string"),
                     jsonObj.getJSONObject("field_object").getInt("field_number")));
             jsonParsed.setMapString(jsonObj.getJSONObject("field_map").getString("field_string"));
             jsonParsed.setMapNumber(jsonObj.getJSONObject("field_map").getInt("field_number"));
             
             System.out.println(jsonParsed);
             return jsonParsed;
         }
         catch (JSONException ex) {
             ex.printStackTrace();
         }
         return null; 
    } 
}


