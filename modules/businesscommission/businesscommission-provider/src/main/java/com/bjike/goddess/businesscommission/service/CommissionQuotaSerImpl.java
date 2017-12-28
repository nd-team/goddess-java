//package com.bjike.goddess.businesscommission.service;
//
//import com.bjike.goddess.businesscommission.api.WeightAllotAPI;
//import com.bjike.goddess.businesscommission.bo.CommissionQuotaBO;
//import com.bjike.goddess.businesscommission.bo.WeightAllotBO;
//import com.bjike.goddess.businesscommission.dto.CommissionQuotaDTO;
//import com.bjike.goddess.businesscommission.dto.WeightAllotDTO;
//import com.bjike.goddess.businesscommission.entity.CommissionQuota;
//import com.bjike.goddess.businesscommission.enums.GuideAddrStatus;
//import com.bjike.goddess.businesscommission.excel.CommissionQuotaExcele;
//import com.bjike.goddess.businesscommission.excel.CommissionQuotaTemplateExcele;
//import com.bjike.goddess.businesscommission.to.CommissionQuotaTO;
//import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
//import com.bjike.goddess.common.api.dto.Restrict;
//import com.bjike.goddess.common.api.exception.SerException;
//import com.bjike.goddess.common.jpa.service.ServiceImpl;
//import com.bjike.goddess.common.provider.utils.RpcTransmit;
//import com.bjike.goddess.common.utils.bean.BeanTransform;
//import com.bjike.goddess.common.utils.excel.Excel;
//import com.bjike.goddess.common.utils.excel.ExcelUtil;
////import com.bjike.goddess.contractcommunicat.bo.ProjectContractBO;
////import com.bjike.goddess.market.bo.MarketInfoBO;
////import com.bjike.goddess.marketactivitymanage.bo.MarketServeRecordBO;
//import com.bjike.goddess.organize.api.PositionDetailUserAPI;
//import com.bjike.goddess.projectroyalty.api.TargetAuotaAPI;
//import com.bjike.goddess.projectroyalty.bo.TargetAuotaBO;
//import com.bjike.goddess.projectroyalty.dto.TargetAuotaDTO;
//import com.bjike.goddess.user.api.UserAPI;
//import com.bjike.goddess.user.bo.UserBO;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * 业务提成定额表业务实现
// *
// * @Author: [ zhuangkaiqin ]
// * @Date: [ 2017-06-29 04:49 ]
// * @Description: [ 业务提成定额表业务实现 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//@CacheConfig(cacheNames = "businesscommissionSerCache")
//@Service
//public class CommissionQuotaSerImpl extends ServiceImpl<CommissionQuota, CommissionQuotaDTO>  {
//
////    @Autowired
////    private UserAPI userAPI;
////    @Autowired
////    private CusPermissionSer cusPermissionSer;
////    @Autowired
////    private TargetAuotaAPI targetAuotaAPI;
////    @Autowired
////    private WeightAllotAPI weightAllotAPI;
////    @Autowired
////    private PositionDetailUserAPI positionDetailUserAPI;
////
////    @Override
////    public Long countCommissionQuota(CommissionQuotaDTO commissionQuotaDTO) throws SerException {
////        searchCondition(commissionQuotaDTO);
////        Long count = super.count(commissionQuotaDTO);
////        return count;
////    }
////
////    @Override
////    public CommissionQuotaBO getOneById(String id) throws SerException {
////        if (StringUtils.isBlank(id)) {
////            throw new SerException("id不能为空");
////        }
////
////        CommissionQuota commissionQuota = super.findById(id);
////        return BeanTransform.copyProperties(commissionQuota, CommissionQuotaBO.class);
////    }
////
////    @Override
////    public List<CommissionQuotaBO> listCommissionQuota(CommissionQuotaDTO commissionQuotaDTO) throws SerException {
////        //checkSeeIdentity();
////
////        searchCondition(commissionQuotaDTO);
////        List<CommissionQuota> list = super.findByPage(commissionQuotaDTO);
////        List<CommissionQuotaBO> commissionQuotaBOS = new ArrayList<>();
////        list.stream().forEach(str -> {
////            CommissionQuotaBO bo = BeanTransform.copyProperties(str, CommissionQuotaBO.class);
////            commissionQuotaBOS.add(bo);
////        });
////        return commissionQuotaBOS;
////    }
////
////    @Transactional(rollbackFor = SerException.class)
////    @Override
////    public CommissionQuotaBO addCommissionQuota(CommissionQuotaTO commissionQuotaTO) throws SerException {
//////        checkAddIdentity();
////
////        //根据地区和项目名称获得信息提供占比
////        WeightAllotDTO weightAllotDTO = new WeightAllotDTO();
////        if (StringUtils.isNotBlank(commissionQuotaTO.getArea()) && StringUtils.isNotBlank(commissionQuotaTO.getProjectName())) {
//////            weightAllotDTO.getConditions().add(Restrict.eq("area", commissionQuotaTO.getArea()));
//////            weightAllotDTO.getConditions().add(Restrict.eq("projectName", commissionQuotaTO.getProjectName()));
////            weightAllotDTO.setArea(commissionQuotaTO.getArea());
////            weightAllotDTO.setProjectName(commissionQuotaTO.getProjectName());
////        } else {
////            throw new SerException("地区和项目名称不能为空");
////
////        }
////        if (null != weightAllotAPI.listWeightAllot(weightAllotDTO) && weightAllotAPI.listWeightAllot(weightAllotDTO).size() > 0) {
////            WeightAllotBO bo = weightAllotAPI.listWeightAllot(weightAllotDTO).get(0);
////
////            //判断 如果信息提供人与业务揽接人与业务洽谈人与维护人是同一个人且是商务部的人就要*80%,否则不用
////            if (commissionQuotaTO.getInformationProvide().equals(commissionQuotaTO.getBusinessContracting()) && commissionQuotaTO.getInformationProvide().equals(commissionQuotaTO.getBusinessNegotiation()) && commissionQuotaTO.getInformationProvide().equals(commissionQuotaTO.getMaintenance())
////                    && isBusiness(commissionQuotaTO)) {
////                commissionQuotaTO.setActualAmount(commissionQuotaTO.getActualAmount() * 0.8);
////            }
////
////            //信息提供占额  实际业务提成总额*信息提供占比
////            commissionQuotaTO.setProvideAccount(bo.getMessageProportion() * commissionQuotaTO.getActualAmount());
////
////            //业务揽接占额  实际业务提成总额*业务揽接占比
////            commissionQuotaTO.setContractAccount(bo.getBusinessProportion() * commissionQuotaTO.getActualAmount());
////
////            //业务洽谈占额  实际业务提成总额*业务洽谈占比
////            commissionQuotaTO.setNegotiationAccount(bo.getTalkProportion() * commissionQuotaTO.getActualAmount());
////
////            //维护占额  实际业务提成总额*维护占比
////            commissionQuotaTO.setMaintenanceAccount(bo.getMaintainProportion() * commissionQuotaTO.getActualAmount());
////
////            //剩余占额  实际业务提成总额*剩余占比
////            commissionQuotaTO.setRemainingAccount(bo.getSurplusProportion() * commissionQuotaTO.getActualAmount());
////
////            CommissionQuota commissionQuota = BeanTransform.copyProperties(commissionQuotaTO, CommissionQuota.class, true);
////
////            commissionQuota.setCreateTime(LocalDateTime.now());
////            super.save(commissionQuota);
////
////            CommissionQuotaBO commissionQuotaBO = BeanTransform.copyProperties(commissionQuota, CommissionQuotaBO.class);
////            return commissionQuotaBO;
////        } else {
////            throw new SerException("业务提成权重分配表中内没有相对应的地区与项目名称");
////        }
////    }
////
////    @Transactional(rollbackFor = SerException.class)
////    @Override
////    public CommissionQuotaBO editCommissionQuota(CommissionQuotaTO commissionQuotaTO) throws SerException {
////        //checkAddIdentity();
////
////        //根据地区和项目名称获得信息提供占比
////        WeightAllotDTO weightAllotDTO = new WeightAllotDTO();
////        if (StringUtils.isNotBlank(commissionQuotaTO.getArea()) && StringUtils.isNotBlank(commissionQuotaTO.getProjectName())) {
////            weightAllotDTO.getConditions().add(Restrict.eq("area", commissionQuotaTO.getArea()));
////            weightAllotDTO.getConditions().add(Restrict.eq("projectName", commissionQuotaTO.getProjectName()));
////        } else {
////            throw new SerException("地区和项目名称不能为空");
////        }
////        if (null != weightAllotAPI.listWeightAllot(weightAllotDTO) && weightAllotAPI.listWeightAllot(weightAllotDTO).size() > 0) {
////            WeightAllotBO bo = weightAllotAPI.listWeightAllot(weightAllotDTO).get(0);
////
////            //判断 如果信息提供人与业务揽接人与业务洽谈人与维护人是同一个人且是商务部的人就要*80%,否则不用
////            if (commissionQuotaTO.getInformationProvide().equals(commissionQuotaTO.getBusinessContracting()) && commissionQuotaTO.getInformationProvide().equals(commissionQuotaTO.getBusinessNegotiation()) && commissionQuotaTO.getInformationProvide().equals(commissionQuotaTO.getMaintenance())
////                    && isBusiness(commissionQuotaTO)) {
////                commissionQuotaTO.setActualAmount(commissionQuotaTO.getActualAmount() * 0.8);
////            }
////
////            //信息提供占额  实际业务提成总额*信息提供占比
////            commissionQuotaTO.setProvideAccount(bo.getMessageProportion() * commissionQuotaTO.getActualAmount());
////
////            //业务揽接占额  实际业务提成总额*业务揽接占比
////            commissionQuotaTO.setContractAccount(bo.getBusinessProportion() * commissionQuotaTO.getActualAmount());
////
////            //业务洽谈占额  实际业务提成总额*业务洽谈占比
////            commissionQuotaTO.setNegotiationAccount(bo.getTalkProportion() * commissionQuotaTO.getActualAmount());
////
////            //维护占额  实际业务提成总额*维护占比
////            commissionQuotaTO.setMaintenanceAccount(bo.getMaintainProportion() * commissionQuotaTO.getActualAmount());
////
////            //剩余占额  实际业务提成总额*剩余占比
////            commissionQuotaTO.setRemainingAccount(bo.getSurplusProportion() * commissionQuotaTO.getActualAmount());
////
////            CommissionQuota temp = super.findById(commissionQuotaTO.getId());
////            CommissionQuota commissionQuota = BeanTransform.copyProperties(commissionQuotaTO, CommissionQuota.class, true);
////            BeanUtils.copyProperties(commissionQuota, temp, "id", "createTime");
////            temp.setModifyTime(LocalDateTime.now());
////            super.update(temp);
////
////            CommissionQuotaBO commissionQuotaBO = BeanTransform.copyProperties(temp, CommissionQuotaBO.class);
////            return commissionQuotaBO;
////        } else {
////            throw new SerException("业务提成权重分配表中内没有相对应的地区与项目名称");
////        }
////    }
////
////    @Transactional(rollbackFor = SerException.class)
////    @Override
////    public void deleteCommissionQuota(String id) throws SerException {
////        checkAddIdentity();
////        super.remove(id);
////    }
////
////    public void searchCondition(CommissionQuotaDTO commissionQuotaDTO) throws SerException {
////        /**
////         * 地区
////         */
////        if (commissionQuotaDTO.getArea() != null) {
////            commissionQuotaDTO.getConditions().add(Restrict.eq("area", commissionQuotaDTO.getArea()));
////        }
////        /**
////         * 项目名称
////         */
////        if (StringUtils.isNotBlank(commissionQuotaDTO.getProjectName())) {
////            commissionQuotaDTO.getConditions().add(Restrict.like("projectName", commissionQuotaDTO.getProjectName()));
////        }
////        /**
////         * 实际业务提成总额
////         */
////        if (commissionQuotaDTO.getActualAmount() != null) {
////            commissionQuotaDTO.getConditions().add(Restrict.eq("actualAmount", commissionQuotaDTO.getActualAmount()));
////        }
////        /**
////         * 信息提供人
////         */
////        if (StringUtils.isNotBlank(commissionQuotaDTO.getInformationProvide())) {
////            commissionQuotaDTO.getConditions().add(Restrict.like("informationProvide", commissionQuotaDTO.getInformationProvide()));
////        }
////        /**
////         * 信息提供占额
////         */
////        if (null != commissionQuotaDTO.getProvideAccount()) {
////            commissionQuotaDTO.getConditions().add(Restrict.like("provideAccount", commissionQuotaDTO.getProvideAccount()));
////        }
////        /**
////         * 业务揽接人
////         */
////        if (StringUtils.isNotBlank(commissionQuotaDTO.getBusinessContracting())) {
////            commissionQuotaDTO.getConditions().add(Restrict.like("businessContracting", commissionQuotaDTO.getBusinessContracting()));
////        }
////        /**
////         * 业务揽接占额
////         */
////        if (null != commissionQuotaDTO.getContractAccount()) {
////            commissionQuotaDTO.getConditions().add(Restrict.eq("contractAccount", commissionQuotaDTO.getContractAccount()));
////        }
////        /**
////         * 业务洽谈人
////         */
////        if (StringUtils.isNotBlank(commissionQuotaDTO.getBusinessNegotiation())) {
////            commissionQuotaDTO.getConditions().add(Restrict.eq("businessNegotiation", commissionQuotaDTO.getBusinessNegotiation()));
////        }
////        /**
////         * 业务洽谈占额
////         */
////        if (null != commissionQuotaDTO.getNegotiationAccount()) {
////            commissionQuotaDTO.getConditions().add(Restrict.eq("negotiationAccount", commissionQuotaDTO.getNegotiationAccount()));
////        }
////        /**
////         * 维护人
////         */
////        if (StringUtils.isNotBlank(commissionQuotaDTO.getMaintenance())) {
////            commissionQuotaDTO.getConditions().add(Restrict.eq("maintenance", commissionQuotaDTO.getMaintenance()));
////        }
////        /**
////         * 维护占额
////         */
////        if (null != commissionQuotaDTO.getMaintenanceAccount()) {
////            commissionQuotaDTO.getConditions().add(Restrict.eq("maintenanceAccount", commissionQuotaDTO.getMaintenanceAccount()));
////        }
////        /**
////         * 剩余占额
////         */
////        if (null != commissionQuotaDTO.getRemainingAccount()) {
////            commissionQuotaDTO.getConditions().add(Restrict.eq("remainingAccount", commissionQuotaDTO.getRemainingAccount()));
////        }
////    }
////
////    @Override
////    public CommissionQuotaBO importExcel(List<CommissionQuotaTO> commissionQuotaTO) throws SerException {
////
////        List<CommissionQuota> commissionQuota = BeanTransform.copyProperties(commissionQuotaTO, CommissionQuota.class, true);
////        commissionQuota.stream().forEach(str -> {
////            str.setCreateTime(LocalDateTime.now());
////            str.setModifyTime(LocalDateTime.now());
////        });
////        super.save(commissionQuota);
////
////        CommissionQuotaBO commissionQuotaBO = BeanTransform.copyProperties(new CommissionQuota(), CommissionQuotaBO.class);
////        return commissionQuotaBO;
////    }
////
////    @Override
////    public byte[] exportExcel(CommissionQuotaDTO dto) throws SerException {
////        if (StringUtils.isNotBlank(dto.getProjectName())) {
////            dto.getConditions().add(Restrict.eq("projectName", dto.getProjectName()));
////        }
////
////        List<CommissionQuota> list = super.findByCis(dto);
////
////        List<CommissionQuotaExcele> commissionQuotaExports = new ArrayList<>();
////        list.stream().forEach(str -> {
////            CommissionQuotaExcele excel = BeanTransform.copyProperties(str, CommissionQuotaExcele.class);
////            commissionQuotaExports.add(excel);
////        });
////        Excel excel = new Excel(0, 2);
////        byte[] bytes = ExcelUtil.clazzToExcel(commissionQuotaExports, excel);
////        return bytes;
////    }
////
////
////    @Override
////    public byte[] templateExport() throws SerException {
////        List<CommissionQuotaTemplateExcele> commissionQuotaTemplateExcele = new ArrayList<>();
////
////        CommissionQuotaTemplateExcele excel = new CommissionQuotaTemplateExcele();
////        excel.setArea("dad");
////        excel.setProjectName("fhdj");
////        excel.setActualAmount(12.0);
////        excel.setInformationProvide("fds");
////        excel.setProvideAccount(123.1);
////        excel.setBusinessContracting("fds");
////        excel.setContractAccount(123.01);
////        excel.setBusinessNegotiation("fds");
////        excel.setNegotiationAccount(54.1);
////        excel.setMaintenance("fds");
////        excel.setMaintenanceAccount(78.0);
////        excel.setRemainingAccount(23.1);
////        excel.setRemark("fds");
////        commissionQuotaTemplateExcele.add(excel);
////        Excel exce = new Excel(0, 2);
////        byte[] bytes = ExcelUtil.clazzToExcel(commissionQuotaTemplateExcele, exce);
////        return bytes;
////    }
////
////    /**
////     * 核对查看权限（部门级别）
////     */
////    private void checkSeeIdentity() throws SerException {
////        Boolean flag = false;
////        String userToken = RpcTransmit.getUserToken();
////        UserBO userBO = userAPI.currentUser();
////        RpcTransmit.transmitUserToken(userToken);
////        String userName = userBO.getUsername();
////        if (!"admin".equals(userName.toLowerCase())) {
////            flag = cusPermissionSer.getCusPermission("1");
////            if (!flag) {
////                throw new SerException("您不是相应部门的人员，不可以查看");
////            }
////        }
////        RpcTransmit.transmitUserToken(userToken);
////    }
////
////    /**
////     * 核对添加修改删除审核权限（岗位级别）
////     */
////    private void checkAddIdentity() throws SerException {
////        Boolean flag = false;
////        String userToken = RpcTransmit.getUserToken();
////        UserBO userBO = userAPI.currentUser();
////        RpcTransmit.transmitUserToken(userToken);
////        String userName = userBO.getUsername();
////        if (!"admin".equals(userName.toLowerCase())) {
////            flag = cusPermissionSer.busCusPermission("2");
////            if (!flag) {
////                throw new SerException("您不是相应部门的人员，不可以操作");
////            }
////        }
////        RpcTransmit.transmitUserToken(userToken);
////    }
////
////
////    /**
////     * 导航栏核对查看权限（部门级别）
////     */
////    private Boolean guideSeeIdentity() throws SerException {
////        Boolean flag = false;
////        String userToken = RpcTransmit.getUserToken();
////        UserBO userBO = userAPI.currentUser();
////        RpcTransmit.transmitUserToken(userToken);
////        String userName = userBO.getUsername();
////        if (!"admin".equals(userName.toLowerCase())) {
////            flag = cusPermissionSer.busCusPermission("2");
////        } else {
////            flag = true;
////        }
////        return flag;
////    }
////
////    @Override
////    public Boolean sonPermission() throws SerException {
////        String userToken = RpcTransmit.getUserToken();
////        Boolean flagSee = guideSeeIdentity();
////        RpcTransmit.transmitUserToken(userToken);
////        Boolean flagAdd = guideAddIdentity();
////        if (flagSee || flagAdd) {
////            return true;
////        } else {
////            return false;
////        }
////    }
////
////    /**
////     * 导航栏核对添加修改删除审核权限（岗位级别）
////     */
////    private Boolean guideAddIdentity() throws SerException {
////        Boolean flag = false;
////        String userToken = RpcTransmit.getUserToken();
////        UserBO userBO = userAPI.currentUser();
////        RpcTransmit.transmitUserToken(userToken);
////        String userName = userBO.getUsername();
////        if (!"admin".equals(userName.toLowerCase())) {
////            flag = cusPermissionSer.getCusPermission("1");
////        } else {
////            flag = true;
////        }
////        return flag;
////    }
////
////    @Override
////    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
////        String userToken = RpcTransmit.getUserToken();
////        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
////        Boolean flag = true;
////        switch (guideAddrStatus) {
////            case LIST:
////                flag = guideSeeIdentity();
////                break;
////            case ADD:
////                flag = guideAddIdentity();
////                break;
////            case EDIT:
////                flag = guideAddIdentity();
////                break;
////            case AUDIT:
////                flag = guideAddIdentity();
////                break;
////            case DELETE:
////                flag = guideAddIdentity();
////                break;
////            case CONGEL:
////                flag = guideAddIdentity();
////                break;
////            case THAW:
////                flag = guideAddIdentity();
////                break;
////            case COLLECT:
////                flag = guideAddIdentity();
////                break;
////            case IMPORT:
////                flag = guideAddIdentity();
////                break;
////            case EXPORT:
////                flag = guideAddIdentity();
////                break;
////            case UPLOAD:
////                flag = guideAddIdentity();
////                break;
////            case DOWNLOAD:
////                flag = guideAddIdentity();
////                break;
////            case SEE:
////                flag = guideSeeIdentity();
////                break;
////            case SEEFILE:
////                flag = guideSeeIdentity();
////                break;
////            default:
////                flag = true;
////                break;
////        }
////
////        RpcTransmit.transmitUserToken(userToken);
////        return flag;
////    }
////
////    //信息提供人
////    @Override
////    public List<String> listInformationProvide() throws SerException {
////        String[] fields = new String[]{"marketInfoCollecting"};
////        List<MarketInfoBO> marketInfoBOS = super.findBySql("select marketInfoCollecting from market_marketinfo group by marketInfoCollecting order by marketInfoCollecting asc ", MarketInfoBO.class, fields);
////
////        List<String> marketInfoCollectingList = marketInfoBOS.stream().map(MarketInfoBO::getMarketInfoCollecting)
////                .filter(marketInfoCollecting -> (marketInfoCollecting != null || !"".equals(marketInfoCollecting.trim()))).distinct().collect(Collectors.toList());
////        return marketInfoCollectingList;
////    }
////
////    //业务揽接人
////    @Override
////    public List<String> listBusinessContracting() throws SerException {
////        return null;
////    }
////
////    //业务洽谈人
////    @Override
////    public List<String> listBusinessNegotiation() throws SerException {
////        String[] fields = new String[]{"communicateUser"};
////        List<ProjectContractBO> projectContractBOS = super.findBySql("select communicateUser from contractcommunicate_projectcontract group by communicateUser order by communicateUser asc ", ProjectContractBO.class, fields);
////        List<String> communicateUserList = projectContractBOS.stream().map(ProjectContractBO::getCommunicateUser)
////                .filter(communicateUser -> (communicateUser != null || !"".equals(communicateUser.trim()))).distinct().collect(Collectors.toList());
////        return communicateUserList;
////    }
////
////    //维护人
////    @Override
////    public List<String> listMaintenance() throws SerException {
////        String[] fields = new String[]{"servePrincipal"};
////
////        List<MarketServeRecordBO> marketServeRecordBOS = super.findBySql("select servePrincipal from marketactivitymanage_marketserverecord group by servePrincipal order by servePrincipal asc ", MarketServeRecordBO.class, fields);
////        List<String> servePrincipalList = marketServeRecordBOS.stream().map(MarketServeRecordBO::getServePrincipal)
////                .filter(servePrincipal -> (servePrincipal != null || !"".equals(servePrincipal.trim()))).distinct().collect(Collectors.toList());
////        return servePrincipalList;
////    }
////
////    @Override
////    public List<CommissionQuotaBO> listAreas() throws SerException {
////        //从项目提成中的实际项目提成中获得地区,项目名称,实际业务提成总额
////        TargetAuotaDTO targetAuotaDTO = new TargetAuotaDTO();
//////        targetAuotaDTO.getConditions().add(Restrict.eq("plan", !Boolean.FALSE));
//////        targetAuotaDTO.getSorts().add("createTime=desc");
////        List<TargetAuotaBO> targetAuotaBOList = targetAuotaAPI.actualMaps(targetAuotaDTO);
////
////        List<CommissionQuotaBO> commissionQuotaBOList = new ArrayList<CommissionQuotaBO>();
////        if (null != targetAuotaBOList && targetAuotaBOList.size() > 0) {
////            for (TargetAuotaBO bo : targetAuotaBOList) {
////                CommissionQuotaBO commissionQuotaBO = BeanTransform.copyProperties(bo, CommissionQuotaBO.class, "allocationId", "factor", "manage", "capital", "staff", "risk", "profit", "remark", "plan","project","business");
////                commissionQuotaBO.setProjectName(bo.getProject());
////                commissionQuotaBO.setActualAmount(bo.getBusiness());
////                commissionQuotaBOList.add(commissionQuotaBO);
////            }
////            return commissionQuotaBOList;
////        }
////        return commissionQuotaBOList;
////    }
////
////    //判断信息提供人是否是商务部门
////    Boolean isBusiness(CommissionQuotaTO commissionQuotaTO) throws SerException {
////        boolean flag = false;
////
////        UserBO userBO = userAPI.findByUsername(commissionQuotaTO.getInformationProvide());
////
////        if (userBO == null) {
////            return false;
////        } else {
////            String id = userBO.getId();
////            flag = positionDetailUserAPI.checkAsUserDepartment(id, "7f3b8940-abfc-4deb-8a81-2140cbf2ccfb");
////            return flag;
////        }
////    }
//}