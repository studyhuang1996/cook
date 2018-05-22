    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>厨房美食后台管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="css/font.css">
    <link rel="stylesheet" href="css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">用户管理</a>
        <a>
          <cite>用户列表</cite></a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
   <!--  <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
            <input type="text" name="uname"  placeholder="请输入用户名" autocomplete="off" class="layui-input">
            <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div> -->
    <xblock>
        <button class="layui-btn add" ><i class="layui-icon"></i>添加</button>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>用户名</th>
            <th>邮箱</th>
            <th>联系方式</th>
            <th>注册时间</th>
            <th>状态</th>
            <th>操作</th></tr>
        </thead>
        <tbody id="view">    </tbody>
    </table>
    	<div id="edit"  style="display: none;"></div>
        <div class="laypage" id="laypage" style="margin-left:350px"></div>

   

</div>
<script>

layui.use(['laypage','form','base'], function(){
	var laypage = layui.laypage
  		,form = layui.form
		,$ = layui.jquery
   		,base = layui.base;

var options={
        url:"/user/list",
        view:view,
        pageEle:'laypage',
        template:list,
        pager:{limit:10,page:1}
 }; 
  $(function(){
        base.fetchRenderTable(options);
    });

    //新增
    $('.add').on('click', function() {
        base.fetchData(base.config.host+"/user/list",{},function(result){
        if(result.succee){
        base.renderView(result,edit,editTemplate);;
        form.render(null,"edi-form");
        base.fetchRenderTable(options);
        }
        return false;
        });
    layer.open({
    title:'新增',
    type: 1,
    scrollbar:false,
    skin: 'layui_k',
    area: ['600px', '300px'],
    content:  $('#edit')
    });
 });
    form.on('submit(add)', function(data) {
        console.log(data);
        base.fetchData(base.config.host+'/user/save',$(data.form).serialize(),function(result){
        if(result.succee){
        	layer.msg(result.msg);
            layer.closeAll();
            base.fetchRenderTable(options);
         
         }else{
              layer.msg(result.msg);
        }
        });
    });
    form.on('submit(sreach)',function(data){
    	
    });
    form.on('submit(adds)', function(data) {
    console.log(data);
    base.fetchData(base.config.host+'/user/save',$(data.form).serialize(),function(result){
    if(result.succee){
    	layer.msg(result.msg);
        layer.closeAll();
        base.fetchRenderTable(options);
     
     }else{
          layer.msg(result.msg);
    }
    });
        });
  
  /*删除*/
  $('#view').on('click','.cla_del',function(data){
            var id = $(this).attr('data-id');
            if(id){
                layer.msg('确认删除吗?', {
                        time: 0 //不自动关闭
                        ,btn: ['确认', '取消']
                        ,btnAlign: 'c'
                        ,yes: function(index){
                            base.fetchData(base.config.host+"/user/delete/"+id,{},function(result){
                                if(result.succee){
                                    options.pager.page = 1;
                                    base.fetchRenderTable(options);
                                }else{
                                    layer.msg(result.msg);
                                }
                            });
                        layer.close(index);
                    }
                });
            }
    });
 
  $('#view').on('click','.edit', function(data) {
      var id = $(this).attr('data-id');     
      if(id){
              base.fetchData(base.config.host+"/user/get/"+id,{},function(result){
                   console.log(result);    
            	  if(result.succee){
                         base.renderView(result,edit,editTemplate);
                       form.render(null,"edi-form");//模板中的表单，需要手动渲染
                      }else{
                            layer.msg(result.msg);
                      }
              });
      }
      layer.open({
              title:'编辑',
              type: 1,
			 scrollbar:false,
              skin: 'layui_k',
              area: ['500px', '350px'],
              content: $('#edit')
      });
}); 

        });
    /*用户-停用*/
    function member_stop(obj,id){
        layer.confirm('确认要冻结吗？',function(index){

            if($(obj).attr('title')=='冻结'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

            }else{
                $(obj).attr('title','冻结')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('正常!',{icon: 5,time:1000});
            }

        });
    }






</script>



</body>
<script id="list" type="text/html">
 {{#  layui.each(d.data.list, function(index, item){ }}
<tr>
  <td>
    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
  </td>
  <td>{{ item.uid}}</td>
  <td>{{ item.uname}}</td>
  <td>{{ item.uemail}}</td>
  <td>{{ item.utel}}</td>
   <td>{{ new Date(item.ubirthday).format("yyyy-MM-dd")}}</td>
  <td class="td-status">
    <span class="layui-btn layui-btn-normal layui-btn-mini">{{item.ustate==1?'已启用':'未启用'}}</span></td>
  <td class="td-manage">
    <a onclick="member_stop(this,'10001')" href="javascript:;"  title="启用">
        <i class="layui-icon">&#xe601;</i>
    </a>
   <button class="layui-btn layui-btn-primary layui-btn-small edit" data-id="{{item.uid}}" >编辑</button>
    <button class="layui-btn layui-btn-primary layui-btn-small cla_del" data-id="{{item.uid}}">删除</button>
</td>
</tr>
{{# }); }}
</script>
<script type="text/html" id="editTemplate">
<form class="layui-form">
<input type="hidden" name="uid" value="{{d.data.uid}}"/>
<input type="hidden" name="upassword" value="{{d.data.upassword}}"/>
 <div class="layui-form-item">
    <label for="L_username" class="layui-form-label">
        <span class="x-red">*</span>用户名
    </label>
    <div class="layui-input-inline">
        <input type="text" id="L_username" name="uname" required="" lay-verify="nikename"
        autocomplete="off" class="layui-input" value="{{d.data.uname==undefined?'':d.data.uname}}">
    </div>
   <div class="layui-form-mid layui-word-aux">
        <span class="x-red">*</span>将会成为您唯一的登入名
    </div>
</div>
<div class="layui-form-item">
    <label for="L_email" class="layui-form-label">
        <span class="x-red">*</span>邮箱
    </label>
    <div class="layui-input-inline">
        <input type="text" id="L_email" name="uemail" required="" lay-verify="email"
        autocomplete="off" class="layui-input" value="{{d.data.uemail==undefined?'':d.data.uemail}}">
    </div>
 
</div>
<div class="layui-form-item">
    <label for="phone" class="layui-form-label">
        <span class="x-red">*</span>手机
    </label>
    <div class="layui-input-inline">
        <input type="text" id="phone" name="utel" required="" lay-verify="nikename"
        autocomplete="off" class="layui-input" value="{{d.data.utel==undefined?'':d.data.utel}}">
    </div>
</div>

<div class="layui-form-item">
    <button  class="layui-btn" lay-filter="adds" lay-submit="" style="margin-left:100px">
       提交
    </button>

</div>
</form>
</script>
</html>