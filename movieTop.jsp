<%@ page pageEncoding="Windows-31J"
 contentType="text/html;charset=Windows-31J"
%>

<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ja">
    <head>
        <meta charset="Windows-31J">
        <title>映画トップページ</title>
            <link href="slick-1.8.1/slick/slick-theme.css" rel="stylesheet" type="text/css" charset="UTF-8">
            <link href="slick-1.8.1/slick/slick.css" rel="stylesheet" type="text/css" charset="UTF-8">
            <script src="https://cdn.jsdelivr.net/npm/jquery@3/dist/jquery.min.js" charset="UTF-8"></script>
            <script type="text/javascript" src="slick-1.8.1/slick/slick.min.js" charset="UTF-8"></script> 
            <link rel="stylesheet" type="text/css" href="">
            <style>
                .movie{
                    height: auto;
                    width: 70%;
                    text-align:center;
                    
                }
                .slider{
                    height: 10%;
                    margin: 50px auto;
                    width: 50%;
                }
                /* .slider button{
                    height: auto;
                    width: 50%;
                } 
                /*slick setting*/
                .slick-prev:before, /*横移動のスイッチ*/
                .slick-next:before {
                    color: #000;
                }

            </style>

            <script type="text/javascript">
                var judge = /[0-9]{2}/;
                window.onload = function aaa(){
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
                        htmlstr += "<button class='tabcontent' onclick=dateChange('"+day+"') id='"+(date.getFullYear())+"/"+(date.getMonth()+1)+"/0"+date.getDate()+"'>"+((date.getMonth()+1)+"/"+date.getDate()+"("+dayOfWeekStr+")")+"</button>";

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
                function dateChange(day){
                    console.log(day);
                    var id = day.replace(/\//g,'-');
                    console.log(id);
                        $.ajax({
                            type:"GET",
                            url:"AjaxServlet",
                            data:{data:id}
                        }).done(function(result){
                            $("#aaa").load("dayservlet #detailTable");
                        })
                }

                function detailDisplay(id){
                        var aid = "#A"+id+", #B"+id;
                        $(aid).toggleClass("active passive");
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
        <style>
            *{
                margin:0;
                padding:0;
            }
            .active{
                display: inline-block;
            }
            .passive{
                display: none;
            }
        </style>
        <br><br>

        <center>
            <form action="dayservlet" method="POST">

                <select name="theater_name">
                    <option value="${theater_name}">${theater_name}</option>
                    <option value="青木映画館">青木映画館</option>
                    <option value="ざわ映画館">ざわ映画館</option>
                    <option value="しおだ映画館">しおだ映画館</option>
                </select>
                <input type="submit" value="映画館変更">

            </form>
    
            <br><br>

            <div id="movieArea"></div>
            <div class="tabcontent" id="tabcontent1"><br><br>
                <div class="movieS">
                    <div id="mTitle">
                        ${result}
                        <!-- <tbody> -->
                        <br>
                        <div id="aaa">
                        <table border="1" width="1000" id="detailTable">
                            <c:forEach var="d" items="${daylist}">
                                <tr>
                                    <th colspan="5">${d.title}</th>
                                </tr>
                                <tr>
                                    <td colspan="5">
                                        <button class="detail" onclick="detailDisplay('${d.schedule_detail_id}')" id="${d.schedule_detail_id}">詳細</button>
                                        <!-- <input type="submit" name="detail" id="${d.schedule_detail_id}" value="詳細"> -->
                                    </td>
                                </tr>
                                <tr>
                                    <td class="passive" id="A${d.schedule_detail_id}">
                                            <p style="display: inline; font-size: 110%; font-weight: bold;">映画詳細</p><p style="width: 994px;">${d.detailed_explanation}</p>
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
                        <!-- </tbody> -->
                    </div>
                </div>
            </div>
        </center>

    </body>
</html>


