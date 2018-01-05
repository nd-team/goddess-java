package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.BalancecardOperateBO;
import com.bjike.goddess.balancecard.bo.BalancecardPermissionBO;
import com.bjike.goddess.balancecard.dto.BalancecardPermissionDTO;
import com.bjike.goddess.balancecard.dto.BalancecardPermissionOperateDTO;
import com.bjike.goddess.balancecard.entity.BalancecardPermission;
import com.bjike.goddess.balancecard.entity.BalancecardPermissionOperate;
import com.bjike.goddess.balancecard.enums.CusPermissionType;
import com.bjike.goddess.balancecard.to.BalancecardPermissionTO;
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
 * 平衡计分卡权限配置业务实现
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 平衡计分卡权限配置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "balancecardSerCache")
@Service
public class BalancecardPermissionSerImpl extends ServiceImpl<BalancecardPermission, BalancecardPermissionDTO> implements BalancecardPermissionSer {

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
    private BalancecardPermissionOperateSer cusPermissionOperateSer;

    @Override
    public Long countPermission(BalancecardPermissionDTO cusPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(cusPermissionDTO.getDescription())) {
            cusPermissionDTO.getConditions().add(Restrict.like("description", cusPermissionDTO.getDescription()));
        }

        Long count = super.count(cusPermissionDTO);
        return count;
    }

    @Override
    public List<BalancecardPermissionBO> list(BalancecardPermissionDTO cusPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(cusPermissionDTO.getDescription())) {
            cusPermissionDTO.getConditions().add(Restrict.like("description", cusPermissionDTO.getDescription()));
        }

        List<BalancecardPermission> list = super.findByCis(cusPermissionDTO, true);
        List<BalancecardPermissionBO> bo = new ArrayList<>();
        for (BalancecardPermission str : list) {
            BalancecardPermissionBO temp = BeanTransform.copyProperties(str, BalancecardPermissionBO.class);

            //先查询操作对象
            List<String> idList = new ArrayList<>();
            BalancecardPermissionOperateDTO cpoDTO = new BalancecardPermissionOperateDTO();
            cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", temp.getId()));
            List<BalancecardPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
            CusPermissionType type = str.getType();
            List<OpinionBO> opinionBOS = new ArrayList<>();
            List<BalancecardOperateBO> coboList = null;
            if (null != ids && ids.length != 0) {

                if (CusPermissionType.LEVEL.equals(type)) {
                    opinionBOS = arrangementAPI.findByIds(ids);
                } else if (CusPermissionType.MODULE.equals(type)) {
                    opinionBOS = moduleTypeAPI.findByIds(ids);
                } else if (CusPermissionType.POSITION.equals(type)) {
                    opinionBOS = positionDetailAPI.findByIds(ids);
                } else if (CusPermissionType.DEPART.equals(type)) {
                    opinionBOS = departmentDetailAPI.findByIds(ids);
                }

                coboList = new ArrayList<>();
                for (OpinionBO op : opinionBOS) {
                    BalancecardOperateBO cobo = new BalancecardOperateBO();
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
    public BalancecardPermissionBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        BalancecardPermission cusPermission = super.findById(id);


        //先查询操作对象
        List<String> idList = new ArrayList<>();
        BalancecardPermissionOperateDTO cpoDTO = new BalancecardPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<BalancecardPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
        CusPermissionType type = cusPermission.getType();
        List<OpinionBO> opinionBOS = new ArrayList<>();
        List<BalancecardOperateBO> coboList = new ArrayList<>();
        if (null != ids && ids.length != 0) {

            if (CusPermissionType.LEVEL.equals(type)) {
                //根据id数组查询名字和id
                opinionBOS = arrangementAPI.findByIds(ids);
            } else if (CusPermissionType.MODULE.equals(type)) {
                opinionBOS = moduleTypeAPI.findByIds(ids);
            } else if (CusPermissionType.POSITION.equals(type)) {
                opinionBOS = positionDetailAPI.findByIds(ids);
            } else if (CusPermissionType.DEPART.equals(type)) {
                opinionBOS = departmentDetailAPI.findByIds(ids);
            }


            for (OpinionBO op : opinionBOS) {
                BalancecardOperateBO cobo = new BalancecardOperateBO();
                cobo.setId(op.getId());
                cobo.setOperator(op.getValue());
                coboList.add(cobo);
            }
        }
        BalancecardPermissionBO bo = BeanTransform.copyProperties(cusPermission, BalancecardPermissionBO.class);

        bo.setCusOperateBO(coboList);

        return bo;
    }

    @Override
    public List<OpinionBO> listOperateById(String id) throws SerException {
        List<OpinionBO> list = new ArrayList<>();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        BalancecardPermission cusPermission = super.findById(id);
        CusPermissionType type = cusPermission.getType();
        if (CusPermissionType.LEVEL.equals(type)) {
            list = arrangementAPI.findThawOpinion();
        } else if (CusPermissionType.MODULE.equals(type)) {
            list = moduleTypeAPI.findThawOpinion();
        } else if (CusPermissionType.POSITION.equals(type)) {
            list = positionDetailAPI.findThawOpinion();
        } else if (CusPermissionType.DEPART.equals(type)) {
            //TODO 部门查询
            list = departmentDetailAPI.findThawOpinion();
        }

        return list;
    }

    @Override
    public BalancecardPermissionBO add(List<BalancecardPermissionTO> cusPermissionTO) throws SerException {
        List<BalancecardPermission> list = BeanTransform.copyProperties(cusPermissionTO, BalancecardPermission.class, true);
        list = list.stream().filter(str -> StringUtils.isNotBlank(str.getIdFlag())).collect(Collectors.toList());
        List<String> idList = list.stream().map(BalancecardPermission::getIdFlag).collect(Collectors.toList());
        String[] ids = idList.toArray(new String[idList.size()]);

        BalancecardPermissionDTO dto = new BalancecardPermissionDTO();
        dto.getConditions().add(Restrict.in("idFlag", ids));
        List<BalancecardPermission> cusPermissionList = super.findByCis(dto);
        List<BalancecardPermission> cusPermissionTempList = new ArrayList<>();
        if (cusPermissionList != null && cusPermissionList.size() > 0) {
            for (int i = 0; i < cusPermissionList.size(); i++) {
                BalancecardPermission temp = cusPermissionList.get(i);
                Optional<BalancecardPermission> cp = list.stream().filter(l->l.getIdFlag().equals(temp.getIdFlag())).findFirst();
                if(cp.isPresent()){
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
        List<String> listIdFlag = list.stream().map(BalancecardPermission::getIdFlag).collect(Collectors.toList());
        List<String> databaseIdFlag = cusPermissionList.stream().map(BalancecardPermission::getIdFlag).collect(Collectors.toList());

        List<String> addIdFlag = listIdFlag.stream().filter(str -> !databaseIdFlag.contains(str)).collect(Collectors.toList());

        //传进来的新增的添加
        List<BalancecardPermission> addList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            BalancecardPermission cPermission = list.get(i);
            cPermission.setCreateTime(LocalDateTime.now());
            cPermission.setModifyTime(LocalDateTime.now());
            if (addIdFlag.contains(cPermission.getIdFlag())) {
                addList.add(cPermission);
            }
        }
        super.save(addList);
        //没有的删除
        dto = new BalancecardPermissionDTO();
        dto.getConditions().add(Restrict.notIn("idFlag", ids));
        List<BalancecardPermission> deleteList = super.findByCis(dto);
        super.remove(deleteList);

        return new BalancecardPermissionBO();
    }

    @Override
    public BalancecardPermissionBO edit(BalancecardPermissionTO cusPermissionTO) throws SerException {
        if (StringUtils.isBlank(cusPermissionTO.getId())) {
            throw new SerException("id不能为空");
        }

        if (StringUtils.isBlank(cusPermissionTO.getDescription())) {
            throw new SerException("描述不能为空不能为空");
        }
        String[] operators = cusPermissionTO.getOperators();

        BalancecardPermission temp = super.findById(cusPermissionTO.getId());
        temp.setDescription(cusPermissionTO.getDescription());
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        //先删除
        BalancecardPermissionOperateDTO cpoDTO = new BalancecardPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", temp.getId()));
        List<BalancecardPermissionOperate> deleteList = cusPermissionOperateSer.findByCis(cpoDTO);
        if (deleteList != null && deleteList.size() > 0) {
            cusPermissionOperateSer.remove(deleteList);
        }
        List<BalancecardPermissionOperate> list = new ArrayList<>();
        for (String operateId : operators) {
            BalancecardPermissionOperate cpo = new BalancecardPermissionOperate();
            cpo.setOperator(operateId);
            cpo.setCuspermissionId(temp.getId());
            list.add(cpo);
        }
        cusPermissionOperateSer.save(list);

        return BeanTransform.copyProperties(temp, BalancecardPermissionBO.class);
    }

    @Override
    public Boolean getCusPermission(String idFlag) throws SerException {
        Boolean flag = false;
        //当前用户
        UserBO userBO = userAPI.currentUser();
        String userId = userBO.getId();
        if (StringUtils.isBlank(idFlag)) {
            throw new SerException("idFlag不能为空");
        }
        BalancecardPermissionDTO dto = new BalancecardPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        BalancecardPermission cusPermission = super.findOne(dto);

        //先查询获操作对象
        List<String> idList = new ArrayList<>();
        BalancecardPermissionOperateDTO cpoDTO = new BalancecardPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<BalancecardPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
        Boolean arrangementFlag = positionDetailUserAPI.checkAsUserArrangement(userId, operateIds);
//        Boolean moduleFlag = positionDetailUserAPI.checkAsUserModule(userId, operateIds);
//        Boolean depart = positionDetailUserAPI.checkAsUserDepartment(userId, operateIds);


        //TODO 部门
        if (arrangementFlag) {
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
        BalancecardPermissionDTO dto = new BalancecardPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        BalancecardPermission cusPermission = super.findOne(dto);


        //先查询操作对象
        List<String> idList = new ArrayList<>();
        BalancecardPermissionOperateDTO cpoDTO = new BalancecardPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<BalancecardPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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