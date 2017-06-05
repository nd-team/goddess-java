package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.ReportAddressInforAPI;
import com.bjike.goddess.recruit.bo.RecruitWayBO;
import com.bjike.goddess.recruit.bo.ReportAddressInforBO;
import com.bjike.goddess.recruit.dto.RecruitWayDTO;
import com.bjike.goddess.recruit.dto.ReportAddressInforDTO;
import com.bjike.goddess.recruit.to.RecruitWayTO;
import com.bjike.goddess.recruit.to.ReportAddressInforTO;
import com.bjike.goddess.recruit.vo.RecruitWayVO;
import com.bjike.goddess.recruit.vo.ReportAddressInforVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 报道地址信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 17:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("recruit/reportAddressInfor")
public class ReportAddressInforAct {

    @Autowired
    private ReportAddressInforAPI reportAddressInforAPI;

    /**
     * 获取列表
     *
     * @param dto 报道地址信息
     * @return class ReportAddressInforVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ReportAddressInforDTO dto) throws ActException {
        try {
            List<ReportAddressInforBO> boList = reportAddressInforAPI.list(dto);
            List<ReportAddressInforVO> voList = BeanTransform.copyProperties(boList, ReportAddressInforVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加报道地址信息
     *
     * @param to 报道地址信息to信息
     * @return class ReportAddressInforVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ReportAddressInforTO to) throws ActException {
        try {
            ReportAddressInforBO bo = reportAddressInforAPI.save(to);
            ReportAddressInforVO vo = BeanTransform.copyProperties(bo, ReportAddressInforVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除报道地址信息
     *
     * @param id 报道地址信息唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            reportAddressInforAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑报道地址信息
     *
     * @param to 报道地址信息to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ReportAddressInforTO to) throws ActException {
        try {
            reportAddressInforAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
