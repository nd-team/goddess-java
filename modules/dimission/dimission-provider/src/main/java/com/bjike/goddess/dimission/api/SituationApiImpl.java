package com.bjike.goddess.dimission.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dimission.bo.DimissionCollectBO;
import com.bjike.goddess.dimission.bo.SituationBO;
import com.bjike.goddess.dimission.dto.SituationDTO;
import com.bjike.goddess.dimission.service.SituationSer;
import com.bjike.goddess.dimission.to.GuidePermissionTO;
import com.bjike.goddess.dimission.to.SituationTO;
import com.bjike.goddess.organize.bo.OptionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 离职办理节点情况业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-28 02:23 ]
* @Description:	[ 离职办理节点情况业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("situationApiImpl")
public class SituationApiImpl implements SituationAPI  {
    @Autowired
    private SituationSer situationSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return situationSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return situationSer.guidePermission(guidePermissionTO);
    }

    @Override
    public SituationBO save(SituationTO to) throws SerException {
        return situationSer.save(to);
    }

    @Override
    public SituationBO update(SituationTO to) throws SerException {
        return situationSer.update(to);
    }

    @Override
    public SituationBO delete(String id) throws SerException {
        return situationSer.delete(id);
    }

    @Override
    public List<SituationBO> list(SituationDTO dto) throws SerException {
        return situationSer.list(dto);
    }

    @Override
    public SituationBO findById(String id) throws SerException {
        return situationSer.getById(id);
    }

    @Override
    public List<String> getName() throws SerException {
        return situationSer.getName();
    }

    @Override
    public Boolean isSelf(String name) throws SerException {
        return situationSer.isSelf(name);
    }

    @Override
    public List<DimissionCollectBO> collect(String startTime, String endTime) throws SerException {
        return situationSer.collect(startTime, endTime);
    }

    @Override
    public Long getTotal() throws SerException {
        return situationSer.getTotal();
    }

    @Override
    public OptionBO figureShowMonth(String month) throws SerException {
        return situationSer.figureShowMonth(month);
    }
}