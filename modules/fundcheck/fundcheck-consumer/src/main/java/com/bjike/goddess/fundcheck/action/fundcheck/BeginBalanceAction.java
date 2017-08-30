package com.bjike.goddess.fundcheck.action.fundcheck;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.fundcheck.api.BeginBalanceAPI;
import com.bjike.goddess.fundcheck.bo.BeginBalanceBO;
import com.bjike.goddess.fundcheck.dto.BeginBalanceDTO;
import com.bjike.goddess.fundcheck.to.BeginBalanceTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.vo.BeginBalanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 期初余额
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-05 03:34 ]
 * @Description: [ 期初余额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("beginbalance")
public class BeginBalanceAction {
    @Autowired
    private BeginBalanceAPI beginBalanceAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = beginBalanceAPI.guidePermission(guidePermissionTO);
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
     * 期初余额列表总条数
     *
     * @param beginBalanceDTO 期初余额dto
     * @des 获取所有期初余额
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BeginBalanceDTO beginBalanceDTO) throws ActException {
        try {
            Long count = beginBalanceAPI.count(beginBalanceDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个期初余额
     *
     * @param id
     * @return class BeginBalanceVO
     * @des 获取一个期初余额
     * @version v1
     */
    @GetMapping("v1/payStock/{id}")
    public Result payStock(@PathVariable String id) throws ActException {
        try {
            BeginBalanceBO beginBalanceBO = beginBalanceAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(beginBalanceBO, BeginBalanceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 期初余额列表
     *
     * @param beginBalanceDTO 期初余额dto
     * @return class BeginBalanceVO
     * @des 获取所有期初余额
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BeginBalanceDTO beginBalanceDTO, HttpServletRequest request) throws ActException {
        try {
            List<BeginBalanceVO> beginBalanceVOS = BeanTransform.copyProperties(
                    beginBalanceAPI.findList(beginBalanceDTO), BeginBalanceVO.class, request);
            return ActResult.initialize(beginBalanceVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加期初余额
     *
     * @param beginBalanceTO 期初余额to
     * @return class BeginBalanceVO
     * @des 添加期初余额
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BeginBalanceTO beginBalanceTO, BindingResult bindingResult) throws ActException {
        try {
            BeginBalanceBO beginBalanceBO = beginBalanceAPI.insert(beginBalanceTO);
            return ActResult.initialize(BeanTransform.copyProperties(beginBalanceBO, BeginBalanceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑期初余额
     *
     * @param beginBalanceTO 期初余额数据to
     * @return class BeginBalanceVO
     * @des 编辑期初余额
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BeginBalanceTO beginBalanceTO, BindingResult bindingResult) throws ActException {
        try {
            BeginBalanceBO beginBalanceBO = beginBalanceAPI.edit(beginBalanceTO);
            return ActResult.initialize(BeanTransform.copyProperties(beginBalanceBO, BeginBalanceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除期初余额
     *
     * @param id 用户id
     * @des 根据用户id删除期初余额记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            beginBalanceAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 期初余额
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @des 根据时间获取期初余额
     * @version v1
     */
    @GetMapping("v1/getBeginBalace")
    public Result getBeginBalace(String startTime, String endTime) throws ActException {
        try {
            List<BeginBalanceVO> beginBalanceVOS = BeanTransform.copyProperties(beginBalanceAPI.getBeginBalace(startTime, endTime), BeginBalanceVO.class);
            return ActResult.initialize(beginBalanceVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}