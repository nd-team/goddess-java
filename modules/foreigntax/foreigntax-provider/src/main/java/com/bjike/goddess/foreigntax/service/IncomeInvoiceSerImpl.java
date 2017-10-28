package com.bjike.goddess.foreigntax.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.bo.CompareCollectBO;
import com.bjike.goddess.foreigntax.bo.IncomeInvoiceBO;
import com.bjike.goddess.foreigntax.dto.IncomeCollectDTO;
import com.bjike.goddess.foreigntax.dto.IncomeInvoiceDTO;
import com.bjike.goddess.foreigntax.entity.IncomeInvoice;
import com.bjike.goddess.foreigntax.enums.GuideAddrStatus;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.IncomeInvoiceTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 进项发票业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:45 ]
 * @Description: [ 进项发票业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "foreigntaxSerCache")
@Service
public class IncomeInvoiceSerImpl extends ServiceImpl<IncomeInvoice, IncomeInvoiceDTO> implements IncomeInvoiceSer {

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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long count(IncomeInvoiceDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public IncomeInvoiceBO getOne(String id) throws SerException {
        IncomeInvoice incomeInvoice = super.findById(id);
        IncomeInvoiceBO bo = BeanTransform.copyProperties(incomeInvoice, IncomeInvoiceBO.class);
        return bo;
    }

    @Override
    public List<IncomeInvoiceBO> list(IncomeInvoiceDTO dto) throws SerException {
        checkSeeIdentity();
        List<IncomeInvoice> incomeInvoices = super.findByCis(dto);
        List<IncomeInvoiceBO> incomeInvoiceBOS = BeanTransform.copyProperties(incomeInvoices, IncomeInvoiceBO.class);
        return incomeInvoiceBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IncomeInvoiceBO insert(IncomeInvoiceTO to) throws SerException {
        checkAddIdentity();
        IncomeInvoice incomeInvoice = BeanTransform.copyProperties(to, IncomeInvoice.class, true);
        incomeInvoice.setCreateTime(LocalDateTime.now());
        super.save(incomeInvoice);
        IncomeInvoiceBO bo = BeanTransform.copyProperties(incomeInvoice, IncomeInvoiceBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IncomeInvoiceBO edit(IncomeInvoiceTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            IncomeInvoice incomeInvoice = BeanTransform.copyProperties(to, IncomeInvoice.class, true);
            incomeInvoice.setCreateTime(LocalDateTime.now());
            super.save(incomeInvoice);
            IncomeInvoiceBO bo = BeanTransform.copyProperties(incomeInvoice, IncomeInvoiceBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public List<IncomeInvoiceBO> collect(IncomeCollectDTO dto) throws SerException {
        List<IncomeInvoiceBO> boList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT company AS company,taxCode AS taxCode,mainName AS mainName,sum(rate) AS rate, ");
        sb.append(" sum(notTax) AS notTax,sum(tax) AS tax,signer AS signer ");
        sb.append(" FROM foreigntax_incomeinvoice ");
        sb.append(" WHERE invoicingTime BETWEEN '" + dto.getStartTime() + "' AND '" + dto.getEndTime() + "' ");
        sb.append(" GROUP BY company,taxCode,mainName,signer ");
        String[] fields = new String[]{"company", "taxCode", "mainName", "rate", "notTax", "tax", "signer"};
        boList = super.findBySql(sb.toString(), IncomeInvoiceBO.class, fields);
        //不含税金额
        Double notTax = boList.stream().filter(p -> p.getNotTax() != null).mapToDouble(p -> p.getNotTax()).sum();
        //税金
        Double tax = boList.stream().filter(p -> p.getTax() != null).mapToDouble(p -> p.getTax()).sum();
        IncomeInvoiceBO bo = new IncomeInvoiceBO();
        bo.setCompany("合计");
        bo.setNotTax(notTax);
        bo.setTax(tax);
        boList.add(bo);
        return boList;
    }

    @Override
    public List<IncomeInvoiceBO> checkCollect(IncomeInvoiceDTO dto) throws SerException {
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        String[] cond = new String[]{startTime,endTime};
        dto.getConditions().add(Restrict.between("invoicingTime",cond));
        dto.getConditions().add(Restrict.in("id", dto.getId()));
        List<IncomeInvoice> incomeInvoices = super.findByCis(dto);
        List<IncomeInvoiceBO> incomeInvoiceBOS = BeanTransform.copyProperties(incomeInvoices, IncomeInvoiceBO.class);
        //不含税金额
        Double notTax = incomeInvoiceBOS.stream().filter(p -> p.getNotTax() != null).mapToDouble(p -> p.getNotTax()).sum();
        //税金
        Double tax = incomeInvoiceBOS.stream().filter(p -> p.getTax() != null).mapToDouble(p -> p.getTax()).sum();
        IncomeInvoiceBO bo = new IncomeInvoiceBO();
        bo.setCompany("合计");
        bo.setNotTax(notTax);
        bo.setTax(tax);
        incomeInvoiceBOS.add(bo);
        return incomeInvoiceBOS;
    }


    @Override
    public List<CompareCollectBO> compareCollect(IncomeCollectDTO dto) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT a.invoicingTime as invoicingTime, sum(a.notTax) AS outputNotTax,sum(a.tax) AS outputTax, ");
        sb.append(" sum(b.notTax) AS incomeNotTax,sum(b.tax) AS incomeTax ");
        sb.append(" FROM foreigntax_outputinvoice a,foreigntax_incomeinvoice b ");
        sb.append(" WHERE a.invoicingTime = b.invoicingTime AND a.invoicingTime BETWEEN '" + dto.getStartTime() + "' AND '" + dto.getEndTime() + "' ");
        sb.append(" GROUP BY a.invoicingTime ");
        String[] fields = new String[]{"invoicingTime", "outputNotTax", "outputTax", "incomeNotTax", "incomeTax"};
        List<CompareCollectBO> compareCollectBOS = super.findBySql(sb.toString(), CompareCollectBO.class, fields);
        //销项发票不含税金额
        Double outputNotTax = compareCollectBOS.stream().filter(p -> p.getOutputNotTax() != null).mapToDouble(p -> p.getOutputNotTax()).sum();
        //销项发票税金
        Double outputTax = compareCollectBOS.stream().filter(p -> p.getOutputTax() != null).mapToDouble(p -> p.getOutputTax()).sum();
        //进项发票不含税金额
        Double incomeNotTax = compareCollectBOS.stream().filter(p -> p.getIncomeNotTax() != null).mapToDouble(p -> p.getIncomeNotTax()).sum();
        //进项发票税金
        Double incomeTax = compareCollectBOS.stream().filter(p -> p.getIncomeTax() != null).mapToDouble(p -> p.getIncomeTax()).sum();
        CompareCollectBO bo = new CompareCollectBO();
        bo.setInvoicingTime("合计");
        bo.setOutputNotTax(outputNotTax);
        bo.setOutputTax(outputTax);
        bo.setIncomeNotTax(incomeNotTax);
        bo.setIncomeTax(incomeTax);
        compareCollectBOS.add(bo);
        CompareCollectBO collectBO = new CompareCollectBO();
        collectBO.setInvoicingTime("差异");
        collectBO.setOutputNotTax(outputNotTax-incomeNotTax);
        collectBO.setOutputTax(outputTax-incomeTax);
        compareCollectBOS.add(collectBO);
        return compareCollectBOS;
    }
}