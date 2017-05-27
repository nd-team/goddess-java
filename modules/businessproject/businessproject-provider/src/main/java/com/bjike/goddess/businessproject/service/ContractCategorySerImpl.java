package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.ContractCategoryBO;
import com.bjike.goddess.businessproject.to.ContractCategoryTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businessproject.dto.ContractCategoryDTO;
import com.bjike.goddess.businessproject.entity.ContractCategory;
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

/**
 * 商务项目合同类型业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 04:24 ]
 * @Description: [ 商务项目合同类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectSerCache")
@Service
public class ContractCategorySerImpl extends ServiceImpl<ContractCategory, ContractCategoryDTO> implements ContractCategorySer {

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
    public Long countContractCategory(ContractCategoryDTO contractCategoryDTO) throws SerException {
        Long count = super.count( contractCategoryDTO );
        return count;
    }
    @Override
    public ContractCategoryBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能呢为空");
        }
        ContractCategory contractCategory = super.findById(id);
        return BeanTransform.copyProperties(contractCategory, ContractCategoryBO.class );
    }

    @Override
    public List<ContractCategoryBO> listContractCategory(ContractCategoryDTO contractCategoryDTO) throws SerException {
        checkSeeIdentity();

        List<ContractCategory> list = super.findByPage(contractCategoryDTO);
        List<ContractCategoryBO> contractCategoryBOS = BeanTransform.copyProperties(list, ContractCategoryBO.class);
        return contractCategoryBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContractCategoryBO addContractCategory(ContractCategoryTO contractCategoryTO) throws SerException {
        checkAddIdentity();

        ContractCategory contractCategory = BeanTransform.copyProperties(contractCategoryTO,ContractCategory.class,true);
        contractCategory.setCreateTime(LocalDateTime.now());

        super.save( contractCategory );
        ContractCategoryBO bo = BeanTransform.copyProperties( contractCategory , ContractCategoryBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContractCategoryBO editContractCategory(ContractCategoryTO contractCategoryTO) throws SerException {
        checkAddIdentity();

        ContractCategory temp = super.findById( contractCategoryTO.getId());

        ContractCategory contractCategory = BeanTransform.copyProperties(contractCategoryTO,ContractCategory.class,true);
        BeanUtils.copyProperties( contractCategory , temp, "id","createTime");
        temp.setModifyTime(LocalDateTime.now());

        super.update( temp );
        ContractCategoryBO bo = BeanTransform.copyProperties( temp , ContractCategoryBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteContractCategory(String id) throws SerException {
        checkAddIdentity();

        super.remove(id);
    }
}