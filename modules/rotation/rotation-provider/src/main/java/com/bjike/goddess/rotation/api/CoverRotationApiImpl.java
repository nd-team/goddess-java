package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.CoverRotationBO;
import com.bjike.goddess.rotation.bo.CoverRotationOpinionBO;
import com.bjike.goddess.rotation.dto.CoverRotationDTO;
import com.bjike.goddess.rotation.service.CoverRotationSer;
import com.bjike.goddess.rotation.to.CoverRotationOpinionTO;
import com.bjike.goddess.rotation.to.CoverRotationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位轮换自荐业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:18 ]
 * @Description: [ 岗位轮换自荐业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("coverRotationApiImpl")
public class CoverRotationApiImpl implements CoverRotationAPI {

    @Autowired
    private CoverRotationSer coverRotationSer;

    @Override
    public CoverRotationBO save(CoverRotationTO to) throws SerException {
        return coverRotationSer.save(to);
    }

    @Override
    public CoverRotationBO update(CoverRotationTO to) throws SerException {
        return coverRotationSer.update(to);
    }

    @Override
    public CoverRotationBO delete(String id) throws SerException {
        return coverRotationSer.delete(id);
    }

    @Override
    public CoverRotationBO getById(String id) throws SerException {
        return coverRotationSer.getById(id);
    }

    @Override
    public CoverRotationOpinionBO opinion(CoverRotationOpinionTO to) throws SerException {
        return coverRotationSer.opinion(to);
    }

    @Override
    public CoverRotationBO generalOpinion(CoverRotationTO to) throws SerException {
        return coverRotationSer.generalOpinion(to);
    }

    @Override
    public List<CoverRotationBO> maps(CoverRotationDTO dto) throws SerException {
        return coverRotationSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return coverRotationSer.getTotal();
    }
}