package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.fundcheck.bo.*;
import com.bjike.goddess.fundcheck.dto.PayStockDTO;
import com.bjike.goddess.fundcheck.dto.StockMoneyDTO;
import com.bjike.goddess.fundcheck.entity.OtherSpend;
import com.bjike.goddess.fundcheck.entity.PayStock;
import com.bjike.goddess.fundcheck.enums.GuideAddrStatus;
import com.bjike.goddess.fundcheck.excel.PayStockTemplateExcel;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.OtherIncomeTO;
import com.bjike.goddess.fundcheck.to.PayStockCollectTO;
import com.bjike.goddess.fundcheck.to.PayStockTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 支付给股东业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:55 ]
 * @Description: [ 支付给股东业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "fundcheckSerCache")
@Service
public class PayStockSerImpl extends ServiceImpl<PayStock, PayStockDTO> implements PayStockSer {
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
    public Long count(PayStockDTO payStockDTO) throws SerException {
        Long count = super.count(payStockDTO);
        return count;
    }

    @Override
    public PayStockBO getOne(String id) throws SerException {
        PayStock payStock = super.findById(id);
        return BeanTransform.copyProperties(payStock,PayStockBO.class);
    }

    @Override
    public List<PayStockBO> findListBack(PayStockDTO payStockDTO) throws SerException {
        checkSeeIdentity();
        payStockDTO.getSorts().add("createTime=desc");
        List<PayStock> payStocks = super.findByPage(payStockDTO);
        List<PayStockBO> payStockBOS = BeanTransform.copyProperties(payStocks,PayStockBO.class);
        return payStockBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public PayStockBO insert(PayStockTO payStockTO) throws SerException {
        checkAddIdentity();
        PayStock payStock = BeanTransform.copyProperties(payStockTO,PayStock.class,true);
        payStock.setCreateTime(LocalDateTime.now());
        super.save(payStock);
        return BeanTransform.copyProperties(payStock,PayStockBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public PayStockBO edit(PayStockTO payStockTO) throws SerException {
        checkAddIdentity();
        PayStock payStock = super.findById(payStockTO.getId());
        BeanTransform.copyProperties(payStock,PayStockBO.class);
        payStock.setModifyTime(LocalDateTime.now());
        super.update(payStock);
        return BeanTransform.copyProperties(payStock,PayStockBO.class);

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
    public LinkedHashMap<String, String> collect(PayStockCollectTO to) throws SerException {
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        String sql = "select stockName from fundcheck_paystock where " +
                "date BETWEEN '" + startTime + "' and '" + endTime + "' GROUP BY stockName";
        List<Object> titles = super.findBySql(sql);
        for(Object o: titles){
            if(NumberUtils.isCreatable(String.valueOf(o))){
                throw new SerException("类型不能为数字");
            }
        }
        String stockName =null;
        if (null != titles && titles.size() > 0) {
            StringBuilder sb = new StringBuilder("SELECT * from (SELECT");
            for (Object o : titles) {
                stockName = String.valueOf(o);
                sb.append(" max(CASE WHEN stockName='" + stockName + "' THEN money  else NULL end )AS " + stockName + ",");
            }
            sb.append(" sum(money) as '合计' ");
            sb.append(" FROM ");
            sb.append(" (SELECT sum(money)as money,stockName  from fundcheck_paystock a  WHERE a.date BETWEEN ");
            sb.append("  '" + startTime + "' AND '" + endTime + "' ");
            sb.append("GROUP BY stockName) a)a where '"+stockName+"' IS NOT NULL");
            sql = sb.toString();
            titles.add("合计");
            map.put("日期",startTime+"-"+endTime);
            List<Object> values= super.findBySql(sql);
            if (null != values && values.size() > 0) {
                Object[] obj = (Object[]) values.get(0);
                for(int i=0;i<obj.length;i++){
                    map.put(String.valueOf(titles.get(i)),String.valueOf(obj[i]));
                }
            }
        }
        return  map;

    }
    @Override
    public PayStockBO importExcel(List<PayStockTO> payStockTOS) throws SerException {
        List<PayStock> payStocks = BeanTransform.copyProperties(payStockTOS, PayStock.class, true);
        super.save(payStocks);

        PayStockBO bo = BeanTransform.copyProperties(new PayStock(), PayStockBO.class);
        return bo;
    }
    @Override
    public byte[] templateExport() throws SerException {
        List<PayStockTemplateExcel> templateExcels = new ArrayList<>();

        PayStockTemplateExcel excel = new PayStockTemplateExcel();
        excel.setDate(LocalDate.now());
        excel.setStockName("test");
        excel.setMoney(10.0d);
        templateExcels.add(excel);

        Excel exce = new Excel(0,2);
        byte[] bytes = ExcelUtil.clazzToExcel(templateExcels,exce);
        return bytes;
    }
}