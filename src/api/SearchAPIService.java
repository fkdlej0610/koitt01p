package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class SearchAPIService {
	public List<HashMap<String, String>> searchAPIInfo(String search, String contentTypeId, String areaCode) throws Exception {
		List<HashMap<String, String>> result = new ArrayList<>();
		HashMap<String, String> value = new HashMap<>(); //검색값을 담아줄 HashMap선언
	
       StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" +constant.Key.DAUMKEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("SERVICE_KEY", "UTF-8")); /*공공데이터에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("sample", "UTF-8")); /*서비스명*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*AND(안드로이드),IOS,ETC(web)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("15", "UTF-8")); /*표출건수*/
        urlBuilder.append("&" + URLEncoder.encode("keyword","UTF-8") + "=" + URLEncoder.encode(search, "UTF-8")); /*키워드*/
        urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode(areaCode, "UTF-8")); /*지역코드(areaCode 없을때), 시군구코드(areaCode=1)*/
        urlBuilder.append("&" + URLEncoder.encode("sigunguCode","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*지역코드(areaCode 없을때), 시군구코드(areaCode=1)*/
        urlBuilder.append("&" + URLEncoder.encode("contentTypeId","UTF-8") + "=" + URLEncoder.encode(contentTypeId, "UTF-8")); /*관광타입(관광지, 숙박 등) ID*/
        urlBuilder.append("&" + URLEncoder.encode("cat1","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*대분류코드*/
        urlBuilder.append("&" + URLEncoder.encode("cat2","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*중분류코드(대분류코드 필수)*/
        urlBuilder.append("&" + URLEncoder.encode("cat3","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*소분류코드(중분류,대분류코드 필수)*/
        urlBuilder.append("&" + URLEncoder.encode("arrange","UTF-8") + "=" + URLEncoder.encode("B", "UTF-8")); /*A=제목순,B=인기순,C=최근수정순,D=등록순*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        

		XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
		parser.setInput(new InputStreamReader(conn.getInputStream()));
		for (int eventType = parser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = parser
				.next()) {
			if (eventType == XmlPullParser.START_TAG && parser.getName().equals("item")) {
				for (eventType = parser.next(); eventType != XmlPullParser.END_TAG
						|| !parser.getName().equals("item"); eventType = parser.next()) {
					if (eventType == XmlPullParser.START_TAG)
						if (parser.getName().equals("title") || parser.getName().equals("addr1")
								|| parser.getName().equals("mapx") || parser.getName().equals("mapy")
								|| parser.getName().equals("contentId"))
							value.put(parser.getName(), parser.nextText());
				}
				result.add(value);
				value = new HashMap<String, String>();
			}
		}
		rd.close();
		conn.disconnect();
		return result;
	}
	
	
}
