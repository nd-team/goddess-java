package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.AwardStandardBO;
import com.bjike.goddess.interiorrecommend.dto.AwardStandardDTO;
import com.bjike.goddess.interiorrecommend.service.AwardStandardSer;
import com.bjike.goddess.interiorrecommend.to.AwardStandardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 推荐奖励要求标准业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 11:39 ]
 * @Description: [ 推荐奖励要求标准业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("awardStandardApiImpl")
public class AwardStandardApiImpl implements AwardStandardAPI {

    @Autowired
    private AwardStandardSer awardStandardSer;

    @Override
    public AwardStandardBO addModel(AwardStandardTO to) throws SerException {
        return awardStandardSer.insertModel(to);
    }

    @Override
    public AwardStandardBO editModel(AwardStandardTO to) throws SerException {
        return awardStandardSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        awardStandardSer.remove(id);
    }

    @Override
    public List<AwardStandardBO> pageList(AwardStandardDTO dto) throws SerException {
        return awardStandardSer.pageList(dto);
    }
}