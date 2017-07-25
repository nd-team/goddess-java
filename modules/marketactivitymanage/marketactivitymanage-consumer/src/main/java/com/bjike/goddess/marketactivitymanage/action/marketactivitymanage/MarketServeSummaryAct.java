package com.bjike.goddess.marketactivitymanage.action.marketactivitymanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.api.MarketServeApplyAPI;
import com.bjike.goddess.marketactivitymanage.api.MarketServeRecordAPI;
import com.bjike.goddess.marketactivitymanage.api.MarketServeSummaryAPI;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeSummaryBO;
import com.bjike.goddess.marketactivitymanage.bo.ServeSummaryBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeSummaryDTO;
import com.bjike.goddess.marketactivitymanage.excel.SonPermissionObject;
import com.bjike.goddess.marketactivitymanage.to.GuidePermissionTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeSummaryTO;
import com.bjike.goddess.marketactivitymanage.vo.MarketServeSummaryVO;
import com.bjike.goddess.marketactivitymanage.vo.ServeSummaryVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 市场招待汇总及邮件发送
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 02:19 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketservesummary")
public class MarketServeSummaryAct {

    @Autowired
    private MarketServeSummaryAPI marketServeSummaryAPI;

    @Autowired
    private MarketServeApplyAPI marketServeApplyAPI;

    @Autowired
    private MarketServeRecordAPI marketServeRecordAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = marketServeSummaryAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = marketServeSummaryAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据id查询市场招待汇总邮件发送
     *
     * @param id 市场招待汇总邮件发送唯一标识
     * @return class MarketServeSummaryVO
     * @version v1
     */
    @GetMapping("v1/marketservesummary/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MarketServeSummaryBO bo = marketServeSummaryAPI.findById(id);
            MarketServeSummaryVO vo = BeanTransform.copyProperties(bo, MarketServeSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 市场招待汇总邮件发送dto
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated MarketServeSummaryDTO dto, BindingResult result) throws ActException {
        try {
            Long count = marketServeSummaryAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询市场招待邮件
     *
     * @param dto 市场招待邮箱发送传输对象
     * @return class MarketServeSummaryVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MarketServeSummaryDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<MarketServeSummaryBO> boList = marketServeSummaryAPI.list(dto);
            List<MarketServeSummaryVO> voList = BeanTransform.copyProperties(boList, MarketServeSummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加市场招待邮件发送
     *
     * @param to 市场招待邮件发送to信息
     * @return class MarketServeSummaryVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) MarketServeSummaryTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MarketServeSummaryBO bo = marketServeSummaryAPI.save(to);
            MarketServeSummaryVO vo = BeanTransform.copyProperties(bo, MarketServeSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除市场招待邮件发送
     *
     * @param id 市场招待邮件发送唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            marketServeSummaryAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑市场招待邮件发送
     *
     * @param to 市场招待邮件发送to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MarketServeSummaryTO to, BindingResult result) throws ActException {
        try {
            marketServeSummaryAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻市场招待邮件发送
     *
     * @param id 市场招待邮件发送唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable(value = "id") String id) throws ActException {
        try {
            marketServeSummaryAPI.thaw(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结市场招待邮件发送
     *
     * @param id 市场招待邮件发送唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable(value = "id") String id) throws ActException {
        try {
            marketServeSummaryAPI.congeal(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场招待汇总
     *
     * @param type            汇总类型true:按照计划汇总,false:按照实际汇总
     * @param projectGroups   部门/项目组
     * @param startTimeString 起始时间
     * @param endTimeString   结束时间
     * @return class MarketServeSummaryVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/summarize")
    public Result summarize(Boolean type, String[] projectGroups, String startTimeString, String endTimeString, HttpServletRequest request) throws ActException {
        try {
            List<ServeSummaryBO> boList = marketServeSummaryAPI.summarize(type, projectGroups, startTimeString, endTimeString);
            List<ServeSummaryVO> voList = BeanTransform.copyProperties(boList, ServeSummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有市场活动的项目名称
     *
     * @param type
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findProjectName")
    public Result findApplyProjectName(@RequestParam Boolean type) throws ActException {
        try {
//            if(type==null){
//                throw new SerException("类型不能为空");
//            }
            List<String> projectNames = new ArrayList<>();
            if (type) {
                projectNames = marketServeApplyAPI.findAllProjectName();
            } else {
                projectNames = marketServeRecordAPI.findAllProjectName();
            }
            return ActResult.initialize(projectNames);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场活动汇总邮件发送检查
     *
     * @throws ActException
     * @des 市场活动汇总邮件发送检查
     * @version v1
     */
    @GetMapping("v1/checkEmail")
    public Result checkEmail() throws ActException {
        try {
            marketServeSummaryAPI.checkSendEmail();
            return ActResult.initialize("发送成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}