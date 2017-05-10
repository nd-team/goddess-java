package com.bjike.goddess.businessproject.action.businessproject;

import com.bjike.goddess.businessproject.api.SiginManageAPI;
import com.bjike.goddess.businessproject.bo.SiginManageBO;
import com.bjike.goddess.businessproject.dto.SiginManageDTO;
import com.bjike.goddess.businessproject.to.SiginManageTO;
import com.bjike.goddess.businessproject.vo.SiginManageVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 商务项目合同签订与立项管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T19:37:28.298 ]
 * @Description: [ 商务项目合同签订与立项管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("siginmanage")
public class SiginManageAction {

    @Autowired
    private SiginManageAPI siginManageAPI;

    /**
     * 列表总条数
     *
     * @param siginManageDTO 签订与立项dto
     * @des 获取所有签订与立项总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SiginManageDTO siginManageDTO) throws ActException {
        try {
            Long count = siginManageAPI.countSiginManage(siginManageDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个签订与立项
     *
     * @param id 项目签订与立项id
     * @des 根据id获取项目签订与立项
     * @return  class SiginManageVO
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            SiginManageVO projectCarryVO = BeanTransform.copyProperties(
                    siginManageAPI.getOneById(id), SiginManageVO.class);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目签订与立项列表
     *
     * @param siginManageDTO 项目签订与立项信息dto
     * @return class SiginManageVO
     * @des 获取所有项目签订与立项信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListSiginManage(SiginManageDTO siginManageDTO,BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<SiginManageBO> list = siginManageAPI.listSiginManage(siginManageDTO);
            List<SiginManageVO> siginManageVOList =new ArrayList<>();
            list.stream().forEach(str->{
                SiginManageVO vo = BeanTransform.copyProperties(str, SiginManageVO.class,"businessType","businessCooperate","contractProperty");
                vo.setBusinessType( str.getBusinessType());
                vo.setBusinessCooperate(str.getBusinessCooperate());
                vo.setContractProperty( str.getContractProperty());
                siginManageVOList.add( vo );
            });

            return ActResult.initialize(siginManageVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目签订与立项
     *
     * @param siginManageTO 项目签订与立项基本信息数据to
     * @return class SiginManageVO
     * @des 添加项目签订与立项
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addSiginManage(@Validated(SiginManageTO.TestAdd.class) SiginManageTO siginManageTO, BindingResult bindingResult) throws ActException {
        try {
            SiginManageBO siginManageBO1 = siginManageAPI.addSiginManage(siginManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(siginManageBO1, SiginManageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目签订与立项
     *
     * @param siginManageTO 项目签订与立项基本信息数据bo
     * @return class SiginManageVO
     * @des 添加项目签订与立项
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editSiginManage(@Validated(SiginManageTO.TestAdd.class) SiginManageTO siginManageTO , BindingResult bindingResult) throws ActException {
        try {
            SiginManageBO siginManageBO1 = siginManageAPI.editSiginManage(siginManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(siginManageBO1, SiginManageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目签订与立项信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteSiginManage(@PathVariable String id) throws ActException {
        try {
            siginManageAPI.deleteSiginManage(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 审核
     *
     * @param siginManageTO 项目签订与立项基本信息数据bo
     * @return class SiginManageVO
     * @des 审核项目签订与立项
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/audit")
    public Result auditSiginManage( SiginManageTO siginManageTO , BindingResult bindingResult) throws ActException {
        try {
            SiginManageBO siginManageBO1 = siginManageAPI.addSiginManage(siginManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(siginManageBO1, SiginManageVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}