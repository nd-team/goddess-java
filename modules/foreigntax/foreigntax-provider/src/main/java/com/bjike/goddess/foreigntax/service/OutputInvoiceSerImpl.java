package com.bjike.goddess.foreigntax.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.bo.OutputInvoiceBO;
import com.bjike.goddess.foreigntax.dto.OutputInvoiceDTO;
import com.bjike.goddess.foreigntax.entity.OutputInvoice;
import com.bjike.goddess.foreigntax.enums.GuideAddrStatus;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.OutputInvoiceTO;
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
 * 销项发票业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:43 ]
 * @Description: [ 销项发票业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "foreigntaxSerCache")
@Service
public class OutputInvoiceSerImpl extends ServiceImpl<OutputInvoice, OutputInvoiceDTO> implements OutputInvoiceSer {
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
    public Long count(OutputInvoiceDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public OutputInvoiceBO getOne(String id) throws SerException {
        OutputInvoice outputInvoice = super.findById(id);
        OutputInvoiceBO bo = BeanTransform.copyProperties(outputInvoice, OutputInvoiceBO.class);
        return bo;
    }

    @Override
    public List<OutputInvoiceBO> list(OutputInvoiceDTO dto) throws SerException {
        checkSeeIdentity();
        List<OutputInvoice> outputInvoices = super.findByCis(dto);
        List<OutputInvoiceBO> outputInvoiceBOS = BeanTransform.copyProperties(outputInvoices, OutputInvoiceBO.class);
        return outputInvoiceBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OutputInvoiceBO insert(OutputInvoiceTO to) throws SerException {
        checkAddIdentity();
        OutputInvoice outputInvoice = BeanTransform.copyProperties(to, OutputInvoice.class, true);
        outputInvoice.setCreateTime(LocalDateTime.now());
        super.save(outputInvoice);
        OutputInvoiceBO bo = BeanTransform.copyProperties(outputInvoice, OutputInvoiceBO.class);
        return bo;

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OutputInvoiceBO edit(OutputInvoiceTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            OutputInvoice outputInvoice = super.findById(to.getId());
            LocalDateTime createTime = outputInvoice.getCreateTime();
            outputInvoice = BeanTransform.copyProperties(to, OutputInvoice.class, true);
            outputInvoice.setCreateTime(createTime);
            outputInvoice.setModifyTime(LocalDateTime.now());
            super.update(outputInvoice);
            OutputInvoiceBO bo = BeanTransform.copyProperties(outputInvoice, OutputInvoiceBO.class);
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
    public List<OutputInvoiceBO> collect(Integer year, Integer month) throws SerException {
        List<OutputInvoiceBO> boList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT company AS company,taxCode AS taxCode,mainName AS mainName, sum(rate) AS rate, ");
        sb.append(" sum(notTax) AS notTax,sum(tax) AS tax,signer AS signer,drawer AS drawer, ");
        sb.append(" invalidSign AS invalidSign,invalidDate AS invalidDate ");
        sb.append(" FROM foreigntax_outputinvoice ");
        sb.append(" WHERE year(invoicingTime) = '" + year + "' AND month(invoicingTime) = '" + month + "' ");
        sb.append(" GROUP BY company,taxCode,mainName,signer,drawer,invalidSign,invalidDate ");
        String[] fields = new String[]{"company", "taxCode", "mainName", "rate", "notTax", "tax",
                "signer", "drawer", "invalidSign", "invalidDate"};
        boList = super.findBySql(sb.toString(), OutputInvoiceBO.class, fields);
        //不含税金额
        Double notTax = boList.stream().filter(p -> p.getNotTax() != null).mapToDouble(p -> p.getNotTax()).sum();
        //税金
        Double tax = boList.stream().filter(p -> p.getTax() != null).mapToDouble(p -> p.getTax()).sum();
        OutputInvoiceBO bo = new OutputInvoiceBO();
        bo.setCompany("合计");
        bo.setNotTax(notTax);
        bo.setTax(tax);
        boList.add(bo);
        return boList;
    }
}