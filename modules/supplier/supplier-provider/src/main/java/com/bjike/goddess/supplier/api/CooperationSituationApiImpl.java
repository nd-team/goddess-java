package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.CooperationSituationBO;
import com.bjike.goddess.supplier.service.CooperationSituationSer;
import com.bjike.goddess.supplier.to.ContactSituationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合作情况业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:05:37.662 ]
 * @Description: [ 合作情况业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("cooperationSituationApiImpl")
public class CooperationSituationApiImpl implements CooperationSituationAPI {

    @Autowired
    private CooperationSituationSer cooperationSituationSer;

    @Override
    public List<CooperationSituationBO> findByInformation(String info_id) throws SerException {
        return cooperationSituationSer.findByInformation(info_id);
    }

    @Override
    public CooperationSituationBO save(ContactSituationTO to) throws SerException {
        return cooperationSituationSer.save(to);
    }

    @Override
    public CooperationSituationBO update(ContactSituationTO to) throws SerException {
        return cooperationSituationSer.update(to);
    }

    @Override
    public CooperationSituationBO delete(String id) throws SerException {
        return cooperationSituationSer.delete(id);
    }
}