/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacoco.examples.java.gradle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Никита
 */
   //@JsonIgnoreProperties({"field_object", "field_map"})

class SerializingObject {

    @JsonSubTypes({
        @JsonSubTypes.Type(value = FieldObject.class, name = "field_object"),
        //@JsonSubTypes.Type(value = FieldMap.class, name = "field_map")
    })
   @Expose
   @SerializedName("field_string")
   private String fieldString;
   @Expose
   @SerializedName("field_number")
   private int fieldNumber;
   @Expose
   @SerializedName("field_object")   
   private FieldObject fieldObject;
   @Expose
   @SerializedName("field_map")   
   private Map<String, Object> fieldMap;
   private String mapString;
   private int mapNumber;
   
    public String getMapString() {
        return mapString;
    }
    
    @JsonProperty("field_map")
    private void unpackStringFromNestedObject(Map<String, Object> FieldMap){
        mapString = FieldMap.get("field_string").toString();
        mapNumber = (int)FieldMap.get("field_number");
    }
    public void setFieldObject(FieldObject fieldObject) {
        this.fieldObject = fieldObject;
    }

    public void setFieldMap(Map<String, Object> fieldMap) {
        this.fieldMap = fieldMap;
    }
    
    public void setMapString(String mapString) {
        this.mapString = mapString;
        this.fieldMap.replace("field_string", mapString);
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
        this.fieldMap.replace("field_number", mapNumber);
    }
       
   @JsonCreator
   public SerializingObject(
      @JsonProperty("field_string") String fString, 
      @JsonProperty("field_number") int fNumber,
      @JsonProperty("field_object") FieldObject fObject,
      @JsonProperty("field_map") HashMap<String,Object> fMap){
       fieldString = fString;
       fieldNumber = fNumber;
       fieldObject = fObject;
       fieldMap = fMap;
       mapNumber = (int)fieldMap.get("field_number");
       mapString = (String)fieldMap.get("field_string").toString().trim();
   }
   
   public SerializingObject(String fString, int fNumber){
       fieldString = mapString = fString;
       fieldNumber = mapNumber = fNumber;
       fieldObject = new FieldObject(fieldString, fieldNumber);
       fieldMap = new HashMap<String, Object>();
       fieldMap.put("field_string", mapString);
       fieldMap.put("field_number", mapNumber);
   }
   
   @JsonTypeName("field_object")
   public static class FieldObject{
        @Expose
        @SerializedName("field_string")
        private final String objectString;
        @Expose
        @SerializedName("field_number")
        private final int objectNumber;
        
        @JsonCreator
        public FieldObject(
                @JsonProperty("field_string")String st,
                @JsonProperty("field_number")int num){
        objectNumber = num;
        objectString = st;
        }
        
        @JsonProperty("field_string")
        public String getObjectString() {
            return objectString;
        }
        @JsonProperty("field_number")
        public int getObjectNumber() {
            return objectNumber;
        }
        @Override
        public String toString(){
            return "{\n\t\t\"field_number\": "+objectNumber
              +",\n\t\t\"field_string\": \""+ objectString
              +"\"\n\t}";
        }
   } 

   @JsonProperty("field_string")
   public String getFieldString() {
      return fieldString;
   }
	
   public void setFieldString(String fieldString) {
      this.fieldString = fieldString;
   }

   @JsonProperty("field_number")	
   public int getFieldNumber() {
      return fieldNumber;
   }
	
   public void setFieldNumber(int fieldNumber) {
      this.fieldNumber = fieldNumber;
   }

   @JsonProperty("field_object")
   public FieldObject getFieldObject() {
      return fieldObject;
   }   

   @JsonProperty("field_map")
   public Map<String, Object> getFieldMap() {
      return fieldMap;
      //fieldMap.values();
   }    
   
   @Override
   public String toString(){
      return "{\n\t\"field_number\": "+fieldNumber
              +",\n\t\"field_string\": "+ fieldString
              +",\n\t\"field_object\": "+fieldObject
              +",\n\t\"field_map\": "+fieldMap
              +"\n}";
   }
}
