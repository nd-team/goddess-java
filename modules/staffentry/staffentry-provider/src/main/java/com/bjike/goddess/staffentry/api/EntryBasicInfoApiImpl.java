package com.bjike.goddess.staffentry.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.bo.FindNameBO;
import com.bjike.goddess.staffentry.bo.EntryOptionBO;
import com.bjike.goddess.staffentry.dto.EntryBasicInfoDTO;
import com.bjike.goddess.staffentry.entity.EntryBasicInfo;
import com.bjike.goddess.staffentry.service.EntryBasicInfoSer;
import com.bjike.goddess.staffentry.to.EntryBasicInfoTO;
import com.bjike.goddess.staffentry.to.GuidePermissionTO;
import com.bjike.goddess.staffentry.vo.EntryBasicInfoVO;
import com.bjike.goddess.staffentry.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 入职基本信息业务接口
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 11:55]
 * @Description: [入职基本信息业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("entryBasicInfoApiImpl")
public class EntryBasicInfoApiImpl implements EntryBasicInfoAPI{

    @Autowired
    private EntryBasicInfoSer entryBasicInfoSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return entryBasicInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return entryBasicInfoSer.guidePermission( guidePermissionTO );
    }

    @Override
    public Long countEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws SerException {
        return entryBasicInfoSer.countEntryBasicInfo(entryBasicInfoDTO);
    }

    @Override
    public List<EntryBasicInfoBO> listEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws SerException {
        List<EntryBasicInfoBO> entryBasicInfos = entryBasicInfoSer.listEntryBasicInfo(entryBasicInfoDTO);
        return entryBasicInfos;
    }

    @Override
    public EntryBasicInfoBO insertEntryBasicInfo(EntryBasicInfoTO entryBasicInfoTO) throws SerException {
        return entryBasicInfoSer.insertEntryBasicInfo(entryBasicInfoTO);
    }

    @Override
    public EntryBasicInfoBO editEntryBasicInfo(EntryBasicInfoTO entryBasicInfoTO) throws SerException {
        return entryBasicInfoSer.editEntryBasicInfo(entryBasicInfoTO);
    }

    @Override
    public void removeEntryBasicInfo(String id) throws SerException {
        entryBasicInfoSer.removeEntryBasicInfo(id);
    }

    @Override
    public EntryBasicInfoBO getEntryBasicInfo(String id) throws SerException {
        EntryBasicInfoBO entryBasicInfo = entryBasicInfoSer.getEntryBasicInfo(id);

        return entryBasicInfo ;
    }

    @Override
    public EntryBasicInfoBO sendEntryBasicInfo(EntryBasicInfoTO entryBasicInfoTO) throws SerException {
        return entryBasicInfoSer.sendEntryBasicInfo(entryBasicInfoTO);
    }

    @Override
    public List<EntryBasicInfoBO> collectEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws SerException {
        return entryBasicInfoSer.collectEntryBasicInfo(entryBasicInfoDTO);
    }

    @Override
    public List<String> listPost() throws SerException {
        return entryBasicInfoSer.listPost();
    }

    @Override
    public List<EntryBasicInfoBO> getEntryBasicInfoByName(String name) throws SerException {
        return entryBasicInfoSer.getEntryBasicInfoByName(name);
    }

    @Override
    public List<EntryBasicInfoBO> getByEmpNumber(String empNumber) throws SerException {
        return entryBasicInfoSer.getByEmpNumber(empNumber);
    }

    @Override
    public List<FindNameBO> findName() throws SerException {
        return entryBasicInfoSer.findName();
    }
    public List<EntryOptionBO> getEntryOptionByNameAndEmpNum(String name, String empNumer) throws SerException {
        return entryBasicInfoSer.getEntryOptionByNameAndEmpNum(name,empNumer);
    }

    @Override
    public List<EntryBasicInfoBO> listEntryBasicInfo() throws SerException {
        return BeanTransform.copyProperties(entryBasicInfoSer.findAll(),EntryBasicInfoBO.class,false);
    }


}
