package com.bjike.goddess.task.api;


import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.dto.FieldDTO;
import com.bjike.goddess.task.entity.Field;
import com.bjike.goddess.task.service.FieldSer;
import com.bjike.goddess.task.to.FieldTO;
import com.bjike.goddess.task.vo.FieldVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 列业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("fieldApiImpl")
public class FieldApiImpl implements FieldAPI {
    @Autowired
    private FieldSer fieldSer;
    @Override
    public void add(FieldTO to) throws SerException {
        fieldSer.add(to);
    }



    @Override
    public List<FieldVO> list(String tableId,String node ) throws SerException {
        List<Field> fields = fieldSer.list(tableId,node);
        return BeanTransform.copyProperties(fields,FieldVO.class);
    }
}
