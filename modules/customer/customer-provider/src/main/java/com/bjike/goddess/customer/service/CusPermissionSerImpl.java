package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.CusPermissionBO;
import com.bjike.goddess.customer.dto.CusPermissionDTO;
import com.bjike.goddess.customer.entity.CusPermission;
import com.bjike.goddess.customer.enums.CusPermissionType;
import com.bjike.goddess.customer.enums.CustomerType;
import com.bjike.goddess.customer.to.CusPermissionTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户权限配置业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 客户权限配置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class CusPermissionSerImpl extends ServiceImpl<CusPermission, CusPermissionDTO> implements CusPermissionSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Override
    public Long countPermission(CusPermissionDTO cusPermissionDTO) throws SerException {
        if(StringUtils.isBlank(cusPermissionDTO.getDescription())){
            cusPermissionDTO.getConditions().add(Restrict.like("description",cusPermissionDTO.getDescription()));
        }
        if(StringUtils.isBlank(cusPermissionDTO.getOperator())) {
            cusPermissionDTO.getConditions().add(Restrict.like("operator", cusPermissionDTO.getOperator()));
        }
        Long count = super.count( cusPermissionDTO );
        return count;
    }

    @Override
    public List<CusPermissionBO> list(CusPermissionDTO cusPermissionDTO) throws SerException {
        if(StringUtils.isBlank(cusPermissionDTO.getDescription())){
            cusPermissionDTO.getConditions().add(Restrict.like("description",cusPermissionDTO.getDescription()));
        }
        if(StringUtils.isBlank(cusPermissionDTO.getOperator())){
            cusPermissionDTO.getConditions().add(Restrict.like("operator",cusPermissionDTO.getOperator()));
        }
        List<CusPermission> list = super.findByCis(cusPermissionDTO,true);
        return BeanTransform.copyProperties(list,CusPermissionBO.class);
    }

    @Override
    public CusPermissionBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        CusPermission cusPermission = super.findById(id);
        return BeanTransform.copyProperties(cusPermission,CusPermissionBO.class);
    }

    @Override
    public List<String> listOperateById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        CusPermission cusPermission = super.findById(id);
        CusPermissionType  type = cusPermission.getType();
        if( type.equals( CusPermissionType.LEVEL )){

        }else if( type.equals( CusPermissionType.MODULE )){

        }else if( type.equals( CusPermissionType.POSITION )){

        }
        //提供接口
//        positionDetailUserAPI

        return null;
    }

    @Override
    public CusPermissionBO add(List<CusPermissionTO> cusPermissionTO) throws SerException {
        List<CusPermission> list = BeanTransform.copyProperties(cusPermissionTO, CusPermission.class,true);
        list = list.stream().filter(str-> StringUtils.isNotBlank(str.getIdFlag())).collect(Collectors.toList());
        List<String> idList = list.stream().map(CusPermission::getIdFlag).collect(Collectors.toList());
        String[] ids = idList.toArray(new String[idList.size()]);

        CusPermissionDTO dto = new CusPermissionDTO();
        dto.getConditions().add(Restrict.in("idFlag",ids));
        List<CusPermission> cusPermissionList = super.findByCis(dto);
        List<CusPermission> cusPermissionTempList = new ArrayList<>();
        if(cusPermissionList!= null && cusPermissionList.size()>0 ){
            for(int i =0 ; i<cusPermissionList.size() ; i++){
                CusPermission temp = cusPermissionList.get(i);
                temp.setDescription(list.get(i).getDescription());
                temp.setType(list.get(i).getType());
                cusPermissionTempList.add( temp);
            }
            super.update( cusPermissionTempList );

        }else{
            super.save( list );
        }

        //传进来的list>cusPermissionList数据库的，则添加
        //list ids cus ids
        List<String> listIdFlag = list.stream().map(CusPermission::getIdFlag).collect(Collectors.toList());
        List<String> databaseIdFlag = cusPermissionList.stream().map(CusPermission::getIdFlag).collect(Collectors.toList());

        List<String> addIdFlag = listIdFlag.stream().filter(str->!databaseIdFlag.contains(str)).collect(Collectors.toList());

        //传进来的新增的添加
        List<CusPermission> addList = new ArrayList<>();
        for(int i=0 ;i<list.size();i++){
            CusPermission cPermission = list.get(i);
            cPermission.setCreateTime(LocalDateTime.now());
            cPermission.setModifyTime(LocalDateTime.now());
            if( addIdFlag.contains(cPermission.getIdFlag())){
                addList.add( cPermission );
            }
        }
        super.save( addList);
        //没有的删除
        dto = new CusPermissionDTO();
        dto.getConditions().add(Restrict.notIn("idFlag",ids));
        List<CusPermission> deleteList = super.findByCis(dto);
        super.remove( deleteList);

        return new CusPermissionBO();
    }

    @Override
    public CusPermissionBO edit(CusPermissionTO cusPermissionTO) throws SerException {
        if(StringUtils.isBlank(cusPermissionTO.getId())){
            throw new SerException("id不能为空");
        }

        if(StringUtils.isBlank(cusPermissionTO.getDescription())){
            throw new SerException("描述不能为空不能为空");
        }
        String [] operators = cusPermissionTO.getOperators();

        CusPermission temp = super.findById( cusPermissionTO.getId() );
        temp.setDescription( cusPermissionTO.getDescription());
        temp.setOperator(String.join(";", operators));
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
        return BeanTransform.copyProperties(temp,CusPermissionBO.class);
    }

    @Override
    public Boolean getCusPermission(String idFlag) throws SerException {
        Boolean flag = false;
        //但前用户
        UserBO userBO = userAPI.currentUser();
        String userId = userBO.getId();
        if(StringUtils.isBlank(idFlag)){
            throw new SerException("idFlag不能为空");
        }
        CusPermissionDTO dto = new CusPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag",idFlag));
        CusPermission cusPermission = super.findOne(dto);
        //获取操作对象
        String operate = cusPermission.getOperator();
        String[] operateIds = null;
        if(StringUtils.isNotBlank(operate)){
            operateIds = operate.split(";");
        }
        //checkAsUserPosition
        //checkAsUserArrangement
        //checkAsUserModule
        Boolean positionFlag = positionDetailUserAPI.checkAsUserPosition(userId,operateIds);
        Boolean arrangementFlag = positionDetailUserAPI.checkAsUserArrangement(userId,operateIds);
        Boolean moduleFlag = positionDetailUserAPI.checkAsUserModule(userId,operateIds);
        if( positionFlag || arrangementFlag || moduleFlag ){
            flag = true;
        }else{
            flag = false;
        }

        return flag;
    }
}