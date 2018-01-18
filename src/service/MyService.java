package service;

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

import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

@Service
public class MyService {
	public List<HashMap<String, String>> getFestivalInfo() throws Exception {
		List<HashMap<String, String>> result = new ArrayList<>();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		StringBuilder urlBuilder = new StringBuilder(
				"http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + constant.Key.DAUMKEY); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "="
				+ URLEncoder.encode("SERVICE_KEY", "UTF-8")); /* 서비스인증 */
		urlBuilder.append(
				"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /* 현재페이지결과수 */
		urlBuilder.append(
				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 현재페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "="
				+ URLEncoder.encode("ETC", "UTF-8")); /* IOS(아이폰),AND(안드로이드),WIN(원도우폰),ETC */
		urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "="
				+ URLEncoder.encode("AppTest", "UTF-8")); /* 서비스명=어플명 */
		urlBuilder.append("&" + URLEncoder.encode("arrange", "UTF-8") + "=" + URLEncoder.encode("A",
				"UTF-8")); /* (A=제목순,B=조회순,C=수정순,D=생성일순) 대표이미지 정렬추가 (O=제목순,P=조회순,Q=수정일순,R=생성일순) */
		urlBuilder.append("&" + URLEncoder.encode("listYN", "UTF-8") + "="
				+ URLEncoder.encode("Y", "UTF-8")); /* 목록구분(Y=목록,N=개수) */
		
		 // 1. 서울 2. 인천 3. 대전 4. 대구 5.광주  6. 부산 7. 울산 8. 충청남도 공주  ...
		urlBuilder
				.append("&" + URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 지역코드 */
		
		urlBuilder.append(
				"&" + URLEncoder.encode("sigunguCode", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 시군구코드 */
		urlBuilder.append("&" + URLEncoder.encode("eventStartDate", "UTF-8") + "="
				+ URLEncoder.encode(format.format(new Date()), "UTF-8")); /* 행사시작일(형식:YYYYMMDD) */
		urlBuilder.append("&" + URLEncoder.encode("eventEndDate", "UTF-8") + "="
				+ URLEncoder.encode("", "UTF-8")); /* 행사종료일(형식:YYYYMMDD) */
		/*urlBuilder
		.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode("12", "UTF-8")); /* 관광타입 ID*/
		
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		
		System.out.println("Response code: 200");
		
		XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
		parser.setInput(new InputStreamReader(conn.getInputStream()));
		
		List<Map<String, String>> festivalList = new ArrayList<>();
		Map<String, String> festival;
		
		for(int eventType = parser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = parser.next()) {
			
			if(eventType == XmlPullParser.START_TAG && parser.getName().equals("item")) {
				festivalList.add(festival = new HashMap<>());
				for(eventType = parser.next(); eventType != XmlPullParser.END_TAG || !parser.getName().equals("item"); eventType = parser.next()) {
					if(eventType == XmlPullParser.START_TAG)
					if(parser.getName().equals("addr1") || parser.getName().equals("title") || parser.getName().equals("mapy") || parser.getName().equals("mapx"))
						festival.put(parser.getName(), parser.nextText());
				}
			}
		}
		
		festivalList.forEach(fe -> {
			System.out.println("-------------------------------------------------------------------------");
			HashMap<String, String> map = new HashMap<>();
			fe.forEach((k, v) -> {
				System.out.println(k + " : " + v);
				map.put(k, v);
			});
			result.add(map);
		});
		
		conn.disconnect();
		
		return result;
	}
}
