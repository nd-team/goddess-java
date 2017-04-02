package com.bjike.goddess.enterpriseculturemanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.enterpriseculturemanage.bo.EnterpriseCultureInfoBO;
import com.bjike.goddess.enterpriseculturemanage.bo.PeriodicalProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.bo.PublicizeProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.dto.EnterpriseCultureInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.service.EnterpriseCultureInfoSer;
import com.bjike.goddess.enterpriseculturemanage.to.EnterpriseCultureInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业文化信息业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:05 ]
 * @Description: [ 企业文化信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("enterpriseCultureInfoApiImpl")
public class EnterpriseCultureInfoApiImpl implements EnterpriseCultureInfoAPI {

    @Autowired
    private EnterpriseCultureInfoSer enterpriseCultureInfoSer;

    @Override
    public EnterpriseCultureInfoBO addModel(EnterpriseCultureInfoTO to) throws SerException {
        return enterpriseCultureInfoSer.insertModel(to);
    }

    @Override
    public EnterpriseCultureInfoBO editModel(EnterpriseCultureInfoTO to) throws SerException {
        return enterpriseCultureInfoSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        enterpriseCultureInfoSer.remove(id);
    }

    @Override
    public List<EnterpriseCultureInfoBO> pageList(EnterpriseCultureInfoDTO dto) throws SerException {
        return enterpriseCultureInfoSer.pageList(dto);
    }

    @Override
    public PublicizeProgramInfoBO findPublicize(String id) throws SerException {
        return enterpriseCultureInfoSer.findPublicize(id);
    }

    @Override
    public PeriodicalProgramInfoBO findPeriodical(String id) throws SerException {
        return enterpriseCultureInfoSer.findPeriodical(id);
    }
}