package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.RecommendContentBO;
import com.bjike.goddess.interiorrecommend.bo.RecommendInfoBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendInfoDTO;
import com.bjike.goddess.interiorrecommend.service.RecommendInfoSer;
import com.bjike.goddess.interiorrecommend.to.RecommendInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 推荐信息业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:54 ]
 * @Description: [ 推荐信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("recommendInfoApiImpl")
public class RecommendInfoApiImpl implements RecommendInfoAPI {

    @Autowired
    private RecommendInfoSer recommendInfoSer;

    @Override
    public RecommendInfoBO addModel(RecommendInfoTO to) throws SerException {
        return recommendInfoSer.insertModel(to);
    }

    @Override
    public RecommendInfoBO editModel(RecommendInfoTO to) throws SerException {
        return recommendInfoSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        recommendInfoSer.delete(id);
    }

    @Override
    public List<RecommendInfoBO> pageList(RecommendInfoDTO dto) throws SerException {
        return recommendInfoSer.pageList(dto);
    }

    @Override
    public void acceptAudit(String id, String reason, Boolean accept) throws SerException {
        recommendInfoSer.acceptAudit(id,reason,accept);
    }

    @Override
    public void conformAudit(String id, Boolean conform) throws SerException {
        recommendInfoSer.conformAudit(id,conform);
    }
}