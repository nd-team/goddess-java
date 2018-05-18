package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.SchemeImplementBO;
import com.bjike.goddess.interiorrecommend.dto.SchemeImplementDTO;
import com.bjike.goddess.interiorrecommend.service.SchemeImplementSer;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.SchemeImplementTO;
import com.bjike.goddess.staffentry.bo.EntryRegisterBO;
import com.bjike.goddess.staffentry.dto.EntryRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 内部推荐方案实施业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 11:29 ]
* @Description:	[ 内部推荐方案实施业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("schemeImplementApiImpl")
public class SchemeImplementApiImpl implements SchemeImplementAPI  {

    @Autowired
    private SchemeImplementSer schemeImplementSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return schemeImplementSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return schemeImplementSer.guidePermission(guidePermissionTO);
    }

    @Override
    public void add(SchemeImplementTO to) throws SerException {
        schemeImplementSer.add(to);
    }

    @Override
    public void delete(String id) throws SerException {
        schemeImplementSer.delete(id);
    }

    @Override
    public void modify(SchemeImplementTO to) throws SerException {
        schemeImplementSer.modify(to);
    }

    @Override
    public List<SchemeImplementBO> pageList(SchemeImplementDTO dto) throws SerException {
        return schemeImplementSer.pageList(dto);
    }

    @Override
    public Long count(SchemeImplementDTO dto) throws SerException {
        return schemeImplementSer.count(dto);
    }

    @Override
    public SchemeImplementBO findOne(String id) throws SerException {
        return schemeImplementSer.findOne(id);
    }

    @Override
    public Integer findAward(String type, String recommendPosition) throws SerException {
        return schemeImplementSer.findAward(type,recommendPosition);
    }

    @Override
    public String findPosition(String type) throws SerException {
        return schemeImplementSer.findPosition(type);
    }

    @Override
    public List<EntryRegisterBO> findEntry(EntryRegisterDTO dto) throws SerException {
        return schemeImplementSer.findEntry ( dto );
    }
}