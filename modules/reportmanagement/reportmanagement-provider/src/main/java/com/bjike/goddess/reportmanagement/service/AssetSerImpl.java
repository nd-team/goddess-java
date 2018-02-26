package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Override
    public List<AssetBO> list(AssetDTO dto) throws SerException {
//        checkSeeIdentity();
        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            dto.setStartTime(DateUtil.dateToString(DateUtil.getStartMonth()));
            dto.setEndTime(DateUtil.dateToString(DateUtil.getEndMonth()));
        }
        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(dto, formulaDTO);
        dto.getSorts().add("assetType=ASC");
        dto.getSorts().add("createTime=asc");
        List<Asset> list = super.findByCis(dto);
        List<AssetBO> boList = new ArrayList<AssetBO>();
        boolean b1 = true;
        boolean b2 = true;
        boolean b3 = true;
        boolean b4 = true;
        boolean b5 = true;
        double beginSum = 0;
//        double currentSum = 0;
        double endSum = 0;
        double countBegin = 0;
//        double countCurrent = 0;
        double countEnd = 0;       //总资产
        int num = 1;
        for (Asset asset : list) {
            List<FormulaBO> formulaBOs = formulaSer.findByFid(asset.getId(), formulaDTO);
            if (AssetType.AFLOW.equals(asset.getAssetType()) && b1) {
                AssetBO assetBO = new AssetBO();
                assetBO.setAsset("流动资产：");
                boList.add(assetBO);
                b1 = false;
            } else if (AssetType.BLONG.equals(asset.getAssetType()) && b2) {
                AssetBO sumBO = new AssetBO();
                sumBO.setAsset("流动资产合计");
                sumBO.setBeginAsset(beginSum);
//                sumBO.setCurrent(currentSum);
                sumBO.setEndAsset(endSum);
                sumBO.setAssetNum(num);
                num++;
                boList.add(sumBO);
                beginSum = 0;
//                currentSum = 0;
                endSum = 0;    //置为0
                AssetBO assetBO = new AssetBO();
                assetBO.setAsset("长期资产：");
                boList.add(assetBO);
                b2 = false;
            } else if (AssetType.CFIX.equals(asset.getAssetType()) && b3) {
                AssetBO sumBO = new AssetBO();
                sumBO.setAsset("长期资产合计");
                sumBO.setBeginAsset(beginSum);
//                sumBO.setCurrent(currentSum);
                sumBO.setEndAsset(endSum);
                sumBO.setAssetNum(num);
                num++;
                boList.add(sumBO);
                beginSum = 0;
//                currentSum = 0;
                endSum = 0;    //置为0
                AssetBO assetBO = new AssetBO();
                assetBO.setAsset("固定资产：");
                boList.add(assetBO);
                b3 = false;
            } else if (AssetType.DINVISIBLE.equals(asset.getAssetType()) && b4) {
                AssetBO sumBO = new AssetBO();
                sumBO.setAsset("固定资产合计");
                sumBO.setBeginAsset(beginSum);
//                sumBO.setCurrent(currentSum);
                sumBO.setEndAsset(endSum);
                sumBO.setAssetNum(num);
                num++;
                boList.add(sumBO);
                beginSum = 0;
//                currentSum = 0;
                endSum = 0;    //置为0
                AssetBO assetBO = new AssetBO();
                assetBO.setAsset("无形资产及其他资产：");
                boList.add(assetBO);
                b4 = false;
            } else if (AssetType.ETAX.equals(asset.getAssetType()) && b5) {
                AssetBO sumBO = new AssetBO();
                sumBO.setAsset("无形资产及其他资产合计");
                sumBO.setBeginAsset(beginSum);
//                sumBO.setCurrent(currentSum);
                sumBO.setEndAsset(endSum);
                sumBO.setAssetNum(num);
                num++;
                boList.add(sumBO);
                beginSum = 0;
//                currentSum = 0;
                endSum = 0;    //置为0
                AssetBO assetBO = new AssetBO();
                assetBO.setAsset("递延税款：");
                boList.add(assetBO);
                b5 = false;
            }
            if ((formulaBOs != null) && (!formulaBOs.isEmpty())) {
                FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
                AssetBO bo = BeanTransform.copyProperties(asset, AssetBO.class);
                bo.setBeginAsset(formulaBO.getBegin());
                bo.setCurrent(formulaBO.getCurrent());
                bo.setEndAsset(formulaBO.getEnd());
                if (Type.ADD.equals(asset.getType())) {
                    if (!"固定资产原值".equals(bo.getAsset()) || !"累计折旧".equals(bo.getAsset()) || !"固定资产净值".equals(bo.getAsset()) || !"固定资产减值准备".equals(bo.getAsset())) {
                        beginSum += bo.getBeginAsset();
//                    currentSum += bo.getCurrent();
                        endSum += bo.getEndAsset();
                        countBegin += bo.getBeginAsset();
//                    countCurrent += bo.getCurrent();
                        countEnd += bo.getEndAsset();
                    }
                } else if (Type.REMOVE.equals(asset.getType())) {

                    if (!"固定资产原值".equals(bo.getAsset()) || !"累计折旧".equals(bo.getAsset()) || !"固定资产净值".equals(bo.getAsset()) || !"固定资产减值准备".equals(bo.getAsset())) {
                        beginSum = beginSum - bo.getBeginAsset();
//                    currentSum -= bo.getCurrent();
                        endSum = endSum - bo.getEndAsset();
                        countBegin = countBegin - bo.getBeginAsset();
//                    countCurrent -= bo.getCurrent();
                        countEnd = countEnd - bo.getEndAsset();
                    }
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

        DebtDTO debtDTO = new DebtDTO();
        BeanUtils.copyProperties(dto, debtDTO, "sorts");
        Long size = debtSer.count(debtDTO);

        if (size + 10 > boList.size() + 1) {
            for (int i = 0; i < size + 10 - (boList.size() + 1); i++) {
                AssetBO assetBO = new AssetBO();
                boList.add(assetBO);
            }
        }

        AssetBO lastBO = new AssetBO();
        lastBO.setAsset("资产总计");
        lastBO.setBeginAsset(countBegin);
//        lastBO.setCurrent(countCurrent);
        lastBO.setEndAsset(countEnd);
        lastBO.setAssetNum(num);
        num++;
        Static.setNum(num);
        boList.add(lastBO);
        boList.stream().forEach(obj -> {
            obj.setStartTime(dto.getStartTime());
            obj.setEndTime(dto.getEndTime());
        });
        return boList;
    }


    //    zhuangkaiqin
//    @Override
//    public List<AssetBO> list(AssetDTO dto) throws SerException {
//        checkSeeIdentity();
//        List<AssetBO> assetBOs = new ArrayList<>(0);
//        AssetDTO assetDTO = new AssetDTO();
//        assetDTO.getSorts().add("seqNum=ASC");
//        List<Asset> assets = super.findByCis(assetDTO);
//        for (AssetType type : AssetType.values()) {
//            List<Asset> typeList = assets.stream().filter(obj -> type.equals(obj.getAssetType())).collect(Collectors.toList());
//            String string = getTypeData(type);
//            AssetBO assetBO = new AssetBO();
//            assetBO.setAsset(string + "：");
//            assetBOs.add(assetBO);
//            typeList.stream().forEach(obj -> {
//                assetBOs.add(BeanTransform.copyProperties(obj, AssetBO.class));
//            });
//        }
//        //增加资产总计
//        AssetBO assetBO = new AssetBO();
//        assetBO.setAsset("资产总计");
//        assetBO.setAssetNum(assetBOs.size() - 5);
//        assetBOs.add(assetBO);
//
//        FormulaDTO formulaDTO = new FormulaDTO();
//        BeanUtils.copyProperties(dto, formulaDTO);
//        dto.getSorts().add("assetType=ASC");
//        double beginSum = 0;
//        double currentSum = 0;
//        double endSum = 0;
//        double countBegin = 0;
//        double countCurrent = 0;
//        double countEnd = 0;
//        for (AssetBO bo : assetBOs) {
//            List<FormulaBO> formulaBOs = formulaSer.findByFid(bo.getId(), formulaDTO);
//            if (null != formulaBOs && formulaBOs.size() > 0) {
//                FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
//                if (bo.getAssetType() != null) {
//                    bo.setBeginAsset(formulaBO.getBegin());
//                    bo.setCurrent(formulaBO.getCurrent());
//                    bo.setEndAsset(formulaBO.getEnd());
//                    if (Type.ADD.equals(bo.getType())) {
//                        beginSum += bo.getBeginAsset();
//                        currentSum += bo.getCurrent();
//                        endSum += bo.getEndAsset();
//                        countBegin += bo.getBeginAsset();
//                        countCurrent += bo.getCurrent();
//                        countEnd += bo.getEndAsset();
//                    } else if (Type.REMOVE.equals(bo.getType())) {
//                        bo.setAsset("减：" + bo.getAsset());
//                        beginSum = beginSum - bo.getBeginAsset();
//                        currentSum -= bo.getCurrent();
//                        endSum = endSum - bo.getEndAsset();
//                        countBegin = countBegin - bo.getBeginAsset();
//                        countCurrent -= bo.getCurrent();
//                        countEnd = countEnd - bo.getEndAsset();
//                    }
//                }
//                if ("资产总计".equals(bo.getAsset())) {
//                    bo.setBeginAsset(countBegin);
//                    bo.setCurrent(countCurrent);
//                    bo.setEndAsset(countEnd);
//                }
//            }
//        }
//        return assetBOs;
//    }

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
//        }
        return null;
    }

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
}