package com.jwm.selfbook;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;

/**
 * This class is used solely to create the INSERT statements for the data.sql file in the resources folder.  Run only if the json file changes.
 */
public class GenerateDataSql {

    static Logger logger = LoggerFactory.getLogger(GenerateDataSql.class);
    private static final String NEW_LINE = "\n";
    private static final String NEW_LINES = NEW_LINE + NEW_LINE + NEW_LINE;
    private static final String VALUE_NL = "VALUES \n";
    private static final String OPEN_PAREN = "(";
    private static final String CLOSE_PAREN = (")");
    private static final String SEMICOLON = ";";

    public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();

        try {
            Object obj = jsonParser.parse(new FileReader("movies.json"));

            StringBuilder dataSqlSb = new StringBuilder("INSERT INTO MOVIES (movie_id, title) ");
            JSONObject jsonObject = (JSONObject) obj;

            dataSqlSb.append(addMovies((JSONArray) jsonObject.get("movies")));
            logger.info("dataSqlSb:\n  {}", dataSqlSb);
            logger.info(NEW_LINES);

            dataSqlSb = new StringBuilder();
            dataSqlSb.append(addCustomersAndMovies((JSONArray) jsonObject.get("users")));
            logger.info("dataSqlSb:\n  {}", dataSqlSb);
            logger.info(NEW_LINES);

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private static String addMovies(JSONArray movies) {
        StringBuilder sb = new StringBuilder(VALUE_NL);
        String comma = "  ";
        for (Object movie : movies) {
            JSONObject entry = (JSONObject) movie;
            sb.append(comma).append(OPEN_PAREN).append(entry.get("id")).append(", ").append("\'").append(sanitizeObject(entry.get("title"))).append("\'").append(CLOSE_PAREN).append(NEW_LINE);
            comma = ", ";
        }
        sb.append(SEMICOLON);
        sb.append(NEW_LINE);
        return sb.toString();
    }

    private static String addCustomersAndMovies(JSONArray users) {
        StringBuilder user = new StringBuilder("INSERT INTO CUSTOMERS (customer_id) ").append(VALUE_NL);
        StringBuilder userMovie = new StringBuilder("INSERT INTO CUSTOMERS_MOVIES (customer_id, movie_id) ").append(VALUE_NL);
        String comma = "  ";
        Long userId;

        for (Object u : users) {
            JSONObject entry = (JSONObject) u;
            userId = (Long) entry.get("user_id");
            user.append(comma).append(OPEN_PAREN).append(userId).append(CLOSE_PAREN).append(NEW_LINE);
            for (Object movie : (JSONArray) entry.get("movies")) {
                userMovie.append(comma).append(OPEN_PAREN).append(userId).append(", ").append(movie).append(CLOSE_PAREN).append(NEW_LINE);
                comma = ", ";
            }
            comma = ", ";
        }

        user.append(SEMICOLON).append(NEW_LINE);
        userMovie.append(SEMICOLON).append(NEW_LINE);
        return new StringBuilder(user).append(userMovie).toString();

    }

    private static String sanitizeObject(Object obj) {
        return ((String) obj).replace("'", "''");
    }
}
