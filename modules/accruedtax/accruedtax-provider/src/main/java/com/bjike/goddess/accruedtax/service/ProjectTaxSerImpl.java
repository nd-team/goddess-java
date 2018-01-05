package com.bjike.goddess.accruedtax.service;

import com.bjike.goddess.accruedtax.bo.ProjectTaxBO;
import com.bjike.goddess.accruedtax.dto.ProjectTaxDTO;
import com.bjike.goddess.accruedtax.entity.ProjectTax;
import com.bjike.goddess.accruedtax.enums.GuideAddrStatus;
import com.bjike.goddess.accruedtax.to.GuidePermissionTO;
import com.bjike.goddess.accruedtax.to.ProjectTaxTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目上税金业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-24 09:18 ]
 * @Description: [ 项目上税金业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "accruedtaxSerCache")
@Service
public class ProjectTaxSerImpl extends ServiceImpl<ProjectTax, ProjectTaxDTO> implements ProjectTaxSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
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
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
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
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SHARE:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countProjectTax(ProjectTaxDTO projectTaxDTO) throws SerException {
        if( StringUtils.isNotBlank(projectTaxDTO.getProject())){
            projectTaxDTO.getConditions().add(Restrict.eq("project",projectTaxDTO.getProject()));
        }
        Long count = super.count(projectTaxDTO);
        return count;
    }

    @Override
    public ProjectTaxBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        ProjectTax payTax = super.findById(id);
        return BeanTransform.copyProperties(payTax, ProjectTaxBO.class);
    }

    @Override
    public List<ProjectTaxBO> listProjectTax(ProjectTaxDTO projectTaxDTO) throws SerException {
        checkSeeIdentity();
        if( StringUtils.isNotBlank(projectTaxDTO.getProject())){
            projectTaxDTO.getConditions().add(Restrict.eq("project",projectTaxDTO.getProject()));
        }
        projectTaxDTO.getSorts().add("createTime=desc");
        List<ProjectTax> list = super.findByCis(projectTaxDTO, true);

        return BeanTransform.copyProperties(list, ProjectTaxBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectTaxBO addProjectTax(ProjectTaxTO projectTaxTO) throws SerException {
        checkAddIdentity();
        if(StringUtils.isBlank(projectTaxTO.getPayTaxId())){
            throw  new SerException("应交税金id不能为空");
        }
        ProjectTax projectTax = BeanTransform.copyProperties(projectTaxTO, ProjectTax.class, true);

        projectTax.setRate( projectTax.getActualTax()/projectTax.getPlanTax());
        projectTax.setBalance( projectTax.getActualTax() - projectTax.getPlanTax() );
        projectTax.setCreateTime(LocalDateTime.now());
        super.save(projectTax);
        return BeanTransform.copyProperties(projectTax, ProjectTaxBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectTaxBO editProjectTax(ProjectTaxTO projectTaxTO) throws SerException {
        checkAddIdentity();
        ProjectTax projectTax = BeanTransform.copyProperties(projectTaxTO, ProjectTax.class, true);
        ProjectTax cusLevel = super.findById(projectTaxTO.getId());

        BeanUtils.copyProperties(projectTax, cusLevel, "id", "createTime","payTaxId");
        cusLevel.setRate( cusLevel.getActualTax()/cusLevel.getPlanTax());
        cusLevel.setBalance( cusLevel.getActualTax() - cusLevel.getPlanTax() );
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(projectTax, ProjectTaxBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteProjectTax(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public List<ProjectTaxBO> collectCompany(ProjectTaxDTO projectTaxDTO) throws SerException {
        checkSeeIdentity();
        String project = projectTaxDTO.getProject();
        String[] field = new String[]{"project","targetTax","planTax","actualTax","rate","balance"};
        String sql = "select project , sum(targetTax) as targetTax, sum(planTax) as planTax, sum(actualTax) as actualTax " +
                " ,(sum(actualTax)/sum(planTax)) as rate , (sum(actualTax)-sum(planTax)) as balance from accruedtax_projecttax where 1=1  ";
        if( StringUtils.isNotBlank(project)){
            field = new String[]{"project","taxDate","targetTax","planTax","actualTax","rate","balance"};
            sql = " select project , taxDate , targetTax , planTax , actualTax , rate , balance from accruedtax_projecttax where 1=1 and project ='"+project+"' ";
        }
        if( StringUtils.isNotBlank(projectTaxDTO.getStartTime()) && StringUtils.isNotBlank(projectTaxDTO.getEndTime()) ){
            LocalDate start = LocalDate.parse(projectTaxDTO.getStartTime());
            LocalDate end = LocalDate.parse(projectTaxDTO.getEndTime());
            sql = sql +" and taxDate between '"+start+"' and '"+end+"' ";
        }
        if(StringUtils.isBlank(project)){
            sql = sql + " group by project ";
        }
        List<ProjectTax> list = super.findBySql(sql , ProjectTax.class, field);
        List<ProjectTaxBO> listBO = BeanTransform.copyProperties(list,ProjectTaxBO.class);
        if( listBO!= null && listBO.size()>0 ){
            listBO.stream().forEach(str->{
                if( StringUtils.isNotBlank(projectTaxDTO.getStartTime()) && StringUtils.isNotBlank(projectTaxDTO.getEndTime()) ) {
                    str.setTaxDate(projectTaxDTO.getStartTime() + "至" + projectTaxDTO.getEndTime());
                }else{
                    str.setTaxDate(  "--" );
                }
            });
        }

        return listBO;
    }

    @Override
    public List<ProjectTaxBO> collectTaxType(ProjectTaxDTO projectTaxDTO) throws SerException {
        checkSeeIdentity();
        String taxType = projectTaxDTO.getTaxType();
        String[] field = new String[]{"taxType","targetTax","planTax","actualTax","rate","balance"};
        String sql = "select taxType , sum(targetTax) as targetTax, sum(planTax) as planTax, sum(actualTax) as actualTax " +
                ", (sum(actualTax)/sum(planTax)) as rate , (sum(actualTax)-sum(planTax)) as balance from accruedtax_projecttax where 1=1  ";
        if( StringUtils.isNotBlank(taxType)){
            field = new String[]{"taxType","taxDate","targetTax","planTax","actualTax","rate","balance"};
            sql = " select taxType , taxDate , targetTax , planTax , actualTax , rate , balance from accruedtax_projecttax where 1=1 and taxType ='"+taxType+"' ";
        }
        if( StringUtils.isNotBlank(projectTaxDTO.getStartTime()) && StringUtils.isNotBlank(projectTaxDTO.getEndTime()) ){
            LocalDate start = LocalDate.parse(projectTaxDTO.getStartTime());
            LocalDate end = LocalDate.parse(projectTaxDTO.getEndTime());
            sql = sql +" and taxDate between '"+start+"' and '"+end+"' ";
        }
        if( StringUtils.isBlank(taxType)){
            sql = sql + " group by taxType ";
        }
        List<ProjectTax> list = super.findBySql(sql , ProjectTax.class, field);
        List<ProjectTaxBO> listBO = BeanTransform.copyProperties(list,ProjectTaxBO.class);
        if( listBO!= null && listBO.size()>0 ){
            listBO.stream().forEach(str->{
                if( StringUtils.isNotBlank(projectTaxDTO.getStartTime()) && StringUtils.isNotBlank(projectTaxDTO.getEndTime()) ) {
                    str.setTaxDate(projectTaxDTO.getStartTime() + "至" + projectTaxDTO.getEndTime());
                }else{
                    str.setTaxDate(  "--" );
                }
            });
        }

        return listBO;
    }

    @Override
    public List<String> listProject() throws SerException {
        String [] field = new String[]{"project"};
        String sql = "select project  from accruedtax_projecttax group by project ";
        List<ProjectTax> list = super.findBySql( sql , ProjectTax.class, field );
        List<String> companyList = list.stream().map(ProjectTax::getProject).collect(Collectors.toList());
        return companyList;
    }

    @Override
    public List<String> listTaxType() throws SerException {
        String [] field = new String[]{"taxType"};
        String sql = "select taxType  from accruedtax_projecttax group by taxType ";
        List<ProjectTax> list = super.findBySql( sql , ProjectTax.class, field );
        List<String> companyList = list.stream().map(ProjectTax::getTaxType).collect(Collectors.toList());
        return companyList;
    }

}