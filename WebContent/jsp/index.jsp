<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>예제</title>
<style>
table, #map {
	float: left;
}
</style>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>이름</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="festval" items="${festvalInfo }">
				<tr>
					<td><a onclick="setMap('${festval.mapy}', '${festval.mapx }', '${festval.title }')">${festval.title }</a></td>
					<td>${festval.addr1 }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 지도를 표시할 div 입니다 -->
	<div id="map" style="width: 500px; height: 500px;"></div>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=70d78e61bdb96cf13e612f9908e948d0"></script>
	<script>
		var mapContainer = document.getElementById('map'); // 지도를 표시할 div 
		var map;

		if (navigator.geolocation) { // GPS를 지원하면
			navigator.geolocation.getCurrentPosition(function(position) {
				mapOption = {
					center : new daum.maps.LatLng(position.coords.latitude, position.coords.longitude), // 지도의 중심좌표
					level : 3 // 지도의 확대 레벨
				};
				map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
			}, function(error) {
				console.error(error);
			}, {
				enableHighAccuracy : false,
				maximumAge : 0,
				timeout : Infinity
			});
		} else {
			alert('GPS를 지원하지 않습니다');
		}
		
		function setMap(latitude, longitude, title) {
			// 마커가 표시될 위치입니다 
			var markerPosition = new daum.maps.LatLng(latitude, longitude);
			// 마커를 생성합니다
			var marker = new daum.maps.Marker({
				position : markerPosition
			});
			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);
	
	
			var iwContent = '<div style="padding:5px;">' + title + '<br><a href="http://map.daum.net/link/map/' + title + ','+ latitude +', '+ longitude +' " style="color:blue" target="_blank">큰지도보기</a> <a href="http://map.daum.net/link/to/' + title + ',' + latitude + ',' + longitude + '" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
				iwPosition = new daum.maps.LatLng(latitude, longitude); //인포윈도우 표시 위치입니다
	
			// 인포윈도우를 생성합니다
			var infowindow = new daum.maps.InfoWindow({
				position : iwPosition,
				content : iwContent
			});
	
			// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
			infowindow.open(map, marker);
			
			map.panTo(markerPosition);
		}
	</script>
</body>
</html>