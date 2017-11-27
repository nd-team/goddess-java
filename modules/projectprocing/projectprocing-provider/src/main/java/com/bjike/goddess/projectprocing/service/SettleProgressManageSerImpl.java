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
import com.bjike.goddess.projectprocing.bo.AllotmentNodeDataBO;
import com.bjike.goddess.projectprocing.bo.NodeHeadersCustomBO;
import com.bjike.goddess.projectprocing.bo.ScreeningSettleProgressManageBO;
import com.bjike.goddess.projectprocing.bo.SettleProgressManageBO;
import com.bjike.goddess.projectprocing.dto.NodeHeadersCustomDTO;
import com.bjike.goddess.projectprocing.dto.SettleProgressManageDTO;
import com.bjike.goddess.projectprocing.entity.HeadersCustom;
import com.bjike.goddess.projectprocing.entity.NodeHeadersCustom;
import com.bjike.goddess.projectprocing.entity.SettleProgressManage;
import com.bjike.goddess.projectprocing.entity.SettleProgressRecord;
import com.bjike.goddess.projectprocing.to.HeadersCustomTO;
import com.bjike.goddess.projectprocing.to.NodeHeadersCustomTO;
import com.bjike.goddess.projectprocing.to.ScheduleDelayDataTO;
import com.bjike.goddess.projectprocing.to.SettleProgressManageTO;
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

import javax.persistence.Column;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private UserAPI userAPI;
    @Autowired
    private SettleProgressRecordSer settleProgressRecordSer;
    @Autowired
    private PositionUserDetailAPI positionUserDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

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
        SettleProgressManage settleProgressManage = BeanTransform.copyProperties(settleProgressManageTO, SettleProgressManage.class, true);
        settleProgressManage.setCreateTime(LocalDateTime.now());
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
        SettleProgressManage settleProgressManage = super.findById(settleProgressManageTO.getId());
        LocalDateTime dateTime = settleProgressManage.getCreateTime();
        settleProgressManage = BeanTransform.copyProperties(settleProgressManageTO, SettleProgressManage.class, true);
        settleProgressManage.setCreateTime(dateTime);
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
                Column c = field.getAnnotation(Column.class);
                fields.add(c.name());
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
        int nodeStartSize = titles.size(); //

        NodeHeadersCustomDTO ncDto = new NodeHeadersCustomDTO();
        ncDto.getConditions().add(Restrict.eq("outUnit", "外包单位1"));
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
        if (settleProgressManages.size() > 0) {
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

    @Transactional
    @Override
    public void importExcel(List<InputStream> inputStreams, String outUnit) throws SerException {
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
            ncDto.getConditions().add(Restrict.eq("outUnit", "外包单位1"));
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
            int rowCount = sheet.getLastRowNum();
            List<String> str_titles = new ArrayList<>();
            List<Field> fields = ClazzUtils.getFields(SettleProgressManage.class);
            for (Field field : fields) {
                Column c = field.getAnnotation(Column.class);
                if (StringUtils.isNotBlank(c.name()) && !c.name().equals("id"))
                    str_titles.add(c.name());
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
                                    ExcelUtil.enumToField(field, spm, cellVal);
                                } else {
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
                    for(NodeHeadersCustom ncm:ncs){
                        NodeHeadersCustom nhc = new NodeHeadersCustom();
                        nhc.setNodeFourInterDate(1);
                        nhc.setNodeTwoInterDate(1);
                        nhc.setNodeThreeInterDate(1);
                        nhc.setFatherId(ncm.getId());
                        nhc.setProssManageId(ncm.getProssManageId());
                        nhc.setOutUnit(outUnit);
                        for (int z = nodeSize; nodeSize < titles.size(); z++) {
                            String head =titles.get(z);
                            String val =null;
                            try {
                                val = ExcelUtil.getCellValue(xr.getCell(z),null);//值
                            }catch (Exception e){
                                throw  new SerException(e.getMessage());
                            }

                            if(ncm.getNodeOneName().equals(head)){
                                nhc.setNodeOneName(head);
                                nhc.setNodeOneNameContent(val==null?0:Integer.parseInt(val));
                            }else if(ncm.getNodeOneHeader().equals(head)){
                                nhc.setNodeOneHeader(head);
                                nhc.setNodeOneContent(val == null ? LocalDate.now() : DateUtil.parseDate(val));
                            }
                            else if(ncm.getNodeTwoHeader().equals(head)){
                                nhc.setNodeTwoHeader(head);
                                nhc.setNodeTwoContent(val == null ? LocalDate.now() : DateUtil.parseDate(val));
                            }
                            else if(ncm.getNodeThreeHeader().equals(head)){
                                nhc.setNodeThreeHeader(head);
                                nhc.setNodeThreeContent(val == null ? LocalDate.now() : DateUtil.parseDate(val));
                            }
                            else if(ncm.getNodeFourHeader().equals(head)){
                                nhc.setNodeFourHeader(head);
                                nhc.setNodeFourContent(val == null ? LocalDate.now() : DateUtil.parseDate(val));
                            }
                            break;
                        }
                        nodeHeadersCustomSer.save(nhc);
                    }

                }

            }

        } else {
            throw new SerException("没解析到任何上传文件");
        }
    }

    @Override
    public void scheduleDelay(ScheduleDelayDataTO scheduleDelayDataTO) throws SerException {
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
        settleProgressRecord.setDispatchingName(settleProgressManage.getDispatchingItems());
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


}