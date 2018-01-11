package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.RotationCollectBO;
import com.bjike.goddess.rotation.bo.RotationCollectEchartBO;
import com.bjike.goddess.rotation.bo.RotationDetailsCollectBO;
import com.bjike.goddess.rotation.dto.RotationCollectEchartDTO;
import com.bjike.goddess.rotation.dto.RotationDetailsCollectDTO;
import com.bjike.goddess.rotation.enums.CollectTimeType;
import com.bjike.goddess.rotation.service.RotationCollectSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-09 15:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Service
public class RotationCollectApiImpl implements RotationCollectAPI{
    @Autowired
    RotationCollectSer rotationCollectSer;

    @Override
    public List<RotationDetailsCollectBO> listDetailsCollect(RotationDetailsCollectDTO dto) throws SerException {
        return rotationCollectSer.listDetailsCollect(dto);
    }

    @Override
    public List<RotationCollectBO> listCollect(CollectTimeType collectTimeType, String... params) throws SerException {
        return rotationCollectSer.listCollect(collectTimeType, params);
    }

    @Override
    public RotationCollectEchartBO collectDetailsEchart(RotationCollectEchartDTO dto) throws SerException {
        return rotationCollectSer.collectDetailsEchart(dto);
    }

    @Override
    public RotationCollectEchartBO collectEchart(RotationCollectEchartDTO dto) throws SerException {
        return rotationCollectSer.collectEchart(dto);
    }
}
