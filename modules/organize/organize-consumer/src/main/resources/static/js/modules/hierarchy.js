$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'hierarchy/v1/datalist',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', width: 100, key: true ,hidden:true},
            { label: '体系编号', name: 'serialNumber', width: 75 },
            { label: '体系', name: 'hierarchy', width: 90 },
            { label: '描述', name: 'description', width: 100 },
            { label: '状态', name: 'status', width: 80,formatter: function(value, options, row){
                return value === 'CONGEAL' ?
                    '<span class="label label-danger">禁用</span>' :
                    '<span class="label label-success">正常</span>';
            }}
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
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
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

        },
        showList: true,  //控制div开关
        title:null,       //div表头
        hierarchy : {}
    },
    methods : {
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.hierarchy = {};
        },
        update: function(){
            var rowId = getSelectedRow();
            if(rowId == null){
                return ;
            }
            vm.title = "更新";
            vm.showList = false;

            var rowData = $("#jqGrid").jqGrid("getRowData",rowId);
            console.log(rowData);

            vm.hierarchy = rowData;

        },
        saveOrUpdate: function () {
            var url = vm.hierarchy.id == null ? "hierarchy/v1/save" : "hierarchy/v1/update/"+vm.hierarchy.id ;
            var type = vm.hierarchy.id == null ?"POST":"PUT"
            $.ajax({
                type: type,
                url: baseURL + url,
                // contentType: "application/json",
                // contentType: "application/x-www-form-urlencoded",
                data:{
                    // data:JSON.stringify(vm.hierarchy)
                    hierarchy : vm.hierarchy.hierarchy,
                    serialNumber : vm.hierarchy.serialNumber,
                    description : vm.hierarchy.description
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
        del: function () {
            var rowIds = getSelectedRows();
            if(rowIds == null){
                return ;
            }
            console.log(JSON.stringify(rowIds).replace("[","").replace("]","").replace(/\"/g, ""));

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "hierarchy/v1/delete",
                    contentType: "application/x-www-form-urlencoded",
                    data:{
                        ids: JSON.stringify(rowIds).replace("[","").replace("]","").replace(/\"/g, "")
                    },
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
        freeze : function () {
            var rowIds = getSelectedRows();
            if(rowIds == null){
                return ;
            }
            console.log(JSON.stringify(rowIds).replace("[","").replace("]","").replace(/\"/g, ""));

            confirm('确定要冻结选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "hierarchy/v1/freeze",
                    contentType: "application/x-www-form-urlencoded",
                    data:{
                        ids: JSON.stringify(rowIds).replace("[","").replace("]","").replace(/\"/g, "")
                    },
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
        enable : function () {
            var rowIds = getSelectedRows();
            if(rowIds == null){
                return ;
            }
            console.log(JSON.stringify(rowIds).replace("[","").replace("]","").replace(/\"/g, ""));

            confirm('确定要冻结选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "hierarchy/v1/enable",
                    contentType: "application/x-www-form-urlencoded",
                    data:{
                        ids: JSON.stringify(rowIds).replace("[","").replace("]","").replace(/\"/g, "")
                    },
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
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
        },
        onkeyup: function (msg) {
            var hierarchy = vm.hierarchy.hierarchy;
            console.log(pinyinUtil.getFirstLetter(hierarchy, false));
            console.log(vm.hierarchy.hierarchy);
            vm.hierarchy.serialNumber = pinyinUtil.getFirstLetter(hierarchy, false);

            $("#txt_ser").val(pinyinUtil.getFirstLetter(hierarchy, false));

        }
    }
});





























