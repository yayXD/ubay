package world.ucode.utilits;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonUtils {

    public static String jsonToString (HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();
        String inputLine = null;
        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }
        return new String(stringBuilder);
    }

    public static JSONObject jsonToJsonObject(String json) {
        JSONParser parser = new JSONParser();
        JSONObject jo;
        try {
            jo = (JSONObject) parser.parse(String.valueOf(json));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            jo = null;
        }
        return jo;
    }
}
