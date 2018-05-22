<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>厨房美食后台</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8" />
	<link rel="stylesheet" href="css/font.css">
	<link rel="stylesheet" href="css/xadmin.css">
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/xadmin.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript" src="js/basic.js"></script>
<base href=""></base>
</head>

<body>
<table class="layui-hide" id="test" lay-filter="demo"></table>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a href="">首页</a> <a href="">分类管理</a>
			<a> <cite>分类列表</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 x-so layui-form-pane">
				<input class="layui-input" placeholder="分类名" name="cname">
				<button class="layui-btn" lay-submit="" lay-filter="add">
					<i class="layui-icon"></i>增加
				</button>
			</form>
		</div>
		<table id="demo" lay-filter="test"></table>
		<table class="layui-table" >
			<thead>
				<tr>
					<th>
						<div class="layui-unselect header layui-form-checkbox"
							lay-skin="primary">
							<i class="layui-icon">&#xe605;</i>
						</div>
					</th>
					<th>ID</th>
					<th>分类名</th>
					<th>操作</th>
			</thead>
			<tbody id="view"></tbody>
			<script id="list" type="text/html">
        {{#  layui.each(d.data, function(index, item){ }}
          <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td>{{ item.cid}}</td>
            <td>{{ item.cname }}</td>
            <td class="td-manage">
         <button class="layui-btn layui-btn-primary layui-btn-small cla_edit" data-id="{{item.cid}}" >编辑</button>
        <button class="layui-btn layui-btn-primary layui-btn-small cla_del" data-id="{{item.cid}}">删除</button>
            </td>
          </tr>
      {{# }); }}
      </script>
		</table>
		<div id="cla_edit" style="display: none;"></div>
		<div class="laypage" id="laypage" style="margin-left:350px"></div>
	</div>
	<script>
	layui.use(['laypage','form','base','table'], function(){
		var laypage = layui.laypage
	  		,form = layui.form
	  		,table = layui.table 
			,$ = layui.jquery
	   		,base = layui.base;
		
	/* 	  table.render({
			    elem: '#test'
			    ,height: 332
			    ,url: 'http://localhost:8098/cookssm/admin/category/list' //数据接口
			    ,page: true //开启分页
			    ,cols: [[ //表头
			      {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
			      ,{field: 'cid', title: '', width:80}
			      ,{field: 'cname', title: '性别', width:80, sort: true}
			      ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
			    ]]
			  }); */
		  
	var options={
            url:base.config.host+"/category/list",
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
		  base.fetchData(base.config.host+'/category/save',$(data.form).serialize(),function(result){
             if(result.succee){
		 	 	layer.closeAll('page');
                base.fetchRenderTable(options);
            }else{
            	layer.msg(result.msg);
             }             
                });
                return false;
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
                                base.fetchData(base.config.host+"/category/delete/"+id,{},function(result){
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
	 
      $('#view').on('click','.cla_edit', function(data) {
          var id = $(this).attr('data-id');     
          if(id){
                  base.fetchData(base.config.host+"/category/get/"+id,{},function(result){
                       console.log(result);    
                	  if(result.succee){
                             base.renderView(result,cla_edit,editTemplate);
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
                  area: ['400px', '280px'],
                  content: $('#cla_edit')
          });
  }); 
	});
	
	/* $.ajax({  
        type : "get" ,  
        url : "http://localhost:8098/cookssm/category/list",  
        page:true,
        
        success : function(data){  
        	console.log(data);
          alert(data.data[1].cname);
        }  
     });     */

     /* 	function member_del(obj, id) {
		layer.confirm('确认要删除吗？', function(index) {
			//发异步删除数据
			alert(id);
    base.fetchData(base.config.host+"/category/delete/"+id,{},function(result){
       if(result.succee){
          options.pager.page = 1;
        base.fetchRenderTable(options);
     }else{
         layer.msg(result.msg);
       }	  });
      layer.close(index);
       });
}    */

		layui.use('laydate', function() {
			var laydate = layui.laydate;

			//执行一个laydate实例
			laydate.render({
				elem : '#start' //指定元素
			});

			//执行一个laydate实例
			laydate.render({
				elem : '#end' //指定元素
			});
		});

	
	</script>

	<script type="text/javascript" id="editTemplate">
	<form class="layui-form  layui-form-pane" action="/" lay-filter="edi-form">
	   {{#  if(d.data && d.data.cid){}}
      <input type="hidden" name="cid" value="{{d.data.cid}}" />
      {{# } }}   
	<div class="layui-form-item">
            <label for="username" class="layui-form-label">
                <span class="x-red">*</span>菜谱类别名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="username" name="cname" required="" lay-verify="required"
                       autocomplete="off" value="{{d.data.cname}}" class="layui-input">
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