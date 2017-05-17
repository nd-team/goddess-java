package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.CoverRotationOpinionBO;
import com.bjike.goddess.rotation.dto.CoverRotationOpinionDTO;
import com.bjike.goddess.rotation.service.CoverRotationOpinionSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位轮换自荐意见业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 04:04 ]
 * @Description: [ 岗位轮换自荐意见业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("coverRotationOpinionApiImpl")
public class CoverRotationOpinionApiImpl implements CoverRotationOpinionAPI {

    @Autowired
    private CoverRotationOpinionSer coverRotationOpinionSer;

    @Override
    public List<CoverRotationOpinionBO> findByCover(String id, CoverRotationOpinionDTO dto) throws SerException {
        return coverRotationOpinionSer.findByCover(id, dto);
    }

    @Override
    public Long getTotal(String id) throws SerException {
        return coverRotationOpinionSer.getTotal(id);
    }
}