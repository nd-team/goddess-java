package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.vo.CommonalityVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.EmailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.dto.EmailDTO;
import com.bjike.goddess.organize.to.EmailTO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.EmailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 邮件发送
 *
 * @Author: [chenjunhao]
 * @Date: [17-3-8 上午9:54]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("email")
public class EmailAct {
    @Autowired
    private EmailAPI emailAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;

    /**
     * 添加
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) EmailTO to, BindingResult result) throws ActException {
        try {
            emailAPI.add(to);
            return new ActResult("添加成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) EmailTO to, BindingResult result) throws ActException {
        try {
            emailAPI.edit(to);
            return new ActResult("添加成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            emailAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto dto
     * @return class EmailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(EmailDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(emailAPI.list(dto), EmailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id id
     * @return class EmailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/one/{id}")
    public Result one(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(emailAPI.one(id), EmailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总条数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result one(EmailDTO dto) throws ActException {
        try {
            return ActResult.initialize(emailAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有部门
     *
     * @return class DepartmentDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/depart")
    public Result depart(HttpServletRequest request) throws ActException {
        try {
            List<DepartmentDetailBO> list = departmentDetailAPI.findStatus();
            return ActResult.initialize(BeanTransform.copyProperties(list, DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据姓名数组获取邮箱
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/emails")
    public Result emails(@Validated(EmailDTO.EMAIL.class) EmailDTO dto, BindingResult result) throws ActException {
        try {
            String[] names = dto.getNames();
            List<String> list = internalContactsAPI.getEmails(names);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有公邮
     *
     * @return class CommonalityVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw(HttpServletRequest request) throws ActException {
        try {
            List<CommonalityBO> list = commonalityAPI.findThaw();
            return ActResult.initialize(BeanTransform.copyProperties(list, CommonalityVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
