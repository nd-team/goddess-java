package com.bjike.goddess.announcement.api;

import com.bjike.goddess.announcement.bo.ClassBO;
import com.bjike.goddess.announcement.dto.ClassDTO;
import com.bjike.goddess.announcement.service.ClassSer;
import com.bjike.goddess.announcement.to.ClassTO;
import com.bjike.goddess.announcement.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 公告分类业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-07 02:20 ]
 * @Description: [ 公告分类业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("classApiImpl")
public class ClassApiImpl implements ClassAPI {
    @Autowired
    private ClassSer classSer;

    @Override
    public List<ClassBO> list(ClassDTO dto) throws SerException {
        return classSer.list(dto);
    }

    @Override
    public ClassBO save(ClassTO to) throws SerException {
        return classSer.save(to);
    }

    @Override
    public void edit(ClassTO to) throws SerException {
        classSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        classSer.delete(id);
    }

    @Override
    public ClassBO findByID(String id) throws SerException {
        return classSer.findByID(id);
    }

    @Override
    public Long count(ClassDTO dto) throws SerException {
        return classSer.count(dto);
    }

    @Override
    public Set<String> allClass() throws SerException {
        return classSer.allClass();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return classSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return classSer.guidePermission(guidePermissionTO);
    }
}