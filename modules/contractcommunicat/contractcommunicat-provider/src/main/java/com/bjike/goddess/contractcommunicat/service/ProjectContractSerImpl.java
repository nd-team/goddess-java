package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractCollectBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectContractDTO;
import com.bjike.goddess.contractcommunicat.entity.ProjectContract;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;
import com.bjike.goddess.contractcommunicat.enums.GuideAddrStatus;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.excel.ProjectContractExcel;
import com.bjike.goddess.contractcommunicat.excel.SonPermissionObject;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.to.ProjectContractTO;
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
 * 项目承包洽谈业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.940 ]
 * @Description: [ 项目承包洽谈业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectContractSerCache")
@Service
public class ProjectContractSerImpl extends ServiceImpl<ProjectContract, ProjectContractDTO> implements ProjectContractSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ProjectOutsourcingSer projectOutsourcingSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectContractBO saveProjectContract(ProjectContractTO to) throws SerException {

//        getCusPermission();

//        isExist(to, null);
        ProjectContract model = BeanTransform.copyProperties(to, ProjectContract.class, true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, ProjectContractBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectContractBO editProjectContract(ProjectContractTO to) throws SerException {

        getCusPermission();

        if (!StringUtils.isEmpty(to.getId())) {
            ProjectContract model = super.findById(to.getId());
            if (model != null) {
                isExist(to, null);
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, ProjectContractBO.class);
    }

    //校验字段是否存在
    public void isExist(ProjectContractTO to, Integer row) throws SerException {
        ProjectContractDTO dto = null;
        if (!StringUtils.isEmpty(to.getContractExtProject())) {
            dto = new ProjectContractDTO();
            dto.getConditions().add(Restrict.eq("contractExtProject", to.getContractExtProject()));
            List<ProjectContract> list = super.findByCis(dto);
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
            dto = new ProjectContractDTO();
            dto.getConditions().add(Restrict.eq("contractExtCode", to.getContractExtCode()));
            List<ProjectContract> list = super.findByCis(dto);
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
            dto = new ProjectContractDTO();
            dto.getConditions().add(Restrict.eq("contractInProject", to.getContractInProject()));
            List<ProjectContract> list = super.findByCis(dto);
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
            dto = new ProjectContractDTO();
            dto.getConditions().add(Restrict.eq("contractInCode", to.getContractInCode()));
            List<ProjectContract> list = super.findByCis(dto);
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
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectContractBO> pageList(ProjectContractDTO dto) throws SerException {

        getCusPermission();

        dto.getSorts().add("createTime=desc");
        if (!StringUtils.isEmpty(dto.getCommunicateUser())) {
            dto.getConditions().add(Restrict.like("communicateUser", dto.getCommunicateUser()));
        }
        if (!StringUtils.isEmpty(dto.getCommunicateObj())) {
            dto.getConditions().add(Restrict.like("communicateObj", dto.getCommunicateObj()));
        }
        if (dto.getCommunicateResult() != null) {
            dto.getConditions().add(Restrict.eq("projectResult", dto.getCommunicateResult()));
        }

        List<ProjectContractBO> pageList = BeanTransform.copyProperties(super.findByPage(dto), ProjectContractBO.class);
        return pageList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectContractCollectBO> collect(CollectConditionTO to) throws SerException {

        getCusPermission();

        ProjectContractDTO dto = new ProjectContractDTO();
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
    @Transactional(rollbackFor = SerException.class)
    public void leadExcel(List<ProjectContractTO> toList) throws SerException {

        getCusPermission();

        for (int i = 1; i <= toList.size(); i++) {
            isExist(toList.get(i - 1), i);
        }
        List<ProjectContract> list = BeanTransform.copyProperties(toList, ProjectContract.class, true);
        super.save(list);
    }

    @Override
    public byte[] exportExcel(String contractInProject, String startDate, String endDate) throws SerException {

        getCusPermission();

        ProjectContractDTO dto = new ProjectContractDTO();
        if (!StringUtils.isEmpty(contractInProject)) {
            dto.getConditions().add(Restrict.eq("contractInProject", contractInProject));
        }
        if (!StringUtils.isEmpty(startDate)) {
            dto.getConditions().add(Restrict.gt("communicateDate", startDate));
        }
        if (!StringUtils.isEmpty(endDate)) {
            dto.getConditions().add(Restrict.lt("communicateDate", endDate));
        }
        List<ProjectContract> list = super.findByCis(dto);
        List<ProjectContractExcel> excelList = new ArrayList<ProjectContractExcel>();
        if (!CollectionUtils.isEmpty(list)) {
            for (ProjectContract model : list) {
                ProjectContractExcel excel = new ProjectContractExcel();
                BeanUtils.copyProperties(model, excel);
                excelList.add(excel);
            }
        } else {
            excelList.add(new ProjectContractExcel());
        }

        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(excelList, excel);
        return bytes;
    }

    //查询内部项目名称
    @Override
    public List<ProjectContractBO> projects() throws SerException {

        String sql = "select distinct contractInProject from contractcommunicate_projectcontract ";
        List<ProjectContractBO> list = super.findBySql(sql, ProjectContractBO.class, new String[]{"contractInProject"});

        return list;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();

        Boolean flagAddSign = guideSeeIdentity();
        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("contract");
        obj.setDescribesion("项目承包洽谈");
        if (flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

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
        List<ProjectContractExcel> list = new ArrayList<ProjectContractExcel>();
        list.add(new ProjectContractExcel());
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
    public List<ProjectContractCollectBO> setCollectField(List<ProjectContract> list) throws SerException {


        List<ProjectContractBO> boList = BeanTransform.copyProperties(list, ProjectContractBO.class);

        List<ProjectContractCollectBO> returnBoList = BeanTransform.copyProperties(list, ProjectContractCollectBO.class);
        Integer totalCooperate = 0;
        Integer totalTrail = 0;
        Integer totalAbandon = 0;
        Double totalCostBudget = 0.0;
        if (returnBoList != null && !returnBoList.isEmpty()) {
            for (ProjectContractCollectBO bo : returnBoList) {
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
            totalCostBudget = returnBoList.stream().mapToDouble(p -> p.getCostBudget()).sum();
        } else {
            returnBoList = new ArrayList<ProjectContractCollectBO>();
        }

        ProjectContractCollectBO total = new ProjectContractCollectBO("合计", null, null, null, null,
                totalCostBudget, totalCooperate.toString(), totalTrail.toString(), totalAbandon.toString());
        returnBoList.add(total);

        return returnBoList;
    }

    public void getCusPermission() throws SerException {

        Boolean permission = cusPermissionSer.getCusPermission("1");

        if (!permission) {
            throw new SerException("该模块只有商务部可操作，您的帐号尚无权限");
        }
    }
}