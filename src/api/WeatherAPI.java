package api;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.util.SimpleAnnotationValueVisitor6;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;

public class WeatherAPI {
	public List<HashMap<String, String>> getWeather() throws IOException, XmlPullParserException {
		Date today = new Date();
		String[] middleWeatherCodeList = {"11B00000","11D10000","11D20000","11C20000","11C10000","11F20000","11F10000","11H10000","11H20000","11G0000"};
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		StringBuilder urlBuilder = new StringBuilder(
				"http://newsky2.kma.go.kr/service/MiddleFrcstInfoService/getMiddleLandWeather"); /* URL */
		urlBuilder.append(
				"?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + constant.Constant.WEATHER); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "="
				+ URLEncoder.encode("TEST_SERVICE_KEY", "UTF-8")); /* 서비스 키 */
		urlBuilder.append("&" + URLEncoder.encode("regId", "UTF-8") + "="
				+ URLEncoder.encode("11B00000", "UTF-8")); /* 예보구역코드 *활용가이드 참조 */
		
		urlBuilder.append("&" + URLEncoder.encode("tmFc", "UTF-8") + "="
				+ URLEncoder.encode(date.format(today) + "0600", "UTF-8")); /* 2017년 7월 20일 06시 발표 * 활용가이드 참조 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append(
				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지 번호 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
//		StringBuilder sb = new StringBuilder();
//		String line;
//		while((line = rd.readLine()) != null) {
//			sb.append(line);
//		}
		
		XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
		parser.setInput(new InputStreamReader(conn.getInputStream()));

		List<HashMap<String, String>> weatherList = new ArrayList<>();
		HashMap<String, String> weather = new HashMap<>();

		for (int eventType = parser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = parser
				.next()) {

			if (eventType == XmlPullParser.START_TAG && parser.getName().equals("item")) {
				weatherList.add(weather = new HashMap<>());
				for (eventType = parser.next(); eventType != XmlPullParser.END_TAG
						|| !parser.getName().equals("item"); eventType = parser.next()) {
					if (eventType == XmlPullParser.START_TAG)
						if (parser.getName().equals("wf3Am") || parser.getName().equals("wf3Pm")) {
							weather.put(parser.getName(), parser.nextText());
						}
				}
			}
		}
		rd.close();
		conn.disconnect();
		return weatherList;
	}
}
