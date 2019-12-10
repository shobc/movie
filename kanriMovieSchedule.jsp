<%@ page pageEncoding="Windows-31J"
 contentType="text/html;charset=Windows-31J"
%>

<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
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
				<div id="movie_Details">
					<div id="movie_Details_Minute">
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
			<div id="movie_Details">
				<div id="movie_Details_Minute">
					<!--年/月/日 何時:何分 開始時間-->
					<input type="text" size="20" name="start_time" style="width: 110px;" value="2019/11/22 11:00">
					~<!--年/月/日 何時:何分 終了時間-->
					<input type="text" size="20" name="end_time" style="width: 110px;" value="2019/11/22 13:00"><br>
					<!--シアター番号-->
					<input type="text" size="20" name="theater" style="width: 90px;" value="シアター2"><br>
					<!--映画館　見えなくする-->
					<input type="hidden" size="20" name="theater_name" value="${theater_name}">
					<!--映画タイトル　見えなくする-->
					<input type="hidden" name="title" value="${title}">
				</div>
				<input type='submit' value='追加'>
			</div>
		</form>

		<a href="kanriTop.html">管理トップへ</a>
        <a href="kanrimovie">映画管理画面へ</a>
    </body>
</html>

