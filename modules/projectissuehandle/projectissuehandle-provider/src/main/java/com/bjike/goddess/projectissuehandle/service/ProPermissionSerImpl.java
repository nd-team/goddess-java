package com.bjike.goddess.projectissuehandle.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.*;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.projectissuehandle.bo.ProOperateBO;
import com.bjike.goddess.projectissuehandle.bo.ProPermissionBO;
import com.bjike.goddess.projectissuehandle.dto.ProPermissionDTO;
import com.bjike.goddess.projectissuehandle.dto.ProPermissionOperateDTO;
import com.bjike.goddess.projectissuehandle.entity.ProPermission;
import com.bjike.goddess.projectissuehandle.entity.ProPermissionOperate;
import com.bjike.goddess.projectissuehandle.enums.ProPermissionType;
import com.bjike.goddess.projectissuehandle.to.ProPermissionTO;
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
 * 问题权限配置业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-26 05:43 ]
 * @Description: [ 问题权限配置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectissuehandleSerCache")
@Service
public class ProPermissionSerImpl extends ServiceImpl<ProPermission, ProPermissionDTO> implements ProPermissionSer {

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
    private ProPermissionOperateSer proPermissionOperateSer;

    @Override
    public Long countPermission(ProPermissionDTO proPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(proPermissionDTO.getDescription())) {
            proPermissionDTO.getConditions().add(Restrict.like("description", proPermissionDTO.getDescription()));
        }

        Long count = super.count(proPermissionDTO);
        return count;
    }

    @Override
    public List<ProPermissionBO> list(ProPermissionDTO proPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(proPermissionDTO.getDescription())) {
            proPermissionDTO.getConditions().add(Restrict.like("description", proPermissionDTO.getDescription()));
        }

        List<ProPermission> list = super.findByCis(proPermissionDTO, true);
        List<ProPermissionBO> bo = new ArrayList<>();
        for (ProPermission str : list) {
            ProPermissionBO temp = BeanTransform.copyProperties(str, ProPermissionBO.class);

            //先查询操作对象
            List<String> idList = new ArrayList<>();
            ProPermissionOperateDTO cpoDTO = new ProPermissionOperateDTO();
            cpoDTO.getConditions().add(Restrict.eq("propermissionId", temp.getId()));
            List<ProPermissionOperate> operateList = proPermissionOperateSer.findByCis(cpoDTO);
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
            ProPermissionType type = str.getType();
            List<OpinionBO> opinionBOS = new ArrayList<>();
            List<ProOperateBO> coboList = null;
            if (null != ids && ids.length != 0) {

                if (ProPermissionType.LEVEL.equals(type)) {
                    opinionBOS = arrangementAPI.findByIds(ids);
                } else if (ProPermissionType.MODULE.equals(type)) {
                    opinionBOS = moduleTypeAPI.findByIds(ids);
                } else if (ProPermissionType.POSITION.equals(type)) {
                    opinionBOS = positionDetailAPI.findByIds(ids);
                } else if (ProPermissionType.DEPART.equals(type)) {
                    opinionBOS = departmentDetailAPI.findByIds(ids);
                }

                coboList = new ArrayList<>();
                for (OpinionBO op : opinionBOS) {
                    ProOperateBO cobo = new ProOperateBO();
                    cobo.setId(op.getId());
                    cobo.setOperator(op.getValue());
                    coboList.add(cobo);
                }
            }
            temp.setProOperateBO(coboList);

            bo.add(temp);
        }
        return bo;
    }

    @Override
    public ProPermissionBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        ProPermission proPermission = super.findById(id);


        //先查询操作对象
        List<String> idList = new ArrayList<>();
        ProPermissionOperateDTO cpoDTO = new ProPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("propermissionId", proPermission.getId()));
        List<ProPermissionOperate> operateList = proPermissionOperateSer.findByCis(cpoDTO);
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
        ProPermissionType type = proPermission.getType();
        List<OpinionBO> opinionBOS = new ArrayList<>();
        List<ProOperateBO> coboList = new ArrayList<>();
        if (null != ids && ids.length != 0) {

            if (ProPermissionType.LEVEL.equals(type)) {
                //根据id数组查询名字和id
                opinionBOS = arrangementAPI.findByIds(ids);
            } else if (ProPermissionType.MODULE.equals(type)) {
                opinionBOS = moduleTypeAPI.findByIds(ids);
            } else if (ProPermissionType.POSITION.equals(type)) {
                opinionBOS = positionDetailAPI.findByIds(ids);
            } else if (ProPermissionType.DEPART.equals(type)) {
                opinionBOS = departmentDetailAPI.findByIds(ids);
            }


            for (OpinionBO op : opinionBOS) {
                ProOperateBO cobo = new ProOperateBO();
                cobo.setId(op.getId());
                cobo.setOperator(op.getValue());
                coboList.add(cobo);
            }
        }
        ProPermissionBO bo = BeanTransform.copyProperties(proPermission, ProPermissionBO.class);

        bo.setProOperateBO(coboList);

        return bo;
    }

    @Override
    public List<OpinionBO> listOperateById(String id) throws SerException {
        List<OpinionBO> list = new ArrayList<>();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        ProPermission proPermission = super.findById(id);
        ProPermissionType type = proPermission.getType();
        if (ProPermissionType.LEVEL.equals(type)) {
            list = arrangementAPI.findThawOpinion();
        } else if (ProPermissionType.MODULE.equals(type)) {
            list = moduleTypeAPI.findThawOpinion();
        } else if (ProPermissionType.POSITION.equals(type)) {
            list = positionDetailAPI.findThawOpinion();
        } else if (ProPermissionType.DEPART.equals(type)) {
            //TODO 部门查询
            list = departmentDetailAPI.findThawOpinion();
        }

        return list;
    }

    @Override
    public ProPermissionBO add(List<ProPermissionTO> proPermissionTO) throws SerException {
        List<ProPermission> list = BeanTransform.copyProperties(proPermissionTO, ProPermission.class, true);
        list = list.stream().filter(str -> StringUtils.isNotBlank(str.getIdFlag())).collect(Collectors.toList());
        List<String> idList = list.stream().map(ProPermission::getIdFlag).collect(Collectors.toList());
        String[] ids = idList.toArray(new String[idList.size()]);

        ProPermissionDTO dto = new ProPermissionDTO();
        dto.getConditions().add(Restrict.in("idFlag", ids));
        List<ProPermission> proPermissionList = super.findByCis(dto);
        List<ProPermission> proPermissionTempList = new ArrayList<>();
        if (proPermissionList != null && proPermissionList.size() > 0) {
            for (int i = 0; i < proPermissionList.size(); i++) {
                ProPermission temp = proPermissionList.get(i);
                Optional<ProPermission> cp = list.stream().filter(l -> l.getIdFlag().equals(temp.getIdFlag())).findFirst();
                if (cp.isPresent()) {
                    temp.setDescription(cp.get().getDescription());
                    temp.setType(cp.get().getType());
                    proPermissionTempList.add(temp);
                }
            }
            super.update(proPermissionTempList);

        } else {
            super.save(list);
        }

        //传进来的list>cusPermissionList数据库的，则添加
        //list ids cus ids
        List<String> listIdFlag = list.stream().map(ProPermission::getIdFlag).collect(Collectors.toList());
        List<String> databaseIdFlag = proPermissionList.stream().map(ProPermission::getIdFlag).collect(Collectors.toList());

        List<String> addIdFlag = listIdFlag.stream().filter(str -> !databaseIdFlag.contains(str)).collect(Collectors.toList());

        //传进来的新增的添加
        List<ProPermission> addList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ProPermission cPermission = list.get(i);
            cPermission.setCreateTime(LocalDateTime.now());
            cPermission.setModifyTime(LocalDateTime.now());
            if (addIdFlag.contains(cPermission.getIdFlag())) {
                addList.add(cPermission);
            }
        }
        super.save(addList);
        //没有的删除
        dto = new ProPermissionDTO();
        dto.getConditions().add(Restrict.notIn("idFlag", ids));
        List<ProPermission> deleteList = super.findByCis(dto);
        super.remove(deleteList);

        return new ProPermissionBO();
    }

    @Override
    public ProPermissionBO edit(ProPermissionTO proPermissionTO) throws SerException {
        if (StringUtils.isBlank(proPermissionTO.getId())) {
            throw new SerException("id不能为空");
        }

        if (StringUtils.isBlank(proPermissionTO.getDescription())) {
            throw new SerException("描述不能为空不能为空");
        }
        String[] operators = proPermissionTO.getOperators();

        ProPermission temp = super.findById(proPermissionTO.getId());
        temp.setDescription(proPermissionTO.getDescription());
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        //先删除
        ProPermissionOperateDTO cpoDTO = new ProPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("propermissionId", temp.getId()));
        List<ProPermissionOperate> deleteList = proPermissionOperateSer.findByCis(cpoDTO);
        if (deleteList != null && deleteList.size() > 0) {
            proPermissionOperateSer.remove(deleteList);
        }
        List<ProPermissionOperate> list = new ArrayList<>();
        for (String operateId : operators) {
            ProPermissionOperate cpo = new ProPermissionOperate();
            cpo.setOperator(operateId);
            cpo.setPropermissionId(temp.getId());
            list.add(cpo);
        }
        proPermissionOperateSer.save(list);

        return BeanTransform.copyProperties(temp, ProPermissionBO.class);
    }

    @Override
    public Boolean getProPermission(String idFlag) throws SerException {
        Boolean flag = false;
        //但前用户
        UserBO userBO = userAPI.currentUser();
        String userId = userBO.getId();
        if (StringUtils.isBlank(idFlag)) {
            throw new SerException("idFlag不能为空");
        }
        ProPermissionDTO dto = new ProPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        ProPermission proPermission = super.findOne(dto);

        //先查询获操作对象
        List<String> idList = new ArrayList<>();
        ProPermissionOperateDTO cpoDTO = new ProPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("propermissionId", proPermission.getId()));
        List<ProPermissionOperate> operateList = proPermissionOperateSer.findByCis(cpoDTO);
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
        if ( depart) {
            flag = true;
        } else {
            flag = false;
        }


        return flag;
    }

    @Override
    public Boolean busProPermission(String idFlag) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flag = false;
        //但前用户
        UserBO userBO = userAPI.currentUser();
        String userId = userBO.getId();
        if (StringUtils.isBlank(idFlag)) {
            throw new SerException("idFlag不能为空");
        }
        ProPermissionDTO dto = new ProPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        ProPermission proPermission = super.findOne(dto);


        //先查询操作对象
        List<String> idList = new ArrayList<>();
        ProPermissionOperateDTO cpoDTO = new ProPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("propermissionId", proPermission.getId()));
        List<ProPermissionOperate> operateList = proPermissionOperateSer.findByCis(cpoDTO);
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