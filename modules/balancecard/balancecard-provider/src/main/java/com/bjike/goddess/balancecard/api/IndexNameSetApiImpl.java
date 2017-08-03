package com.bjike.goddess.balancecard.api;

import com.bjike.goddess.balancecard.bo.IndexNameSetBO;
import com.bjike.goddess.balancecard.dto.IndexNameSetDTO;
import com.bjike.goddess.balancecard.service.IndexNameSetSer;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.balancecard.to.IndexNameSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 指标名称设置业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:02 ]
 * @Description: [ 指标名称设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("indexNameSetApiImpl")
public class IndexNameSetApiImpl implements IndexNameSetAPI {


    @Autowired
    private IndexNameSetSer indexNameSetSer;

    @Override
    public Long countIndexNameSet(IndexNameSetDTO indexNameSetDTO) throws SerException {
        return indexNameSetSer.countIndexNameSet( indexNameSetDTO);
    }

    @Override
    public IndexNameSetBO getOneById(String id) throws SerException {
        return indexNameSetSer.getOneById(id);
    }

    @Override
    public List<IndexNameSetBO> listIndexNameSet(IndexNameSetDTO indexNameSetDTO) throws SerException {
        return indexNameSetSer.listIndexNameSet(indexNameSetDTO);
    }

    @Override
    public IndexNameSetBO addIndexNameSet(IndexNameSetTO indexNameSetTO) throws SerException {
        return indexNameSetSer.addIndexNameSet(indexNameSetTO);
    }

    @Override
    public IndexNameSetBO editIndexNameSet(IndexNameSetTO indexNameSetTO) throws SerException {
        return indexNameSetSer.editIndexNameSet(indexNameSetTO);
    }

    @Override
    public void deleteIndexNameSet(String id) throws SerException {
        indexNameSetSer.deleteIndexNameSet(id);
    }


    @Override
    public List<String> listName( ) throws SerException {
        return indexNameSetSer.listName();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return indexNameSetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return indexNameSetSer.guidePermission(guidePermissionTO);
    }
}