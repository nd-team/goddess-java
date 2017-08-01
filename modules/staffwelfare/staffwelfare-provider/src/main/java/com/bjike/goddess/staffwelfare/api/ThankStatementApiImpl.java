package com.bjike.goddess.staffwelfare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfare.bo.ThankStatementBO;
import com.bjike.goddess.staffwelfare.dto.ThankStatementDTO;
import com.bjike.goddess.staffwelfare.service.ThankStatementSer;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.to.ThankStatementTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 感谢语业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 09:14 ]
 * @Description: [ 感谢语业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("thankStatementApiImpl")
public class ThankStatementApiImpl implements ThankStatementAPI {

    @Autowired
    private ThankStatementSer thankStatementSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return thankStatementSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return thankStatementSer.guidePermission(guidePermissionTO);
    }

    @Override
    public ThankStatementBO addModel(ThankStatementTO to) throws SerException {
        return thankStatementSer.insertModel(to);
    }

    @Override
    public ThankStatementBO editModel(ThankStatementTO to) throws SerException {
        return thankStatementSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        thankStatementSer.remove(id);
    }

    @Override
    public List<ThankStatementBO> pageList(ThankStatementDTO dto) throws SerException {
        return thankStatementSer.pageList(dto);
    }

    @Override
    public void freeze(String id) throws SerException {
        thankStatementSer.freeze(id);
    }

    @Override
    public void breakFreeze(String id) throws SerException {
        thankStatementSer.breakFreeze(id);
    }
}