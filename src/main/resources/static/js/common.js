$(function () {
    layui.use('element')
    var layer = null
    var form = null;

    layui.use('layer', function () {
        layer = layui.layer;

    });

    //loadTable()
    //loadTable2()
    //layer.msg("spark任务已完成！", {icon: 1})
    $('#crawl-form').submit(function () {
            startCrawler()
            return false;
        }
    )
    // $('#crawl-form').submit(function () {
    //         startCrawler()
    //         return false;
    //     }
    // )
    // getCrawlerStatus()
})

function startCrawler() {
    let k = $('#search_key').val();
    let type = $('#selectValue').val();
    $('#btn_crawl').attr('disabled')
    $('#btn_crawl').addClass('layui-btn-disabled')
    $('#btn_crawl').text("爬取任务正在进行中...")
    $.post('/spark', {
            content: k,
            type:type
        },
        function (data) {
            //layer.msg(data.code, {icon: 6})
            layer.msg("spark任务已完成！", {icon: 1})
            $('#btn_crawl').removeAttr('disabled')
            $('#btn_crawl').removeClass('layui-btn-disabled')
            $('#btn_crawl').text("提交")
            $(".layui-laypage-btn").click();
            loadTable()
        })

}







function loadTable() {
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#comment_table0'
            , title: 'spark'
            , height: 'full-250'
            , url: '/byPage' //数据接口
            , request: {
                pageName: 'currentPage'
            }
            , response: {
                statusCode: 1000 //规定成功的状态码，默认：0
            }
            , parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.data.sum, //解析数据长度
                    "data": res.data.rows //解析数据列表
                };
            }
            , cols: [[ //表头
                {field: 'id', title: 'ID', width: 70, sort: true, fixed: 'left'}
                , {field: 'name', title: '电影名', width: 1150}
                , {
                    field: 'people', title: '演员', width: 120, sort: true,

                }
                , {fixed: 'right', title: '操作', toolbar: '#bar_right', width: 120}
            ]]
            , page: true
            , toolbar: '#bar_left'
        });
        //监听工具条
        table.on('tool(comment_table)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if (layEvent === 'edit') { //删除
                let msg = '将要删除行<br>id：' + data.id + '<br>内容：未关注' ;
                layer.confirm(msg, {title: '真的要取关吗？'}, function (index) {
                    obj.update({
                        type: 1
                    },toolbar);
                    // table.reload('comment_table', {
                    //     url: '/byPage'
                    //     ,where: {} //设定异步数据接口的额外参数
                    //     //,height: 300
                    // });
                    //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    $.get('/weibo/attends/' + data.id,function (result) {
                        // console.log(result)
                        if (result.code ===1000) {
                            //alert("SUCCESS");
                            console.log(result)
                            console.log("请求成功");
                            table.reload('comment_table', {
                                url: '/byPage'
                                ,where: {} //设定异步数据接口的额外参数
                                //,height: 300
                            });
                            //window.location.href = "/index";
                            //$('#myModal').modal('hide');
                        } else {
                            alert("请求失败");
                        }
                    }, 'json')
                });
            } else if (layEvent === 'del') { //编辑
                let msg = '将要取消关注<br>id：' + data.id + '<br>内容：已关注'  ;
                layer.confirm(msg, {title: '真的要取关吗？'}, function (index) {
                    obj.update({
                        type: 2
                    },toolbar);

                    //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    $.get('/weibo/unAttends/' + data.id,function (result) {
                       // console.log(result)
                        if (result.code ===1000) {
                            //alert("SUCCESS");
                            console.log(result)
                            console.log("请求成功");
                            table.reload('comment_table', {
                                url: '/byPage'
                                ,where: {} //设定异步数据接口的额外参数
                                //,height: 300
                            });
                            //window.location.href = "/index";
                            //$('#myModal').modal('hide');
                        } else {
                            alert("请求失败");
                        }
                    }, 'json')
                });
            }
        });
        //头工具栏事件
        table.on('toolbar(comment_table)', function (obj) {
            if (obj.event == 'add_comment') {
                let content
                layer.prompt({
                        formType: 2,
                        title: '请输入评论内容'
                    },
                    function (value, index) {
                        content = value
                        layer.close(index);
                        layer.prompt({
                                value: 3,
                                title: '请输入评论类型'
                            },
                            function (value, index) {
                                layer.close(index);
                                $.post('/comment/insert', {
                                        content: content,
                                        type: value
                                    },
                                    function () {
                                        layer.msg('添加成功！', {icon: 6})
                                        $(".layui-laypage-btn").click();
                                    })
                            });
                    });
            } else if (obj.event == 'generator_model') {
                $.post('dispose/wordCount',
                    function (res) {
                        layer.alert(res.data)
                    })
            }
        });
    });
}

function loadTable2() {
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#comment_table2'
            , title: '微博用户'
            , height: 'full-250'
            , url: '/weibo/byFansPage' //数据接口
            , request: {
                pageName: 'currentPage'
            }
            , response: {
                statusCode: 1000 //规定成功的状态码，默认：0
            }
            , parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.data.sum, //解析数据长度
                    "data": res.data.rows //解析数据列表
                };
            }
            , cols: [[ //表头
                {field: 'id', title: 'ID', width: 70, sort: true, fixed: 'left'}
                , {field: 'name', title: '用户名', width: 1150}
                , {
                    field: 'type', title: '是否关注', width: 120, sort: true,
                    templet: function (res) {
                        let str
                        switch (res.type) {
                            case 2:
                                str = '粉丝';
                                break;
                            case 1:
                                str = '粉丝';
                                break;
                            default:
                                str = res.type;
                        }
                        return str;
                    }
                }
            ]]
            , page: true
        });

    });
}