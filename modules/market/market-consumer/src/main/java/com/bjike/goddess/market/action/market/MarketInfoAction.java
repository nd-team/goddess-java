package com.bjike.goddess.market.action.market;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.market.api.MarketInfoAPI;
import com.bjike.goddess.market.bo.MarketInfoBO;
import com.bjike.goddess.market.dto.MarketInfoDTO;
import com.bjike.goddess.market.excel.SonPermissionObject;
import com.bjike.goddess.market.to.GuidePermissionTO;
import com.bjike.goddess.market.to.MarketInfoTO;
import com.bjike.goddess.market.vo.MarketInfoVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 市场信息管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-21T11:00:01.563 ]
 * @Description: [ 市场信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketinfo")
public class MarketInfoAction extends BaseFileAction{
    @Autowired
    private MarketInfoAPI marketInfoAPI;
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
    public Result i() throws ActException {
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

            List<SonPermissionObject> hasPermissionList = marketInfoAPI.sonPermission();
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

            Boolean isHasPermission = marketInfoAPI.guidePermission(guidePermissionTO);
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
     * 市场信息管理列表总条数
     *
     * @param marketInfoDTO 市场信息管理dto
     * @des 获取所有市场信息管理总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MarketInfoDTO marketInfoDTO) throws ActException {
        try {
            Long count = marketInfoAPI.countMarketInfo(marketInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个市场信息管理
     *
     * @param id
     * @des 获取一个市场信息管理
     * @return  class MarketInfoVO
     * @version v1
     */
    @GetMapping("v1/market/{id}")
    public Result market(@PathVariable String id) throws ActException {
        try {
            MarketInfoBO marketInfoBO = marketInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(marketInfoBO , MarketInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息列表
     *
     * @param marketInfoDTO 市场信息dto
     * @return class MarketInfoVO
     * @des 获取所有市场信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MarketInfoDTO marketInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<MarketInfoVO> marketInfoVOS = BeanTransform.copyProperties
                    (marketInfoAPI.findListMarketInfo(marketInfoDTO), MarketInfoVO.class,request);
            return ActResult.initialize(marketInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加市场信息
     *
     * @param marketInfoTO 市场信息数据to
     * @return class MarketInfoVO
     * @des 添加市场信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) MarketInfoTO marketInfoTO, BindingResult bindingResult) throws ActException {
        try {
            MarketInfoBO marketInfoBO = marketInfoAPI.insertMarketInfo(marketInfoTO);
            return ActResult.initialize(marketInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑市场信息
     *
     * @param marketInfoTO 市场信息数据to
     * @return class MarketInfoVO
     * @des 编辑市场信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) MarketInfoTO marketInfoTO,BindingResult bindingResult) throws ActException {
        try {
            MarketInfoBO marketInfoBO = marketInfoAPI.editMarketInfo(marketInfoTO);
            return ActResult.initialize(marketInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除市场信息
     *
     * @param id 用户id
     * @des 根据用户id删除市场信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            marketInfoAPI.removeMarketInfo(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取客户名称
     *
     * @des 获取客户名称集合
     * @version v1
     */
    @GetMapping("v1/names")
    public Result names() throws ActException {
        try {
            List<String> namesList = marketInfoAPI.getCustomerName();
            return ActResult.initialize(namesList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 导出excel
     *
     * @param dto 市场信息
     * @des 导出市场信息
     * @version v1
     */
    //@LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(MarketInfoDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "市场信息.xlsx";
            super.writeOutFile(response, marketInfoAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


}