package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.RecordBO;
import com.bjike.goddess.task.dto.RecordDTO;
import com.bjike.goddess.task.service.RecordSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内部协助单记录业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 11:30 ]
 * @Description: [ 内部协助单记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("recordApiImpl")
public class RecordApiImpl implements RecordAPI {
    @Autowired
    private RecordSer recordSer;

    @Override
    public List<RecordBO> list(RecordDTO dto) throws SerException {
        return recordSer.list(dto);
    }

    @Override
    public Long count(RecordDTO dto) throws SerException {
        return recordSer.count(dto);
    }
}