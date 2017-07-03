package com.bjike.goddess.foreigntax.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.bo.TaxCollectBO;
import com.bjike.goddess.foreigntax.bo.TaxManagementBO;
import com.bjike.goddess.foreigntax.dto.TaxManagementDTO;
import com.bjike.goddess.foreigntax.entity.TaxManagement;
import com.bjike.goddess.foreigntax.enums.GuideAddrStatus;
import com.bjike.goddess.foreigntax.enums.PaymentStatus;
import com.bjike.goddess.foreigntax.excel.SonPermissionObject;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.TaxManagementTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 税金管理业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:40 ]
 * @Description: [ 税金管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "foreigntaxSerCache")
@Service
public class TaxManagementSerImpl extends ServiceImpl<TaxManagement, TaxManagementDTO> implements TaxManagementSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private AccountInfoManagementSer accountInfoManagementSer;
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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("taxmanagement");
        obj.setDescribesion("税金管理");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeAnswer = accountInfoManagementSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("accountinfomanagement");
        obj.setDescribesion("外账资料管理");
        if (flagSeeAnswer) {
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

    @Override
    public Long countTaxManagement(TaxManagementDTO taxManagementDTO) throws SerException {
        Long counts = super.count(taxManagementDTO);
        return counts;
    }

    @Override
    public TaxManagementBO getOne(String id) throws SerException {
        TaxManagement taxManagement = super.findById(id);
        return BeanTransform.copyProperties(taxManagement, TaxManagementBO.class);
    }

    @Override
    public List<TaxManagementBO> findListTaxManagement(TaxManagementDTO taxManagementDTO) throws SerException {
        checkSeeIdentity();
        viewTaxManagement(taxManagementDTO);
        List<TaxManagement> taxManagements = super.findByCis(taxManagementDTO, true);
        List<TaxManagementBO> taxManagementBOS = BeanTransform.copyProperties(taxManagements, TaxManagementBO.class);
        return taxManagementBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TaxManagementBO insertTaxManagement(TaxManagementTO taxManagementTO) throws SerException {
        checkAddIdentity();
        TaxManagement taxManagement = BeanTransform.copyProperties(taxManagementTO, TaxManagement.class, true);
        taxManagement.setCreateTime(LocalDateTime.now());
        taxManagement.setCompany(taxManagementTO.getCompany());
        taxManagement.setMonth(LocalDate.parse(taxManagementTO.getMonth()));
        taxManagement.setTaxType(taxManagementTO.getTaxType());
        taxManagement.setRate(taxManagement.getRate());
        taxManagement.setTax(taxManagement.getTax());
        taxManagement.setPaymentStatus(PaymentStatus.DIDPAY);
        taxManagement.setCreateTime(LocalDateTime.now());
        super.save(taxManagement);
        return BeanTransform.copyProperties(taxManagement, TaxManagementBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TaxManagementBO editTaxManagement(TaxManagementTO taxManagementTO) throws SerException {
        checkAddIdentity();
        TaxManagement taxManagement = super.findById(taxManagementTO.getId());
        BeanTransform.copyProperties(taxManagementTO, taxManagement, true);
        taxManagement.setPaymentDate(LocalDate.parse(taxManagementTO.getPaymentDate()));
        taxManagement.setPaymentUnit(taxManagementTO.getPaymentUnit());
        taxManagement.setPaymentStatus(PaymentStatus.DUTYPAID);
        taxManagement.setModifyTime(LocalDateTime.now());
        super.update(taxManagement);
        return BeanTransform.copyProperties(taxManagementTO, TaxManagementBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeTaxManagement(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    public void viewTaxManagement(TaxManagementDTO taxManagementDTO) throws SerException {
        /**
         * 公司
         */
        if (StringUtils.isNotBlank(taxManagementDTO.getCompany())) {
            taxManagementDTO.getConditions().add(Restrict.eq("company", taxManagementDTO.getCompany()));
        }
        /**
         * 所属月份
         */
        if (StringUtils.isNotBlank(taxManagementDTO.getMonth())) {
            taxManagementDTO.getConditions().add(Restrict.eq("month", taxManagementDTO.getMonth()));
        }
        /**
         * 税种
         */
        if (StringUtils.isNotBlank(taxManagementDTO.getTaxType())) {
            taxManagementDTO.getConditions().add(Restrict.eq("taxType", taxManagementDTO.getTaxType()));
        }
    }

    @Override
    public List<TaxCollectBO> collectTaxManagement(String[] company) throws SerException {
        if (company == null || company.length <= 0) {
            throw new SerException("汇总失败，请选择地区");
        }
        String[] companyTemp = new String[company.length];
        for (int i = 0; i < company.length; i++) {
            companyTemp[i] = "'" + company[i] + "'";
        }
        String companyStr = StringUtils.join(companyTemp, ",");

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * from ");
        sb.append(" (SELECT company,month AS month,taxType AS taxType,rate AS rate, ");
        sb.append(" tax AS tax FROM foreigntax_taxmanagement a WHERE company IN (%s) ");
        sb.append(" GROUP BY month,taxType,rate,tax,company ORDER BY company)A ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计'AS company,NULL AS month,NULL AS taxType, ");
        sb.append(" NULL AS rate,sum(tax) AS tax FROM ");
        sb.append(" (SELECT company,month AS month,taxType AS taxType,rate AS rate, ");
        sb.append(" tax AS tax FROM foreigntax_taxmanagement a WHERE company IN (%s) ");
        sb.append(" GROUP BY month,taxType,rate,tax,company ORDER BY company)A ");
        String sql = sb.toString();
        sql = String.format(sql, companyStr, companyStr);
        String[] fields = new String[]{"company", "month", "taxType", "rate", "tax"};
        List<TaxCollectBO> taxCollectBOS = super.findBySql(sql, TaxCollectBO.class, fields);
        return taxCollectBOS;

    }

    @Override
    public List<String> getCompany() throws SerException {
        String[] fields = new String[]{"company"};
        List<TaxManagementBO> taxManagementBOS = super.findBySql("select distinct company from foreigntax_taxmanagement group by company order by company asc ", TaxManagementBO.class, fields);

        List<String> companyList = taxManagementBOS.stream().map(TaxManagementBO::getCompany)
                .filter(company -> (StringUtils.isNotBlank(company))).distinct().collect(Collectors.toList());


        return companyList;
    }

    @Override
    public List<TaxManagementBO> listByCompany(String company, String monthStart, String monthEnd) throws SerException {
        TaxManagementDTO dto = new TaxManagementDTO();
//        List<String> con = Arrays.asList(monthStart,monthEnd);
        String[] con = new String[]{monthStart, monthEnd};
        if (StringUtils.isNotBlank(company)) {
            dto.getConditions().add(Restrict.eq("company", company));
            dto.getConditions().add(Restrict.between("month", con));
        }
        List<TaxManagement> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, TaxManagementBO.class);
    }
}