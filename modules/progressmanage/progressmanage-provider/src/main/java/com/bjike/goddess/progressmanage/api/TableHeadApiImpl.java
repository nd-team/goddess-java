package com.bjike.goddess.progressmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.progressmanage.bo.TableHeadBO;
import com.bjike.goddess.progressmanage.dto.ProgressTableDTO;
import com.bjike.goddess.progressmanage.dto.TableHeadDTO;
import com.bjike.goddess.progressmanage.service.TableHeadSer;
import com.bjike.goddess.progressmanage.to.TableHeadTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 进度表表头业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:08 ]
 * @Description: [ 进度表表头业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("tableHeadApiImpl")
public class TableHeadApiImpl implements TableHeadAPI {

    @Autowired
    private TableHeadSer tableHeadSer;

    @Override
    public TableHeadBO add(TableHeadTO to) throws SerException {
        return tableHeadSer.insertModel(to);
    }

    @Override
    public TableHeadBO edit(TableHeadTO to) throws SerException {
        return tableHeadSer.editModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        tableHeadSer.delete(id);
    }

    @Override
    public List<TableHeadBO> pageList(TableHeadDTO dto) throws SerException {
        return tableHeadSer.pageList(dto);
    }
}