<%@ page pageEncoding="Windows-31J"
 contentType="text/html;charset=Windows-31J"
%>

<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

		<script>
			$(function(){
				var today = new Date();
				today.setDate(today.getDate()-today.getDay());
				for(var i=0;i<4;i++){
					today.setDate(today.getDate()-today.getDay()+7);
					$("#option").append("<option value='"+today.getFullYear()+"/"+(today.getMonth()+1)+"/"+today.getDate()+"'>"+today.getFullYear()+"年"+(today.getMonth()+1)+"月"+today.getDate()+"日</option>");
				}
			});

			function tuika(){
				var movie_Details_Minute = document.getElementById('movie_Details_Minute');
    			var clone = movie_Details_Minute.cloneNode(true);
				// // div要素を生成
				// var div = document.createElement('div');
				// // classを追加
				// div.className = 'sample';

				// 生成したdiv要素を追加する
				document.getElementById('movie_Details').appendChild(clone);
			}

			

		</script>

        <title>
			上映スケジュール管理
		</title>
    </head>
    <body>
		<h1>上映スケジュール管理</h1>
<!--ーーーーーーーー映画館名とタイトルで検索し上映スケジュールの情報を取得するーーーーーーーー-->
        <form method='post' action='schedulesearchservlet'><!--ScheduleSearchServletへ-->
			映　画　館　<input type="text" size="20" name="theater_name" value="${theater_name}"><br>
			映画タイトル<input type="text" size="20" name="title" value="${title}">
			<input type='submit' value='検索'>
		</form>
<!--ーーーーーーーーーーーーーーーーーースケジュールーーーーーーーーーーーーーーーーーーーー-->
<!--ーーーーーーーーーーーーーーーーーーーーループーーーーーーーーーーーーーーーーーーーーー-->
		<c:forEach var="schedulelist" items="${schedulelist}">
			<form method='post' action='scheduledeleteservlet'><!--ScheduleDeleteServletへ-->
				<div id="movie_Details1">
					<div id="movie_Details_Minute1">
						<!--年/月/日-->
						<input type="text" size="20" name="release_period" style="width: 70px;" value="${schedulelist.release_period}"><br>
						<!--開始時間-->
						<input type="text" size="20" name="start_time" style="width: 40px;" value="${schedulelist.start_time}">
						~<!--終了時間-->
						<input type="text" size="20" name="end_time" style="width: 40px;" value="${schedulelist.end_time}"><br>
						<!--シアター番号-->
						<input type="text" size="20" name="theater" style="width: 80px;" value="${schedulelist.theater}"><br>
						<!--一意の番号　見えなくする-->
						<input type="text" size="20" name="schedule_detail_id" style="width: 25px;" value="${schedulelist.schedule_detail_id}">
						<!--映画館　タイトル　見えなくする-->
						<input type="hidden" size="20" name="theater_name" value="${theater_name}">
						<input type="hidden" size="20" name="title" value="${title}">
					</div>
					<input type='submit' value='変更' formaction='schedulechangeservlet'>　<input type='submit' value='削除'>
				 </div>
			</form>
			<form method='post' action='schedulechangeservlet' id="schedulechangeservlet"></form>
		</c:forEach>
<!--ーーーーーーーーーーーーーーーーーー追加用ーーーーーーーーーーーーーーーーーーーー--><br><p>ーーー追加用ーーーーーー</p>
		<form method='post' action='scheduleaddservlet'><!--ScheduleAddServletへ-->

			<!-- 年/月/日 プルダウン js -->
			<select id="option" name="date">
			</select>
			<input type="button" onclick="tuika()" value="div追加">
			<br><br>
			<div id="movie_Details">
				<div id="movie_Details_Minute">
					<!--開始時間 何時:何分-->
					<input type="text" size="20" name="start_time" style="width: 110px;" value="11:00">
					~<!--終了時間 何時:何分-->
					<input type="text" size="20" name="end_time" style="width: 110px;" value="13:00"><br>
					<!--シアター番号-->
					<input type="text" size="20" name="theater" style="width: 90px;" value="シアター2"><br>
					<!--映画館　見えなくする-->
					<input type="hidden" size="20" name="theater_name" value="${theater_name}">
					<!--映画タイトル　見えなくする-->
					<input type="hidden" name="title" value="${title}">
					<p>----------------------------------------------------------------------------------------</p>
				</div>
			</div>
			<input type='submit' value='追加'>
		</form>

		<a href="kanriTop.html">管理トップへ</a>
        <a href="kanrimovie">映画管理画面へ</a>
    </body>
</html>

