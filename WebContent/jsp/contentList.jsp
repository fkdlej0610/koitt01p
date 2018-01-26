<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색</title>
<style>
table, #map {
	float: left;
}
.mapCursor {
	cursor: pointer;
}
</style>
</head>
<body>
	<form action="contentList.do">
		<select name="contentTypeId">
			<option value="32">숙박</option>
			<option value="12">관광지</option>
			<option value="24">행사/축제</option>
			<option value="39">맛집</option>
			<option value="14">문화시설</option>
			<option value="28">레포츠</option>
		</select> 
		
		<select name="areaCode">
			<option value="">전체</option>
			<option value="1">서울</option>
			<option value="2">인천</option>
			<option value="3">대전</option>
			<option value="4">대구</option>
			<option value="5">광주</option>
			<option value="7">부산</option>
			<option value="8">울산</option>
		</select>

		
		<input type="text" name="search" />
		<input type="submit" value="검색" />
	</form>
	<br/>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>주소</th>
			<th>ID</th>
		</tr>
		<c:forEach var="searchTest" items="${searchInfo }">
			<tr>
				<td class="mapCursor"><a onclick="setMap('${searchTest.mapy}', '${searchTest.mapx }', '${searchTest.title }')">${searchTest.title }</a></td>
				<td>${searchTest.addr1 }</td>
				<td><a href="contentView.do?contentid=${searchTest.contentid }&contenttypeid=${searchTest.contenttypeid}">바로가기</td>
			</tr>
		</c:forEach>
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