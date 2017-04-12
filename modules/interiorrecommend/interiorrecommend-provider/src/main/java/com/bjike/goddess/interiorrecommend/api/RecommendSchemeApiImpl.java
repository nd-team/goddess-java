package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.RecommendSchemeBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendSchemeDTO;
import com.bjike.goddess.interiorrecommend.service.RecommendSchemeSer;
import com.bjike.goddess.interiorrecommend.to.RecommendSchemeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        recommendSchemeSer.remove(id);
    }

    @Override
    public List<RecommendSchemeBO> pageList(RecommendSchemeDTO dto) throws SerException {
        return recommendSchemeSer.pageList(dto);
    }

    @Override
    public void resourcesAudit(RecommendSchemeTO to) throws SerException {
        recommendSchemeSer.resourcesAudit(to);
    }

    @Override
    public void operateAudit(RecommendSchemeTO to) throws SerException {
        recommendSchemeSer.operateAudit(to);
    }

    @Override
    public void generalAudit(RecommendSchemeTO to) throws SerException {
        recommendSchemeSer.generalAudit(to);
    }
}