package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.TemplateManageAPI;
import com.bjike.goddess.recruit.bo.RecruitWayBO;
import com.bjike.goddess.recruit.bo.TemplateManageBO;
import com.bjike.goddess.recruit.dto.RecruitWayDTO;
import com.bjike.goddess.recruit.dto.TemplateManageDTO;
import com.bjike.goddess.recruit.to.RecruitWayTO;
import com.bjike.goddess.recruit.to.TemplateManageTO;
import com.bjike.goddess.recruit.vo.RecruitWayVO;
import com.bjike.goddess.recruit.vo.TemplateManageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 模板管理
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 17:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("recruit/templateManage")
public class TemplateManageAct {

    @Autowired
    private TemplateManageAPI templateManageAPI;

    /**
     * 获取列表
     *
     * @param dto 模板管理传输对象
     * @return class TemplateManageVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(TemplateManageDTO dto) throws ActException {
        try {
            List<TemplateManageBO> boList = templateManageAPI.list(dto);
            List<TemplateManageVO> voList = BeanTransform.copyProperties(boList, TemplateManageVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加模板管理
     *
     * @param to 模板管理to信息
     * @return class TemplateManageVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) TemplateManageTO to) throws ActException {
        try {
            TemplateManageBO bo = templateManageAPI.save(to);
            TemplateManageVO vo = BeanTransform.copyProperties(bo, TemplateManageVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除模板管理
     *
     * @param id 模板管理唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            templateManageAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 模板管理
     *
     * @param to 模板管理to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) TemplateManageTO to) throws ActException {
        try {
            templateManageAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
