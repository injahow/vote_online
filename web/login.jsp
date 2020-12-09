<%--
  Created by IntelliJ IDEA.
  User: injah
  Date: 2020/11/22
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>User Login</title>
    <link rel="stylesheet" type="text/css" href="main.css">
    <script src="js/jquery-1.10.2.min.js"></script>
  </head>
  <body>
    <form>
      账号：<input id="account" type="text" name="account" /><br>
      密码：<input id="pwd" type="password" name="pwd" /><br><br>
      <input type="button" value="登陆" onclick="login()" />
      <input type="reset" value="重置" />
    </form>

    <br>
    <div id="msg" style="color:red"></div>

    <script>
      function login() {
        $('#msg').html('')
        const account = $('#account').val()
        const pwd = $('#pwd').val()
        if(!account || !pwd){
          $('#msg').html('账号或密码为空！')
          return
        }
        $.ajax({
          type: 'post',
          url: 'login',
          data:{
            'account':account,
            'pwd':pwd
          },
          async: true,
          cache: false,
          dataType: 'text',
          success:function(data) {
            if(data=="success"){
              window.location.href='vote.jsp'
            }else{
              $('#msg').html(data)
            }
          }
        })
      }

    </script>
  </body>
</html>
