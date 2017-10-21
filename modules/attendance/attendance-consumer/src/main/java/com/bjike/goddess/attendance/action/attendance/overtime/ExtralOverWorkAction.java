package com.bjike.goddess.attendance.action.attendance.overtime;

import com.bjike.goddess.attendance.api.overtime.ExtralOverWorkAPI;
import com.bjike.goddess.attendance.bo.overtime.ExtralOverWorkBO;
import com.bjike.goddess.attendance.dto.overtime.ExtralOverWorkDTO;
import com.bjike.goddess.attendance.dto.overtime.ExtralOverWorkDayDTO;
import com.bjike.goddess.attendance.to.overtime.ExtralOverWorkTO;
import com.bjike.goddess.attendance.vo.overtime.ExtralOverWorkDayVO;
import com.bjike.goddess.attendance.vo.overtime.ExtralOverWorkVO;
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

import java.util.List;

/**
 * 补班设置（针对全体员工）
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-12 04:42 ]
 * @Description: [ 补班设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("extraloverwork")
public class ExtralOverWorkAction {

    @Autowired
    private ExtralOverWorkAPI extralOverWorkAPI;

    /**
     * 列表总条数
     *
     * @param overWorkDTO 补班信息dto
     * @des 获取所有补班信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ExtralOverWorkDTO overWorkDTO) throws ActException {
        try {
            Long count = extralOverWorkAPI.countExtralOverWork(overWorkDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个补班
     *
     * @param id 补班id
     * @return class ExtralOverWorkVO
     * @des 根据id获取补班
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ExtralOverWorkVO overWorkVO = BeanTransform.copyProperties(
                    extralOverWorkAPI.getOneById(id), ExtralOverWorkVO.class);
            return ActResult.initialize(overWorkVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param overWorkDTO 补班信息dto
     * @return class ExtralOverWorkVO
     * @des 获取所有补班信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListExtralOverWork(ExtralOverWorkDTO overWorkDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ExtralOverWorkVO> overWorkVOList = BeanTransform.copyProperties(
                    extralOverWorkAPI.listExtralOverWork(overWorkDTO), ExtralOverWorkVO.class, true);
            return ActResult.initialize(overWorkVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添补
     *
     * @param overWorkTO 补班基本信息数据to
     * @return class ExtralOverWorkVO
     * @des 添补补班
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addExtralOverWork(@Validated(ExtralOverWorkTO.TESTAddAndEdit.class) ExtralOverWorkTO overWorkTO, BindingResult bindingResult) throws ActException {
        try {
            ExtralOverWorkBO overWorkBO1 = extralOverWorkAPI.addExtralOverWork(overWorkTO);
            return ActResult.initialize(BeanTransform.copyProperties(overWorkBO1, ExtralOverWorkVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑
     *
     * @param overWorkTO 补班基本信息数据to
     * @return class ExtralOverWorkVO
     * @des 编辑补班
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editExtralOverWork(@Validated(ExtralOverWorkTO.TESTAddAndEdit.class) ExtralOverWorkTO overWorkTO, BindingResult bindingResult) throws ActException {
        try {
            ExtralOverWorkBO overWorkBO1 = extralOverWorkAPI.editExtralOverWork(overWorkTO);
            return ActResult.initialize(BeanTransform.copyProperties(overWorkBO1, ExtralOverWorkVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除补班信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteExtralOverWork(@PathVariable String id) throws ActException {
        try {
            extralOverWorkAPI.deleteExtralOverWork(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }


    /**
     * 获取补班天数
     *
     * @param extralOverWorkDayDTO 补班天数
     * @des 获取补班天数
     * @version v1
     * @return class ExtralOverWorkDayVO
     */
    @GetMapping("v1/caculateTime")
    public Result caculateTime(@Validated(ExtralOverWorkDayDTO.TestAdd.class) ExtralOverWorkDayDTO extralOverWorkDayDTO ) throws ActException {
        try {
            ExtralOverWorkDayVO list = extralOverWorkAPI.caculateTime( extralOverWorkDayDTO );
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



}