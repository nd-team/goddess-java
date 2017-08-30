package com.bjike.goddess.balancecard.api;

import com.bjike.goddess.balancecard.bo.IndexTypeSetBO;
import com.bjike.goddess.balancecard.dto.IndexTypeSetDTO;
import com.bjike.goddess.balancecard.service.IndexTypeSetSer;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.balancecard.to.IndexTypeSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 指标类型业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 08:54 ]
 * @Description: [ 指标类型业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("indexTypeSetApiImpl")
public class IndexTypeSetApiImpl implements IndexTypeSetAPI {


    @Autowired
    private IndexTypeSetSer indexTypeSetSer;

    @Override
    public Long countIndexTypeSet(IndexTypeSetDTO indexTypeSetDTO) throws SerException {
        return indexTypeSetSer.countIndexTypeSet( indexTypeSetDTO);
    }

    @Override
    public IndexTypeSetBO getOneById(String id) throws SerException {
        return indexTypeSetSer.getOneById(id);
    }

    @Override
    public List<IndexTypeSetBO> listIndexTypeSet(IndexTypeSetDTO indexTypeSetDTO) throws SerException {
        return indexTypeSetSer.listIndexTypeSet(indexTypeSetDTO);
    }

    @Override
    public IndexTypeSetBO addIndexTypeSet(IndexTypeSetTO indexTypeSetTO) throws SerException {
        return indexTypeSetSer.addIndexTypeSet(indexTypeSetTO);
    }

    @Override
    public IndexTypeSetBO editIndexTypeSet(IndexTypeSetTO indexTypeSetTO) throws SerException {
        return indexTypeSetSer.editIndexTypeSet(indexTypeSetTO);
    }

    @Override
    public void deleteIndexTypeSet(String id) throws SerException {
        indexTypeSetSer.deleteIndexTypeSet(id);
    }

    @Override
    public List<String> listName( ) throws SerException {
        return indexTypeSetSer.listName();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return indexTypeSetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return indexTypeSetSer.guidePermission(guidePermissionTO);
    }
}