package com.bjike.goddess.socialinsurance.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.socialinsurance.bo.SocialInsuranceBO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceDTO;
import com.bjike.goddess.socialinsurance.entity.SocialInsurance;
import com.bjike.goddess.socialinsurance.enums.GuideAddrStatus;
import com.bjike.goddess.socialinsurance.excel.SocialInsuranceExportExcel;
import com.bjike.goddess.socialinsurance.excel.SocialInsuranceExportExcelTemplate;
import com.bjike.goddess.socialinsurance.to.GuidePermissionTO;
import com.bjike.goddess.socialinsurance.to.SocialInsuranceTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 社会保险管理业务实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-21 04:23 ]
 * @Description: [ 社会保险管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "socialinsuranceSerCache")
@Service
public class SocialInsuranceSerImpl extends ServiceImpl<SocialInsurance, SocialInsuranceDTO> implements SocialInsuranceSer {

    @Autowired
    UserAPI userAPI;
    @Autowired
    CusPermissionSer cusPermissionSer;

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
            flag = cusPermissionSer.getCusPermission("1", null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对添加修改删除审核权限（部门级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1", null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

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
            flag = cusPermissionSer.getCusPermission("1", null);
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
            flag = cusPermissionSer.getCusPermission("2", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideManageIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guidePlanIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.modCusPermission("2", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideBudgetIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.modCusPermission("2", null);
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
            case MANAGER:
                flag = guideManageIdentity();
                break;
            case PLAN:
                flag = guidePlanIdentity();
                break;
            case BUDGET:
                flag = guideBudgetIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }


    @Override
    public Long count(SocialInsuranceDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public List<SocialInsuranceBO> list(SocialInsuranceDTO dto) throws SerException {
        List<SocialInsurance> entities = super.findByCis(dto);
        return BeanTransform.copyProperties(entities, SocialInsuranceBO.class);
    }

    @Override
    public SocialInsuranceBO getOne(String id) throws SerException {
        SocialInsurance entity = super.findById(id);
        return BeanTransform.copyProperties(entity, SocialInsuranceBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void add(SocialInsuranceTO to) throws SerException {

        SocialInsurance entity = BeanTransform.copyProperties(to, SocialInsurance.class);
        entity.setTaxDate(entity.getTaxDate() + "01");
        super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(SocialInsuranceTO to) throws SerException {
        SocialInsurance entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("更新实体不存在");
        }
        SocialInsurance newEntity = BeanTransform.copyProperties(to, SocialInsurance.class);
        newEntity.setModifyTime(LocalDateTime.now());
        newEntity.setCreateTime(entity.getCreateTime());
        entity.setTaxDate(newEntity.getTaxDate() + "01");
        super.update(newEntity);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void importExcel(List<SocialInsuranceTO> tos) throws SerException {
        List<SocialInsurance> entites = BeanTransform.copyProperties(tos, SocialInsurance.class);
        super.save(entites);
    }

    @Override
    public byte[] exportExcel(SocialInsuranceDTO dto) throws SerException {

        List<SocialInsurance> list = super.findByCis(dto);
        List<SocialInsuranceExportExcel> exports = new ArrayList<>();
        list.stream().forEach(str -> {
            SocialInsuranceExportExcel export = BeanTransform.copyProperties(str, SocialInsuranceExportExcel.class);
            export.setTaxDate(export.getTaxDate() == null ? null : export.getTaxDate().substring(0, 6));
            exports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }

    @Override
    public byte[] exportExcelTemplate() throws SerException {
        List<SocialInsuranceExportExcelTemplate> templateExcels = new ArrayList<>();
        SocialInsuranceExportExcelTemplate templateExcel = new SocialInsuranceExportExcelTemplate();
        templateExcel.setTaxDate("201712");
        templateExcel.setTaxpayer("北京艾佳天诚");
        templateExcel.setCompanySSNumber("67373");
        templateExcel.setName("张三");
        templateExcel.setIdCard("441581194407023965");
        templateExcel.setCardName("身份证");
        templateExcel.setPersonalSSNumber("6737355");
        templateExcel.setPensionInsuranceWage(6000.0);
        templateExcel.setPensionInsuranceCompany(300.0);
        templateExcel.setPensionInsurancePerson(300.0);
        templateExcel.setInjuryInsuranceWage(6000.0);
        templateExcel.setInjuryInsuranceCompany(300.0);
        templateExcel.setInjuryInsurancePerson(300.0);
        templateExcel.setUnemploymentInsuranceWage(6000.0);
        templateExcel.setUnemploymentInsuranceCompany(300.0);
        templateExcel.setUnemploymentInsurancePerson(300.0);
        templateExcel.setMedicalInsuranceWage(6000.0);
        templateExcel.setMedicalInsuranceCompany(300.0);
        templateExcel.setMedicalInsurancePerson(300.0);
        templateExcel.setDiseaseInsuranceWage(6000.0);
        templateExcel.setDiseaseInsuranceCompany(300.0);
        templateExcel.setDiseaseInsurancePerson(300.0);
        templateExcel.setFertilityInsuranceWage(6000.0);
        templateExcel.setFertilityInsuranceCompany(300.0);
        templateExcel.setFertilityInsurancePerson(300.0);
        templateExcel.setCompanyTotal(1800.0);
        templateExcel.setPersonalTotal(1800.0);
        templateExcel.setAmountDue(3600.0);

        templateExcels.add(templateExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(templateExcels, excel);
        return bytes;
    }
}