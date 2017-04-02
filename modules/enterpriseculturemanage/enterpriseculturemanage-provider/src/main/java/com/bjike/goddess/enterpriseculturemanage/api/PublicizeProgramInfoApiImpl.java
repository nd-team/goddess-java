package com.bjike.goddess.enterpriseculturemanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.enterpriseculturemanage.bo.EnterpriseCultureInfoBO;
import com.bjike.goddess.enterpriseculturemanage.bo.PublicizeProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.dto.PublicizeProgramInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.service.EnterpriseCultureInfoSer;
import com.bjike.goddess.enterpriseculturemanage.service.PublicizeProgramInfoSer;
import com.bjike.goddess.enterpriseculturemanage.to.PublicizeProgramInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 宣传方案信息业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:28 ]
 * @Description: [ 宣传方案信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("publicizeProgramInfoApiImpl")
public class PublicizeProgramInfoApiImpl implements PublicizeProgramInfoAPI {

    @Autowired
    private PublicizeProgramInfoSer publicizeProgramInfoSer;
    @Autowired
    private EnterpriseCultureInfoSer enterpriseCultureInfoSer;

    @Override
    public List<EnterpriseCultureInfoBO> findInfo() throws SerException {
        return enterpriseCultureInfoSer.findThawAll();
    }

    @Override
    public PublicizeProgramInfoBO addModel(PublicizeProgramInfoTO to) throws SerException {
        return publicizeProgramInfoSer.insertModel(to);
    }

    @Override
    public PublicizeProgramInfoBO editModel(PublicizeProgramInfoTO to) throws SerException {
        return publicizeProgramInfoSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        publicizeProgramInfoSer.remove(id);
    }

    @Override
    public void audit(PublicizeProgramInfoTO to) throws SerException {
        publicizeProgramInfoSer.audit(to);
    }

    @Override
    public List<PublicizeProgramInfoBO> pageList(PublicizeProgramInfoDTO dto) throws SerException {
        return publicizeProgramInfoSer.pageList(dto);
    }
}