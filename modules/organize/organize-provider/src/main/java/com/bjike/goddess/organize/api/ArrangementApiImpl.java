package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.ArrangementBO;
import com.bjike.goddess.organize.service.ArrangementSer;
import com.bjike.goddess.organize.to.ArrangementTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位层级业务实现
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("arrangementApiImpl")
public class ArrangementApiImpl implements ArrangementAPI {

    @Autowired
    private ArrangementSer arrangementSer;

    @Override
    public List<ArrangementBO> findStatus() throws SerException {
        return arrangementSer.findStatus();
    }

    @Override
    public ArrangementBO save(ArrangementTO to) throws SerException {
        return arrangementSer.save(to);
    }

    @Override
    public ArrangementBO update(ArrangementTO to) throws SerException {
        return arrangementSer.update(to);
    }

    @Override
    public ArrangementBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(arrangementSer.findById(id), ArrangementBO.class);
    }

    @Override
    public List<ArrangementBO> findChild(String id) throws SerException {
        return arrangementSer.findChild(id);
    }
}
