$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'departmentDetail/v1/datalist',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', width: 100, key: true ,hidden:true},
            { label: '部门编号', name: 'serialNumber', width: 75 },
            { label: '所属体系', name: 'hierarchyName', width: 90 },
            { label: '项目组/部门', name: 'department', width: 100 },
            { label: '所属地区', name: 'area', width: 100 },
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
        departmentDetail : {},
        hierarchys : []
    },
    methods : {
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.departmentDetail = {};
            vm.getHierarchys();
        },
        update: function(){
            // var rowId = getSelectedRow();
            // if(rowId == null){
            //     return ;
            // }
            // vm.title = "更新";
            // vm.showList = false;
            //
            // var rowData = $("#jqGrid").jqGrid("getRowData",rowId);
            // console.log(rowData);
            //
            // vm.hierarchy = rowData;

        },
        saveOrUpdate: function () {
            // var url = vm.hierarchy.id == null ? "hierarchy/v1/save" : "hierarchy/v1/update/"+vm.hierarchy.id ;
            // var type = vm.hierarchy.id == null ?"POST":"PUT"
            // $.ajax({
            //     type: type,
            //     url: baseURL + url,
            //     data:{
            //         hierarchy : vm.hierarchy.hierarchy,
            //         serialNumber : vm.hierarchy.serialNumber,
            //         description : vm.hierarchy.description
            //     } ,
            //     success: function(r){
            //         if(r.code === 0){
            //             alert('操作成功', function(){
            //                 vm.reload();
            //             });
            //         }else{
            //             alert(r.msg);
            //         }
            //     }
            // });
        },
        del: function () {

        },
        freeze : function () {

        },
        enable : function () {

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
            var department = vm.departmentDetail.department;

            $("#txt_ser").val(pinyinUtil.getFirstLetter(department, false));

        },
        changeType : function (ele) {
            var optionTxt = $(ele.target).find("option:selected").text();
            var optionVal = ele.target.value;

            vm.departmentDetail.area = optionTxt;
            console.log(vm.departmentDetail.area);
        },
        changeHierarchy : function (ele) {
            var optionTxt = $(ele.target).find("option:selected").text();
            var optionVal = ele.target.value;
            vm.departmentDetail.hierarchyId = optionVal;

            console.log(vm.departmentDetail.hierarchyId);
        },
        getHierarchys: function(){
            $.get(baseURL + "hierarchy/v1/findStatus", function(r){
                console.log(r);
                vm.hierarchys = r.data;
            },"json");
        }


    }
});