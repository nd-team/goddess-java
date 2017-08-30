package com.bjike.goddess.progressmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.progressmanage.bo.TableHeadForValueBO;
import com.bjike.goddess.progressmanage.bo.TableHeadRowSignBO;
import com.bjike.goddess.progressmanage.dto.TableHeadRowSignDTO;
import com.bjike.goddess.progressmanage.dto.TableHeadValueDTO;
import com.bjike.goddess.progressmanage.service.TableHeadRowSignSer;
import com.bjike.goddess.progressmanage.to.TableHeadRowSignTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 进度表表头对应值的行标记业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-20 03:27 ]
 * @Description: [ 进度表表头对应值的行标记业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("tableHeadRowSignApiImpl")
public class TableHeadRowSignApiImpl implements TableHeadRowSignAPI {

    @Autowired
    private TableHeadRowSignSer tableHeadRowSignSer;

    @Override
    public List<TableHeadForValueBO> heads(TableHeadRowSignDTO dto) throws SerException {
        return tableHeadRowSignSer.heads(dto);
    }

    @Override
    public void add(TableHeadRowSignTO to) throws SerException {
        tableHeadRowSignSer.insertModel(to);
    }

    @Override
    public void edit(TableHeadRowSignTO to) throws SerException {
        tableHeadRowSignSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        tableHeadRowSignSer.delete(id);
    }

    @Override
    public List<TableHeadRowSignBO> pageList(TableHeadRowSignDTO dto) throws SerException {
        return tableHeadRowSignSer.pageList(dto);
    }
}