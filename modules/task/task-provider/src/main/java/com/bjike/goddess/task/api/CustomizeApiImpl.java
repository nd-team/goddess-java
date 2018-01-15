package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.CustomizeBO;
import com.bjike.goddess.task.bo.DataBO;
import com.bjike.goddess.task.dto.CollectDTO;
import com.bjike.goddess.task.dto.CustomizeDTO;
import com.bjike.goddess.task.dto.CustomizeSonDTO;
import com.bjike.goddess.task.entity.CustomizeSon;
import com.bjike.goddess.task.service.CustomizeSer;
import com.bjike.goddess.task.service.CustomizeSonSer;
import com.bjike.goddess.task.to.CustomizeTO;
import com.bjike.goddess.taskallotment.api.ProjectAPI;
import com.bjike.goddess.taskallotment.api.TableAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Set;

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
    private CustomizeSonSer customizeSonSer;
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
        CustomizeSonDTO customizeSonDTO = new CustomizeSonDTO();
        customizeSonDTO.getConditions().add(Restrict.eq("customizeId",id));
        List<CustomizeSon> list = customizeSonSer.findByCis(customizeSonDTO);
        customizeSonSer.remove(list);
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

    @Override
    public String gather(CollectDTO to) throws SerException {
        return customizeSer.gather(to);
    }

    @Override
    public List<String> values(CustomizeTO to) throws SerException {
        return customizeSer.values(to);
    }

    @Override
    public void send(String id) throws SerException {
        customizeSer.send(id);
    }
}
