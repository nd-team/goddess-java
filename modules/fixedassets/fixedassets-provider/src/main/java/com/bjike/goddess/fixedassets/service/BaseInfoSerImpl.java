package com.bjike.goddess.fixedassets.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.fixedassets.bo.BaseInfoBO;
import com.bjike.goddess.fixedassets.bo.BaseInfoDetailBO;
import com.bjike.goddess.fixedassets.bo.SummationBO;
import com.bjike.goddess.fixedassets.dto.BaseInfoDTO;
import com.bjike.goddess.fixedassets.entity.BaseInfo;
import com.bjike.goddess.fixedassets.enums.DepreciationWay;
import com.bjike.goddess.fixedassets.enums.FixedAssetType;
import com.bjike.goddess.fixedassets.enums.GuideAddrStatus;
import com.bjike.goddess.fixedassets.excel.BaseInfoImport;
import com.bjike.goddess.fixedassets.excel.BaseInfoImportTemple;
import com.bjike.goddess.fixedassets.excel.SonPermissionObject;
import com.bjike.goddess.fixedassets.to.BaseInfoTO;
import com.bjike.goddess.fixedassets.to.GuidePermissionTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 基本信息业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-23 11:41 ]
 * @Description: [ 基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "fixedassetsSerCache")
@Service
public class BaseInfoSerImpl extends ServiceImpl<BaseInfo, BaseInfoDTO> implements BaseInfoSer {
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

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
                throw new SerException("您不是相应财务部门的人员，不可以查看");
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


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("baseinfo");
        obj.setDescribesion("基本信息");
        if (flagSeeSign) {
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
            case UPLOAD:
                flag = guideSeeIdentity();
                break;
            case DOWNLOAD:
                flag = guideSeeIdentity();
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
    public List<BaseInfoBO> list(BaseInfoDTO dto) throws SerException {
       checkSeeIdentity();
        List<BaseInfo> baseInfoList = super.findByCis(dto, true);
        return BeanTransform.copyProperties(baseInfoList, BaseInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public BaseInfoBO save(BaseInfoTO to) throws SerException {
        checkSeeIdentity();
        BaseInfo baseInfo = BeanTransform.copyProperties(to, BaseInfo.class, true);
        baseInfo.setCreateTime(LocalDateTime.now());
        baseInfo.setEstimatedValue(to.getOriginalValue() * to.getSalvage());
        baseInfo.setDepreciationWay(DepreciationWay.AVERAGELIFE);
        baseInfo.setEstimatedPeriod(checkValue(to.getFixedAssetType()));
        baseInfo.setAccumulatedPeriod(computationMonth(to.getBookDate(), DateUtil.dateToString(LocalDate.now())));
        baseInfo.setDepreciationMonth((to.getOriginalValue() - baseInfo.getEstimatedValue()) * Double.parseDouble(baseInfo.getEstimatedPeriod().substring(0, baseInfo.getEstimatedPeriod().lastIndexOf("("))));
        super.save(baseInfo);
        return BeanTransform.copyProperties(baseInfo, BaseInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public BaseInfoBO edit(BaseInfoTO to) throws SerException {
       checkSeeIdentity();
        BaseInfo baseInfo = super.findById(to.getId());
        LocalDateTime dateTime = baseInfo.getCreateTime();
        Integer accumulatedPeriod = baseInfo.getAccumulatedPeriod();
        LocalDate date = baseInfo.getBookDate();
        baseInfo = BeanTransform.copyProperties(to, BaseInfo.class, true, "estimatedPeriod");
        baseInfo.setCreateTime(dateTime);
        baseInfo.setModifyTime(LocalDateTime.now());
        baseInfo.setEstimatedValue(to.getOriginalValue() * to.getSalvage());
        //计算从入账开始预计使用年限
        Double year = to.getEstimatedPeriod() / 12d;
        String estimatedPeriod = to.getEstimatedPeriod() + "(" + year + "年)";
        baseInfo.setEstimatedPeriod(estimatedPeriod);
        if (DateUtil.parseDate(to.getBookDate()) != date) {
            baseInfo.setAccumulatedPeriod(computationMonth(to.getBookDate(), DateUtil.dateToString(LocalDate.now())));
        } else {
            baseInfo.setAccumulatedPeriod(accumulatedPeriod);
        }
        baseInfo.setDepreciationMonth((to.getOriginalValue() - baseInfo.getEstimatedValue()) * Double.parseDouble(baseInfo.getEstimatedPeriod().substring(0, baseInfo.getEstimatedPeriod().lastIndexOf("("))));
        super.update(baseInfo);
        return BeanTransform.copyProperties(baseInfo, BaseInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void deleteBaseInfo(String id) throws SerException {
        checkSeeIdentity();
        super.remove(id);
    }

    @Override
    public Long countBaseInfo(BaseInfoDTO baseInfoDTO) throws SerException {
        return super.count(baseInfoDTO);
    }

    @Override
    public BaseInfoBO getById(String id) throws SerException {
        BaseInfo baseInfo = super.findById(id);
        return BeanTransform.copyProperties(baseInfo, BaseInfoBO.class);
    }

    /**
     * 计算两个日期相差月数
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws SerException
     */
    private Integer computationMonth(String startDate, String endDate) throws SerException {
        Integer result = 0;
        LocalDate start = DateUtil.parseDate(startDate);
        LocalDate end = DateUtil.parseDate(endDate);
        Integer year = end.getYear() - start.getYear();
        Integer month = end.getMonthValue() - start.getMonthValue();
        if (Math.abs(year) == 0) {
            result = month;
        } else {
            result = year * 12 + month;
        }
        return Math.abs(result);
    }

    private String checkValue(FixedAssetType fixedAssetType) throws SerException {
        String name = null;
        Double month = 0d;
        switch (fixedAssetType) {
            case HOUSESBUILDING:
                month = 20 * 12d;
                name = month + "(20年)";
                break;
            case GENERALEQUIPMENT:
                month = 5 * 12d;
                name = month + "(5年)";
                break;
            case SPECIALEQUIPMENT:
                month = 5 * 12d;
                name = month + "(5年)";
                break;
            case MACHINERYEQUIPMENT:
                month = 10 * 12d;
                name = month + "(10年)";
                break;
            case APPLIANCESTOOLSFURNITURE:
                month = 5 * 12d;
                name = month + "(5年)";
                break;
            case MEANSTRANSPORT:
                month = 10 * 12d;
                name = month + "(10年)";
                break;
            case ELECTRONICEQUIPMENT:
                month = 5 * 12d;
                name = month + "(5年)";
                break;
            case OTHERFIXEDASSETS:
                month = 5 * 12d;
                name = month + "(5年)";
                break;
            default:
                name = month + "(0年)";
                break;
        }
        return name;
    }

    @Override
    public byte[] exportExcel() throws SerException {
//        checkSeeIdentity();
        List<BaseInfo> list = super.findAll();
        List<BaseInfoImport> exports = new ArrayList<>();
        for (BaseInfo baseInfo : list) {
            BaseInfoImport export = BeanTransform.copyProperties(baseInfo, BaseInfoImport.class);
            exports.add(export);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void importExcel(List<BaseInfoTO> baseInfoTOS) throws SerException {
//       checkSeeIdentity();
        for (BaseInfoTO to : baseInfoTOS) {
            BaseInfo baseInfo = BeanTransform.copyProperties(to, BaseInfo.class, true);
            baseInfo.setCreateTime(LocalDateTime.now());
            baseInfo.setEstimatedValue(to.getOriginalValue() * to.getSalvage());
            baseInfo.setDepreciationWay(DepreciationWay.AVERAGELIFE);
            baseInfo.setEstimatedPeriod(checkValue(to.getFixedAssetType()));
            baseInfo.setAccumulatedPeriod(computationMonth(to.getBookDate(), DateUtil.dateToString(LocalDate.now())));
            baseInfo.setDepreciationMonth((to.getOriginalValue() - baseInfo.getEstimatedValue()) * Double.parseDouble(baseInfo.getEstimatedPeriod().substring(0, baseInfo.getEstimatedPeriod().lastIndexOf("("))));
            super.save(baseInfo);
        }
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<BaseInfoImportTemple> baseInfoImportTemples = new ArrayList<>();
        BaseInfoImportTemple templateExcel = new BaseInfoImportTemple();
        templateExcel.setFixedAssetName("固定资产1");
        templateExcel.setFixedAssetSize("30zp");
        templateExcel.setFixedAssetType("房屋、建筑物");
        templateExcel.setNumbers(12);
        templateExcel.setUnit("台");
        templateExcel.setDepartment("研发部");
        templateExcel.setUserName("李四");
        templateExcel.setBookDate("2017-12-12");
        templateExcel.setFixedAssetAccountCourse("100102:资产额");
        templateExcel.setTotalDepreciaSubject("2002:累计折旧");
        templateExcel.setLossImpairmentSubject("3003:减值准备");
        templateExcel.setDepreciaExpenceSubject("400402:管理费用-折旧费");
        templateExcel.setTaxSubject("31120206:应交税金-应交增值税-进项税额");
        templateExcel.setLossImpairmentOppSubject("2002:营业外支出");
        templateExcel.setIncreaseWay("改造");
        templateExcel.setUses("收费");
        templateExcel.setStatus("使用中");
        templateExcel.setRemark("很重要很重要");
        templateExcel.setOriginalValue(10d);
        templateExcel.setTax(100d);
        templateExcel.setSalvage(0.10);
        templateExcel.setImpairmentLoss(10d);
        templateExcel.setAccumulatDepreciation(100d);
        templateExcel.setNetBegining(10d);
        baseInfoImportTemples.add(templateExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(baseInfoImportTemples, excel);
        return bytes;
    }

    @Override
    public List<SummationBO> summation(Integer year, Integer month) throws SerException {
       checkSeeIdentity();
        String date = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        List<SummationBO> summationBOList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            BaseInfoDTO baseInfoDTO = new BaseInfoDTO();
            baseInfoDTO.getConditions().add(Restrict.eq("fixedAssetType", i));
            baseInfoDTO.getConditions().add(Restrict.lt_eq("bookDate", date));
            List<BaseInfo> baseInfoList = super.findByCis(baseInfoDTO);
            if (baseInfoList != null && baseInfoList.size() > 0) {
                Double totalOriginalValue = 0d;
                Double totalDepreciationMonth = 0d;
                Double accumDepreciation = 0d;
                Double yearAccumDepreciation = 0d;
                Double impairmentLoss = 0d;
                Double netFinalValue = 0d;
                for (BaseInfo baseInfo : baseInfoList) {
                    totalOriginalValue += baseInfo.getOriginalValue();
                    totalDepreciationMonth += baseInfo.getDepreciationMonth();
                    //期末累计折旧=当前输入的年份和月份-入账日期的月份*月折旧额
                    Integer diffMonth = computationMonth(DateUtil.dateToString(baseInfo.getBookDate()), date);
                    accumDepreciation += diffMonth * baseInfo.getDepreciationMonth();
                    //本年累计折旧 = 汇总的年份与入账日期比较是他们的年份相同的话就是汇总的(月份-1)*月折旧额,如果是汇总年份与入账日期比较不同的就是直接是月份*月折旧额
                    if (year == baseInfo.getBookDate().getYear()) {
                        yearAccumDepreciation += (month - 1) * baseInfo.getDepreciationMonth();
                    } else {
                        yearAccumDepreciation += month * baseInfo.getDepreciationMonth();
                    }
                    impairmentLoss += (baseInfo.getImpairmentLoss() == null ? 0 : baseInfo.getImpairmentLoss());
                }
                SummationBO summationBO = new SummationBO();
                summationBO.setFixedAssetType(fixedAssetTypeToString(i));
                summationBO.setOriginalValue(totalOriginalValue);
                summationBO.setDepreciationMonth(totalDepreciationMonth);
                summationBO.setAccumDepreciation(accumDepreciation);
                summationBO.setYearAccumDepreciation(yearAccumDepreciation);
                summationBO.setImpairmentLoss(impairmentLoss);
                summationBO.setNetFinalValue(netFinalValue);
                summationBOList.add(summationBO);
            }
        }
        return summationBOList;
    }

    private String fixedAssetTypeToString(Integer i) throws SerException {
        String name = "";
        switch (i) {
            case 0:
                name = "房屋、建筑物";
                break;
            case 1:
                name = "一般办公设备";
                break;
            case 2:
                name = "专用设备";
                break;
            case 3:
                name = "机器机械生产设备";
                break;
            case 4:
                name = "器具、工具、家具";
                break;
            case 5:
                name = "运输工具";
                break;
            case 6:
                name = "电子设备";
                break;
            case 7:
                name = "其他固定资产";
                break;
            default:
                name = "";
                break;
        }
        return name;
    }

    @Override
    public List<BaseInfoDetailBO> baseInfoDetail(Integer year, Integer month) throws SerException {
       checkSeeIdentity();
        String date = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        List<BaseInfoDetailBO> baseInfoDetailBOS = new ArrayList<>();
        BaseInfoDTO baseInfoDTO = new BaseInfoDTO();
        baseInfoDTO.getConditions().add(Restrict.lt_eq("bookDate", date));
        List<BaseInfo> baseInfoList = super.findByCis(baseInfoDTO);
        if (baseInfoList != null && baseInfoList.size() > 0) {
            for (BaseInfo baseInfo : baseInfoList) {
                BaseInfoDetailBO baseInfoDetailBO = new BaseInfoDetailBO();
                baseInfoDetailBO.setFixedAssetName(baseInfo.getFixedAssetName());
                baseInfoDetailBO.setDepartment(baseInfo.getDepartment());
                //计算折旧率
                Integer months = Integer.parseInt(baseInfo.getEstimatedPeriod().substring(0, baseInfo.getEstimatedPeriod().lastIndexOf("(")));
                Double depreRate = (1 - baseInfo.getSalvage()) / months / 12;
                baseInfoDetailBO.setDepreciationMonthRate(depreRate);
                baseInfoDetailBO.setOriginalValue(baseInfo.getOriginalValue());
                baseInfoDetailBO.setDepreciationMonth(baseInfo.getDepreciationMonth());
                //期末累计折旧=当前输入的年份和月份-入账日期的月份*月折旧额
                Integer diffMonth = computationMonth(DateUtil.dateToString(baseInfo.getBookDate()), date);
                baseInfoDetailBO.setAccumDepreciation(diffMonth * baseInfo.getDepreciationMonth());
                //本年累计折旧 = 汇总的年份与入账日期比较是他们的年份相同的话就是汇总的(月份-1)*月折旧额,如果是汇总年份与入账日期比较不同的就是直接是月份*月折旧额
                if (year == baseInfo.getBookDate().getYear()) {
                    baseInfoDetailBO.setYearAccumDepreciation((month - 1) * baseInfo.getDepreciationMonth());
                } else {
                    baseInfoDetailBO.setYearAccumDepreciation(month * baseInfo.getDepreciationMonth());
                }
                baseInfoDetailBO.setImpairmentLoss(baseInfo.getImpairmentLoss());
                baseInfoDetailBO.setNetFinalValue(baseInfo.getNetBegining());
                baseInfoDetailBOS.add(baseInfoDetailBO);
            }
        }
        return baseInfoDetailBOS;
    }

    @Override
    public List<String> findallUser() throws SerException {
        List<UserBO> userBOS = positionDetailUserAPI.findUserList();
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(userBOS)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (UserBO userBO : userBOS) {
            String userName = userBO.getUsername();
            if (StringUtils.isNotBlank(userBO.getUsername())) {
                set.add(userName);
            }
        }
        return new ArrayList<>(set);
    }
}