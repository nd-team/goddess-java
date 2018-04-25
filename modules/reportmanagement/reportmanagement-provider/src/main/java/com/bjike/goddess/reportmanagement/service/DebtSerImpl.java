package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.reportmanagement.bo.*;
import com.bjike.goddess.reportmanagement.dto.DebtDTO;
import com.bjike.goddess.reportmanagement.dto.DebtStructureAdviceDTO;
import com.bjike.goddess.reportmanagement.dto.FormulaDTO;
import com.bjike.goddess.reportmanagement.entity.AssetData;
import com.bjike.goddess.reportmanagement.entity.AssetDebtData;
import com.bjike.goddess.reportmanagement.entity.Debt;
import com.bjike.goddess.reportmanagement.enums.DebtType;
import com.bjike.goddess.reportmanagement.enums.Form;
import com.bjike.goddess.reportmanagement.enums.GuideAddrStatus;
import com.bjike.goddess.reportmanagement.enums.Type;
import com.bjike.goddess.reportmanagement.to.DebtTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 负债表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:21 ]
 * @Description: [ 负债表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class DebtSerImpl extends ServiceImpl<Debt, DebtDTO> implements DebtSer {
    @Autowired
    private FormulaSer formulaSer;
    @Autowired
    private DebtStructureAdviceSer debtStructureAdviceSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private AssetSer assetSer;
    @Autowired
    private AssetDebtDataSer assetDebtDataSer;


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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public DebtBO save(DebtTO to) throws SerException {
        checkAddIdentity();
        Debt entity = BeanTransform.copyProperties(to, Debt.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, DebtBO.class);
    }

    //**资产负债列表
    @Override
    public List<DebtBO> list(DebtDTO dto) throws SerException {

        String token = RpcTransmit.getUserToken();
//        checkSeeIdentity();
        checkSeeIdentity();
      //  System.out.println(dto);
        List<DebtBO> boList = new ArrayList<DebtBO>();
        if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            dto.setStartTime(DateUtil.dateToString(DateUtil.getStartMonth()));
            dto.setEndTime(DateUtil.dateToString(DateUtil.getEndMonth()));
        }

        if (!dto.isLastest()) {
            //判断是否已有存储
            DebtDTO profitDTO = new DebtDTO();
            profitDTO.getConditions().add(Restrict.eq("startTime", dto.getStartTime()));
            profitDTO.getConditions().add(Restrict.eq("endTime", dto.getEndTime()));
            List<AssetDebtData> cashFlows = assetDebtDataSer.findByCis(profitDTO);
            if (null != cashFlows && cashFlows.size() > 0) {
                for (AssetDebtData data : cashFlows) {
                      System.out.println(data);
                    DebtBO bo = new DebtBO();
                    //负债期末数
                    bo.setEndDebt(data.getEndAsset().doubleValue());
                    //负债年初数
                    bo.setBeginDebt(data.getBeginAsset().doubleValue());
                    //负债和所有者权益行次
                    bo.setDebtNum(data.getNum());
                    //负债和所有者权益
                    bo.setDebt(data.getProject());
                    //当前月发生额
                    bo.setCurrent(null);
                    //运算类型
                    bo.setType(null);
                    //负债类型
                    bo.setDebtType(null);
                    bo.setStartTime(data.getStartTime().toString());
                    bo.setEndTime(data.getEndTime().toString());
                    bo.setId(data.getProjectId());
                    boList.add(bo);
                }
                return convertDebt(boList);
                }
            }
        //对应的公式数据传输对象
        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(dto, formulaDTO);
        dto.getSorts().add("debtType=ASC");
        dto.getSorts().add("createTime=ASC");
        List<Debt> list = super.findByCis(dto);

        Double beginSum1 = 0.0;
        Double beginSum2 = 0.0;
        Double beginSum3 = 0.0;
        Double beginSum4 = 0.0;
        Double beginSum5_1 = 0.0;
        Double beginSum5_2 = 0.0;
        Double endSum1 = 0.0;
        Double endSum2 = 0.0;
        Double endSum3 = 0.0;
        Double endSum4 = 0.0;
        Double endSum5_1 = 0.0;
        Double endSum5_2 = 0.0;

        int num = 0;
        for (Debt debt : list) {
            RpcTransmit.transmitUserToken(token);

            List<FormulaBO> formulaBOs = formulaSer.findByFid(debt.getId(), formulaDTO);
            if ((formulaBOs == null) || (formulaBOs.isEmpty())) {
                continue;
            }
            FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
            DebtBO bo = BeanTransform.copyProperties(debt, DebtBO.class);
            bo.setBeginDebt(formulaBO.getBegin());
            bo.setCurrent(formulaBO.getCurrent());
            bo.setEndDebt(formulaBO.getEnd());
            bo.setDebtNum(num);
            boList.add(bo);
            num++;
        }

        for (DebtBO bo : boList) {
            if (Type.ADD.equals(bo.getType())) {
                if (DebtType.AFLOW.equals(bo.getDebtType())) {
                    beginSum1 += bo.getBeginDebt();
                    endSum1 += bo.getEndDebt();
                }
                if (DebtType.BLONG.equals(bo.getDebtType())) {
                    beginSum2 += bo.getBeginDebt();
                    endSum2 += bo.getEndDebt();
                }
                if (DebtType.CTAX.equals(bo.getDebtType())) {
                    beginSum3 += bo.getBeginDebt();
                    endSum3 += bo.getEndDebt();

                }
                if (DebtType.DALL.equals(bo.getDebtType())) {
                    if ("实收资本（或股本）净额".equals(bo.getDebt()) || "实收资本(或股本)净额".equals(bo.getDebt()) || "资本公积".equals(bo.getDebt()) ||
                            "盈余公积".equals(bo.getDebt()) || "未分配利润".equals(bo.getDebt())) {
                        beginSum4 += bo.getBeginDebt();
                        endSum4 += bo.getEndDebt();
                    }

                    if ("实收资本(或股本)".equals(bo.getDebt()) || "实收资本（或股本）".equals(bo.getDebt())) {
                        beginSum5_1 = bo.getBeginDebt();
                        endSum5_1 = bo.getEndDebt();
                    }
                    if ("已归还投资".equals(bo.getDebt()) || "减：已归还投资".equals(bo.getDebt())) {
                        beginSum5_2 = bo.getBeginDebt();
                        endSum5_2 = bo.getEndDebt();
                    }
                }
            } else if (Type.REMOVE.equals(bo.getType())) {
                if (DebtType.AFLOW.equals(bo.getDebtType())) {
                    beginSum1 -= bo.getBeginDebt();
                    endSum1 -= bo.getEndDebt();
                }
                if (DebtType.BLONG.equals(bo.getDebtType())) {
                    beginSum2 -= bo.getBeginDebt();
                    endSum2 -= bo.getEndDebt();
                }
                if (DebtType.CTAX.equals(bo.getDebtType())) {
                    beginSum3 -= bo.getBeginDebt();
                    endSum3 -= bo.getEndDebt();

                }
                if (DebtType.DALL.equals(bo.getDebtType())) {
                    if ("实收资本（或股本）净额".equals(bo.getDebt()) || "实收资本(或股本)净额".equals(bo.getDebt()) || "资本公积".equals(bo.getDebt()) ||
                            "盈余公积".equals(bo.getDebt()) || "未分配利润".equals(bo.getDebt())) {
                        beginSum4 -= bo.getBeginDebt();
                        endSum4 -= bo.getEndDebt();
                    }
                    if ("实收资本(或股本)".equals(bo.getDebt()) || "实收资本（或股本）".equals(bo.getDebt())) {
                        beginSum5_1 = bo.getBeginDebt();
                        endSum5_1 = bo.getEndDebt();
                    }
                    if ("已归还投资".equals(bo.getDebt()) || "减：已归还投资".equals(bo.getDebt())) {
                        beginSum5_2 = bo.getBeginDebt();
                        endSum5_2 = bo.getEndDebt();
                    }
                }
            }
        }

        for (DebtBO bo : boList) {
            if ("实收资本(或股本)净额".equals(bo.getDebt()) || "实收资本（或股本）净额".equals(bo.getDebt())) {
                bo.setBeginDebt(beginSum5_1 - beginSum5_2);
                bo.setEndDebt(endSum5_1 - beginSum5_2);
                break;
            }
        }

        DebtBO debtBO1= new DebtBO();
        debtBO1.setDebt("流动负债：");
        boList.add(debtBO1);
        DebtBO debtBO2 = new DebtBO();
        debtBO2.setDebt("流动负债合计");
        debtBO2.setBeginDebt(beginSum1);
        debtBO2.setEndDebt(endSum1);
        debtBO2.setDebtNum(num);
        boList.add(debtBO2);
        num ++;
        DebtBO debtBO3= new DebtBO();
        debtBO3.setDebt("长期负债：");
        boList.add(debtBO3);
        DebtBO debtBO4 = new DebtBO();
        debtBO4.setDebt("长期负债合计");
        debtBO4.setBeginDebt(beginSum2);
        debtBO4.setEndDebt(endSum2);
        debtBO4.setDebtNum(num);
        boList.add(debtBO4);
        num ++;
        DebtBO debtBO5= new DebtBO();
        debtBO5.setDebt("递延税项：");
        boList.add(debtBO5);
        DebtBO debtBO6 = new DebtBO();
        debtBO6.setDebt("负债合计");
        debtBO6.setBeginDebt(beginSum1 + beginSum2 + beginSum3);
        debtBO6.setEndDebt(endSum1 + endSum2 + endSum3);
        debtBO6.setDebtNum(num);
        boList.add(debtBO6);
        num ++;
        DebtBO debtBO7_1= new DebtBO();
        debtBO7_1.setDebt("所有者权益(或股东权益)：");
        boList.add(debtBO7_1);
        DebtBO debtBO7= new DebtBO();
        debtBO7.setDebt("所有者权益(或股东权益)合计");
        debtBO7.setBeginDebt(beginSum4);
        debtBO7.setEndDebt(endSum4);
        debtBO7.setDebtNum(num);
        boList.add(debtBO7);
        num ++;
        boList.add(debtBO7);
        DebtBO debtBO8 = new DebtBO();
        debtBO8.setDebt("负债和所有者权益(或股东权益)总计");
        debtBO8.setBeginDebt(beginSum1 + beginSum2 + beginSum3 + beginSum4);
        debtBO8.setEndDebt(endSum1 + endSum2 + endSum3 + endSum4);
        debtBO8.setDebtNum(num);
        boList.add(debtBO8);
        num ++;

        //存储数据
        List<AssetDebtData> profitDataList = new ArrayList<>();
        for (DebtBO bo : boList) {
            AssetDebtData data = new AssetDebtData();
            data.setProject(bo.getDebt());
            data.setNum(bo.getDebtNum());
            data.setEndAsset(bo.getEndDebt() == null ? new BigDecimal(0.0) : new BigDecimal(bo.getEndDebt()));
            data.setBeginAsset(bo.getBeginDebt() == null ? new BigDecimal(0.0) : new BigDecimal(bo.getBeginDebt()));
            data.setStartTime(LocalDate.parse(dto.getStartTime()));
            data.setEndTime(LocalDate.parse(dto.getEndTime()));
            data.setProjectId(bo.getId());
            data.setSystemId(null);
            profitDataList.add(data);
        }
        new Thread(new saveAssetDebtDataThread(profitDataList)).start();


        return this.convertDebt(boList);
    }

    /**
     * 存储利润表的查询结果
     *
     */
    public class saveAssetDebtDataThread implements Runnable {

        private List<AssetDebtData> list;

        public saveAssetDebtDataThread(List<AssetDebtData> list) {
            this.list = list;
        }

        @Override
        public void run() {
            try {
                assetDebtDataSer.save(list);
            } catch (SerException e) {
                e.printStackTrace();
            }
        }
    }

    List<DebtBO> convertDebt(List<DebtBO> list) {
        List<DebtBO> bos = new ArrayList<>();
        DebtBO bo1 = new DebtBO();
        DebtBO bo2 = new DebtBO();
        DebtBO bo3 = new DebtBO();
        DebtBO bo4 = new DebtBO();
        DebtBO bo5 = new DebtBO();
        DebtBO bo6 = new DebtBO();
        DebtBO bo7 = new DebtBO();
        DebtBO bo8 = new DebtBO();
        DebtBO bo9 = new DebtBO();
        DebtBO bo10 = new DebtBO();
        DebtBO bo11 = new DebtBO();
        DebtBO bo12 = new DebtBO();
        DebtBO bo13 = new DebtBO();
        DebtBO bo14 = new DebtBO();
        DebtBO bo15 = new DebtBO();
        DebtBO bo16 = new DebtBO();
        DebtBO bo17 = new DebtBO();
        DebtBO bo18 = new DebtBO();
        DebtBO bo19 = new DebtBO();
        DebtBO bo20 = new DebtBO();
        DebtBO bo21 = new DebtBO();
        DebtBO bo22 = new DebtBO();
        DebtBO bo23 = new DebtBO();
        DebtBO bo24 = new DebtBO();
        DebtBO bo25 = new DebtBO();
        DebtBO bo26 = new DebtBO();
        DebtBO bo27 = new DebtBO();
        DebtBO bo28 = new DebtBO();
        DebtBO bo29 = new DebtBO();
        DebtBO bo30 = new DebtBO();
        DebtBO bo31 = new DebtBO();
        DebtBO bo32 = new DebtBO();
        DebtBO bo33 = new DebtBO();
        DebtBO bo34 = new DebtBO();
        DebtBO bo35 = new DebtBO();
        DebtBO bo36 = new DebtBO();
        DebtBO bo37 = new DebtBO();
        DebtBO bo38 = new DebtBO();
        int i = 1;
        for (DebtBO bo : list) {
            if (null != bo.getBeginDebt()) {
                bo.setBeginDebt(new BigDecimal(bo.getBeginDebt()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            if (null != bo.getEndDebt()) {
                bo.setEndDebt(new BigDecimal(bo.getEndDebt()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
            }

            if ("流动负债：".equals(bo.getDebt()) || "流动负债:".equals(bo.getDebt())) {
                bo1 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), null, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("短期借款".equals(bo.getDebt())) {
                bo2 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("应付票据".equals(bo.getDebt())) {
                bo3 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("应付账款".equals(bo.getDebt())) {
                bo4 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("预收账款".equals(bo.getDebt())) {
                bo5 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("应付职工薪酬".equals(bo.getDebt())) {
                bo6 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("应付利息".equals(bo.getDebt())) {
                bo7 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("应付股利".equals(bo.getDebt())) {
                bo8 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("应交税金".equals(bo.getDebt())) {
                bo9 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("其他应交款".equals(bo.getDebt())) {
                bo10 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("其他应付款".equals(bo.getDebt())) {
                bo11 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("预提费用".equals(bo.getDebt())) {
                bo12 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("预计负债".equals(bo.getDebt())) {
                bo13 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("一年内到期的长期负债".equals(bo.getDebt())) {
                bo14 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("其他流动负债".equals(bo.getDebt())) {
                bo15 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
                bo16 = new DebtBO();
            }
            if ("流动负债合计".equals(bo.getDebt())) {
                bo17 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("长期负债：".equals(bo.getDebt()) || "长期负债:".equals(bo.getDebt())) {
                bo18 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), null, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("长期借款".equals(bo.getDebt())) {
                bo19 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("应付债券".equals(bo.getDebt())) {
                bo20 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("长期应付款".equals(bo.getDebt())) {
                bo21 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("专项应付款".equals(bo.getDebt())) {
                bo22 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("其他长期负债".equals(bo.getDebt())) {
                bo23 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("长期负债合计".equals(bo.getDebt())) {
                bo24 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("递延税项：".equals(bo.getDebt()) || "递延税项:".equals(bo.getDebt())) {
                bo25 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), null, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("递延税款贷项".equals(bo.getDebt())) {
                bo26 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("负债合计".equals(bo.getDebt())) {
                bo27 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
                bo28 = new DebtBO();
            }
            if ("所有者权益(或股东权益)：".equals(bo.getDebt()) || "所有者权益(或股东权益):".equals(bo.getDebt())
                    || "所有者权益（或股东权益）：".equals(bo.getDebt()) || "所有者权益（或股东权益）:".equals(bo.getDebt())) {
                bo29 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), null, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("实收资本(或股本)".equals(bo.getDebt()) || "实收资本（或股本）".equals(bo.getDebt())) {
                bo30 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("减：已归还投资".equals(bo.getDebt()) || "减:已归还投资".equals(bo.getDebt())) {
                bo31 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("实收资本(或股本)净额".equals(bo.getDebt()) || "实收资本（或股本）净额".equals(bo.getDebt())) {
                bo32 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("资本公积".equals(bo.getDebt())) {
                bo33 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("盈余公积".equals(bo.getDebt())) {
                bo34 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("其中：法定公益金".equals(bo.getDebt()) || "其中:法定公益金".equals(bo.getDebt())) {
                bo35 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("未分配利润".equals(bo.getDebt())) {
                bo36 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("所有者权益(或股东权益)合计".equals(bo.getDebt()) || "所有者权益（或股东权益）合计".equals(bo.getDebt())) {
                bo37 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
            }
            if ("负债和所有者权益（或股东权益）总计".equals(bo.getDebt()) || "负债和所有者权益(或股东权益)总计".equals(bo.getDebt())) {
                bo38 = new DebtBO(bo.getId(), bo.getStartTime(), bo.getEndTime(), bo.getDebt(), bo.getDebtType(), bo.getType(), i++, bo.getBeginDebt(), bo.getCurrent(), bo.getEndDebt());
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

    @Override
    public List<StructureBO> debtStructure(DebtDTO dto) throws SerException {
        checkSeeIdentity();
        String userToken = RpcTransmit.getUserToken();
        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(dto, formulaDTO, "sorts");
        dto.getSorts().add("debtType=ASC");
        dto.getSorts().add("createTime=asc");
        List<Debt> list = super.findByCis(dto);
        List<StructureBO> boList = new ArrayList<StructureBO>();
        boolean b1 = true;
        boolean b2 = true;
        boolean b3 = true;
        double flowSum = 0;
        double longSum = 0;
        double allSum = 0;
        double currentSum = 0;
        double countCurrent = 0;       //负债和所有权益合计
        for (Debt debt : list) {
            List<FormulaBO> formulaBOs = formulaSer.findByFid(debt.getId(), formulaDTO);
            if (formulaBOs != null) {
                if (DebtType.BLONG.equals(debt.getDebtType()) && b1) {
                    flowSum = currentSum;
                    currentSum = 0;    //置为0
                    b1 = false;
                } else if (DebtType.CTAX.equals(debt.getDebtType()) && b2) {
                    longSum = currentSum;
                    currentSum = 0;    //置为0
                    b2 = false;
                } else if (DebtType.DALL.equals(debt.getDebtType()) && b3) {
                    currentSum = 0;    //置为0
                    b3 = false;
                }
                FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
                DebtBO bo = BeanTransform.copyProperties(debt, DebtBO.class);
//                资产期末数
//                bo.setCurrent(formulaBO.getCurrent());
                bo.setCurrent(formulaBO.getEnd());
                if (Type.ADD.equals(debt.getType())) {
                    currentSum += bo.getCurrent();
                    countCurrent += bo.getCurrent();
                } else if (Type.REMOVE.equals(debt.getType())) {
                    currentSum -= bo.getCurrent();
                    countCurrent -= bo.getCurrent();
                }
            }
        }
        allSum = currentSum;
        StructureBO flowBO = new StructureBO();
        flowBO.setProject("流动负债合计");
        flowBO.setFee(flowSum);
        String flow = "0";
        if (0d != countCurrent) {
            flow = String.format("%.2f", (flowSum / countCurrent) * 100);
        }
        flowBO.setScale(flow + "%");
        boList.add(flowBO);
        StructureBO longBO = new StructureBO();
        longBO.setProject("长期负债合计");
        longBO.setFee(longSum);
        String l = "0";
        if (0d != countCurrent) {
            l = String.format("%.2f", (longSum / countCurrent) * 100);
        }
        longBO.setScale(l + "%");
        boList.add(longBO);
        StructureBO debtBO = new StructureBO();
        debtBO.setProject("负债合计");
        debtBO.setFee(flowSum + longSum);
        String debt = "0";
        if (0d != countCurrent) {
            debt = String.format("%.2f", (debtBO.getFee() / countCurrent) * 100);
        }
        debtBO.setScale(debt + "%");
        boList.add(debtBO);
        StructureBO allBO = new StructureBO();
        allBO.setProject("所有者权益合计");
        allBO.setFee(allSum);
        String all = "0";
        if (0d != countCurrent) {
            all = String.format("%.2f", (allSum / countCurrent) * 100);
        }
        allBO.setScale(all + "%");
        boList.add(allBO);
        StructureBO sumBO = new StructureBO();
        sumBO.setProject("负债与所有者权益总计");
        sumBO.setFee(countCurrent);
        sumBO.setScale("100%");
        boList.add(sumBO);
        StructureBO rate = new StructureBO();
        rate.setFee(-1.00);
        rate.setScale("随便");
        rate.setProject("比例说明");
        rate.setBestScale("低负债资本、高权益资本可以降低企业财务风险，" +
                "减少企业发生债务危机的比率，但是会增加企业资本成本，不能有效发挥债务资本的财务杠杆效益。");
        boList.add(rate);
        RpcTransmit.transmitUserToken(userToken);
        String advice = debtStructureAdvice(flow, l, all);
        StructureBO adviceBO = new StructureBO();
        adviceBO.setProject("管理建议");
        adviceBO.setFee(-1.00);
        adviceBO.setScale("随便");
        adviceBO.setBestScale(advice);
        boList.add(adviceBO);
        return boList;
    }

    /**
     * 获取负债与权益结构管理建议
     *
     * @param flow
     * @param l
     * @param all
     * @return
     * @throws SerException
     */
    private String debtStructureAdvice(String flow, String l, String all) throws SerException {
        List<DebtStructureAdviceBO> advices = debtStructureAdviceSer.list(new DebtStructureAdviceDTO());
        String advice = null;
        if (advices != null && !advices.isEmpty()) {
            for (DebtStructureAdviceBO r : advices) {
                boolean b1 = Double.parseDouble(flow) >= r.getFlowMin() && Double.parseDouble(flow) <= r.getFlowMax();
                boolean b2 = Double.parseDouble(l) >= r.getLongMin() && Double.parseDouble(l) <= r.getLongMax();
                boolean b3 = Double.parseDouble(all) >= r.getAllMin() && Double.parseDouble(all) <= r.getAllMax();
                if (b1 && b2 && b3) {
                    advice = r.getAdvice();
                }
            }
        }
        return advice;
    }

    @Override
    public List<DetailBO> findDetails(String id, DebtDTO dto) throws SerException {
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
            Form form = Form.CREDIT;
            double currentSum = 0;
            String project = findByID(id).getDebt();
            String term = startTime + "~" + endTime;
            DetailBO currentBO = new DetailBO();
            currentBO.setProject(project);
            currentBO.setTerm(term);
            currentBO.setState("本期合计");
            currentBO.setForm(form);
            currentBO.setDebit(issueDebitAmount);
            currentBO.setCredit(issueCreditAmount);
            currentBO.setRemain(issueTotalAmount);
            currentBO.setRemain(currentSum);
//            if (Form.DEBIT.equals(form)) {
//                currentSum = begin + current;
//                currentBO.setDebit(current);
//            } else if (Form.CREDIT.equals(form)) {
//                currentSum = begin - current;
//                currentBO.setCredit(current);
//            }
            boList.add(currentBO);

            double year = currentSum;
            DetailBO beginBO = new DetailBO();
            beginBO.setProject(project);
            beginBO.setTerm(term);
            beginBO.setState("期初余额");
            beginBO.setForm(form);
            beginBO.setRemain(beginningCreditAmount);
            boList.add(beginBO);

            DetailBO yearBO = new DetailBO();
            yearBO.setTerm(term);
            yearBO.setState("本年累计");
            yearBO.setForm(form);
            yearBO.setDebit(endDebitAmount);
            yearBO.setCredit(endCreditAmount);
            yearBO.setRemain(endTotalAmount);
//            yearBO.setRemain(year);
            boList.add(yearBO);
        }
        return boList;
    }

    @Override
    public DebtBO findByID(String id) throws SerException {
        Debt entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, DebtBO.class);
    }

    @Override
    public Long count(DebtDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void edit(DebtTO to) throws SerException {
        checkAddIdentity();
        Debt entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, Debt.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        checkAddIdentity();
        Debt entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public List<DebtBO> list1(DebtDTO dto) throws SerException {
        checkSeeIdentity();
        List<Debt> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, DebtBO.class);
    }

    @Override
    public void assetDebtTask() throws SerException {
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

                DebtDTO dto = new DebtDTO();
                dto.setStartTime(start.toString());
                dto.setEndTime(end.toString());
                dto.setLastest(true);
                List<DebtBO> bos = this.list(dto);
//                List<ProfitBO> bos = new ArrayList<>();
//                bos.add(new ProfitBO("1", null,null,0.0,0.0,null,null,null));
                if (bos == null || bos.size() < 1) {
                    continue;
                }
                List<AssetDebtData> profitDataList = new ArrayList<>();
                for (DebtBO bo : bos) {
                    AssetDebtData data = new AssetDebtData();
                    data.setStartTime(start);
                    data.setEndTime(end);
                    data.setBeginAsset(bo.getBeginDebt() == null ? new BigDecimal(0.0) : new BigDecimal(bo.getBeginDebt()));
                    data.setEndAsset(bo.getEndDebt() == null ? new BigDecimal(0.0) : new BigDecimal(bo.getEndDebt()));
                    data.setNum(bo.getDebtNum());
                    data.setProject(bo.getDebt());
                    data.setProjectId(bo.getId());
                    data.setSystemId(null);   //公司id，预留字段 todo
                    if (data == null) {
                        continue;
                    }
                    profitDataList.add(data);
                }
                //保存到本模块
                if (profitDataList != null && profitDataList.size() > 0) {

                    assetDebtDataSer.save(profitDataList);
                }
            }
            year--;
            before = now.minusYears(year);
        }
    }
}