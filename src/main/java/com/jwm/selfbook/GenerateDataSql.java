package com.jwm.selfbook;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * This class is used solely to create the INSERT statements for the data.sql file in the resources folder.  Run only if the json file changes.
 */
public class GenerateDataSql {

    public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();

        try {
            Object obj = jsonParser.parse(new FileReader("movies.json"));

            StringBuilder dataSqlSb = new StringBuilder("INSERT INTO MOVIE (id, title) ");
            JSONObject jsonObject = (JSONObject) obj;

            dataSqlSb.append(addMovies((JSONArray) jsonObject.get("movies")));
            System.out.println(dataSqlSb.toString());
            System.out.println("\n\n\n");

//            dataSqlSb = new StringBuilder("INSERT INTO USER (user_id) ");
//            dataSqlSb.append(addUser((JSONArray) jsonObject.get("users")));
//            System.out.println(dataSqlSb.toString());
//            System.out.println("\n\n\n");

            dataSqlSb = new StringBuilder("INSERT INTO USERS (user_id, movie_id) ");
            dataSqlSb.append(addUserSelections((JSONArray) jsonObject.get("users")));
            System.out.println(dataSqlSb.toString());
            System.out.println("\n\n\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private static String addMovies(JSONArray movies) {
        StringBuilder sb = new StringBuilder("VALUES \n");
        String comma = "  ";
        for (Object movie : movies) {
            JSONObject entry = (JSONObject) movie;
            sb.append(comma).append("(").append(entry.get("id")).append(", ").append("\'").append(sanitizeObject(entry.get("title"))).append("\'").append(")").append("\n");
            comma = ", ";
        }
        sb.append(";");
        sb.append("\n");
        return sb.toString();
    }

    private static String addUser(JSONArray users) {
        StringBuilder sb = new StringBuilder("VALUES \n");
        String comma = "  ";
        Long userId = 0L;
        for(Object user : users) {
            JSONObject entry = (JSONObject) user;
            userId = (Long) entry.get("user_id");
            sb.append(comma).append("(").append(userId).append(")\n");
            comma = ", ";
        }
        sb.append(";");
        sb.append("\n");
        return sb.toString();
    }

    private static String addUserSelections(JSONArray users) {
        StringBuilder sb = new StringBuilder("VALUES \n");
        String comma = "  ";
        Long userId = 0L;
        for(Object user : users) {
            JSONObject entry = (JSONObject) user;
            userId = (Long) entry.get("user_id");
            for (Object movie : (JSONArray) entry.get("movies")) {
                sb.append(comma).append("(").append(userId).append(", ").append(movie).append(")").append("\n");
                comma = ", ";
            }
            comma = ", ";
        }
        sb.append(";");
        sb.append("\n");
        return sb.toString();
    }

    private static String sanitizeObject(Object obj) {
        return ((String) obj).replaceAll("'", "''");
    }
}
