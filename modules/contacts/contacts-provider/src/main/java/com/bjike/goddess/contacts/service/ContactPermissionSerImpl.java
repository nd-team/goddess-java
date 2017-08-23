package com.bjike.goddess.contacts.service;

import com.bjike.goddess.contacts.bo.ContactOperateBO;
import com.bjike.goddess.contacts.bo.ContactPermissionBO;
import com.bjike.goddess.contacts.dto.ContactPermissionDTO;
import com.bjike.goddess.contacts.dto.ContactPermissionOperateDTO;
import com.bjike.goddess.contacts.entity.ContactPermission;
import com.bjike.goddess.contacts.entity.ContactPermissionOperate;
import com.bjike.goddess.contacts.enums.ContactPermissionType;
import com.bjike.goddess.contacts.to.ContactPermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.*;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
@CacheConfig(cacheNames = "contactsSerCache")
@Service
public class ContactPermissionSerImpl extends ServiceImpl<ContactPermission, ContactPermissionDTO> implements ContactPermissionSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private ArrangementAPI arrangementAPI;
    @Autowired
    private ModuleTypeAPI moduleTypeAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private ContactPermissionOperateSer cusPermissionOperateSer;

    @Override
    public Long countPermission(ContactPermissionDTO cusPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(cusPermissionDTO.getDescription())) {
            cusPermissionDTO.getConditions().add(Restrict.like("description", cusPermissionDTO.getDescription()));
        }

        Long count = super.count(cusPermissionDTO);
        return count;
    }

    @Override
    public List<ContactPermissionBO> list(ContactPermissionDTO cusPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(cusPermissionDTO.getDescription())) {
            cusPermissionDTO.getConditions().add(Restrict.like("description", cusPermissionDTO.getDescription()));
        }

        List<ContactPermission> list = super.findByCis(cusPermissionDTO, true);
        List<ContactPermissionBO> bo = new ArrayList<>();
        for (ContactPermission str : list) {
            ContactPermissionBO temp = BeanTransform.copyProperties(str, ContactPermissionBO.class);

            //先查询操作对象
            List<String> idList = new ArrayList<>();
            ContactPermissionOperateDTO cpoDTO = new ContactPermissionOperateDTO();
            cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", temp.getId()));
            List<ContactPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
            if (operateList != null && operateList.size() > 0) {
                operateList.stream().forEach(op -> {
                    idList.add(op.getOperator());
                });
            }
            //操作对象list转String[]
            String[] ids = null;
            if (null != idList && idList.size() > 0) {
                ids = new String[idList.size()];
                for (int i = 0; i < idList.size(); i++) {
                    ids[i] = idList.get(i);
                }

            }
            ContactPermissionType type = str.getType();
            List<OpinionBO> opinionBOS = new ArrayList<>();
            List<ContactOperateBO> coboList = null;
            if (null != ids && ids.length != 0) {

                if (ContactPermissionType.LEVEL.equals(type)) {
                    opinionBOS = arrangementAPI.findByIds(ids);
                } else if (ContactPermissionType.MODULE.equals(type)) {
                    opinionBOS = moduleTypeAPI.findByIds(ids);
                } else if (ContactPermissionType.POSITION.equals(type)) {
                    opinionBOS = positionDetailAPI.findByIds(ids);
                } else if (ContactPermissionType.DEPART.equals(type)) {
                    opinionBOS = departmentDetailAPI.findByIds(ids);
                }

                coboList = new ArrayList<>();
                for (OpinionBO op : opinionBOS) {
                    ContactOperateBO cobo = new ContactOperateBO();
                    cobo.setId(op.getId());
                    cobo.setOperator(op.getValue());
                    coboList.add(cobo);
                }
            }
            temp.setCusOperateBO(coboList);

            bo.add(temp);
        }
        return bo;
    }

    @Override
    public ContactPermissionBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        ContactPermission cusPermission = super.findById(id);


        //先查询操作对象
        List<String> idList = new ArrayList<>();
        ContactPermissionOperateDTO cpoDTO = new ContactPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<ContactPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
        if (operateList != null && operateList.size() > 0) {
            operateList.stream().forEach(op -> {
                idList.add(op.getOperator());
            });
        }

        String[] ids = null;
        if (null != idList && idList.size() > 0) {
            ids = new String[idList.size()];
            for (int i = 0; i < idList.size(); i++) {
                ids[i] = idList.get(i);
            }

        }
        ContactPermissionType type = cusPermission.getType();
        List<OpinionBO> opinionBOS = new ArrayList<>();
        List<ContactOperateBO> coboList = new ArrayList<>();
        if (null != ids && ids.length != 0) {

            if (ContactPermissionType.LEVEL.equals(type)) {
                //根据id数组查询名字和id
                opinionBOS = arrangementAPI.findByIds(ids);
            } else if (ContactPermissionType.MODULE.equals(type)) {
                opinionBOS = moduleTypeAPI.findByIds(ids);
            } else if (ContactPermissionType.POSITION.equals(type)) {
                opinionBOS = positionDetailAPI.findByIds(ids);
            } else if (ContactPermissionType.DEPART.equals(type)) {
                opinionBOS = departmentDetailAPI.findByIds(ids);
            }


            for (OpinionBO op : opinionBOS) {
                ContactOperateBO cobo = new ContactOperateBO();
                cobo.setId(op.getId());
                cobo.setOperator(op.getValue());
                coboList.add(cobo);
            }
        }
        ContactPermissionBO bo = BeanTransform.copyProperties(cusPermission, ContactPermissionBO.class);

        bo.setCusOperateBO(coboList);

        return bo;
    }

    @Override
    public List<OpinionBO> listOperateById(String id) throws SerException {
        List<OpinionBO> list = new ArrayList<>();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        ContactPermission cusPermission = super.findById(id);
        ContactPermissionType type = cusPermission.getType();
        if (ContactPermissionType.LEVEL.equals(type)) {
            list = arrangementAPI.findThawOpinion();
        } else if (ContactPermissionType.MODULE.equals(type)) {
            list = moduleTypeAPI.findThawOpinion();
        } else if (ContactPermissionType.POSITION.equals(type)) {
            list = positionDetailAPI.findThawOpinion();
        } else if (ContactPermissionType.DEPART.equals(type)) {
            //TODO 部门查询
            list = departmentDetailAPI.findThawOpinion();
        }

        return list;
    }

    @Override
    public ContactPermissionBO add(List<ContactPermissionTO> cusPermissionTO) throws SerException {
        List<ContactPermission> list = BeanTransform.copyProperties(cusPermissionTO, ContactPermission.class, true);
        list = list.stream().filter(str -> StringUtils.isNotBlank(str.getIdFlag())).collect(Collectors.toList());
        List<String> idList = list.stream().map(ContactPermission::getIdFlag).collect(Collectors.toList());
        String[] ids = idList.toArray(new String[idList.size()]);

        ContactPermissionDTO dto = new ContactPermissionDTO();
        dto.getConditions().add(Restrict.in("idFlag", ids));
        List<ContactPermission> cusPermissionList = super.findByCis(dto);
        List<ContactPermission> cusPermissionTempList = new ArrayList<>();
        if (cusPermissionList != null && cusPermissionList.size() > 0) {
            for (int i = 0; i < cusPermissionList.size(); i++) {
                ContactPermission temp = cusPermissionList.get(i);
                Optional<ContactPermission> cp = list.stream().filter(l -> l.getIdFlag().equals(temp.getIdFlag())).findFirst();
                if (cp.isPresent()) {
                    if (StringUtils.isBlank(temp.getDescription())) {
                        temp.setDescription(cp.get().getDescription());
                    }
                    temp.setType(cp.get().getType());
                    cusPermissionTempList.add(temp);
                }
            }
            super.update(cusPermissionTempList);

        } else {
            super.save(list);
        }

        //传进来的list>cusPermissionList数据库的，则添加
        //list ids cus ids
        List<String> listIdFlag = list.stream().map(ContactPermission::getIdFlag).collect(Collectors.toList());
        List<String> databaseIdFlag = cusPermissionList.stream().map(ContactPermission::getIdFlag).collect(Collectors.toList());

        List<String> addIdFlag = listIdFlag.stream().filter(str -> !databaseIdFlag.contains(str)).collect(Collectors.toList());

        //传进来的新增的添加
        List<ContactPermission> addList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ContactPermission cPermission = list.get(i);
            cPermission.setCreateTime(LocalDateTime.now());
            cPermission.setModifyTime(LocalDateTime.now());
            if (addIdFlag.contains(cPermission.getIdFlag())) {
                addList.add(cPermission);
            }
        }
        super.save(addList);
        //没有的删除
        dto = new ContactPermissionDTO();
        dto.getConditions().add(Restrict.notIn("idFlag", ids));
        List<ContactPermission> deleteList = super.findByCis(dto);
        super.remove(deleteList);

        return new ContactPermissionBO();
    }

    @Override
    public ContactPermissionBO edit(ContactPermissionTO cusPermissionTO) throws SerException {
        if (StringUtils.isBlank(cusPermissionTO.getId())) {
            throw new SerException("id不能为空");
        }

        if (StringUtils.isBlank(cusPermissionTO.getDescription())) {
            throw new SerException("描述不能为空不能为空");
        }
        String[] operators = cusPermissionTO.getOperators();

        ContactPermission temp = super.findById(cusPermissionTO.getId());
        temp.setDescription(cusPermissionTO.getDescription());
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        //先删除
        ContactPermissionOperateDTO cpoDTO = new ContactPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", temp.getId()));
        List<ContactPermissionOperate> deleteList = cusPermissionOperateSer.findByCis(cpoDTO);
        if (deleteList != null && deleteList.size() > 0) {
            cusPermissionOperateSer.remove(deleteList);
        }
        if (  operators!= null && operators.length>0 ) {
            List<ContactPermissionOperate> list = new ArrayList<>();
            for (String operateId : operators) {
                ContactPermissionOperate cpo = new ContactPermissionOperate();
                cpo.setOperator(operateId);
                cpo.setCuspermissionId(temp.getId());
                list.add(cpo);
            }
            cusPermissionOperateSer.save(list);
        }

        return BeanTransform.copyProperties(temp, ContactPermissionBO.class);
    }

    @Override
    public Boolean getCusPermission(String idFlag) throws SerException {
        String ss = RpcTransmit.getUserToken();
        Boolean flag = false;
        //但前用户
        UserBO userBO = userAPI.currentUser();
        String userId = userBO.getId();
        if (StringUtils.isBlank(idFlag)) {
            throw new SerException("idFlag不能为空");
        }
        ContactPermissionDTO dto = new ContactPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        ContactPermission cusPermission = super.findOne(dto);

        //先查询获操作对象
        List<String> idList = new ArrayList<>();
        ContactPermissionOperateDTO cpoDTO = new ContactPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<ContactPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
        if (operateList != null && operateList.size() > 0) {
            operateList.stream().forEach(op -> {
                idList.add(op.getOperator());
            });
        }


        String[] operateIds = null;
        if (null != idList && idList.size() > 0) {
            operateIds = new String[idList.size()];
            for (int i = 0; i < idList.size(); i++) {
                operateIds[i] = idList.get(i);
            }

        }

        //checkAsUserPosition
        //checkAsUserArrangement
        //checkAsUserModule
//        Boolean positionFlag = positionDetailUserAPI.checkAsUserPosition(userId, operateIds);
//        Boolean arrangementFlag = positionDetailUserAPI.checkAsUserArrangement(userId, operateIds);
//        Boolean moduleFlag = positionDetailUserAPI.checkAsUserModule(userId, operateIds);
        Boolean depart = positionDetailUserAPI.checkAsUserDepartment(userId, operateIds);


        //TODO 部门
        if (  depart) {
            flag = true;
        } else {
            flag = false;
        }


        return flag;
    }

    @Override
    public Boolean busCusPermission(String idFlag) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flag = false;
        //但前用户
        UserBO userBO = userAPI.currentUser();
        String userId = userBO.getId();
        if (StringUtils.isBlank(idFlag)) {
            throw new SerException("idFlag不能为空");
        }
        ContactPermissionDTO dto = new ContactPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        ContactPermission cusPermission = super.findOne(dto);


        //先查询操作对象
        List<String> idList = new ArrayList<>();
        ContactPermissionOperateDTO cpoDTO = new ContactPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<ContactPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
        if (operateList != null && operateList.size() > 0) {
            operateList.stream().forEach(op -> {
                idList.add(op.getOperator());
            });
        }
        String[] operateIds = null;
        if (null != idList && idList.size() > 0) {
            operateIds = new String[idList.size()];
            for (int i = 0; i < idList.size(); i++) {
                operateIds[i] = idList.get(i);
            }

        }


        //TODO 部门id 商务部
//        Boolean moduleFlag = positionDetailUserAPI.checkAsUserModule(userId,operateIds);
        Boolean moduleFlag = positionDetailUserAPI.checkAsUserDepartment(userId, operateIds);
//        Boolean positionFlag = positionDetailUserAPI.checkAsUserPosition(userId, operateIds);

        if (moduleFlag) {
            flag = true;
        } else {
            flag = false;
        }
        RpcTransmit.transmitUserToken(userToken);
        String aa = RpcTransmit.getUserToken();
        return flag;
    }
}