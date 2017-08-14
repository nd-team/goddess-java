package com.bjike.goddess.managefee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.managefee.bo.ManageFeeBO;
import com.bjike.goddess.managefee.dto.ManageFeeDTO;
import com.bjike.goddess.managefee.entity.ManageFee;
import com.bjike.goddess.managefee.excel.*;
import com.bjike.goddess.managefee.to.*;
import com.bjike.goddess.managefee.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理费业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:38 ]
 * @Description: [ 管理费业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managefeeSerCache")
@Service
public class ManageFeeSerImpl extends ServiceImpl<ManageFee, ManageFeeDTO> implements ManageFeeSer {

    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;


    @Override
    public Long countManageFee(ManageFeeDTO manageFeeDTO) throws SerException {
        Long count = super.count(manageFeeDTO);
        return count;
    }

    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        //财务模块权限
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是财务人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
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
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
            case IMPORT:
                flag = guideIdentity();
            case EXPORT:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public ManageFeeBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        ManageFee manageFee = super.findById(id);
        return BeanTransform.copyProperties(manageFee, ManageFeeBO.class);
    }

    @Override
    public List<ManageFeeBO> listManageFee(ManageFeeDTO manageFeeDTO) throws SerException {
        checkPermission();
        manageFeeDTO.getSorts().add("createTime=desc");
        List<ManageFee> list = super.findByCis(manageFeeDTO, true);

        return BeanTransform.copyProperties(list, ManageFeeBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public ManageFeeBO addManageFee(ManageFeeTO manageFeeTO) throws SerException {
        checkPermission();
        if (manageFeeTO.getTargetFee() == null) {
            throw new SerException("目标管理费不能为空");
        }
        int year = Integer.parseInt(manageFeeTO.getYear().trim());
        int month = Integer.parseInt(manageFeeTO.getMonth().trim());
        LocalDate startTime = LocalDate.of(year, month, 1);
        LocalDate endTime = startTime.with(TemporalAdjusters.lastDayOfMonth());
        VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO();
        voucherGenerateDTO.setArea(manageFeeTO.getArea());
        voucherGenerateDTO.setProjectName(manageFeeTO.getProject());
        voucherGenerateDTO.setProjectGroup(manageFeeTO.getProjectGroup());
        voucherGenerateDTO.setStartTime(startTime + "");
        voucherGenerateDTO.setEndTime(endTime + "");
        List<VoucherGenerateBO> voucherList = voucherGenerateAPI.listStatistic(voucherGenerateDTO, "manageFee");
        //记账凭证里面的费用

        Double money = 0d;
        if (voucherList != null && voucherList.size() > 0) {
            money = voucherList.stream().filter(str -> str.getBorrowMoney() != null).mapToDouble(VoucherGenerateBO::getBorrowMoney).sum();
        }

        ManageFee manageFee = BeanTransform.copyProperties(manageFeeTO, ManageFee.class, true);
        if (money == null && manageFeeTO.getActualFee() == null) {
            money = 0d;
        }
        manageFee.setActualFee(money);
        manageFee.setRate(manageFee.getActualFee() / manageFee.getTargetFee());
        manageFee.setBalance(manageFee.getActualFee() - manageFee.getTargetFee());
        manageFee.setCreateTime(LocalDateTime.now());
        super.save(manageFee);
        return BeanTransform.copyProperties(manageFee, ManageFeeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ManageFeeBO editManageFee(ManageFeeTO manageFeeTO) throws SerException {
        checkPermission();
        if (manageFeeTO.getTargetFee() == null) {
            throw new SerException("目标管理费不能为空");
        }
        if (manageFeeTO.getActualFee() == null) {
            throw new SerException("实际管理费不能为空");
        }

        ManageFee manageFee = BeanTransform.copyProperties(manageFeeTO, ManageFee.class, true);
        ManageFee cusLevel = super.findById(manageFeeTO.getId());

        BeanUtils.copyProperties(manageFee, cusLevel, "id", "createTime");
        cusLevel.setRate(cusLevel.getActualFee() / cusLevel.getTargetFee());
        cusLevel.setBalance(cusLevel.getActualFee() - cusLevel.getTargetFee());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, ManageFeeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteManageFee(String id) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }


    @Override
    public List<ManageFeeBO> collectAreaDetial(CollectAreaTO collectAreaTO) throws SerException {
        checkPermission();
        List<ManageFeeBO> returnList = new ArrayList<>();

        String startTime = collectAreaTO.getStartTime();
        String endTime = collectAreaTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"area", "year", "month", "project", "projectGroup", "type", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<ManageFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectAreaTO.getAreas() == null || collectAreaTO.getAreas().length <= 0) {
            list.add(new ManageFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectAreaTO.getAreas())).concat("'");
        sql.append("select a.area , a.year as year , a.month as month , a.project, a.projectGroup, a.type, a.targetFee as targetFee , a.actualFee as actualFee ,")
                .append("  (a.actualFee/a.targetFee) as rate , (a.actualFee-a.targetFee) as balance  from   ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_managefee as a)a ")
                .append(" where a.area in (" + condition + ") and a.date BETWEEN '" + startTime + "' AND '" + endTime + "'")
                .append("  order by a.area desc ,a.projectGroup desc ,a.project DESC,a.type desc , a.year desc , a.month desc ");
        list = super.findBySql(sql.toString(), ManageFeeBO.class, field);
        if (list != null && list.size() > 0) {
            String area = list.get(0).getArea();
            Double targetFee = 0d;
            Double actualFee = 0d;
            int index = -1;
            ManageFeeBO manageFeeBO = new ManageFeeBO();
            for (ManageFeeBO str : list) {
                index = index + 1;
                if (!area.equals(str.getArea())) {
                    String temp_area = str.getArea();
                    Double temp_target = str.getTargetFee();
                    Double temp_actual = str.getActualFee();

                    manageFeeBO = new ManageFeeBO();
                    manageFeeBO.setYear("");
                    manageFeeBO.setMonth("");
                    manageFeeBO.setArea(area);
                    manageFeeBO.setType("合计");
                    manageFeeBO.setActualFee(actualFee);
                    manageFeeBO.setTargetFee(targetFee);
                    manageFeeBO.setRate(  (double)Math.round(actualFee / targetFee *100*100)/100 );
                    manageFeeBO.setRatePersent( manageFeeBO.getRate()+"%" );
                    manageFeeBO.setBalance(new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(manageFeeBO);

                    //重置
                    area = temp_area;
                    targetFee = temp_target;
                    actualFee = temp_actual;

                    str.setRate(  (double)Math.round(str.getRate()*100*100)/100 );
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);

                } else {
                    targetFee = targetFee + str.getTargetFee();
                    actualFee = actualFee + str.getActualFee();

                    str.setRate(  (double)Math.round( str.getRate()*100 *100 )/100 );
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);
                }
                if (list.size() - 1 == index) {

                    manageFeeBO = new ManageFeeBO();
                    manageFeeBO.setYear("");
                    manageFeeBO.setMonth("");
                    manageFeeBO.setArea(str.getArea());
                    manageFeeBO.setType("合计");
                    manageFeeBO.setActualFee(actualFee);
                    manageFeeBO.setTargetFee(targetFee);
                    manageFeeBO.setRate(  (double)Math.round(actualFee / targetFee * 100 * 100)/100 );
                    manageFeeBO.setRatePersent( manageFeeBO.getRate()+"%" );
                    manageFeeBO.setBalance( new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(manageFeeBO);
                }
            }
        }
        return returnList;
    }


    @Override
    public List<ManageFeeBO> collectProjectDetail(CollectProjectTO collectProjectTO) throws SerException {
        checkPermission();
        List<ManageFeeBO> returnList = new ArrayList<>();
        String startTime = collectProjectTO.getStartTime();
        String endTime = collectProjectTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"project", "year", "month", "project", "projectGroup", "type", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<ManageFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectProjectTO.getProjectNames() != null || collectProjectTO.getProjectNames().length > 0) {
            list.add(new ManageFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectProjectTO.getProjectNames())).concat("'");
        sql.append("select a.project , a.year as year , a.month as month , a.project, a.projectGroup, a.type,a.targetFee as targetFee , a.actualFee as actualFee ,")
                .append("  (a.actualFee/a.targetFee) as rate , (a.actualFee-a.targetFee) as balance  from   ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_managefee as a)a ")
                .append(" where a.project in (" + condition + ") and a.date BETWEEN '" + startTime + "' AND '" + endTime + "'")
                .append("  order by a.project desc ,a.projectGroup desc ,a.area DESC,a.type desc , a.year desc , a.month desc ");
        list = super.findBySql(sql.toString(), ManageFeeBO.class, field);
        if (list != null && list.size() > 0) {
            String area = list.get(0).getProject();
            Double targetFee = 0d;
            Double actualFee = 0d;
            int index = -1;
            ManageFeeBO manageFeeBO = new ManageFeeBO();
            for (ManageFeeBO str : list) {
                index = index + 1;
                if (!area.equals(str.getProject())) {
                    String temp_area = str.getProject();
                    Double temp_target = str.getTargetFee();
                    Double temp_actual = str.getActualFee();

                    manageFeeBO = new ManageFeeBO();
                    manageFeeBO.setYear("");
                    manageFeeBO.setMonth("");
                    manageFeeBO.setProject(area);
                    manageFeeBO.setType("合计");
                    manageFeeBO.setActualFee(actualFee);
                    manageFeeBO.setTargetFee(targetFee);
                    manageFeeBO.setRate(  (double)Math.round(actualFee / targetFee * 100 * 100)/100  );
                    manageFeeBO.setRatePersent( manageFeeBO.getRate()+"%" );
                    manageFeeBO.setBalance(new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(manageFeeBO);

                    //重置
                    area = temp_area;
                    targetFee = temp_target;
                    actualFee = temp_actual;

                    str.setRate(  (double)Math.round(str.getRate()*100*100)/100 );
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);
                } else {
                    targetFee = targetFee + str.getTargetFee();
                    actualFee = actualFee + str.getActualFee();

                    str.setRate(  (double)Math.round(str.getRate()*100*100)/100 );
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);
                }
                if (list.size() - 1 == index) {
                    manageFeeBO = new ManageFeeBO();
                    manageFeeBO.setYear("");
                    manageFeeBO.setMonth("");
                    manageFeeBO.setProject(str.getProject());
                    manageFeeBO.setType("合计");
                    manageFeeBO.setActualFee(actualFee);
                    manageFeeBO.setTargetFee(targetFee);
                    manageFeeBO.setRate(  (double)Math.round(actualFee / targetFee * 100* 100)/100 );
                    manageFeeBO.setRatePersent( manageFeeBO.getRate()+"%" );
                    manageFeeBO.setBalance(new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(manageFeeBO);
                }
            }
        }
        return returnList;
    }

    @Override
    public List<ManageFeeBO> collectGroupDetail(CollectGroupTO collectGroupTO) throws SerException {
        checkPermission();
        List<ManageFeeBO> returnList = new ArrayList<>();
        String startTime = collectGroupTO.getStartTime();
        String endTime = collectGroupTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"projectGroup", "year", "month", "project", "area", "type", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<ManageFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectGroupTO.getProjectGroups() == null || collectGroupTO.getProjectGroups().length <= 0) {
            list.add(new ManageFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectGroupTO.getProjectGroups())).concat("'");
        sql.append("select a.projectGroup , a.year as year , a.month as month ,a.project,a.area,  a.type, a.targetFee as targetFee , a.actualFee as actualFee ,")
                .append("  (a.actualFee/a.targetFee) as rate , (a.actualFee-a.targetFee) as balance  from   ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_managefee as a)a ")
                .append(" where a.projectGroup in (" + condition + ") and a.date BETWEEN '" + startTime + "' AND '" + endTime + "'")
                .append("  order by a.projectGroup desc , a.area desc ,a.project DESC, a.type desc ,a.year desc , a.month desc ");
        list = super.findBySql(sql.toString(), ManageFeeBO.class, field);
        if (list != null && list.size() > 0) {
            String area = list.get(0).getProjectGroup();
            Double targetFee = 0d;
            Double actualFee = 0d;
            int index = -1;
            ManageFeeBO manageFeeBO = new ManageFeeBO();
            for (ManageFeeBO str : list) {
                index = index + 1;
                if (!area.equals(str.getProjectGroup())) {
                    String temp_area = str.getProjectGroup();
                    Double temp_target = str.getTargetFee();
                    Double temp_actual = str.getActualFee();

                    manageFeeBO = new ManageFeeBO();
                    manageFeeBO.setYear("");
                    manageFeeBO.setMonth("");
                    manageFeeBO.setProjectGroup(area);
                    manageFeeBO.setType("合计");
                    manageFeeBO.setActualFee(actualFee);
                    manageFeeBO.setTargetFee(targetFee);
                    manageFeeBO.setRate(  (double)Math.round(actualFee / targetFee * 100* 100)/100 );
                    manageFeeBO.setRatePersent( manageFeeBO.getRate()+"%" );
                    manageFeeBO.setBalance(new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(manageFeeBO);

                    //重置
                    area = temp_area;
                    targetFee = temp_target;
                    actualFee = temp_actual;

                    str.setRate(  (double)Math.round(str.getRate()*100*100)/100 );
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);
                } else {
                    targetFee = targetFee + str.getTargetFee();
                    actualFee = actualFee + str.getActualFee();
                    str.setRate(  (double)Math.round(str.getRate()*100*100)/100 );
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);
                }
                if (list.size() - 1 == index) {
                    manageFeeBO = new ManageFeeBO();
                    manageFeeBO.setYear("");
                    manageFeeBO.setMonth("");
                    manageFeeBO.setProjectGroup(str.getProjectGroup());
                    manageFeeBO.setType("合计");
                    manageFeeBO.setActualFee(actualFee);
                    manageFeeBO.setTargetFee(targetFee);
                    manageFeeBO.setRate(  (double)Math.round(actualFee / targetFee * 100* 100)/100 );
                    manageFeeBO.setRatePersent( manageFeeBO.getRate()+"%" );
                    manageFeeBO.setBalance(new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(manageFeeBO);
                }
            }
        }
        return returnList;
    }

    @Override
    public List<ManageFeeBO> collectTypeDetail(CollectCategoryTO collectCategoryTO) throws SerException {
        checkPermission();
        List<ManageFeeBO> returnList = new ArrayList<>();
        String startTime = collectCategoryTO.getStartTime();
        String endTime = collectCategoryTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"type", "year", "month", "project", "projectGroup", "area", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<ManageFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectCategoryTO.getTypes() == null || collectCategoryTO.getTypes().length <= 0) {
            list.add(new ManageFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectCategoryTO.getTypes())).concat("'");
        sql.append("select a.type , a.year as year , a.month as month , a.project, a.projectGroup, a.area,a.targetFee as targetFee , a.actualFee as actualFee ,")
                .append("  (a.actualFee/a.targetFee) as rate , (a.actualFee-a.targetFee) as balance  from  ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_managefee as a)a ")
                .append(" where a.type in (" + condition + ") and a.date BETWEEN '" + startTime + "' AND '" + endTime + "'")
                .append("  order by a.type desc , a.project DESC,a.projectGroup desc ,a.area desc ,  a.year desc , a.month desc ");
        list = super.findBySql(sql.toString(), ManageFeeBO.class, field);
        if (list != null && list.size() > 0) {
            String area = list.get(0).getType();
            Double targetFee = 0d;
            Double actualFee = 0d;
            int index = -1;
            ManageFeeBO manageFeeBO = new ManageFeeBO();
            for (ManageFeeBO str : list) {
                index = index + 1;
                if (!area.equals(str.getType())) {
                    String temp_area = str.getType();
                    Double temp_target = str.getTargetFee();
                    Double temp_actual = str.getActualFee();

                    manageFeeBO = new ManageFeeBO();
                    manageFeeBO.setYear("");
                    manageFeeBO.setMonth("");
                    manageFeeBO.setType(area);
                    manageFeeBO.setArea("合计");
                    manageFeeBO.setActualFee(actualFee);
                    manageFeeBO.setTargetFee(targetFee);
                    manageFeeBO.setRate(  (double)Math.round(actualFee / targetFee * 100* 100)/100 );
                    manageFeeBO.setRatePersent( manageFeeBO.getRate()+"%" );
                    manageFeeBO.setBalance(new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(manageFeeBO);

                    //重置
                    area = temp_area;
                    targetFee = temp_target;
                    actualFee = temp_actual;

                    str.setRate(  (double)Math.round(str.getRate()*100*100)/100 );
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);

                } else {
                    targetFee = targetFee + str.getTargetFee();
                    actualFee = actualFee + str.getActualFee();
                    str.setRate(  (double)Math.round(str.getRate()*100*100)/100 );
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);
                }
                if (list.size() - 1 == index) {

                    manageFeeBO = new ManageFeeBO();
                    manageFeeBO.setYear("");
                    manageFeeBO.setMonth("");
                    manageFeeBO.setType(str.getType());
                    manageFeeBO.setArea("合计");
                    manageFeeBO.setActualFee(actualFee);
                    manageFeeBO.setTargetFee(targetFee);
                    manageFeeBO.setRate(  (double)Math.round(actualFee / targetFee * 100* 100)/100 );
                    manageFeeBO.setRatePersent( manageFeeBO.getRate()+"%" );
                    manageFeeBO.setBalance(new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(manageFeeBO);
                }
            }
        }
        return returnList;
    }

    @Override
    public List<ManageFeeBO> collectArea(CollectAreaTO collectAreaTO) throws SerException {
        checkPermission();
        String startTime = collectAreaTO.getStartTime();
        String endTime = collectAreaTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"area", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<ManageFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)
                && collectAreaTO.getAreas() == null && collectAreaTO.getAreas().length <= 0) {
            list.add(new ManageFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectAreaTO.getAreas())).concat("'");
        sql.append("select a.area , sum(a.targetFee) as targetFee , sum(a.actualFee) as actualFee ,")
                .append("  (sum(a.actualFee)/sum(a.targetFee)) as rate , (sum(a.actualFee)-sum(a.targetFee)) as balance from   ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_managefee as a)a ")
                .append(" where a.area in (" + condition + ") and a.date BETWEEN '" + startTime + "' AND '" + endTime + "'")
                .append(" GROUP BY a.area order by a.area desc");
        list = super.findBySql(sql.toString(), ManageFeeBO.class, field);
        if (list != null && list.size() > 0) {
            list.stream().forEach(str -> {
                str.setYear(startTime + " 至 " + endTime);
                str.setRate(  (double)Math.round( str.getRate()*100*100 )/100);
                str.setRatePersent( str.getRate()+"%" );
            });

            ManageFeeBO total = new ManageFeeBO();
            total.setArea("合计");
            total.setTargetFee(list.stream().mapToDouble(ManageFeeBO::getTargetFee).sum());
            total.setActualFee(list.stream().mapToDouble(ManageFeeBO::getActualFee).sum());
            total.setRate( (double) Math.round( total.getActualFee() / total.getTargetFee() * 100 *100 )/100 );
            total.setRatePersent( total.getRate()+"%" );
            total.setBalance( new BigDecimal(total.getActualFee() - total.getTargetFee()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            list.add(total);
        }

        return list;
    }

    @Override
    public List<ManageFeeBO> collectGroup(CollectGroupTO collectGroupTO) throws SerException {
        checkPermission();

        String startTime = collectGroupTO.getStartTime();
        String endTime = collectGroupTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"projectGroup", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<ManageFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectGroupTO.getProjectGroups() == null || collectGroupTO.getProjectGroups().length <= 0) {
            list.add(new ManageFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectGroupTO.getProjectGroups())).concat("'");
        sql.append("select a.projectGroup , sum(a.targetFee) as targetFee , sum(a.actualFee) as actualFee ,")
                .append("  (sum(a.actualFee)/sum(a.targetFee)) as rate , (sum(a.actualFee)-sum(a.targetFee)) as balance from  ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_managefee as a)a ")
                .append(" where a.projectGroup in (" + condition + ") and a.date BETWEEN '" + startTime + "' AND '" + endTime + "'")
                .append(" GROUP BY a.projectGroup order by a.projectGroup desc");
        list = super.findBySql(sql.toString(), ManageFeeBO.class, field);
        if (list != null && list.size() > 0) {
            list.stream().forEach(str -> {
                str.setYear(startTime + " 至 " + endTime);
                str.setRate(  (double)Math.round( str.getRate()*100*100)/100 );
                str.setRatePersent( str.getRate()+"%" );
            });

            ManageFeeBO total = new ManageFeeBO();
            total.setArea("合计");
            total.setTargetFee(list.stream().mapToDouble(ManageFeeBO::getTargetFee).sum());
            total.setActualFee(list.stream().mapToDouble(ManageFeeBO::getActualFee).sum());
            total.setRate(  (double)Math.round(total.getActualFee() / total.getTargetFee() * 100* 100)/100 );
            total.setRatePersent( total.getRate()+"%" );
            total.setBalance( new BigDecimal(total.getActualFee() - total.getTargetFee()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            list.add(total);
        }

        return list;


    }


    @Override
    public List<ManageFeeBO> collectProject(CollectProjectTO collectProjectTO) throws SerException {
        checkPermission();
        String startTime = collectProjectTO.getStartTime();
        String endTime = collectProjectTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"project", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<ManageFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectProjectTO.getProjectNames() == null || collectProjectTO.getProjectNames().length <= 0) {
            list.add(new ManageFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectProjectTO.getProjectNames())).concat("'");
        sql.append("select a.project , sum(a.targetFee) as targetFee , sum(a.actualFee) as actualFee ,")
                .append("  (sum(a.actualFee)/sum(a.targetFee)) as rate , (sum(a.actualFee)-sum(a.targetFee)) as balance from  ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_managefee as a)a ")
                .append(" where a.project in (" + condition + ") and a.date BETWEEN '" + startTime + "' AND '" + endTime + "'")
                .append(" GROUP BY a.project order by a.project desc");
        list = super.findBySql(sql.toString(), ManageFeeBO.class, field);
        if (list != null && list.size() > 0) {
            list.stream().forEach(str -> {
                str.setYear(startTime + " 至 " + endTime);
                str.setRate(  (double)Math.round(str.getRate()*100*100)/100 );
                str.setRatePersent( str.getRate()+"%" );
            });

            ManageFeeBO total = new ManageFeeBO();
            total.setArea("合计");
            total.setTargetFee(list.stream().mapToDouble(ManageFeeBO::getTargetFee).sum());
            total.setActualFee(list.stream().mapToDouble(ManageFeeBO::getActualFee).sum());
            total.setRate(  (double)Math.round( total.getActualFee() / total.getTargetFee() * 100* 100)/100 );
            total.setRatePersent( total.getRate()+"%" );
            total.setBalance( new BigDecimal(total.getActualFee() - total.getTargetFee()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            list.add(total);
        }

        return list;


    }


    @Override
    public List<ManageFeeBO> collectType(CollectCategoryTO collectCategoryTO) throws SerException {
        checkPermission();

        String startTime = collectCategoryTO.getStartTime();
        String endTime = collectCategoryTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"type", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<ManageFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectCategoryTO.getTypes() == null || collectCategoryTO.getTypes().length <= 0) {
            list.add(new ManageFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectCategoryTO.getTypes())).concat("'");
        sql.append("select a.type , sum(a.targetFee) as targetFee , sum(a.actualFee) as actualFee ,")
                .append("  (sum(a.actualFee)/sum(a.targetFee)) as rate , (sum(a.actualFee)-sum(a.targetFee)) as balance from   ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_managefee as a)a ")
                .append(" where a.type in (" + condition + ") and a.date BETWEEN '" + startTime + "' AND '" + endTime + "'")
                .append(" GROUP BY a.type order by a.type desc");
        list = super.findBySql(sql.toString(), ManageFeeBO.class, field);
        if (list != null && list.size() > 0) {
            list.stream().forEach(str -> {
                str.setYear(startTime + " 至 " + endTime);
                str.setRate(  (double)Math.round( str.getRate()*100*100)/100 );
                str.setRatePersent( str.getRate()+"%" );
            });

            ManageFeeBO total = new ManageFeeBO();
            total.setArea("合计");
            total.setTargetFee(list.stream().mapToDouble(ManageFeeBO::getTargetFee).sum());
            total.setActualFee(list.stream().mapToDouble(ManageFeeBO::getActualFee).sum());
            total.setRate( (double)Math.round(total.getActualFee() / total.getTargetFee() * 100* 100)/100 );
            total.setRatePersent( total.getRate()+"%" );
            total.setBalance( new BigDecimal(total.getActualFee() - total.getTargetFee()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            list.add(total);
        }


        return list;

    }

    @Override
    public List<String> yearList() throws SerException {
        //获取所有年
        List<String> yearList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        int start = 1900;
        int end = now.getYear();
        String year = "";
        while (start <= end) {
            year = start + "";
            start = start + 1;
            yearList.add(year);
        }
        return yearList;
    }

    @Override
    public List<String> areaList() throws SerException {
        String[] field = new String[]{"area"};
        String sql = "select area   from managefee_managefee group by area ";
        List<ManageFee> manageFeeList = super.findBySql(sql, ManageFee.class, field);
        List<String> list = manageFeeList.stream().map(ManageFee::getArea).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<String> groupList() throws SerException {
        String[] field = new String[]{"projectGroup"};
        String sql = "select projectGroup  from managefee_managefee group by projectGroup ";
        List<ManageFee> manageFeeList = super.findBySql(sql, ManageFee.class, field);
        List<String> list = manageFeeList.stream().map(ManageFee::getProjectGroup).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<String> projectList() throws SerException {
        String[] field = new String[]{"project"};
        String sql = "select project  from managefee_managefee group by project ";
        List<ManageFee> manageFeeList = super.findBySql(sql, ManageFee.class, field);
        List<String> list = manageFeeList.stream().map(ManageFee::getProject).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<String> typeList() throws SerException {
        String[] field = new String[]{"type"};
        String sql = "select type  from managefee_managefee group by type ";
        List<ManageFee> manageFeeList = super.findBySql(sql, ManageFee.class, field);
        List<String> list = manageFeeList.stream().map(ManageFee::getType).collect(Collectors.toList());
        return list;
    }

    @Override
    public byte[] areaExportReport(CollectAreaTO collectAreaTO) throws SerException {
        XSSFWorkbook wb = new XSSFWorkbook();

        //表1
        List<ManageFeeBO> listCollect = collectArea(collectAreaTO);
        List<ManageFeeAreaExport> manageFeeAreaExports = new ArrayList<>();
        listCollect.stream().forEach(str -> {
            ManageFeeAreaExport excel = BeanTransform.copyProperties(str, ManageFeeAreaExport.class);
            manageFeeAreaExports.add(excel);
        });
        if (listCollect != null && listCollect.size() > 0) {
            ManageFeeAreaExport excel_time = new ManageFeeAreaExport();
            excel_time.setArea("时间：" + listCollect.get(0).getYear());
            manageFeeAreaExports.add(excel_time);
        }
        Excel excel = new Excel(0, 2);
        excel.setSheetName("地区汇总");
        wb = ExcelUtil.clazzToExcelAddMoreSheet(wb, manageFeeAreaExports, excel, 0);

        //表2
        List<ManageFeeBO> listCollectDetail = collectAreaDetial(collectAreaTO);
        List<ManageFeeAreaExportDetail> manageFeeAreaExportDetails = new ArrayList<>();
        listCollectDetail.stream().forEach(str -> {
            ManageFeeAreaExportDetail exportDetail = BeanTransform.copyProperties(str, ManageFeeAreaExportDetail.class);
            manageFeeAreaExportDetails.add(exportDetail);
        });
        Excel excel2 = new Excel(0, 2);
        excel2.setSheetName("地区汇总明细");
        wb = ExcelUtil.clazzToExcelAddMoreSheet(wb, manageFeeAreaExportDetails, excel2, 1);

        //输出流，结束流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return os.toByteArray();
    }

    @Override
    public byte[] projectExportReport(CollectProjectTO collectProjectTO) throws SerException {
        XSSFWorkbook wb = new XSSFWorkbook();

        //表1
        List<ManageFeeBO> listCollect = collectProject(collectProjectTO);
        List<ManageFeeProjectExport> manageFeeProjectExports = new ArrayList<>();
        listCollect.stream().forEach(str -> {
            ManageFeeProjectExport excel = BeanTransform.copyProperties(str, ManageFeeProjectExport.class);
            manageFeeProjectExports.add(excel);
        });
        if (listCollect != null && listCollect.size() > 0) {
            ManageFeeProjectExport excel_time = new ManageFeeProjectExport();
            excel_time.setProject("时间：" + listCollect.get(0).getYear());
            manageFeeProjectExports.add(excel_time);
        }
        Excel excel = new Excel(0, 2);
        excel.setSheetName("项目名称汇总");
        wb = ExcelUtil.clazzToExcelAddMoreSheet(wb, manageFeeProjectExports, excel, 0);

        //表2
        List<ManageFeeBO> listCollectDetail = collectProjectDetail(collectProjectTO);
        List<ManageFeeProjectExportDetail> manageFeeProjectExportDetails = new ArrayList<>();
        listCollectDetail.stream().forEach(str -> {
            ManageFeeProjectExportDetail exportDetail = BeanTransform.copyProperties(str, ManageFeeProjectExportDetail.class);
            manageFeeProjectExportDetails.add(exportDetail);
        });
        Excel excel2 = new Excel(0, 2);
        excel2.setSheetName("项目名称汇总明细");
        wb = ExcelUtil.clazzToExcelAddMoreSheet(wb, manageFeeProjectExportDetails, excel2, 1);

        //输出流，结束流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return os.toByteArray();
    }

    @Override
    public byte[] groupExportReport(CollectGroupTO collectProjectTO) throws SerException {
        XSSFWorkbook wb = new XSSFWorkbook();

        //表1
        List<ManageFeeBO> listCollect = collectGroup(collectProjectTO);
        List<ManageFeeGroupExport> manageFeeGroupExports = new ArrayList<>();
        listCollect.stream().forEach(str -> {
            ManageFeeGroupExport excel = BeanTransform.copyProperties(str, ManageFeeGroupExport.class);
            manageFeeGroupExports.add(excel);
        });
        if (listCollect != null && listCollect.size() > 0) {
            ManageFeeGroupExport excel_time = new ManageFeeGroupExport();
            excel_time.setProjectGroup("时间：" + listCollect.get(0).getYear());
            manageFeeGroupExports.add(excel_time);
        }
        Excel excel = new Excel(0, 2);
        excel.setSheetName("项目组汇总");
        wb = ExcelUtil.clazzToExcelAddMoreSheet(wb, manageFeeGroupExports, excel, 0);

        //表2
        List<ManageFeeBO> listCollectDetail = collectGroupDetail(collectProjectTO);
        List<ManageFeeGroupExportDetail> manageFeeGroupExportDetails = new ArrayList<>();
        listCollectDetail.stream().forEach(str -> {
            ManageFeeGroupExportDetail exportDetail = BeanTransform.copyProperties(str, ManageFeeGroupExportDetail.class);
            manageFeeGroupExportDetails.add(exportDetail);
        });
        Excel excel2 = new Excel(0, 2);
        excel2.setSheetName("项目组汇总明细");
        wb = ExcelUtil.clazzToExcelAddMoreSheet(wb, manageFeeGroupExportDetails, excel2, 1);

        //输出流，结束流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return os.toByteArray();
    }

    @Override
    public byte[] typeExportReport(CollectCategoryTO collectCategoryTO) throws SerException {
        XSSFWorkbook wb = new XSSFWorkbook();

        //表1
        List<ManageFeeBO> listCollect = collectType(collectCategoryTO);
        List<ManageFeeTypeExport> manageFeeTypeExports = new ArrayList<>();
        listCollect.stream().forEach(str -> {
            ManageFeeTypeExport excel = BeanTransform.copyProperties(str, ManageFeeTypeExport.class);
            manageFeeTypeExports.add(excel);
        });
        if (listCollect != null && listCollect.size() > 0) {
            ManageFeeTypeExport excel_time = new ManageFeeTypeExport();
            excel_time.setType("时间：" + listCollect.get(0).getYear());
            manageFeeTypeExports.add(excel_time);
        }
        Excel excel = new Excel(0, 2);
        excel.setSheetName("类别汇总");
        wb = ExcelUtil.clazzToExcelAddMoreSheet(wb, manageFeeTypeExports, excel, 0);

        //表2
        List<ManageFeeBO> listCollectDetail = collectTypeDetail(collectCategoryTO);
        List<ManageFeeTypeExportDetail> manageFeeTypeExportDetails = new ArrayList<>();
        listCollectDetail.stream().forEach(str -> {
            ManageFeeTypeExportDetail exportDetail = BeanTransform.copyProperties(str, ManageFeeTypeExportDetail.class);
            manageFeeTypeExportDetails.add(exportDetail);
        });
        Excel excel2 = new Excel(0, 2);
        excel2.setSheetName("类别汇总明细");
        wb = ExcelUtil.clazzToExcelAddMoreSheet(wb, manageFeeTypeExportDetails, excel2, 1);

        //输出流，结束流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return os.toByteArray();
    }

    @Override
    public ManageFeeBO importExcel(List<ManageFeeTO> manageFeeTOS) throws SerException {
        List<ManageFee> list = BeanTransform.copyProperties(manageFeeTOS, ManageFee.class);
        list.stream().forEach(str->{
            str.setRate( str.getActualFee() / str.getTargetFee());
            str.setBalance( str.getActualFee() - str.getTargetFee());
        });
        super.save(list);

        return new ManageFeeBO();
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<ManageFeeImport> manageFeeImports = new ArrayList<>();
        ManageFeeImport temp1 = new ManageFeeImport();
        temp1.setArea("湖南");
        temp1.setProject("临时项目1");
        temp1.setProjectGroup("临时项目组1");
        temp1.setType("临时类别1");
        temp1.setYear("2017");
        temp1.setMonth("8");
        temp1.setTargetFee( 100d );
        temp1.setActualFee( 20d );
        manageFeeImports.add( temp1 );

        temp1 = new ManageFeeImport();
        temp1.setArea("湖南");
        temp1.setProject("临时项目2");
        temp1.setProjectGroup("临时项目组2");
        temp1.setType("临时类别2");
        temp1.setYear("2017");
        temp1.setMonth("8");
        temp1.setTargetFee( 55d );
        temp1.setActualFee( 11d );
        manageFeeImports.add( temp1 );
        Excel excel2 = new Excel(0, 2);
        excel2.setSheetName("管理费用导入模板");
        byte[] bytes = ExcelUtil.clazzToExcel(manageFeeImports, excel2);

        return bytes;
    }
}