package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.MarOperateBO;
import com.bjike.goddess.marketdevelopment.bo.MarPermissionBO;
import com.bjike.goddess.marketdevelopment.dto.MarPermissionDTO;
import com.bjike.goddess.marketdevelopment.dto.MarPermissionOperateDTO;
import com.bjike.goddess.marketdevelopment.entity.MarPermission;
import com.bjike.goddess.marketdevelopment.entity.MarPermissionOperate;
import com.bjike.goddess.marketdevelopment.enums.MarPermissionType;
import com.bjike.goddess.marketdevelopment.to.MarPermissionTO;
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
 * 市场计划进度管理权限配置业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-31 11:37 ]
 * @Description: [ 市场计划进度管理权限配置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class MarPermissionSerImpl extends ServiceImpl<MarPermission, MarPermissionDTO> implements MarPermissionSer {


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
    private MarPermissionOperateSer cusPermissionOperateSer;

    @Override
    public Long countPermission(MarPermissionDTO supPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(supPermissionDTO.getDescription())) {
            supPermissionDTO.getConditions().add(Restrict.like("description", supPermissionDTO.getDescription()));
        }

        Long count = super.count(supPermissionDTO);
        return count;
    }

    @Override
    public List<MarPermissionBO> list(MarPermissionDTO cusPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(cusPermissionDTO.getDescription())) {
            cusPermissionDTO.getConditions().add(Restrict.like("description", cusPermissionDTO.getDescription()));
        }

        List<MarPermission> list = super.findByCis(cusPermissionDTO, true);
        List<MarPermissionBO> bo = new ArrayList<>();
        for (MarPermission str : list) {
            MarPermissionBO temp = BeanTransform.copyProperties(str, MarPermissionBO.class);

            //先查询操作对象
            List<String> idList = new ArrayList<>();
            MarPermissionOperateDTO cpoDTO = new MarPermissionOperateDTO();
            cpoDTO.getConditions().add(Restrict.eq("marPermissionId", temp.getId()));
            List<MarPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
            MarPermissionType type = str.getType();
            List<OpinionBO> opinionBOS = new ArrayList<>();
            List<MarOperateBO> coboList = null;
            if (null != ids && ids.length != 0) {

                if (MarPermissionType.LEVEL.equals(type)) {
                    opinionBOS = arrangementAPI.findByIds(ids);
                } else if (MarPermissionType.MODULE.equals(type)) {
                    opinionBOS = moduleTypeAPI.findByIds(ids);
                } else if (MarPermissionType.POSITION.equals(type)) {
                    opinionBOS = positionDetailAPI.findByIds(ids);
                } else if (MarPermissionType.DEPART.equals(type)) {
                    opinionBOS = departmentDetailAPI.findByIds(ids);
                }

                coboList = new ArrayList<>();
                for (OpinionBO op : opinionBOS) {
                    MarOperateBO cobo = new MarOperateBO();
                    cobo.setId(op.getId());
                    cobo.setOperator(op.getValue());
                    coboList.add(cobo);
                }
            }
            temp.setMarOperateBOs(coboList);

            bo.add(temp);
        }
        return bo;
    }

    @Override
    public MarPermissionBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        MarPermission cusPermission = super.findById(id);

        //先查询操作对象
        List<String> idList = new ArrayList<>();
        MarPermissionOperateDTO cpoDTO = new MarPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("marPermissionId", cusPermission.getId()));
        List<MarPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
        MarPermissionType type = cusPermission.getType();
        List<OpinionBO> opinionBOS = new ArrayList<>();
        List<MarOperateBO> coboList = new ArrayList<>();
        if (null != ids && ids.length != 0) {

            if (MarPermissionType.LEVEL.equals(type)) {
                //根据id数组查询名字和id
                opinionBOS = arrangementAPI.findByIds(ids);
            } else if (MarPermissionType.MODULE.equals(type)) {
                opinionBOS = moduleTypeAPI.findByIds(ids);
            } else if (MarPermissionType.POSITION.equals(type)) {
                opinionBOS = positionDetailAPI.findByIds(ids);
            } else if (MarPermissionType.DEPART.equals(type)) {
                opinionBOS = departmentDetailAPI.findByIds(ids);
            }


            for (OpinionBO op : opinionBOS) {
                MarOperateBO cobo = new MarOperateBO();
                cobo.setId(op.getId());
                cobo.setOperator(op.getValue());
                coboList.add(cobo);
            }
        }
        MarPermissionBO bo = BeanTransform.copyProperties(cusPermission, MarPermissionBO.class);

        bo.setMarOperateBOs(coboList);

        return bo;
    }

    @Override
    public List<OpinionBO> listOperateById(String id) throws SerException {
        List<OpinionBO> list = new ArrayList<>();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        MarPermission cusPermission = super.findById(id);
        MarPermissionType type = cusPermission.getType();
        if (MarPermissionType.LEVEL.equals(type)) {
            list = arrangementAPI.findThawOpinion();
        } else if (MarPermissionType.MODULE.equals(type)) {
            list = moduleTypeAPI.findThawOpinion();
        } else if (MarPermissionType.POSITION.equals(type)) {
            list = positionDetailAPI.findThawOpinion();
        } else if (MarPermissionType.DEPART.equals(type)) {
            list = departmentDetailAPI.findThawOpinion();
        }

        return list;
    }

    @Override
    public MarPermissionBO add(List<MarPermissionTO> cusPermissionTO) throws SerException {
        List<MarPermission> list = BeanTransform.copyProperties(cusPermissionTO, MarPermission.class, true);
        list = list.stream().filter(str -> StringUtils.isNotBlank(str.getIdFlag())).collect(Collectors.toList());
        List<String> idList = list.stream().map(MarPermission::getIdFlag).collect(Collectors.toList());
        String[] ids = idList.toArray(new String[idList.size()]);

        MarPermissionDTO dto = new MarPermissionDTO();
        dto.getConditions().add(Restrict.in("idFlag", ids));
        List<MarPermission> cusPermissionList = super.findByCis(dto);
        List<MarPermission> cusPermissionTempList = new ArrayList<>();
        if (cusPermissionList != null && cusPermissionList.size() > 0) {
            for (int i = 0; i < cusPermissionList.size(); i++) {
                MarPermission temp = cusPermissionList.get(i);
                Optional<MarPermission> cp = list.stream().filter(l -> l.getIdFlag().equals(temp.getIdFlag())).findFirst();
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
        List<String> listIdFlag = list.stream().map(MarPermission::getIdFlag).collect(Collectors.toList());
        List<String> databaseIdFlag = cusPermissionList.stream().map(MarPermission::getIdFlag).collect(Collectors.toList());

        List<String> addIdFlag = listIdFlag.stream().filter(str -> !databaseIdFlag.contains(str)).collect(Collectors.toList());

        //传进来的新增的添加
        List<MarPermission> addList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            MarPermission cPermission = list.get(i);
            cPermission.setCreateTime(LocalDateTime.now());
            cPermission.setModifyTime(LocalDateTime.now());
            if (addIdFlag.contains(cPermission.getIdFlag())) {
                addList.add(cPermission);
            }
        }
        super.save(addList);
        //没有的删除
        dto = new MarPermissionDTO();
        dto.getConditions().add(Restrict.notIn("idFlag", ids));
        List<MarPermission> deleteList = super.findByCis(dto);
        super.remove(deleteList);

        return new MarPermissionBO();
    }

    @Override
    public MarPermissionBO edit(MarPermissionTO cusPermissionTO) throws SerException {
        if (StringUtils.isBlank(cusPermissionTO.getId())) {
            throw new SerException("id不能为空");
        }

        if (StringUtils.isBlank(cusPermissionTO.getDescription())) {
            throw new SerException("描述不能为空不能为空");
        }
        String[] operators = cusPermissionTO.getOperators();

        MarPermission temp = super.findById(cusPermissionTO.getId());
        temp.setDescription(cusPermissionTO.getDescription());
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        //先删除
        MarPermissionOperateDTO cpoDTO = new MarPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("marPermissionId", temp.getId()));
        List<MarPermissionOperate> deleteList = cusPermissionOperateSer.findByCis(cpoDTO);
        if (deleteList != null && deleteList.size() > 0) {
            cusPermissionOperateSer.remove(deleteList);
        }
        List<MarPermissionOperate> list = new ArrayList<>();
        for (String operateId : operators) {
            MarPermissionOperate cpo = new MarPermissionOperate();
            cpo.setOperator(operateId);
            cpo.setMarPermissionId(temp.getId());
            list.add(cpo);
        }
        cusPermissionOperateSer.save(list);

        return BeanTransform.copyProperties(temp, MarPermissionBO.class);
    }

    @Override
    public Boolean getMarPermission(String idFlag) throws SerException {
        //当前用户
        UserBO userBO = userAPI.currentUser();
        String userId = userBO.getId();
        if (StringUtils.isBlank(idFlag)) {
            throw new SerException("idFlag不能为空");
        }

        MarPermissionDTO dto = new MarPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        MarPermission cusPermission = super.findOne(dto);

        //先查询获操作对象
        List<String> idList = new ArrayList<>();
        MarPermissionOperateDTO cpoDTO = new MarPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("marPermissionId", cusPermission.getId()));
        List<MarPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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