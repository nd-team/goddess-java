package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.CustomizeBO;
import com.bjike.goddess.task.dto.CustomizeDTO;
import com.bjike.goddess.task.service.CustomizeSer;
import com.bjike.goddess.task.to.CustomizeTO;
import com.bjike.goddess.taskallotment.api.ProjectAPI;
import com.bjike.goddess.taskallotment.api.TableAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-25 17:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("customizeApiImpl")
public class CustomizeApiImpl implements CustomizeAPI {
    @Autowired
    private CustomizeSer customizeSer;
    @Autowired
    private ProjectAPI projectAPI;
    @Autowired
    private TableAPI tableAPI;

    @Override
    public List<CustomizeBO> list(CustomizeDTO dto) throws SerException {
        return customizeSer.list(dto);
    }

    @Override
    public Long count(CustomizeDTO dto) throws SerException {
        return customizeSer.count(dto);
    }

    @Override
    public void add(CustomizeTO to) throws SerException {
        customizeSer.add(to);
    }

    @Override
    public void delete(String id) throws SerException {
        customizeSer.remove(id);
    }

    @Override
    public void edit(CustomizeTO to) throws SerException {
        customizeSer.edit(to);
    }

    @Override
    public Double get(String[] tableIds) throws SerException {
        return customizeSer.get(tableIds);
    }

    @Override
    public void notice(CustomizeTO to) throws SerException {
        customizeSer.notice(to);
    }

    @Override
    public CustomizeBO one(String id) throws SerException {
        return customizeSer.one(id);
    }

    @Override
    public void quartz() throws SerException {
        customizeSer.quartz();
    }

    @Override
    public String detail(String id) throws SerException {
        return customizeSer.detail(id);
    }
}
