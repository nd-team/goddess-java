package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.bo.CompletionTimeBO;
import com.bjike.goddess.projectroyalty.dto.CompletionTimeDTO;
import com.bjike.goddess.projectroyalty.service.CompletionTimeSer;
import com.bjike.goddess.projectroyalty.to.CompletionTimeTO;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 完工时间业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:44 ]
 * @Description: [ 完工时间业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("completionTimeApiImpl")
public class CompletionTimeApiImpl implements CompletionTimeAPI {

    @Autowired
    private CompletionTimeSer completionTimeSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return completionTimeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return completionTimeSer.guidePermission(guidePermissionTO);
    }


    @Override
    public CompletionTimeBO save(CompletionTimeTO to) throws SerException {
        return completionTimeSer.save(to);
    }

    @Override
    public CompletionTimeBO update(CompletionTimeTO to) throws SerException {
        return completionTimeSer.update(to);
    }

    @Override
    public CompletionTimeBO delete(String id) throws SerException {
        return completionTimeSer.delete(id);
    }

    @Override
    public CompletionTimeBO getById(String id) throws SerException {
        return completionTimeSer.getById(id);
    }

    @Override
    public List<CompletionTimeBO> maps(CompletionTimeDTO dto) throws SerException {
        return completionTimeSer.maps(dto);
    }

    @Override
    public List<OpinionBO> findOpinion() throws SerException {
        return completionTimeSer.findOpinion();
    }

    @Override
    public Long getTotal() throws SerException {
        return completionTimeSer.getTotal();
    }
}