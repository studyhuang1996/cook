<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>厨房美食</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="css/font.css">
	<link rel="stylesheet" href="css/xadmin.css">
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/xadmin.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript" src="js/basic.js"></script>
  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form">
          <div class="layui-form-item">
           <input type="hidden" name="aid" value="${admin.aid}"/>
              <label for="L_username" class="layui-form-label">
                  昵称
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_username" name="aname" disabled="disabled" value="${admin.aname}" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_oldpass" class="layui-form-label">
                  <span class="x-red">*</span>旧密码
              </label>
              <div class="layui-input-inline">
                  <input type="password" id="L_oldpass" name="oldpass" required="" lay-verify="required"
    autocomplete="off" class="layui-input">
    </div>
          </div>
          <div class="layui-form-item">
              <label for="L_pass" class="layui-form-label">
                  <span class="x-red">*</span>新密码
              </label>
              <div class="layui-input-inline">
                  <input type="password" id="L_pass" name="newpass" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  6到16个字符
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>确认密码
              </label>
              <div class="layui-input-inline">
                  <input type="password" id="L_repass" name="repass" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="save" lay-submit="">
                                                   修改密码
              </button>
          </div>
      </form>
    </div>
    <script>
     layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer;

               //自定义验证规则
        form.verify({
            nikename: function(value){
                if(value.length < 5){
                    return '昵称至少得5个字符啊';
                }
            }
            ,old:function(value){
               if($('#L_oldpass').val()!="123456"){
                    return '旧密码出错';
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
       form.on('submit(save)', function(data){
                console.log(data);
                $.post("http://119.29.56.63:8080/admin/editpass",$(data.form).serialize(),function(result){
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