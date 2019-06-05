import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    //9461979ba0f5caf12f88a2d2ca66bc1d

    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&appid=9461979ba0f5caf12f88a2d2ca66bc1d");

        Scanner sc = new Scanner((InputStream) url.getContent());
        String result = "";
        while (sc.hasNext()) {
            result += sc.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));


        JSONArray jsonArray = object.getJSONArray("weather");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));
        }


        return "Город " + model.getName() + "\n"
                + "Температура  " + (model.getTemp()) + "F" + "\n"
                + "Влажность " + model.getHumidity() + "%" + "\n"
                + "http://openweathermap.org/img/w/" + model.getIcon() + ".png";


    }

}
