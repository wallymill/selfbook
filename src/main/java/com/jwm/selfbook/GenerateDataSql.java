package com.jwm.selfbook;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * This class is used solely to create the INSERT statements for the data.sql file in the resources folder.  Run only if the json file changes.
 */
public class GenerateDataSql {

    public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();

        try {
            Object obj = jsonParser.parse(new FileReader("movies.json"));
            System.out.println(obj.toString());
            JSONObject jsonObject = (JSONObject)obj;
//            String name = (String)jsonObject.get("Name");
//            String course = (String)jsonObject.get("Course");
            JSONArray movies = (JSONArray)jsonObject.get("movies");
//            System.out.println("Name: " + name);
//            System.out.println("Course: " + course);
            System.out.println("Movies:");
            Iterator iterator = movies.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
