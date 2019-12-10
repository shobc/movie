<%@ page pageEncoding="Windows-31J"
 contentType="text/html;charset=Windows-31J"
%>

<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>映画管理</title>
    </head>
    <body>
		<h1>映画管理</h1>
	<!--ーーーーーーーー映画館名で検索し情報を取得するーーーーーーーー-->
		<form method='post' action='moviesearchservlet'><!--MovieSearchServletへ-->
			映　画　館　<input type="text" size="20" name="theater_name" value="${theater_name}">
			<input type='submit' value='検索'>
		</form>
		<p>ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー</p>

	<!--ーーーーーーーーーーーーーーーーーースケジュールーーーーーーーーーーーーーーーーーーーー-->
	<!--ーーーーーーーーーーーーーーーーーーーーループーーーーーーーーーーーーーーーーーーーーー-->
		<c:forEach var="movielist" items="${movielist}">
			<form method='post' action='moviedeleteservlet' id="moviedeleteservlet"><!--MovieDeleteServletへ-->
				タイトル<input type='text' name='title' value="${movielist.title}"><br>
				画　像　<input type='text' name='image' value="${movielist.image}"><br>
				開始期間<input type='text' name="release_period" value="${movielist.release_period}"><br>
				終了期間<input type='text' name="end_period" value="${movielist.end_period}"><br>
				作品詳細<input type="text" size="20" name="Detailed_explanation" 
								style="width: 350px; height: 100px;" value="${movielist.detailed_explanation}"><br>
				<!--schedule_id 映画　一意 見えなくする-->
				<input type='hidden' name="schedule_id" value="${movielist.schedule_id}"><br>
				<!--映画館　タイトル　見えなくする-->
				<input type="hidden" size="20" name="theater_name" value="${theater_name}">

				<!--ーーーーーーーーーー映画を変更するーーーーーーーーーー-->
				<input type='submit' value='変更' formaction='moviechangeservlet'>

				<!--ーーーーーーーーーー映画を削除するーーーーーーーーーー-->
				<input type='submit' value='削除' formaction="moviedeleteservlet">
				<p>ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー</p>
			</form>

			<form method='post' action='moviechangeservlet' id="moviechangeservlet">
				<!--変更-->
			</form>
		</c:forEach>
			
		
	
	<!--ーーーーーーーーーー映画を追加するーーーーーーーーーー-->
        <form method='post' action='movieaddservlet'>
            <p>ーーーーーーーーーーーーーーーーーーーー追加ーーーーーーーーーーーーーーーーーーーー</p>	
	        <div id="movietheater_add">
		        映画館　<input type="text" size="20" name="theater_name" value="${theater_name}"><br>
				タイトル<input type='text' name='title'><br>
	            画　像　<input type='text' name='image'><br>
				開始期間<input type='text' name="release_period"><br>
				終了期間<input type='text' name="end_period"><br>
	            作品詳細<input type="text" size="20" name="Detailed_explanation" style="width: 350px; height: 100px;"><br>
	            <input type='submit' value='追加'><br><br>
			</div>
		</form>

        <a href="kanriTop.html">管理トップへ</a>        
    </body>
</html>

