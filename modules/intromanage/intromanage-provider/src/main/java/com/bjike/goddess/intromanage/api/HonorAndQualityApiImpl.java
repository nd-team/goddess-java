package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.intromanage.bo.HonorAndQualityBO;
import com.bjike.goddess.intromanage.dto.HonorAndQualityDTO;
import com.bjike.goddess.intromanage.service.HonorAndQualitySer;
import com.bjike.goddess.intromanage.to.HonorAndQualityTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 荣誉与资质业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:28 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("honorAndQualityApiImpl")
public class HonorAndQualityApiImpl implements HonorAndQualityAPI {

    @Autowired
    private HonorAndQualitySer honorAndQualitySer;

    /**
     * 分页查询荣誉与资质
     *
     * @return class HonorAndQualityBO
     * @throws SerException
     */
    @Override
    public List<HonorAndQualityBO> list(HonorAndQualityDTO dto) throws SerException {
        return honorAndQualitySer.list(dto);
    }

    /**
     * 保存荣誉与资质
     *
     * @param to 荣誉与资质to
     * @return class HonorAndQualityBO
     * @throws SerException
     */
    @Override
    public HonorAndQualityBO save(HonorAndQualityTO to) throws SerException {
        return honorAndQualitySer.save(to);
    }

    /**
     * 根据id删除荣誉与资质
     *
     * @param id 荣誉与资质唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        honorAndQualitySer.remove(id);
    }

    /**
     * 更新荣誉与资质
     *
     * @param to 荣誉与资质to
     * @throws SerException
     */
    @Override
    public void update(HonorAndQualityTO to) throws SerException {
        honorAndQualitySer.update(to);
    }
}