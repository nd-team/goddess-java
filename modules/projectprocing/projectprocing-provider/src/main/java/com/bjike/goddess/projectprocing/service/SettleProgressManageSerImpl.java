package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.businessproject.api.BusinessContractAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.bean.DataTypeUtils;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.projectprocing.bo.*;
import com.bjike.goddess.projectprocing.dto.NodeHeadersCustomDTO;
import com.bjike.goddess.projectprocing.dto.SettleProgressManageDTO;
import com.bjike.goddess.projectprocing.entity.HeadersCustom;
import com.bjike.goddess.projectprocing.entity.NodeHeadersCustom;
import com.bjike.goddess.projectprocing.entity.SettleProgressManage;
import com.bjike.goddess.projectprocing.entity.SettleProgressRecord;
import com.bjike.goddess.projectprocing.enums.GuideAddrStatus;
import com.bjike.goddess.projectprocing.to.*;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 结算进度管理业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 02:22 ]
 * @Description: [ 结算进度管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class SettleProgressManageSerImpl extends ServiceImpl<SettleProgressManage, SettleProgressManageDTO> implements SettleProgressManageSer {
    @Autowired
    private HeadersCustomSer headersCustomSer;
    @Autowired
    private NodeHeadersCustomSer nodeHeadersCustomSer;
    @Autowired
    private BusinessContractAPI businessContractAPI;
    @Autowired
    private SettleProgressRecordSer settleProgressRecordSer;
    @Autowired
    private PositionUserDetailAPI positionUserDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 检查权限(部门)
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
            throw new SerException("您不是本部门人员,没有该操作权限");
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

    /**
     * 分配责任人权限（部门级别）
     */
    private Boolean guideAllotmentIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("7");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAllotment = guideAllotmentIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagAllotment) {
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
                flag = guideIdentity() || guideAllotmentIdentity();
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
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case SEEFILE:
                flag = guideIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            case ASSIGNEDPERSON:
                flag = guideAllotmentIdentity();
                break;
            case IMPORT:
                flag = guideIdentity();
                break;
            case EXPORT:
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
    public Long countManage(SettleProgressManageDTO settleProgressManageDTO) throws SerException {
        searchCondi(settleProgressManageDTO);
        Long count = super.count(settleProgressManageDTO);
        return count;
    }

    @Override
    public SettleProgressManageBO getOneById(String id) throws SerException {
        SettleProgressManage settleProgressManage = super.findById(id);
        return BeanTransform.copyProperties(settleProgressManage, SettleProgressManageBO.class);
    }

    @Override
    public List<SettleProgressManageBO> listManage(SettleProgressManageDTO settleProgressManageDTO) throws SerException {
        searchCondi(settleProgressManageDTO);
        List<SettleProgressManage> settleProgressManages = super.findByCis(settleProgressManageDTO, true);
        return BeanTransform.copyProperties(settleProgressManages, SettleProgressManageBO.class);
    }

    private void searchCondi(SettleProgressManageDTO settleProgressManageDTO) throws SerException {
        if (StringUtils.isNotBlank(settleProgressManageDTO.getOperatorName())) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("operatorName", settleProgressManageDTO.getOperatorName()));
        }
        if (StringUtils.isNotBlank(settleProgressManageDTO.getArea())) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("area", settleProgressManageDTO.getArea()));
        }
        if (StringUtils.isNotBlank(settleProgressManageDTO.getOutUnit())) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("outUnit", settleProgressManageDTO.getOutUnit()));
        }
        if (StringUtils.isNotBlank(settleProgressManageDTO.getSaleContractNo())) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("saleContractNo", settleProgressManageDTO.getSaleContractNo()));
        }
        if (StringUtils.isNotBlank(settleProgressManageDTO.getContractNo())) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("contractNo", settleProgressManageDTO.getContractNo()));
        }
        if (settleProgressManageDTO.getDispatCondition() != null) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("dispatCondition", settleProgressManageDTO.getDispatCondition()));
        }
        if (settleProgressManageDTO.getActualCompletedState() != null) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("actualCompletedState", settleProgressManageDTO.getActualCompletedState()));
        }
        if (settleProgressManageDTO.getKpi() != null) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("kpi", settleProgressManageDTO.getKpi()));
        }
        if (StringUtils.isNotBlank(settleProgressManageDTO.getProgress())) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("progress", settleProgressManageDTO.getProgress()));
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SettleProgressManageBO addManage(SettleProgressManageTO settleProgressManageTO) throws SerException {
        checkPermission();
        SettleProgressManage settleProgressManage = BeanTransform.copyProperties(settleProgressManageTO, SettleProgressManage.class, true);
        settleProgressManage.setCreateTime(LocalDateTime.now());
        settleProgressManage.setUpdateDate(LocalDateTime.now());
        super.save(settleProgressManage);
        List<HeadersCustomTO> headersCustomTOList = settleProgressManageTO.getHeadersCustomTOS();
        List<NodeHeadersCustomTO> nodeHeadersCustomTOList = settleProgressManageTO.getNodeHeadersCustomTOS();
        if (headersCustomTOList != null && headersCustomTOList.size() > 0) {
            List<HeadersCustom> headersCustomList = new ArrayList<>();
            for (HeadersCustomTO headersCustomTO : headersCustomTOList) {
                HeadersCustom headersCustom = new HeadersCustom();
                headersCustom.setCreateTime(LocalDateTime.now());
                headersCustom.setContent(headersCustomTO.getContent());
                headersCustom.setFatherId(headersCustomTO.getId());
                headersCustom.setProssManageId(settleProgressManage.getId());
                headersCustomList.add(headersCustom);
            }
            headersCustomSer.save(headersCustomList);
        }
        if (nodeHeadersCustomTOList != null && nodeHeadersCustomTOList.size() > 0) {
            List<NodeHeadersCustom> nodeHeadersCustomList = new ArrayList<>();
            for (NodeHeadersCustomTO nodeHeadersCustomTO : nodeHeadersCustomTOList) {
                NodeHeadersCustom nodeHeadersCustom = new NodeHeadersCustom();
                nodeHeadersCustom.setCreateTime(LocalDateTime.now());
                nodeHeadersCustom.setProssManageId(settleProgressManage.getId());
                nodeHeadersCustom.setFatherId(nodeHeadersCustomTO.getId());
                nodeHeadersCustom.setNodeOneNameContent(1);
                nodeHeadersCustom.setNodeOneContent(DateUtil.parseDate(nodeHeadersCustomTO.getNodeOneContent()));
                nodeHeadersCustom.setNodeTwoContent(nodeHeadersCustom.getNodeOneContent().plusDays(nodeHeadersCustomTO.getNodeTwoInterDate()));
                nodeHeadersCustom.setNodeThreeContent(nodeHeadersCustom.getNodeTwoContent().plusDays(nodeHeadersCustomTO.getNodeThreeInterDate()));
                nodeHeadersCustom.setNodeFourContent(nodeHeadersCustom.getNodeThreeContent().plusDays(nodeHeadersCustomTO.getNodeFourInterDate()));
                nodeHeadersCustomList.add(nodeHeadersCustom);
            }
            nodeHeadersCustomSer.save(nodeHeadersCustomList);
        }
        return BeanTransform.copyProperties(settleProgressManage, SettleProgressManageBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SettleProgressManageBO editManage(SettleProgressManageTO settleProgressManageTO) throws SerException {
        checkPermission();
        SettleProgressManage settleProgressManage = super.findById(settleProgressManageTO.getId());
        LocalDateTime dateTime = settleProgressManage.getCreateTime();
        settleProgressManage = BeanTransform.copyProperties(settleProgressManageTO, SettleProgressManage.class, true);
        settleProgressManage.setCreateTime(dateTime);
        settleProgressManage.setUpdateDate(LocalDateTime.now());
        settleProgressManage.setModifyTime(LocalDateTime.now());
        super.update(settleProgressManage);
        List<HeadersCustomTO> headersCustomTOList = settleProgressManageTO.getHeadersCustomTOS();
        List<NodeHeadersCustomTO> nodeHeadersCustomTOList = settleProgressManageTO.getNodeHeadersCustomTOS();
        headersCustomSer.removeByManageId(settleProgressManageTO.getId());
        nodeHeadersCustomSer.removeByManageId(settleProgressManageTO.getId());
        if (headersCustomTOList != null && headersCustomTOList.size() > 0) {
            List<HeadersCustom> headersCustomList = new ArrayList<>();
            for (HeadersCustomTO headersCustomTO : headersCustomTOList) {
                HeadersCustom headersCustom = new HeadersCustom();
                headersCustom.setCreateTime(LocalDateTime.now());
                headersCustom.setContent(headersCustomTO.getContent());
                headersCustom.setFatherId(headersCustomTO.getId());
                headersCustom.setProssManageId(settleProgressManage.getId());
                headersCustomList.add(headersCustom);
            }
            headersCustomSer.save(headersCustomList);
        }
        if (nodeHeadersCustomTOList != null && nodeHeadersCustomTOList.size() > 0) {
            List<NodeHeadersCustom> nodeHeadersCustomList = new ArrayList<>();
            for (NodeHeadersCustomTO nodeHeadersCustomTO : nodeHeadersCustomTOList) {
                NodeHeadersCustom nodeHeadersCustom = new NodeHeadersCustom();
                nodeHeadersCustom.setCreateTime(LocalDateTime.now());
                nodeHeadersCustom.setProssManageId(settleProgressManage.getId());
                nodeHeadersCustom.setFatherId(nodeHeadersCustomTO.getId());
                nodeHeadersCustom.setNodeOneNameContent(1);
                nodeHeadersCustom.setNodeOneContent(DateUtil.parseDate(nodeHeadersCustomTO.getNodeOneContent()));
                nodeHeadersCustom.setNodeTwoContent(nodeHeadersCustom.getNodeOneContent().plusDays(nodeHeadersCustomTO.getNodeTwoInterDate()));
                nodeHeadersCustom.setNodeThreeContent(nodeHeadersCustom.getNodeTwoContent().plusDays(nodeHeadersCustomTO.getNodeThreeInterDate()));
                nodeHeadersCustom.setNodeFourContent(nodeHeadersCustom.getNodeThreeContent().plusDays(nodeHeadersCustomTO.getNodeFourInterDate()));
                nodeHeadersCustomList.add(nodeHeadersCustom);
            }
            nodeHeadersCustomSer.save(nodeHeadersCustomList);
        }
        return BeanTransform.copyProperties(settleProgressManage, SettleProgressManageBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteManage(String id) throws SerException {
        checkPermission();
        headersCustomSer.removeByManageId(id);
        nodeHeadersCustomSer.removeByManageId(id);
        super.remove(id);
    }

    @Override
    public List<String> findOperatorName() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        if (CollectionUtils.isEmpty(settleProgressManageList)) {
            Collections.emptyList();
        }
        return settleProgressManageList.stream().map(SettleProgressManage::getOperatorName).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> findArea() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        if (CollectionUtils.isEmpty(settleProgressManageList)) {
            Collections.emptyList();
        }
        return settleProgressManageList.stream().map(SettleProgressManage::getArea).distinct().collect(Collectors.toList());

    }

    @Override
    public List<String> findOutUnit() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        if (CollectionUtils.isEmpty(settleProgressManageList)) {
            Collections.emptyList();
        }
        return settleProgressManageList.stream().map(SettleProgressManage::getOutUnit).distinct().collect(Collectors.toList());

    }

    @Override
    public List<String> findSaleContractNo() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        if (CollectionUtils.isEmpty(settleProgressManageList)) {
            Collections.emptyList();
        }
        return settleProgressManageList.stream().map(SettleProgressManage::getSaleContractNo).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> findContractNo() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        if (CollectionUtils.isEmpty(settleProgressManageList)) {
            Collections.emptyList();
        }
        return settleProgressManageList.stream().map(SettleProgressManage::getContractNo).distinct().collect(Collectors.toList());

    }

    @Override
    public List<String> findProgress() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        if (CollectionUtils.isEmpty(settleProgressManageList)) {
            Collections.emptyList();
        }
        return settleProgressManageList.stream().map(SettleProgressManage::getProgress).distinct().collect(Collectors.toList());

    }

    @Override
    public List<String> findDispatName() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        List<String> dispatNames = businessContractAPI.findSingleContractName();
        List<String> returnDates = new ArrayList<>();
        if (dispatNames != null && dispatNames.size() > 0) {
            if (settleProgressManageList == null || settleProgressManageList.size() == 0) {
                returnDates = dispatNames;
            } else {
                if (dispatNames != null && dispatNames.size() > 0) {
                    for (String dispatName : dispatNames) {
                        for (SettleProgressManage settleProgressManage : settleProgressManageList) {
                            if (!dispatName.equals(settleProgressManage.getDispatName())) {
                                returnDates.add(dispatName);
                            }
                        }
                    }
                }
            }
        }
        return returnDates;
    }

    @Override
    public List<ScreeningSettleProgressManageBO> listByOutUnit(String outUnit) throws SerException {
        SettleProgressManageDTO settleProgressManageDTO = new SettleProgressManageDTO();
        settleProgressManageDTO.getConditions().add(Restrict.eq("outUnit", outUnit));
        List<SettleProgressManage> settleProgressManageList = super.findByCis(settleProgressManageDTO, true);
        List<ScreeningSettleProgressManageBO> screeningSettleProgressManageBOList = BeanTransform.copyProperties(settleProgressManageList, ScreeningSettleProgressManageBO.class);
        if (screeningSettleProgressManageBOList != null && screeningSettleProgressManageBOList.size() > 0) {
            for (ScreeningSettleProgressManageBO screeningSettleProgressManageBO : screeningSettleProgressManageBOList) {
                screeningSettleProgressManageBO.setHeadersCustomBOList(headersCustomSer.getByManageId(screeningSettleProgressManageBO.getId()));
                screeningSettleProgressManageBO.setNodeHeadersCustomBOList(nodeHeadersCustomSer.getByManageId(screeningSettleProgressManageBO.getId()));
            }
        }
        return screeningSettleProgressManageBOList;
    }

    @Override
    public List<AllotmentNodeDataBO> findAllNodeById(String id) throws SerException {
        SettleProgressManage settleProgressManage = super.findById(id);
        List<NodeHeadersCustomBO> nodeHeadersCustomList = nodeHeadersCustomSer.getByManageId(id);
        List<AllotmentNodeDataBO> allotmentNodeDataBOList = new ArrayList<>();
        if (nodeHeadersCustomList != null && nodeHeadersCustomList.size() > 0) {
            for (NodeHeadersCustomBO nodeHeadersCustomBO : nodeHeadersCustomList) {
                NodeHeadersCustomBO nodeHeadersCustomBO1 = nodeHeadersCustomSer.getByFatherId(nodeHeadersCustomBO.getFatherId());
                AllotmentNodeDataBO allotmentNodeDataBO = new AllotmentNodeDataBO();
                allotmentNodeDataBO.setNodeId(nodeHeadersCustomBO.getId());
                allotmentNodeDataBO.setNodeHeader(nodeHeadersCustomBO1.getNodeOneHeader());
                allotmentNodeDataBO.setNodeContent(nodeHeadersCustomBO.getNodeOneContent());
                allotmentNodeDataBO.setContractNo(settleProgressManage.getContractNo());
                allotmentNodeDataBO.setDispatName(settleProgressManage.getDispatName());
                allotmentNodeDataBO.setPayableAmount(settleProgressManage.getPayableAmount());
                allotmentNodeDataBO.setSaleContractNo(settleProgressManage.getSaleContractNo());
                allotmentNodeDataBOList.add(allotmentNodeDataBO);

                AllotmentNodeDataBO allotmentNodeDataBO1 = new AllotmentNodeDataBO();
                allotmentNodeDataBO1.setNodeId(nodeHeadersCustomBO.getId());
                allotmentNodeDataBO1.setNodeHeader(nodeHeadersCustomBO1.getNodeTwoHeader());
                allotmentNodeDataBO1.setNodeContent(nodeHeadersCustomBO.getNodeTwoContent());
                allotmentNodeDataBO1.setContractNo(settleProgressManage.getContractNo());
                allotmentNodeDataBO1.setDispatName(settleProgressManage.getDispatName());
                allotmentNodeDataBO1.setPayableAmount(settleProgressManage.getPayableAmount());
                allotmentNodeDataBO1.setSaleContractNo(settleProgressManage.getSaleContractNo());
                allotmentNodeDataBOList.add(allotmentNodeDataBO1);

                AllotmentNodeDataBO allotmentNodeDataBO2 = new AllotmentNodeDataBO();
                allotmentNodeDataBO2.setNodeId(nodeHeadersCustomBO.getId());
                allotmentNodeDataBO2.setNodeHeader(nodeHeadersCustomBO1.getNodeThreeHeader());
                allotmentNodeDataBO2.setNodeContent(nodeHeadersCustomBO.getNodeThreeContent());
                allotmentNodeDataBO2.setContractNo(settleProgressManage.getContractNo());
                allotmentNodeDataBO2.setDispatName(settleProgressManage.getDispatName());
                allotmentNodeDataBO2.setPayableAmount(settleProgressManage.getPayableAmount());
                allotmentNodeDataBO2.setSaleContractNo(settleProgressManage.getSaleContractNo());
                allotmentNodeDataBOList.add(allotmentNodeDataBO2);

                AllotmentNodeDataBO allotmentNodeDataBO3 = new AllotmentNodeDataBO();
                allotmentNodeDataBO3.setNodeId(nodeHeadersCustomBO.getId());
                allotmentNodeDataBO3.setNodeHeader(nodeHeadersCustomBO1.getNodeFourHeader());
                allotmentNodeDataBO3.setNodeContent(nodeHeadersCustomBO.getNodeFourContent());
                allotmentNodeDataBO3.setContractNo(settleProgressManage.getContractNo());
                allotmentNodeDataBO3.setDispatName(settleProgressManage.getDispatName());
                allotmentNodeDataBO3.setPayableAmount(settleProgressManage.getPayableAmount());
                allotmentNodeDataBO3.setSaleContractNo(settleProgressManage.getSaleContractNo());
                allotmentNodeDataBOList.add(allotmentNodeDataBO3);
            }
        }
        return allotmentNodeDataBOList;
    }

    @Override
    public SettleProgressManageBO findByContractNo(String contractNo) throws SerException {
        SettleProgressManageDTO settleProgressManageDTO = new SettleProgressManageDTO();
        settleProgressManageDTO.getConditions().add(Restrict.eq("contractNo", contractNo));
        SettleProgressManage settleProgressManage = super.findOne(settleProgressManageDTO);
        return BeanTransform.copyProperties(settleProgressManage, SettleProgressManageBO.class);
    }

    @Override
    public byte[] exportExcel(String outUnit) throws SerException {
        checkPermission();
        List<String> titles = new ArrayList<>();
        titles.add("更改日期");
        titles.add("是否更改日期");
        titles.add("测算分类");
        titles.add("测算是否通过");
        titles.add("运营商名称");
        titles.add("地区");
        titles.add("外包单位");
        titles.add("类型");
        titles.add("专业");
        titles.add("派工名称");
        titles.add("厂家");
        titles.add("销售合同号");
        titles.add("外包合同号");
        titles.add("所属项目组");
        titles.add("内部项目编号");
        titles.add("内部项目名称");
        titles.add("派工情况");
        titles.add("派工金额");
        titles.add("实际完工状态");
        titles.add("完工时间");
        titles.add("可结算金额");
        titles.add("本次预计结算金额");
        titles.add("剩余未结算金额");
        titles.add("已到帐金额");
        titles.add("到账时间");
        titles.add("是否全部结算完成");
        titles.add("分批结算");
        titles.add("预计开票时间");
        titles.add("扣除违约金后金额");
        titles.add("扣除管理费后金额");
        titles.add("督导未结");
        titles.add("状态");
        titles.add("派工条目");
        titles.add("单位");
        titles.add("工程类型");
        titles.add("工期");
        titles.add("数量");
        titles.add("单价");
        titles.add("站点");
        titles.add("已完工未做结算");
        titles.add("进度");
        titles.add("到货时间");
        titles.add("kpi");
        titles.add("现场实际情况（KPI");
        titles.add("设备型号");
        titles.add("大概描述项目派工的情况备注");
        titles.add("总规模数");
        titles.add("是否可制作申请结算");
        titles.add("是否影响结算");
        titles.add("结算计划");
        titles.add("正在执行项目");
        titles.add("归属");
        titles.add("备注");
        List<String> fields = new ArrayList<>();
        List<Field> fieldList = ClazzUtils.getFields(SettleProgressManage.class);
        for (Field field : fieldList) {
            if (!field.getName().equalsIgnoreCase("id")) {
                //TODO 这里改了
//                Column c = field.getAnnotation(Column.class);
                fields.add(field.getName());
            }
        }
        String sql = " SELECT b.header,a.content,b.outUnit,a.is_requiredFill,b.types,a.remark,a.prossManageId  FROM " +
                "    (SELECT b.* FROM projectprocing_settleprogressmanage a, " +
                "    projectprocing_headerscustom b " +
                "    where " +
                "    a.id=prossManageId)a,projectprocing_headerscustom b " +
                " where a.fatherId=b.id and b.outUnit='" + outUnit + "' ";

        int customStartSize = titles.size();
        String[] str_fields = new String[]{"header", "content", "outUnit", "is_requiredFill", "types", "remark", "prossManageId"};
        List<HeadersCustom> customs = super.findBySql(sql, HeadersCustom.class, str_fields);
        for (HeadersCustom custom : customs) {//自定义
            titles.add(custom.getHeader());
        }
        int nodeStartSize = titles.size(); //节点开始列

        NodeHeadersCustomDTO ncDto = new NodeHeadersCustomDTO();
        ncDto.getConditions().add(Restrict.eq("outUnit", outUnit));
        List<NodeHeadersCustom> nodeHeadersCustoms = nodeHeadersCustomSer.findByCis(ncDto);
        for (NodeHeadersCustom custom : nodeHeadersCustoms) {//自定义
            titles.add(custom.getNodeOneName());
            titles.add(custom.getNodeOneHeader());
            titles.add(custom.getNodeTwoHeader());
            titles.add(custom.getNodeThreeHeader());
            titles.add(custom.getNodeFourHeader());
        }
        XSSFRow headerRow;
        SettleProgressManageDTO dto = new SettleProgressManageDTO();
        dto.getConditions().add(Restrict.eq("outUnit", outUnit));
        List<ScreeningSettleProgressManageBO> settleProgressManages = listByOutUnit(outUnit);//固定
        if (settleProgressManages != null && settleProgressManages.size() > 0) {
            XSSFWorkbook wb = new XSSFWorkbook(); // 创建一个工作execl文档
            XSSFSheet sheet = wb.createSheet("test");
            headerRow = sheet.createRow(0);
            int i = 0;
            for (String title : titles) {
                XSSFCell cell = headerRow.createCell(i++);
                cell.setCellValue(title);
                cell.setCellType(CellType.STRING);
            }
            int rowIndex = 1;
            for (ScreeningSettleProgressManageBO spm : settleProgressManages) {
                List<Field> _fields = ClazzUtils.getFields(spm.getClass());
                XSSFRow row = sheet.createRow(rowIndex++);
                int cellIndex = 0;
                for (String f : fields) { //固定的
                    XSSFCell cell = row.createCell(cellIndex++);
                    for (Field field : _fields) {
                        field.setAccessible(true);
                        if (field.getName().equalsIgnoreCase(f)) {
                            try {
                                String name = field.getType().getSimpleName();
                                if (!name.equalsIgnoreCase("boolean")) {
                                    ExcelUtil.setCellValue(cell, field, field.get(spm));
                                } else {
                                    Object o = field.get(spm);
                                    if (null != o)
                                        cell.setCellValue((boolean) o == true ? "是" : "否");
                                }

                            } catch (Exception e) {
                                throw new SerException(e.getMessage());
                            }
                            break;
                        }
                    }
                }
                //自定义的
                for (HeadersCustom custom : customs) {
                    if (custom.getProssManageId().equals(spm.getId())) {
                        XSSFCell cell = row.createCell(customStartSize++);
                        cell.setCellValue(custom.getContent());
                    }
                }
                int sum = titles.size();
                for (int index = nodeStartSize; index < sum; index++) {
                    String title = titles.get(index);
                    if (spm.getNodeHeadersCustomBOList() != null) {

                        for (NodeHeadersCustomBO nhc : spm.getNodeHeadersCustomBOList()) {
                            if (title.equals(nhc.getNodeOneName())) {
                                XSSFCell cell = row.createCell(index);
                                cell.setCellValue(nhc.getNodeOneNameContent() == null ? "" : nhc.getNodeOneNameContent().toString());

                            }
                            if (title.equals(nhc.getNodeOneHeader())) {
                                XSSFCell cell = row.createCell(index);
                                cell.setCellValue(nhc.getNodeOneContent() == null ? "" : nhc.getNodeOneContent().toString());

                            }
                            if (title.equals(nhc.getNodeTwoHeader())) {
                                XSSFCell cell = row.createCell(index);
                                cell.setCellValue(nhc.getNodeTwoContent() == null ? "" : nhc.getNodeTwoContent().toString());

                            }
                            if (title.equals(nhc.getNodeThreeHeader())) {
                                XSSFCell cell = row.createCell(index);
                                cell.setCellValue(nhc.getNodeThreeContent() == null ? "" : nhc.getNodeThreeContent().toString());

                            }
                            if (title.equals(nhc.getNodeFourHeader())) {
                                XSSFCell cell = row.createCell(index);
                                cell.setCellValue(nhc.getNodeFourContent() == null ? "" : nhc.getNodeFourContent().toString());

                            }
                        }
                    }

                }

            }
            ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
            try {
                wb.write(os);
            } catch (IOException e) {
                throw new SerException("文件写入错误");
            }
            return os.toByteArray();
        }
        return new byte[0];

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void importExcel(List<InputStream> inputStreams, String outUnit) throws SerException {
        checkPermission();
        if (null != inputStreams && inputStreams.size() > 0) {
            List<String> titles = new ArrayList<>();
            titles.add("更改日期");
            titles.add("是否更改日期");
            titles.add("测算分类");
            titles.add("测算是否通过");
            titles.add("运营商名称");
            titles.add("地区");
            titles.add("外包单位");
            titles.add("类型");
            titles.add("专业");
            titles.add("派工名称");
            titles.add("厂家");
            titles.add("销售合同号");
            titles.add("外包合同号");
            titles.add("所属项目组");
            titles.add("内部项目编号");
            titles.add("内部项目名称");
            titles.add("派工情况");
            titles.add("派工金额");
            titles.add("实际完工状态");
            titles.add("完工时间");
            titles.add("可结算金额");
            titles.add("本次预计结算金额");
            titles.add("剩余未结算金额");
            titles.add("已到帐金额");
            titles.add("到账时间");
            titles.add("是否全部结算完成");
            titles.add("分批结算");
            titles.add("预计开票时间");
            titles.add("扣除违约金后金额");
            titles.add("扣除管理费后金额");
            titles.add("督导未结");
            titles.add("状态");
            titles.add("派工条目");
            titles.add("单位");
            titles.add("工程类型");
            titles.add("工期");
            titles.add("数量");
            titles.add("单价");
            titles.add("站点");
            titles.add("已完工未做结算");
            titles.add("进度");
            titles.add("到货时间");
            titles.add("kpi");
            titles.add("现场实际情况（KPI");
            titles.add("设备型号");
            titles.add("大概描述项目派工的情况备注");
            titles.add("总规模数");
            titles.add("是否可制作申请结算");
            titles.add("是否影响结算");
            titles.add("结算计划");
            titles.add("正在执行项目");
            titles.add("归属");
            titles.add("备注");
            int customSize = titles.size();
            String sql = " SELECT b.header,a.content,b.outUnit,a.is_requiredFill,b.types,a.remark,a.prossManageId  FROM " +
                    "    (SELECT b.* FROM projectprocing_settleprogressmanage a, " +
                    "    projectprocing_headerscustom b " +
                    "    where " +
                    "    a.id=prossManageId)a,projectprocing_headerscustom b " +
                    " where a.fatherId=b.id and b.outUnit='" + outUnit + "' ";

            String[] str_fields = new String[]{"header", "content", "outUnit", "is_requiredFill", "types", "remark", "prossManageId"};
            List<HeadersCustom> customs = super.findBySql(sql, HeadersCustom.class, str_fields);
            for (HeadersCustom custom : customs) {//自定义
                titles.add(custom.getHeader());
            }
            int nodeSize = titles.size();
            NodeHeadersCustomDTO ncDto = new NodeHeadersCustomDTO();
            ncDto.getConditions().add(Restrict.eq("outUnit", outUnit));
            List<NodeHeadersCustom> ncs = nodeHeadersCustomSer.findByCis(ncDto);
            for (NodeHeadersCustom c : ncs) {
                titles.add(c.getNodeOneName());
                titles.add(c.getNodeOneHeader());
                titles.add(c.getNodeTwoHeader());
                titles.add(c.getNodeThreeHeader());
                titles.add(c.getNodeFourHeader());
            }
            Object o_file = inputStreams.get(1);
            InputStream is = new ByteArrayInputStream((byte[]) o_file);
            XSSFWorkbook wb;
            try {
                wb = ExcelUtil.getWb(is);
            } catch (Exception e) {
                throw new SerException("解析excel文件错误,请检查文件是否正确");
            }
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow titleRow = sheet.getRow(0);
            int i = 0;
            for (String title : titles) {//验证表头
                String f = String.valueOf(titleRow.getCell(i++).getStringCellValue());
                if (!f.equals(title)) {
                    throw new SerException("导入表头【" + f + "】" +
                            "与系统设置的表头【" + title + "】不一致");
                }
            }
            //开始插入数据
            int rowCount = sheet.getLastRowNum() + 1;
            List<String> str_titles = new ArrayList<>();
            List<Field> fields = ClazzUtils.getFields(SettleProgressManage.class);
            for (Field field : fields) {
                if (!field.getName().equals("id") && !field.getName().equals("createTime") && !field.getName().equals("modifyTime")) {
                    str_titles.add(field.getName());
                }
            }

            for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                SettleProgressManage spm = new SettleProgressManage();
                List<Field> fieldList = ClazzUtils.getFields(spm.getClass());
                int cellIndex = 0;
                for (String s : str_titles) {
                    String cellVal = null;
                    try {
                        cellVal = ExcelUtil.getCellValue(row.getCell(cellIndex++), null);
                    } catch (Exception e) {
                        throw new SerException(e.getMessage());
                    }

                    for (Field field : fieldList) {
                        if (field.getName().equalsIgnoreCase(s)) {
                            field.setAccessible(true);
                            try {
                                if (field.getType().isEnum()) {
                                    cellVal = StringUtils.isBlank(cellVal) ? "0" : cellVal;
                                    ExcelUtil.enumToField(field, spm, cellVal);
                                } else {
                                    if (StringUtils.isBlank(cellVal)) {
                                        if (field.getType().getSimpleName().equals("LocalDateTime")) {
                                            cellVal = DateUtil.dateToString(LocalDateTime.now());
                                        } else if (field.getType().getSimpleName().equals("LocalDate")) {
                                            cellVal = DateUtil.dateToString(LocalDate.now());
                                        } else if (field.getType().getSimpleName().equals("LocalTime")) {
                                            cellVal = DateUtil.dateToString(LocalTime.now());
                                        } else if (field.getType().getSimpleName().equals("Boolean")) {
                                            cellVal = "true";
                                        }
                                    }
                                    Object v = DataTypeUtils.convertDataType(cellVal, field.getType().getSimpleName());
                                    field.set(spm, v);
                                }

                            } catch (Exception e) {
                                throw new SerException(e.getMessage());
                            }
                        }
                    }
                }
                spm.setCreateTime(LocalDateTime.now());
                spm.setModifyTime(LocalDateTime.now());
                super.save(spm);
                //自定义表头添加数据
                cellIndex = customSize;
                if (customs.size() > 0) {
                    try {
                        for (HeadersCustom headersCustom : customs) {
                            XSSFCell customCell = row.getCell(cellIndex++);
                            String cellVal = ExcelUtil.getCellValue(customCell, null);
                            HeadersCustom custom = new HeadersCustom();
                            custom.setCreateTime(LocalDateTime.now());
                            custom.setContent(cellVal);
                            custom.setProssManageId(spm.getId());
                            custom.setFatherId(headersCustom.getId());
                            headersCustomSer.save(custom);
                        }
                    } catch (Exception e) {
                        throw new SerException(e.getMessage());
                    }
                }

//                //自定义节点表头添加数据(跟导出处理相反操作)
                if (ncs.size() > 0) {
                    XSSFRow xr = sheet.getRow(rowIndex);
                    for (NodeHeadersCustom ncm : ncs) {
                        NodeHeadersCustom nhc = new NodeHeadersCustom();
                        nhc.setFatherId(ncm.getId());
                        nhc.setProssManageId(spm.getId());
                        Boolean bool = true;
                        for (int z = nodeSize; z < titles.size(); z++) {
                            String head = titles.get(z);
                            String val = null;
                            try {
                                val = ExcelUtil.getCellValue(xr.getCell(z), null);//值
                            } catch (Exception e) {
                                throw new SerException(e.getMessage());
                            }

                            if (ncm.getNodeOneName().equals(head)) {
                                nhc.setNodeOneNameContent(val == null ? 1 : Integer.parseInt(val));
                            } else if (ncm.getNodeOneHeader().equals(head)) {
                                if (StringUtils.isBlank(val)) {
                                    bool = false;
                                } else {
                                    nhc.setNodeOneContent(DateUtil.parseDate(val));
                                }
                            } else if (ncm.getNodeTwoHeader().equals(head)) {
                                if (nhc.getNodeOneContent() != null) {
                                    nhc.setNodeTwoContent(val == null ? nhc.getNodeOneContent().plusDays(ncm.getNodeTwoInterDate()) : DateUtil.parseDate(val));
                                }
                            } else if (ncm.getNodeThreeHeader().equals(head)) {
                                if (nhc.getNodeTwoContent() != null) {
                                    nhc.setNodeThreeContent(val == null ? nhc.getNodeTwoContent().plusDays(ncm.getNodeThreeInterDate()) : DateUtil.parseDate(val));
                                }
                            } else if (ncm.getNodeFourHeader().equals(head)) {
                                if (nhc.getNodeThreeContent() != null) {
                                    nhc.setNodeFourContent(val == null ? nhc.getNodeThreeContent().plusDays(ncm.getNodeFourInterDate()) : DateUtil.parseDate(val));
                                }
                            }
//                            break;
                        }
                        if (bool) {
                            nodeHeadersCustomSer.save(nhc);
                        }
                    }

                }

            }

        } else {
            throw new SerException("没解析到任何上传文件");
        }
    }

    @Override
    public List<String> findOutUnitByArea(String area) throws SerException {
        SettleProgressManageDTO settleProgressManageDTO = new SettleProgressManageDTO();
        settleProgressManageDTO.getConditions().add(Restrict.eq("area", area));
        List<SettleProgressManage> settleProgressManageList = super.findByCis(settleProgressManageDTO);
        if (CollectionUtils.isEmpty(settleProgressManageList)) {
            return Collections.emptyList();
        }
        return settleProgressManageList.stream().map(SettleProgressManage::getOutUnit).distinct().collect(Collectors.toList());
    }

    @Override
    public void scheduleDelay(ScheduleDelayDataTO scheduleDelayDataTO) throws SerException {
        checkPermission();
        SettleProgressManage settleProgressManage = super.findById(scheduleDelayDataTO.getId());
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        checkUpdateZD(scheduleDelayDataTO);//修改节点数据
        //添加至结算进度调整记录&结算问题汇总表中
        SettleProgressRecord settleProgressRecord = new SettleProgressRecord();
        String moneyModuleName = positionDetailUserAPI.moneyModulePerson();
        List<PositionDetailUserBO> positionDetailUserBOS = positionUserDetailAPI.findManager();
        settleProgressRecord.setOutUnit(settleProgressManage.getOutUnit());
        settleProgressRecord.setDispatchingName(settleProgressManage.getDispatName());
        settleProgressRecord.setInternalName(settleProgressManage.getInternalProName());
        settleProgressRecord.setModifier(userBO.getUsername());
        settleProgressRecord.setUpdateDate(LocalDateTime.now());
        settleProgressRecord.setUpdateContent("修改了" + scheduleDelayDataTO.getNode() + "节点时间改为" + scheduleDelayDataTO.getNodeContent());
        settleProgressRecord.setProblemDescription(scheduleDelayDataTO.getProblemDescription());
        settleProgressRecord.setProblemType(scheduleDelayDataTO.getProblemType());
        settleProgressRecord.setAssistPeoper(scheduleDelayDataTO.getAssistPeoper());
        settleProgressRecord.setAssistContent(scheduleDelayDataTO.getAssistContent());
        settleProgressRecord.setMoneyModule(moneyModuleName);
        if (positionDetailUserBOS != null && positionDetailUserBOS.size() > 0) {
            settleProgressRecord.setMoneyModule(positionDetailUserBOS.get(0).getName());
        }
        settleProgressRecordSer.save(settleProgressRecord);

        //更新本表中的更新时间
        settleProgressManage.setChangeDate(true);
        settleProgressManage.setUpdateDate(settleProgressRecord.getUpdateDate());
        super.update(settleProgressManage);
    }

    /**
     * 检测修改的是哪个节点然后进行后面节点数据的推算
     *
     * @param scheduleDelayDataTO
     * @throws SerException
     */
    private void checkUpdateZD(ScheduleDelayDataTO scheduleDelayDataTO) throws SerException {
        NodeHeadersCustom nodeHeadersCustom = nodeHeadersCustomSer.findById(scheduleDelayDataTO.getNodeId());//本条节点内容
        NodeHeadersCustomBO nodeHeadersCustomBO = nodeHeadersCustomSer.getByFatherId(nodeHeadersCustom.getFatherId());//本条数据的对应父数据(节点表头名)
        if (scheduleDelayDataTO.getNode().equals(nodeHeadersCustomBO.getNodeOneHeader())) {
            nodeHeadersCustom.setNodeOneContent(DateUtil.parseDate(scheduleDelayDataTO.getNodeContent()));
            nodeHeadersCustom.setNodeTwoContent(nodeHeadersCustom.getNodeOneContent().plusDays(nodeHeadersCustomBO.getNodeTwoInterDate()));
            nodeHeadersCustom.setNodeThreeContent(nodeHeadersCustom.getNodeTwoContent().plusDays(nodeHeadersCustomBO.getNodeThreeInterDate()));
            nodeHeadersCustom.setNodeFourContent(nodeHeadersCustom.getNodeThreeContent().plusDays(nodeHeadersCustomBO.getNodeFourInterDate()));
        } else if (scheduleDelayDataTO.getNode().equals(nodeHeadersCustomBO.getNodeTwoHeader())) {
            nodeHeadersCustom.setNodeTwoContent(DateUtil.parseDate(scheduleDelayDataTO.getNodeContent()));
            nodeHeadersCustom.setNodeThreeContent(nodeHeadersCustom.getNodeTwoContent().plusDays(nodeHeadersCustomBO.getNodeThreeInterDate()));
            nodeHeadersCustom.setNodeFourContent(nodeHeadersCustom.getNodeThreeContent().plusDays(nodeHeadersCustomBO.getNodeFourInterDate()));
        } else if (scheduleDelayDataTO.getNode().equals(nodeHeadersCustomBO.getNodeThreeHeader())) {
            nodeHeadersCustom.setNodeThreeContent(DateUtil.parseDate(scheduleDelayDataTO.getNodeContent()));
            nodeHeadersCustom.setNodeFourContent(nodeHeadersCustom.getNodeThreeContent().plusDays(nodeHeadersCustomBO.getNodeFourInterDate()));
        } else if (scheduleDelayDataTO.getNode().equals(nodeHeadersCustomBO.getNodeFourHeader())) {
            nodeHeadersCustom.setNodeFourContent(DateUtil.parseDate(scheduleDelayDataTO.getNodeContent()));
        }
        nodeHeadersCustom.setModifyTime(LocalDateTime.now());
        nodeHeadersCustomSer.update(nodeHeadersCustom);
    }

    @Override
    public List<SettleProgressSummBO> settleProgress(String area, String outUnit) throws SerException {
//        checkPermission();
        List<String> areas = findArea();
        if (StringUtils.isNotBlank(area)) {
            areas = new ArrayList<>();
            areas.add(area);
        }
        List<SettleProgressSummBO> settleProgressSummBOList = new ArrayList<>();
        for (String a : areas) {
            List<String> outUnits = findOutUnitByArea(a);
            if (StringUtils.isNotBlank(outUnit)) {
                outUnits = new ArrayList<>();
                outUnits.add(outUnit);
            }
            SettleProgressSummBO settleProgressSummBO = new SettleProgressSummBO();
            String sql1 = "SELECT ifnull(sum(dispatAmount),0) FROM projectprocing_settleprogressmanage WHERE area = '" + a + "'";
            List<Object> objectList = super.findBySql(sql1);
            settleProgressSummBO.setArea(a);
            settleProgressSummBO.setAreaStatistics(Double.parseDouble(String.valueOf(objectList.get(0))));//所属地区的总额

            List<OutUnitSummBO> outUnitSummBOList = new ArrayList<>();
            for (String out : outUnits) {
                String sql = "SELECT outUnit as outUnit,ifnull(sum(dispatAmount),0) as dispatAmount FROM projectprocing_settleprogressmanage WHERE area = '" + a + "' and outUnit = '" + out + "'";
                List<Object> objectList1 = super.findBySql(sql);
                if (objectList1 != null && objectList1.size() > 0) {
                    Object[] objects = (Object[]) objectList1.get(0);
                    OutUnitSummBO outUnitSummBO = new OutUnitSummBO();
                    outUnitSummBO.setOutUnit(String.valueOf(objects[0]));
                    outUnitSummBO.setOutUnitTotal(Double.parseDouble(String.valueOf(objects[1])));


                    List<DispatchingConditionBO> dispatchingConditionBOList = new ArrayList<>();
                    //派工情况(已派工)
                    DispatchingConditionBO dispatchingConditionBO1 = new DispatchingConditionBO();
                    dispatchingConditionBO1.setCompletedAmount("已派工金额");
                    String sql2 = "SELECT ifnull(sum(dispatAmount),0) as dispatAmount FROM projectprocing_settleprogressmanage WHERE area = '" + a + "' and outUnit = '" + out + "' and dispatCondition = 0";
                    List<Object> objectList2 = super.findBySql(sql2);
                    dispatchingConditionBO1.setCompletedAmountTot(Double.parseDouble(String.valueOf(objectList2.get(0))));

                    List<CompletionStateBO> completionStateBOS = new ArrayList<>();
                    //完工情况(已派工-已完工)
                    CompletionStateBO completionStateBO = new CompletionStateBO();
                    completionStateBO.setCompletedAmount("已派工-已完工");
                    String sql4 = "SELECT ifnull(sum(dispatAmount),0) as dispatAmount FROM projectprocing_settleprogressmanage WHERE area = '" + a + "' and outUnit = '" + out + "' and dispatCondition = 0 and actualCompletedState = 0";
                    List<Object> objectList4 = super.findBySql(sql4);
                    completionStateBO.setCompletedAmountTot(Double.parseDouble(String.valueOf(objectList4.get(0))));
                    //验收情况
                    List<AcceptSituationBO> acceptSituationBOS = new ArrayList<>();
                    //属于kip状态的数据
                    List<String> kip_list = new ArrayList<>();
                    kip_list.add("已完工-不需要验收金额");
                    kip_list.add("已完工-到货金额");
                    kip_list.add("已完工-待初验金额");
                    kip_list.add("已完工-待终验金额");
                    int i = 0;
                    for (String kip : kip_list) {
                        //如果状态为完成就跳过
                        if (i == 2) {
                            i++;
                        }
                        AcceptSituationBO acceptSituationBO = new AcceptSituationBO();
                        String sql7 = "SELECT ifnull(sum(dispatAmount),0) as dispatAmount FROM projectprocing_settleprogressmanage WHERE area = '" + a + "' and outUnit = '" + out + "' and dispatCondition = 0 and actualCompletedState = 0 and kpi = " + i + "";
                        List<Object> objectList7 = super.findBySql(sql7);
                        acceptSituationBO.setCompletedAmount(kip);
                        acceptSituationBO.setCompletedAmountCount(Double.parseDouble(String.valueOf(objectList7.get(0))));
                        acceptSituationBOS.add(acceptSituationBO);
                        i++;
                    }

                    List<String> settlementPlan_list = new ArrayList<>();
                    settlementPlan_list.add("已完工-可制作结算资料");
                    settlementPlan_list.add("已完工-正在走结算金额（到账金额）");
                    List<Integer> int_list = new ArrayList<>();
                    int_list.add(1);
                    int_list.add(3);
                    int j = 0;
                    for (String settlementPlan : settlementPlan_list) {
                        AcceptSituationBO acceptSituationBO = new AcceptSituationBO();
                        String sql8 = "SELECT ifnull(sum(estimSettleAmount),0) as dispatAmount FROM projectprocing_settleprogressmanage WHERE area = '" + a + "' and outUnit = '" + out + "' and dispatCondition = 0 and actualCompletedState = 0 and settlementPlan = " + int_list.get(j) + "";
                        List<Object> objectList8 = super.findBySql(sql8);
                        acceptSituationBO.setCompletedAmount(settlementPlan);
                        acceptSituationBO.setCompletedAmountCount(Double.parseDouble(String.valueOf(objectList8.get(0))));
                        acceptSituationBOS.add(acceptSituationBO);
                        j++;
                    }


                    completionStateBO.setAcceptSituationBOS(acceptSituationBOS);

                    completionStateBOS.add(completionStateBO);

                    //完工情况(已派工-未完工)
                    CompletionStateBO completionStateBO1 = new CompletionStateBO();
                    completionStateBO1.setCompletedAmount("已派工-未完工");
                    String sql5 = "SELECT ifnull(sum(dispatAmount),0) as dispatAmount FROM projectprocing_settleprogressmanage WHERE area = '" + a + "' and outUnit = '" + out + "' and dispatCondition = 0 and actualCompletedState = 1";
                    List<Object> objectList5 = super.findBySql(sql5);
                    completionStateBO1.setCompletedAmountTot(Double.parseDouble(String.valueOf(objectList5.get(0))));
                    completionStateBOS.add(completionStateBO1);

                    dispatchingConditionBO1.setCompletionStateBOS(completionStateBOS);
                    dispatchingConditionBOList.add(dispatchingConditionBO1);


                    //派工情况(未派工)
                    DispatchingConditionBO dispatchingConditionBO2 = new DispatchingConditionBO();
                    dispatchingConditionBO2.setCompletedAmount("未派工金额");
                    String sql3 = "SELECT ifnull(sum(dispatAmount),0) as dispatAmount FROM projectprocing_settleprogressmanage WHERE area = '" + a + "' and outUnit = '" + out + "' and dispatCondition = 1";
                    List<Object> objectList3 = super.findBySql(sql3);
                    dispatchingConditionBO2.setCompletedAmountTot(Double.parseDouble(String.valueOf(objectList3.get(0))));

                    List<CompletionStateBO> completionStateBOS1 = new ArrayList<>();
                    //完工情况(未派工-已完工)
                    CompletionStateBO completionStateBO3 = new CompletionStateBO();
                    completionStateBO3.setCompletedAmount("未派工-已完工");
                    String sql6 = "SELECT ifnull(sum(dispatAmount),0) as dispatAmount FROM projectprocing_settleprogressmanage WHERE area = '" + a + "' and outUnit = '" + out + "' and dispatCondition = 1 and actualCompletedState = 0";
                    List<Object> objectList6 = super.findBySql(sql6);
                    completionStateBO3.setCompletedAmountTot(Double.parseDouble(String.valueOf(objectList6.get(0))));
                    completionStateBOS1.add(completionStateBO3);

                    //完工情况(未派工-未完工)
                    CompletionStateBO completionStateBO4 = new CompletionStateBO();
                    completionStateBO4.setCompletedAmount("未派工-未完工");
                    String sql7 = "SELECT ifnull(sum(dispatAmount),0) as dispatAmount FROM projectprocing_settleprogressmanage WHERE area = '" + a + "' and outUnit = '" + out + "' and dispatCondition = 1 and actualCompletedState = 1";
                    List<Object> objectList7 = super.findBySql(sql7);
                    completionStateBO4.setCompletedAmountTot(Double.parseDouble(String.valueOf(objectList7.get(0))));
                    completionStateBOS1.add(completionStateBO4);

                    dispatchingConditionBO2.setCompletionStateBOS(completionStateBOS1);
                    dispatchingConditionBOList.add(dispatchingConditionBO2);

                    outUnitSummBO.setDispatchingConditionBOS(dispatchingConditionBOList);

                    //获取节点和对应数据
                    NodeHeadersCustomDTO nodeHeadersCustomDTO = new NodeHeadersCustomDTO();
                    nodeHeadersCustomDTO.getConditions().add(Restrict.eq("outUnit", out));
//                    nodeHeadersCustomDTO.getConditions().add(Restrict.eq("area", a));
                    List<NodeHeadersCustom> nodeHeadersCustomList = nodeHeadersCustomSer.findByCis(nodeHeadersCustomDTO);
                    List<NodeDataBO> nodeDataBOList = new ArrayList<>();
                    if (nodeHeadersCustomList != null && nodeHeadersCustomList.size() > 0) {
                        for (NodeHeadersCustom nodeHeadersCustom : nodeHeadersCustomList) {
                            NodeDataBO nodeDataBO1 = new NodeDataBO();
                            nodeDataBO1.setNode(nodeHeadersCustom.getNodeOneHeader());
                            nodeDataBOList.add(nodeDataBO1);
                            NodeDataBO nodeDataBO2 = new NodeDataBO();
                            nodeDataBO2.setNode(nodeHeadersCustom.getNodeTwoHeader());
                            nodeDataBOList.add(nodeDataBO2);
                            NodeDataBO nodeDataBO3 = new NodeDataBO();
                            nodeDataBO3.setNode(nodeHeadersCustom.getNodeThreeHeader());
                            nodeDataBOList.add(nodeDataBO3);
                            NodeDataBO nodeDataBO4 = new NodeDataBO();
                            nodeDataBO4.setNode(nodeHeadersCustom.getNodeFourHeader());
                            nodeDataBOList.add(nodeDataBO4);
                        }
                    }

                    String[] filds = new String[]{"estimSettleAmount", "nodeOneNameContent", "fatherId", "nodeOneHeader", "nodeTwoHeader", "nodeThreeHeader", "nodeFourHeader"};
                    String sql9 = "SELECT " +
                            "  sum(a.estimSettleAmount) as estimSettleAmount," +
                            "  b.nodeOneNameContent as nodeOneNameContent," +
                            "  b.fatherId as fatherId," +
                            "  c.nodeOneHeader as nodeOneHeader," +
                            "  c.nodeTwoHeader as nodeTwoHeader," +
                            "  c.nodeThreeHeader as nodeThreeHeader," +
                            "  c.nodeFourHeader as nodeFourHeader" +
                            "  FROM projectprocing_settleprogressmanage a" +
                            "  LEFT JOIN projectprocing_nodeheaderscustom b ON a.id = b.prossManageId" +
                            "  LEFT JOIN" +
                            "  (SELECT" +
                            "     id," +
                            "     nodeOneHeader," +
                            "     nodeTwoHeader," +
                            "     nodeThreeHeader," +
                            "     nodeFourHeader" +
                            "   FROM projectprocing_nodeheaderscustom)" +
                            "  c ON c.id = b.fatherId" +
                            "  WHERE a.outUnit = '" + out + "' and area = '" + a + "'" +
                            "  GROUP BY" +
                            "  b.nodeOneNameContent," +
                            "  b.fatherId," +
                            "  c.nodeOneHeader," +
                            "  c.nodeTwoHeader," +
                            "  c.nodeThreeHeader," +
                            "  c.nodeFourHeader";
                    List<EncapsulationNodeBO> encapsulationNodeBOList = nodeHeadersCustomSer.findBySql(sql9, EncapsulationNodeBO.class, filds);
                    if (encapsulationNodeBOList != null && encapsulationNodeBOList.size() > 0) {
                        for (EncapsulationNodeBO encapsulationNodeBO : encapsulationNodeBOList) {
                            for (NodeDataBO nodeDataBO : nodeDataBOList) {
                                if (encapsulationNodeBO.getNodeOneNameContent() != null) {
                                    switch (encapsulationNodeBO.getNodeOneNameContent()) {
                                        case 1:
                                            if (nodeDataBO.getNode().equals(encapsulationNodeBO.getNodeOneHeader())) {
                                                nodeDataBO.setNodeAmount(encapsulationNodeBO.getEstimSettleAmount());
                                            }
                                            break;
                                        case 2:
                                            if (nodeDataBO.getNode().equals(encapsulationNodeBO.getNodeTwoHeader())) {
                                                nodeDataBO.setNodeAmount(encapsulationNodeBO.getEstimSettleAmount());
                                            }
                                            break;
                                        case 3:
                                            if (nodeDataBO.getNode().equals(encapsulationNodeBO.getNodeThreeHeader())) {
                                                nodeDataBO.setNodeAmount(encapsulationNodeBO.getEstimSettleAmount());
                                            }
                                            break;
                                        case 4:
                                            if (nodeDataBO.getNode().equals(encapsulationNodeBO.getNodeFourHeader())) {
                                                nodeDataBO.setNodeAmount(encapsulationNodeBO.getEstimSettleAmount());
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                        }
                    }
                    outUnitSummBO.setNodeDataBOS(nodeDataBOList);
                    outUnitSummBOList.add(outUnitSummBO);
                }
            }
            settleProgressSummBO.setOutUnitSummBOS(outUnitSummBOList);
            settleProgressSummBOList.add(settleProgressSummBO);
        }
        return settleProgressSummBOList;
    }

    @Override
    public List<String> findInternalProName() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        if (CollectionUtils.isEmpty(settleProgressManageList)) {
            Collections.emptyList();
        }
        return settleProgressManageList.stream().map(SettleProgressManage::getInternalProName).distinct().collect(Collectors.toList());

    }

    @Override
    public List<SettleProgressManageSummBO> settleProgressManageSumm() throws SerException {
        checkPermission();
        List<String> internalProNames = findInternalProName();
        List<SettleProgressManageSummBO> settleProgressManageSummBOList = new ArrayList<>();
        for (String name : internalProNames) {
            StringBuilder sql = new StringBuilder();
            String[] filds = new String[]{"contractTotal", "amountTotal", "completedCount", "completedAmount",
                    "uncompletedCount", "uncompletedAmount", "settleCompletedStart", "settleCompletedStartAmount",
                    "settleCompletedNoStart", "settleCompletedNoStartAmount", "settleUnCompletedStart", "settleUnCompletedStartAmount",
                    "unfinishedSettled", "unfinishedSettledAmount", "returnedItemsNum", "returnedItemsAmount", "noReturnSingular"};
            sql.append("SELECT * FROM ( ");
            sql.append(" (SELECT count(*) as contractTotal  FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "') a, ");
            sql.append(" (SELECT sum(IFNULL(dispatAmount, IFNULL(estimSettleAmount, 0))) as amountTotal FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "') b, ");
            sql.append(" (SELECT count(*) as completedCount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 0) c, ");
            sql.append(" (SELECT IFNULL(sum(payableAmount),0) as completedAmount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 0) d, ");
            sql.append(" (SELECT count(*) as uncompletedCount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 1) e, ");
            sql.append(" (SELECT IFNULL(sum(payableAmount),0) as uncompletedAmount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 1) f, ");
            sql.append(" (SELECT count(*) as settleCompletedStart FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 0 and settleMadeApple = 1) g, ");
            sql.append(" (SELECT IFNULL(sum(estimSettleAmount),0) as settleCompletedStartAmount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 0 and settleMadeApple = 1) h, ");
            sql.append(" (SELECT count(*) as settleCompletedNoStart FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 0 and settleMadeApple = 0) i, ");
            sql.append(" (SELECT IFNULL(sum(estimSettleAmount),0) as settleCompletedNoStartAmount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 0 and settleMadeApple = 0) j, ");
            sql.append(" (SELECT count(*) as settleUnCompletedStart FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 1 and settleMadeApple = 1) k, ");
            sql.append(" (SELECT IFNULL(sum(estimSettleAmount),0) as settleUnCompletedStartAmount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 1 and settleMadeApple = 1) l, ");
            sql.append(" (SELECT count(*) as unfinishedSettled FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 1 and settleMadeApple = 0) m, ");
            sql.append(" (SELECT IFNULL(sum(estimSettleAmount),0) as unfinishedSettledAmount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 1 and settleMadeApple = 0) n, ");
            sql.append(" (SELECT count(*) as returnedItemsNum FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND accountAmount is NOT NULL) o, ");
            sql.append(" (SELECT IFNULL(sum(accountAmount),0) as returnedItemsAmount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "') p, ");
            sql.append(" (SELECT count(*) as noReturnSingular FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND is_allSettleComple = 0) q ");
            sql.append(") ");


            List<SettleProgressManageSummBO> settleProgressManageSummBOS = super.findBySql(sql.toString(), SettleProgressManageSummBO.class, filds);
            SettleProgressManageSummBO settleProgressManageSummBO = settleProgressManageSummBOS.get(0);
            settleProgressManageSummBO.setProject(name);
            settleProgressManageSummBO.setNoReturnSingularAmount(settleProgressManageSummBO.getAmountTotal() - settleProgressManageSummBO.getReturnedItemsAmount());
            settleProgressManageSummBOList.add(settleProgressManageSummBO);
        }
        return settleProgressManageSummBOList;
    }

    @Override
    public OptionBO settleProgressNum() throws SerException {
        List<String> internalProNames = findInternalProName();
        List<Integer> contractTotal_list = new ArrayList<>();
        List<Integer> completedCount_list = new ArrayList<>();
        List<Integer> uncompletedCount_list = new ArrayList<>();
        List<Integer> settleCompletedStart_list = new ArrayList<>();
        List<Integer> settleCompletedNoStart_list = new ArrayList<>();
        List<Integer> settleUnCompletedStart_list = new ArrayList<>();
        List<Integer> unfinishedSettled_list = new ArrayList<>();
        List<Integer> returnedItemsNum_list = new ArrayList<>();
        List<Integer> noReturnSingular_list = new ArrayList<>();

        for (String name : internalProNames) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ( ");
            sql.append(" (SELECT count(*) as contractTotal  FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "') a, ");
            sql.append(" (SELECT count(*) as completedCount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 0) c, ");
            sql.append(" (SELECT count(*) as uncompletedCount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 1) e, ");
            sql.append(" (SELECT count(*) as settleCompletedStart FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 0 and settleMadeApple = 1) g, ");
            sql.append(" (SELECT count(*) as settleCompletedNoStart FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 0 and settleMadeApple = 0) i, ");
            sql.append(" (SELECT count(*) as settleUnCompletedStart FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 1 and settleMadeApple = 1) k, ");
            sql.append(" (SELECT count(*) as unfinishedSettled FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 1 and settleMadeApple = 0) m, ");
            sql.append(" (SELECT count(*) as returnedItemsNum FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND accountAmount is NOT NULL) o, ");
            sql.append(" (SELECT count(*) as noReturnSingular FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND is_allSettleComple = 0) q ");
            sql.append(") ");
            List<Object> objectList = super.findBySql(sql.toString());
            Object[] objects = (Object[]) objectList.get(0);
            contractTotal_list.add(Integer.parseInt(String.valueOf(objects[0])));
            completedCount_list.add(Integer.parseInt(String.valueOf(objects[1])));
            uncompletedCount_list.add(Integer.parseInt(String.valueOf(objects[2])));
            settleCompletedStart_list.add(Integer.parseInt(String.valueOf(objects[3])));
            settleCompletedNoStart_list.add(Integer.parseInt(String.valueOf(objects[4])));
            settleUnCompletedStart_list.add(Integer.parseInt(String.valueOf(objects[5])));
            unfinishedSettled_list.add(Integer.parseInt(String.valueOf(objects[6])));
            returnedItemsNum_list.add(Integer.parseInt(String.valueOf(objects[7])));
            noReturnSingular_list.add(Integer.parseInt(String.valueOf(objects[8])));

        }
        List<SeriesBO> seriesBOList = new ArrayList<>();
        String[] ziduan_str = new String[]{"总合同数量", "已完工数量", "未完工数量", "已完工已启动结算", "已完工未启动结算"
                , "未完工已启动结算", "未完工未结算", "已回款单数", "未回款单数"};


        List<List<Integer>> data_list = new ArrayList<>();
        data_list.add(contractTotal_list);
        data_list.add(completedCount_list);
        data_list.add(uncompletedCount_list);
        data_list.add(settleCompletedStart_list);
        data_list.add(settleCompletedNoStart_list);
        data_list.add(settleUnCompletedStart_list);
        data_list.add(unfinishedSettled_list);
        data_list.add(returnedItemsNum_list);
        data_list.add(noReturnSingular_list);

        for (int i = 0; i < 9; i++) {
            SeriesBO seriesBO = new SeriesBO();
            seriesBO.setName(ziduan_str[i]);
            seriesBO.setType("bar");
            Integer[] str = new Integer[data_list.get(i).size()];
            str = data_list.get(i).toArray(str);
            seriesBO.setData(str);
            seriesBOList.add(seriesBO);
        }

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText("各项目组结算进度情况汇总");

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        legendBO.setData(ziduan_str);

        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        String[] internalName = new String[internalProNames.size()];
        internalName = internalProNames.toArray(internalName);
        xAxisBO.setData(internalName);

        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
//        tooltipBO.setTrigger("axis");

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setTooltip(tooltipBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    @Override
    public OptionAmountBO settleProgressAmount() throws SerException {
        checkPermission();
        List<String> internalProNames = findInternalProName();
        List<Double> amountTotal_list = new ArrayList<>();
        List<Double> completedAmount_list = new ArrayList<>();
        List<Double> uncompletedAmount_list = new ArrayList<>();
        List<Double> settleCompletedStartAmount_list = new ArrayList<>();
        List<Double> settleUnCompletedStartAmount_list = new ArrayList<>();
        List<Double> unfinishedSettledAmount_list = new ArrayList<>();
        List<Double> returnedItemsAmount_list = new ArrayList<>();
        List<Double> noReturnSingularAmount_list = new ArrayList<>();

        for (String name : internalProNames) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ( ");
            sql.append(" (SELECT sum(IFNULL(dispatAmount, IFNULL(estimSettleAmount, 0))) as amountTotal FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "') b, ");
            sql.append(" (SELECT IFNULL(sum(payableAmount),0) as completedAmount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 0) d, ");
            sql.append(" (SELECT IFNULL(sum(payableAmount),0) as uncompletedAmount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 1) f, ");
            sql.append(" (SELECT IFNULL(sum(estimSettleAmount),0) as settleCompletedStartAmount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 0 and settleMadeApple = 1) h, ");
            sql.append(" (SELECT IFNULL(sum(estimSettleAmount),0) as settleUnCompletedStartAmount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 1 and settleMadeApple = 1) l, ");
            sql.append(" (SELECT IFNULL(sum(estimSettleAmount),0) as unfinishedSettledAmount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "' AND actualCompletedState = 1 and settleMadeApple = 0) n, ");
            sql.append(" (SELECT IFNULL(sum(accountAmount),0) as returnedItemsAmount FROM projectprocing_settleprogressmanage WHERE internalProName = '" + name + "') p ");
            sql.append(") ");
            List<Object> objectList = super.findBySql(sql.toString());
            Object[] objects = (Object[]) objectList.get(0);
            amountTotal_list.add(Double.parseDouble(String.valueOf(objects[0])));
            completedAmount_list.add(Double.parseDouble(String.valueOf(objects[1])));
            uncompletedAmount_list.add(Double.parseDouble(String.valueOf(objects[2])));
            settleCompletedStartAmount_list.add(Double.parseDouble(String.valueOf(objects[3])));
            settleUnCompletedStartAmount_list.add(Double.parseDouble(String.valueOf(objects[4])));
            unfinishedSettledAmount_list.add(Double.parseDouble(String.valueOf(objects[5])));
            returnedItemsAmount_list.add(Double.parseDouble(String.valueOf(objects[6])));
            noReturnSingularAmount_list.add(Double.parseDouble(String.valueOf(objects[0])) - Double.parseDouble(String.valueOf(objects[6])));

        }
        List<SeriesAmountBO> seriesAmountBOS = new ArrayList<>();
        String[] ziduan_str = new String[]{"总金额/万", "已完工金额", "未完工金额", "已完工已启动结算金额", "未完工已启动结算金额"
                , "未完工未结算金额", "已回款金额", "未回款金额"};


        List<List<Double>> data_list = new ArrayList<>();
        data_list.add(amountTotal_list);
        data_list.add(completedAmount_list);
        data_list.add(uncompletedAmount_list);
        data_list.add(settleCompletedStartAmount_list);
        data_list.add(settleUnCompletedStartAmount_list);
        data_list.add(unfinishedSettledAmount_list);
        data_list.add(returnedItemsAmount_list);
        data_list.add(noReturnSingularAmount_list);

        for (int i = 0; i < 8; i++) {
            SeriesAmountBO seriesAmountBO = new SeriesAmountBO();
            seriesAmountBO.setName(ziduan_str[i]);
            seriesAmountBO.setType("bar");
            Double[] str = new Double[data_list.get(i).size()];
            str = data_list.get(i).toArray(str);
            seriesAmountBO.setData(str);
            seriesAmountBOS.add(seriesAmountBO);
        }

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText("各项目组结算进度金额情况");

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        legendBO.setData(ziduan_str);

        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        String[] internalName = new String[internalProNames.size()];
        internalName = internalProNames.toArray(internalName);
        xAxisBO.setData(internalName);

        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
//        tooltipBO.setTrigger("axis");

        SeriesAmountBO[] text_4 = new SeriesAmountBO[seriesAmountBOS.size()];
        text_4 = seriesAmountBOS.toArray(text_4);
        OptionAmountBO optionAmountBO = new OptionAmountBO();
        optionAmountBO.setTitle(titleBO);
        optionAmountBO.setTooltip(tooltipBO);
        optionAmountBO.setLegend(legendBO);
        optionAmountBO.setxAxis(xAxisBO);
        optionAmountBO.setyAxis(yAxisBO);

        optionAmountBO.setSeries(text_4);
        return optionAmountBO;
    }
}