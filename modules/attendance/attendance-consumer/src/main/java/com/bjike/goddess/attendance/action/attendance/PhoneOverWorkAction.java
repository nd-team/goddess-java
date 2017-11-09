package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.overtime.OverWorkAPI;
import com.bjike.goddess.attendance.dto.overtime.PhoneMyEntryOverWorkDTO;
import com.bjike.goddess.attendance.dto.overtime.PhoneMyOverWorkDTO;
import com.bjike.goddess.attendance.vo.OverWorkVO;
import com.bjike.goddess.attendance.vo.overtime.PhoneOverWorkVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 手机版加班
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("phoneoverwork")
public class PhoneOverWorkAction {

    @Autowired
    private OverWorkAPI overWorkAPI;


    /**
     * 一个加班
     *
     * @param id 加班id
     * @return class PhoneOverWorkVO
     * @des 根据id获取加班
     * @version v1
     */
    @GetMapping("v1/getPhoneOneById/{id}")
    public Result getPhoneOneById(@PathVariable String id) throws ActException {
        try {
            PhoneOverWorkVO overWorkVO = BeanTransform.copyProperties(
                    overWorkAPI.getPhoneOneById(id), OverWorkVO.class);
            return ActResult.initialize(overWorkVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我的加班列表
     *
     * @param phoneMyOverWorkDTO 加班信息dto
     * @return class PhoneOverWorkVO
     * @des 我的加班列表
     * @version v1
     */
    @GetMapping("v1/my/list")
    public Result findListOverWork(@Validated(PhoneMyOverWorkDTO.TESTLIST.class) PhoneMyOverWorkDTO phoneMyOverWorkDTO, BindingResult bindingResult) throws ActException {
        try {
            List<PhoneOverWorkVO> overWorkVOList = BeanTransform.copyProperties(
                    overWorkAPI.myListOverWork(phoneMyOverWorkDTO), PhoneOverWorkVO.class, true);
            return ActResult.initialize(overWorkVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我录入的加班列表
     *
     * @param phoneMyEntryOverWorkDTO 加班信息dto
     * @return class PhoneOverWorkVO
     * @des 仅项目经理和负责人和本人录入的才可以看
     * @version v1
     */
    @GetMapping("v1/my/entry/list")
    public Result myEntryList(@Validated(PhoneMyEntryOverWorkDTO.TESTLIST.class) PhoneMyEntryOverWorkDTO phoneMyEntryOverWorkDTO, BindingResult bindingResult) throws ActException {
        try {
            List<PhoneOverWorkVO> overWorkVOList = BeanTransform.copyProperties(
                    overWorkAPI.myEntryList(phoneMyEntryOverWorkDTO), PhoneOverWorkVO.class, true);
            return ActResult.initialize(overWorkVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 待我审核的加班列表
     *
     * @param phoneMyEntryOverWorkDTO 加班信息dto
     * @return class PhoneOverWorkVO
     * @des 仅项目经理和审核人才可以看
     * @version v1
     */
    @GetMapping("v1/my/audit/list")
    public Result myAuditList(@Validated(PhoneMyEntryOverWorkDTO.TESTLIST.class) PhoneMyEntryOverWorkDTO phoneMyEntryOverWorkDTO, BindingResult bindingResult) throws ActException {
        try {
            List<PhoneOverWorkVO> overWorkVOList = BeanTransform.copyProperties(
                    overWorkAPI.myAuditList(phoneMyEntryOverWorkDTO), PhoneOverWorkVO.class, true);
            return ActResult.initialize(overWorkVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}