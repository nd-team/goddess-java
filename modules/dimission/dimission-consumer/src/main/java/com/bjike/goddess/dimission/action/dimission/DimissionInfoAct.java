package com.bjike.goddess.dimission.action.dimission;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dimission.api.DimissionInfoAPI;
import com.bjike.goddess.dimission.dto.DimissionInfoDTO;
import com.bjike.goddess.dimission.enums.DimissionType;
import com.bjike.goddess.dimission.to.*;
import com.bjike.goddess.dimission.vo.DimissionInfoCollectVO;
import com.bjike.goddess.dimission.vo.DimissionInfoVO;
import com.bjike.goddess.dimission.vo.DimissionReasonVO;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 离职信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:12 ]
 * @Description: [ 离职信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("dimissioninfo")
public class DimissionInfoAct extends BaseFileAction {

    @Autowired
    private DimissionInfoAPI dimissionInfoAPI;
    @Autowired
    private FileAPI fileAPI;


    /**
     * 申请离职
     *
     * @param to 离职信息传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/apply")
    public Result apply(@Validated(ADD.class) DimissionInfoTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.apply(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 离职信息传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) DimissionInfoTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.update(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 自离信息添加
     *
     * @param to 离职信息传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @PostMapping("v1/presume")
    public Result presume(@Validated(ADD.class) DimissionInfoTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.presume(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 离职信息数据id
     * @return class DimissionInfoVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.delete(id), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存负责人面谈记录
     *
     * @param to 离职面谈信息传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @PutMapping("v1/authority/{id}")
    public Result authority(@Validated(ADD.class) DimissionInterviewTo to, BindingResult result) throws ActException {
        try {
            to.setAuthority(Boolean.TRUE);
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.interview(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存项目经理面谈记录
     *
     * @param to 离职面谈信息传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/interview/{id}")
    public Result interview(@Validated(EDIT.class) DimissionInterviewTo to, BindingResult result) throws ActException {
        try {
            to.setAuthority(Boolean.FALSE);
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.interview(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办审核
     *
     * @param to 离职审核传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @PutMapping("v1/audit/{id}")
    public Result audit(@Validated(ADD.class) DimissionAuditTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.audit(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 薪资确认
     *
     * @param to 离职薪资确认传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @PutMapping("v1/affirm/{id}")
    public Result affirm(@Validated(ADD.class) DimissionAffirmTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.affirm(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认离职
     *
     * @param id 离职信息数据id
     * @return class DimissionInfoVO
     * @version v1
     */
    @PutMapping("v1/success/{id}")
    public Result success(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.success(id), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改离职类型
     *
     * @param to 离职类型修改传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @PutMapping("v1/editType/{id}")
    public Result editType(@Validated(EDIT.class) DimissionTypeTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.editType(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据离职类型查询离职信息
     *
     * @param type 离职类型
     * @return class DimissionInfoVO
     * @version v1
     */
    @GetMapping("v1/findByType")
    public Result findByType(DimissionType type) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.findByType(type), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 自离信息列表
     *
     * @param dto 离职信息数据传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @GetMapping("v1/presumeList")
    public Result presumeList(DimissionInfoDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.presumeList(dto), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 离职信息列表
     *
     * @param dto 离职信息数据传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(DimissionInfoDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.maps(dto), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询指定离职时间内的离职信息
     *
     * @param start 查询开始时间
     * @param end   查询结束时间
     * @return class DimissionInfoVO
     * @version v1
     */
    @GetMapping("v1/findByDate")
    public Result findByDimissionDate(String start, String end) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.findByDimissionDate(start, end), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取全部离职信息
     *
     * @return class DimissionInfoVO
     * @version v1
     */
    @GetMapping("v1/all")
    public Result all() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.all(), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门离职汇总
     *
     * @param to 离职信息汇总传输对象
     * @return class DimissionInfoCollectVO
     * @version v1
     */
    @GetMapping("v1/departmentCollect")
    public Result departmentCollect(DimissionCollectTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.departmentCollect(to), DimissionInfoCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 岗位离职汇总
     *
     * @param to 离职信息汇总传输对象
     * @return class DimissionInfoCollectVO
     * @version v1
     */
    @GetMapping("v1/positionCollect")
    public Result positionCollect(DimissionCollectTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.positionCollect(to), DimissionInfoCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 入职时间汇总
     *
     * @param to 离职信息汇总传输对象
     * @return class DimissionInfoCollectVO
     * @version v1
     */
    @GetMapping("v1/entryCollect")
    public Result entryCollect(DimissionCollectTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.entryCollect(to), DimissionInfoCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 工龄汇总
     *
     * @param to 离职信息汇总传输对象
     * @return class DimissionInfoCollectVO
     * @version v1
     */
    @GetMapping("v1/seniorityCollect")
    public Result seniorityCollect(DimissionCollectTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.seniorityCollect(to), DimissionInfoCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 学历汇总
     *
     * @param to 离职信息汇总传输对象
     * @return class DimissionInfoCollectVO
     * @version v1
     */
    @GetMapping("v1/educationCollect")
    public Result educationCollect(DimissionCollectTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.educationCollect(to), DimissionInfoCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 原因汇总
     *
     * @param to 离职信息汇总传输对象
     * @return class DimissionReasonVO
     * @version v1
     */
    @GetMapping("v1/reasonCollect")
    public Result reasonCollect(DimissionCollectTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.reasonCollect(to), DimissionReasonVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param request  上传请求
     * @param username 员工姓名(创建对应文件夹使用)
     * @return class Result
     * @version v1
     */
    @PostMapping("v1/uploadEnclosure")
    public Result uploadEnclosure(HttpServletRequest request, String username) throws ActException {
        try {

            String path = "/" + username;

            fileAPI.upload(this.getInputStreams(request, path));
            return new ActResult("上传成功");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }


}