$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'index/datalist',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', width: 100, key: true },
            { label: '用户名', name: 'name', width: 75 },
            { label: 'week', name: 'week', width: 90 },
            { label: 'time', name: 'time', width: 100 },
            { label: 'absenteeismDay', name: 'absenteeismDay', width: 80},
            { label: 'actualDay', name: 'actualDay', width: 80},
            { label: 'vacateDay', name: 'vacateDay', width: 80},
            { label: 'finishDay', name: 'finishDay', width: 80},
            { label: 'attendanceDay', name: 'attendanceDay', width: 80}

        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "data.page.list",
            page: "data.page.currPage",
            total: "data.page.totalPage",
            records: "data.page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            name: null
        },               //查询对象的参数
        showList: true,  //控制div开关
        title:null,       //div表头
        user:{},
        users:[]
    },
    methods: {
        query: function () {
            vm.reload();    //刷新页面
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.user = {};
        },
        update: function () {
            var userId = getSelectedRow();
            if(userId == null){
                return ;
            }

            vm.showList = false;
            vm.title = "修改";

            vm.getUser(userId);
        },
        del: function () {
            var userIds = getSelectedRows();
            if(userIds == null){
                return ;
            }
            console.log(userIds);

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "index/delete",
                    contentType: "application/json",
                    data: JSON.stringify(userIds),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function () {
            var url = vm.user.id == null ? "index/save" : "index/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                // contentType: "application/json",
                // contentType: "application/x-www-form-urlencoded",
                data:{
                    data:JSON.stringify(vm.user)
                } ,
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        getUser: function(userId){
            $.get(baseURL + "index/info/"+userId, function(r){
                console.log(r);
                vm.user = r.data.user;
            },"json");
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
        }
    }
});