<%--
  Created by IntelliJ IDEA.
  User: injah
  Date: 2020/11/21
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Register</title>
    <script src="js/jquery-1.10.2.min.js"></script>
</head>
<body>

<form>
    账号：<input id="account" type="text" name="account" /><br>
    密码：<input id="pwd" type="password" name="pwd" /><br>
    确认密码：<input id="pwd2" type="password" name="pwd2" /><br>
    <input type="button" value="注册" onclick="register()" />
</form>


<br>
<div id="msg" style="color:red"></div>

<script>
    function register() {
        $('#msg').html('')
        const account = $('#account').val()
        const pwd = $('#pwd').val()
        const pwd2 = $('#pwd2').val()

        if(!account || !pwd){
            $('#msg').html('账号或密码为空！')
            return
        }

        if(pwd !== pwd2){
            $('#msg').html('两次密码不相符！')
            return
        }

        $.ajax({
            type: 'post',
            url: 'register',
            data:{
                'account':account,
                'pwd':pwd
            },
            async: true,
            cache: false,
            dataType: 'text',
            success:function(data) {
                if(data=="success"){
                    window.location.href='login.jsp'
                }else{
                    $('#msg').html(data)
                }
            }
        })
    }

</script>
</body>
</html>
