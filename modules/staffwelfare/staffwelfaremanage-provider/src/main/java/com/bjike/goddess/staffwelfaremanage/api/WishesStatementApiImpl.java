package com.bjike.goddess.staffwelfaremanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfaremanage.bo.WishesStatementBO;
import com.bjike.goddess.staffwelfaremanage.dto.WishesStatementDTO;
import com.bjike.goddess.staffwelfaremanage.service.WishesStatementSer;
import com.bjike.goddess.staffwelfaremanage.to.WishesStatementTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 祝福语业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 03:21 ]
 * @Description: [ 祝福语业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("wishesStatementApiImpl")
public class WishesStatementApiImpl implements WishesStatementAPI {

    @Autowired
    private WishesStatementSer wishesStatementSer;

    @Override
    public WishesStatementBO addModel(WishesStatementTO to) throws SerException {
        return wishesStatementSer.insertModel(to);
    }

    @Override
    public WishesStatementBO editModel(WishesStatementTO to) throws SerException {
        return wishesStatementSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        wishesStatementSer.remove(id);
    }

    @Override
    public List<WishesStatementBO> pageList(WishesStatementDTO dto) throws SerException {
        return wishesStatementSer.pageList(dto);
    }
}