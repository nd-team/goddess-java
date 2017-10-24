package com.bjike.goddess.financeinit.action.financeinit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.CurrencyAPI;
import com.bjike.goddess.financeinit.bo.CurrencyBO;
import com.bjike.goddess.financeinit.dto.CurrencyDTO;
import com.bjike.goddess.financeinit.entity.Currency;
import com.bjike.goddess.financeinit.to.CurrencyTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.vo.CurrencyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 设置币别
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:17 ]
 * @Description: [ 设置币别 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("currency")
public class CurrencyAction {
    @Autowired
    private CurrencyAPI currencyAPI;
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

            Boolean isHasPermission = currencyAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param currencyDTO 公司基本信息dto
     * @des 获取所有公司基本信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CurrencyDTO currencyDTO) throws ActException {
        try {
            Long count = currencyAPI.countCurren(currencyDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个公司基本信息
     *
     * @param id 公司基本信息id
     * @return class CurrencyVO
     * @des 根据id获取公司基本信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            CurrencyVO currencyVO = BeanTransform.copyProperties(
                    currencyAPI.getOneById(id), CurrencyVO.class);
            return ActResult.initialize(currencyVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 公司基本信息列表
     *
     * @param currencyDTO 公司基本信息dto
     * @return class CurrencyVO
     * @des 获取所有公司基本信息
     * @version v1
     */
    @GetMapping("v1/listAccount")
    public Result findListAccount(CurrencyDTO currencyDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<CurrencyVO> companyBasicInfoVOS = BeanTransform.copyProperties(
                    currencyAPI.listCurren(currencyDTO), CurrencyVO.class, request);
            return ActResult.initialize(companyBasicInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加公司基本信息
     *
     * @param currencyTO 公司基本信息数据to
     * @return class CurrencyVO
     * @des 添加公司基本信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAccount(@Validated(value = ADD.class) CurrencyTO currencyTO, BindingResult bindingResult) throws ActException {
        try {
            CurrencyBO currencyBO = currencyAPI.addCurren(currencyTO);
            return ActResult.initialize(BeanTransform.copyProperties(currencyBO, CurrencyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑公司基本信息
     *
     * @param currencyTO 公司基本信息数据bo
     * @return class CurrencyVO
     * @des 编辑公司基本信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAccount(@Validated(value = EDIT.class) CurrencyTO currencyTO, BindingResult bindingResult) throws ActException {
        try {
            CurrencyBO currencyBO = currencyAPI.editCurren(currencyTO);
            return ActResult.initialize(BeanTransform.copyProperties(currencyBO, CurrencyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除公司基本信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAccount(@PathVariable String id) throws ActException {
        try {
            currencyAPI.deleteCurren(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }
}