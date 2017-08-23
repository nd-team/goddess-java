package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.DetailBO;
import com.bjike.goddess.rotation.bo.RotationStatisticsBO;
import com.bjike.goddess.rotation.dto.RotationStatisticsDTO;
import com.bjike.goddess.rotation.service.RotationStatisticsSer;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.rotation.to.RotationStatisticsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位轮换统计业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:38 ]
 * @Description: [ 岗位轮换统计业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("rotationStatisticsApiImpl")
public class RotationStatisticsApiImpl implements RotationStatisticsAPI {

    @Autowired
    private RotationStatisticsSer rotationStatisticsSer;

    @Override
    public RotationStatisticsBO save(RotationStatisticsTO to) throws SerException {
        return rotationStatisticsSer.save(to);
    }

    @Override
    public RotationStatisticsBO update(RotationStatisticsTO to) throws SerException {
        return rotationStatisticsSer.update(to);
    }

    @Override
    public RotationStatisticsBO delete(String id) throws SerException {
        return rotationStatisticsSer.delete(id);
    }

    @Override
    public RotationStatisticsBO getById(String id) throws SerException {
        return rotationStatisticsSer.getById(id);
    }

    @Override
    public List<RotationStatisticsBO> maps(RotationStatisticsDTO dto) throws SerException {
        return rotationStatisticsSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return rotationStatisticsSer.getTotal();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return rotationStatisticsSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return rotationStatisticsSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<DetailBO> getDetail() throws SerException {
        return rotationStatisticsSer.getDetail();
    }
}