package resources.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GlobalProperties {

	public static String getGolobalValues(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("global.properties");
		prop.load(fis);
		return prop.getProperty(key);

	}

	public static String getJsonPath(Response response, String key) {
		String res = response.asString();
		JsonPath jp = new JsonPath(res);
		return jp.get(key).toString();
	}

}
