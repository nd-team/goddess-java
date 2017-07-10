package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.fundcheck.bo.OtherIncomeBO;
import com.bjike.goddess.fundcheck.dto.OtherIncomeDTO;
import com.bjike.goddess.fundcheck.entity.OtherIncome;
import com.bjike.goddess.fundcheck.enums.GuideAddrStatus;
import com.bjike.goddess.fundcheck.excel.OtherIncomeExcel;
import com.bjike.goddess.fundcheck.excel.OtherIncomeTemplateExcel;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.OtherIncomeCollectTO;
import com.bjike.goddess.fundcheck.to.OtherIncomeTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 其他收入业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:53 ]
 * @Description: [ 其他收入业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "fundcheckSerCache")
@Service
public class OtherIncomeSerImpl extends ServiceImpl<OtherIncome, OtherIncomeDTO> implements OtherIncomeSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;

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
    public Long count(OtherIncomeDTO otherIncomeDTO) throws SerException {
        Long count = super.count(otherIncomeDTO);
        return count;
    }

    @Override
    public OtherIncomeBO getOne(String id) throws SerException {
        OtherIncome otherIncome = super.findById(id);
        return BeanTransform.copyProperties(otherIncome, OtherIncomeBO.class);
    }

    @Override
    public List<OtherIncomeBO> findList(OtherIncomeDTO otherIncomeDTO) throws SerException {
        checkSeeIdentity();
        otherIncomeDTO.getSorts().add("createTime=desc");
        List<OtherIncome> otherIncomes = super.findByPage(otherIncomeDTO);
        List<OtherIncomeBO> otherIncomeBOS = BeanTransform.copyProperties(otherIncomes, OtherIncomeBO.class);
        return otherIncomeBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OtherIncomeBO insert(OtherIncomeTO otherIncomeTO) throws SerException {
        checkAddIdentity();
        OtherIncome otherIncome = BeanTransform.copyProperties(otherIncomeTO, OtherIncome.class,true);
        otherIncome.setCreateTime(LocalDateTime.now());
        super.save(otherIncome);
        return BeanTransform.copyProperties(otherIncome, OtherIncomeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OtherIncomeBO edit(OtherIncomeTO otherIncomeTO) throws SerException {
        checkAddIdentity();
        OtherIncome otherIncome = super.findById(otherIncomeTO.getId());
        BeanTransform.copyProperties(otherIncomeTO, otherIncome, true);
        otherIncome.setModifyTime(LocalDateTime.now());
        super.update(otherIncome);
        return BeanTransform.copyProperties(otherIncome, OtherIncomeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public List<OtherIncomeBO> collect(OtherIncomeCollectTO to) throws SerException {
        List<OtherIncomeBO> otherIncomeBOList = new ArrayList<>();
        OtherIncomeDTO dto = new OtherIncomeDTO();
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            String[] condi = new String[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("date", condi));
        }
        //获取所有类型(科目)
        List<String> typeList = new ArrayList<>();
        dto.getSorts().add("type=desc");
        String sql = "SELECT type FROM fundcheck_otherincome WHERE date BETWEEN '" + startTime + "' AND '" + endTime + "' GROUP BY type ";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            typeList.addAll((List) objects);
        }
        //获取所有类型(科目)对应的金额
        List<Double> moneyList = new ArrayList<>();
        sql = "SELECT money FROM fundcheck_otherincome WHERE date BETWEEN '" + startTime + "' AND '" + endTime + "' GROUP BY money;";
        List<Object> objectList = super.findBySql(sql);
        if (null != objectList && objectList.size() > 0) {
            moneyList.addAll((List) objectList);
        }
        //获取金额合计
        String[] fields = new String[]{"money"};
        sql = "select sum(money) AS money from  fundcheck_stockmoney where date between '" + startTime + "' and '" + endTime + "' ";
        List<OtherIncomeBO> stockMoneyBOS = super.findBySql(sql, OtherIncomeBO.class, fields);
        Double money = stockMoneyBOS.stream().filter(str -> null != str.getMoney()).mapToDouble(OtherIncomeBO::getMoney).sum();

        OtherIncomeBO otherIncomeBO = new OtherIncomeBO();
        otherIncomeBO.setDate(startTime + "-" + endTime);
        otherIncomeBO.setTypeList(typeList);
        otherIncomeBO.setMoneyList(moneyList);
        otherIncomeBO.setMoney(money);
        otherIncomeBOList.add(otherIncomeBO);
        return otherIncomeBOList;
    }
    @Override
    public List<String> listFirstSubject() throws SerException {
        List<String> firstSubject = voucherGenerateAPI.listFirstSubject();
        return firstSubject;
    }

    @Override
    public List<String> listSubByFirst(String firstSub) throws SerException {
        List<String> secondSubject = voucherGenerateAPI.listSubByFirst(firstSub);
        return secondSubject;
    }

    @Override
    public List<String> listTubByFirst(String firstSub, String secondSub) throws SerException {
        List<String> thirdSubject = voucherGenerateAPI.listTubByFirst(firstSub, secondSub);
        return thirdSubject;
    }

    @Override
    public OtherIncomeBO importExcel(List<OtherIncomeTO> otherIncomeTOS) throws SerException {
        List<OtherIncome> otherIncomes = BeanTransform.copyProperties(otherIncomeTOS, OtherIncome.class, true);
        super.save(otherIncomes);

        OtherIncomeBO bo = BeanTransform.copyProperties(new OtherIncome(), OtherIncomeBO.class);
        return bo;
    }
    @Override
    public byte[] templateExport() throws SerException {
        List<OtherIncomeTemplateExcel> otherIncomeExcels = new ArrayList<>();

        OtherIncomeTemplateExcel excel = new OtherIncomeTemplateExcel();
        excel.setDate(LocalDate.now());
        excel.setType("test");
        excel.setMoney(10.0d);
        otherIncomeExcels.add(excel);

        Excel excels = new Excel(0,2);
        byte[] bytes = ExcelUtil.clazzToExcel(otherIncomeExcels,excels);
        return bytes;
    }

}