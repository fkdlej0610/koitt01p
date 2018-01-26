package service;

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

import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

@Service
public class APIService {
	String contentid2;
	
	//축제
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
	
	//키워드 검색
	public List<HashMap<String, String>> searchAPIInfo(String search, String contenttypeid, String areaCode) throws Exception {
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
        urlBuilder.append("&" + URLEncoder.encode("contentTypeId","UTF-8") + "=" + URLEncoder.encode(contenttypeid, "UTF-8")); /*관광타입(관광지, 숙박 등) ID*/
        urlBuilder.append("&" + URLEncoder.encode("cat1","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*대분류코드*/
        urlBuilder.append("&" + URLEncoder.encode("cat2","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*중분류코드(대분류코드 필수)*/
        urlBuilder.append("&" + URLEncoder.encode("cat3","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*소분류코드(중분류,대분류코드 필수)*/
        urlBuilder.append("&" + URLEncoder.encode("arrange","UTF-8") + "=" + URLEncoder.encode("B", "UTF-8")); /*A=제목순,B=인기순,C=최근수정순,D=등록순*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        

        XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();

        parser.setInput(new InputStreamReader(conn.getInputStream()));

        

        for(int eventType = parser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = parser.next()) {

        	if(eventType == XmlPullParser.START_TAG && parser.getName().equals("item")) {

        		for(eventType = parser.next(); eventType != XmlPullParser.END_TAG || !parser.getName().equals("item"); eventType = parser.next()) {

    				
        			if(eventType == XmlPullParser.START_TAG && (parser.getName().equals("title") || parser.getName().equals("addr1") 
        					|| parser.getName().equals("mapy") || parser.getName().equals("mapx") || parser.getName().equals("contentid") 
        					|| parser.getName().equals("contenttypeid")))

        				value.put(parser.getName(), parser.nextText()); //HashMap안에 값을 담아줌

        			}
        		result.add(value); //result안에 HashMap담기
        		
        		value = new HashMap<String, String>(); //HashMap값 초기화(다른값을 다시 담으려고)

        	}
        	

        }

 
        rd.close();

        conn.disconnect();
	
        return result;
	}	
	
	//반복정보조회(숙소 사진 등)
	public List<HashMap<String, String>> Detail(String contentid, String contenttypeid) throws Exception {
		List<HashMap<String, String>> result = new ArrayList<>();
		HashMap<String, String> value = new HashMap<>();
		 StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailInfo"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + constant.Constant.TOURKEY); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("Servicekey","UTF-8") + "=" + URLEncoder.encode("인증키", "UTF-8")); /*공공데이터포털에서 발급받은 인증키*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
	        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰),AND(안드로이드),WIN(원도우폰),ETC*/
	        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
	        urlBuilder.append("&" + URLEncoder.encode("contentId","UTF-8") + "=" + URLEncoder.encode(contentid, "UTF-8")); /*콘텐츠 ID*/
	        urlBuilder.append("&" + URLEncoder.encode("contentTypeId","UTF-8") + "=" + URLEncoder.encode(contenttypeid, "UTF-8")); /*관광타입(관광지, 숙박 등)ID*/
	        urlBuilder.append("&" + URLEncoder.encode("detailYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*반복정보 조회여부*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
//	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }

	        XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
	        parser.setInput(new InputStreamReader(conn.getInputStream()));

	        for(int eventType = parser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = parser.next()) {
	        	if(eventType == XmlPullParser.START_TAG && parser.getName().equals("item")) {
	        		for(eventType = parser.next(); eventType != XmlPullParser.END_TAG || !parser.getName().equals("item"); eventType = parser.next()) {
	        			if(eventType == XmlPullParser.START_TAG && (parser.getName().equals("roomtitle") 
	        					|| parser.getName().equals("roomtitle")
	        					|| parser.getName().equals("roomsize1") 
	        					|| parser.getName().equals("roomcount")
	        					|| parser.getName().equals("roombasecount")
	        					|| parser.getName().equals("roommaxcount")
	        					|| parser.getName().equals("roomoffseasonminfee1")
								|| parser.getName().equals("roomoffseasonminfee2")
								|| parser.getName().equals("roompeakseasonminfee1")
								|| parser.getName().equals("roompeakseasonminfee2")
								|| parser.getName().equals("roomimg1")
								|| parser.getName().equals("roomimg2")
								|| parser.getName().equals("infoname")
								|| parser.getName().equals("infotext")))
	        				value.put(parser.getName(), parser.nextText());
	        			}
    				result.add(value);
    				value = new HashMap<String, String>(); //초기화 안하면 같은값만 들어감
	        	}
	        }
			return result;
	}

	
}


