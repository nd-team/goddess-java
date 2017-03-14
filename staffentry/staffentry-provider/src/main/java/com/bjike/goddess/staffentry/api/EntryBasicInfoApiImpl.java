package com.bjike.goddess.staffentry.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.dto.EntryBasicInfoDTO;
import com.bjike.goddess.staffentry.entity.EntryBasicInfo;
import com.bjike.goddess.staffentry.service.EntryBasicInfoImpl;
import com.bjike.goddess.staffentry.service.EntryBasicInfoSer;
import com.bjike.goddess.staffentry.to.EntryBasicInfoTO;
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
    public List<EntryBasicInfoBO> listEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws SerException {
        List<EntryBasicInfo> entryBasicInfos = entryBasicInfoSer.listEntryBasicInfo(entryBasicInfoDTO);
        return BeanTransform.copyProperties(entryBasicInfos, EntryBasicInfoBO.class, true);
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
        EntryBasicInfo entryBasicInfo = entryBasicInfoSer.getEntryBasicInfo(id);

        return BeanTransform.copyProperties(entryBasicInfo, EntryBasicInfoBO.class, true);
    }

    @Override
    public EntryBasicInfoBO sendEntryBasicInfo(EntryBasicInfoTO entryBasicInfoTO) throws SerException {
        return entryBasicInfoSer.sendEntryBasicInfo(entryBasicInfoTO);
    }

    @Override
    public List<EntryBasicInfoBO> collectEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws SerException {
        return entryBasicInfoSer.collectEntryBasicInfo(entryBasicInfoDTO);
    }
}
