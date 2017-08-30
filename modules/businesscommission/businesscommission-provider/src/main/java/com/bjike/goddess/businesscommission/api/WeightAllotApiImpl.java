package com.bjike.goddess.businesscommission.api;

import com.bjike.goddess.businesscommission.bo.WeightAllotBO;
import com.bjike.goddess.businesscommission.dto.WeightAllotDTO;
import com.bjike.goddess.businesscommission.excel.SonPermissionObject;
import com.bjike.goddess.businesscommission.service.WeightAllotSer;
import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
import com.bjike.goddess.businesscommission.to.WeightAllotTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务提成权重分配表业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-29 04:34 ]
 * @Description: [ 业务提成权重分配表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("weightAllotApiImpl")
public class WeightAllotApiImpl implements WeightAllotAPI {

    @Autowired
    private WeightAllotSer weightAllotSer;



    @Override
    public Long countWeightAllot(WeightAllotDTO weightAllotDTO) throws SerException {
        return weightAllotSer.countWeightAllot(weightAllotDTO);
    }

    @Override
    public WeightAllotBO getOneById(String id) throws SerException {
        return weightAllotSer.getOneById(id);
    }

    @Override
    public List<WeightAllotBO> listWeightAllot(WeightAllotDTO weightAllotDTO) throws SerException {
        return weightAllotSer.listWeightAllot(weightAllotDTO);
    }

    @Override
    public WeightAllotBO addWeightAllot(WeightAllotTO weightAllotTO) throws SerException {
        return weightAllotSer.addWeightAllot(weightAllotTO);
    }

    @Override
    public WeightAllotBO editWeightAllot(WeightAllotTO weightAllotTO) throws SerException {
        return weightAllotSer.editWeightAllot(weightAllotTO);
    }

    @Override
    public void deleteWeightAllot(String id) throws SerException {
        weightAllotSer.deleteWeightAllot(id);
    }

    @Override
    public WeightAllotBO importExcel(List<WeightAllotTO> weightAllotTO) throws SerException {
        return weightAllotSer.importExcel( weightAllotTO );
    }

    @Override
    public byte[] exportExcel(WeightAllotDTO dto) throws SerException {
        return weightAllotSer.exportExcel( dto );
    }
    @Override
    public byte[] templateExport( ) throws SerException {
        return weightAllotSer.templateExport(   );
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return weightAllotSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return weightAllotSer.guidePermission( guidePermissionTO );
    }

}