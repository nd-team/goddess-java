package com.bjike.goddess.costdetail.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.costdetail.bo.DetailTypeBO;
import com.bjike.goddess.costdetail.dto.*;
import com.bjike.goddess.costdetail.entity.*;
import com.bjike.goddess.costdetail.excel.SonPermissionObject;
import com.bjike.goddess.costdetail.to.DetailTypeTO;
import com.bjike.goddess.costdetail.to.GuidePermissionTO;
import com.bjike.goddess.costdetail.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 明细分类业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-07 09:43 ]
 * @Description: [ 明细分类业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "costdetailSerCache")
@Service
public class DetailTypeSerImpl extends ServiceImpl<DetailType, DetailTypeDTO> implements DetailTypeSer {
    @Autowired
    private LaborCostDetailSer laborCostDetailSer;
    @Autowired
    private CompanyBorrowedDetailSer companyBorrowedDetailSer;
    @Autowired
    private PaidCapitalDetailSer paidCapitalDetailSer;
    @Autowired
    private CompanyLendDetailSer companyLendDetailSer;
    @Autowired
    private BusinessIncomeDetailSer businessIncomeDetailSer;
    @Autowired
    private CostDetailsSer costDetailsSer;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是财务部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagType = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("detailtype");
        obj.setDescribesion("明细分类");
        if (flagType) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagCostDetail = costDetailsSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("costdetails");
        obj.setDescribesion("成本明细");
        if (flagCostDetail) {
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
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case SEE:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countDetailType(DetailTypeDTO detailTypeDTO) throws SerException {
        Long count = super.count(detailTypeDTO);
        return count;
    }

    @Override
    public DetailTypeBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        DetailType detailType = super.findById(id);
        return BeanTransform.copyProperties(detailType, DetailTypeBO.class);
    }

    @Override
    public List<String> findTypeName(String parNode) throws SerException {
        DetailTypeDTO detailTypeDTO = new DetailTypeDTO();
        detailTypeDTO.getConditions().add(Restrict.eq("parNode", parNode));
        List<DetailType> list = super.findByCis(detailTypeDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (DetailType model : list) {
            String typeName = model.getTypeName();
            if (StringUtils.isNotBlank(model.getTypeName())) {
                set.add(typeName);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public DetailTypeBO add(DetailTypeTO detailTypeTO) throws SerException {
        DetailType detailType = BeanTransform.copyProperties(detailTypeTO, DetailType.class, "true");
        detailType.setCreateTime(LocalDateTime.now());
        super.save(detailType);
        return BeanTransform.copyProperties(detailType, DetailTypeBO.class);
    }

    @Override
    public DetailTypeBO edit(DetailTypeTO detailTypeTO) throws SerException {
        DetailType type = super.findById(detailTypeTO.getId());
        String typeName = type.getTypeName();
        DetailType detailType = super.findById(detailTypeTO.getId());
        BeanTransform.copyProperties(detailTypeTO, detailType, "true");
        detailType.setModifyTime(LocalDateTime.now());
        if (detailType.getParNode().equals("劳务成本")) {
            LaborCostDetailDTO laborCostDetailDTO = new LaborCostDetailDTO();
            laborCostDetailDTO.getConditions().add(Restrict.eq("typeName", typeName));
            List<LaborCostDetail> laborCostDetails = laborCostDetailSer.findByCis(laborCostDetailDTO);
            laborCostDetails.forEach(laborCostDetail -> {
                laborCostDetail.setTypeName(detailTypeTO.getTypeName());
                laborCostDetail.setModifyTime(LocalDateTime.now());
            });
            laborCostDetailSer.update(laborCostDetails);
        } else if (detailType.getParNode().equals("公司借入")) {
            CompanyBorrowedDetailDTO companyBorrowedDetailDTO = new CompanyBorrowedDetailDTO();
            companyBorrowedDetailDTO.getConditions().add(Restrict.eq("typeName", typeName));
            List<CompanyBorrowedDetail> companyBorrowedDetails = companyBorrowedDetailSer.findByCis(companyBorrowedDetailDTO);
            companyBorrowedDetails.forEach(companyBorrowedDetail -> {
                companyBorrowedDetail.setTypeName(detailTypeTO.getTypeName());
                companyBorrowedDetail.setModifyTime(LocalDateTime.now());
            });
            companyBorrowedDetailSer.update(companyBorrowedDetails);
        } else if (detailType.getParNode().equals("实收资本")) {
            PaidCapitalDetailDTO paidCapitalDetailDTO = new PaidCapitalDetailDTO();
            paidCapitalDetailDTO.getConditions().add(Restrict.eq("typeName", typeName));
            List<PaidCapitalDetail> paidCapitalDetails = paidCapitalDetailSer.findByCis(paidCapitalDetailDTO);
            paidCapitalDetails.forEach(paidCapitalDetail -> {
                paidCapitalDetail.setTypeName(detailTypeTO.getTypeName());
                paidCapitalDetail.setModifyTime(LocalDateTime.now());
            });
            paidCapitalDetailSer.update(paidCapitalDetails);
        } else if (detailType.getParNode().equals("公司借出")) {
            CompanyLendDetailDTO companyLendDetailDTO = new CompanyLendDetailDTO();
            companyLendDetailDTO.getConditions().add(Restrict.eq("typeName", typeName));
            List<CompanyLendDetail> companyLendDetails = companyLendDetailSer.findByCis(companyLendDetailDTO);
            companyLendDetails.forEach(companyLendDetail -> {
                companyLendDetail.setTypeName(detailTypeTO.getTypeName());
                companyLendDetail.setModifyTime(LocalDateTime.now());
            });
            companyLendDetailSer.update(companyLendDetails);
        } else if (detailType.getParNode().equals("主营业务收入")) {
            BusinessIncomeDetailDTO businessIncomeDetailDTO = new BusinessIncomeDetailDTO();
            businessIncomeDetailDTO.getConditions().add(Restrict.eq("typeName", typeName));
            List<BusinessIncomeDetail> businessIncomeDetails = businessIncomeDetailSer.findByCis(businessIncomeDetailDTO);
            businessIncomeDetails.forEach(businessIncomeDetail -> {
                businessIncomeDetail.setTypeName(detailTypeTO.getTypeName());
                businessIncomeDetail.setModifyTime(LocalDateTime.now());
            });
            businessIncomeDetailSer.update(businessIncomeDetails);
        }
        super.update(detailType);
        return BeanTransform.copyProperties(detailType, DetailTypeBO.class);
    }

    @Override
    public List<DetailTypeBO> findByNode(String parNode) throws SerException {
        DetailTypeDTO dto = new DetailTypeDTO();
        dto.getConditions().add(Restrict.eq("parNode", parNode));
        List<DetailType> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DetailTypeBO.class);
    }
}