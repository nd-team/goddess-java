package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.ResumeInfoBO;
import com.bjike.goddess.archive.bo.ResumeInfoDataBO;
import com.bjike.goddess.archive.dto.ResumeInfoDTO;
import com.bjike.goddess.archive.service.ResumeInfoSer;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.ResumeInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 人员简历信息业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-14 11:30 ]
* @Description:	[ 人员简历信息业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("resumeInfoApiImpl")
public class ResumeInfoApiImpl implements ResumeInfoAPI  {
    @Autowired
    private ResumeInfoSer resumeInfoSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return resumeInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return resumeInfoSer.guidePermission(guidePermissionTO);
    }

    @Override
    public ResumeInfoDataBO findDataByName(String name) throws SerException {
        return resumeInfoSer.findDataByName(name);
    }

    @Override
    public void add(ResumeInfoTO to) throws SerException {
        resumeInfoSer.add(to);
    }

    @Override
    public void edit(ResumeInfoTO to) throws SerException {
        resumeInfoSer.edit(to);
    }

    @Override
    public ResumeInfoBO findEntity(String id) throws SerException {
        return resumeInfoSer.findEntity(id);
    }

    @Override
    public void delete(String id) throws SerException {
        resumeInfoSer.delete(id);
    }

    @Override
    public byte[] exportExcel(ResumeInfoDTO dto) throws SerException {
        return resumeInfoSer.exportExcel(dto);
    }

    @Override
    public void freeze(String id) throws SerException {
        resumeInfoSer.freeze(id);
    }

    @Override
    public void thaw(String id) throws SerException {
        resumeInfoSer.thaw(id);
    }

    @Override
    public void upload(List<ResumeInfoTO> toList) throws SerException {
        resumeInfoSer.upload(toList);
    }

    @Override
    public List<ResumeInfoBO> maps(ResumeInfoDTO dto) throws SerException {
        return resumeInfoSer.maps(dto);
    }

    @Override
    public Long getTotal(ResumeInfoDTO dto) throws SerException {
        return resumeInfoSer.getTotal(dto);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        return resumeInfoSer.templateExcel();
    }
}