package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.bo.UserSetOperateBO;
import com.bjike.goddess.organize.bo.UserSetPermissionBO;
import com.bjike.goddess.organize.dto.UserSetPermissionDTO;
import com.bjike.goddess.organize.dto.UserSetPermissionOperateDTO;
import com.bjike.goddess.organize.entity.UserSetPermission;
import com.bjike.goddess.organize.entity.UserSetPermissionOperate;
import com.bjike.goddess.organize.enums.UserSetPermissionType;
import com.bjike.goddess.organize.to.UserSetPermissionTO;
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
@Service
public class UserSetPermissionSerImpl extends ServiceImpl<UserSetPermission, UserSetPermissionDTO> implements UserSetPermissionSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserSer positionDetailUserSer;
    @Autowired
    private ArrangementSer arrangementSer;
    @Autowired
    private ModuleTypeSer moduleTypeSer;
    @Autowired
    private PositionDetailSer positionDetailSer;
    @Autowired
    private DepartmentDetailSer departmentDetailSer;
    @Autowired
    private UserSetPermissionOperateSer userSetPermissionOperateSer;

    @Override
    public UserBO adminPermission() throws SerException {
        UserBO userBO = userAPI.currentUser( );
        return userBO;
    }

    @Override
    public Long countPermission(UserSetPermissionDTO cusPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(cusPermissionDTO.getDescription())) {
            cusPermissionDTO.getConditions().add(Restrict.like("description", cusPermissionDTO.getDescription()));
        }

        Long count = super.count(cusPermissionDTO);
        return count;
    }

    @Override
    public List<UserSetPermissionBO> list(UserSetPermissionDTO cusPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(cusPermissionDTO.getDescription())) {
            cusPermissionDTO.getConditions().add(Restrict.like("description", cusPermissionDTO.getDescription()));
        }

        List<UserSetPermission> list = super.findByCis(cusPermissionDTO, true);
        List<UserSetPermissionBO> bo = new ArrayList<>();
        for (UserSetPermission str : list) {
            UserSetPermissionBO temp = BeanTransform.copyProperties(str, UserSetPermissionBO.class);

            //先查询操作对象
            List<String> idList = new ArrayList<>();
            UserSetPermissionOperateDTO cpoDTO = new UserSetPermissionOperateDTO();
            cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", temp.getId()));
            List<UserSetPermissionOperate> operateList = userSetPermissionOperateSer.findByCis(cpoDTO);
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
            UserSetPermissionType type = str.getType();
            List<OpinionBO> opinionBOS = new ArrayList<>();
            List<UserSetOperateBO> coboList = null;
            if (null != ids && ids.length != 0) {

                if (UserSetPermissionType.LEVEL.equals(type)) {
                    opinionBOS = arrangementSer.findByIds(ids);
                } else if (UserSetPermissionType.MODULE.equals(type)) {
                    opinionBOS = moduleTypeSer.findByIds(ids);
                } else if (UserSetPermissionType.POSITION.equals(type)) {
                    opinionBOS = positionDetailSer.findByIds(ids);
                } else if (UserSetPermissionType.DEPART.equals(type)) {
                    opinionBOS = departmentDetailSer.findByIds(ids);
                }

                coboList = new ArrayList<>();
                for (OpinionBO op : opinionBOS) {
                    UserSetOperateBO cobo = new UserSetOperateBO();
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
    public UserSetPermissionBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        UserSetPermission cusPermission = super.findById(id);


        //先查询操作对象
        List<String> idList = new ArrayList<>();
        UserSetPermissionOperateDTO cpoDTO = new UserSetPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<UserSetPermissionOperate> operateList = userSetPermissionOperateSer.findByCis(cpoDTO);
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
        UserSetPermissionType type = cusPermission.getType();
        List<OpinionBO> opinionBOS = new ArrayList<>();
        List<UserSetOperateBO> coboList = new ArrayList<>();
        if (null != ids && ids.length != 0) {

            if (UserSetPermissionType.LEVEL.equals(type)) {
                //根据id数组查询名字和id
                opinionBOS = arrangementSer.findByIds(ids);
            } else if (UserSetPermissionType.MODULE.equals(type)) {
                opinionBOS = moduleTypeSer.findByIds(ids);
            } else if (UserSetPermissionType.POSITION.equals(type)) {
                opinionBOS = positionDetailSer.findByIds(ids);
            } else if (UserSetPermissionType.DEPART.equals(type)) {
                opinionBOS = departmentDetailSer.findByIds(ids);
            }


            for (OpinionBO op : opinionBOS) {
                UserSetOperateBO cobo = new UserSetOperateBO();
                cobo.setId(op.getId());
                cobo.setOperator(op.getValue());
                coboList.add(cobo);
            }
        }
        UserSetPermissionBO bo = BeanTransform.copyProperties(cusPermission, UserSetPermissionBO.class);

        bo.setCusOperateBO(coboList);

        return bo;
    }

    @Override
    public List<OpinionBO> listOperateById(String id) throws SerException {
        List<OpinionBO> list = new ArrayList<>();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        UserSetPermission cusPermission = super.findById(id);
        UserSetPermissionType type = cusPermission.getType();
        if (UserSetPermissionType.LEVEL.equals(type)) {
            list = arrangementSer.findThawOpinion();
        } else if (UserSetPermissionType.MODULE.equals(type)) {
            list = moduleTypeSer.findThawOpinion();
        } else if (UserSetPermissionType.POSITION.equals(type)) {
            list = positionDetailSer.findThawOpinion();
        } else if (UserSetPermissionType.DEPART.equals(type)) {
            //TODO 部门查询
            list = departmentDetailSer.findThawOpinion();
        }

        return list;
    }

    @Override
    public UserSetPermissionBO add(List<UserSetPermissionTO> cusPermissionTO) throws SerException {
        List<UserSetPermission> list = BeanTransform.copyProperties(cusPermissionTO, UserSetPermission.class, true);
        list = list.stream().filter(str -> StringUtils.isNotBlank(str.getIdFlag())).collect(Collectors.toList());
        List<String> idList = list.stream().map(UserSetPermission::getIdFlag).collect(Collectors.toList());
        String[] ids = idList.toArray(new String[idList.size()]);

        UserSetPermissionDTO dto = new UserSetPermissionDTO();
        dto.getConditions().add(Restrict.in("idFlag", ids));
        List<UserSetPermission> cusPermissionList = super.findByCis(dto);
        List<UserSetPermission> cusPermissionTempList = new ArrayList<>();
        if (cusPermissionList != null && cusPermissionList.size() > 0) {
            for (int i = 0; i < cusPermissionList.size(); i++) {
                UserSetPermission temp = cusPermissionList.get(i);
                Optional<UserSetPermission> cp = list.stream().filter(l -> l.getIdFlag().equals(temp.getIdFlag())).findFirst();
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
        List<String> listIdFlag = list.stream().map(UserSetPermission::getIdFlag).collect(Collectors.toList());
        List<String> databaseIdFlag = cusPermissionList.stream().map(UserSetPermission::getIdFlag).collect(Collectors.toList());

        List<String> addIdFlag = listIdFlag.stream().filter(str -> !databaseIdFlag.contains(str)).collect(Collectors.toList());

        //传进来的新增的添加
        List<UserSetPermission> addList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            UserSetPermission cPermission = list.get(i);
            cPermission.setCreateTime(LocalDateTime.now());
            cPermission.setModifyTime(LocalDateTime.now());
            if (addIdFlag.contains(cPermission.getIdFlag())) {
                addList.add(cPermission);
            }
        }
        super.save(addList);
        //没有的删除
        dto = new UserSetPermissionDTO();
        dto.getConditions().add(Restrict.notIn("idFlag", ids));
        List<UserSetPermission> deleteList = super.findByCis(dto);
        super.remove(deleteList);

        return new UserSetPermissionBO();
    }

    @Override
    public UserSetPermissionBO edit(UserSetPermissionTO cusPermissionTO) throws SerException {
        if (StringUtils.isBlank(cusPermissionTO.getId())) {
            throw new SerException("id不能为空");
        }

        if (StringUtils.isBlank(cusPermissionTO.getDescription())) {
            throw new SerException("描述不能为空不能为空");
        }
        String[] operators = cusPermissionTO.getOperators();

        UserSetPermission temp = super.findById(cusPermissionTO.getId());
        temp.setDescription(cusPermissionTO.getDescription());
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        //先删除
        UserSetPermissionOperateDTO cpoDTO = new UserSetPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", temp.getId()));
        List<UserSetPermissionOperate> deleteList = userSetPermissionOperateSer.findByCis(cpoDTO);
        if (deleteList != null && deleteList.size() > 0) {
            userSetPermissionOperateSer.remove(deleteList);
        }
        List<UserSetPermissionOperate> list = new ArrayList<>();
        for (String operateId : operators) {
            UserSetPermissionOperate cpo = new UserSetPermissionOperate();
            cpo.setOperator(operateId);
            cpo.setCuspermissionId(temp.getId());
            list.add(cpo);
        }
        userSetPermissionOperateSer.save(list);

        return BeanTransform.copyProperties(temp, UserSetPermissionBO.class);
    }

    @Override
    public Boolean getCusPermission(String idFlag) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flag = false;
        //但前用户
        UserBO userBO = userAPI.currentUser(userToken);
        String userId = userBO.getId();
        if (StringUtils.isBlank(idFlag)) {
            throw new SerException("idFlag不能为空");
        }
        UserSetPermissionDTO dto = new UserSetPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        UserSetPermission cusPermission = super.findOne(dto);

        //先查询获操作对象
        List<String> idList = new ArrayList<>();
        UserSetPermissionOperateDTO cpoDTO = new UserSetPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<UserSetPermissionOperate> operateList = userSetPermissionOperateSer.findByCis(cpoDTO);
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
        Boolean positionFlag = positionDetailUserSer.checkAsUserPosition(userId, operateIds);
//        Boolean arrangementFlag = positionDetailUserAPI.checkAsUserArrangement(userId, operateIds);
//        Boolean moduleFlag = positionDetailUserAPI.checkAsUserModule(userId, operateIds);
//        Boolean depart = positionDetailUserSer.checkAsUserDepartment(userId, operateIds);


        //TODO 岗位
        if ( positionFlag) {
            flag = true;
        } else {
            flag = false;
        }


        return flag;
    }

    @Override
    public Boolean checkSetPermission( ) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flag = false;

        UserBO userBO = userAPI.currentUser( );
        RpcTransmit.transmitUserToken( userToken );
        String userName = userBO.getUsername();
        if( "admin".equals( userName.toLowerCase())){
            flag = true;

        }else {

            //但前用户
            String userId = userBO.getId();

            UserSetPermissionDTO dto = new UserSetPermissionDTO();
            dto.getConditions().add(Restrict.eq("idFlag", "1"));
            UserSetPermission cusPermission = super.findOne(dto);

            //先查询获操作对象
            List<String> idList = new ArrayList<>();
            UserSetPermissionOperateDTO cpoDTO = new UserSetPermissionOperateDTO();
            cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
            List<UserSetPermissionOperate> operateList = userSetPermissionOperateSer.findByCis(cpoDTO);
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
            Boolean positionFlag = positionDetailUserSer.checkAsUserPosition(userId, operateIds);


            //TODO 岗位
            if (positionFlag) {
                flag = true;
            } else {
                flag = false;
            }
        }

        return flag;
    }
}