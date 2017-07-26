package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.buyticket.bo.BuyCusOperateBO;
import com.bjike.goddess.buyticket.bo.BuyCusPermissionBO;
import com.bjike.goddess.buyticket.dto.BuyCusPermissionDTO;
import com.bjike.goddess.buyticket.dto.BuyCusPermissionOperateDTO;
import com.bjike.goddess.buyticket.entity.BuyCusPermission;
import com.bjike.goddess.buyticket.entity.BuyCusPermissionOperate;
import com.bjike.goddess.buyticket.to.BuyCusPermissionTO;
import com.bjike.goddess.buyticket.enums.CusPermissionType;
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
@CacheConfig(cacheNames = "buyCustomerSerCache")
@Service
public class BuyCusPermissionSerImpl extends ServiceImpl<BuyCusPermission, BuyCusPermissionDTO> implements BuyCusPermissionSer {

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
    private BuyCusPermissionOperateSer cusPermissionOperateSer;

    @Override
    public Long countPermission(BuyCusPermissionDTO cusPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(cusPermissionDTO.getDescription())) {
            cusPermissionDTO.getConditions().add(Restrict.like("description", cusPermissionDTO.getDescription()));
        }

        Long count = super.count(cusPermissionDTO);
        return count;
    }

    @Override
    public List<BuyCusPermissionBO> list(BuyCusPermissionDTO cusPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(cusPermissionDTO.getDescription())) {
            cusPermissionDTO.getConditions().add(Restrict.like("description", cusPermissionDTO.getDescription()));
        }

        List<BuyCusPermission> list = super.findByCis(cusPermissionDTO, true);
        List<BuyCusPermissionBO> bo = new ArrayList<>();
        for (BuyCusPermission str : list) {
            BuyCusPermissionBO temp = BeanTransform.copyProperties(str, BuyCusPermissionBO.class);

            //先查询操作对象
            List<String> idList = new ArrayList<>();
            BuyCusPermissionOperateDTO cpoDTO = new BuyCusPermissionOperateDTO();
            cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", temp.getId()));
            List<BuyCusPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
            List<BuyCusOperateBO> coboList = null;
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
                    BuyCusOperateBO cobo = new BuyCusOperateBO();
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
    public BuyCusPermissionBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        BuyCusPermission cusPermission = super.findById(id);


        //先查询操作对象
        List<String> idList = new ArrayList<>();
        BuyCusPermissionOperateDTO cpoDTO = new BuyCusPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<BuyCusPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
        List<BuyCusOperateBO> coboList = new ArrayList<>();
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
                BuyCusOperateBO cobo = new BuyCusOperateBO();
                cobo.setId(op.getId());
                cobo.setOperator(op.getValue());
                coboList.add(cobo);
            }
        }
        BuyCusPermissionBO bo = BeanTransform.copyProperties(cusPermission, BuyCusPermissionBO.class);

        bo.setCusOperateBO(coboList);

        return bo;
    }

    @Override
    public List<OpinionBO> listOperateById(String id) throws SerException {
        List<OpinionBO> list = new ArrayList<>();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        BuyCusPermission cusPermission = super.findById(id);
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
    public BuyCusPermissionBO add(List<BuyCusPermissionTO> cusPermissionTO) throws SerException {
        List<BuyCusPermission> list = BeanTransform.copyProperties(cusPermissionTO, BuyCusPermission.class, true);
        list = list.stream().filter(str -> StringUtils.isNotBlank(str.getIdFlag())).collect(Collectors.toList());
        List<String> idList = list.stream().map(BuyCusPermission::getIdFlag).collect(Collectors.toList());
        String[] ids = idList.toArray(new String[idList.size()]);

        BuyCusPermissionDTO dto = new BuyCusPermissionDTO();
        dto.getConditions().add(Restrict.in("idFlag", ids));
        List<BuyCusPermission> cusPermissionList = super.findByCis(dto);
        List<BuyCusPermission> cusPermissionTempList = new ArrayList<>();
        if (cusPermissionList != null && cusPermissionList.size() > 0) {
            for (int i = 0; i < cusPermissionList.size(); i++) {
                BuyCusPermission temp = cusPermissionList.get(i);
                Optional<BuyCusPermission> cp = list.stream().filter(l -> l.getIdFlag().equals(temp.getIdFlag())).findFirst();
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
        List<String> listIdFlag = list.stream().map(BuyCusPermission::getIdFlag).collect(Collectors.toList());
        List<String> databaseIdFlag = cusPermissionList.stream().map(BuyCusPermission::getIdFlag).collect(Collectors.toList());

        List<String> addIdFlag = listIdFlag.stream().filter(str -> !databaseIdFlag.contains(str)).collect(Collectors.toList());

        //传进来的新增的添加
        List<BuyCusPermission> addList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            BuyCusPermission cPermission = list.get(i);
            cPermission.setCreateTime(LocalDateTime.now());
            cPermission.setModifyTime(LocalDateTime.now());
            if (addIdFlag.contains(cPermission.getIdFlag())) {
                addList.add(cPermission);
            }
        }
        super.save(addList);
        //没有的删除
        dto = new BuyCusPermissionDTO();
        dto.getConditions().add(Restrict.notIn("idFlag", ids));
        List<BuyCusPermission> deleteList = super.findByCis(dto);
        super.remove(deleteList);

        return new BuyCusPermissionBO();
    }

    @Override
    public BuyCusPermissionBO edit(BuyCusPermissionTO cusPermissionTO) throws SerException {
        if (StringUtils.isBlank(cusPermissionTO.getId())) {
            throw new SerException("id不能为空");
        }

        if (StringUtils.isBlank(cusPermissionTO.getDescription())) {
            throw new SerException("描述不能为空不能为空");
        }
        String[] operators = cusPermissionTO.getOperators();

        BuyCusPermission temp = super.findById(cusPermissionTO.getId());
        temp.setDescription(cusPermissionTO.getDescription());
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        //先删除
        BuyCusPermissionOperateDTO cpoDTO = new BuyCusPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", temp.getId()));
        List<BuyCusPermissionOperate> deleteList = cusPermissionOperateSer.findByCis(cpoDTO);
        if (deleteList != null && deleteList.size() > 0) {
            cusPermissionOperateSer.remove(deleteList);
        }
        if( operators != null && operators.length>0 ){
            List<BuyCusPermissionOperate> list = new ArrayList<>();
            for (String operateId : operators) {
                BuyCusPermissionOperate cpo = new BuyCusPermissionOperate();
                cpo.setOperator(operateId);
                cpo.setCuspermissionId(temp.getId());
                list.add(cpo);
            }
            cusPermissionOperateSer.save(list);
        }


        return BeanTransform.copyProperties(temp, BuyCusPermissionBO.class);
    }

    //模块
    @Override
    public Boolean getCusPermission(String idFlag) throws SerException {
        Boolean flag = false;
        //但前用户
        UserBO userBO = userAPI.currentUser();
        String userId = userBO.getId();
        if (StringUtils.isBlank(idFlag)) {
            throw new SerException("idFlag不能为空");
        }
        BuyCusPermissionDTO dto = new BuyCusPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        BuyCusPermission cusPermission = super.findOne(dto);

        //先查询获操作对象
        List<String> idList = new ArrayList<>();
        BuyCusPermissionOperateDTO cpoDTO = new BuyCusPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<BuyCusPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
        Boolean positionFlag = positionDetailUserAPI.checkAsUserPosition(userId, operateIds);//岗位
        Boolean arrangementFlag = positionDetailUserAPI.checkAsUserArrangement(userId, operateIds);//层次
        Boolean moduleFlag = positionDetailUserAPI.checkAsUserModule(userId, operateIds);


        //TODO 部门
        if (moduleFlag) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    //部门
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
        BuyCusPermissionDTO dto = new BuyCusPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        BuyCusPermission cusPermission = super.findOne(dto);


        //先查询操作对象
        List<String> idList = new ArrayList<>();
        BuyCusPermissionOperateDTO cpoDTO = new BuyCusPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<BuyCusPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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

        Boolean departmentFlag = positionDetailUserAPI.checkAsUserDepartment(userId, operateIds);

        if (departmentFlag) {
            flag = true;
        } else {
            flag = false;
        }
        RpcTransmit.transmitUserToken(userToken);
        String aa = RpcTransmit.getUserToken();
        return flag;
    }

    //层次
    @Override
    public Boolean arrCusPermission(String idFlag) throws SerException {
        Boolean flag = false;
        //但前用户
        UserBO userBO = userAPI.currentUser();
        String userId = userBO.getId();
        if (StringUtils.isBlank(idFlag)) {
            throw new SerException("idFlag不能为空");
        }
        BuyCusPermissionDTO dto = new BuyCusPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        BuyCusPermission cusPermission = super.findOne(dto);

        //先查询获操作对象
        List<String> idList = new ArrayList<>();
        BuyCusPermissionOperateDTO cpoDTO = new BuyCusPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<BuyCusPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
//        Boolean positionFlag = positionDetailUserAPI.checkAsUserPosition(userId, operateIds);//岗位
        Boolean arrangementFlag = positionDetailUserAPI.checkAsUserArrangement(userId, operateIds);//层次
//        Boolean moduleFlag = positionDetailUserAPI.checkAsUserModule(userId, operateIds);


        //TODO 部门
        if (arrangementFlag) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }
    //岗位
    @Override
    public Boolean jobsCusPermission(String idFlag) throws SerException {
        Boolean flag = false;
        //但前用户
        UserBO userBO = userAPI.currentUser();
        String userId = userBO.getId();
        if (StringUtils.isBlank(idFlag)) {
            throw new SerException("idFlag不能为空");
        }
        BuyCusPermissionDTO dto = new BuyCusPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        BuyCusPermission cusPermission = super.findOne(dto);

        //先查询获操作对象
        List<String> idList = new ArrayList<>();
        BuyCusPermissionOperateDTO cpoDTO = new BuyCusPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<BuyCusPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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

        Boolean jobsFlag = positionDetailUserAPI.checkAsUserPosition(userId, operateIds);//层次


        if (jobsFlag) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }
}