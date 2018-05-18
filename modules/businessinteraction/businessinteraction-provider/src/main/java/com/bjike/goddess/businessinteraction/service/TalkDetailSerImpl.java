package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.TalkDetailBO;
import com.bjike.goddess.businessinteraction.dto.TalkDetailDTO;
import com.bjike.goddess.businessinteraction.entity.InteractionRelation;
import com.bjike.goddess.businessinteraction.entity.TalkDetail;
import com.bjike.goddess.businessinteraction.enums.GuideAddrStatus;
import com.bjike.goddess.businessinteraction.excel.TalkDetailImport;
import com.bjike.goddess.businessinteraction.excel.TalkDetailImportTemple;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.businessinteraction.to.SonPermissionObject;
import com.bjike.goddess.businessinteraction.to.TalkDetailTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 资料信息业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:48 ]
 * @Description: [ 资料信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessinteractionSerCache")
@Service
public class TalkDetailSerImpl extends ServiceImpl<TalkDetail, TalkDetailDTO> implements TalkDetailSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private DemandSer demandSer;
    @Autowired
    private InteractionRelationSer interactionRelationSer;

    /**
     * 核对添加删除修改查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加删除修改查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
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
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("talkdetail");
        obj.setDescribesion("资料信息");
        if (flagSeeSign ) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeComputer = demandSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("demand");
        obj.setDescribesion("需求信息");
        if (flagSeeComputer) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeSeniorit = interactionRelationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("interactionrelation");
        obj.setDescribesion("公司信息");
        if (flagSeeSeniorit) {
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
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case IMPORT:
                flag = guideSeeIdentity();
                break;
            case EXPORT:
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
    public Long countInter(TalkDetailDTO talkDetailDTO) throws SerException {
        if (StringUtils.isNotBlank(talkDetailDTO.getBusinessTarget())) {
            talkDetailDTO.getConditions().add(Restrict.eq("businessTarget", talkDetailDTO.getBusinessTarget()));
        }
        Long count = super.count(talkDetailDTO);
        return count;
    }

    @Override
    public TalkDetailBO getOneById(String id) throws SerException {
        TalkDetail talkDetail = super.findById(id);
        return BeanTransform.copyProperties(talkDetail, TalkDetailBO.class);
    }

    @Override
    public List<TalkDetailBO> listIntera(TalkDetailDTO talkDetailDTO) throws SerException {
        checkSeeIdentity();
        if (StringUtils.isNotBlank(talkDetailDTO.getBusinessTarget())) {
            talkDetailDTO.getConditions().add(Restrict.eq("businessTarget", talkDetailDTO.getBusinessTarget()));
        }
        List<TalkDetail> talkDetails = super.findByCis(talkDetailDTO, true);
        return BeanTransform.copyProperties(talkDetails, TalkDetailBO.class);
    }

    @Override
    public TalkDetailBO addIntera(TalkDetailTO talkDetailTO) throws SerException {
       checkSeeIdentity();
        TalkDetail talkDetail = BeanTransform.copyProperties(talkDetailTO, TalkDetail.class, true);
        talkDetail.setCreateTime(LocalDateTime.now());
        super.save(talkDetail);
        return BeanTransform.copyProperties(talkDetail, TalkDetailBO.class);
    }

    @Override
    public TalkDetailBO editIntera(TalkDetailTO talkDetailTO) throws SerException {
        checkSeeIdentity();
        TalkDetail talkDetail = super.findById(talkDetailTO.getId());
        LocalDateTime dateTime = talkDetail.getCreateTime();
        talkDetail = BeanTransform.copyProperties(talkDetailTO, TalkDetail.class, true);
        talkDetail.setCreateTime(dateTime);
        talkDetail.setModifyTime(LocalDateTime.now());
        super.update(talkDetail);
        return BeanTransform.copyProperties(talkDetail, TalkDetailBO.class);
    }

    @Override
    public void deleteIntera(String id) throws SerException {
        checkSeeIdentity();
        super.remove(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        List<TalkDetail> list = super.findAll();
        List<TalkDetailImport> talkDetailImports = new ArrayList<>();
        for (TalkDetail talkDetail : list) {
            TalkDetailImport excel = BeanTransform.copyProperties(talkDetail, TalkDetailImport.class);
            talkDetailImports.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(talkDetailImports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<TalkDetailImportTemple> talkDetailImportTemples = new ArrayList<>();
        TalkDetailImportTemple excel = new TalkDetailImportTemple();
        excel.setCooperCompany("test");
        excel.setArea("test");
        excel.setBusinessTarget("通信");
        excel.setProfessional("创新");
        excel.setObjectSize("1220m");
        excel.setCooperWay("test");
        excel.setCooperDate("2017-12-12");
        excel.setServiceFee(20d);
        excel.setIntermediaryFee(20d);
        excel.setReachedCooper("是");
        excel.setRemark("lalal");
        talkDetailImportTemples.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(talkDetailImportTemples, exce);
        return bytes;
    }

    @Override
    public void importExcel(List<TalkDetailTO> talkDetailTOS) throws SerException {
        List<TalkDetail> talkDetails = BeanTransform.copyProperties(talkDetailTOS, TalkDetail.class, true);
        talkDetails.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(talkDetails);
    }

    @Override
    public List<String> findBussType() throws SerException {
        List<TalkDetail> talkDetailList = super.findAll();
        if(CollectionUtils.isEmpty(talkDetailList)){
            return Collections.emptyList();
        }
        return talkDetailList.stream().map(TalkDetail::getBusinessTarget).distinct().collect(Collectors.toList());
    }
}