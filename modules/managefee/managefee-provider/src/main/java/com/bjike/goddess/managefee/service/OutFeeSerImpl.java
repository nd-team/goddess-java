package com.bjike.goddess.managefee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managefee.bo.OutFeeBO;
import com.bjike.goddess.managefee.bo.OutFeeBO;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.entity.OutFee;
import com.bjike.goddess.managefee.entity.OutFee;
import com.bjike.goddess.managefee.excel.SonPermissionObject;
import com.bjike.goddess.managefee.to.GuidePermissionTO;
import com.bjike.goddess.managefee.to.OutFeeTO;
import com.bjike.goddess.managefee.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            flag =  cusPermissionSer.busCusPermission("1");
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
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        OutFee outFee = super.findById(id);
        return BeanTransform.copyProperties(outFee, OutFeeBO.class);
    }

    @Override
    public List<OutFeeBO> listOutFee(OutFeeDTO outFeeDTO) throws SerException {
        checkPermission();
        outFeeDTO.getSorts().add("createTime=desc");
        List<OutFee> list = super.findByCis(outFeeDTO,true);

        return BeanTransform.copyProperties(list, OutFeeBO.class );
    }

    public static void main(String[] args) {
        Double d = null;
        Double d2 = 0d;

        System.out.println(d.isNaN());
        System.out.println(d2.isNaN());
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OutFeeBO addOutFee(OutFeeTO outFeeTO) throws SerException {
        checkPermission();
        if( outFeeTO.getTargetFee()==null){
            throw new SerException("目标管理费不能为空");
        }
        int year = Integer.parseInt(outFeeTO.getYear().trim());
        int month = Integer.parseInt(outFeeTO.getMonth().trim());
        LocalDate startTime = LocalDate.of(year,month,1);
        LocalDate endTime = startTime.with(TemporalAdjusters.lastDayOfMonth());
        VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO();
        voucherGenerateDTO.setArea( outFeeTO.getArea());
        voucherGenerateDTO.setProjectName( outFeeTO.getProject());
        voucherGenerateDTO.setProjectGroup( outFeeTO.getProjectGroup());
        voucherGenerateDTO.setStartTime( startTime+"");
        voucherGenerateDTO.setEndTime( endTime+"" );
        List<VoucherGenerateBO> voucherList = voucherGenerateAPI.listStatistic( voucherGenerateDTO ,"outFee");
        //记账凭证里面的费用
        Double money = 0d;
        if( voucherList!= null && voucherList.size()>0){
            money = voucherList.stream().filter(str->str.getBorrowMoney()!=null).mapToDouble(VoucherGenerateBO::getBorrowMoney).sum();
        }

        OutFee outFee = BeanTransform.copyProperties(outFeeTO, OutFee.class, true);
        if( money == null && outFeeTO.getActualFee()==null ){
            money = 0d;
        }
        outFee.setActualFee( money );
        outFee.setRate( outFee.getActualFee()/outFee.getTargetFee() );
        outFee.setBalance( outFee.getActualFee() - outFee.getTargetFee() );
        outFee.setCreateTime(LocalDateTime.now());
        super.save( outFee );
        return BeanTransform.copyProperties(outFee, OutFeeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OutFeeBO editOutFee(OutFeeTO outFeeTO) throws SerException {
        checkPermission();
        if( outFeeTO.getTargetFee()==null){
            throw new SerException("目标管理费不能为空");
        }
        if( outFeeTO.getActualFee()==null){
            throw new SerException("实际管理费不能为空");
        }

        OutFee outFee = BeanTransform.copyProperties(outFeeTO,OutFee.class,true);
        OutFee cusLevel = super.findById( outFeeTO.getId() );

        BeanUtils.copyProperties( outFee , cusLevel ,"id","createTime");
        cusLevel.setRate( cusLevel.getActualFee()/cusLevel.getTargetFee() );
        cusLevel.setBalance( cusLevel.getActualFee() - cusLevel.getTargetFee() );
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(cusLevel, OutFeeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteOutFee(String id) throws SerException {
        if(StringUtils.isBlank( id)){
            throw  new SerException("id不能为空");
        }
        super.remove( id );
    }



    @Override
    public List<OutFeeBO> collectArea(OutFeeDTO outFeeDTO) throws SerException {
        checkPermission();
        String startTime = outFeeDTO.getStartTime();
        String endTime = outFeeDTO.getEndTime();

        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if( StringUtils.isNotBlank(startTime) ){
            start = LocalDate.parse(startTime);
        }
        if(  StringUtils.isNotBlank( endTime) ){
            end = LocalDate.parse(endTime);
        }

        //如果没有选地区，汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String [] field = new String[]{"area","targetFee","actualFee","rate","balance"};
        String sql = "";
        List<OutFeeBO> list = new ArrayList<>();
        int yearBegin  = start.getYear();
        int yearEnd  = end.getYear();
        if( StringUtils.isBlank(outFeeDTO.getArea())){
            sql = "select area ,  sum(targetFee) as targetFee , sum(actualFee) as actualFee ," +
                    "  (sum(actualFee)/sum(targetFee)) as rate , (sum(actualFee)-sum(targetFee)) as balance from managefee_outfee where 1= 1";
            if(StringUtils.isNotBlank( startTime) && StringUtils.isNotBlank( endTime)){
                sql = sql +" and year between '"+yearBegin+"' and '"+yearEnd+"' and month between '"+start.getMonthValue()+"' and '"+end.getMonthValue()+"' ";
            }
            sql = sql +" group by area  order by area desc ";
            list = super.findBySql( sql , OutFeeBO.class , field);
            list.stream().forEach(str->{
                str.setYear( yearBegin+"-"+yearEnd);
            });
        }else{
            //如果有选地区，汇总表头：(地区/年份/月份/项目组/项目名称/类别/目标管理费/实际管理费/比例/差额)
            field = new String[]{"area","year","month","projectGroup","project","type","targetFee","actualFee","rate","balance"};
            sql = "select area , year , month ,projectGroup , project,type, targetFee , actualFee ," +
                    "  (actualFee/targetFee) as rate , (actualFee-targetFee) as balance from managefee_outfee where 1=1 ";
            if(StringUtils.isNotBlank( startTime) && StringUtils.isNotBlank( endTime)){
                sql = sql +" and year between '"+yearBegin+"' and '"+yearEnd+"' and month between '"+start.getMonthValue()+"' and '"+end.getMonthValue()+"' ";
            }
            sql = sql + " and area = '"+outFeeDTO.getArea()+"' order by area desc ";
            list = super.findBySql( sql , OutFeeBO.class , field);
        }


        return list;
    }

    @Override
    public List<OutFeeBO> collectGroup(OutFeeDTO outFeeDTO) throws SerException {
        checkPermission();
        String startTime = outFeeDTO.getStartTime();
        String endTime = outFeeDTO.getEndTime();

        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if( StringUtils.isNotBlank(startTime) ){
            start = LocalDate.parse(startTime);
        }
        if(  StringUtils.isNotBlank( endTime) ){
            end = LocalDate.parse(endTime);
        }

        //如果没有选地区，汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String [] field = new String[]{"projectGroup","targetFee","actualFee","rate","balance"};
        String sql = "";
        List<OutFeeBO> list = new ArrayList<>();
        int yearBegin  = start.getYear();
        int yearEnd  = end.getYear();
        if( StringUtils.isBlank(outFeeDTO.getProjectGroup())){
            sql = "select projectGroup , sum(targetFee) as targetFee , sum(actualFee) as actualFee ," +
                    "  (sum(actualFee)/sum(targetFee)) as rate , (sum(actualFee)-sum(targetFee)) as balance from managefee_outfee where 1= 1";
            if(StringUtils.isNotBlank( startTime) && StringUtils.isNotBlank( endTime)){
                sql = sql +" and year between '"+start.getYear()+"' and '"+end.getYear()+"' and month between '"+start.getMonthValue()+"' and '"+end.getMonthValue()+"' ";
            }
            sql = sql +" group by projectGroup  order by projectGroup desc ";
            list = super.findBySql( sql , OutFeeBO.class , field);
            list.stream().forEach(str->{
                str.setYear( yearBegin+"-"+yearEnd);
            });
        }else{
            //如果有选地区，汇总表头：(地区/年份/月份/项目组/项目名称/类别/目标管理费/实际管理费/比例/差额)
            field = new String[]{"projectGroup","year","month","area","project","type","targetFee","actualFee","rate","balance"};
            sql = "select projectGroup , year , month ,area , project,type, targetFee , actualFee ," +
                    "  (actualFee/targetFee) as rate , (actualFee-targetFee) as balance from managefee_outfee where 1=1 ";
            if(StringUtils.isNotBlank( startTime) && StringUtils.isNotBlank( endTime)){
                sql = sql +" and year between '"+yearBegin+"' and '"+yearEnd+"' and month between '"+start.getMonthValue()+"' and '"+end.getMonthValue()+"' ";
            }
            sql = sql + " and projectGroup = '"+outFeeDTO.getProjectGroup()+"' order by projectGroup desc ";
            list = super.findBySql( sql , OutFeeBO.class , field);
        }


        return list;
    }

    @Override
    public List<OutFeeBO> collectProject(OutFeeDTO outFeeDTO) throws SerException {
        checkPermission();
        String startTime = outFeeDTO.getStartTime();
        String endTime = outFeeDTO.getEndTime();

        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if( StringUtils.isNotBlank(startTime) ){
            start = LocalDate.parse(startTime);
        }
        if(  StringUtils.isNotBlank( endTime) ){
            end = LocalDate.parse(endTime);
        }

        //如果没有选地区，汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String [] field = new String[]{"project","targetFee","actualFee","rate","balance"};
        String sql = "";
        List<OutFeeBO> list = new ArrayList<>();
        int yearBegin  = start.getYear();
        int yearEnd  = end.getYear();
        if( StringUtils.isBlank(outFeeDTO.getProject())){
            sql = "select project , sum(targetFee) as targetFee , sum(actualFee) as actualFee ," +
                    "  (sum(actualFee)/sum(targetFee)) as rate , (sum(actualFee)-sum(targetFee)) as balance from managefee_outfee where 1= 1";
            if(StringUtils.isNotBlank( startTime) && StringUtils.isNotBlank( endTime)){
                sql = sql +" and year between '"+start.getYear()+"' and '"+end.getYear()+"' and month between '"+start.getMonthValue()+"' and '"+end.getMonthValue()+"' ";
            }
            sql = sql +" group by project  order by project desc ";
            list = super.findBySql( sql , OutFeeBO.class , field);
            list.stream().forEach(str->{
                str.setYear( yearBegin+"-"+yearEnd);
            });
        }else{
            //如果有选地区，汇总表头：(地区/年份/月份/项目组/项目名称/类别/目标管理费/实际管理费/比例/差额)
            field = new String[]{"project","year","month","area","projectGroup","type","targetFee","actualFee","rate","balance"};
            sql = "select project , year , month ,area , projectGroup,type, targetFee , actualFee ," +
                    "  (actualFee/targetFee) as rate , (actualFee-targetFee) as balance from managefee_outfee where 1=1 ";
            if(StringUtils.isNotBlank( startTime) && StringUtils.isNotBlank( endTime)){
                sql = sql +" and year between '"+yearBegin+"' and '"+yearEnd+"' and month between '"+start.getMonthValue()+"' and '"+end.getMonthValue()+"' ";
            }
            sql = sql + " and project = '"+outFeeDTO.getProject()+"' order by project desc ";
            list = super.findBySql( sql , OutFeeBO.class , field);
        }


        return list;
    }


    @Override
    public List<OutFeeBO> collectType(OutFeeDTO outFeeDTO) throws SerException {
        checkPermission();
        String startTime = outFeeDTO.getStartTime();
        String endTime = outFeeDTO.getEndTime();

        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if( StringUtils.isNotBlank(startTime) ){
            start = LocalDate.parse(startTime);
        }
        if(  StringUtils.isNotBlank( endTime) ){
            end = LocalDate.parse(endTime);
        }
        //如果没有选地区，汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String [] field = new String[]{"type","targetFee","actualFee","rate","balance"};
        String sql = "";
        List<OutFeeBO> list = new ArrayList<>();
        int yearBegin  = start.getYear();
        int yearEnd  = end.getYear();
        if( StringUtils.isBlank(outFeeDTO.getType())){
            sql = "select type ,  sum(targetFee) as targetFee , sum(actualFee) as actualFee ," +
                    "  (sum(actualFee)/sum(targetFee)) as rate , (sum(actualFee)-sum(targetFee)) as balance from managefee_outfee where 1= 1";
            if(StringUtils.isNotBlank( startTime) && StringUtils.isNotBlank( endTime)){
                sql = sql +" and year between '"+yearBegin+"' and '"+yearEnd+"' and month between '"+start.getMonthValue()+"' and '"+end.getMonthValue()+"' ";
            }
            sql = sql +" group by type  order by type desc ";
            list = super.findBySql( sql , OutFeeBO.class , field);
            list.stream().forEach(str->{
                str.setYear( yearBegin+"-"+yearEnd);
            });
        }else{
            //如果有选地区，汇总表头：(地区/年份/月份/项目组/项目名称/类别/目标管理费/实际管理费/比例/差额)
            field = new String[]{"type","year","month","area","projectGroup","project","targetFee","actualFee","rate","balance"};
            sql = "select type , year , month ,area , projectGroup,project, targetFee , actualFee ," +
                    "  (actualFee/targetFee) as rate , (actualFee-targetFee) as balance from managefee_outfee where 1=1 ";
            if(StringUtils.isNotBlank( startTime) && StringUtils.isNotBlank( endTime)){
                sql = sql +" and year between '"+start.getYear()+"' and '"+end.getYear()+"' and month between '"+start.getMonthValue()+"' and '"+end.getMonthValue()+"' ";
            }
            sql = sql + " and type = '"+outFeeDTO.getType()+"' order by type desc ";
            list = super.findBySql( sql , OutFeeBO.class , field);
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
        while(start<= end ){
            year = start+"";
            start = start +1 ;
            yearList.add( year );
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
}