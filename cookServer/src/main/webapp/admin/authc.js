/**
 * 用于用户认证校验
 * 不需要入口,依赖base模块
 */
layui.define(['base'], function (exports) {
    "use strict";
    var $ = layui.$, base = layui.base, Authc = function () {
        this.config = {
            authUrl: "/login/check",
            during: 1//minute
        }
    };
    $.ajaxSetup({crossDomain: true, xhrFields: {withCredentials: true}});

    //预备校验:authUrl:options.authUrl
    Authc.prototype.prepare = function (options) {
        if (options) {
            if (options.host && options.authUrl) {
                authc.config.authUrl = options.host.concat(options.authUrl);
            } else {
                authc.config.authUrl = options.authUrl;
            }
        } else {
            authc.config.authUrl = base.config.host.concat(authc.config.authUrl);
        }
    };

    Authc.prototype.check = function (during, callback) {
        var timer = setTimeout(function () {
            authc.check(during, callback);
        }, during * 1000 * 60);
        callback(timer);
    };

    Authc.prototype.checker = function (timer) {
        base.fetchData(authc.config.authUrl,{},function(data,xhr){
            if (!data.succee) {
                clearTimeout(timer);
                authc.check.failure ? authc.check.failure(data) : '';
            } else {
                authc.check.success ? authc.check.success(data,xhr) : '';
            }
        });
    };

    Authc.prototype.start = function (success, failure) {
        authc.check.success = success;
        authc.check.failure = failure;
        authc.check(authc.config.during, authc.checker);
    };

    //自动完成渲染
    var authc = new Authc();
    //认证检测
    authc.prepare();

    exports('authc', authc);
});

 
