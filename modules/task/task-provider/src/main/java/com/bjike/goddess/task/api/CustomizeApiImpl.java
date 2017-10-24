package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.bo.CustomizeBO;
import com.bjike.goddess.task.dto.CustomizeDTO;
import com.bjike.goddess.task.entity.Customize;
import com.bjike.goddess.task.service.CustomizeSer;
import com.bjike.goddess.task.to.CustomizeTO;
import com.bjike.goddess.taskallotment.api.ProjectAPI;
import com.bjike.goddess.taskallotment.api.TableAPI;
import com.bjike.goddess.taskallotment.bo.ProjectBO;
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
    public void enable(String id, boolean enable) throws SerException {
        customizeSer.enable(id, enable);
    }

    @Override
    public CustomizeBO findByID(String id) throws SerException {
        Customize customize = customizeSer.findById(id);
        CustomizeBO bo= BeanTransform.copyProperties(customize, CustomizeBO.class);
        bo.setCollectName(customizeSer.findById(bo.getId()).getName());
        ProjectBO project = projectAPI.findByID(bo.getProjectId());
        if (null != project) {
            bo.setProject(project.getProject());
        }
        bo.setTables(tableAPI.names(bo.getTablesId().split(",")));
        return bo;
    }
}
