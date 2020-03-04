<%@ page pageEncoding="Windows-31J"
 contentType="text/html;charset=Windows-31J"
%>

<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="Windows-31J">
        <title>映画トップページ</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
            <link href="slick-1.8.1/slick/slick-theme.css" rel="stylesheet" type="text/css" charset="UTF-8">
            <link href="slick-1.8.1/slick/slick.css" rel="stylesheet" type="text/css" charset="UTF-8">
            <script src="https://cdn.jsdelivr.net/npm/jquery@3/dist/jquery.min.js" charset="UTF-8"></script>
            <script type="text/javascript" src="slick-1.8.1/slick/slick.min.js" charset="UTF-8"></script>
            <link rel="stylesheet" type="text/css" href="css/style.css">
        <link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">

        
            <script type="text/javascript">
                var judge = /[0-9]{2}/;
                window.onload = function aaa(){
//                    $(".deitals").slideUp(0);
                    var date = new Date();
                    var dayOfWeek = date.getDay();  //曜日
                    var htmlstr = "<div class='movie'>";
                    for(i=0;i<=7;i++){
                        var dayOfWeekStr = [ "日", "月", "火", "水", "木", "金", "土" ][dayOfWeek];
                        //htmlstr += '<button id="tabcontent1" formaction="test.html">'+(date.getMonth()+1)+"/"+date.getDate()+"("+dayOfWeekStr+")</button>";
                        var url = '"ChangeDetailServlet?date='+(date.getFullYear())+'-'+(date.getMonth()+1)+"-"+date.getDate()+'"';
                        var day = (date.getFullYear());
                        var md = date.getMonth()+1;
                        if(judge.test(md)){
                            day += "/"+md;
                        }else{
                            day += "/0"+md;
                        }
                        md = date.getDate();
                        if(judge.test(md)){
                            day += "/"+md;
                        }else{
                            day += "/0"+md;
                        }
                        // if(/2[0-9]{3}\/?[0-9]{2}\/?[0-9]{2}/.test(day)){
                        //     console.log("aaa");
                        // }
                        // htmlstr += "<input type='button' class='' id='"+(date.getMonth()+1)+"/"+date.getDate()+"' onClick='document.location="+url+";' value='"+(date.getMonth()+1)+"/"+date.getDate()+"("+dayOfWeekStr+")'></input>";
                        htmlstr += "<button style='font-weight: bold;font-size: 20px;' class='tabcontent' onclick=dateChange('"+day+"','${theater_name}') id='"+(date.getFullYear())+"/"+(date.getMonth()+1)+"/0"+date.getDate()+"' name=${theater_name}>"+((date.getMonth()+1)+"/"+date.getDate()+"("+dayOfWeekStr+")")+"</button>";

                        // htmlstr += '<input type="radio" name="hiduke" id="hidukeC" checked><label for="hidukeC" class="hi">'+(date.getMonth()+1)+"/"+date.getDate()+"("+dayOfWeekStr+")"+"</label>";
                            
                        dayOfWeek = (date.getDay()+1);
                        if(dayOfWeek==7){
                            dayOfWeek=0;
                        }
                        date.setDate(date.getDate() + 1);
                    }
                    document.getElementById("movieArea").innerHTML = htmlstr+"</div>";
                }

                $(document).ready(function(){
                    // $('.movie').bxSlider({
                    $('.movie').slick({
                        //dots:true,
                        infinite: false,
                        slidesToShow:4,
                        // slidesToScroll:2
                        responsive:[
                            {
                                breakpoint: 1024,
                                setting:{
                                    slidesToShow:3,
                                }
                            },
                            {
                                breakpoint: 768,
                                setting:{
                                    slidesToShow:2,
                                }
                            },
                            {
                                breakpoint: 480,
                                setting:{
                                    slidesToShow:1,
                                }
                            },
                        ]
                        // auto: true,
                        // pause: 5000,
                    });
                });

                // $('.movie').bxSlider({
                //     autoplay:true,
                //     // autoplaySpeed:5000,
                //     slidesToShow:2,
                //     slidesToScroll:2
                // });
        
            </script>
            <script>
                function titleSlide(){
                    $(".deitals").slideUp(0);
                }
                function dateChange(day,theater_name){
                    console.log(day);
                    var id = day.replace(/\//g,'-');
                    var theater_name = theater_name;
                    console.log(id);
                    console.log(theater_name);
                        $.ajax({
                            type:"GET",
                            url:"AjaxServlet",
                            data:{data:id,theater_name:theater_name}
                        }).done(function(result){
                            $("#detailTable").load("dayservlet #loadDetailTable");
//                            ロード場所
//                            titleSlide();
//                            <c:forEach var="d" items="${daylist}">
////                                slideTitle('${d.schedule_detail_id}')
//                                var Id = "#deital"+'${d.schedule_detail_id}'; 
//                                $(Id).slideUp(0);
//                            </c:forEach>
                        })
                }

                function detailDisplay(id){
                        var aid = "#A"+id+", #B"+id;
                        $(aid).toggleClass("zikan passive");
                }
                // $(function(){
                //     $(document).on("click",".tabcontent",function(){
                //         var id = $(this).attr("id").replace(/\//g,'-');
                //         console.log(id);
                //         $.ajax({
                //             type:"GET",
                //             url:"AjaxServlet",
                //             data:{data:id}
                //         }).done(function(result){
                //             $("#detailTable").load("dayservlet #detailTable");
                //         })
                //     })
                // })
                // $(function(){
                //     $(document).on("click",".detail",function(){
                //         var id = $(this).attr("id");
                //         var aid = "#A"+id+", #B"+id;
                //         $(aid).toggleClass("active passive");
                //     });
                // });
            </script>
    </head>
    <body>

        <ul class="topnav">
            <li><img src="LegTicke.png" id="img"></li>
            <li><a class="active">${theater_name}</a></li>
            <li class="right">
                <!--ハンバーガー-->
                <div class="cp_cont">
                    <div class="cp_offcm01">
                        <input type="checkbox" id="cp_toggle01">
                        <label for="cp_toggle01"><img src="menu.png" id="right2"><span></span></label>
                        <div class="cp_menu">
                            <ul>
                                <c:forEach var="bb" items="${hamburger}">
                                    <form action="dayservlet" method="POST">
                                        <li><input id="input" type="submit" value="${bb.name}" name="theater_name"></li>
                                        
                                    </form>
                                    <div class="sen"></div>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </li>
        </ul>

<br><br>
        
        

            <!--
            <form action="dayservlet" method="POST">

                <select name="theater_name">
                    <option value="${theater_name}">${theater_name}</option>
                    <option value="青木映画館">青木映画館</option>
                    <option value="ざわ映画館">ざわ映画館</option>
                    <option value="しおだ映画館">しおだ映画館</option>
                </select>
                <input type="submit" value="映画館変更">

            </form>
-->
    

<!--            <div id="movieArea"></div>-->
<!--
            <div class="tabcontent2" id="tabcontent1"><br><br>
                <div class="movieS">
                    <div id="mTitle">
-->
<!--                        ${result}-->
                        <!-- <tbody> -->
<!--
                        <br>
                        <div id="aaa">
                        <table border="0" width="1000" id="detailTable">
                            <c:forEach var="d" items="${daylist}">
                                <tr>
                                    <th colspan="5" style="font-size: 20px;color: #434d5d;">${d.title}</th>
                                </tr>
                                <tr>
                                    <td colspan="5">
                                        <button class="detail" onclick="detailDisplay('${d.schedule_detail_id}')" id="${d.schedule_detail_id}">詳細</button>
-->
                                        <!-- <input type="submit" name="detail" id="${d.schedule_detail_id}" value="詳細"> -->
<!--
                                    </td>
                                </tr>
                                <tr>
                                    <td class="passive" id="A${d.schedule_detail_id}">
                                            <p style="display: inline; font-size: 110%; font-weight: bold;">映画詳細</p>
                                        <p style="width: 994px;">${d.detailed_explanation}</p>
                                    </td>
                                    <c:forEach var="tt" items="${d.day}">
                                        <td class="active" id="B${d.schedule_detail_id}">
                                                ${tt.theater}<br>${tt.start_time}~${tt.end_time}<a href="AccessSeatServlet?theater=${tt.theater}&schedule_detail_id=${tt.schedule_detail_id}">予約</a>
                                        </td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </table>
                        </div>
                        <br>
                        <br>
                         </tbody>
                    </div>
                </div>
-->
<!--
            </div>
        </center>
-->
        
<!--
    <center>
        <div id="movieArea"></div>
        <div class="tabcontent2" id="tabcontent1"><br><br>
            <div class="movieS">
                <div id="mTitle">
                    ${result}
                     <tbody> 
                    <br>
                    <div id="aaa">
                        <table border="0" width="1000" id="detailTable">
                            <c:forEach var="d" items="${daylist}">
                                <div class="header_right">
                                    <ul class="menu_list">
                                        <li class="nav_item">
                                            <tr><th class="js-dropdown" colspan="5" style="font-size: 20px;color: #434d5d;">${d.title}</th></tr>
                                            <div class="panel js-dropdown-menu">
                                                <ul class="panel-inner">
                                                    <li class="panel_item">
                                                        <tr>
                                                        <td colspan="5">
                                                        <button class="detail" onclick="detailDisplay('${d.schedule_detail_id}')" id="${d.schedule_detail_id}">詳細</button>
                                                        </td>
                                                        </tr>
                                                    </li>
-->
<!--
                                                    <li class="panel_item">
                                                        <p style="width: 994px;">${d.detailed_explanation}</p>
                                                    </li>
-->
<!--
                                                    <li class="panel_item">
                                                        <td class="passive" id="A${d.schedule_detail_id}">
                                                                <p style="display: inline; font-size: 110%; font-weight: bold;">映画詳細</p>
                                                                <p style="width: 994px;">${d.detailed_explanation}</p>
                                                        </td>
                                                    </li>
                                                    <c:forEach var="tt" items="${d.day}">
                                                        <li class="panel_item">
                                                                <td class="active" id="B${d.schedule_detail_id}">
                                                                    ${tt.theater}<br>${tt.start_time}~${tt.end_time}
                                                                    <a href="AccessSeatServlet?theater=${tt.theater}&schedule_detail_id=${tt.schedule_detail_id}">予約</a>
                                                                </td>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </c:forEach>
                        </table>
                    </div><br><br>
-->
                    <!-- </tbody> -->
<!--
                </div>
            </div>
        </div>
    </center>
-->
        
    <div class="contant">   
        <center>
               
            <div id="movieArea"></div>
            <div class="movieS">
                <div id="mTitle">
                        <!-- ${result} -->
                    <div id="detailTable"> 
                            <div id="loadDetailTable">
                                <c:forEach var="d" items="${daylist}">
<!--                                    問題点１（全てが開かれてしまう）-->
                                    <div id="container" onclick="slideTitle('${d.schedule_detail_id}')" style="background-color: white; height: 90px; margin-left: 180px; margin-right: 180px;">
                                        <div style="font-size: 20px; color: #434d5d;">
                                            <p class="titleDrop"><br>${d.title}<img src="reverseTriangle.png" style="height: 10px; width: 15px; right: 30px;"></p>
                                            <!-- <p><img src="reverseTriangle.png"></p> -->
                                        </div>

                                    </div>
                                    <span class="profile">
                                        <!-- <div id="deital deital{${d.schedule_detail_id}}"> -->
                                        <div class="deitals" id="deital${d.schedule_detail_id}" style="background-color: white; height: 235px; margin-left: 180px; margin-right: 180px;">
                                            <div>
                                                <button class="detail" onclick="detailDisplay('${d.schedule_detail_id}')" id="${d.schedule_detail_id}" style="border-color: #a8a8a8; background-color: #ffffff; height: 45px; width: 120px; font-size: 18px; float: left; margin-top: 10px; margin-left: 20px;">詳細</button>
                                            </div>
                                            <div class="passive" id="A${d.schedule_detail_id}">
                                                <p style="display: inline; font-size: 110%; font-weight: bold;">映画詳細ｰ</p>
                                                <p style="width: 700px;">${d.detailed_explanation}</p>				
                                            </div>	
                                            <div class="detail_time">
                                                <c:forEach var="tt" items="${d.day}">
                                                    <div class="zikan" id="B${d.schedule_detail_id}" style="margin-top: 30px;">
                                                        ${tt.theater}<br>${tt.start_time}~${tt.end_time}<a href="AccessSeatServlet?theater=${tt.theater}&schedule_detail_id=${tt.schedule_detail_id}">予約</a>                       	
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </span>
                                </c:forEach>
                            </div>
                         </div> 
                </div>
            </div>
        </center>
    </div>
    
        <script type="text/javascript">
            function slideTitle(id){
//                $(".deitals").slideUp(0);
                var Id = "#deital"+id; 
                console.log(Id);
                $(Id).slideToggle(400);
                console.log("aaaaaaaaaaaaaaaaaaaaaaaaa");
            }
            // window.onload = function(){
            //     $(".deitals").slideUp(0);
            // }
        </script>
        <style>
            .titleDrop{
                margin-top: 70px;
            }
            .detail_time{
                display: flex;
                justify-content:flex-start;
                margin-left: 20px;
            }
        </style>

        <!-- <style>

            .profile{
                display: inline;
                position: absolute;
                right: 55%;
                z-index: 99;
                left: 20%;
            }

            .friend{
                text-decoration: underline;
            }

            #container {
/*                display: flex;*/
                justify-content: space-between;
                align-items: center;
                height: 60px;
                border: 1px solid black;
                border-left: 0px;
                border-right: 0px;
                margin-top: 5%;
                margin-bottom: 3%;
                z-index: 999;
            }

            .passive{
                display: none;
            }

            .up .down{
                width: 50px;
                height: 50px;
            }

            .zikan{
                display: block;
            }

            /* ドロップダウン▽△ボタン */
/*
            .triangle{
                width: 20px;
                height: 20px;
                position: relative;
                left: 0;
                top: 5px;
            }
*/
        </style> -->

    </body>
</html>


