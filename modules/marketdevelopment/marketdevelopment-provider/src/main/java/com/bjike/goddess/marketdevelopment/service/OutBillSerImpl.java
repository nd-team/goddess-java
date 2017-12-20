package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.marketdevelopment.bo.InformationBO;
import com.bjike.goddess.marketdevelopment.bo.OutBillBO;
import com.bjike.goddess.marketdevelopment.dto.OutBillDTO;
import com.bjike.goddess.marketdevelopment.dto.PlanDayDTO;
import com.bjike.goddess.marketdevelopment.entity.OutBill;
import com.bjike.goddess.marketdevelopment.entity.PlanDay;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.OutBillTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 外出单业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 11:12 ]
 * @Description: [ 外出单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class OutBillSerImpl extends ServiceImpl<OutBill, OutBillDTO> implements OutBillSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MarPermissionSer marPermissionSer;
    @Autowired
    private PlanDaySer planDaySer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String planManage = "plan-manage";

    private static final String planCheck = "plan-check";


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = marPermissionSer.getMarPermission(marketCheck);
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = marPermissionSer.getMarPermission(marketManage);
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
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
            flag = marPermissionSer.getMarPermission(marketCheck);
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
            flag = marPermissionSer.getMarPermission(marketManage);
        } else {
            flag = true;
        }
        return flag;
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
    public List<OutBillBO> maps(OutBillDTO dto) throws SerException {
        dto.getSorts().add("num=asc");
        dto.getSorts().add("billNum=asc");
        List<OutBill> outBills = super.findByPage(dto);
        List<OutBillBO> bos = BeanTransform.copyProperties(outBills, OutBillBO.class, false);
        return bos;
    }

    @Override
    public void save(OutBillTO to) throws SerException {
        OutBill entity = BeanTransform.copyProperties(to, OutBill.class, true);
        List<OutBill> outBills = super.findAll();
        int num = outBills.size();
        entity.setNum(num);
        String time = DateUtil.dateToString(LocalDate.now());
        String billTime = time.substring(0, 4) + "年-" + time.substring(time.indexOf("-") + 1, time.lastIndexOf("-")) + "月-";
        entity.setBillNum("swbwcd-" + billTime + to.getBillNum());
        super.save(entity);
    }

    @Override
    public void update(OutBillTO to) throws SerException {
        OutBill entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void delete(OutBillTO to) throws SerException {
        OutBill entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        super.remove(entity);
    }

    @Override
    public OutBillBO getById(String id) throws SerException {
        OutBill entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        OutBillBO bo = BeanTransform.copyProperties(entity, OutBillBO.class, false);
        return bo;
    }

    @Override
    public Long getTotal(OutBillDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public List<String> findName() throws SerException {
        List<PlanDay> planDays = planDaySer.findAll();
        if (null != planDays && planDays.size() > 0) {
            List<String> list = planDays.stream().map(PlanDay::getName).distinct().collect(Collectors.toList());
            return list;
        }
        return null;
    }

    @Override
    public InformationBO findDataByName(String name) throws SerException {
        PlanDayDTO planDayDTO = new PlanDayDTO();
        planDayDTO.getConditions().add(Restrict.eq("name", name));
        List<PlanDay> planDays = planDaySer.findByCis(planDayDTO);
        if (null != planDays && planDays.size() > 0) {
            PlanDay planDay = planDays.get(0);
            InformationBO bo = BeanTransform.copyProperties(planDay, InformationBO.class);
            return bo;
        }
        return null;
    }

    private void searchCondition(OutBillDTO dto) throws SerException {

    }
}