/**
 * 全局配置
 * 不需要入口
 * config={authUrl:'',host:''}
 */
layui.define(['jquery', 'layer', 'basic', 'laytpl', 'laypage', 'flow'], function (exports) {
    "use strict";
    var $ = layui.$, basic = layui.basic, laytpl = layui.laytpl, laypage = layui.laypage, flow = layui.flow, authz, Base = function () {
        this.config = {
            host: basic.host
        };
    };
    //跨域支持
    $.ajaxSetup({crossDomain: true, xhrFields: {withCredentials: true}});

    //全局设置
    Base.prototype.set = function (options) {
        var that = this;
        if (options) {
            $.extend(true, that.config, options);
        }
        return that;
    };
    
    Base.prototype.logout = function () {
        base.fetchData(base.config.host + '/login/logout', {}, function (data) {
            if (data.succee) {
                if (self == top) {
                    location.href = "login.jsp";
                } else {
                    parent.location.href = "login.jsp";
                }//判断是否是iframe
            } else {
                layer.msg(data.msg);
            }
        }, 'JSON');
    };
    //分页封装
    Base.prototype.fetchData = function (url, params, callback, method,async) {
        basic.fetchData(url, params, callback, method,async);
    };

    Base.prototype.get = function (options, callback) {
        basic.get(options, callback);
    };

    Base.prototype.post = function (options, callback) {
        basic.post(options, callback);
    };

    Base.prototype.download = function (options) {
        basic.download(options);
    };

    Base.prototype.getCookie = function (cname) {
        return basic.getCookie(cname);
    };

    Base.prototype.setCookie = function (cname,cvalue) {
        return basic.setCookie(cname,cvalue);
    };

    Base.prototype.loaded = false;

    //渲染模板,data:数据,view:展示的对象,template:渲染模板对象
    Base.prototype.renderView = function (data, view, template,callback) {
        if (!data) {
            data = {};
        }
        if (!data.data) {
            data.data = [];
        }
        laytpl(template.innerHTML).render(data, function (html) {
            view.innerHTML = html;
            if (!base.loaded) {
                layui.config({remoteModules: ['authz'], remoteDir: base.config.host + '/auth/js/'});
                layui.use('authz', function (module) {
                    authz = module;
                    base.loaded = true;
                    authz.render("#" + view.id);
                    callback && callback(html);
                });
            } else {
                if (authz && view.id) {
                    authz.render("#" + view.id);
                    callback && callback(html);
                }
            }
        });
    };

    Base.prototype.renderHtml = function (data, template, callback) {
        if (!data) {
            data = {};
        }
        if (!data.data) {
            data.data = [];
        }
        if (!data.datas) {
            data.datas = [];
        }
        laytpl(template.innerHTML).render(data, function (html) {
            if (!base.loaded) {
                layui.config({remoteModules: ['authz'], remoteDir: base.config.host + '/auth/js/'});
                layui.use('authz', function (module) {
                    authz = module;
                    base.loaded = true;
                    return callback && callback(authz.renderHtml(html));
                });
            } else {
                if (callback && authz) {
                    return callback && callback(authz.renderHtml(html));
                }
            }
        });
    };

    //配置
    /**
     var options = {
        url: "url",
        view: view,//dom id,展示容器
        pageEle: 'laypage',//分页对象, id名称
        template: templateId,//dom id,模板
        prev:'prev',
        next:'next',
        query:{},//用于查询嵌入,json
        pager: {
            limit: 10,
            page: 1
        }
    };**/
    Base.prototype.fetchRenderTable = function renderTable(options) {
        if (!options) {
            layer.msg("请传入配置");
            return;
        }
        if (!options.pageEle) {
            layer.msg("请指定分页组件");
            return;
        }
        if (!options.view) {
            layer.msg("请指定展示容器组件");
            return;
        }
        if (!options.template) {
            layer.msg("请指定数据模板");
            return;
        }
        if (!options.url) {
            layer.msg("请指定url");
            return;
        }
        var url;
        if (options.url && (options.url.toLocaleLowerCase().indexOf("http") != -1)) {
            url = options.url;
        } else {
            url = base.config.host + options.url;
        }

        var prev = '<em><</em>', next = '<em>></em>';
        options.prev ? prev = options.prev : '';
        options.next ? next = options.next : '';

        var query = base.buildParams(options);
        if (options.type == 1) {//流加载判断
            base.fetchData(url, query, function (data) {
                flow.load({
                    elem: options.view //流加载容器。
					, scrollElem: options.scroll
                    , done: function (pages, next) { //执行下一页的回调
                        //模拟数据插入
                        setTimeout(function () {
                            var lis = [];
                            if (pages == 1) {
                                if(!data){
                                    data = {};
                                }
                                data.innerCallback = options.innerCallback;
                                data.innerConfig = options.innerConfig;
                                base.renderHtml(data, options.template, function (html) {
                                    lis.push(html);
                                    next(lis.join(''), pages < data.total / options.pager.limit);
                                    options.callback && options.callback(data);
                                });
                            } else {
                                options.pager.page = pages;
                                base.fetchData(url, base.buildParams(options), function (data) {
                                    if(!data){
                                        data = {};
                                    }
                                    data.innerCallback = options.innerCallback;
                                    data.innerConfig = options.innerConfig;
                                    base.renderHtml(data, options.template, function (html) {
                                        lis.push(html);
                                        next(lis.join(''), pages < data.total / options.pager.limit);
                                        options.callback && options.callback(data);
                                    });
                                });
                            }
                            //假设总页数为 10
                        }, 500);
                    }
                });
            });
        } else {
            base.fetchData(url, query, function (page) {
                laypage.render({
                    elem: options.pageEle,
                    count: page.data.total,
                    limit: options.pager.limit,
                    prev: page.data.prePage,
                    curr :options.pager.page,
                    next: page.data.nextPage,
                    jump: function (obj, first) {//模拟渲染
                        if (first) {
                            base.renderView(page, options.view, options.template);
                            if (typeof(page.data.total)=="undefined" || page.data.total < options.pager.limit) {
                                $('#' + options.pageEle).hide();
                            } else {
                                $('#' + options.pageEle).show();
                            }//总条数小于单页时隐藏分页控件
                        } else {
                            options.pager.limit = obj.limit;
                            options.pager.page = obj.curr;
                            base.fetchData(url, base.buildParams(options), function (data) {
                                base.renderView(data, options.view, options.template);
                            });
                        }
                    }
                });
            });
        }
    };

    Base.prototype.buildParams = function (options) {
        var query = $.param(options.pager);
        if (options.query) {
            query = query + '&' + $.param(options.query);
        }
        return query;
    };

    //全局配置基类
    var base = new Base()
        , dom = $(document), win = $(window);

    base.options = function (options) {
        base.set(options);
    };



    exports('base', base);
});
