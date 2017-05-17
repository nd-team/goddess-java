package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.RecommendRotationBO;
import com.bjike.goddess.rotation.dto.RecommendRotationDTO;
import com.bjike.goddess.rotation.service.RecommendRotationSer;
import com.bjike.goddess.rotation.to.RecommendRotationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位轮换推荐业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:28 ]
 * @Description: [ 岗位轮换推荐业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("recommendRotationApiImpl")
public class RecommendRotationApiImpl implements RecommendRotationAPI {

    @Autowired
    private RecommendRotationSer recommendRotationSer;

    @Override
    public RecommendRotationBO save(RecommendRotationTO to) throws SerException {
        return recommendRotationSer.save(to);
    }

    @Override
    public RecommendRotationBO update(RecommendRotationTO to) throws SerException {
        return recommendRotationSer.update(to);
    }

    @Override
    public RecommendRotationBO delete(String id) throws SerException {
        return recommendRotationSer.delete(id);
    }

    @Override
    public RecommendRotationBO opinion(RecommendRotationTO to) throws SerException {
        return recommendRotationSer.opinion(to);
    }

    @Override
    public RecommendRotationBO getById(String id) throws SerException {
        return recommendRotationSer.getById(id);
    }

    @Override
    public List<RecommendRotationBO> maps(RecommendRotationDTO dto) throws SerException {
        return recommendRotationSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return recommendRotationSer.getTotal();
    }
}