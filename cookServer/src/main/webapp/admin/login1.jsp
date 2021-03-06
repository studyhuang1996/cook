<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>厨房美食管理登录</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="admin/css/font.css">
	<link rel="stylesheet" type="text/css" href="admin/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="admin/js/xadmin.js"></script>
    <script type="text/javascript" src="admin/js/base.js"></script>
    <script type="text/javascript" src="admin/js/basic.js"></script>
</head>
<body class="login-bg">
    
    <div class="login">
        <div class="message">厨房美食管理登录</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form" >
            <input name="aname" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="apassword" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
             <a href="http://localhost:8080/index.jsp"><input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
           </a>
            <hr class="hr20" >
        </form>
    </div>

    <script>
        $(function  () {
            layui.use(['laypage','form','base'],function(){
              var form = layui.form
               ,base =layui.base;
              //监听提交
              form.on('submit(login)', function(data){
            	  console.log($(data.form).serialize());
               console.log(base.config.host);

            	  base.fetchData(base.config.host+'/login',$(data.form).serialize(),function(result){
                      if(result.succee){
         		 	 	window.location.href="http://localhost:8080/index.jsp";
         		 	 	layer.msg("登录成功");
                     }else{
                     	layer.msg(result.msg);
                      }             
                     }); 
                    
                }); 
                return false;
              });
            });
  

        
    </script>

    
    <!-- 底部结束 -->
    <script>
    //百度统计可去掉
    var _hmt = _hmt || [];
    (function() {
      var hm = document.createElement("script");
      hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
      var s = document.getElementsByTagName("script")[0]; 
      s.parentNode.insertBefore(hm, s);
    })();
    </script>
</body>
</html>