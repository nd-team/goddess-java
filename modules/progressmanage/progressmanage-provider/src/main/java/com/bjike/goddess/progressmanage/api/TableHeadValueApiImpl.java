package com.bjike.goddess.progressmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.progressmanage.bo.TableHeadForValueBO;
import com.bjike.goddess.progressmanage.bo.TableHeadValueBO;
import com.bjike.goddess.progressmanage.dto.TableHeadValueDTO;
import com.bjike.goddess.progressmanage.service.TableHeadValueSer;
import com.bjike.goddess.progressmanage.to.TableHeadValueTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 进度表表头对应Value业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-19 04:48 ]
 * @Description: [ 进度表表头对应Value业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("tableHeadValueApiImpl")
public class TableHeadValueApiImpl implements TableHeadValueAPI {

    @Autowired
    private TableHeadValueSer tableHeadValueSer;

    @Override
    public List<TableHeadValueBO> pageList(TableHeadValueDTO dto) throws SerException {
        return tableHeadValueSer.pageList(dto);
    }

}