package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.ComputerAssistBO;
import com.bjike.goddess.assistance.dto.ComputerAssistDTO;
import com.bjike.goddess.assistance.service.ComputerAssistSer;
import com.bjike.goddess.assistance.to.ComputerAssistTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 电脑补助业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:20 ]
 * @Description: [ 电脑补助业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("computerAssistApiImpl")
public class ComputerAssistApiImpl implements ComputerAssistAPI {

    @Autowired
    private ComputerAssistSer computerAssistSer;

    @Override
    public Long countComputerAssist(ComputerAssistDTO computerAssistDTO) throws SerException {
        return computerAssistSer.countComputerAssist( computerAssistDTO);
    }

    @Override
    public ComputerAssistBO getOneById(String id) throws SerException {
        return computerAssistSer.getOneById(id);
    }

    @Override
    public List<ComputerAssistBO> listComputerAssist(ComputerAssistDTO computerAssistDTO) throws SerException {
        return computerAssistSer.listComputerAssist(computerAssistDTO);
    }

    @Override
    public ComputerAssistBO addComputerAssist(ComputerAssistTO computerAssistTO) throws SerException {
        return computerAssistSer.addComputerAssist(computerAssistTO);
    }

    @Override
    public ComputerAssistBO editComputerAssist(ComputerAssistTO computerAssistTO) throws SerException {
        return computerAssistSer.editComputerAssist(computerAssistTO);
    }

    @Override
    public void deleteComputerAssist(String id) throws SerException {
        computerAssistSer.deleteComputerAssist(id);
    }

    @Override
    public List<ComputerAssistBO> collectByArea(ComputerAssistDTO computerAssistDTO) throws SerException {
        return computerAssistSer.collectByArea( computerAssistDTO);
    }

    @Override
    public List<ComputerAssistBO> collectByProGroup(ComputerAssistDTO computerAssistDTO) throws SerException {
        return computerAssistSer.collectByProGroup(computerAssistDTO);
    }

    @Override
    public List<String> listAllUser() throws SerException {
        return computerAssistSer.listAllUser();
    }

    @Override
    public List<String> listAllArea() throws SerException {
        return computerAssistSer.listAllArea();
    }

    @Override
    public List<String> listAllProject() throws SerException {
        return computerAssistSer.listAllProject();
    }

    @Override
    public EntryBasicInfoBO getUserByName(ComputerAssistDTO computerAssistDTO) throws SerException {
        return computerAssistSer.getUserByName(computerAssistDTO);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return computerAssistSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return computerAssistSer.guidePermission(guidePermissionTO);
    }
}