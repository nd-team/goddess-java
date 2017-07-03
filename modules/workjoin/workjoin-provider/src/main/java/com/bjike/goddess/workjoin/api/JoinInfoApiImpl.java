package com.bjike.goddess.workjoin.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.bo.JoinInfoBO;
import com.bjike.goddess.workjoin.dto.JoinInfoDTO;
import com.bjike.goddess.workjoin.entity.JoinInfo;
import com.bjike.goddess.workjoin.service.JoinInfoSer;
import com.bjike.goddess.workjoin.to.JoinInfoTO;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 交接资料业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:34 ]
 * @Description: [ 交接资料业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("joinInfoApiImpl")
public class JoinInfoApiImpl implements JoinInfoAPI {
    @Autowired
    private JoinInfoSer joinInfoSer;
    @Override
    public Long countJoinInfo(JoinInfoDTO joinInfoDTO) throws SerException {
        return joinInfoSer.countJoinInfo(joinInfoDTO);
    }

    @Override
    public JoinInfoBO getOne(String id) throws SerException {
        return joinInfoSer.getOne(id);
    }

    @Override
    public List<JoinInfoBO> findListJoinInfo(JoinInfoDTO joinInfoDTO) throws SerException {
        return joinInfoSer.findListJoinInfo(joinInfoDTO);
    }

    @Override
    public JoinInfoBO insertJoinInfo(JoinInfoTO joinInfoTO) throws SerException {
        return joinInfoSer.insertJoinInfo(joinInfoTO);
    }

    @Override
    public JoinInfoBO editJoinInfo(JoinInfoTO joinInfoTO) throws SerException {
        return joinInfoSer.editJoinInfo(joinInfoTO);
    }

    @Override
    public void removeJoinInfo(String id) throws SerException {
        joinInfoSer.removeJoinInfo(id);
    }

}