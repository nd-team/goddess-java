package com.bjike.goddess.annual.action.annual;

import com.bjike.goddess.annual.api.AnnualStandardAPI;
import com.bjike.goddess.annual.dto.AnnualStandardDTO;
import com.bjike.goddess.annual.excel.SonPermissionObject;
import com.bjike.goddess.annual.to.AnnualStandardTO;
import com.bjike.goddess.annual.to.GuidePermissionTO;
import com.bjike.goddess.annual.vo.AnnualStandardVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 年假标准
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:26 ]
 * @Description: [ 年假标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("annualstandard")
public class AnnualStandardAct {

    @Autowired
    private AnnualStandardAPI annualStandardAPI;

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

            List<SonPermissionObject> hasPermissionList = annualStandardAPI.sonPermission();
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

            Boolean isHasPermission = annualStandardAPI.guidePermission(guidePermissionTO);
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
     * 保存年假标准实体数据
     *
     * @param to 年假标准传输对象
     * @return class AnnualStandardVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) AnnualStandardTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualStandardAPI.save(to), AnnualStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改年假标准实体数据
     *
     * @param to 年假标准传输对象
     * @return class AnnualStandardVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) AnnualStandardTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualStandardAPI.update(to), AnnualStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除年假表尊实体数据
     *
     * @param to 年假标准传输对象
     * @return class AnnualStandardVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(AnnualStandardTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualStandardAPI.delete(to), AnnualStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结年假标准实体数据
     *
     * @param to 年假标准传输对象
     * @return class AnnualStandardVO
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(AnnualStandardTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualStandardAPI.congeal(to), AnnualStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻年假标准实体数据
     *
     * @param to 年假标准传输对象
     * @return class AnnualStandardVO
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(AnnualStandardTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualStandardAPI.thaw(to), AnnualStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常状态的年假标准
     *
     * @return class AnnualStandardVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualStandardAPI.findThaw(), AnnualStandardVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据工龄获取年假标准
     *
     * @param seniority 工龄
     * @return class AnnualStandardVO
     * @version v1
     */
    @GetMapping("v1/findBySeniority")
    public Result findBySeniority(Integer seniority) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualStandardAPI.findBySeniority(seniority), AnnualStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 查询列表
     *
     * @param dto 年假标准数据传输对象
     * @return class AnnualStandardVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(AnnualStandardDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualStandardAPI.maps(dto), AnnualStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id获取年假标准数据
     *
     * @param id 年假标准数据id
     * @return class AnnualStandardVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualStandardAPI.getById(id), AnnualStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(annualStandardAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}