package com.bjike.goddess.receivable.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.receivable.bo.BackProgressBO;
import com.bjike.goddess.receivable.bo.BackProgressCollectBO;
import com.bjike.goddess.receivable.bo.CollectDetailBO;
import com.bjike.goddess.receivable.dto.BackProgressDTO;
import com.bjike.goddess.receivable.service.BackProgressSer;
import com.bjike.goddess.receivable.to.BackProgressCollectTO;
import com.bjike.goddess.receivable.to.BackProgressTO;
import com.bjike.goddess.receivable.to.GuidePermissionTO;
import com.bjike.goddess.receivable.to.YearCollectTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 回款进度业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 02:52 ]
 * @Description: [ 回款进度业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("backProgressApiImpl")
public class BackProgressApiImpl implements BackProgressAPI {
    @Autowired
    private BackProgressSer backProgressSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return backProgressSer.sonPermission();
    }
    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return backProgressSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long count(BackProgressDTO dto) throws SerException {
        return backProgressSer.count(dto);
    }
    @Override
    public List<BackProgressBO> list(BackProgressDTO dto) throws SerException {
        return backProgressSer.list(dto);
    }
    @Override
    public BackProgressBO getOne(String id) throws SerException {
        return backProgressSer.getOne(id);
    }
    @Override
    public BackProgressBO importExcel(List<BackProgressTO> backProgressTOS) throws SerException {
        return backProgressSer.importExcel(backProgressTOS);
    }
    @Override
    public byte[] templateExport() throws SerException {
        return backProgressSer.templateExport();
    }


    @Override
    public BackProgressBO edit(BackProgressTO to) throws SerException {
        return backProgressSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        backProgressSer.delete(id);
    }

    @Override
    public List<BackProgressCollectBO> backProgressCollect(BackProgressCollectTO to) throws SerException {
        return backProgressSer.backProgressCollect(to);
    }

    @Override
    public Set<String> areas() throws SerException {
        return backProgressSer.areas();
    }

    @Override
    public Set<String> contractors() throws SerException {
        return backProgressSer.contractors();
    }

    @Override
    public Set<String> types() throws SerException {
        return backProgressSer.types();
    }

    @Override
    public Set<String> majors() throws SerException {
        return backProgressSer.majors();
    }

    @Override
    public Set<String> operatorNames() throws SerException {
        return backProgressSer.operatorNames();
    }

    @Override
    public Set<String> taskCases() throws SerException {
        return backProgressSer.taskCases();
    }

    @Override
    public Set<String> completeStatus() throws SerException {
        return backProgressSer.completeStatus();
    }

    @Override
    public List<CollectDetailBO> yearCollect(YearCollectTO to) throws SerException {
        return backProgressSer.yearCollect(to);
    }
}