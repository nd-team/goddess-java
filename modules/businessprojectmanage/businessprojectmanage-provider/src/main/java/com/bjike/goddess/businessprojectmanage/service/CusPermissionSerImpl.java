package com.bjike.goddess.businessprojectmanage.service;

import com.bjike.goddess.businessprojectmanage.bo.CusOperateBO;
import com.bjike.goddess.businessprojectmanage.bo.CusPermissionBO;
import com.bjike.goddess.businessprojectmanage.dto.CusPermissionDTO;
import com.bjike.goddess.businessprojectmanage.dto.CusPermissionOperateDTO;
import com.bjike.goddess.businessprojectmanage.entity.CusPermission;
import com.bjike.goddess.businessprojectmanage.entity.CusPermissionOperate;
import com.bjike.goddess.businessprojectmanage.enums.CusPermissionType;
import com.bjike.goddess.businessprojectmanage.enums.GuideAddrStatus;
import com.bjike.goddess.businessprojectmanage.excel.SonPermissionObject;
import com.bjike.goddess.businessprojectmanage.to.CusPermissionTO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
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
 * 客户权限设置业务实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-12 05:56 ]
 * @Description: [ 客户权限设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectmanageSerCache")
@Service
public class CusPermissionSerImpl extends ServiceImpl<CusPermission, CusPermissionDTO> implements CusPermissionSer {

    @Autowired
    UserAPI userAPI;
    @Autowired
    CusPermissionOperateSer cusPermissionOperateSer;
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


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
//        String userToken = RpcTransmit.getUserToken();
//        Boolean flagSeeSign = guideSeeIdentity();
//        RpcTransmit.transmitUserToken(userToken);
//        Boolean flagAddSign = guideAddIdentity();
        Boolean flag1 = false;
        Boolean flag2 = false;
        Boolean flag3 = false;
        Boolean flag4 = false;
        Boolean flag5 = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag1 = getCusPermission("1", userBO);
            flag2 = getCusPermission("2", userBO);
            flag3 = busCusPermission("3", userBO);
            flag4 = modCusPermission("4", userBO);
            flag5 = modCusPermission("5", userBO);
        } else {
            flag1 = true;
            flag2 = true;
            flag3 = true;
            flag4 = true;
            flag5 = true;
        }
        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("contractinfo");
        obj.setDescribesion("项目合同基本信息");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        obj = new SonPermissionObject();
        obj.setName("notification");
        obj.setDescribesion("通报机制制定");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("communication");
        obj.setDescribesion("各类沟通模板");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("progresscollect");
        obj.setDescribesion("项目实施进度管理汇总");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("chart");
        obj.setDescribesion("图表");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = getCusPermission("1", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = busCusPermission("2", null);
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public Long busCountPermission(CusPermissionDTO cusPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(cusPermissionDTO.getDescription())) {
            cusPermissionDTO.getConditions().add(Restrict.like("description", cusPermissionDTO.getDescription()));
        }
        cusPermissionDTO.getConditions().add(Restrict.in("idFlag", new String[]{"1", "2", "3", "4", "5"}));
        Long count = super.count(cusPermissionDTO);
        return count;
    }

    @Override
    public List<CusPermissionBO> busList(CusPermissionDTO cusPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(cusPermissionDTO.getDescription())) {
            cusPermissionDTO.getConditions().add(Restrict.like("description", cusPermissionDTO.getDescription()));
        }
        cusPermissionDTO.getConditions().add(Restrict.in("idFlag", new String[]{"1", "2", "3", "4", "5"}));
        List<CusPermission> list = super.findByCis(cusPermissionDTO, true);
        List<CusPermissionBO> bo = new ArrayList<>();
        for (CusPermission str : list) {
            CusPermissionBO temp = BeanTransform.copyProperties(str, CusPermissionBO.class);

            //先查询操作对象
            List<String> idList = new ArrayList<>();
            CusPermissionOperateDTO cpoDTO = new CusPermissionOperateDTO();
            cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", temp.getId()));
            List<CusPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
            List<CusOperateBO> coboList = null;
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
                    CusOperateBO cobo = new CusOperateBO();
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
    public Long countPermission(CusPermissionDTO cusPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(cusPermissionDTO.getDescription())) {
            cusPermissionDTO.getConditions().add(Restrict.like("description", cusPermissionDTO.getDescription()));
        }
        cusPermissionDTO.getConditions().add(Restrict.in("idFlag", new String[]{"1", "2"}));
        Long count = super.count(cusPermissionDTO);
        return count;
    }

    @Override
    public List<CusPermissionBO> list(CusPermissionDTO cusPermissionDTO) throws SerException {
        if (StringUtils.isNotBlank(cusPermissionDTO.getDescription())) {
            cusPermissionDTO.getConditions().add(Restrict.like("description", cusPermissionDTO.getDescription()));
        }
        cusPermissionDTO.getConditions().add(Restrict.in("idFlag", new String[]{"1", "2"}));
        List<CusPermission> list = super.findByCis(cusPermissionDTO, true);
        List<CusPermissionBO> bo = new ArrayList<>();
        for (CusPermission str : list) {
            CusPermissionBO temp = BeanTransform.copyProperties(str, CusPermissionBO.class);

            //先查询操作对象
            List<String> idList = new ArrayList<>();
            CusPermissionOperateDTO cpoDTO = new CusPermissionOperateDTO();
            cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", temp.getId()));
            List<CusPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
            List<CusOperateBO> coboList = null;
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
                    CusOperateBO cobo = new CusOperateBO();
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
    public CusPermissionBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        CusPermission cusPermission = super.findById(id);


        //先查询操作对象
        List<String> idList = new ArrayList<>();
        CusPermissionOperateDTO cpoDTO = new CusPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<CusPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
        List<CusOperateBO> coboList = new ArrayList<>();
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
                CusOperateBO cobo = new CusOperateBO();
                cobo.setId(op.getId());
                cobo.setOperator(op.getValue());
                coboList.add(cobo);
            }
        }
        CusPermissionBO bo = BeanTransform.copyProperties(cusPermission, CusPermissionBO.class);

        bo.setCusOperateBO(coboList);

        return bo;
    }

    @Override
    public List<OpinionBO> listOperateById(String id) throws SerException {
        List<OpinionBO> list = new ArrayList<>();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        CusPermission cusPermission = super.findById(id);
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
    public CusPermissionBO add(List<CusPermissionTO> cusPermissionTO) throws SerException {
        List<CusPermission> list = BeanTransform.copyProperties(cusPermissionTO, CusPermission.class, true);
        list = list.stream().filter(str -> StringUtils.isNotBlank(str.getIdFlag())).collect(Collectors.toList());
        List<String> idList = list.stream().map(CusPermission::getIdFlag).collect(Collectors.toList());
        String[] ids = idList.toArray(new String[idList.size()]);

        CusPermissionDTO dto = new CusPermissionDTO();
        dto.getConditions().add(Restrict.in("idFlag", ids));
        List<CusPermission> cusPermissionList = super.findByCis(dto);
        List<CusPermission> cusPermissionTempList = new ArrayList<>();
        if (cusPermissionList != null && cusPermissionList.size() > 0) {
            for (int i = 0; i < cusPermissionList.size(); i++) {
                CusPermission temp = cusPermissionList.get(i);
                Optional<CusPermission> cp = list.stream().filter(l -> l.getIdFlag().equals(temp.getIdFlag())).findFirst();
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
        List<String> listIdFlag = list.stream().map(CusPermission::getIdFlag).collect(Collectors.toList());
        List<String> databaseIdFlag = cusPermissionList.stream().map(CusPermission::getIdFlag).collect(Collectors.toList());

        List<String> addIdFlag = listIdFlag.stream().filter(str -> !databaseIdFlag.contains(str)).collect(Collectors.toList());

        //传进来的新增的添加
        List<CusPermission> addList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CusPermission cPermission = list.get(i);
            cPermission.setCreateTime(LocalDateTime.now());
            cPermission.setModifyTime(LocalDateTime.now());
            if (addIdFlag.contains(cPermission.getIdFlag())) {
                addList.add(cPermission);
            }
        }
        super.save(addList);
        //没有的删除
        dto = new CusPermissionDTO();
        dto.getConditions().add(Restrict.notIn("idFlag", ids));
        List<CusPermission> deleteList = super.findByCis(dto);
        super.remove(deleteList);

        return new CusPermissionBO();
    }

    @Override
    public CusPermissionBO edit(CusPermissionTO cusPermissionTO) throws SerException {
        if (StringUtils.isBlank(cusPermissionTO.getId())) {
            throw new SerException("id不能为空");
        }

        if (StringUtils.isBlank(cusPermissionTO.getDescription())) {
            throw new SerException("描述不能为空不能为空");
        }
        String[] operators = cusPermissionTO.getOperators();

        CusPermission temp = super.findById(cusPermissionTO.getId());
        temp.setDescription(cusPermissionTO.getDescription());
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        //先删除
        CusPermissionOperateDTO cpoDTO = new CusPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", temp.getId()));
        List<CusPermissionOperate> deleteList = cusPermissionOperateSer.findByCis(cpoDTO);
        if (deleteList != null && deleteList.size() > 0) {
            cusPermissionOperateSer.remove(deleteList);
        }
        if (operators != null && operators.length > 0) {
            List<CusPermissionOperate> list = new ArrayList<>();
            for (String operateId : operators) {
                CusPermissionOperate cpo = new CusPermissionOperate();
                cpo.setOperator(operateId);
                cpo.setCuspermissionId(temp.getId());
                list.add(cpo);
            }
            cusPermissionOperateSer.save(list);
        }

        return BeanTransform.copyProperties(temp, CusPermissionBO.class);
    }

    @Override
    public Boolean getCusPermission(String idFlag, UserBO user) throws SerException {
        String ss = RpcTransmit.getUserToken();
        Boolean flag = false;
        //但前用户
        UserBO userBO = new UserBO();
        if (null == user) {
            userBO = userAPI.currentUser();
        } else {
            userBO = user;
        }
        String userId = userBO.getId();
        if (StringUtils.isBlank(idFlag)) {
            throw new SerException("idFlag不能为空");
        }
        CusPermissionDTO dto = new CusPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        CusPermission cusPermission = super.findOne(dto);

        //先查询获操作对象
        List<String> idList = new ArrayList<>();
        CusPermissionOperateDTO cpoDTO = new CusPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<CusPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
        if (depart) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }

    @Override
    public Boolean busCusPermission(String idFlag, UserBO user) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flag = false;
        //但前用户
        UserBO userBO = new UserBO();
        if (null == user) {
            userBO = userAPI.currentUser();
        } else {
            userBO = user;
        }
        String userId = userBO.getId();
        if (StringUtils.isBlank(idFlag)) {
            throw new SerException("idFlag不能为空");
        }
        CusPermissionDTO dto = new CusPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        CusPermission cusPermission = super.findOne(dto);


        //先查询操作对象
        List<String> idList = new ArrayList<>();
        CusPermissionOperateDTO cpoDTO = new CusPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<CusPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
        Boolean positionFlag = positionDetailUserAPI.checkAsUserPosition(userId, operateIds);
//        Boolean positionFlag = positionDetailUserAPI.checkAsUserPosition(userId, operateIds);

        if (positionFlag) {
            flag = true;
        } else {
            flag = false;
        }
        RpcTransmit.transmitUserToken(userToken);
        String aa = RpcTransmit.getUserToken();
        return flag;
    }

    @Override
    public Boolean modCusPermission(String idFlag, UserBO user) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flag = false;
        //但前用户
        UserBO userBO = new UserBO();
        if (null == user) {
            userBO = userAPI.currentUser();
        } else {
            userBO = user;
        }
        String userId = userBO.getId();
        if (StringUtils.isBlank(idFlag)) {
            throw new SerException("idFlag不能为空");
        }
        CusPermissionDTO dto = new CusPermissionDTO();
        dto.getConditions().add(Restrict.eq("idFlag", idFlag));
        CusPermission cusPermission = super.findOne(dto);


        //先查询操作对象
        List<String> idList = new ArrayList<>();
        CusPermissionOperateDTO cpoDTO = new CusPermissionOperateDTO();
        cpoDTO.getConditions().add(Restrict.eq("cuspermissionId", cusPermission.getId()));
        List<CusPermissionOperate> operateList = cusPermissionOperateSer.findByCis(cpoDTO);
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
        Boolean moduleFlag = positionDetailUserAPI.checkAsUserModule(userId, operateIds);
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