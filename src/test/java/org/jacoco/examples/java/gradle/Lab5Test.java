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
        assertEquals("FieldMaps aren't equal", so.getFieldMap().toString(), labClass.orgJsonDeserialize(jsonString).getFieldMap().toString());
    }

    /**
     * Test of orgJsonSerialize method, of class Lab5.
     */
    @Test
    public void testOrgJsonSerialize() {
        System.out.println("orgJsonSerialize");
        assertNotEquals("Err", jsonString, labClass.orgJsonSerialize(so));
        assertFalse("FieldNumbers aren't equal", labClass.orgJsonSerialize(so).contains("\"field_number\": 1"));
        assertFalse("FieldStrings aren't equal", labClass.orgJsonSerialize(so).contains("\"field_string\": \"qwerty\""));
        assertFalse("FieldObjects aren't equal", labClass.orgJsonSerialize(so).contains(
                    "\"field_object\": {\n"+
                    "		\"field_number\": 1,\n" +
                    "		\"field_string\": \"qwerty\"\n" +
                    "	}"));
        assertFalse("FieldMaps aren't equal", labClass.orgJsonSerialize(so).contains(
                    "\"field_map\": {\n" +
                    "		\"field_string\": \"qwerty\",\n" +
                    "		\"field_number\": 1\n" +
                    "	}"));
    }

    /**
     * Test of gsonDeSerialize method, of class Lab5.
     */
    @Test
    public void testGsonDeSerialize() {
        labClass.setJsonString(jsonString);
        System.out.println("gsonDeserialize");
        assertEquals("Err", 1, labClass.gsonDeSerialize(jsonString).getFieldNumber());
        //assertEquals("FieldNumbers aren't equal",1,labClass.gsonDeSerialize(jsonString).getFieldNumber());
        //assertEquals("FieldStrings aren't equal", "qwerty", labClass.gsonDeSerialize(jsonString).getFieldString());
        //assertEquals("FieldObjects aren't equal", so.getFieldObject().toString(), labClass.gsonDeSerialize(jsonString).getFieldObject().toString());
        //assertEquals("FieldMaps aren't equal", so.getFieldMap().toString(), labClass.gsonDeSerialize(jsonString).getFieldMap().toString());
    }

    /**
     * Test of gsonSerialize method, of class Lab5.
     */
    @Test
    public void testGsonSerialize() {
        System.out.println("gsonSerialize");
        assertEquals("Err", jsonString, labClass.gsonSerialize(so));
        assertTrue("FieldNumbers aren't equal", labClass.gsonSerialize(so).contains("\"field_number\": 1"));
        assertTrue("FieldStrings aren't equal", labClass.gsonSerialize(so).contains("\"field_string\": \"qwerty\""));
        assertTrue("FieldObjects aren't equal", labClass.gsonSerialize(so).contains(
                    "\"field_object\": {\n"+
                    "		\"field_number\": 1,\n" +
                    "		\"field_string\": \"qwerty\"\n" +
                    "	}"));
        assertTrue("FieldMaps aren't equal", labClass.orgJsonSerialize(so).contains(
                    "\"field_map\": {\n" +
                    "		\"field_string\": \"qwerty\",\n" +
                    "		\"field_number\": 1\n" +
                    "	}"));
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
        assertEquals("FieldMaps aren't equal", so.getFieldMap().toString(), labClass.jacksonDeSerialize(jsonString).getFieldMap().toString());
    }

    /**
     * Test of jacksonSerialize method, of class Lab5.
     */
    @Test
    public void testJacksonSerialize() {
        System.out.println("jacksonSerialize");
        assertEquals("Err", jsonString, labClass.jacksonSerialize(so).toString());
        assertTrue("FieldNumbers aren't equal", labClass.jacksonSerialize(so).contains("\"field_number\": 1"));
        assertTrue("FieldStrings aren't equal", labClass.jacksonSerialize(so).contains("\"field_string\": \"qwerty\""));
        assertTrue("FieldObjects aren't equal", labClass.jacksonSerialize(so).contains(
                    "\"field_object\": {\n"+
                    "		\"field_number\": 1,\n" +
                    "		\"field_string\": \"qwerty\"\n" +
                    "	}"));
        assertTrue("FieldMaps aren't equal", labClass.orgJsonSerialize(so).contains(
                    "\"field_map\": {\n" +
                    "		\"field_string\": \"qwerty\",\n" +
                    "		\"field_number\": 1\n" +
                    "	}"));
    }
    
}
