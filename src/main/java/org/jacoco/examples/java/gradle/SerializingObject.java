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

/**
 *
 * @author Никита
 */
   //@JsonIgnoreProperties({"field_object", "field_map"})

class SerializingObject {

    @JsonSubTypes({
        @JsonSubTypes.Type(value = FieldObject.class, name = "field_object"),
        @JsonSubTypes.Type(value = FieldMap.class, name = "field_map")
    })
    
   private String fieldString;
   private int fieldNumber;
   private FieldObject fieldObject;
   private FieldMap fieldMap;
   
   @JsonCreator
   public SerializingObject(
      @JsonProperty("field_string") String fString, 
      @JsonProperty("field_number") int fNumber,
      @JsonProperty("field_object") FieldObject fObject,
      @JsonProperty("field_map") FieldMap fMap){
       fieldString = fString;
       fieldNumber = fNumber;
       fieldObject = fObject;
       fieldMap = fMap;
   }
   
   public SerializingObject(String fString, int fNumber){
       fieldString = fString;
       fieldNumber = fNumber;
       fieldObject = new FieldObject();
       fieldMap = new FieldMap();
   }
   
   @JsonTypeName("field_object")
   public class FieldObject{
       
        private final String objectString;
        private final int objectNumber;
        @JsonCreator
        public FieldObject()
        {
        objectNumber = fieldNumber;
        objectString = fieldString;
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
   
   @JsonTypeName("field_map")
   public class FieldMap{
        private final String mapString;
        private final int mapNumber;
        @JsonCreator
        public FieldMap()
        {
        mapNumber = fieldNumber;
        mapString = fieldString;
        }
        @JsonProperty("field_string")
        public String getMapString() {
            return mapString;
        }
        @JsonProperty("field_number")
        public int getMapNumber() {
            return mapNumber;
        }
        
        @Override
        public String toString(){
            return "{\n\t\t\"field_string\": "+mapString
              +",\n\t\t\"field_number\": \""+ mapNumber
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
   public FieldMap getFieldMap() {
      return fieldMap;
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
