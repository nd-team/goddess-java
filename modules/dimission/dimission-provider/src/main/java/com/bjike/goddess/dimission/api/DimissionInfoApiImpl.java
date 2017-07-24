package com.bjike.goddess.dimission.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dimission.bo.DimissionInfoBO;
import com.bjike.goddess.dimission.bo.DimissionInfoCollectBO;
import com.bjike.goddess.dimission.bo.DimissionReasonBO;
import com.bjike.goddess.dimission.dto.DimissionInfoDTO;
import com.bjike.goddess.dimission.enums.DimissionType;
import com.bjike.goddess.dimission.excel.SonPermissionObject;
import com.bjike.goddess.dimission.service.DimissionInfoSer;
import com.bjike.goddess.dimission.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 离职信息业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:12 ]
 * @Description: [ 离职信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("dimissionInfoApiImpl")
public class DimissionInfoApiImpl implements DimissionInfoAPI {

    @Autowired
    private DimissionInfoSer dimissionInfoSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return dimissionInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return dimissionInfoSer.guidePermission( guidePermissionTO );
    }

    @Override
    public DimissionInfoBO apply(DimissionInfoTO to) throws SerException {
        return dimissionInfoSer.apply(to);
    }

    @Override
    public DimissionInfoBO update(DimissionInfoTO to) throws SerException {
        return dimissionInfoSer.update(to);
    }

    @Override
    public DimissionInfoBO presume(DimissionInfoTO to) throws SerException {
        return dimissionInfoSer.presume(to);
    }

    @Override
    public DimissionInfoBO delete(String id) throws SerException {
        return dimissionInfoSer.delete(id);
    }

    @Override
    public DimissionInfoBO interview(DimissionInterviewTo to) throws SerException {
        return dimissionInfoSer.interview(to);
    }

    @Override
    public DimissionInfoBO audit(DimissionAuditTO to) throws SerException {
        return dimissionInfoSer.audit(to);
    }

    @Override
    public DimissionInfoBO affirm(DimissionAffirmTO to) throws SerException {
        return dimissionInfoSer.affirm(to);
    }

    @Override
    public DimissionInfoBO success(String id) throws SerException {
        return dimissionInfoSer.success(id);
    }

    @Override
    public DimissionInfoBO editType(DimissionTypeTO to) throws SerException {
        return dimissionInfoSer.editType(to);
    }

    @Override
    public List<DimissionInfoBO> findByType(DimissionType type) throws SerException {
        return dimissionInfoSer.findByType(type);
    }

    @Override
    public List<DimissionInfoBO> presumeList(DimissionInfoDTO dto) throws SerException {
        return dimissionInfoSer.presumeList(dto);
    }

    @Override
    public List<DimissionInfoBO> maps(DimissionInfoDTO dto) throws SerException {
        return dimissionInfoSer.maps(dto);
    }

    @Override
    public List<DimissionInfoBO> findByDimissionDate(String start, String end) throws SerException {
        return dimissionInfoSer.findByDimissionDate(start, end);
    }

    @Override
    public List<DimissionInfoBO> all() throws SerException {
        return dimissionInfoSer.all();
    }

    @Override
    public List<DimissionInfoCollectBO> departmentCollect(DimissionCollectTO to) throws SerException {
        return dimissionInfoSer.departmentCollect(to);
    }

    @Override
    public List<DimissionInfoCollectBO> positionCollect(DimissionCollectTO to) throws SerException {
        return dimissionInfoSer.positionCollect(to);
    }

    @Override
    public List<DimissionInfoCollectBO> entryCollect(DimissionCollectTO to) throws SerException {
        return dimissionInfoSer.entryCollect(to);
    }

    @Override
    public List<DimissionInfoCollectBO> seniorityCollect(DimissionCollectTO to) throws SerException {
        return dimissionInfoSer.seniorityCollect(to);
    }

    @Override
    public List<DimissionInfoCollectBO> educationCollect(DimissionCollectTO to) throws SerException {
        return dimissionInfoSer.educationCollect(to);
    }

    @Override
    public List<DimissionReasonBO> reasonCollect(DimissionCollectTO to) throws SerException {
        return dimissionInfoSer.reasonCollect(to);
    }

    @Override
    public DimissionInfoBO getById(String id) throws SerException {
        return dimissionInfoSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return dimissionInfoSer.getTotal();
    }

    @Override
    public List<String> getAllName() throws SerException {
        return dimissionInfoSer.getAllName();
    }
}