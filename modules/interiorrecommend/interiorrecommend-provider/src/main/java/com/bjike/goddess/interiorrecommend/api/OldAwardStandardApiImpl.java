package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.OldAwardStandardBO;
import com.bjike.goddess.interiorrecommend.dto.OldAwardStandardDTO;
import com.bjike.goddess.interiorrecommend.service.OldAwardStandardSer;
import com.bjike.goddess.interiorrecommend.to.OldAwardStandardTO;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
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
public class OldAwardStandardApiImpl implements OldAwardStandardAPI {

    @Autowired
    private OldAwardStandardSer awardStandardSer;

    @Override
    public OldAwardStandardBO addModel(OldAwardStandardTO to) throws SerException {
        return awardStandardSer.insertModel(to);
    }

    @Override
    public OldAwardStandardBO editModel(OldAwardStandardTO to) throws SerException {
        return awardStandardSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        awardStandardSer.remove(id);
    }

    @Override
    public List<OldAwardStandardBO> pageList(OldAwardStandardDTO dto) throws SerException {
        return awardStandardSer.pageList(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return awardStandardSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return awardStandardSer.guidePermission(guidePermissionTO);
    }

    @Override
    public OldAwardStandardBO findOne(String id) throws SerException {
        return awardStandardSer.findOne(id);
    }

    @Override
    public Long count(OldAwardStandardDTO dto) throws SerException {
        return awardStandardSer.count(dto);
    }
}