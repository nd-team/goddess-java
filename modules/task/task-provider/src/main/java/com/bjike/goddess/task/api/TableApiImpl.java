package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.bo.TableBO;
import com.bjike.goddess.task.dto.TableDTO;
import com.bjike.goddess.task.entity.Table;
import com.bjike.goddess.task.service.TableSer;
import com.bjike.goddess.task.to.TableTO;
import com.bjike.goddess.task.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 表业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("tableApiImpl")
public class TableApiImpl implements TableAPI {
    @Autowired
    private TableSer tableSer;

    @Override
    public void add(TableTO to) throws SerException {
        tableSer.add(to);
    }

    @Override
    public TableBO findById(String id) throws SerException {
        Table table = tableSer.findById(id);
        if(null!=table){
            return BeanTransform.copyProperties(table,TableBO.class);
        }else {
            throw  new SerException("该表不存在");
        }
    }

    @Override
    public void add(List<TableTO> tos) throws SerException {
        tableSer.add(tos);
    }

    @Override
    public void delete(String id) throws SerException {

        tableSer.remove(id);
    }

    @Override
    public List<TableVO> list(TableDTO dto) throws SerException {
        List<Table> tables = tableSer.list(dto);
        return BeanTransform.copyProperties(tables,TableVO.class);
    }
}
