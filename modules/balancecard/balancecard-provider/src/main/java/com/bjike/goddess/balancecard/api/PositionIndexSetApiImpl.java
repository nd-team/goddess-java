package com.bjike.goddess.balancecard.api;

import com.bjike.goddess.balancecard.bo.PositionIndexSetBO;
import com.bjike.goddess.balancecard.dto.PositionIndexSetDTO;
import com.bjike.goddess.balancecard.service.PositionIndexSetSer;
import com.bjike.goddess.balancecard.to.ExportExcelPositTO;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.balancecard.to.PositionIndexSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位指标设置业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:38 ]
 * @Description: [ 岗位指标设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("positionIndexSetApiImpl")
public class PositionIndexSetApiImpl implements PositionIndexSetAPI {


    @Autowired
    private PositionIndexSetSer positionIndexSetSer;

    @Override
    public Long countPositionIndexSet(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return positionIndexSetSer.countPositionIndexSet( positionIndexSetDTO);
    }

    @Override
    public PositionIndexSetBO getOneById(String id) throws SerException {
        return positionIndexSetSer.getOneById(id);
    }

    @Override
    public List<PositionIndexSetBO> listPositionIndexSet(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return positionIndexSetSer.listPositionIndexSet(positionIndexSetDTO);
    }

    @Override
    public PositionIndexSetBO addPositionIndexSet(PositionIndexSetTO positionIndexSetTO) throws SerException {
        return positionIndexSetSer.addPositionIndexSet(positionIndexSetTO);
    }

    @Override
    public PositionIndexSetBO editPositionIndexSet(PositionIndexSetTO positionIndexSetTO) throws SerException {
        return positionIndexSetSer.editPositionIndexSet(positionIndexSetTO);
    }

    @Override
    public void deletePositionIndexSet(String id) throws SerException {
        positionIndexSetSer.deletePositionIndexSet(id);
    }

    @Override
    public Long countNow(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return positionIndexSetSer.countNow( positionIndexSetDTO );
    }

    @Override
    public List<PositionIndexSetBO> listNow(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return positionIndexSetSer.listNow(positionIndexSetDTO);
    }

    @Override
    public Long countSelf(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return positionIndexSetSer.countSelf(positionIndexSetDTO);
    }

    @Override
    public List<PositionIndexSetBO> listSelf(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return positionIndexSetSer.listSelf(positionIndexSetDTO);
    }

    @Override
    public PositionIndexSetBO addSelf(PositionIndexSetTO positionIndexSetTO) throws SerException {
        return positionIndexSetSer.addSelf(positionIndexSetTO);
    }

    @Override
    public PositionIndexSetBO editSelf(PositionIndexSetTO positionIndexSetTO) throws SerException {
        return positionIndexSetSer.editSelf(positionIndexSetTO);
    }

    @Override
    public void deleteSelf(String id) throws SerException {
        positionIndexSetSer.deleteSelf(id);
    }

    @Override
    public void leadExcel(List<PositionIndexSetTO> toList) throws SerException {
        positionIndexSetSer.leadExcel(toList);
    }

    @Override
    public byte[] positionReport(ExportExcelPositTO to) throws SerException {
        return positionIndexSetSer.positionReport(to);
    }

    @Override
    public byte[] personReport(ExportExcelPositTO to) throws SerException {
        return positionIndexSetSer.personReport(to);
    }

    @Override
    public List<PositionIndexSetBO> dendrogram(String id) throws SerException {
        return positionIndexSetSer.dendrogram(id);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return positionIndexSetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return positionIndexSetSer.guidePermission(guidePermissionTO);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return positionIndexSetSer.templateExport();
    }
}