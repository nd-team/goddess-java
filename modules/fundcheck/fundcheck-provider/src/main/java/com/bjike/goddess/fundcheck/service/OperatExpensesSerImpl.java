package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.fundcheck.bo.OperatExpensesBO;
import com.bjike.goddess.fundcheck.dto.OperatExpensesDTO;
import com.bjike.goddess.fundcheck.entity.OperatExpenses;
import com.bjike.goddess.fundcheck.enums.GuideAddrStatus;
import com.bjike.goddess.fundcheck.excel.OperatExpensesTemplateExcel;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.OperatExpensesCollectTO;
import com.bjike.goddess.fundcheck.to.OperatExpensesTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 营业费用业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:54 ]
 * @Description: [ 营业费用业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "fundcheckSerCache")
@Service
public class OperatExpensesSerImpl extends ServiceImpl<OperatExpenses, OperatExpensesDTO> implements OperatExpensesSer {
    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;
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
    public Long count(OperatExpensesDTO operatExpensesDTO) throws SerException {
        Long count = super.count(operatExpensesDTO);
        return count;
    }

    @Override
    public OperatExpensesBO getOne(String id) throws SerException {

        OperatExpenses operatExpenses = super.findById(id);
        return BeanTransform.copyProperties(operatExpenses, OperatExpensesBO.class);
    }

    @Override
    public List<OperatExpensesBO> findList(OperatExpensesDTO operatExpensesDTO) throws SerException {
        checkSeeIdentity();
        operatExpensesDTO.getSorts().add("createTime=desc");
        operatExpensesDTO.getSorts().add("modifyTime=desc");
        List<OperatExpenses> operatExpenses = super.findByPage(operatExpensesDTO);
        List<OperatExpensesBO> operatExpensesBOS = BeanTransform.copyProperties(operatExpenses, OperatExpensesBO.class);
        return operatExpensesBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OperatExpensesBO insert(OperatExpensesTO operatExpensesTO) throws SerException {
        checkAddIdentity();
        OperatExpenses operatExpenses = BeanTransform.copyProperties(operatExpensesTO, OperatExpenses.class, true);
        operatExpenses.setCreateTime(LocalDateTime.now());
        super.save(operatExpenses);
        return BeanTransform.copyProperties(operatExpenses, OperatExpensesBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OperatExpensesBO edit(OperatExpensesTO operatExpensesTO) throws SerException {
        checkAddIdentity();
        OperatExpenses operatExpenses = super.findById(operatExpensesTO.getId());
        BeanTransform.copyProperties(operatExpensesTO, operatExpenses, true);
        operatExpenses.setModifyTime(LocalDateTime.now());
        super.update(operatExpenses);
        return BeanTransform.copyProperties(operatExpenses, OperatExpensesBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public LinkedHashMap<String, String> collect(OperatExpensesCollectTO to) throws SerException {
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        String sql = "select type from fundcheck_operatexpenses where " +
                "date BETWEEN '" + startTime + "' and '" + endTime + "' GROUP BY type";
        List<Object> titles = super.findBySql(sql);
        for(Object o: titles){
            if(NumberUtils.isCreatable(String.valueOf(o))){
                throw new SerException("类型不能为数字");
            }
        }
        String type = null;
        if (null != titles && titles.size() > 0) {
            StringBuilder sb = new StringBuilder("SELECT * from (SELECT");
            for (Object o : titles) {
                type = String.valueOf(o);
                sb.append(" max(CASE WHEN type='" + type + "' THEN money  else NULL end )AS " + type + ",");
            }
            sb.append(" sum(money) as '合计' ");
            sb.append(" FROM ");
            sb.append(" (SELECT sum(money)as money,type  from fundcheck_operatexpenses a  WHERE a.date BETWEEN ");
            sb.append("  '" + startTime + "' AND '" + endTime + "' ");
            sb.append("GROUP BY type) a)a where '" + type + "' IS NOT NULL");
            sql = sb.toString();
            titles.add("合计");
            map.put("日期",startTime+"-"+endTime);
            List<Object> values = super.findBySql(sql);
            if (null != values && values.size() > 0) {
                Object[] obj = (Object[]) values.get(0);
                for (int i = 0; i < obj.length; i++) {
                    map.put(String.valueOf(titles.get(i)), String.valueOf(obj[i]));
                }
            }
        }
        return map;

    }

    public static void main(String[] args) {
        System.out.println(NumberUtils.isCreatable("fa"));
    }

    @Override
    public List<String> listType() throws SerException {
        List<String> type = voucherGenerateAPI.listFirstSubject();
        return type;
    }

    @Override
    public OperatExpensesBO importExcel(List<OperatExpensesTO> operatExpensesTOS) throws SerException {
        List<OperatExpenses> operatExpenses = BeanTransform.copyProperties(operatExpensesTOS, OperatExpenses.class, true);
        super.save(operatExpenses);

        OperatExpensesBO bo = BeanTransform.copyProperties(new OperatExpenses(), OperatExpensesBO.class);
        return bo;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<OperatExpensesTemplateExcel> templateExcels = new ArrayList<>();

        OperatExpensesTemplateExcel excel = new OperatExpensesTemplateExcel();
        excel.setDate(LocalDate.now());
        excel.setType("test");
        excel.setMoney(10.0d);
        templateExcels.add(excel);

        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(templateExcels, exce);
        return bytes;
    }
}