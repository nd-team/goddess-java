package com.bjike.goddess.receivable.action.receivable;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.receivable.api.MainIncomeAPI;
import com.bjike.goddess.receivable.bo.MainIncomeBO;
import com.bjike.goddess.receivable.dto.MainIncomeDTO;
import com.bjike.goddess.receivable.to.GuidePermissionTO;
import com.bjike.goddess.receivable.to.MainIncomeTO;
import com.bjike.goddess.receivable.vo.MainIncomeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 主营业务收入
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 10:19 ]
 * @Description: [ 主营业务收入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("mainincome")
public class MainIncomeAction {
    @Autowired
    private MainIncomeAPI mainIncomeAPI;
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

            Boolean isHasPermission = mainIncomeAPI.guidePermission(guidePermissionTO);
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
     * 主营业务收入列表总条数
     *
     * @param dto 主营业务收入dto
     * @des 获取所有主营业务收入总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MainIncomeDTO dto) throws ActException {
        try {
            Long count = mainIncomeAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个主营业务收入
     *
     * @param id
     * @return class MainIncomeVO
     * @des 获取一个主营业务收入
     * @version v1
     */
    @GetMapping("v1/main/{id}")
    public Result main(@PathVariable String id) throws ActException {
        try {
            MainIncomeBO bo = mainIncomeAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, MainIncomeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取主营业务收入
     *
     * @param dto 主营业务收入dto
     * @return class MainIncomeVO
     * @des 获取所有主营业务收入
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MainIncomeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<MainIncomeVO> vos = BeanTransform.copyProperties
                    (mainIncomeAPI.list(dto), MainIncomeVO.class, request);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加主营业务收入
     *
     * @param to 主营业务收入数据to
     * @return class MainIncomeVO
     * @des 添加主营业务收入
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(MainIncomeTO.TestAdd.class) MainIncomeTO to, BindingResult bindingResult) throws ActException {
        try {
            MainIncomeBO bo = mainIncomeAPI.add(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo,MainIncomeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑主营业务收入
     *
     * @param to 主营业务收入数据to
     * @return class MainIncomeVO
     * @des 编辑主营业务收入
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(MainIncomeTO.TestEdit.class) MainIncomeTO to, BindingResult bindingResult) throws ActException {
        try {
            MainIncomeBO bo = mainIncomeAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo,MainIncomeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除主营业务收入
     *
     * @param id 用户id
     * @des 根据用户id删除主营业务收入记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            mainIncomeAPI.remove(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}