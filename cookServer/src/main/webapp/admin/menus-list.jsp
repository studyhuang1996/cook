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
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
    <link rel="stylesheet" href="css/font.css">
    <link rel="stylesheet" href="css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/xadmin.js"></script>
  <script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript" src="js/basic.js"></script>
  </head>

  <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">菜谱管理</a>
        <a>
          <cite>菜谱列表</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" lay-filter="search-form">
          <input type="text" name="mname"  placeholder="请输入菜谱名" autocomplete="off" class="layui-input">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div><div>
      <button class="layui-btn  add "><i class="layui-icon"></i>新增</button></div>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>菜谱名</th>
            <th>所在分类</th>
            <th>描述</th>
            <th>创建用户</th>
            <th>图片</th>
            <th>加入时间</th>
            <th>操作</th></tr>
        </thead>
    <tbody id="view"></tbody>
      </table>
    <div id="edit"  style="display: none;"></div>
    <div class="laypage" id="laypage" style="margin-left:350px"></div>

    </div>
    <script>
  layui.use(['laypage','upload','form','base'], function(){
	var laypage = layui.laypage
  		,form = layui.form
  		,upload = layui.upload
		,$ = layui.jquery
		,finish = true
   		,base = layui.base;

    var options={
        url:"/menu/list",
        view:view,
        pageEle:'laypage',
        template:list,
        pager:{limit:5,page:1}
     };
  $(function(){
        base.fetchRenderTable(options);
    });
        var option={
        url:"/menu/",
        view:view,
        pageEle:'laypage',
        template:list,
        pager:{limit:5,page:1}
        };
    form.on('submit(sreach)',function(data){
       console.log($(data.form).serialize());
        console.log(data.field.querys);
        options.query=data.field;
        options.url="/menu/query/"+data.field.mname;
        console.log(options);
        base.fetchRenderTable(options);
        return false;
     base.fetchData(base.config.host+'/menu/query/'+data.field.mname,$(data.form).serialize(),function(result){
         if(result.succee){
          //base.renderView(result,view,list);
          //form.render(null,"search-form");
           options.url="/menu/query/"+data.field.querys;
          base.fetchRenderTable(options);
          }else{
          layer.msg(result.msg);
        }
        return false;
     });
    });

    //新增
    $('.add').on('click', function() {
        base.fetchData(base.config.host+"/menu/basic",{},function(result){
        if(result.succee){
 	
        base.renderView(result,edit,editTemplate);
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
    area: ['600px', '500px'],
    content:  $('#edit')
    });
        });
    form.on('submit(adds)', function(data) {
    	if(!finish){
			layer.msg("图片上传中…，请稍等", {icon: 16,shade: 0.01});
			return false;
		}
    console.log(data);
    base.fetchData(base.config.host+'/menu/save',$(data.form).serialize(),function(result){
    if(result.succee){
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
                            base.fetchData(base.config.host+"/menu/delete/"+id,{},function(result){
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
              base.fetchData(base.config.host+"/menu/get/"+id,{},function(result){
                   console.log(result);
            	  if(result.succee){
                         base.renderView(result,edit,editTemplate);
                       form.render(null,"edi-form");//模板中的表单，需要手动渲染
                     //普通图片上传
       				var uploadInst = upload.render({
       					elem: '#my_upload',
       					url: base.config.host+'/upload/image',
       					before: function(obj){
       						finish = false;
       						$('#my_img').css('opacity','0.1');
       						//预读本地文件示例，不支持ie8
       						obj.preview(function(index, file, result){							
       							$('#my_img').attr('src', result); //图片链接（base64）
       						});
       					},
       					done: function(res){
       						//如果上传失败
       						if(res.code > 0){
       							layer.msg('上传失败');
       							return;
       						}else{
       							$("#mpic").val(res.datas.url);
       							$("#mpic").attr("url",res.datas.url);
       							base.setCookie("mpic",new Date().getTime());
       						}
       						finish = true;
       						$('#my_img').animate({'opacity':'1'},'fast');
       						//上传成功
       					},
       					error: function(){
       						var demoText = $('#demoText');
       							demoText.html('<span style="color:#f63f34;">上传失败</span> <a class="layui-btn layui-btn-normal layui-btn-mini demo-reload">重试</a>');
       							demoText.find('.demo-reload').on('click', function(){
       							uploadInst.upload();
       						});
       					}
       				});
                       
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
              area: ['700px', '500px'],
              content: $('#edit')
      });
});
    });


	  
  
    </script>
  </body>
    <script id="list" type="text/html">
    {{#  layui.each(d.data.list, function(index, item){ }}
      <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td>{{item.mid}}</td>
            <td>{{item.mname}}</td>
        {{# layui.each(d.datas.cate,function(index,items){ }}
           {{# if(item.cid == items.cid){ }}
            <td>{{items.cname}}</td>
         {{# } }}
       {{# });}}
            <td width="100px" height="50px">{{item.mdesc}}</td>
            <td>{{item.userid}}</td>
            <td><img src="{{item.mpic}}" width="50px" height="50px" /></td>
            <td>{{new Date(item.mdate).format('yyyy-MM-dd')}}</td>
            <td class="td-manage">
    <button class="layui-btn layui-btn-primary layui-btn-small cla_edit" data-id="{{item.mid}}" >编辑</button>
    <button class="layui-btn layui-btn-primary layui-btn-small cla_del" data-id="{{item.mid}}">删除</button>
            </td>
          </tr>
    {{# }); }}
    </script>
    <script type="text/html" id="editTemplate">
<form class="layui-form layui-form-pane" lay-filter="edi-form" enctype="multipart/form-data" method="post">
 <input type="hidden" name="mid" id="mid" value="{{d.data.mid?d.data.mid:''}}" />
<input type="hidden" name="userid" id="mid" value="{{d.data.userid?d.data.userid:''}}" />
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
    <label for="sort" class="layui-form-label">
        <span class="x-red">*</span>分类
    </label>
    <div class="layui-input-inline">
    <select id="sort" name="cid" lay-verify="required" lay-search="" >
        {{# layui.each(d.datas.cate,function(index,item){ }}
                    {{# if(d.data && d.data.cid == item.cid){ }}
                        <option value="{{item.cid}}" selected="selected">{{item.cname}}</option>
                    {{# }else{ }}
                        <option value="{{item.cid}}">{{item.cname}}</option>
                    {{# } }}
                {{# }); }}
    </select>
    </div>
    <div class="layui-form-mid layui-word-aux">
        <span class="x-red">*</span>
    </div>
</div>
<div class="layui-form-item">
    <label for="phone" class="layui-form-label">
        <span class="x-red">*</span>描述
    </label>
    <div class="layui-input-inline">
    <textarea  id="phone" name="mdesc"  rows=＂4＂ cols=＂5＂ style="margin: 0px; width: 418px; height: 150px;">{{d.data.mdesc==undefined?'':d.data.mdesc}}</textarea>
    </div>
</div>
<div class="layui-form-item">
    <label for="birthday" class="layui-form-label">
        <span class="x-red">*</span>创建时间
    </label>
    <div class="layui-input-inline">
        <input type="text" id="birthday" name="mdate" required="" lay-verify="pass"
        autocomplete="off" class="layui-input" value="{{new Date(d.data.mdate).format('yyyy-MM-dd')==undefined?'':new Date(d.data.mdate).format('yyyy-MM-dd')}}" >
    </div> 
</div>
<div class="layui-form-item">
    <label for="birthday" class="layui-form-label">
        <span class="x-red">*</span>图片
    </label>
    <div class="layui-upload">
      <div class="layui-upload-list">
        <img class="layui-upload-img" id="my_img" src="{{d.data.mpic==undefined?'':d.data.mpic}}"  width="90px" height="90px">
        <p id="demoText" style="margin-left:50px">建议图片不超过500KB</p>
		<input type="hidden" name="mpic" id="mpic" value="{{d.data.mpic?d.data.mpic:''}}" />
      </div>
      <button type="button" class="layui-btn layui-btn-primary layui-btn-small" id="my_upload" style="margin-left:50px">上传图片</button>
    </div></div>
</div> 
<div class="layui-form-item">
    <button  class="layui-btn" lay-filter="adds" lay-submit="" style="margin-left:100px">
       提交
    </button>

</div>
</form>


</script>
 <!--     {{# if(d.data && d.data.mpic != null){ }}
       <img src="{{d.data.mpic==undefined?'':d.data.mpic}}" width="70px" height="70px"/>
    {{# } }} -->
</html>