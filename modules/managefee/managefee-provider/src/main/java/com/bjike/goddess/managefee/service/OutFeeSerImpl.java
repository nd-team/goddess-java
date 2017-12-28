package com.bjike.goddess.managefee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.managefee.bo.ManageFeeBO;
import com.bjike.goddess.managefee.bo.OutFeeBO;
import com.bjike.goddess.managefee.bo.OutFeeBO;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.entity.OutFee;
import com.bjike.goddess.managefee.entity.OutFee;
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
 * 外包费业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:39 ]
 * @Description: [ 外包费业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managefeeSerCache")
@Service
public class OutFeeSerImpl extends ServiceImpl<OutFee, OutFeeDTO> implements OutFeeSer {

    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private ManageFeeSer manageFeeSer;

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
            throw new SerException("您不是财务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 导航检查权限
     *
     * @throws SerException
     */
    private Boolean guildPermission() throws SerException {
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagoutfee = guildPermission();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("outfeeserimpl");
        obj.setDescribesion("外部费用管理");
        if (flagoutfee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAppMulM = manageFeeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("managefeeser");
        obj.setDescribesion("内部管理费用");
        if (flagAppMulM) {
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
                flag = guildPermission();
                break;
            case ADD:
                flag = guildPermission();
                break;
            case EDIT:
                flag = guildPermission();
                break;
            case DELETE:
                flag = guildPermission();
                break;
            case COLLECT:
                flag = guildPermission();
                break;
            case IMPORT:
                flag = guildPermission();
            case EXPORT:
                flag = guildPermission();
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countOutFee(OutFeeDTO outFeeDTO) throws SerException {
        Long count = super.count(outFeeDTO);
        return count;
    }

    @Override
    public OutFeeBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        OutFee outFee = super.findById(id);
        return BeanTransform.copyProperties(outFee, OutFeeBO.class);
    }

    @Override
    public List<OutFeeBO> listOutFee(OutFeeDTO outFeeDTO) throws SerException {
        checkPermission();
        outFeeDTO.getSorts().add("createTime=desc");
        List<OutFee> list = super.findByCis(outFeeDTO, true);

        List<OutFeeBO> listBO =BeanTransform.copyProperties(list, OutFeeBO.class);
        if( listBO!= null && listBO.size()>0 ){
            listBO.stream().forEach(str->{
                str.setTargetFee( new BigDecimal(str.getTargetFee() ).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                str.setActualFee( new BigDecimal(str.getActualFee() ).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                str.setRate(  (double)Math.round(str.getActualFee() / str.getTargetFee() *100*100)/100  );
                str.setRatePersent( str.getRate()+"%" );
                str.setBalance( new BigDecimal(str.getActualFee() - str.getTargetFee()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            });
        }

        return listBO;
    }



    @Transactional(rollbackFor = SerException.class)
    @Override
    public OutFeeBO addOutFee(OutFeeTO outFeeTO) throws SerException {
        checkPermission();
        if (outFeeTO.getTargetFee() == null) {
            throw new SerException("目标管理费不能为空");
        }
        int year = Integer.parseInt(outFeeTO.getYear().trim());
        int month = Integer.parseInt(outFeeTO.getMonth().trim());
        LocalDate startTime = LocalDate.of(year, month, 1);
        LocalDate endTime = startTime.with(TemporalAdjusters.lastDayOfMonth());
        VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO();
        voucherGenerateDTO.setArea(outFeeTO.getArea());
        voucherGenerateDTO.setProjectName(outFeeTO.getProject());
        voucherGenerateDTO.setProjectGroup(outFeeTO.getProjectGroup());
        voucherGenerateDTO.setStartTime(startTime + "");
        voucherGenerateDTO.setEndTime(endTime + "");
        List<VoucherGenerateBO> voucherList = voucherGenerateAPI.listStatistic(voucherGenerateDTO, "outFee");
        //TODO:这里是关联记账凭证里面的费用
        Double money = 0d;
        if (voucherList != null && voucherList.size() > 0) {
            money = voucherList.stream().filter(str -> str.getBorrowMoney() != null).mapToDouble(VoucherGenerateBO::getBorrowMoney).sum();
        }else{
            if (  outFeeTO.getActualFee() == null) {
                money = 0d;
            }else{
                money = outFeeTO.getActualFee();
            }
        }

        OutFee outFee = BeanTransform.copyProperties(outFeeTO, OutFee.class, true);

        outFee.setActualFee(money);
        outFee.setRate(outFee.getActualFee() / outFee.getTargetFee());
        outFee.setBalance(outFee.getActualFee() - outFee.getTargetFee());
        outFee.setCreateTime(LocalDateTime.now());
        super.save(outFee);
        return BeanTransform.copyProperties(outFee, OutFeeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OutFeeBO editOutFee(OutFeeTO outFeeTO) throws SerException {
        checkPermission();
        if (outFeeTO.getTargetFee() == null) {
            throw new SerException("目标管理费不能为空");
        }
        if (outFeeTO.getActualFee() == null) {
            throw new SerException("实际管理费不能为空");
        }

        OutFee outFee = BeanTransform.copyProperties(outFeeTO, OutFee.class, true);
        OutFee cusLevel = super.findById(outFeeTO.getId());

        BeanUtils.copyProperties(outFee, cusLevel, "id", "createTime");
        cusLevel.setRate(cusLevel.getActualFee() / cusLevel.getTargetFee());
        cusLevel.setBalance(cusLevel.getActualFee() - cusLevel.getTargetFee());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, OutFeeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteOutFee(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<OutFeeBO> collectAreaDetial(CollectAreaTO collectAreaTO) throws SerException {
        checkPermission();

        List<OutFeeBO> returnList = new ArrayList<>();
        String startTime = collectAreaTO.getStartTime();
        String endTime = collectAreaTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"area", "year", "month","project","projectGroup","type", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<OutFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectAreaTO.getAreas() == null || collectAreaTO.getAreas().length <= 0) {
            list.add(new OutFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectAreaTO.getAreas())).concat("'");
        sql.append("select a.area , a.year as year , a.month as month , a.project, a.projectGroup, a.type,a.targetFee as targetFee , a.actualFee as actualFee ,")
                .append("  (a.actualFee/a.targetFee) as rate , (a.actualFee-a.targetFee) as balance  from  ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_outfee as a)a ")
                .append(" where a.area in (" + condition + ") and a.date BETWEEN '"+startTime+"' AND '"+endTime+"'")
                .append("  order by a.area desc , a.projectGroup desc ,a.project DESC,a.type desc ,a.year desc , a.month desc ");
        list = super.findBySql(sql.toString(), OutFeeBO.class, field);
        if (list != null && list.size() > 0) {
            String area = list.get(0).getArea();
            Double targetFee = 0d;
            Double actualFee = 0d;
            int index = -1;
            OutFeeBO outFeeBO = new OutFeeBO();
            for (OutFeeBO str : list) {
                index = index + 1;
                if (!area.equals(str.getArea())) {
                    String temp_area = str.getArea();
                    Double temp_target = str.getTargetFee();
                    Double temp_actual = str.getActualFee();

                    outFeeBO = new OutFeeBO();
                    outFeeBO.setYear("");
                    outFeeBO.setMonth("");
                    outFeeBO.setArea( area );
                    outFeeBO.setType("合计");
                    outFeeBO.setActualFee(actualFee);
                    outFeeBO.setTargetFee(targetFee);
                    outFeeBO.setRate( (double)Math.round( actualFee / targetFee* 100 * 100)/100);
                    outFeeBO.setRatePersent( outFeeBO.getRate()+"%" );
                    outFeeBO.setBalance( new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(outFeeBO);

                    //重置
                    area = temp_area;
                    targetFee = temp_target;
                    actualFee = temp_actual;

                    str.setRate( (double)Math.round( str.getActualFee() / str.getTargetFee()* 100 * 100)/100);
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);
                } else {
                    targetFee = targetFee + str.getTargetFee();
                    actualFee = actualFee + str.getActualFee();

                    str.setRate( (double)Math.round( str.getActualFee() / str.getTargetFee()* 100 * 100)/100);
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);
                }
                if (list.size() - 1 == index) {

                    outFeeBO = new OutFeeBO();
                    outFeeBO.setYear("");
                    outFeeBO.setMonth("");
                    outFeeBO.setArea( str.getArea() );
                    outFeeBO.setType("合计");
                    outFeeBO.setActualFee(actualFee);
                    outFeeBO.setTargetFee(targetFee);
                    outFeeBO.setRate((double)Math.round( actualFee / targetFee* 100 * 100)/100);
                    outFeeBO.setRatePersent( outFeeBO.getRate()+"%" );
                    outFeeBO.setBalance( new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(outFeeBO);
                }
            }
        }
        return returnList;
    }

    @Override
    public List<OutFeeBO> collectGroupDetail(CollectGroupTO collectGroupTO) throws SerException {

        List<OutFeeBO> returnList = new ArrayList<>();
        String startTime = collectGroupTO.getStartTime();
        String endTime = collectGroupTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"projectGroup", "year", "month","project","area","type", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<OutFeeBO> list = new ArrayList<>();
         if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectGroupTO.getProjectGroups() == null || collectGroupTO.getProjectGroups().length <= 0) {
             list.add(new OutFeeBO());
             return list;
         }
        String condition = "'".concat(String.join("','", collectGroupTO.getProjectGroups())).concat("'");
        sql.append("select a.projectGroup , a.year as year , a.month as month , a.project, a.area, a.type, a.targetFee as targetFee , a.actualFee as actualFee ,")
                .append("  (a.actualFee/a.targetFee) as rate , (a.actualFee-a.targetFee) as balance  from  ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_outfee as a)a ")
                .append(" where a.projectGroup in (" + condition + ") and a.date BETWEEN '"+startTime+"' AND '"+endTime+"'")
                .append("  order by a.projectGroup desc ,a.area desc ,a.project DESC,a.type desc , a.year desc , a.month desc ");
        list = super.findBySql(sql.toString(), OutFeeBO.class, field);
        if (list != null && list.size() > 0) {
            String area = list.get(0).getProjectGroup();
            Double targetFee = 0d;
            Double actualFee = 0d;
            int index = -1;
            OutFeeBO outFeeBO = new OutFeeBO();
            for (OutFeeBO str : list) {
                index = index + 1;
                if (!area.equals(str.getProjectGroup())) {
                    String temp_area = str.getProjectGroup();
                    Double temp_target = str.getTargetFee();
                    Double temp_actual = str.getActualFee();

                    outFeeBO = new OutFeeBO();
                    outFeeBO.setYear("");
                    outFeeBO.setMonth("");
                    outFeeBO.setProjectGroup( area );
                    outFeeBO.setType("合计");
                    outFeeBO.setActualFee(actualFee);
                    outFeeBO.setTargetFee(targetFee);
                    outFeeBO.setRate((double)Math.round( actualFee / targetFee* 100 * 100)/100);
                    outFeeBO.setRatePersent( outFeeBO.getRate()+"%" );
                    outFeeBO.setBalance( new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(outFeeBO);

                    //重置
                    area = temp_area;
                    targetFee = temp_target;
                    actualFee = temp_actual;

                    str.setRate( (double)Math.round( str.getActualFee() / str.getTargetFee()* 100 * 100)/100);
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);
                } else {
                    targetFee = targetFee + str.getTargetFee();
                    actualFee = actualFee + str.getActualFee();

                    str.setRate( (double)Math.round( str.getActualFee() / str.getTargetFee()* 100 * 100)/100);
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);
                }
                if (list.size() - 1 == index) {
                    outFeeBO = new OutFeeBO();
                    outFeeBO.setYear("");
                    outFeeBO.setMonth("");
                    outFeeBO.setProjectGroup( str.getProjectGroup() );
                    outFeeBO.setType("合计");
                    outFeeBO.setActualFee(actualFee);
                    outFeeBO.setTargetFee(targetFee);
                    outFeeBO.setRate((double)Math.round( actualFee / targetFee* 100 * 100)/100);
                    outFeeBO.setRatePersent( outFeeBO.getRate()+"%" );
                    outFeeBO.setBalance( new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(outFeeBO);
                }
            }
        }
        return returnList;
    }

    @Override
    public List<OutFeeBO> collectProjectDetail(CollectProjectTO collectProjectTO) throws SerException {

        List<OutFeeBO> returnList = new ArrayList<>();

        String startTime = collectProjectTO.getStartTime();
        String endTime = collectProjectTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"project", "year", "month","area","projectGroup","type", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<OutFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectProjectTO.getProjectNames() == null || collectProjectTO.getProjectNames().length <= 0) {
            list.add(new OutFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectProjectTO.getProjectNames())).concat("'");
        sql.append("select a.project , a.year as year , a.month as month , a.area, a.projectGroup, a.type, a.targetFee as targetFee , a.actualFee as actualFee ,")
                .append("  (a.actualFee/a.targetFee) as rate , (a.actualFee-a.targetFee) as balance  from  ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_outfee as a)a ")
                .append(" where a.project in (" + condition + ") and a.date BETWEEN '"+startTime+"' AND '"+endTime+"'")
                .append("  order by a.project desc , a.projectGroup desc ,a.area DESC,a.type desc ,a.year desc , a.month desc ");
        list = super.findBySql(sql.toString(), OutFeeBO.class, field);
        if (list != null && list.size() > 0) {
            String area = list.get(0).getProject();
            Double targetFee = 0d;
            Double actualFee = 0d;
            int index = -1;
            OutFeeBO outFeeBO = new OutFeeBO();
            for (OutFeeBO str : list) {
                index = index + 1;
                if (!area.equals(str.getProject())) {
                    String temp_area = str.getProject();
                    Double temp_target = str.getTargetFee();
                    Double temp_actual = str.getActualFee();

                    outFeeBO = new OutFeeBO();
                    outFeeBO.setYear("");
                    outFeeBO.setMonth("");
                    outFeeBO.setProject( area );
                    outFeeBO.setType("合计");
                    outFeeBO.setActualFee(actualFee);
                    outFeeBO.setTargetFee(targetFee);
                    outFeeBO.setRate((double)Math.round( actualFee / targetFee* 100 * 100)/100);
                    outFeeBO.setRatePersent( outFeeBO.getRate()+"%" );
                    outFeeBO.setBalance( new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(outFeeBO);

                    //重置
                    area = temp_area;
                    targetFee = temp_target;
                    actualFee = temp_actual;

                    str.setRate( (double)Math.round( str.getActualFee() / str.getTargetFee()* 100 * 100)/100);
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);
                } else {
                    targetFee = targetFee + str.getTargetFee();
                    actualFee = actualFee + str.getActualFee();

                    str.setRate( (double)Math.round( str.getActualFee() / str.getTargetFee()* 100 * 100)/100);
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);
                }
                if (list.size() - 1 == index) {
                    outFeeBO = new OutFeeBO();
                    outFeeBO.setYear("");
                    outFeeBO.setMonth("");
                    outFeeBO.setProject( str.getProject() );
                    outFeeBO.setType("合计");
                    outFeeBO.setActualFee(actualFee);
                    outFeeBO.setTargetFee(targetFee);
                    outFeeBO.setRate((double)Math.round( actualFee / targetFee* 100 * 100)/100);
                    outFeeBO.setRatePersent( outFeeBO.getRate()+"%" );
                    outFeeBO.setBalance( new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(outFeeBO);
                }
            }
        }
        return returnList;
    }

    @Override
    public List<OutFeeBO> collectTypeDetail(CollectCategoryTO collectCategoryTO) throws SerException {

        List<OutFeeBO> returnList = new ArrayList<>();
        String startTime = collectCategoryTO.getStartTime();
        String endTime = collectCategoryTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"type", "year", "month","project","projectGroup","area", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<OutFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectCategoryTO.getTypes() == null || collectCategoryTO.getTypes().length <= 0) {
            list.add(new OutFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectCategoryTO.getTypes())).concat("'");
        sql.append("select a.type , a.year as year , a.month as month , a.project, a.projectGroup, a.area, a.targetFee as targetFee , a.actualFee as actualFee ,")
                .append("  (a.actualFee/a.targetFee) as rate , (a.actualFee-a.targetFee) as balance  from  ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_outfee as a)a ")
                .append(" where a.type in (" + condition + ") and a.date BETWEEN '"+startTime+"' AND '"+endTime+"'")
                .append("  order by a.type desc ,a.project DESC,a.projectGroup desc , a.area desc ,a.year desc , a.month desc ");
        list = super.findBySql(sql.toString(), OutFeeBO.class, field);
        if (list != null && list.size() > 0) {
            String area = list.get(0).getType();
            Double targetFee = 0d;
            Double actualFee = 0d;
            int index = -1;

            OutFeeBO outFeeBO = new OutFeeBO();
            for (OutFeeBO str : list) {
                index = index + 1;
                if (!area.equals(str.getType())) {
                    String temp_area = str.getType();
                    Double temp_target = str.getTargetFee();
                    Double temp_actual = str.getActualFee();

                    outFeeBO = new OutFeeBO();
                    outFeeBO.setYear("");
                    outFeeBO.setMonth("");
                    outFeeBO.setType( area );
                    outFeeBO.setArea("合计");
                    outFeeBO.setActualFee(actualFee);
                    outFeeBO.setTargetFee(targetFee);
                    outFeeBO.setRate((double)Math.round( actualFee / targetFee* 100 * 100)/100);
                    outFeeBO.setRatePersent( outFeeBO.getRate()+"%" );
                    outFeeBO.setBalance( new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(outFeeBO);

                    //重置
                    area = temp_area;
                    targetFee = temp_target;
                    actualFee = temp_actual;

                    str.setRate( (double)Math.round( str.getActualFee() / str.getTargetFee()* 100 * 100)/100);
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);
                } else {
                    targetFee = targetFee + str.getTargetFee();
                    actualFee = actualFee + str.getActualFee();

                    str.setRate( (double)Math.round( str.getActualFee() / str.getTargetFee()* 100 * 100)/100);
                    str.setRatePersent( str.getRate()+"%" );
                    returnList.add(str);
                }
                if (list.size() - 1 == index) {

                    outFeeBO = new OutFeeBO();
                    outFeeBO.setYear("");
                    outFeeBO.setMonth("");
                    outFeeBO.setType( str.getType() );
                    outFeeBO.setArea("合计");
                    outFeeBO.setActualFee(actualFee);
                    outFeeBO.setTargetFee(targetFee);
                    outFeeBO.setRate((double)Math.round( actualFee / targetFee* 100 * 100)/100);
                    outFeeBO.setRatePersent( outFeeBO.getRate()+"%" );
                    outFeeBO.setBalance( new BigDecimal(actualFee - targetFee).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    returnList.add(outFeeBO);
                }
            }
        }
        return returnList;
    }

    @Override
    public List<OutFeeBO> collectArea(CollectAreaTO collectAreaTO) throws SerException {
        checkPermission();
        String startTime = collectAreaTO.getStartTime();
        String endTime = collectAreaTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"area", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<OutFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectAreaTO.getAreas() == null || collectAreaTO.getAreas().length <= 0) {
            list.add(new OutFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectAreaTO.getAreas())).concat("'");
        sql.append("select a.area , sum(a.targetFee) as targetFee , sum(a.actualFee) as actualFee ,")
                .append("  (sum(a.actualFee)/sum(a.targetFee)) as rate , (sum(a.actualFee)-sum(a.targetFee)) as balance from   ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_outfee as a)a ")
                .append(" where a.area in (" + condition + ") and a.date BETWEEN '"+startTime+"' AND '"+endTime+"'")
                .append(" GROUP BY a.area order by a.area desc");
        list = super.findBySql(sql.toString(), OutFeeBO.class, field);
        if (list != null && list.size() > 0) {
            list.stream().forEach(str -> {
                str.setYear(startTime + " 至 " + endTime);
                str.setRate( (double)Math.round( str.getActualFee()/str.getTargetFee()* 100 * 100)/100 );
                str.setRatePersent( str.getRate()+"%" );
            });

            OutFeeBO total = new OutFeeBO();
            total.setArea("合计");
            total.setTargetFee( new BigDecimal( list.stream().mapToDouble(OutFeeBO::getTargetFee).sum()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            total.setActualFee(new BigDecimal( list.stream().mapToDouble(OutFeeBO::getActualFee).sum() ).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            total.setRate( (double)Math.round( total.getActualFee()/total.getTargetFee()* 100 * 100)/100 );
            total.setRatePersent( total.getRate()+"%" );
            total.setBalance(  new BigDecimal(total.getActualFee() - total.getTargetFee()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            list.add( total );
        }


        return list;
    }

    @Override
    public List<OutFeeBO> collectGroup(CollectGroupTO collectGroupTO) throws SerException {
        checkPermission();
        String startTime = collectGroupTO.getStartTime();
        String endTime = collectGroupTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"projectGroup", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<OutFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectGroupTO.getProjectGroups() == null || collectGroupTO.getProjectGroups().length <= 0) {
            list.add(new OutFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectGroupTO.getProjectGroups())).concat("'");
        sql.append("select a.projectGroup , sum(a.targetFee) as targetFee , sum(a.actualFee) as actualFee ,")
                .append("  (sum(a.actualFee)/sum(a.targetFee)) as rate , (sum(a.actualFee)-sum(a.targetFee)) as balance from   ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_outfee as a)a ")
                .append(" where a.projectGroup in (" + condition + ") and a.date BETWEEN '"+startTime+"' AND '"+endTime+"'")
                .append(" GROUP BY a.projectGroup order by a.projectGroup desc");
        list = super.findBySql(sql.toString(), OutFeeBO.class, field);
        if (list != null && list.size() > 0) {
            list.stream().forEach(str -> {
                str.setYear( startTime + " 至 " + endTime );
                str.setRate( (double)Math.round( str.getActualFee()/str.getTargetFee()* 100 * 100)/100 );
                str.setRatePersent( str.getRate()+"%" );
            });

            OutFeeBO total = new OutFeeBO();
            total.setProjectGroup("合计");
            total.setTargetFee( new BigDecimal( list.stream().mapToDouble(OutFeeBO::getTargetFee).sum()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            total.setActualFee(new BigDecimal( list.stream().mapToDouble(OutFeeBO::getActualFee).sum() ).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            total.setRate( (double)Math.round( total.getActualFee()/total.getTargetFee()* 100 * 100)/100 );
            total.setRatePersent( total.getRate()+"%" );
            total.setBalance(  new BigDecimal(total.getActualFee() - total.getTargetFee()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            list.add( total );
        }

        return list;
    }

    @Override
    public List<OutFeeBO> collectProject(CollectProjectTO collectProjectTO) throws SerException {
        checkPermission();
        String startTime = collectProjectTO.getStartTime();
        String endTime = collectProjectTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"project", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<OutFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectProjectTO.getProjectNames() == null || collectProjectTO.getProjectNames().length <= 0) {
            list.add(new OutFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectProjectTO.getProjectNames())).concat("'");
        sql.append("select a.project , sum(a.targetFee) as targetFee , sum(a.actualFee) as actualFee ,")
                .append("  (sum(a.actualFee)/sum(a.targetFee)) as rate , (sum(a.actualFee)-sum(a.targetFee)) as balance from   ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_outfee as a)a ")
                .append(" where a.project in (" + condition + ") and a.date BETWEEN '"+startTime+"' AND '"+endTime+"'")
                .append(" GROUP BY a.project order by a.project desc");
        list = super.findBySql(sql.toString(), OutFeeBO.class, field);
        if (list != null && list.size() > 0) {
            list.stream().forEach(str -> {
                str.setYear( startTime + " 至 " + endTime );
                str.setRate( (double)Math.round( str.getActualFee()/str.getTargetFee()* 100 * 100)/100 );
                str.setRatePersent( str.getRate()+"%" );
            });

            OutFeeBO total = new OutFeeBO();
            total.setProject("合计");
            total.setTargetFee( new BigDecimal( list.stream().mapToDouble(OutFeeBO::getTargetFee).sum()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            total.setActualFee(new BigDecimal( list.stream().mapToDouble(OutFeeBO::getActualFee).sum() ).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            total.setRate( (double)Math.round( total.getActualFee()/total.getTargetFee()* 100 * 100)/100 );
            total.setRatePersent( total.getRate()+"%" );
            total.setBalance(  new BigDecimal(total.getActualFee() - total.getTargetFee()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            list.add( total );
        }

        return list;
    }


    @Override
    public List<OutFeeBO> collectType(CollectCategoryTO collectCategoryTO) throws SerException {
        checkPermission();

        String startTime = collectCategoryTO.getStartTime();
        String endTime = collectCategoryTO.getEndTime();

        //全部填，一进入汇总页面合计所有
        //汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"type", "targetFee", "actualFee", "rate", "balance"};
        StringBuffer sql = new StringBuffer("");
        List<OutFeeBO> list = new ArrayList<>();
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
                || collectCategoryTO.getTypes() == null || collectCategoryTO.getTypes().length <= 0) {
            list.add(new OutFeeBO());
            return list;
        }
        String condition = "'".concat(String.join("','", collectCategoryTO.getTypes())).concat("'");
        sql.append("select a.type , sum(a.targetFee) as targetFee , sum(a.actualFee) as actualFee ,")
                .append("  (sum(a.actualFee)/sum(a.targetFee)) as rate , (sum(a.actualFee)-sum(a.targetFee)) as balance from  ")
                .append(" (select CONCAT(a.year,'-',if(a.month<=9,CONCAT('0',a.month),a.month))as date,a.*  from managefee_outfee as a)a ")
                .append(" where a.type in (" + condition + ") and a.date BETWEEN '"+startTime+"' AND '"+endTime+"'")
                .append(" GROUP BY a.type order by a.type desc");
        list = super.findBySql(sql.toString(), OutFeeBO.class, field);
        if (list != null && list.size() > 0) {
            list.stream().forEach(str -> {
                str.setYear( startTime + " 至 " + endTime );
                str.setRate( (double)Math.round( str.getActualFee()/str.getTargetFee()* 100 * 100)/100 );
                str.setRatePersent( str.getRate()+"%" );
            });

            OutFeeBO total = new OutFeeBO();
            total.setType("合计");
            total.setTargetFee( new BigDecimal( list.stream().mapToDouble(OutFeeBO::getTargetFee).sum()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            total.setActualFee(new BigDecimal( list.stream().mapToDouble(OutFeeBO::getActualFee).sum() ).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            total.setRate( (double)Math.round( total.getActualFee()/total.getTargetFee()* 100 * 100)/100 );
            total.setRatePersent( total.getRate()+"%" );
            total.setBalance(  new BigDecimal(total.getActualFee() - total.getTargetFee()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
            list.add( total );
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
        String sql = "select area  from managefee_outfee group by area ";
        List<OutFee> manageFeeList = super.findBySql(sql, OutFee.class, field);
        List<String> list = manageFeeList.stream().map(OutFee::getArea).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<String> groupList() throws SerException {
        String[] field = new String[]{"projectGroup"};
        String sql = "select projectGroup  from managefee_outfee group by projectGroup ";
        List<OutFee> manageFeeList = super.findBySql(sql, OutFee.class, field);
        List<String> list = manageFeeList.stream().map(OutFee::getProjectGroup).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<String> projectList() throws SerException {
        String[] field = new String[]{"project"};
        String sql = "select project  from managefee_outfee group by project ";
        List<OutFee> manageFeeList = super.findBySql(sql, OutFee.class, field);
        List<String> list = manageFeeList.stream().map(OutFee::getProject).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<String> typeList() throws SerException {
        String[] field = new String[]{"type"};
        String sql = "select type  from managefee_outfee group by type ";
        List<OutFee> manageFeeList = super.findBySql(sql, OutFee.class, field);
        List<String> list = manageFeeList.stream().map(OutFee::getType).collect(Collectors.toList());
        return list;
    }

    @Override
    public byte[] areaExportReport(CollectAreaTO collectAreaTO) throws SerException {
        XSSFWorkbook wb = new XSSFWorkbook();

        //表1
        List<OutFeeBO> listCollect = collectArea(collectAreaTO);
        List<OutFeeAreaExport> outFeeAreaExports = new ArrayList<>();
        listCollect.stream().forEach(str -> {
            OutFeeAreaExport excel = BeanTransform.copyProperties(str, OutFeeAreaExport.class);
            outFeeAreaExports.add(excel);
        });
        if( listCollect != null && listCollect.size()>0 ){
            OutFeeAreaExport excel_time = new OutFeeAreaExport();
            excel_time.setArea( "时间："+ listCollect.get(0).getYear());
            outFeeAreaExports.add( excel_time );
        }
        Excel excel = new Excel(0, 2);
        excel.setSheetName("地区汇总");
        wb = ExcelUtil.clazzToExcelAddMoreSheet( wb , outFeeAreaExports, excel,0);

        //表2
        List<OutFeeBO> listCollectDetail = collectAreaDetial(collectAreaTO);
        List<OutFeeAreaExportDetail> manageFeeAreaExportDetails = new ArrayList<>();
        listCollectDetail.stream().forEach(str -> {
            OutFeeAreaExportDetail exportDetail = BeanTransform.copyProperties(str, OutFeeAreaExportDetail.class);
            manageFeeAreaExportDetails.add(exportDetail);
        });
        Excel excel2 = new Excel(0, 2);
        excel2.setSheetName("地区汇总明细");
        wb = ExcelUtil.clazzToExcelAddMoreSheet( wb , manageFeeAreaExportDetails, excel2,1);

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
        List<OutFeeBO> listCollect = collectProject(collectProjectTO);
        List<OutFeeProjectExport> outFeeProjectExports = new ArrayList<>();
        listCollect.stream().forEach(str -> {
            OutFeeProjectExport excel = BeanTransform.copyProperties(str, OutFeeProjectExport.class);
            outFeeProjectExports.add(excel);
        });
        if( listCollect != null && listCollect.size()>0 ){
            OutFeeProjectExport excel_time = new OutFeeProjectExport();
            excel_time.setProject( "时间："+ listCollect.get(0).getYear());
            outFeeProjectExports.add( excel_time );
        }
        Excel excel = new Excel(0, 2);
        excel.setSheetName("项目名称汇总");
        wb = ExcelUtil.clazzToExcelAddMoreSheet( wb , outFeeProjectExports, excel,0);

        //表2
        List<OutFeeBO> listCollectDetail = collectProjectDetail(collectProjectTO);
        List<OutFeeProjectExportDetail> outFeeProjectExportDetails = new ArrayList<>();
        listCollectDetail.stream().forEach(str -> {
            OutFeeProjectExportDetail exportDetail = BeanTransform.copyProperties(str, OutFeeProjectExportDetail.class);
            outFeeProjectExportDetails.add(exportDetail);
        });
        Excel excel2 = new Excel(0, 2);
        excel2.setSheetName("项目名称汇总明细");
        wb = ExcelUtil.clazzToExcelAddMoreSheet( wb , outFeeProjectExportDetails, excel2,1);

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
        List<OutFeeBO> listCollect = collectGroup(collectProjectTO);
        List<OutFeeGroupExport> outFeeGroupExports = new ArrayList<>();
        listCollect.stream().forEach(str -> {
            OutFeeGroupExport excel = BeanTransform.copyProperties(str, OutFeeGroupExport.class);
            outFeeGroupExports.add(excel);
        });
        if( listCollect != null && listCollect.size()>0 ){
            OutFeeGroupExport excel_time = new OutFeeGroupExport();
            excel_time.setProjectGroup( "时间："+ listCollect.get(0).getYear());
            outFeeGroupExports.add( excel_time );
        }
        Excel excel = new Excel(0, 2);
        excel.setSheetName("项目组汇总");
        wb = ExcelUtil.clazzToExcelAddMoreSheet( wb , outFeeGroupExports, excel,0);

        //表2
        List<OutFeeBO> listCollectDetail = collectGroupDetail(collectProjectTO);
        List<OutFeeGroupExportDetail> outFeeGroupExportDetails = new ArrayList<>();
        listCollectDetail.stream().forEach(str -> {
            OutFeeGroupExportDetail exportDetail = BeanTransform.copyProperties(str, OutFeeGroupExportDetail.class);
            outFeeGroupExportDetails.add(exportDetail);
        });
        Excel excel2 = new Excel(0, 2);
        excel2.setSheetName("项目组汇总明细");
        wb = ExcelUtil.clazzToExcelAddMoreSheet( wb , outFeeGroupExportDetails, excel2,1);

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
        List<OutFeeBO> listCollect = collectType(collectCategoryTO);
        List<OutFeeTypeExport> outFeeTypeExports = new ArrayList<>();
        listCollect.stream().forEach(str -> {
            OutFeeTypeExport excel = BeanTransform.copyProperties(str, OutFeeTypeExport.class);
            outFeeTypeExports.add(excel);
        });
        if( listCollect != null && listCollect.size()>0 ){
            OutFeeTypeExport excel_time = new OutFeeTypeExport();
            excel_time.setType( "时间："+ listCollect.get(0).getYear());
            outFeeTypeExports.add( excel_time );
        }
        Excel excel = new Excel(0, 2);
        excel.setSheetName("类别汇总");
        wb = ExcelUtil.clazzToExcelAddMoreSheet( wb , outFeeTypeExports, excel,0);

        //表2
        List<OutFeeBO> listCollectDetail = collectTypeDetail(collectCategoryTO);
        List<OutFeeTypeExportDetail> outFeeTypeExportDetails = new ArrayList<>();
        listCollectDetail.stream().forEach(str -> {
            OutFeeTypeExportDetail exportDetail = BeanTransform.copyProperties(str, OutFeeTypeExportDetail.class);
            outFeeTypeExportDetails.add(exportDetail);
        });
        Excel excel2 = new Excel(0, 2);
        excel2.setSheetName("类别汇总明细");
        wb = ExcelUtil.clazzToExcelAddMoreSheet( wb , outFeeTypeExportDetails, excel2,1);

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
    public OutFeeBO importExcel(List<OutFeeTO> manageFeeTOS) throws SerException {
        List<OutFee> list = BeanTransform.copyProperties(manageFeeTOS, OutFee.class);
        list.stream().forEach(str->{
            str.setRate( str.getActualFee() / str.getTargetFee());
            str.setBalance( str.getActualFee() - str.getTargetFee());
        });
        super.save(list);

        return new OutFeeBO();
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<OutFeeImport> outFeeImports = new ArrayList<>();
        OutFeeImport temp1 = new OutFeeImport();
        temp1.setArea("湖南");
        temp1.setProject("外包费临时项目1");
        temp1.setProjectGroup("外包费临时项目组1");
        temp1.setType("外包费临时类别1");
        temp1.setYear("2017");
        temp1.setMonth("8");
        temp1.setTargetFee( 100d );
        temp1.setActualFee( 20d );
        outFeeImports.add( temp1 );

        temp1 = new OutFeeImport();
        temp1.setArea("湖南");
        temp1.setProject("外包费临时项目2");
        temp1.setProjectGroup("外包费临时项目组2");
        temp1.setType("外包费临时类别2");
        temp1.setYear("2017");
        temp1.setMonth("8");
        temp1.setTargetFee( 55d );
        temp1.setActualFee( 11d );
        outFeeImports.add( temp1 );
        Excel excel2 = new Excel(0, 2);
        excel2.setSheetName("外包费导入模板");
        byte[] bytes = ExcelUtil.clazzToExcel(outFeeImports, excel2);

        return bytes;
    }


}