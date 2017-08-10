package com.bjike.goddess.staffshares.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffshares.bo.SchemeApplicationBO;
import com.bjike.goddess.staffshares.bo.SchemeBO;
import com.bjike.goddess.staffshares.bo.SchemeIssueBO;
import com.bjike.goddess.staffshares.dto.SchemeDTO;
import com.bjike.goddess.staffshares.service.SchemeSer;
import com.bjike.goddess.staffshares.to.SchemeApplyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工持股管理业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 08:59 ]
 * @Description: [ 员工持股管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("schemeApiImpl")
public class SchemeApiImpl implements SchemeAPI {
    @Autowired
    private SchemeSer schemeSer;

    @Override
    public void save(SchemeApplyTO to) throws SerException {
        schemeSer.save(to);
    }

    @Override
    public void update(SchemeApplyTO to) throws SerException {
        schemeSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        schemeSer.delete(id);
    }

    @Override
    public List<SchemeApplicationBO> maps(SchemeDTO dto) throws SerException {
        return schemeSer.maps(dto);
    }

    @Override
    public SchemeBO getById(String id) throws SerException {
        return schemeSer.getById(id);
    }

    @Override
    public Long getTotal(SchemeDTO schemeDTO) throws SerException {
        return schemeSer.getTotal(schemeDTO);
    }

    @Override
    public void examine(SchemeApplyTO to) throws SerException {
        schemeSer.examine(to);
    }

    @Override
    public void issue(String id) throws SerException {
        schemeSer.issue(id);
    }


    @Override
    public List<SchemeIssueBO> list(SchemeDTO dto) throws SerException {
        return schemeSer.list(dto);
    }

    @Override
    public SchemeIssueBO getOne(String id) throws SerException {
        return schemeSer.getOne(id);
    }

    @Override
    public Long count(SchemeDTO schemeDTO) throws SerException {
        return schemeSer.count(schemeDTO);
    }
}