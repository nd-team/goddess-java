package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.*;
import com.bjike.goddess.interiorrecommend.dto.OldRecommendRequireDTO;
import com.bjike.goddess.interiorrecommend.service.OldRecommendRequireSer;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.OldRecommendRequireTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 推荐要求业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 03:29 ]
 * @Description: [ 推荐要求业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("recommendRequireApiImpl")
public class OldRecommendRequireApiImpl implements OldRecommendRequireAPI {

    @Autowired
    private OldRecommendRequireSer recommendRequireSer;

    @Override
    public OldRecommendRequireBO addModel(OldRecommendRequireTO to) throws SerException {
        return recommendRequireSer.insertModel(to);
    }

    @Override
    public OldRecommendRequireBO editModel(OldRecommendRequireTO to) throws SerException {
        return recommendRequireSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        recommendRequireSer.delete(id);
    }

    @Override
    public List<OldRecommendRequireBO> pageList(OldRecommendRequireDTO dto) throws SerException {
        return recommendRequireSer.pageList(dto);
    }

    @Override
    public Long count(OldRecommendRequireDTO dto) throws SerException {
        return recommendRequireSer.count(dto);
    }


    @Override
    public Boolean sonPermission() throws SerException {
        return recommendRequireSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return recommendRequireSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<RecommendSchemeBO> findRecommend() throws SerException {
        return recommendRequireSer.findRecommend();
    }

    @Override
    public List<OldRecommendTypeBO> findType() throws SerException {
        return recommendRequireSer.findType();
    }

    @Override
    public List<OldRecommendAssessDetailBO> findAssess() throws SerException {
        return recommendRequireSer.findAssess();
    }

    @Override
    public List<OldRecommendContentBO> findContent() throws SerException {
        return recommendRequireSer.findContent();
    }


    @Override
    public OldRecommendRequireBO findOne(String id) throws SerException {
        return recommendRequireSer.findOne(id);
    }
}