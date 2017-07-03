package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.ForeignStaffingBO;
import com.bjike.goddess.archive.dto.ForeignStaffingDTO;
import com.bjike.goddess.archive.service.ForeignStaffingSer;
import com.bjike.goddess.archive.to.ForeignStaffingTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 对外人员信息业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 03:09 ]
 * @Description: [ 对外人员信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("foreignStaffingApiImpl")
public class ForeignStaffingApiImpl implements ForeignStaffingAPI {

    @Autowired
    private ForeignStaffingSer foreignStaffingSer;

    @Override
    public ForeignStaffingBO save(ForeignStaffingTO to) throws SerException {
        return foreignStaffingSer.save(to);
    }

    @Override
    public ForeignStaffingBO update(ForeignStaffingTO to) throws SerException {
        return foreignStaffingSer.update(to);
    }

    @Override
    public ForeignStaffingBO delete(String id) throws SerException {
        return foreignStaffingSer.delete(id);
    }

    @Override
    public List<ForeignStaffingBO> maps(ForeignStaffingDTO dto) throws SerException {
        return foreignStaffingSer.maps(dto);
    }

    @Override
    public ForeignStaffingBO getById(String id) throws SerException {
        return foreignStaffingSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return foreignStaffingSer.getTotal();
    }
}