package com.bjike.goddess.interiorrecommend.action.interiorrecommend;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.api.CollectAwardAPI;
import com.bjike.goddess.interiorrecommend.bo.CollectAwardBO;
import com.bjike.goddess.interiorrecommend.excel.SonPermissionObject;
import com.bjike.goddess.interiorrecommend.service.CollectAwardSer;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.vo.CollectAwardVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
* 内部推荐奖管理汇总
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 11:43 ]
* @Description:	[ 内部推荐奖管理汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("collectaward")
public class CollectAwardAction {

    @Autowired
    private CollectAwardAPI collectAwardAPI;

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
//
//
//    /**
//     * 下拉导航权限
//     *
//     * @throws ActException
//     * @version v1
//     */
//    @LoginAuth
//    @GetMapping("v1/sonPermission")
//    public Result sonPermission() throws ActException {
//        try {
//
//            List<SonPermissionObject> hasPermissionList = collectAwardAPI.sonPermission();
//            return new ActResult(0, "有权限", hasPermissionList);
//
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 功能导航权限
//     *
//     * @param guidePermissionTO 导航类型数据
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/guidePermission")
//    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//
//            Boolean isHasPermission = collectAwardAPI.guidePermission(guidePermissionTO);
//            if (!isHasPermission) {
//                //int code, String msg
//                return new ActResult(0, "没有权限", false);
//            } else {
//                return new ActResult(0, "有权限", true);
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 根据月份来汇总
     * @param year 年份
     * @param month 月份
     * @return class CollectAwardVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/collect/month")
    public Result collectMonth(@RequestParam String year,@RequestParam String month) throws ActException{
        try {
            CollectAwardBO bo = collectAwardAPI.collectByMonth(year,month);
            CollectAwardVO vo = BeanTransform.copyProperties(bo,CollectAwardVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 累计汇总
     * @param today 汇总条件
     * @return class CollectAwardVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/collect/all")
    public Result collectAll(@RequestParam String today) throws ActException{
        try {
            CollectAwardBO bo = collectAwardAPI.allCollect(today);
            CollectAwardVO vo = BeanTransform.copyProperties(bo,CollectAwardVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

 }