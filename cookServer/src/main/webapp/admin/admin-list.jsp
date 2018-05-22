<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>厨房美食后台</title>
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
<base href=""></base>
</head>

<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a href="">首页</a> <a href="">管理员管理</a>
			<a> <cite>管理员列表</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">

		<table class="layui-table">
			<thead>
				<tr>
					<th>
						<div class="layui-unselect header layui-form-checkbox"
							lay-skin="primary">
							<i class="layui-icon">&#xe605;</i>
						</div>
					</th>
					<th>ID</th>
					<th>管理员账号</th>
					<th>邮箱</th>
					<th>手机</th>
					<th>生日</th>
		             <th>操作</th>
			</thead>
			<tbody id="view"></tbody>
			<script id="list" type="text/html">
        {{#  layui.each(d.data.list, function(index, item){ }}
          <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
			<td>{{ item.aid }}</td>
            <td>{{ item.aname}}</td>
            <td>{{ item.aemail }}</td>
			<td>{{ item.atelphone }}</td>
			<td>{{new Date( item.birthday ).format('yyyy-MM-dd')}}</td>
            <td class="td-manage">
         <button class="layui-btn layui-btn-primary layui-btn-small cla_edit" data-id="{{item.aid}}" >编辑</button>
        <button class="layui-btn layui-btn-primary layui-btn-small cla_del" data-id="{{item.aid}}">删除</button>
            </td>
          </tr>
      {{# }); }}
      </script>
		</table>
		<div id="edit" style="display: none;"></div>
		<div class="laypage" id="laypage" style="margin-left:350px"></div>


	</div>
	<script>
	layui.use(['laypage','form','base'], function(){
		var laypage = layui.laypage
	  		,form = layui.form
			,$ = layui.jquery
	   		,base = layui.base;

	var options={
            url:"/admin/list",
            view:view,
            pageEle:'laypage',
            template:list,
            pager:{limit:5,page:1}
     };
	  $(function(){
	        base.fetchRenderTable(options);
	    });

	  form.on('submit(add)', function(data) {
					console.log(data);
		  base.fetchData(base.config.host+'/admin/save',$(data.form).serialize(),function(result){
             if(result.succee){
		 	 	layer.closeAll('page');
                base.fetchRenderTable(options);
            }else{
            	layer.msg(result.msg);
             }
                });
                return false;
	});
	  $('#view').on('click','.cla_edit', function(data) {
	      var id = $(this).attr('data-id');
	      if(id){
	              base.fetchData(base.config.host+"/admin/get/"+id,{},function(result){
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

	  
	  /*删除*/
      $('#view').on('click','.cla_del',function(data){
                var id = $(this).attr('data-id');
                if(id){
                    layer.msg('确认删除吗?', {
                            time: 0 //不自动关闭
                            ,btn: ['确认', '取消']
                            ,btnAlign: 'c'
                            ,yes: function(index){
                                base.fetchData(base.config.host+"/admin/delete/"+id,{},function(result){
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
});

	</script>
	<script type="text/javascript" id="editTemplate">
	<form class="layui-form  layui-form-pane" action="/" lay-filter="edi-form">
	<input type="hidden" name="aid" value="{{d.data.aid}}"/>
	<input type="hidden" name="apassword" value="{{d.data.apassword}}"/>
	<div class="layui-form-item">
    <label for="username" class="layui-form-label">
        <span class="x-red">*</span>姓名
    </label>
    <div class="layui-input-inline">
        <input type="text" id="username" name="aname" required="" lay-verify="required"
               autocomplete="off" class="layui-input" value="{{d.data.aname}}" >
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
               autocomplete="off" class="layui-input" value="{{d.data.atelphone}}">
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
               autocomplete="off" class="layui-input" value="{{d.data.aemail}}">
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
               autocomplete="off" class="layui-input" value="{{new Date( d.data.birthday ).format('yyyy-MM-dd')}}" >
    </div>
    <div class="layui-form-mid layui-word-aux">
        <span class="x-red">*</span>
    </div>
</div>
        <div class="layui-form-item">
            <button  class="layui-btn" lay-filter="add" lay-submit="" style="margin-left: 100px;">
                         修改
            </button>
        </div>
    </form>
</script>
</body>

</html>