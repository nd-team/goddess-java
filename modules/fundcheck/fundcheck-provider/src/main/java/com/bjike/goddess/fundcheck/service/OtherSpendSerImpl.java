package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.fundcheck.bo.OtherSpendBO;
import com.bjike.goddess.fundcheck.dto.OtherSpendDTO;
import com.bjike.goddess.fundcheck.entity.OtherSpend;
import com.bjike.goddess.fundcheck.enums.GuideAddrStatus;
import com.bjike.goddess.fundcheck.excel.OtherIncomeTemplateExcel;
import com.bjike.goddess.fundcheck.excel.OtherSpendTemplateExcel;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.OtherSpendCollectTO;
import com.bjike.goddess.fundcheck.to.OtherSpendTO;
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
 * 其他支出业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:57 ]
 * @Description: [ 其他支出业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "fundcheckSerCache")
@Service
public class OtherSpendSerImpl extends ServiceImpl<OtherSpend, OtherSpendDTO> implements OtherSpendSer {
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
    public Long count(OtherSpendDTO otherSpendDTO) throws SerException {
        Long count = super.count(otherSpendDTO);
        return count;
    }

    @Override
    public OtherSpendBO getOne(String id) throws SerException {
        OtherSpend otherSpend = super.findById(id);
        return BeanTransform.copyProperties(otherSpend,OtherSpendBO.class);
    }

    @Override
    public List<OtherSpendBO> findListBack(OtherSpendDTO otherSpendDTO) throws SerException {
        checkSeeIdentity();
        otherSpendDTO.getSorts().add("createTime=desc");
        List<OtherSpend> otherSpends = super.findByPage(otherSpendDTO);
        List<OtherSpendBO> otherSpendBOS = BeanTransform.copyProperties(otherSpends,OtherSpendBO.class);
        return otherSpendBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OtherSpendBO insert(OtherSpendTO otherSpendTO) throws SerException {
        checkAddIdentity();
        OtherSpend otherSpend = BeanTransform.copyProperties(otherSpendTO,OtherSpend.class,true);
        otherSpend.setCreateTime(LocalDateTime.now());
        super.save(otherSpend);
        return BeanTransform.copyProperties(otherSpend,OtherSpendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OtherSpendBO edit(OtherSpendTO otherSpendTO) throws SerException {
        checkAddIdentity();
        OtherSpend otherSpend = super.findById(otherSpendTO.getId());
        BeanTransform.copyProperties(otherSpendTO,otherSpend,true);
        otherSpend.setModifyTime(LocalDateTime.now());
        super.update(otherSpend);
        return BeanTransform.copyProperties(otherSpend,OtherSpendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
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
    public List<OtherSpendBO> collect(OtherSpendCollectTO to) throws SerException {
        List<OtherSpendBO> otherSpendBOList = new ArrayList<>();
        OtherSpendDTO dto = new OtherSpendDTO();
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            String[] condi = new String[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("date", condi));
        }
        //获取所有类型(科目)
        List<String> typeList = new ArrayList<>();
        dto.getSorts().add("type=desc");
        String sql = "SELECT type FROM fundcheck_otherspend WHERE date BETWEEN '"+startTime+"' AND '"+endTime+"' GROUP BY type ";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            typeList.addAll((List) objects);
        }
        //获取所有类型(科目)对应的金额
        List<Double> moneyList = new ArrayList<>();
        sql = "SELECT money FROM fundcheck_otherspend WHERE date BETWEEN '"+startTime+"' AND '"+endTime+"' GROUP BY money;";
        List<Object> objectList = super.findBySql(sql);
        if(null != objectList && objectList.size() > 0){
            moneyList.addAll((List) objectList);
        }
        //获取金额合计
        String[] fields = new String[]{"money"};
        sql = "select sum(money) AS money from  fundcheck_otherspend where date between '" + startTime + "' and '" + endTime + "' ";
        List<OtherSpendBO> otherSpendBOS = super.findBySql(sql, OtherSpendBO.class, fields);
        Double money = otherSpendBOS.stream().filter(str -> null != str.getMoney()).mapToDouble(OtherSpendBO::getMoney).sum();

        OtherSpendBO otherSpendBO = new OtherSpendBO();
        otherSpendBO.setDate(startTime+"-"+endTime);
        otherSpendBO.setTypeList(typeList);
        otherSpendBO.setMoneyList(moneyList);
        otherSpendBO.setMoney(money);
        otherSpendBOList.add(otherSpendBO);
        return otherSpendBOList;
    }
    @Override
    public OtherSpendBO importExcel(List<OtherSpendTO> otherSpendTOS) throws SerException {
        List<OtherSpend> otherSpends = BeanTransform.copyProperties(otherSpendTOS, OtherSpend.class, true);
        super.save(otherSpends);

        OtherSpendBO bo = BeanTransform.copyProperties(new OtherSpend(), OtherSpendBO.class);
        return bo;
    }
    @Override
    public byte[] templateExport() throws SerException {
        List<OtherSpendTemplateExcel> templateExcels = new ArrayList<>();

        OtherSpendTemplateExcel excel = new OtherSpendTemplateExcel();
        excel.setDate(LocalDate.now());
        excel.setType("test");
        excel.setMoney(10.0d);
        templateExcels.add(excel);

        Excel exce = new Excel(0,2);
        byte[] bytes = ExcelUtil.clazzToExcel(templateExcels,exce);
        return bytes;
    }
}