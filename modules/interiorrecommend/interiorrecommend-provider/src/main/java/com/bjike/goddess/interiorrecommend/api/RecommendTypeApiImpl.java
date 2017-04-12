package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.RecommendTypeBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendTypeDTO;
import com.bjike.goddess.interiorrecommend.service.RecommendTypeSer;
import com.bjike.goddess.interiorrecommend.to.RecommendTypeTO;
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
public class RecommendTypeApiImpl implements RecommendTypeAPI {

    @Autowired
    private RecommendTypeSer recommendTypeSer;

    @Override
    public RecommendTypeBO addModel(RecommendTypeTO to) throws SerException {
        return recommendTypeSer.insertModel(to);
    }

    @Override
    public RecommendTypeBO editModel(RecommendTypeTO to) throws SerException {
        return recommendTypeSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        recommendTypeSer.remove(id);
    }

    @Override
    public List<RecommendTypeBO> pageList(RecommendTypeDTO dto) throws SerException {
        return recommendTypeSer.pageList(dto);
    }
}