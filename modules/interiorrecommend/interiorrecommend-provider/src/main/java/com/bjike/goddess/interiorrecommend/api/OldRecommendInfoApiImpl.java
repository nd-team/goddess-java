package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.OldRecommendInfoBO;
import com.bjike.goddess.interiorrecommend.dto.OldRecommendInfoDTO;
import com.bjike.goddess.interiorrecommend.service.OldRecommendInfoSer;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
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
public class OldRecommendInfoApiImpl implements OldRecommendInfoAPI {

    @Autowired
    private OldRecommendInfoSer recommendInfoSer;

    @Override
    public OldRecommendInfoBO addModel(RecommendInfoTO to) throws SerException {
        return recommendInfoSer.insertModel(to);
    }

    @Override
    public OldRecommendInfoBO editModel(RecommendInfoTO to) throws SerException {
        return recommendInfoSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        recommendInfoSer.delete(id);
    }

    @Override
    public List<OldRecommendInfoBO> pageList(OldRecommendInfoDTO dto) throws SerException {
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

    @Override
    public List<OldRecommendInfoBO> awardlist() throws SerException {
        return recommendInfoSer.awardlist();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return recommendInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return recommendInfoSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<String[]> findRequire() throws SerException {
        return recommendInfoSer.findRequire();
    }

    @Override
    public OldRecommendInfoBO findOne(String id) throws SerException {
        return recommendInfoSer.findOne(id);
    }

    @Override
    public Long count(OldRecommendInfoDTO dto) throws SerException {
        return recommendInfoSer.count(dto);
    }
}