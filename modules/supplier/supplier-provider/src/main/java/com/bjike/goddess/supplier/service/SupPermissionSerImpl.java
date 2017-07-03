package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.*;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.supplier.bo.SupOperateBO;
import com.bjike.goddess.supplier.bo.SupPermissionBO;
import com.bjike.goddess.supplier.dto.SupPermissionDTO;
import com.bjike.goddess.supplier.dto.SupPermissionOperateDTO;
import com.bjike.goddess.supplier.entity.SupPermission;
import com.bjike.goddess.supplier.entity.SupPermissionOperate;
import com.bjike.goddess.supplier.enums.SupPermissionType;
import com.bjike.goddess.supplier.to.SupPermissionTO;
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
 * 供应商权限配置业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-27 10:43 ]
 * @Description: [ 供应商权限配置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class SupPermissionSerImpl extends ServiceImpl<SupPermission, SupPermissionDTO> implements SupPermissionSer {

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
    private SupPermissionOperateSer cusPermissionOperateSer;

    @Override
    public Long countPermission(SupPermissionDTO supPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(supPermissionDTO.getDescription())) {
            supPermissionDTO.getConditions().add(Restrict.like("description", supPermissionDTO.getDescription()));
        }

        Long count = super.count(supPermissionDTO);
        return count;
    }

    @Override
    public List<SupPermissionBO> list(SupPermissionDTO cusPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(cusPermissionDTO.getDescription())) {
            cusPermissionDTO.getConditions().add(Restrict.like("description", cusPermissionDTO.getDescription()));
        }

        List<SupPermission> list = super.findByCis(cusPermissionDTO, true);
        List<SupPermissionBO> bo = new ArrayList<>();
        for (SupPermission str : list) {
            SupPermissionBO temp = BeanTransform.copyProperties(str, SupPermissionBO.class);

            //先查询操作对象
            List<String> idList = new ArrayList<>();
            SupPermissionOperateDTO cpoDTO = new SupPermissionOperateDTO();
            cpoDTO.getConditions().add(Restrict.eq("supPermissionId", temp.getId()));
            List<SupPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
            SupPermissionType type = str.getType();
            List<OpinionBO> opinionBOS = new ArrayList<>();
            List<SupOperateBO> coboList = null;
            if (null != ids && ids.length != 0) {

                if (SupPermissionType.LEVEL.equals(type)) {
                    opinionBOS = arrangementAPI.findByIds(ids);
                } else if (SupPermissionType.MODULE.equals(type)) {
                    opinionBOS = moduleTypeAPI.findByIds(ids);
                } else if (SupPermissionType.POSITION.equals(type)) {
                    opinionBOS = positionDetailAPI.findByIds(ids);
                } else if (SupPermissionType.DEPART.equals(type)) {
                    opinionBOS = departmentDetailAPI.findByIds(ids);
                }

                coboList = new ArrayList<>();
                for (OpinionBO op : opinionBOS) {
                    SupOperateBO cobo = new SupOperateBO();
                    cobo.setId(op.getId());
                    cobo.setOperator(op.getValue());
                    coboList.add(cobo);
                }
            }
            temp.setSupOperateBOs(coboList);

            bo.add(temp);
        }
        return bo;
    }

    @Override
    public SupPermissionBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        SupPermission cusPermission = super.findById(id);

        //先查询操作对象
        List<String> idList = new ArrayList<>();
        SupPermissionOperateDTO cpoDTO = new SupPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("supPermissionId", cusPermission.getId()));
        List<SupPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
        SupPermissionType type = cusPermission.getType();
        List<OpinionBO> opinionBOS = new ArrayList<>();
        List<SupOperateBO> coboList = new ArrayList<>();
        if (null != ids && ids.length != 0) {

            if (SupPermissionType.LEVEL.equals(type)) {
                //根据id数组查询名字和id
                opinionBOS = arrangementAPI.findByIds(ids);
            } else if (SupPermissionType.MODULE.equals(type)) {
                opinionBOS = moduleTypeAPI.findByIds(ids);
            } else if (SupPermissionType.POSITION.equals(type)) {
                opinionBOS = positionDetailAPI.findByIds(ids);
            } else if (SupPermissionType.DEPART.equals(type)) {
                opinionBOS = departmentDetailAPI.findByIds(ids);
            }


            for (OpinionBO op : opinionBOS) {
                SupOperateBO cobo = new SupOperateBO();
                cobo.setId(op.getId());
                cobo.setOperator(op.getValue());
                coboList.add(cobo);
            }
        }
        SupPermissionBO bo = BeanTransform.copyProperties(cusPermission, SupPermissionBO.class);

        bo.setSupOperateBOs(coboList);

        return bo;
    }

    @Override
    public List<OpinionBO> listOperateById(String id) throws SerException {
        List<OpinionBO> list = new ArrayList<>();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        SupPermission cusPermission = super.findById(id);
        SupPermissionType type = cusPermission.getType();
        if (SupPermissionType.LEVEL.equals(type)) {
            list = arrangementAPI.findThawOpinion();
        } else if (SupPermissionType.MODULE.equals(type)) {
            list = moduleTypeAPI.findThawOpinion();
        } else if (SupPermissionType.POSITION.equals(type)) {
            list = positionDetailAPI.findThawOpinion();
        } else if (SupPermissionType.DEPART.equals(type)) {
            list = departmentDetailAPI.findThawOpinion();
        }

        return list;
    }

    @Override
    public SupPermissionBO add(List<SupPermissionTO> cusPermissionTO) throws SerException {
        List<SupPermission> list = BeanTransform.copyProperties(cusPermissionTO, SupPermission.class, true);
        list = list.stream().filter(str -> StringUtils.isNotBlank(str.getIdFlag())).collect(Collectors.toList());
        List<String> idList = list.stream().map(SupPermission::getIdFlag).collect(Collectors.toList());
        String[] ids = idList.toArray(new String[idList.size()]);

        SupPermissionDTO dto = new SupPermissionDTO();
        dto.getConditions().add(Restrict.in("idFlag", ids));
        List<SupPermission> cusPermissionList = super.findByCis(dto);
        List<SupPermission> cusPermissionTempList = new ArrayList<>();
        if (cusPermissionList != null && cusPermissionList.size() > 0) {
            for (int i = 0; i < cusPermissionList.size(); i++) {
                SupPermission temp = cusPermissionList.get(i);
                Optional<SupPermission> cp = list.stream().filter(l -> l.getIdFlag().equals(temp.getIdFlag())).findFirst();
                if (cp.isPresent()) {
                    temp.setDescription(cp.get().getDescription());
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
        List<String> listIdFlag = list.stream().map(SupPermission::getIdFlag).collect(Collectors.toList());
        List<String> databaseIdFlag = cusPermissionList.stream().map(SupPermission::getIdFlag).collect(Collectors.toList());

        List<String> addIdFlag = listIdFlag.stream().filter(str -> !databaseIdFlag.contains(str)).collect(Collectors.toList());

        //传进来的新增的添加
        List<SupPermission> addList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SupPermission cPermission = list.get(i);
            cPermission.setCreateTime(LocalDateTime.now());
            cPermission.setModifyTime(LocalDateTime.now());
            if (addIdFlag.contains(cPermission.getIdFlag())) {
                addList.add(cPermission);
            }
        }
        super.save(addList);
        //没有的删除
        dto = new SupPermissionDTO();
        dto.getConditions().add(Restrict.notIn("idFlag", ids));
        List<SupPermission> deleteList = super.findByCis(dto);
        super.remove(deleteList);

        return new SupPermissionBO();
    }

    @Override
    public SupPermissionBO edit(SupPermissionTO cusPermissionTO) throws SerException {
        if (StringUtils.isBlank(cusPermissionTO.getId())) {
            throw new SerException("id不能为空");
        }

        if (StringUtils.isBlank(cusPermissionTO.getDescription())) {
            throw new SerException("描述不能为空不能为空");
        }
        String[] operators = cusPermissionTO.getOperators();

        SupPermission temp = super.findById(cusPermissionTO.getId());
        temp.setDescription(cusPermissionTO.getDescription());
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        //先删除
        SupPermissionOperateDTO cpoDTO = new SupPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("supPermissionId", temp.getId()));
        List<SupPermissionOperate> deleteList = cusPermissionOperateSer.findByCis(cpoDTO);
        if (deleteList != null && deleteList.size() > 0) {
            cusPermissionOperateSer.remove(deleteList);
        }
        List<SupPermissionOperate> list = new ArrayList<>();
        for (String operateId : operators) {
            SupPermissionOperate cpo = new SupPermissionOperate();
            cpo.setOperator(operateId);
            cpo.setSupPermissionId(temp.getId());
            list.add(cpo);
        }
        cusPermissionOperateSer.save(list);

        return BeanTransform.copyProperties(temp, SupPermissionBO.class);
    }

    @Override
    public Boolean getSupPermission(String idFlag) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //当前用户
        UserBO userBO = userAPI.currentUser();
        String userId = userBO.getId();
        if (StringUtils.isBlank(idFlag)) {
            throw new SerException("idFlag不能为空");
        }

        SupPermissionDTO dto = new SupPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        SupPermission cusPermission = super.findOne(dto);

        //先查询获操作对象
        List<String> idList = new ArrayList<>();
        SupPermissionOperateDTO cpoDTO = new SupPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("supPermissionId", cusPermission.getId()));
        List<SupPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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

        switch (cusPermission.getType()) {
            case LEVEL:
                return positionDetailUserAPI.checkAsUserArrangement(userId, operateIds);
            case DEPART:
                return positionDetailUserAPI.checkAsUserDepartment(userId, operateIds);
            case POSITION:
                return positionDetailUserAPI.checkAsUserPosition(userId, operateIds);
            default://模块
                return positionDetailUserAPI.checkAsUserModule(userId, operateIds);
        }

    }
}