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
        <a href="">评价管理</a>
        <a>
          <cite>评价列表</cite></a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
 <!--    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
            <input type="text" name="mname"  placeholder="请输入菜谱名" autocomplete="off" class="layui-input">
            <input type="text" name="uname"  placeholder="请输入用户名" autocomplete="off" class="layui-input">
            <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div> -->
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>编号</th>
            <th>菜谱名称</th>
            <th>内容</th>
            <th>评价者ID</th>
            <th>评价时间</th>
            <th>分数</th>
            <th>操作</th></tr>
        </thead>
        <tbody id="view">
        </tbody>
    </table>
        <div id="cla_edit" class="hide"></div>
        <div class="laypage" id="laypage"  style="margin-left:300px"></div>
</div>
<script>
    layui.use(['laypage','form','base','laydate'], function(){
		var laypage = layui.laypage
	  		,form = layui.form
			,$ = layui.jquery
	   		,base = layui.base
             ,laydate=layui.laydate;

	var options={
            url:"/evaluate/list",
            view:view,
            pageEle:'laypage',
            template:list,
            pager:{limit:10,page:1}
     };
	  $(function(){
	        base.fetchRenderTable(options);
	    });


	  /*删除*/
	  $('#view').on('click','.cla_del',function(data){
	            var id = $(this).attr('data-id');
	            console.log(id);
	            if(id){
	                layer.msg('确认删除吗?', {
	                        time: 0 //不自动关闭
	                        ,btn: ['确认', '取消']
	                        ,btnAlign: 'c'
	                        ,yes: function(index){
	                            base.fetchData(base.config.host+"/evaluate/delete/"+id,{},function(result){
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
/*       $('#view').on('click','.cla_edit', function(data) {
          var id = $(this).attr('data-id');
          if(id){
                  base.fetchData(base.config.host+"/evaluate/get/"+id,{},function(result){
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
	}); */

   

 
</script>

</body>
 <script id="list" type="text/html">
    {{#  layui.each(d.data, function(index, item){ }}
         <tr>
            <td>
                <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td>{{ item.evaluateId}}</td>
            <td>{{ item.mname}}</td>
            <td>{{ item.evaluateContent}}</td>
            <td>{{ item.uname}}</td>
            <td>{{ new Date(item.evaluteDate).format("yyyy-MM-dd")}}</td>
            <td>{{ item.score}}</td>
            <td class="td-manage">
        <button class="layui-btn layui-btn-primary layui-btn-small cla_del" data-id="{{item.evaluateId}}">删除</button>
            </td>
        </tr>
        {{# }); }}
        </script>
      <script type="text/html" id="editTemplate">
<form class="layui-form" lay-filter="edi-form">

 <div class="layui-form-item">
    <label for="L_username" class="layui-form-label">
        <span class="x-red">*</span>菜谱名
    </label>
    <div class="layui-input-inline">
        <input type="text" id="L_username" name="mname" required="" lay-verify="nikename"
        autocomplete="off" class="layui-input" value="{{d.data.mname==undefined?'':d.data.mname}}">
    </div>
</div>
 <div class="layui-form-item">
    <label for="L_username" class="layui-form-label">
        <span class="x-red">*</span>
    </label>
    <div class="layui-input-inline">
        <input type="text" id="L_username" name="mname" required="" lay-verify="nikename"
        autocomplete="off" class="layui-input" value="{{d.data.mname==undefined?'':d.data.mname}}">
    </div>
</div>
<div class="layui-form-item">
    <label for="phone" class="layui-form-label">
        <span class="x-red">*</span>描述
    </label>
    <div class="layui-input-inline">
    <textarea  id="phone"   rows=＂4＂ cols=＂5＂ style="margin: 0px; width: 418px; height: 150px;">{{d.data.mdesc==undefined?'':d.data.mdesc}}</textarea>
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