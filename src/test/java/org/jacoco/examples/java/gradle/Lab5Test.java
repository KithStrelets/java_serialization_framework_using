/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacoco.examples.java.gradle;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Никита
 */
public class Lab5Test {
    public String jsonString = "{\n\"field_number\": 1,\n" +
                    "\t\"field_string\": \"qwerty\",\n" +
                    "\t\"field_object\": {\n" +
                    "\t\t\"field_number\": 1,\n" +
                    "\t\t\"field_string\": \"qwerty\"\n" +
                    "\t},\n" +
                    "\t\"field_map\": {\n" +
                    "\t\t\"field_string\": \"qwerty\",\n" +
                    "\t\t\"field_number\": 1\n" +
                    "\t}\n" +
                    "}";
    
    Lab5 labClass = new Lab5();
    SerializingObject so = new SerializingObject("qwerty", 1);
    Metrics metrics = new Metrics();
    @Test
    public void testMetricsOfMethods(){
        System.out.println("\nMETRICS_GSONdeserialize");
        Metrics.start();
        labClass.gsonDeSerialize(jsonString);
        Metrics.stop();
        Metrics.getAllMetrics();
        System.out.println("\nMETRICS_GSONserialize");
        Metrics.start();
        labClass.gsonSerialize(so);
        Metrics.stop();
        Metrics.getAllMetrics();
        System.out.println("\nMETRICS_JSONdeserialize");
        Metrics.start();
        labClass.orgJsonDeserialize(jsonString);
        Metrics.stop();
        Metrics.getAllMetrics();
        System.out.println("\nMETRICS_JSONserialize");
        Metrics.start();
        labClass.orgJsonSerialize(so);
        Metrics.stop();
        Metrics.getAllMetrics();
        System.out.println("\nMETRICS_JACKSONdeserialize");
        Metrics.start();
        labClass.jacksonDeSerialize(jsonString);
        Metrics.stop();
        Metrics.getAllMetrics();
        System.out.println("\nMETRICS_JACKSONserialize");
        Metrics.start();
        labClass.jacksonSerialize(so);
        Metrics.stop();
        Metrics.getAllMetrics();
    }
    
    /**
     * Test of orgJsonDeserialize method, of class Lab5.
     */
    @Test
    public void testOrgJsonDeserialize() {
        labClass.setJsonString(jsonString);
        System.out.println("orgJsonDeserialize");
        assertEquals("FieldNumbers aren't equal", 1, labClass.orgJsonDeserialize(jsonString).getFieldNumber());
        assertEquals("FieldStrings aren't equal", "qwerty", labClass.orgJsonDeserialize(jsonString).getFieldString());
        assertEquals("FieldObjects aren't equal", so.getFieldObject().toString(), labClass.orgJsonDeserialize(jsonString).getFieldObject().toString());
        assertEquals("FieldMaps aren't equal", so.getFieldMap(), labClass.orgJsonDeserialize(jsonString).getFieldMap());
        assertEquals("Map strings aren't equal", "qwerty", labClass.orgJsonDeserialize(jsonString).getMapString());
        assertEquals("Map numbers aren't equal", 1, labClass.orgJsonDeserialize(jsonString).getMapNumber());
    }

    /**
     * Test of orgJsonSerialize method, of class Lab5.
     */
    @Test
    public void testOrgJsonSerialize() {
        System.out.println("orgJsonSerialize");
        assertTrue("FieldNumbers aren't equal", labClass.orgJsonSerialize(so).contains("\"field_number\":1"));
        assertTrue("FieldStrings aren't equal", labClass.orgJsonSerialize(so).contains("\"field_string\":\"qwerty\""));
        assertTrue("FieldObjects aren't equal", labClass.orgJsonSerialize(so).contains(
                    "\"field_object\":{\"objectNumber\":1,\"objectString\":\"qwerty\"}"));
        assertTrue("FieldMaps aren't equal", labClass.orgJsonSerialize(so).contains(
                    "\"field_map\":{\"field_number\":1,\"field_string\":\"qwerty\"}"));
    }

    /**
     * Test of gsonDeSerialize method, of class Lab5.
     */
    @Test
    public void testGsonDeSerialize() {
        labClass.setJsonString(jsonString);
        System.out.println("gsonDeserialize");
        assertEquals("FieldNumbers aren't equal",1,labClass.gsonDeSerialize(jsonString).getFieldNumber());
        assertEquals("FieldStrings aren't equal", "qwerty", labClass.gsonDeSerialize(jsonString).getFieldString());
        assertEquals("FieldObjects aren't equal", so.getFieldObject().toString(), labClass.gsonDeSerialize(jsonString).getFieldObject().toString());
        assertEquals("FieldMaps aren't equal", so.getFieldMap(), labClass.gsonDeSerialize(jsonString).getFieldMap());
        assertEquals("Map strings aren't equal", "qwerty", labClass.gsonDeSerialize(jsonString).getMapString());
        assertEquals("Map numbers aren't equal", 1, labClass.gsonDeSerialize(jsonString).getMapNumber());
    }

    /**
     * Test of gsonSerialize method, of class Lab5.
     */
    @Test
    public void testGsonSerialize() {
        System.out.println("gsonSerialize");
        assertTrue("FieldNumbers aren't equal", labClass.gsonSerialize(so).contains("\"field_number\":1"));
        assertTrue("FieldStrings aren't equal", labClass.gsonSerialize(so).contains("\"field_string\":\"qwerty\""));
        assertTrue("FieldObjects aren't equal", labClass.gsonSerialize(so).contains(
                    "\"field_object\":{\"field_string\":\"qwerty\",\"field_number\":1}"));
        assertTrue("FieldMaps aren't equal", labClass.gsonSerialize(so).contains(
                    "\"field_map\":{\"field_number\":1,\"field_string\":\"qwerty\"}"));
    }

    /**
     * Test of jacksonDeSerialize method, of class Lab5.
     */
    @Test
    public void testJacksonDeSerialize() {
        labClass.setJsonString(jsonString);
        System.out.println("jacksonDeserialize");
        assertEquals("FieldNumbers aren't equal", 1, labClass.jacksonDeSerialize(jsonString).getFieldNumber());
        assertEquals("FieldStrings aren't equal", "qwerty", labClass.jacksonDeSerialize(jsonString).getFieldString());
        assertEquals("FieldObjects aren't equal", so.getFieldObject().toString(), labClass.jacksonDeSerialize(jsonString).getFieldObject().toString());
        assertEquals("FieldMaps aren't equal", so.getFieldMap(), labClass.jacksonDeSerialize(jsonString).getFieldMap());
        assertEquals("Map strings aren't equal", "qwerty", labClass.jacksonDeSerialize(jsonString).getMapString());
        assertEquals("Map numbers aren't equal", 1, labClass.jacksonDeSerialize(jsonString).getMapNumber());
    }

    /**
     * Test of jacksonSerialize method, of class Lab5.
     */
    @Test
    public void testJacksonSerialize() {
        System.out.println("jacksonSerialize");
        assertTrue("FieldNumbers aren't equal", labClass.jacksonSerialize(so).contains("\\\"field_number\\\": 1"));
        assertTrue("FieldStrings aren't equal", labClass.jacksonSerialize(so).contains("\\\"field_string\\\": qwerty"));
        assertTrue("FieldObjects aren't equal", labClass.jacksonSerialize(so).contains(
                    "\\\"field_object\\\": {\\n\\t\\t\\\"field_number\\\": 1,\\n\\t\\t\\\"field_string\\\": \\\"qwerty\\\"\\n\\t}"));
        assertTrue("FieldMaps aren't equal", labClass.jacksonSerialize(so).contains(
                    "\\\"field_map\\\": {field_number=1, field_string=qwerty}"));
    }
    
}
