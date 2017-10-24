package com.bjike.goddess.task.action.task;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.api.RecordAPI;
import com.bjike.goddess.task.bo.RecordBO;
import com.bjike.goddess.task.dto.RecordDTO;
import com.bjike.goddess.task.vo.RecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 内部协助单记录
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 11:30 ]
 * @Description: [ 内部协助单记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("record")
public class RecordAction {
    @Autowired
    private RecordAPI recordAPI;

    /**
     * 列表
     *
     * @param dto 内部协助单记录数据传输
     * @return class RecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RecordDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<RecordBO> list = recordAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, RecordVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 内部协助单记录数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(RecordDTO dto) throws ActException {
        try {
            return ActResult.initialize(recordAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}