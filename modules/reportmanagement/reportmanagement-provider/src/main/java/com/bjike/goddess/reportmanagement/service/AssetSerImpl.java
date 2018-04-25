package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.api.AccountanCourseAPI;
import com.bjike.goddess.financeinit.api.CompanyBasicInfoAPI;
import com.bjike.goddess.financeinit.bo.AccountAddDateBO;
import com.bjike.goddess.reportmanagement.bo.*;
import com.bjike.goddess.reportmanagement.dto.*;
import com.bjike.goddess.reportmanagement.entity.Asset;
import com.bjike.goddess.reportmanagement.entity.AssetData;
import com.bjike.goddess.reportmanagement.entity.ProfitData;
import com.bjike.goddess.reportmanagement.enums.AssetType;
import com.bjike.goddess.reportmanagement.enums.Form;
import com.bjike.goddess.reportmanagement.enums.GuideAddrStatus;
import com.bjike.goddess.reportmanagement.enums.Type;
import com.bjike.goddess.reportmanagement.excel.AssetAndDebtExportExcel;
import com.bjike.goddess.reportmanagement.to.AssetTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.utils.Static;
import com.bjike.goddess.reportmanagement.vo.SonPermissionObject;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 资产表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:19 ]
 * @Description: [ 资产表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class AssetSerImpl extends ServiceImpl<Asset, AssetDTO> implements AssetSer {
    @Autowired
    private FormulaSer formulaSer;
    @Autowired
    private AssetStructureAdviceSer assetStructureAdviceSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private DebtSer debtSer;
    @Autowired
    private DebtStructureAdviceSer debtStructureAdviceSer;
    @Autowired
    private ProfitIndicatorAdviceSer profitIndicatorAdviceSer;
    @Autowired
    private ProfitRegulationAdviceSer profitRegulationAdviceSer;
    @Autowired
    private ProfitSer profitSer;
    @Autowired
    private RepayAnalyzeAdviceSer repayAnalyzeAdviceSer;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private AccountanCourseAPI accountanCourseAPI;
    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;
    @Autowired
    private CashFlowSer cashFlowSer;
    @Autowired
    private CompanyBasicInfoAPI companyBasicInfoAPI;
    @Autowired
    private AssetDataSer assetDataSer;


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
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("asset");
        obj.setDescribesion("资产表");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = debtSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("debt");
        obj.setDescribesion("负债表");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis1 = assetStructureAdviceSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("assetstructureadvice");
        obj.setDescribesion("资产结构管理建议设计");
        if (flagSeeDis1) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis2 = debtStructureAdviceSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("debtstructureadvice");
        obj.setDescribesion("负债与权益结构管理建议设计");
        if (flagSeeDis2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis3 = formulaSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("formula");
        obj.setDescribesion("对应的公式");
        if (flagSeeDis3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis4 = profitSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("profit");
        obj.setDescribesion("利润表");
        if (flagSeeDis4) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis5 = profitIndicatorAdviceSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("profitindicatoradvice");
        obj.setDescribesion("利润分析指标管理建议设计");
        if (flagSeeDis5) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis6 = profitRegulationAdviceSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("profitregulationadvice");
        obj.setDescribesion("利润增减率分析管理建议设计");
        if (flagSeeDis6) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis7 = profitRegulationAdviceSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("repayanalyzeadvice");
        obj.setDescribesion("偿还能力分析管理建议设计");
        if (flagSeeDis7) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis8 = cashFlowSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("cashflow");
        obj.setDescribesion("现金流量表");
        if (flagSeeDis8) {
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
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
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
    @Transactional(rollbackFor = SerException.class)
    public AssetBO save(AssetTO to) throws SerException {
        checkAddIdentity();
        Asset entity = BeanTransform.copyProperties(to, Asset.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, AssetBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void edit(AssetTO to) throws SerException {
        checkAddIdentity();
        Asset entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, Asset.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        checkAddIdentity();
        Asset entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }
    //资产数据列表
    @Override
    public List<AssetBO> list(AssetDTO dto) throws SerException {
        checkSeeIdentity();
        List<AssetBO> boList = new ArrayList<AssetBO>();
        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            dto.setStartTime(DateUtil.dateToString(DateUtil.getStartMonth()));
            dto.setEndTime(DateUtil.dateToString(DateUtil.getEndMonth()));
        }

        if (!dto.isLastest()) {
            //判断是否已有存储
            AssetDTO profitDTO = new AssetDTO();
            profitDTO.getConditions().add(Restrict.eq("startTime", dto.getStartTime()));
            profitDTO.getConditions().add(Restrict.eq("endTime", dto.getEndTime()));
            List<AssetData> cashFlows = assetDataSer.findByCis(profitDTO);
            if (null != cashFlows && cashFlows.size() > 0) {
                for (AssetData data : cashFlows) {
                    AssetBO bo = new AssetBO();
                    bo.setEndAsset(data.getEndAsset().doubleValue());
                    bo.setBeginAsset(data.getBeginAsset().doubleValue());
                    bo.setAsset(data.getProject());
                    bo.setAssetNum(data.getNum());
                    bo.setCurrent(null);
                    bo.setAssetType(data.getAssetType());
                    bo.setType(data.getType());
                    bo.setStartTime(data.getStartTime().toString());
                    bo.setEndTime(data.getEndTime().toString());
                    bo.setId(data.getProjectId());
                    boList.add(bo);
                }
                return convertAsset(boList);
            }
        }


        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(dto, formulaDTO);
        dto.getSorts().add("assetType=ASC");
        dto.getSorts().add("createTime=asc");
        List<Asset> list = super.findByCis(dto);
//        List<AssetBO> boList = new ArrayList<AssetBO>();
        boolean b1 = true;
        boolean b2 = true;
        boolean b3 = true;
        boolean b4 = true;
        boolean b5 = true;
        double beginSum = 0;
        double currentSum = 0;
        double endSum = 0;
        double countBegin = 0;
        double countCurrent = 0;
        double countEnd = 0;       //总资产
        int num = 1;

        Double beginSum1 = 0.0;
        Double beginSum2 = 0.0;
        Double beginSum3 = 0.0;
        Double beginSum4 = 0.0;
        Double beginSum5 = 0.0;
        Double endSum1 = 0.0;
        Double endSum2 = 0.0;
        Double endSum3 = 0.0;
        Double endSum4 = 0.0;
        Double endSum5 = 0.0;
        String token = RpcTransmit.getUserToken();
        for (Asset asset : list) {
            RpcTransmit.transmitUserToken(token);
            List<FormulaBO> formulaBOs = formulaSer.findByFid(asset.getId(), formulaDTO);
            if ((formulaBOs == null) || (formulaBOs.isEmpty())) {
                continue;
            }
            FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
            AssetBO bo = BeanTransform.copyProperties(asset, AssetBO.class);
            bo.setBeginAsset(formulaBO.getBegin());
            bo.setCurrent(formulaBO.getCurrent());
            bo.setEndAsset(formulaBO.getEnd());
            bo.setAssetNum(num);
            bo.setAsset(asset.getAsset());
            boList.add(bo);
            num++;
        }
        for (AssetBO bo : boList) {
            /*List<FormulaBO> formulaBOs = formulaSer.findByFid(asset.getId(), formulaDTO);
            if ((formulaBOs != null) && (!formulaBOs.isEmpty())) {
                continue;
            }
            FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
            AssetBO bo = BeanTransform.copyProperties(asset, AssetBO.class);
            bo.setBeginAsset(formulaBO.getBegin());
            bo.setCurrent(formulaBO.getCurrent());
            bo.setEndAsset(formulaBO.getEnd());
            bo.setAssetNum(num);
            num++;Can not lock the registry cache file
            boList.add(bo);*/
            if (Type.ADD.equals(bo.getType())) {
                if (AssetType.AFLOW.equals(bo.getAssetType())) {
                    beginSum1 += bo.getBeginAsset();
                    endSum1 += bo.getEndAsset();
                }
                if (AssetType.BLONG.equals(bo.getAssetType())) {
                    beginSum2 += bo.getBeginAsset();
                    endSum2 += bo.getEndAsset();
                }
                if (AssetType.CFIX.equals(bo.getAssetType())) {
                    if ("固定资产净额".equals(bo.getAsset()) || "在建工程".equals(bo.getAsset()) ||
                            "工程物资".equals(bo.getAsset()) || "固定资产清理".equals(bo.getAsset())) {
                        beginSum3 += bo.getBeginAsset();
                        endSum3 += bo.getEndAsset();
                    }

                }
                if (AssetType.DINVISIBLE.equals(bo.getAssetType())) {
                    beginSum4 += bo.getBeginAsset();
                    endSum4 += bo.getEndAsset();
                }
                if (AssetType.ETAX.equals(bo.getAssetType())) {
                    beginSum5 += bo.getBeginAsset();
                    endSum5 += bo.getEndAsset();
                }
            } else if (Type.REMOVE.equals(bo.getType())) {
                if (AssetType.AFLOW.equals(bo.getAssetType())) {
                    beginSum1 -= bo.getBeginAsset();
                    endSum1 += bo.getEndAsset();
                }
                if (AssetType.BLONG.equals(bo.getAssetType())) {
                    beginSum2 -= bo.getBeginAsset();
                    endSum2 += bo.getEndAsset();
                }
                if (AssetType.CFIX.equals(bo.getAssetType())) {
                    if ("固定资产净额".equals(bo.getAsset()) || "在建工程".equals(bo.getAsset()) ||
                            "工程物资".equals(bo.getAsset()) || "固定资产清理".equals(bo.getAsset())) {
                        beginSum3 -= bo.getBeginAsset();
                        endSum3 += bo.getEndAsset();
                    }

                }
                if (AssetType.DINVISIBLE.equals(bo.getAssetType())) {
                    beginSum4 -= bo.getBeginAsset();
                    endSum4 += bo.getEndAsset();
                }
                if (AssetType.ETAX.equals(bo.getAssetType())) {
                    beginSum5 -= bo.getBeginAsset();
                    endSum5 += bo.getEndAsset();
                }
            }

        }
        AssetBO assetBO1 = new AssetBO();
        assetBO1.setAsset("流动资产：");
        boList.add(assetBO1);
        AssetBO assetBO2 = new AssetBO();
        assetBO2.setAsset("流动资产合计");
        assetBO2.setBeginAsset(beginSum1);
        assetBO2.setEndAsset(endSum1);
        assetBO2.setAssetNum(num);
        boList.add(assetBO2);
        num++;
        AssetBO assetBO3 = new AssetBO();
        assetBO3.setAsset("长期资产：");
        boList.add(assetBO3);
        AssetBO assetBO4 = new AssetBO();
        assetBO4.setAsset("长期投资合计");
        assetBO4.setBeginAsset(beginSum2);
        assetBO4.setEndAsset(endSum2);
        assetBO4.setAssetNum(num);
        boList.add(assetBO4);
        num++;
        AssetBO assetBO5 = new AssetBO();
        assetBO5.setAsset("固定资产：");
        boList.add(assetBO5);
        AssetBO assetBO6 = new AssetBO();
        assetBO6.setAsset("固定资产合计");
        assetBO6.setBeginAsset(beginSum3);
        assetBO6.setEndAsset(endSum3);
        assetBO6.setAssetNum(num);
        boList.add(assetBO6);
        num++;
        AssetBO assetBO7 = new AssetBO();
        assetBO7.setAsset("无形资产及其他资产：");
        boList.add(assetBO7);
        AssetBO assetBO8 = new AssetBO();
        assetBO8.setAsset("无形资产及其他资产合计");
        assetBO8.setBeginAsset(beginSum4);
        assetBO8.setEndAsset(endSum4);
        assetBO8.setAssetNum(num);
        boList.add(assetBO8);
        num++;
        AssetBO assetBO9 = new AssetBO();
        assetBO9.setAsset("递延税款：");
        boList.add(assetBO9);
        AssetBO assetBO10 = new AssetBO();
        assetBO10.setAsset("资产总计");
        assetBO10.setBeginAsset(beginSum1 + beginSum2 + beginSum3 + beginSum4 + beginSum5);
        assetBO10.setEndAsset(endSum1 + endSum2 + endSum3 + endSum4 + endSum5);
        assetBO10.setAssetNum(num);
        boList.add(assetBO10);

        //存储数据
        List<AssetData> profitDataList = new ArrayList<>();
        for (AssetBO bo : boList) {
            AssetData data = new AssetData();
            data.setType(bo.getType());
            data.setProject(bo.getAsset());
            data.setNum(bo.getAssetNum());
            data.setEndAsset(bo.getEndAsset() == null ? new BigDecimal(0.0) : new BigDecimal(bo.getEndAsset()));
            data.setBeginAsset(bo.getBeginAsset() == null ? new BigDecimal(0.0) : new BigDecimal(bo.getBeginAsset()));
            data.setAssetType(bo.getAssetType());
            data.setStartTime(LocalDate.parse(dto.getStartTime()));
            data.setEndTime(LocalDate.parse(dto.getEndTime()));
            data.setProjectId(bo.getId());
            data.setSystemId(null);
            profitDataList.add(data);
        }
        new Thread(new saveAssetDataThread(profitDataList)).start();

        return this.convertAsset(boList);
    }

    /**
     * 存储利润表的查询结果
     */
    public class saveAssetDataThread implements Runnable {

        private List<AssetData> list;

        public saveAssetDataThread(List<AssetData> list) {
            this.list = list;
        }

        @Override
        public void run() {
            try {
                assetDataSer.save(list);
            } catch (SerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对返回结果进行排序
     *
     * @param list
     * @return
     */
    List<AssetBO> convertAsset(List<AssetBO> list) {
        List<AssetBO> bos = new ArrayList<>();
        AssetBO bo1 = new AssetBO();
        AssetBO bo2 = new AssetBO();
        AssetBO bo3 = new AssetBO();
        AssetBO bo4 = new AssetBO();
        AssetBO bo5 = new AssetBO();
        AssetBO bo6 = new AssetBO();
        AssetBO bo7 = new AssetBO();
        AssetBO bo8 = new AssetBO();
        AssetBO bo9 = new AssetBO();
        AssetBO bo10 = new AssetBO();
        AssetBO bo11 = new AssetBO();
        AssetBO bo12 = new AssetBO();
        AssetBO bo13 = new AssetBO();
        AssetBO bo14 = new AssetBO();
        AssetBO bo15 = new AssetBO();
        AssetBO bo16 = new AssetBO();
        AssetBO bo17 = new AssetBO();
        AssetBO bo18 = new AssetBO();
        AssetBO bo19 = new AssetBO();
        AssetBO bo20 = new AssetBO();
        AssetBO bo21 = new AssetBO();
        AssetBO bo22 = new AssetBO();
        AssetBO bo23 = new AssetBO();
        AssetBO bo24 = new AssetBO();
        AssetBO bo25 = new AssetBO();
        AssetBO bo26 = new AssetBO();
        AssetBO bo27 = new AssetBO();
        AssetBO bo28 = new AssetBO();
        AssetBO bo29 = new AssetBO();
        AssetBO bo30 = new AssetBO();
        AssetBO bo31 = new AssetBO();
        AssetBO bo32 = new AssetBO();
        AssetBO bo33 = new AssetBO();
        AssetBO bo34 = new AssetBO();
        AssetBO bo35 = new AssetBO();
        AssetBO bo36 = new AssetBO();
        AssetBO bo37 = new AssetBO();
        AssetBO bo38 = new AssetBO();
        for (AssetBO bo : list) {
            if (null != bo.getBeginAsset()) {
                bo.setBeginAsset(new BigDecimal(bo.getBeginAsset()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            if (null != bo.getEndAsset()) {
                bo.setEndAsset(new BigDecimal(bo.getEndAsset()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            if ("流动资产：".equals(bo.getAsset()) || "流动资产:".equals(bo.getAsset())) {
                bo1 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());

            }
            if ("货币资金".equals(bo.getAsset())) {
                bo2 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("短期投资".equals(bo.getAsset())) {
                bo3 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("应收票据".equals(bo.getAsset())) {
                bo4 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("应收股利".equals(bo.getAsset())) {
                bo5 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("应收利息".equals(bo.getAsset())) {
                bo6 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("应收账款".equals(bo.getAsset())) {
                bo7 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("其他应收款".equals(bo.getAsset())) {
                bo8 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("预付账款".equals(bo.getAsset())) {
                bo9 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("应收补贴款".equals(bo.getAsset())) {
                bo10 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("存货".equals(bo.getAsset())) {
                bo11 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("待摊费用".equals(bo.getAsset())) {
                bo12 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("一年内到期的长期债券投资".equals(bo.getAsset())) {
                bo13 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("其他流动资产".equals(bo.getAsset())) {
                bo14 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("流动资产合计".equals(bo.getAsset())) {
                bo15 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("长期资产：".equals(bo.getAsset()) || "长期资产:".equals(bo.getAsset())) {
                bo16 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("长期股权投资".equals(bo.getAsset())) {
                bo17 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("长期债券投资".equals(bo.getAsset())) {
                bo18 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("长期投资合计".equals(bo.getAsset())) {
                bo19 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("固定资产：".equals(bo.getAsset()) || "固定资产:".equals(bo.getAsset())) {
                bo20 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("固定资产原价".equals(bo.getAsset())) {
                bo21 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("减：累计折旧".equals(bo.getAsset()) || "减:累计折旧".equals(bo.getAsset())) {
                bo22 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("固定资产净值".equals(bo.getAsset())) {
                bo23 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("减：固定资产减值准备".equals(bo.getAsset()) || "减:固定资产减值准备".equals(bo.getAsset())) {
                bo24 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("固定资产净额".equals(bo.getAsset()) || "固定资产净额:".equals(bo.getAsset())) {
                bo25 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("工程物资".equals(bo.getAsset())) {
                bo26 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("在建工程".equals(bo.getAsset())) {
                bo27 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("固定资产清理".equals(bo.getAsset())) {
                bo28 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("固定资产合计".equals(bo.getAsset())) {
                bo29 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("无形资产及其他资产：".equals(bo.getAsset()) || "无形资产及其他资产:".equals(bo.getAsset())) {
                bo30 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("无形资产".equals(bo.getAsset())) {
                bo31 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("长期待摊费用".equals(bo.getAsset())) {
                bo32 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("其他长期资产".equals(bo.getAsset())) {
                bo33 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("无形资产及其他资产合计".equals(bo.getAsset())) {
                bo34 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
                bo35 = new AssetBO();

            }
            if ("递延税款：".equals(bo.getAsset()) || "递延税款:".equals(bo.getAsset())) {
                bo36 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("递延税款借项".equals(bo.getAsset())) {
                bo37 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
            if ("资产总计".equals(bo.getAsset())) {
                bo38 = new AssetBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getAsset(), bo.getAssetType(), bo.getType(), bo.getAssetNum(), bo.getBeginAsset(), bo.getCurrent(), bo.getEndAsset());
            }
        }
        bos.add(bo1);
        bos.add(bo2);
        bos.add(bo3);
        bos.add(bo4);
        bos.add(bo5);
        bos.add(bo6);
        bos.add(bo7);
        bos.add(bo8);
        bos.add(bo9);
        bos.add(bo10);
        bos.add(bo11);
        bos.add(bo12);
        bos.add(bo13);
        bos.add(bo14);
        bos.add(bo15);
        bos.add(bo16);
        bos.add(bo17);
        bos.add(bo18);
        bos.add(bo19);
        bos.add(bo20);
        bos.add(bo21);
        bos.add(bo22);
        bos.add(bo23);
        bos.add(bo24);
        bos.add(bo25);
        bos.add(bo26);
        bos.add(bo27);
        bos.add(bo28);
        bos.add(bo29);
        bos.add(bo30);
        bos.add(bo31);
        bos.add(bo32);
        bos.add(bo33);
        bos.add(bo34);
        bos.add(bo35);
        bos.add(bo36);
        bos.add(bo37);
        bos.add(bo38);

        return bos;
    }



    private String getTypeData(AssetType type) throws SerException {
        String str = "";
        switch (type) {
            case AFLOW:
                str = "流动资产";
                break;
            case BLONG:
                str = "长期资产";
                break;
            case CFIX:
                str = "固定资产";
                break;
            case DINVISIBLE:
                str = "无形资产及其他资产";
                break;
            case ETAX:
                str = "递延税款";
                break;
            default:
                ;
                break;
        }
        return str;
    }

    //    @Override
    public List<AssetBO> listtest1(AssetDTO dto) throws SerException {
        checkSeeIdentity();
        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(dto, formulaDTO);
        dto.getSorts().add("assetType=ASC");
        dto.getSorts().add("createTime=ASC");
        List<Asset> list = super.findAll();
        List<AssetBO> boList = new ArrayList<AssetBO>();
        boolean b1 = true;
        boolean b2 = true;
        boolean b3 = true;
        boolean b4 = true;
        boolean b5 = true;
        double beginSum = 0;
        double currentSum = 0;
        double endSum = 0;
        double countBegin = 0;
        double countCurrent = 0;
        double countEnd = 0;       //总资产
        int num = 1;
        for (Asset asset : list) {
            List<FormulaBO> formulaBOs = formulaSer.findByFid(asset.getId(), formulaDTO);
            if ((formulaBOs != null) && (!formulaBOs.isEmpty())) {
                if (AssetType.AFLOW.equals(asset.getAssetType()) && b1) {
                    AssetBO assetBO = new AssetBO();
                    assetBO.setAsset("流动资产：");
                    boList.add(assetBO);
                    b1 = false;
                } else if (AssetType.BLONG.equals(asset.getAssetType()) && b2) {
                    AssetBO sumBO = new AssetBO();
                    sumBO.setAsset("流动资产合计");
                    sumBO.setBeginAsset(beginSum);
                    sumBO.setCurrent(currentSum);
                    sumBO.setEndAsset(endSum);
                    sumBO.setAssetNum(num);
                    num++;
                    boList.add(sumBO);
                    beginSum = 0;
                    currentSum = 0;
                    endSum = 0;    //置为0
                    AssetBO assetBO = new AssetBO();
                    assetBO.setAsset("长期资产：");
                    boList.add(assetBO);
                    b2 = false;
                } else if (AssetType.CFIX.equals(asset.getAssetType()) && b3) {
                    AssetBO sumBO = new AssetBO();
                    sumBO.setAsset("长期资产合计");
                    sumBO.setBeginAsset(beginSum);
                    sumBO.setCurrent(currentSum);
                    sumBO.setEndAsset(endSum);
                    sumBO.setAssetNum(num);
                    num++;
                    boList.add(sumBO);
                    beginSum = 0;
                    currentSum = 0;
                    endSum = 0;    //置为0
                    AssetBO assetBO = new AssetBO();
                    assetBO.setAsset("固定资产：");
                    boList.add(assetBO);
                    b3 = false;
                } else if (AssetType.DINVISIBLE.equals(asset.getAssetType()) && b4) {
                    AssetBO sumBO = new AssetBO();
                    sumBO.setAsset("固定资产合计");
                    sumBO.setBeginAsset(beginSum);
                    sumBO.setCurrent(currentSum);
                    sumBO.setEndAsset(endSum);
                    sumBO.setAssetNum(num);
                    num++;
                    boList.add(sumBO);
                    beginSum = 0;
                    currentSum = 0;
                    endSum = 0;    //置为0
                    AssetBO assetBO = new AssetBO();
                    assetBO.setAsset("无形资产及其他资产：");
                    boList.add(assetBO);
                    b4 = false;
                } else if (AssetType.ETAX.equals(asset.getAssetType()) && b5) {
                    AssetBO sumBO = new AssetBO();
                    sumBO.setAsset("无形资产及其他资产合计");
                    sumBO.setBeginAsset(beginSum);
                    sumBO.setCurrent(currentSum);
                    sumBO.setEndAsset(endSum);
                    sumBO.setAssetNum(num);
                    num++;
                    boList.add(sumBO);
                    beginSum = 0;
                    currentSum = 0;
                    endSum = 0;    //置为0
                    AssetBO assetBO = new AssetBO();
                    assetBO.setAsset("递延税款：");
                    boList.add(assetBO);
                    b5 = false;
                }
                FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
                AssetBO bo = BeanTransform.copyProperties(asset, AssetBO.class);
                bo.setBeginAsset(formulaBO.getBegin());
                bo.setCurrent(formulaBO.getCurrent());
                bo.setEndAsset(formulaBO.getEnd());
                if (Type.ADD.equals(asset.getType())) {
                    beginSum += bo.getBeginAsset();
                    currentSum += bo.getCurrent();
                    endSum += bo.getEndAsset();
                    countBegin += bo.getBeginAsset();
                    countCurrent += bo.getCurrent();
                    countEnd += bo.getEndAsset();
                } else if (Type.REMOVE.equals(asset.getType())) {
                    bo.setAsset("减：" + asset.getAsset());
                    beginSum = beginSum - bo.getBeginAsset();
                    currentSum -= bo.getCurrent();
                    endSum = endSum - bo.getEndAsset();
                    countBegin = countBegin - bo.getBeginAsset();
                    countCurrent -= bo.getCurrent();
                    countEnd = countEnd - bo.getEndAsset();
                }
                bo.setAssetNum(num);
                num++;
                boList.add(bo);
            } else {
                AssetBO bo = BeanTransform.copyProperties(asset, AssetBO.class);
                bo.setAssetNum(num);
                num++;
                boList.add(bo);
            }
        }
        AssetBO lastBO = new AssetBO();
        lastBO.setAsset("资产总计");
        lastBO.setBeginAsset(countBegin);
        lastBO.setCurrent(countCurrent);
        lastBO.setEndAsset(countEnd);
        lastBO.setAssetNum(num);
        num++;
        Static.setNum(num);
        boList.add(lastBO);
        return boList;
    }

    @Override
    public List<StructureBO> assetStructure(AssetDTO dto) throws SerException {
        checkSeeIdentity();
        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            dto.setStartTime(DateUtil.dateToString(DateUtil.getStartMonth()));
            dto.setEndTime(DateUtil.dateToString(DateUtil.getEndMonth()));
        }
        String userToken = RpcTransmit.getUserToken();
        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(dto, formulaDTO);
        dto.getSorts().add("assetType=ASC");
        dto.getSorts().add("createTime=asc");
//        List<Asset> list = super.findAll();
        List<Asset> list = super.findByCis(dto);
        List<StructureBO> boList = new ArrayList<StructureBO>();
        boolean b = true;
        double flowSum = 0;
        double currentSum = 0;
        double countCurrent = 0;       //总资产
        for (Asset asset : list) {
            List<FormulaBO> formulaBOs = formulaSer.findByFid(asset.getId(), formulaDTO);
            if ((formulaBOs != null) && (!formulaBOs.isEmpty())) {
                if (AssetType.BLONG.equals(asset.getAssetType()) && b) {
                    flowSum = currentSum;
                    b = false;
                }
                FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
                AssetBO bo = BeanTransform.copyProperties(asset, AssetBO.class);
                bo.setCurrent(formulaBO.getCurrent());
                if (Type.ADD.equals(asset.getType())) {
                    currentSum += bo.getCurrent();
                    countCurrent += bo.getCurrent();
                } else if (Type.REMOVE.equals(asset.getType())) {
                    currentSum -= bo.getCurrent();
                    countCurrent -= bo.getCurrent();
                }
            }
        }
        StructureBO flowBO = new StructureBO();
        flowBO.setProject("流动资产合计");
        flowBO.setFee(flowSum);
        String flow = "0";
        if (0d != countCurrent) {
            flow = String.format("%.2f", (flowSum / countCurrent) * 100);
        }
        flowBO.setScale(flow + "%");
        boList.add(flowBO);
        double otherSum = countCurrent - flowSum;
        StructureBO otherBO = new StructureBO();
        otherBO.setProject("非流动资产合计");
        otherBO.setFee(otherSum);
        String other = "0";
        if (0d != countCurrent) {
            other = String.format("%.2f", (otherSum / countCurrent) * 100);
        }
        otherBO.setScale(other + "%");
        boList.add(otherBO);
        StructureBO sumBO = new StructureBO();
        sumBO.setProject("资产总计");
        sumBO.setFee(countCurrent);
        sumBO.setScale("100%");
        boList.add(sumBO);
        StructureBO rate = new StructureBO();
        rate.setProject("比例说明");
        rate.setFee(-1.00);
        rate.setScale("随便");
        rate.setBestScale("流动资产比重较高会占用大量资金，降低流动资产周转率，从而影响企业的资金利用效率。" +
                "非流动资产比例过低会影响企业的获利能力，从而影响企业未来的发展。");
        boList.add(rate);
        RpcTransmit.transmitUserToken(userToken);
        String advice = assetStructureAdvice(flow, other);
        StructureBO adviceBO = new StructureBO();
        adviceBO.setProject("管理建议");
        adviceBO.setFee(-1.00);
        adviceBO.setScale("随便");
        adviceBO.setBestScale(advice);
        boList.add(adviceBO);
        return boList;
    }

    /**
     * 获取资产结构管理建议
     *
     * @param flow
     * @param other
     * @return
     * @throws SerException
     */
    private String assetStructureAdvice(String flow, String other) throws SerException {
        List<AssetStructureAdviceBO> advices = assetStructureAdviceSer.list(new AssetStructureAdviceDTO());
        String advice = null;
        if (advices != null && !advices.isEmpty()) {
            for (AssetStructureAdviceBO r : advices) {
                boolean b1 = Double.parseDouble(flow) >= r.getFlowMin() && Double.parseDouble(flow) <= r.getFlowMax();
                boolean b2 = Double.parseDouble(other) >= r.getNotFlowMin() && Double.parseDouble(other) <= r.getNotFlowMax();
                if (b1 && b2) {
                    advice = r.getAdvice();
                }
            }
        }
        return advice;
    }

    @Override
    public List<RepayAnalyzeBO> repayAnalyze(AssetDTO dto) throws SerException {
//        checkSeeIdentity();
        String userToken = RpcTransmit.getUserToken();
        double flowAsset = assetStructure(dto).get(0).getFee();
        RpcTransmit.transmitUserToken(userToken);
        double flowDebt = finds(dto).get(0);
        RpcTransmit.transmitUserToken(userToken);
        double asset = assetStructure(dto).get(2).getFee();
        RpcTransmit.transmitUserToken(userToken);
        double debt = finds(dto).get(1);
        RpcTransmit.transmitUserToken(userToken);
        double all = finds(dto).get(2);
        RpcTransmit.transmitUserToken(userToken);
        double fund = 0;
        double stock = 0;
        for (AssetBO bo : list(dto)) {
            if ("货币资金".equals(bo.getAsset())) {
                fund = bo.getCurrent();   //货币资金
            }
            if ("存货".equals(bo.getAsset())) {
                stock = bo.getCurrent();   //存货净额
            }
        }
        RpcTransmit.transmitUserToken(userToken);
        List<RepayAnalyzeBO> list = new ArrayList<>();
        RepayAnalyzeBO firstBO = new RepayAnalyzeBO();
        firstBO.setProject("一、短期偿债能力分析");
        list.add(firstBO);
        RepayAnalyzeBO flowBO = new RepayAnalyzeBO();
        flowBO.setProject("流动比率");
        String flow = "0";
        if (0d != flowDebt) {
            flow = String.format("%.2f", (flowAsset / flowDebt) * 100);
        }
        flowBO.setScale(flow + "%");
        flowBO.setBestScale("200%");
        flowBO.setExplain("流动比率越高，反映企业短期偿债能力越强，但是流动比率过高则表明企业流动资产占用较多，" +
                "会影响企业的资金利用效率，进而降低企业的获利能力。");
        list.add(flowBO);
        RepayAnalyzeBO rateBO = new RepayAnalyzeBO();
        rateBO.setProject("速动比率");
        String rate = "0";
        if (0d != flowDebt) {
            rate = String.format("%.2f", ((flowAsset - stock) / flowDebt) * 100);
        }
        rateBO.setScale(rate + "%");
        rateBO.setBestScale("100%");
        rateBO.setExplain("速动比例较高说明公司不用动用存货，仅仅依靠速动资产就能偿还债务，偿还流动负债的能力较强，" +
                "但过高的速动比率也会造成资金的闲置，影响企业的盈利能力。");
        list.add(rateBO);
        RepayAnalyzeBO cashBO = new RepayAnalyzeBO();
        cashBO.setProject("现金比率");
        String cash = "0";
        if (0d != flowDebt) {
            cash = String.format("%.2f", (fund / flowDebt) * 100);
        }
        cashBO.setScale(cash + "%");
        cashBO.setBestScale("20%");
        cashBO.setExplain("现金比率越高，表明企业的直接偿付能力越强，信用也就越可靠。" +
                "但是由于现金是企业收益率最低的资产，现金比率过高将会影响企业的盈利能力。");
        list.add(cashBO);
        RepayAnalyzeBO secondBO = new RepayAnalyzeBO();
        secondBO.setProject("二、长期偿债能力分析");
        list.add(secondBO);
        RepayAnalyzeBO assetdebtBO = new RepayAnalyzeBO();
        assetdebtBO.setProject("资产负债率");
        String assetdebt = "0";
        if (0d != asset) {
            assetdebt = String.format("%.2f", (debt / asset) * 100);
        }
        assetdebtBO.setScale(assetdebt + "%");
        assetdebtBO.setBestScale("40%-60%");
        assetdebtBO.setExplain("对于经营风险比较高的企业，为减少财务风险应选择比较低的资产负债率；" +
                "对于经营风险低的企业，为增加股东收益应选择比较高的资产负债率。");
        list.add(assetdebtBO);
        RepayAnalyzeBO equityBO = new RepayAnalyzeBO();
        equityBO.setProject("产权比率");
        String equity = "0";
        if (0d != all) {
            equity = String.format("%.2f", (debt / all) * 100);
        }
        equityBO.setScale(equity + "%");
        equityBO.setBestScale("100%");
        equityBO.setExplain("较低的产权比率表明企业采用了低风险、低报酬的资本结构，债权人的利益受保护程度较高，企业财务风险较小。" +
                "但是过低的产权比率也意味着企业不能充分发挥负债带来的财务杠杆作用。产权比率高，是高风险、高报酬的财务结构");
        list.add(equityBO);
        RpcTransmit.transmitUserToken(userToken);
        String advice = repayAdvice(flow, rate, cash, assetdebt, equity);
        RepayAnalyzeBO adviceBO = new RepayAnalyzeBO();
        adviceBO.setProject("三、管理建议");
        adviceBO.setExplain(advice);
        adviceBO.setScale("随便");
        adviceBO.setBestScale("随便");
        list.add(adviceBO);
        return list;
    }

    /**
     * 获取偿债能力分析管理建议
     *
     * @param flow
     * @param rate
     * @param cash
     * @param assetdebt
     * @param equity
     * @return
     * @throws SerException
     */
    private String repayAdvice(String flow, String rate, String cash, String assetdebt, String equity) throws SerException {
        List<RepayAnalyzeAdviceBO> advices = repayAnalyzeAdviceSer.list(new RepayAnalyzeAdviceDTO());
        String advice = null;
        if (advices != null && !advices.isEmpty()) {
            for (RepayAnalyzeAdviceBO r : advices) {
                boolean b1 = Double.parseDouble(flow) >= r.getFlowMin() && Double.parseDouble(flow) <= r.getFlowMax();
                boolean b2 = Double.parseDouble(rate) >= r.getRateMin() && Double.parseDouble(rate) <= r.getRateMax();
                boolean b3 = Double.parseDouble(cash) >= r.getMoneyMin() && Double.parseDouble(cash) <= r.getMoneyMax();
                boolean b4 = Double.parseDouble(assetdebt) >= r.getAssestMin() && Double.parseDouble(assetdebt) <= r.getAssestMax();
                boolean b5 = Double.parseDouble(equity) >= r.getEquityMin() && Double.parseDouble(equity) <= r.getEquityMax();
                if (b1 && b2 && b3 && b4 && b5) {
                    advice = r.getAdvice();
                }
            }
        }
        return advice;
    }

    @Override
    public List<DetailBO> findDetails(String id, AssetDTO dto) throws SerException {
        checkSeeIdentity();
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(dto, formulaDTO);
        List<FormulaBO> list = formulaSer.findByFid(id, formulaDTO);
        List<DetailBO> boList = new ArrayList<>();
        if ((list != null) && (!list.isEmpty())) {
            FormulaBO last = list.get(list.size() - 1);
            double begin = last.getBegin();
            double current = last.getCurrent();

            Double beginningCreditAmount = last.getBeginningCreditAmount();//期初余额
            Double issueDebitAmount = last.getIssueDebitAmount();//本期借方总额
            Double issueCreditAmount = last.getIssueCreditAmount();//本期贷方总额
            Double issueTotalAmount = last.getIssueTotalAmount();//本期合计余额
            Double endDebitAmount = last.getEndDebitAmount();//期末借方总额
            Double endCreditAmount = last.getEndCreditAmount();//期末贷方总额
            Double endTotalAmount = last.getEndTotalAmount();//本年累计额


//            Form form = last.getForm();
            Form form = Form.DEBIT;
            double currentSum = 0;
            String project = findByID(id).getAsset();
            String term = startTime + "~" + endTime;

            DetailBO currentBO = new DetailBO();
            currentBO.setProject(project);
            currentBO.setTerm(term);
            currentBO.setState("本期合计");
            currentBO.setForm(form);
            currentBO.setDebit(issueDebitAmount);
            currentBO.setCredit(issueCreditAmount);
            currentBO.setRemain(issueTotalAmount);
//            if (Form.DEBIT.equals(form)) {
//                currentSum = begin + current;
//                currentBO.setDebit(current);
//            } else if (Form.CREDIT.equals(form)) {
//                currentSum = begin - current;
//                currentBO.setCredit(current);
//            }

            double year = currentSum;
            DetailBO beginBO = new DetailBO();
            beginBO.setProject(project);
            beginBO.setTerm(term);
            beginBO.setState("期初余额");
            beginBO.setForm(form);
            beginBO.setRemain(beginningCreditAmount);
            boList.add(beginBO);
            boList.add(currentBO);

            DetailBO yearBO = new DetailBO();
//            yearBO.setProject(project);
            yearBO.setTerm(term);
            yearBO.setState("本年累计");
            yearBO.setForm(form);
            yearBO.setDebit(endDebitAmount);
            yearBO.setCredit(endCreditAmount);
            yearBO.setRemain(endTotalAmount);
            boList.add(yearBO);
        }
        return boList;
    }

    @Override
    public AssetBO findByID(String id) throws SerException {
        Asset entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, AssetBO.class);
    }

    /**
     * 查找流水负债,负债总额，所有者权益
     *
     * @param dto
     * @return
     * @throws SerException
     */
    private List<Double> finds(AssetDTO dto) throws SerException {
        DebtDTO debtDTO = new DebtDTO();
        BeanUtils.copyProperties(dto, debtDTO, "sorts");
        List<StructureBO> list = debtSer.debtStructure(debtDTO);
        double flow = 0;
        double all = 0;
        double sum = 0;
        double debt = 0;
        if ((list != null) && (!list.isEmpty())) {
            flow = list.get(0).getFee();
            all = list.get(2).getFee();
            sum = list.get(3).getFee();
            debt = sum - all;
        }
        List<Double> doubles = new ArrayList<>();
        doubles.add(flow);
        doubles.add(debt);
        doubles.add(all);
        return doubles;
    }

    @Override
    public List<AssetBO> list1(AssetDTO dto) throws SerException {
        checkSeeIdentity();
        List<Asset> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, AssetBO.class);
    }

    @Override
    public byte[] exportExcel(AssetDTO dto) throws SerException {
        List<AssetAndDebtExportExcel> list = new ArrayList<>(0);
        List<AssetBO> assetBOs = this.list(dto);
        DebtDTO debtDTO = new DebtDTO();
        BeanUtils.copyProperties(dto, debtDTO);
        debtDTO.getSorts().clear();
        List<DebtBO> debtBOs = debtSer.list(debtDTO);

        if (null != assetBOs && assetBOs.size() > 0 && null != debtBOs && debtBOs.size() > 0) {
            int assetSize = assetBOs.size();
            int debtSize = debtBOs.size();
            int i = 0;
            if (assetSize > debtSize) {
                i = assetSize;
            } else {
                i = debtSize;
            }
            for (int j = 0; j < i; j++) {
                AssetAndDebtExportExcel assetAndDebtExportExcel = new AssetAndDebtExportExcel();
                assetAndDebtExportExcel.setAsset(j < assetBOs.size() ? assetBOs.get(j).getAsset() : null);
                assetAndDebtExportExcel.setAssetNum(j < assetBOs.size() ? assetBOs.get(j).getAssetNum() : null);
                assetAndDebtExportExcel.setBeginAsset(j < assetBOs.size() ? assetBOs.get(j).getBeginAsset() : null);
                assetAndDebtExportExcel.setEndAsset(j < assetBOs.size() ? assetBOs.get(j).getEndAsset() : null);
                assetAndDebtExportExcel.setDebt(j < debtBOs.size() ? debtBOs.get(j).getDebt() : null);
                assetAndDebtExportExcel.setDebtNum(j < debtBOs.size() ? debtBOs.get(j).getDebtNum() : null);
                assetAndDebtExportExcel.setBeginDebt(j < debtBOs.size() ? debtBOs.get(j).getBeginDebt() : null);
                assetAndDebtExportExcel.setEndDebt(j < debtBOs.size() ? debtBOs.get(j).getEndDebt() : null);
                list.add(assetAndDebtExportExcel);
            }
        }
        Excel excel = new Excel(2, 3);
        byte[] bytes1 = ExcelUtil.clazzToExcel(list, excel);
        XSSFWorkbook wb = null;
        String comp = "";
        List<String> comps = companyBasicInfoAPI.findCompanyName();
        if (comps != null && comps.size() > 0) {
            comp = comps.get(0);
        }
        try {
            InputStream is = new ByteArrayInputStream(bytes1);
            wb = new XSSFWorkbook(is);// 创建一个工作execl文档
            XSSFCellStyle headerStyle = ExcelUtil.getStyle(wb, IndexedColors.WHITE.getIndex());
            headerStyle.setAlignment(HorizontalAlignment.CENTER); //水平布局：居中
            headerStyle.setWrapText(true);
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row = sheet.createRow(0);
            XSSFRow row1 = sheet.createRow(1);
            //标题
            for (int o = 0; o < 8; o++) {
                row.createCell(o).setCellValue("资产负债表");
            }
            CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 7);
            sheet.addMergedRegion(cra);//这个干嘛的
            //公司和单位
            row1.createCell(0).setCellValue("编制单位");
            row1.createCell(1).setCellValue(comp + "公司");
            row1.createCell(2).setCellValue(comp + "公司");
            row1.createCell(3).setCellValue("所属期:" + dto.getEndTime());
            row1.createCell(4).setCellValue("所属期:" + dto.getEndTime());
            row1.createCell(5).setCellValue("所属期:" + dto.getEndTime());
            row1.createCell(6).setCellValue("所属期:" + dto.getEndTime());
            row1.createCell(7).setCellValue("单位:元");

            CellRangeAddress cra1 = new CellRangeAddress(1, 1, 1, 2);
            sheet.addMergedRegion(cra1);//这个干嘛的
            CellRangeAddress cra2 = new CellRangeAddress(1, 1, 3, 6);
            sheet.addMergedRegion(cra2);//这个干嘛的

            row.getCell(0).setCellStyle(headerStyle);
            row1.getCell(3).setCellStyle(headerStyle);

        } catch (Exception e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return os.toByteArray();
    }

    @Override
    public List<String> allFirstSubjects() throws SerException {
//        if (moduleAPI.isCheck("financeinit")) {
        List<AccountAddDateBO> bos = accountanCourseAPI.findFirstNameCode();
        if (null != bos && bos.size() > 0) {
            List<String> list = bos.stream().map(AccountAddDateBO::getAccountanName).distinct().collect(Collectors.toList());
            return list;
        }
        return null;
    }

    //获取所有项目名称
    @Override
    public List<String> allProjectNames() throws SerException {
        List<String> list = new ArrayList<>(0);
        String userToken = RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("voucher")) {
            list = voucherGenerateAPI.findProjectName();
        }
        return list;
    }

    @Override
    public Long count(AssetDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public void assetTask() throws SerException {
        int year = 10;  //获取最近10年数据
        LocalDate now = LocalDate.now();
        LocalDate before = now.minusYears(year);
        now = now.minusYears(-1);
        while (!now.isEqual(before)) {

            for (int i = 1; i <= 12; i++) {

                LocalDate first = before.withMonth(i);
                if (now.getYear() - 1 == before.getYear() && first.getMonthValue() > before.getMonthValue()) {
                    continue;
                }

                System.out.println("时间段：" + first.with(TemporalAdjusters.firstDayOfMonth()) + " - " + first.with(TemporalAdjusters.lastDayOfMonth()) + "  " + new Date());
                LocalDate start = first.with(TemporalAdjusters.firstDayOfMonth());
                LocalDate end = first.with(TemporalAdjusters.lastDayOfMonth());

                AssetDTO dto = new AssetDTO();
                dto.setStartTime(start.toString());
                dto.setEndTime(end.toString());
                dto.setLastest(true);
                List<AssetBO> bos = this.list(dto);
//                List<ProfitBO> bos = new ArrayList<>();
//                bos.add(new ProfitBO("1", null,null,0.0,0.0,null,null,null));
                if (bos == null || bos.size() < 1) {
                    continue;
                }
                List<AssetData> profitDataList = new ArrayList<>();
                for (AssetBO bo : bos) {
                    AssetData data = new AssetData();
                    data.setStartTime(start);
                    data.setEndTime(end);
                    data.setBeginAsset(bo.getBeginAsset() == null ? new BigDecimal(0.0) : new BigDecimal(bo.getBeginAsset()));
                    data.setEndAsset(bo.getEndAsset() == null ? new BigDecimal(0.0) : new BigDecimal(bo.getEndAsset()));
                    data.setNum(bo.getAssetNum());
                    data.setAssetType(bo.getAssetType());
                    data.setProject(bo.getAsset());
                    data.setType(bo.getType());
                    data.setProjectId(bo.getId());
                    data.setSystemId(null);   //公司id，预留字段 todo
                    if (data == null) {
                        continue;
                    }
                    profitDataList.add(data);
                }
                //保存到本模块
                if (profitDataList != null && profitDataList.size() > 0) {

                    assetDataSer.save(profitDataList);
                }
            }
            year--;
            before = now.minusYears(year);
        }
    }
}