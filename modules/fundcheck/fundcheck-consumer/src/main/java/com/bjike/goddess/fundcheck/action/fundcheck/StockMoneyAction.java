package com.bjike.goddess.fundcheck.action.fundcheck;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.fundcheck.api.StockMoneyAPI;
import com.bjike.goddess.fundcheck.bo.BackBO;
import com.bjike.goddess.fundcheck.bo.StockMoneyBO;
import com.bjike.goddess.fundcheck.dto.BackDTO;
import com.bjike.goddess.fundcheck.dto.StockMoneyDTO;
import com.bjike.goddess.fundcheck.to.BackTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.StockMoneyCollectTO;
import com.bjike.goddess.fundcheck.to.StockMoneyTO;
import com.bjike.goddess.fundcheck.vo.BackVO;
import com.bjike.goddess.fundcheck.vo.StockMoneyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 收到股东款
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:51 ]
 * @Description: [ 收到股东款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("stockmoney")
public class StockMoneyAction {
    @Autowired
    private StockMoneyAPI stockMoneyAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = stockMoneyAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 收到股东款列表总条数
     *
     * @param stockMoneyDTO 收到股东款dto
     * @des 获取所有收到股东款
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(StockMoneyDTO stockMoneyDTO) throws ActException {
        try {
            Long count = stockMoneyAPI.count(stockMoneyDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个收到股东款
     *
     * @param id
     * @return class StockMoneyVO
     * @des 获取一个收到股东款
     * @version v1
     */
    @GetMapping("v1/stock/{id}")
    public Result stock(@PathVariable String id) throws ActException {
        try {
            StockMoneyBO stockMoneyBO = stockMoneyAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(stockMoneyBO, StockMoneyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 收到股东款列表
     *
     * @param stockMoneyDTO 收到股东款dto
     * @return class StockMoneyVO
     * @des 获取所有收到股东款
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(StockMoneyDTO stockMoneyDTO, HttpServletRequest request) throws ActException {
        try {
            List<StockMoneyVO> stockMoneyVOS = BeanTransform.copyProperties(
                    stockMoneyAPI.findList(stockMoneyDTO), StockMoneyVO.class, request);
            return ActResult.initialize(stockMoneyDTO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加收到股东款
     *
     * @param stockMoneyTO 收到股东款to
     * @return class StockMoneyVO
     * @des 添加收到股东款
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) StockMoneyTO stockMoneyTO, BindingResult bindingResult) throws ActException {
        try {
            StockMoneyBO stockMoneyBO = stockMoneyAPI.insert(stockMoneyTO);
            return ActResult.initialize(BeanTransform.copyProperties(stockMoneyBO, StockMoneyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑收到股东款
     *
     * @param stockMoneyTO 收到股东款数据to
     * @return class StockMoneyVO
     * @des 编辑收到股东款
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) StockMoneyTO stockMoneyTO, BindingResult bindingResult) throws ActException {
        try {
            StockMoneyBO stockMoneyBO = stockMoneyAPI.edit(stockMoneyTO);
            return ActResult.initialize(BeanTransform.copyProperties(stockMoneyBO, StockMoneyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除收到股东款
     *
     * @param id 用户id
     * @des 根据用户id删除收到股东款记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            stockMoneyAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param to 收到股东款数据to
     * @return class StockMoneyVO
     * @des 编辑收到股东款
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@Validated StockMoneyCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            List<StockMoneyVO> stockMoneyVOS = BeanTransform.copyProperties(stockMoneyAPI.collect(to), StockMoneyVO.class);
            return ActResult.initialize(stockMoneyVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}