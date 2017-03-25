package com.bjike.goddess.projectmeasure.action.projectmeasure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.api.MultipleProjectSingleUIAPI;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectMultipleUITO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectSingleUITO;
import com.bjike.goddess.projectmeasure.vo.MultipleProjectMultipleUIVO;
import com.bjike.goddess.projectmeasure.vo.MultipleProjectSingleUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 多项目单个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectmeasure/multipleprojectsingleui")
public class MultipleProjectSingleUIAct {

    @Autowired
    private MultipleProjectSingleUIAPI multipleProjectSingleUIAPI;

    /**
     * 分页查询多项目单个界面
     *
     * @param dto 多项目单个界面传输对象
     * @return class MultipleProjectSingleUIVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MultipleProjectSingleUIDTO dto) throws ActException {
        try {
            List<MultipleProjectSingleUIBO> boList = multipleProjectSingleUIAPI.list(dto);
            List<MultipleProjectSingleUIVO> voList = BeanTransform.copyProperties(boList, MultipleProjectSingleUIVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加多项目单个界面
     *
     * @param to 多项目单个界面to信息
     * @return class MultipleProjectSingleUIVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) MultipleProjectSingleUITO to) throws ActException {
        try {
            MultipleProjectSingleUIBO bo = multipleProjectSingleUIAPI.save(to);
            MultipleProjectSingleUIVO vo = BeanTransform.copyProperties(bo, MultipleProjectSingleUIVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除多项目单个界面
     *
     * @param id 多项目单个界面唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            multipleProjectSingleUIAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑多项目单个界面
     *
     * @param to 多项目单个界面to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MultipleProjectSingleUITO to) throws ActException {
        try {
            multipleProjectSingleUIAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}