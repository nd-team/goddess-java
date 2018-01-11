package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.RotationRecordBO;
import com.bjike.goddess.rotation.dto.RotationRecordDTO;
import com.bjike.goddess.rotation.service.RotationRecordSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位轮换记录业务接口实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2018-01-08 09:29 ]
 * @Description: [ 岗位轮换记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("rotationRecordApiImpl")
public class RotationRecordApiImpl implements RotationRecordAPI {

    @Autowired
    RotationRecordSer rotationRecordSer;

    @Override
    public List<RotationRecordBO> list(RotationRecordDTO dto) throws SerException {
        return rotationRecordSer.list(dto);
    }
}