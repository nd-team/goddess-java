package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.EquityGiftBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityGiftDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityGift;
import com.bjike.goddess.shareholdersmanage.service.EquityGiftSer;
import com.bjike.goddess.shareholdersmanage.to.EquityGiftTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 股权赠与业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:08 ]
 * @Description: [ 股权赠与业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("equityGiftApiImpl")
public class EquityGiftApiImpl implements EquityGiftAPI {
    @Autowired
    private EquityGiftSer equityGiftSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return equityGiftSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return equityGiftSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countGift(EquityGiftDTO equityGiftDTO) throws SerException {
        return equityGiftSer.countGift(equityGiftDTO);
    }

    @Override
    public EquityGiftBO getOne(String id) throws SerException {
        return equityGiftSer.getOne(id);
    }

    @Override
    public List<EquityGiftBO> findList(EquityGiftDTO equityGiftDTO) throws SerException {
        return equityGiftSer.findList(equityGiftDTO);
    }

    @Override
    public EquityGiftBO save(EquityGiftTO equityGiftTO) throws SerException {
        return equityGiftSer.save(equityGiftTO);
    }

    @Override
    public EquityGiftBO edit(EquityGiftTO equityGiftTO) throws SerException {
        return equityGiftSer.edit(equityGiftTO);
    }

    @Override
    public void delete(String id) throws SerException {
        equityGiftSer.delete(id);
    }
}