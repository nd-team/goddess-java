package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.DispatchSheetBO;
import com.bjike.goddess.businessproject.to.DispatchSheetTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businessproject.dto.DispatchSheetDTO;
import com.bjike.goddess.businessproject.entity.DispatchSheet;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商务项目派工单信息管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 10:06 ]
 * @Description: [ 商务项目派工单信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectSerCache")
@Service
public class DispatchSheetSerImpl extends ServiceImpl<DispatchSheet, DispatchSheetDTO> implements DispatchSheetSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException{
        Boolean flag = cusPermissionSer.getCusPermission("1");
        if( !flag ){
            throw new SerException("您不是相应部门的人员，不可以查看");
        }
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException{
        Boolean flag = cusPermissionSer.busCusPermission("2");
        if( !flag ){
            throw new SerException("您不是岗位的人员，不可以操作");
        }
    }


    @Override
    public Long countDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        searchCondition( dispatchSheetDTO);

        Long count = super.count( dispatchSheetDTO );
        return count;
    }

    @Override
    public DispatchSheetBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能呢为空");
        }
        DispatchSheet dispatchSheet = super.findById(id);
        return BeanTransform.copyProperties(dispatchSheet, DispatchSheetBO.class );
    }
    @Override
    public List<DispatchSheetBO> listDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        checkSeeIdentity();

        searchCondition( dispatchSheetDTO);
        List<DispatchSheet> list = super.findByPage(dispatchSheetDTO);
        List<DispatchSheetBO> dispatchSheetBOList = BeanTransform.copyProperties(list, DispatchSheetBO.class);
        return dispatchSheetBOList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DispatchSheetBO addDispatchSheet(DispatchSheetTO dispatchSheetTO) throws SerException {
        checkAddIdentity();

        DispatchSheet dispatchSheet = BeanTransform.copyProperties(dispatchSheetTO, DispatchSheet.class, true);
        dispatchSheet.setCreateTime(LocalDateTime.now());
        super.save(dispatchSheet);

        DispatchSheetBO dispatchSheetBO = BeanTransform.copyProperties(dispatchSheet, DispatchSheetBO.class);

        return dispatchSheetBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DispatchSheetBO editDispatchSheet(DispatchSheetTO dispatchSheetTO) throws SerException {
        checkAddIdentity();

        DispatchSheet temp = super.findById( dispatchSheetTO.getId());

        DispatchSheet dispatchSheet = BeanTransform.copyProperties(dispatchSheetTO, DispatchSheet.class, true);
        BeanUtils.copyProperties( dispatchSheet , temp ,"id","createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        DispatchSheetBO dispatchSheetBO = BeanTransform.copyProperties(temp, DispatchSheetBO.class);

        return dispatchSheetBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteDispatchSheet(String id) throws SerException {
        checkAddIdentity();

        super.remove(id);
    }


    public void searchCondition(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        /**
         * 业务类型
         */
        if (dispatchSheetDTO.getBusinessType() != null) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("businessType", dispatchSheetDTO.getBusinessType()));
        }
        /**
         * 业务方向科目
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getBusinessSubject())) {
            dispatchSheetDTO.getConditions().add(Restrict.like("businessSubject", dispatchSheetDTO.getBusinessSubject()));
        }
        /**
         * 合作方式
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getBusinessCooperate())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("businessCooperate", dispatchSheetDTO.getBusinessCooperate()));
        }
        /**
         * 总包单位名称
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getMajorCompany())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("majorCompany", dispatchSheetDTO.getMajorCompany()));
        }
        /**
         * 分包单位名称
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getSubCompany())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("subCompany", dispatchSheetDTO.getSubCompany()));
        }
        /**
         * 地区
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getArea())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("area", dispatchSheetDTO.getArea()));
        }
        /**
         * 派工单名称
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getDispatchProject())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("dispatchProject", dispatchSheetDTO.getDispatchProject()));
        }
        /**
         * 派工单编号
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getDispatchNum())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("dispatchNum", dispatchSheetDTO.getDispatchNum()));
        }

    }

    @Override
    public List<String> listArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<DispatchSheetBO> dispatchSheetBOList =super.findBySql("select area from businessproject_dispatchsheet order by area asc ", DispatchSheetBO.class, fields);

        List<String> areaList  = dispatchSheetBOList.stream().map(DispatchSheetBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim())) ).distinct().collect(Collectors.toList());


        return areaList;
    }

    @Override
    public List<String> listDispatchName() throws SerException {
        String[] fields = new String[]{"dispatchProject"};
        List<DispatchSheetBO> dispatchSheetBOList =super.findBySql("select dispatchProject from businessproject_dispatchsheet order by area asc ", DispatchSheetBO.class, fields);

        List<String> dispatchProjectList  = dispatchSheetBOList.stream().map(DispatchSheetBO::getDispatchProject)
                .filter(str -> (str != null || !"".equals(str.trim())) ).distinct().collect(Collectors.toList());


        return dispatchProjectList;
    }
}