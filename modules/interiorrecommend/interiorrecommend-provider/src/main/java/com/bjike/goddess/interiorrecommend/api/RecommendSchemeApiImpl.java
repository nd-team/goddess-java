package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.bo.RecommendSchemeBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendSchemeDTO;
import com.bjike.goddess.interiorrecommend.service.RecommendSchemeSer;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.RecommendSchemeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 推荐方案业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 10:31 ]
 * @Description: [ 推荐方案业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("recommendSchemeApiImpl")
public class RecommendSchemeApiImpl implements RecommendSchemeAPI {

    @Autowired
    private RecommendSchemeSer recommendSchemeSer;

    @Override
    public RecommendSchemeBO addModel(RecommendSchemeTO to) throws SerException {
        return recommendSchemeSer.insertModel(to);
    }

    @Override
    public RecommendSchemeBO editModel(RecommendSchemeTO to) throws SerException {
        return recommendSchemeSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        recommendSchemeSer.delete(id);
    }

    @Override
    public List<RecommendSchemeBO> pageList(RecommendSchemeDTO dto) throws SerException {
        return recommendSchemeSer.pageList(dto);
    }

//    @Override
//    public void resourcesAudit(String id, String resourcesSuggest, Boolean resourcesAudit) throws SerException {
//        recommendSchemeSer.resourcesAudit(id, resourcesSuggest, resourcesAudit);
//    }
//
//    @Override
//    public void operateAudit(String id, String operateSuggest, Boolean operateAudit) throws SerException {
//        recommendSchemeSer.operateAudit(id, operateSuggest, operateAudit);
//    }
//
//    @Override
//    public void generalAudit(String id, String generalSuggest, Boolean generalAudit) throws SerException {
//        recommendSchemeSer.generalAudit(id, generalSuggest, generalAudit);
//    }

    @Override
    public RecommendSchemeBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(recommendSchemeSer.findById(id), RecommendSchemeBO.class);
    }

    @Override
    public Long count(RecommendSchemeDTO dto) throws SerException {
        return recommendSchemeSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return recommendSchemeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return recommendSchemeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Set<String> findPosition() throws SerException {
        return recommendSchemeSer.findPosition();
    }
}