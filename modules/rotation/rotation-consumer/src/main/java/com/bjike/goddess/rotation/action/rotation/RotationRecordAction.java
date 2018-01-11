package com.bjike.goddess.rotation.action.rotation;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rotation.api.RotationRecordAPI;
import com.bjike.goddess.rotation.bo.RotationRecordBO;
import com.bjike.goddess.rotation.dto.RotationRecordDTO;
import com.bjike.goddess.rotation.vo.RotationRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 岗位轮换记录
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2018-01-08 09:29 ]
 * @Description: [ 岗位轮换记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("rotationrecord")
public class RotationRecordAction {

    @Autowired
    RotationRecordAPI rotationRecordAPI;

    /**
     * 获取岗位轮换记录列表
     *
     * @param dto dto
     * @return class
     * @version v1
     */
    @LoginAuth
    @GetMapping("/v1/list")
    public ActResult list(RotationRecordDTO dto) throws ActException {
        try {
            List<RotationRecordVO> vos = BeanTransform.copyProperties(rotationRecordAPI.list(dto), RotationRecordVO.class);

            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}