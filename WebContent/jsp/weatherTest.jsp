<%@page import="constant.Key"%>
<%@page import="api.TempaeratureAPI"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="api.WeatherAPI"%>
<%@page import="constant.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%
	String key = "70d78e61bdb96cf13e612f9908e948d0";
	WeatherAPI weatherAPI = new WeatherAPI();
	TempaeratureAPI tempAPI = new TempaeratureAPI();
	List<HashMap<String,String>> tempList = tempAPI.getTemperature();
	List<HashMap<String,String>> weatherList = weatherAPI.getWeather();
	String tempMin = tempList.get(0).get("taMin3");
	String tempMax = tempList.get(0).get("taMax3");
	String amWeather = weatherList.get(0).get("wf3Am");
	String pmWeather = weatherList.get(0).get("wf3Pm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Daum 지도 시작하기</title>
</head>
<body>
	<div id="map" style="width: 500px; height: 400px;"></div>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=<%=key%>"></script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=<%=key%>&libraries=LIBRARY"></script>
		<script type="text/javascript" src="js/arrayList.js"></script>
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div

		mapLevel = 13,
		
		mapOption = {
			center : new daum.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표
			level : mapLevel
		// 지도의 확대 레벨
		};

		var map = new daum.maps.Map(mapContainer, mapOption);

		// 마커가 표시될 위치입니다 
		var markerPosition = new daum.maps.LatLng(36.2683, 127.6358);

		// 마커를 생성합니다
		var marker = new daum.maps.Marker({
			position : markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
		
		var infowindow;
		var infowindowList = new ArrayList();

		daum.maps.event.addListener(map, 'zoom_changed', function() {        
		    
		    // 지도의 현재 레벨을 얻어옵니다
		    var level = map.getLevel();
			if(level == 14){
				var iwContent = '<div style="padding:5px;"><p style="font-size:3px;">오전 : <%=amWeather%> 최저 기온 : <%=tempMin%><br>오후 : <%=pmWeather%> 최고 기온 : <%=tempMax%></p></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
				iwPosition = new daum.maps.LatLng(36.2683, 127.6358), //인포윈도우 표시 위치입니다
			    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

			    infowindow = new daum.maps.InfoWindow({
					position : iwPosition,	
					content : iwContent,
				    removable : iwRemoveable
				});
			    
			    
				// 인포윈도우를 생성합니다
				infowindowList.add(infowindow);

				// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
				infowindow.open(map, marker);
			    
				}else if(infowindowList.length() > 0){
					for(var i = 0; i < infowindowList.length(); i++)
						infowindowList.get(i).close();
				}
				
				
				
					    
		});
		
		
	</script>
</body>
</html>