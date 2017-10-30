package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.businessproject.api.BaseInfoManageAPI;
import com.bjike.goddess.businessproject.bo.BaseInfoManageBO;
import com.bjike.goddess.businessproject.dto.BaseInfoManageDTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingCollectBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectOutsourcingDTO;
import com.bjike.goddess.contractcommunicat.entity.ProjectOutsourcing;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;
import com.bjike.goddess.contractcommunicat.enums.GuideAddrStatus;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.excel.ProjectOutsourcingExcel;
import com.bjike.goddess.contractcommunicat.excel.SonPermissionObject;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.ExportExcelTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.to.ProjectOutsourcingTO;
import com.bjike.goddess.market.api.MarketInfoRecordAPI;
import com.bjike.goddess.market.bo.MarketInfoRecordBO;
import com.bjike.goddess.market.dto.MarketInfoRecordDTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目外包洽谈业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-18T09:24:12.833 ]
 * @Description: [ 项目外包洽谈业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectOutsourcingSerCache")
@Service
public class ProjectOutsourcingSerImpl extends ServiceImpl<ProjectOutsourcing, ProjectOutsourcingDTO> implements ProjectOutsourcingSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    @Autowired
    private ModuleAPI moduleAPI;

    @Autowired
    private BaseInfoManageAPI baseInfoManageAPI;

    @Autowired
    private MarketInfoRecordAPI marketInfoRecordAPI;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectOutsourcingBO saveProjectOutsourcing(ProjectOutsourcingTO to) throws SerException {
        getCusPermission();

        isExist(to, null);
        ProjectOutsourcing model = BeanTransform.copyProperties(to, ProjectOutsourcing.class, true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, ProjectOutsourcingBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectOutsourcingBO editProjectOutsourcing(ProjectOutsourcingTO to) throws SerException {

        getCusPermission();

        if (!StringUtils.isEmpty(to.getId())) {
            ProjectOutsourcing model = super.findById(to.getId());
            if (model != null) {
                isExist(to, null);
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("编辑对象不能为空");
            }

        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, ProjectOutsourcingBO.class);
    }

    //校验字段是否存在
    public void isExist(ProjectOutsourcingTO to, Integer row) throws SerException {
        ProjectOutsourcingDTO dto = null;
        if (!StringUtils.isEmpty(to.getContractExtProject())) {
            dto = new ProjectOutsourcingDTO();
            dto.getConditions().add(Restrict.eq("contractExtProject", to.getContractExtProject()));
            List<ProjectOutsourcing> list = super.findByCis(dto);
            if (list != null && !list.isEmpty()) {
                String msg = "合同外部项目名称已经存在!";
                if (row == null) {
                    if (list.get(0).getId().equals(to.getId())) {

                    } else {
                        throw new SerException(msg);
                    }
                } else {
                    throw new SerException("第" + row + "行的" + msg);
                }

            }
        }
        if (!StringUtils.isEmpty(to.getContractExtCode())) {
            dto = new ProjectOutsourcingDTO();
            dto.getConditions().add(Restrict.eq("contractExtCode", to.getContractExtCode()));
            List<ProjectOutsourcing> list = super.findByCis(dto);
            if (list != null && !list.isEmpty()) {
                String msg = "合同外部编号已经存在!";
                if (row == null) {
                    if (list.get(0).getId().equals(to.getId())) {

                    } else {
                        throw new SerException(msg);
                    }
                } else {
                    throw new SerException("第" + row + "行的" + msg);
                }
            }
        }
        if (!StringUtils.isEmpty(to.getContractInProject())) {
            dto = new ProjectOutsourcingDTO();
            dto.getConditions().add(Restrict.eq("contractInProject", to.getContractInProject()));
            List<ProjectOutsourcing> list = super.findByCis(dto);
            if (list != null && !list.isEmpty()) {
                String msg = "内部项目名称已经存在!";
                if (row == null) {
                    if (list.get(0).getId().equals(to.getId())) {

                    } else {
                        throw new SerException(msg);
                    }
                } else {
                    throw new SerException("第" + row + "行的" + msg);
                }
            }
        }
        if (!StringUtils.isEmpty(to.getContractInCode())) {
            dto = new ProjectOutsourcingDTO();
            dto.getConditions().add(Restrict.eq("contractInCode", to.getContractInCode()));
            List<ProjectOutsourcing> list = super.findByCis(dto);
            if (list != null && !list.isEmpty()) {
                String msg = "内部项目编号已经存在!";
                if (row == null) {
                    if (list.get(0).getId().equals(to.getId())) {

                    } else {
                        throw new SerException(msg);
                    }
                } else {
                    throw new SerException("第" + row + "行的" + msg);
                }
            }
        }
        if (!StringUtils.isEmpty(to.getOutsourcingProject())) {
            dto = new ProjectOutsourcingDTO();
            dto.getConditions().add(Restrict.eq("outsourcingProject", to.getOutsourcingProject()));
            List<ProjectOutsourcing> list = super.findByCis(dto);
            if (list != null && !list.isEmpty()) {
                String msg = "外包项目名称已经存在!";
                if (row == null) {
                    if (list.get(0).getId().equals(to.getId())) {

                    } else {
                        throw new SerException(msg);
                    }
                } else {
                    throw new SerException("第" + row + "行的" + msg);
                }
            }
        }
        if (!StringUtils.isEmpty(to.getOutsourcingCode())) {
            dto = new ProjectOutsourcingDTO();
            dto.getConditions().add(Restrict.eq("outsourcingCode", to.getOutsourcingCode()));
            List<ProjectOutsourcing> list = super.findByCis(dto);
            if (list != null && !list.isEmpty()) {
                String msg = "外包项目编号已经存在!";
                if (row == null) {
                    if (list.get(0).getId().equals(to.getId())) {

                    } else {
                        throw new SerException(msg);
                    }
                } else {
                    throw new SerException("第" + row + "行的" + msg);
                }

            }
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectOutsourcingBO> pageList(ProjectOutsourcingDTO dto) throws SerException {

        getCusPermission();

        dto.getSorts().add("createTime=desc");
        if (dto.getCommunicateUser() != null) {
            dto.getConditions().add(Restrict.like("communicateUser", dto.getCommunicateUser()));
        }
        if (dto.getCommunicateObj() != null) {
            dto.getConditions().add(Restrict.like("communicateObj", dto.getCommunicateObj()));
        }
        if (dto.getCommunicateResult() != null) {
            dto.getConditions().add(Restrict.eq("projectResult", dto.getCommunicateResult()));
        }

        List<ProjectOutsourcingBO> pageList = BeanTransform.copyProperties(super.findByPage(dto), ProjectOutsourcingBO.class);
        return pageList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectOutsourcingCollectBO> collect(CollectConditionTO to) throws SerException {

        getCusPermission();

        ProjectOutsourcingDTO dto = new ProjectOutsourcingDTO();
        dto.getSorts().add("createTime=desc");
        if (!StringUtils.isEmpty(to.getContractInProject())) {
            dto.getConditions().add(Restrict.like("contractInProject", to.getContractInProject()));
        }
        if (!StringUtils.isEmpty(to.getStartTime())) {
            dto.getConditions().add(Restrict.gt("createTime", to.getStartTime()));
        }
        if (!StringUtils.isEmpty(to.getEndTime())) {
            dto.getConditions().add(Restrict.lt("createTime", to.getEndTime()));
        }
        return setCollectField(super.findByPage(dto));

    }

    @Override
    public void leadExcel(List<ProjectOutsourcingTO> toList) throws SerException {

        getCusPermission();

        for (int i = 1; i <= toList.size(); i++) {
            isExist(toList.get(i - 1), i);
        }
        List<ProjectOutsourcing> list = BeanTransform.copyProperties(toList, ProjectOutsourcing.class, true);
        super.save(list);
    }

    @Override
    public byte[] exportExcel(String contractInProject, String startDate, String endDate) throws SerException {

        getCusPermission();

        ProjectOutsourcingDTO dto = new ProjectOutsourcingDTO();
        if (!StringUtils.isEmpty(contractInProject)) {
            dto.getConditions().add(Restrict.eq("contractInProject", contractInProject));
        }
        if (!StringUtils.isEmpty(startDate)) {
            dto.getConditions().add(Restrict.gt("communicateDate", startDate));
        }
        if (!StringUtils.isEmpty(endDate)) {
            dto.getConditions().add(Restrict.lt("communicateDate", endDate));
        }
        List<ProjectOutsourcing> list = super.findByCis(dto);
        List<ProjectOutsourcingExcel> excelList = new ArrayList<ProjectOutsourcingExcel>();
        if (!CollectionUtils.isEmpty(list)) {
            for (ProjectOutsourcing model : list) {
                ProjectOutsourcingExcel excel = new ProjectOutsourcingExcel();
                BeanUtils.copyProperties(model, excel);
                excelList.add(excel);
            }
        } else {
            excelList.add(new ProjectOutsourcingExcel());
        }

        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(excelList, excel);
        return bytes;
    }

    @Override
    public List<ProjectOutsourcingBO> prjects() throws SerException {
        String sql = "select distinct contractInProject from contractcommunicate_projectoutsourcing ";
        List<ProjectOutsourcingBO> list = super.findBySql(sql, ProjectOutsourcingBO.class, new String[]{"contractInProject"});

        return list;
    }


    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = to.getGuideAddrStatus();
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
            case DELETE:
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

    @Override
    public byte[] exportExcelModule() throws SerException {
        Excel excel = new Excel(0, 2);
        List<ProjectOutsourcingExcel> list = new ArrayList<ProjectOutsourcingExcel>();
        list.add(new ProjectOutsourcingExcel());
        byte[] bytes = ExcelUtil.clazzToExcel(list, excel);
        return bytes;
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（部门级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    //设置汇总字段
    public List<ProjectOutsourcingCollectBO> setCollectField(List<ProjectOutsourcing> list) throws SerException {
        List<ProjectOutsourcingBO> boList = BeanTransform.copyProperties(list, ProjectOutsourcingBO.class);

        List<ProjectOutsourcingCollectBO> returnBoList = BeanTransform.copyProperties(list, ProjectOutsourcingCollectBO.class);
        Integer totalCooperate = 0;
        Integer totalTrail = 0;
        Integer totalAbandon = 0;
        Double totalCostBudget = 0.0;
        if (returnBoList != null && !returnBoList.isEmpty()) {
            for (ProjectOutsourcingCollectBO bo : returnBoList) {
                if (bo.getProjectResult() == CommunicateResult.COOPERATE) {
                    bo.setCooperate("项目合作");
                    totalCooperate++;
                } else if (bo.getProjectResult() == CommunicateResult.TRAIL) {
                    bo.setTrail("项目跟进");
                    totalTrail++;
                } else if (bo.getProjectResult() == CommunicateResult.ABANDON) {
                    bo.setAbandon("项目丢弃");
                    totalAbandon++;
                }
            }
            totalCostBudget = boList.stream().mapToDouble(p -> p.getCostBudget()).sum();
        } else {
            returnBoList = new ArrayList<ProjectOutsourcingCollectBO>();
        }

        ProjectOutsourcingCollectBO total = new ProjectOutsourcingCollectBO("合计", null, null, null, null, null,
                totalCostBudget, totalCooperate.toString(), totalTrail.toString(), totalAbandon.toString());
        returnBoList.add(total);

        return returnBoList;
    }

    public void getCusPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
//        Boolean permission = cusPermissionSer.getCusPermission("1");
//
//        if (!permission) {
//            throw new SerException("该模块只有商务部可操作，您的帐号尚无权限");
//        }
//    }
    }

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }



    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<BaseInfoManageBO> listBaseInfoManage() throws SerException {
        List<BaseInfoManageBO> list = new ArrayList<>(0);
        if(moduleAPI.isCheck("businessproject")) {
            BaseInfoManageDTO dto = new BaseInfoManageDTO();
            list = baseInfoManageAPI.listBaseInfoManage(dto);
        }
        return list;
    }

    @Override
    public List<MarketInfoRecordBO> findProject() throws SerException {
        List<MarketInfoRecordBO> list = new ArrayList<>(0);
        if(moduleAPI.isCheck("market")){
            MarketInfoRecordDTO dto = new MarketInfoRecordDTO();
            list = marketInfoRecordAPI.findListRecord(dto);
        }
        return list;
    }

}

