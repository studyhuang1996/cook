/**
 * 全局配置
 * 不需要入口
 * config={authUrl:'',host:''}
 */
layui.define(['jquery'], function (exports) {
    "use strict";
    var $ = layui.jquery,jsonLoad, Basic = function () {
        this.host = "http://localhost:8080";//
        this.config = {
            loginUrl: "/login.jsp"
        };
        this.options = {
            dataType: "JSON",
            beforeSend:function(){
                jsonLoad = parent.layer.load(2);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("当前没有服务或服务异常");
                if(jqXHR.status == 0){
                    location.href = basic.config.loginUrl;
                }
            },
            complete:function(){
                parent.layer.close(jsonLoad);
            }
        };
        this.statusCode = {
            500: function (xhr) {
                console.warn("服务异常");
            },
            502: function (xhr) {
                layer.msg("当前没有服务");
            },
            401: function (xhr) {
                location.href = basic.config.loginUrl;
            },
            403: function (xhr) {
                layer.msg("没有权限访问哦");
            }
        };
    };
    //跨域支持
    $.ajaxSetup({crossDomain: true, xhrFields: {withCredentials: true}});

    //全局设置
    Basic.prototype.set = function (options) {
        var that = this;
        if (options) {
            $.extend(true, that.config, options);
        }
        return that;
    };

    Basic.prototype.download = function(options){
        if(options && options.url) {
            var downform = $("<form method='post' />");
            downform.attr("action",options.url);
            $.each(options.data,function(index,item){
                var item = '<input type="hidden" name="'+index+'" value="'+item+'" />';
               $(downform).append(item);
            });
            $(document.body).append($(downform));
            $(downform)[0].submit();
            $(downform).remove();
        }
    };

    Basic.prototype.get = function(options,callback){
        var _options = $.extend({},this.options,options);
        _options.type = 'GET';
        if (!_options.url) {
            layer.msg("请配置url");
            return;
        }
        if (!callback) {
            layer.msg("请配置回调");
            return;
        }
        _options.success= function (data,textStatus,jqXHR) {
            callback(data,jqXHR);
        };
        _options.beforeSend = function(){
            jsonLoad = parent.layer.load(2);
        };
        _options.error = function (jqXHR, textStatus, errorThrown) {
            console.warn("当前没有服务或服务异常");
            if(jqXHR.status == 0){
                location.href = basic.config.loginUrl;
            }
        };
        _options.complete = function(){
            parent.layer.close(jsonLoad);
        };
        $.ajax(_options);
    };

    Basic.prototype.post = function(options,callback){
        var _options = $.extend({},this.options,options);
        _options.type = 'POST';
        if (!_options.url) {
            layer.msg("请配置url");
            return;
        }
        if (!callback) {
            layer.msg("请配置回调");
            return;
        }
        _options.success= function (data,textStatus,jqXHR) {
            callback(data,jqXHR);
        };
        _options.beforeSend = function(){
            jsonLoad = parent.layer.load(2);
        };
        _options.error = function (jqXHR, textStatus, errorThrown) {
            console.warn("当前没有服务或服务异常");
            if(jqXHR.status == 0){
                location.href = basic.config.loginUrl;
            }
        };
        _options.complete = function(){
            parent.layer.close(jsonLoad);
        };
        $.ajax(_options);
    };

    //分页封装
    Basic.prototype.fetchData = function (url, params, callback, method, async) {
        if (!url) {
            layer.msg("请配置url");
            return;
        }
        if (!callback) {
            layer.msg("请配置回调");
            return;
        }
        if (async) {
            async = true;
        }
        var _method = "POST", _contentType = "application/x-www-form-urlencoded";
        (method && method.toUpperCase() == "GET") ? _method = "GET" : "";
        $.ajax({
            url: url,
            data: params,
            dataType: "JSON",
            type: _method,
            async: async,
            beforeSend:function(){
                jsonLoad = parent.layer.load(2);
            },
            statusCode:this.statusCode,
            success: function (data,textStatus,jqXHR) {
                callback(data,jqXHR);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.warn("当前没有服务或服务异常");
                if(jqXHR.status == 0){
                    location.href = basic.config.loginUrl;
                }
            },
            complete:function(){
                parent.layer.close(jsonLoad);
            }
        });
    };

    Basic.prototype.uuid = function(len, radix) {
        var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
        var uuid = [], i;
        radix = radix || chars.length;
        if (len) {
            for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
        } else {
            var r;
            uuid[8] = uuid[13] = uuid[18] = uuid[23] = '';
            uuid[14] = '4';
            for (i = 0; i < 36; i++) {
                if (!uuid[i]) {
                    r = 0 | Math.random()*16;
                    uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
                }
            }
        }
        return uuid.join('');
    };

    Basic.prototype.setCookie = function (cname, cvalue, minutes) {
        if (!minutes) {
            minutes = 30;
        }
        var d = new Date();
        d.setTime(d.getTime() + (minutes * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires;
    };
    Basic.prototype.getCookie = function (cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1);
            if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
        }
        return "";
    };
    Basic.prototype.clearCookie = function (cname) {
        basic.setCookie(cname, "", -1);
    };

    //工具初始化
    Date.prototype.format = function (format) {
        var date = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S+": this.getMilliseconds()
        };
        if (/(y+)/i.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
        }
        for (var k in date) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1
                    ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
            }
        }
        return format;
    };

    Array.prototype.remove = function(val){
        var index = this.indexOf(val);
        if(index != -1){
            this.splice(index,1);
        }
    };

    Date.prototype.addDay = function(days){
        this.setDate(this.getDate()+days);
        return this;
    };
    Date.prototype.addYear = function(years){
        this.setDate(this.getYear()+years);
        return this;
    };

    //全局配置基类
    var basic = new Basic()
        , dom = $(document), win = $(window);
    basic.options = function (options) {
        basic.set(options);
    };
    exports('basic', basic);
});
