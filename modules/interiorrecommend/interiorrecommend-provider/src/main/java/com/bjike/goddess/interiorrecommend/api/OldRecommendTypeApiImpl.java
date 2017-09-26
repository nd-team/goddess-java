package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.bo.OldRecommendTypeBO;
import com.bjike.goddess.interiorrecommend.dto.OldRecommendTypeDTO;
import com.bjike.goddess.interiorrecommend.service.OldRecommendTypeSer;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.OldRecommendTypeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 推荐类型设定业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 02:10 ]
 * @Description: [ 推荐类型设定业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("recommendTypeApiImpl")
public class OldRecommendTypeApiImpl implements OldRecommendTypeAPI {

    @Autowired
    private OldRecommendTypeSer recommendTypeSer;

    @Override
    public OldRecommendTypeBO addModel(OldRecommendTypeTO to) throws SerException {
        return recommendTypeSer.insertModel(to);
    }

    @Override
    public OldRecommendTypeBO editModel(OldRecommendTypeTO to) throws SerException {
        return recommendTypeSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        recommendTypeSer.delete(id);
    }

    @Override
    public List<OldRecommendTypeBO> pageList(OldRecommendTypeDTO dto) throws SerException {
        return recommendTypeSer.pageList(dto);
    }

    @Override
    public OldRecommendTypeBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(recommendTypeSer.findById(id),OldRecommendTypeBO.class);
    }

    @Override
    public Long count(OldRecommendTypeDTO dto) throws SerException {
        return recommendTypeSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return recommendTypeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return recommendTypeSer.guidePermission(guidePermissionTO);
    }


}