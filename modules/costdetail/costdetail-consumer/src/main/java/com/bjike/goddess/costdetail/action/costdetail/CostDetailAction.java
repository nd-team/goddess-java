package com.bjike.goddess.costdetail.action.costdetail;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.WanyJackson;
import com.bjike.goddess.costdetail.api.CostDetailAPI;
import com.bjike.goddess.costdetail.bo.CostDetailBO;
import com.bjike.goddess.costdetail.entity.CostDetail;
import com.bjike.goddess.costdetail.entity.SonCostDetail;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 成本明细
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-25 04:09 ]
 * @Description: [ 成本明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("costdetail")
public class CostDetailAction {

    @Autowired
    private CostDetailAPI costDetailAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

//    /**
//     * 模块设置导航权限
//     *
//     * @throws ActException
//     * @version v1
//     */
//    @LoginAuth
//    @GetMapping("v1/setButtonPermission")
//    public Result setButtonPermission() throws ActException {
//        List<SonPermissionObject> list = new ArrayList<>();
//        try {
//            SonPermissionObject obj = new SonPermissionObject();
//            obj.setName("cuspermission");
//            obj.setDescribesion("设置");
//            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
//            if (!isHasPermission) {
//                //int code, String msg
//                obj.setFlag(false);
//            } else {
//                obj.setFlag(true);
//            }
//            list.add(obj);
//            return new ActResult(0, "设置权限", list);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

//    /**
//     * 下拉导航权限
//     *
//     * @throws ActException
//     * @version v1
//     */
    /*@LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = detailTypeAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }*/

//    /**
//     * 功能导航权限
//     *
//     * @param guidePermissionTO 导航类型数据
//     * @throws ActException
//     * @version v1
//     */
    /*@GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = detailTypeAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }*/

    @GetMapping("test")
    public Result getSave() throws SerException, IOException {
        CostDetail costDetail = new CostDetail();
        SonCostDetail sonCostDetail1 = new SonCostDetail();
        SonCostDetail sonCostDetail2 = new SonCostDetail();
        sonCostDetail1.setType("lee");
        sonCostDetail1.setDepict("ww");
        sonCostDetail1.setFifteenthDay(12.0);
        sonCostDetail1.setTenthDay(12.0);
        sonCostDetail1.setThirtiethDay(10.0);
        sonCostDetail1.setTwentiethDay(5.0);
        sonCostDetail2.setType("liu");
        sonCostDetail2.setDepict("ww");
        sonCostDetail2.setFifteenthDay(42.0);
        sonCostDetail2.setTenthDay(3.0);
        costDetail.setType("king");
        costDetail.setTenthDay(1.0);
        costDetail.setDepartment("ww");
        costDetail.setSonCostDetails(Arrays.asList(sonCostDetail1, sonCostDetail2));
        List<CostDetail> list = Arrays.asList(costDetail);
        costDetailAPI.getSave(costDetail);
//        costDetailAPI.testlist(list);
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(list);
//        List<CostDetail> list1 = objectMapper.getTypeFactory().constructParametricType();
        System.out.println(str);
        System.out.println("hello");

        return new ActResult("success", costDetailAPI.getDao());
    }

    /**
     * 列表
     *
     * @param date       日期
     * @param department 部门
     * @return class CostDetailBO
     * @throws SerException
     * @version v1
     */
    @GetMapping("v1/find")
    public Result getCostDetail(String date, String department) throws SerException {
        List<CostDetailBO> list = costDetailAPI.getCostDetail(date, department);
        return new ActResult("success", list);
    }

    /**
     * 添加
     *
     * @throws SerException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addCostDetail(String data) throws ActException, SerException, IOException {

        System.out.println(data);
        try {
            if (data.indexOf("{\"total\":0,\"tenthDay\":0,\"fifteenthDay\":0,\"twentiethDay\":0,\"thirtiethDay\":0}") != -1||data.indexOf("{\"total\":0,\"tenthDay\":0,\"fifteenthDay\":0,\"twentiethDay\":0,\"thirtiethDay\":0,\"type\":\"\"}")!=-1) {
                throw new SerException("check your ass");
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
        List<CostDetailBO> list = WanyJackson.batman(data, CostDetailBO.class);
        List<CostDetail> costDetails = BeanTransform.wanycopyProperties(list, CostDetail.class);
        costDetailAPI.testlist(costDetails);
        return new ActResult("success");
    }

    /**
     * 编辑
     *
     * @param data c
     * @throws SerException
     * @version v1
     */

    @PostMapping("v1/update")
    public Result updateCostDetail(String data) throws SerException, IOException {
        System.out.println(data);
//        costDetailAPI.update(WanyJackson.batman(data, CostDetail.class));
//        costDetailAPI.update(costDetails);
        return new ActResult("success");
    }

    /**
     * 删除
     *
     * @param date       c
     * @param department c
     * @version v1
     */
    @DeleteMapping("v1/del")
    public Result delCostDetail(String date, String department) {
        costDetailAPI.del(date, department);
        return new ActResult("hello world");
    }

    @GetMapping("v1/dateAndDepart")
    public Result dateAndDepart() throws SerException {
        return new ActResult("success", costDetailAPI.dateAndDepart());
    }

}