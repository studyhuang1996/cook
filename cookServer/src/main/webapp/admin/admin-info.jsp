<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <title>厨房美食后台管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
    content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
	  <link rel="stylesheet" href="css/font.css">
	<link rel="stylesheet" href="css/xadmin.css">
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/xadmin.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript" src="js/basic.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="x-body">
    <form class="layui-form" action="/"  >
      <input type="hidden" name="aid" value="${admin.aid}"/>
     <input type="hidden" id="pass" name="apassword" value=""/>
    <input type="hidden" name="aname" value="${admin.aname}"/>
        <div class="layui-form-item">
            <label for="username" class="layui-form-label">
                <span class="x-red">*</span>姓名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="username" name="aname" required="" lay-verify="required"
                       autocomplete="off" class="layui-input" value="${admin.aname}" disabled="disabled">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>将会成为您唯一的登入名
        </div>
    </div>
        <div class="layui-form-item">
            <label for="phone" class="layui-form-label">
                <span class="x-red">*</span>手机
            </label>
            <div class="layui-input-inline">
                <input type="text" id="phone" name="atelphone" required="" lay-verify="phone"
                       autocomplete="off" class="layui-input" value="">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_email" class="layui-form-label">
                <span class="x-red">*</span>邮箱
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_email" name="aemail" required="" lay-verify="required"
                       autocomplete="off" class="layui-input" value="">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_date" class="layui-form-label">
                <span class="x-red">*</span>出生日期
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_date" name="birthday" required="" lay-verify=""
                       autocomplete="off" class="layui-input" value="" > <%-- <fmt:formatDate value='${admin.birthday}' type='date'/> --%>
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
            </label>
            <button   id="L_repass" class="layui-btn" lay-filter="add" lay-submit="">
                修改
            </button>
        </div>
    </form>
</div>
<script>
    layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer;

        var id = document.getElementsByName("aid")[0].value;
        console.log(id);
        $.ajax({  
            type : "get" ,  
            url : "http://119.29.56.63:8080/admin/get/"+id,  
            page:true,
            success : function(date){  
            //	console.log(date.data);
            	$("#pass").val(date.data.apassword);
             	$("#L_email").val(date.data.aemail);
            	$("#phone").val(date.data.atelphone);
            	$("#L_date").val(new Date(date.data.birthday).format("yyyy-MM-dd"));
            }  
         });   
        //自定义验证规则
        form.verify({
            nikename: function(value){
                if(value.length < 5){
                    return '昵称至少得5个字符啊';
                }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,repass: function(value){
                if($('#L_pass').val()!=$('#L_repass').val()){
                    return '两次密码不一致';
                }
            }
        });

        //监听提交
        form.on('submit(add)', function(data){
            console.log($(data.form).serialize());
       $.post("http://localhost:8080/admin/save",$(data.form).serialize(),function(result){
             if(result.succee){
              layer.msg(result.msg);
              layer.closeAll();
            // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
            //关闭当前frame
             parent.layer.close(index);
            }else{
            	layer.msg(result.msg);
             }
                });
                return false;
        });


    });
</script>
</body>

</html>