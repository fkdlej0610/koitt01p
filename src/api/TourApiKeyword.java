package api;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;

public class TourApiKeyword {
    public List<HashMap<String, Object>> xyCheck() throws IOException{
    	HashMap<String, Object> xy = new HashMap<String,Object>();
    	List<HashMap<String, Object>> result = new ArrayList<>();
        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+constant.Constant.TOURKEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("SERVICE_KEY", "UTF-8")); /*공공데이터에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("sample", "UTF-8")); /*서비스명*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*AND(안드로이드),IOS,ETC(web)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*표출건수*/
        urlBuilder.append("&" + URLEncoder.encode("keyword","UTF-8") + "=" + URLEncoder.encode("어린이대공원", "UTF-8")); /*강원(UTF-8인코딩)*/
        urlBuilder.append("&" + URLEncoder.encode("contentTypeId","UTF-8") + "=" + URLEncoder.encode("32", "UTF-8")); /*관광타입(관광지, 숙박 등) ID*/
        urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*지역코드(areaCode 없을때), 시군구코드(areaCode=1)*/
        urlBuilder.append("&" + URLEncoder.encode("catcodeYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*서비스분류코드(대,중,소 코드) 조회여부*/
        urlBuilder.append("&" + URLEncoder.encode("arrange","UTF-8") + "=" + URLEncoder.encode("B", "UTF-8")); /*A=제목순,B=인기순,C=최근수정순,D=등록순*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
       
        System.out.println(sb);
        
        int x = sb.indexOf("<mapx>");
        int xend = sb.indexOf("</mapx>");
        int y = sb.indexOf("<mapy>");
        int yend = sb.indexOf("</mapy>");
        
        
        System.out.println(sb.substring(x+6, xend));
        System.out.println(sb.substring(y+6, yend));
        
       xy.put("y", sb.substring(x+6, xend));
       xy.put("x", sb.substring(y+6, yend));
       result.add(xy);
       return result;
    }
}
