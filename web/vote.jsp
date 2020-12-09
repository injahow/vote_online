<%--
  Created by IntelliJ IDEA.
  User: injah
  Date: 2020/11/21
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Vote</title>
    <link rel="stylesheet" type="text/css" href="main.css">
    <script src="js/jquery-1.10.2.min.js"></script>
</head>
<body>

<div id="table"></div>
<br><br>
<button onclick="subAll()">批量投票</button>
<br><br>
<div id="msg" style="color:red"></div>

<script>
    function init_vote() {
        $.ajax({
            type: 'get',
            url: 'init_vote',
            async: true,
            cache: false,
            dataType: 'html',
            success: function (data) {
                $("#table").html(data);
            }
        })
    }

    function subAll() {
        $('#msg').html('')
        const inp = $('input[name=sel]')
        let id_arr = []
        for (let i=0; i < inp.length; i++) {
            if(inp[i].checked) {
                id_arr.push(inp[i].value)
            }
        }
        $.ajax({
            type: 'post',
            url: 'vote',
            contentType: 'application/x-www-form-urlencoded',
            data: {'electorIds':id_arr},
            async: true,
            cache: false,
            dataType: 'text',
            success:function(data) {
                if(data==='success'){
                    init_vote()
                }else{
                    $("#msg").html(data);
                }
            }
        })
    }

    function vote(electorId, anonymous) {
        $('#msg').html('')
        $.ajax({
            type: 'post',
            url: 'vote',
            contentType: 'application/x-www-form-urlencoded',
            data: {'electorId':electorId,'anonymous':anonymous},
            async: true,
            cache: false,
            dataType: 'text',
            success:function(data) {
                if(data==='success'){
                    init_vote()
                }else{
                    $("#msg").html(data);
                }
            }
        })
    }
    init_vote()
</script>
</body>
</html>
