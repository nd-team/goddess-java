package com.bjike.goddess.festival.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.bo.CompanyFestivalTimeBO;
import com.bjike.goddess.festival.dto.CompanyFestivalTimeDTO;
import com.bjike.goddess.festival.dto.CompanyFestivalTimeDTO;
import com.bjike.goddess.festival.entity.CompanyFestivalTime;
import com.bjike.goddess.festival.entity.CompanyFestivalTime;
import com.bjike.goddess.festival.to.CompanyFestivalTimeTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 公司放假时间安排业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 08:10 ]
 * @Description: [ 公司放假时间安排业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "festivalSerCache")
@Service
public class CompanyFestivalTimeSerImpl extends ServiceImpl<CompanyFestivalTime, CompanyFestivalTimeDTO> implements CompanyFestivalTimeSer {

    @Override
    public Long countCompanyFestivalTime(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws SerException {
        return super.count( companyFestivalTimeDTO );
    }

    @Override
    public List<CompanyFestivalTimeBO> listCompanyFestivalTime(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws SerException {

        companyFestivalTimeDTO.getSorts().add("createTime=desc");
        List<CompanyFestivalTime> list = super.findByCis(companyFestivalTimeDTO);

        return BeanTransform.copyProperties(list, CompanyFestivalTimeBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyFestivalTimeBO addCompanyFestivalTime(CompanyFestivalTimeTO companyFestivalTimeTO) throws SerException {
        CompanyFestivalTimeDTO dto = new CompanyFestivalTimeDTO();
        dto.getConditions().add(Restrict.eq("name",companyFestivalTimeTO.getName()));
        Long count = super.count( dto );
        if( count >0 ){
            throw new SerException("已存在一条该节日名称，节日名称不能相同,添加失败");
        }
        CompanyFestivalTime companyFestivalTime = BeanTransform.copyProperties(companyFestivalTimeTO,CompanyFestivalTime.class,true);
        companyFestivalTime.setCreateTime(LocalDateTime.now());
        super.save( companyFestivalTime );
        return BeanTransform.copyProperties(companyFestivalTime, CompanyFestivalTimeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyFestivalTimeBO editCompanyFestivalTime(CompanyFestivalTimeTO companyFestivalTimeTO) throws SerException {
        CompanyFestivalTimeDTO dto = new CompanyFestivalTimeDTO();
        dto.getConditions().add(Restrict.eq("name",companyFestivalTimeTO.getName()));
        Long count = super.count( dto );
        if( count >0 ){
            throw new SerException("已存在一条该节日名称，节日名称不能相同,编辑失败");
        }

        CompanyFestivalTime companyFestivalTime = BeanTransform.copyProperties(companyFestivalTimeTO,CompanyFestivalTime.class,true);
        CompanyFestivalTime temp = super.findById( companyFestivalTimeTO.getId() );

        BeanUtils.copyProperties(companyFestivalTime,temp,"id","createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update( temp );
        return BeanTransform.copyProperties(companyFestivalTime, CompanyFestivalTimeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCompanyFestivalTime(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<String> listFestivalName() throws SerException {
        String[] fields = new String[]{"name"};
        List<CompanyFestivalTimeBO> companyFestivalTimeBOS = super.findBySql(
                "select name ,1 from festival_companyfestivaltime  order by createTime desc ", CompanyFestivalTimeBO.class, fields);

        List<String> list = companyFestivalTimeBOS.stream().map(CompanyFestivalTimeBO::getName)
                .filter(name -> (name != null || !"".equals(name.trim()))).distinct().collect(Collectors.toList());


        return list;
    }

    @Override
    public CompanyFestivalTimeBO getCompanyFestivalTime(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws SerException {
        if( StringUtils.isBlank(companyFestivalTimeDTO.getName() )){
            throw  new SerException("节日名不能为空");
        }
        companyFestivalTimeDTO.getConditions().add(Restrict.eq("name",companyFestivalTimeDTO.getName()));
        CompanyFestivalTime companyFestivalTime = super.findOne( companyFestivalTimeDTO );
        CompanyFestivalTimeBO cb = BeanTransform.copyProperties(companyFestivalTime,CompanyFestivalTimeBO.class);
        return cb;
    }
}