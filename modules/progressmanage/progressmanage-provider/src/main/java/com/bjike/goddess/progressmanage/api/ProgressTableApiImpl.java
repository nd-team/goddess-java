package com.bjike.goddess.progressmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.progressmanage.bo.ProgressTableBO;
import com.bjike.goddess.progressmanage.bo.TableListForHeadBO;
import com.bjike.goddess.progressmanage.dto.ProgressTableDTO;
import com.bjike.goddess.progressmanage.service.ProgressTableSer;
import com.bjike.goddess.progressmanage.to.ProgressTableTO;
import com.bjike.goddess.progressmanage.to.TableHeadValueTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 进度表业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:46 ]
 * @Description: [ 进度表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("progressTableApiImpl")
public class ProgressTableApiImpl implements ProgressTableAPI {

    @Autowired
    private ProgressTableSer progressTableSer;


    @Override
    public List<ProgressTableBO> pageList(ProgressTableDTO dto) throws SerException {
        return progressTableSer.pageList(dto);
    }

    @Override
    public ProgressTableBO add(ProgressTableTO to) throws SerException {
        return progressTableSer.insertModel(to);
    }

    @Override
    public ProgressTableBO edit(ProgressTableTO to) throws SerException {
        return progressTableSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        progressTableSer.delete(id);
    }

    @Override
    public List<TableListForHeadBO> tables(String projectId) throws SerException {
        return progressTableSer.tables(projectId);
    }

    @Override
    public void freeze(String id) throws SerException {
        progressTableSer.freeze(id);
    }

    @Override
    public void unfreeze(String id) throws SerException {
        progressTableSer.unfreeze(id);
    }

}