package com.bjike.goddess.costdetail.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.costdetail.bo.CostDetailsAddEditBO;
import com.bjike.goddess.costdetail.bo.CostDetailsAddReturnBO;
import com.bjike.goddess.costdetail.bo.CostDetailsBO;
import com.bjike.goddess.costdetail.bo.CostDetailsYeCollBO;
import com.bjike.goddess.costdetail.dto.CostDetailsDTO;
import com.bjike.goddess.costdetail.service.CostDetailsSer;
import com.bjike.goddess.costdetail.to.CostDetailsAddEditTO;
import com.bjike.goddess.costdetail.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 成本明细业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 成本明细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("costDetailsApiImpl")
public class CostDetailsApiImpl implements CostDetailsAPI {
    @Autowired
    private CostDetailsSer costDetailsSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return costDetailsSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return costDetailsSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(CostDetailsDTO costDetailsDTO) throws SerException {
        return costDetailsSer.count(costDetailsDTO);
    }

    @Override
    public CostDetailsBO getOneById(String id) throws SerException {
        return costDetailsSer.getOneById(id);
    }

    @Override
    public CostDetailsAddEditBO getAllById(String id) throws SerException {
        return costDetailsSer.getAllById(id);
    }

    @Override
    public List<CostDetailsBO> list(CostDetailsDTO costDetailsDTO) throws SerException {
        return costDetailsSer.list(costDetailsDTO);
    }

    @Override
    public CostDetailsBO add(CostDetailsAddEditTO costDetailsAddEditTO) throws SerException {
        return costDetailsSer.add(costDetailsAddEditTO);
    }

    @Override
    public CostDetailsBO edit(CostDetailsAddEditTO costDetailsAddEditTO) throws SerException {
        return costDetailsSer.edit(costDetailsAddEditTO);
    }

    @Override
    public void delete(String id) throws SerException {
        costDetailsSer.delete(id);
    }

    @Override
    public CostDetailsAddEditBO seeDetail(String id) throws SerException {
        return costDetailsSer.seeDetail(id);
    }

    @Override
    public CostDetailsAddEditBO monthCollect(String costTime, String[] department) throws SerException {
        return costDetailsSer.monthCollect(costTime,department);
    }

    @Override
    public List<String> findAllDetails() throws SerException {
        return costDetailsSer.findAllDetails();
    }

    @Override
    public List<CostDetailsYeCollBO> yearCollect(Integer years) throws SerException {
        return costDetailsSer.yearCollect(years);
    }

    @Override
    public List<CostDetailsAddReturnBO> returnAddResult() throws SerException {
        return costDetailsSer.returnAddResult();
    }

    @Override
    public List<String> findAddAllDetails() throws SerException {
        return costDetailsSer.findAddAllDetails();
    }

    @Override
    public CostDetailsAddEditBO listDetail(CostDetailsDTO costDetailsDTO) throws SerException {
        return costDetailsSer.listDetail(costDetailsDTO);
    }
}