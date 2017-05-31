package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.ExamWaySetBO;
import com.bjike.goddess.balancecard.dto.ExamWaySetDTO;
import com.bjike.goddess.balancecard.entity.ExamWaySet;
import com.bjike.goddess.balancecard.to.ExamWaySetTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.balancecard.dto.ExamWaySetDTO;
import com.bjike.goddess.balancecard.entity.ExamWaySet;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 考核方式设置业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:01 ]
 * @Description: [ 考核方式设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "balancecardSerCache")
@Service
public class ExamWaySetSerImpl extends ServiceImpl<ExamWaySet, ExamWaySetDTO> implements ExamWaySetSer {


    @Autowired
    private UserAPI userAPI;

    @Override
    public Long countExamWaySet(ExamWaySetDTO examWaySetDTO) throws SerException {
        if (StringUtils.isNotBlank(examWaySetDTO.getTypeName())) {
            examWaySetDTO.getConditions().add(Restrict.like("typeName", examWaySetDTO.getTypeName()));
        }
        if (StringUtils.isNotBlank(examWaySetDTO.getPerson())) {
            examWaySetDTO.getConditions().add(Restrict.like("person", examWaySetDTO.getPerson()));
        }
        Long count = super.count(examWaySetDTO);
        return count;
    }

    @Override
    public ExamWaySetBO getOneById(String id) throws SerException {

        return null;
    }

    @Override
    public List<ExamWaySetBO> listExamWaySet(ExamWaySetDTO examWaySetDTO) throws SerException {
        if (StringUtils.isNotBlank(examWaySetDTO.getTypeName())) {
            examWaySetDTO.getConditions().add(Restrict.like("typeName", examWaySetDTO.getTypeName()));
        }
        if (StringUtils.isNotBlank(examWaySetDTO.getPerson())) {
            examWaySetDTO.getConditions().add(Restrict.like("person", examWaySetDTO.getPerson()));
        }
        List<ExamWaySet> list = super.findByCis(examWaySetDTO, true);
        List<ExamWaySetBO> boList = BeanTransform.copyProperties(list, ExamWaySetBO.class);
        return boList;
    }

    @Override
    public ExamWaySetBO addExamWaySet(ExamWaySetTO examWaySetTO) throws SerException {
        if (StringUtils.isBlank(examWaySetTO.getTypeName())) {
            throw new SerException("考核方式不能为空");
        }
        ExamWaySet temp = BeanTransform.copyProperties( examWaySetTO , ExamWaySet.class,true);
        temp.setPerson( userAPI.currentUser().getUsername() );
        temp.setCreateTime(LocalDateTime.now());
        temp.setModifyTime(LocalDateTime.now());
        super.save( temp );
        return BeanTransform.copyProperties( temp , ExamWaySetBO.class);
    }

    @Override
    public ExamWaySetBO editExamWaySet(ExamWaySetTO examWaySetTO) throws SerException {
        if( StringUtils.isBlank(examWaySetTO.getId())){
            throw new SerException("失败，id不能为空");
        }if( StringUtils.isBlank(examWaySetTO.getTypeName())){
            throw new SerException("失败，考核方式不能为空");
        }
        ExamWaySet temp = super.findById( examWaySetTO.getId() );
        temp.setTypeName( examWaySetTO.getTypeName());
        temp.setDescribtion( examWaySetTO.getDescribtion() );
        temp.setModifyTime(LocalDateTime.now());
        super.update( temp );
        return BeanTransform.copyProperties( temp , ExamWaySetBO.class);
    }

    @Override
    public void deleteExamWaySet(String id) throws SerException {
        if( StringUtils.isBlank( id )){
            throw new SerException("失败，id不能为空");
        }
        super.remove( id );
    }

    @Override
    public List<String> listName() throws SerException {
        List<String> list = new ArrayList<>();
        String[] field = new String[]{"typeName"};
        String sql = "select typeName from balancecard_examwayset group by typeName order by typeName desc ";
        List<ExamWaySetBO> listBO = super.findBySql(sql , ExamWaySetBO.class,field);
        list = listBO.stream().filter(str->StringUtils.isNotBlank(str.getTypeName())).map(ExamWaySetBO::getTypeName).collect(Collectors.toList());
        return list;
    }
    
}