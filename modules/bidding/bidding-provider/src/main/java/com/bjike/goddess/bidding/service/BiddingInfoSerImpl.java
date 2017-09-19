package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingCollectBO;
import com.bjike.goddess.bidding.bo.BiddingInfoBO;
import com.bjike.goddess.bidding.bo.BiddingInfoCollectBO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.entity.BiddingInfo;
import com.bjike.goddess.bidding.enums.GuideAddrStatus;
import com.bjike.goddess.bidding.excel.BiddingInfoExport;
import com.bjike.goddess.bidding.excel.SonPermissionObject;
import com.bjike.goddess.bidding.to.BiddingCollectTO;
import com.bjike.goddess.bidding.to.BiddingInfoTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 招标信息业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.995 ]
 * @Description: [ 招标信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "biddingSerCache")
@Service
public class BiddingInfoSerImpl extends ServiceImpl<BiddingInfo, BiddingInfoDTO> implements BiddingInfoSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private BiddingWebInfoSer biddingWebInfoSer;
    @Autowired
    private BidOpeningInfoSer bidOpeningInfoSer;
    @Autowired
    private CollectEmailSer collectEmailSer;
    @Autowired
    private BiddingAnswerQuestionsSer biddingAnswerQuestionsSer;

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
                throw new SerException("您不是相应部门的人员，不可以查看");
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

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("biddinginfo");
        obj.setDescribesion("招标信息");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeAnswer = biddingAnswerQuestionsSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("biddinganswerquestions");
        obj.setDescribesion("投标答疑问题记录");
        if (flagSeeAnswer) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeWeb = biddingWebInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("biddingwebinfo");
        obj.setDescribesion("招投标网站信息");
        if (flagSeeWeb) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeOpen = bidOpeningInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("bidopeninginfo");
        obj.setDescribesion("开标信息");
        if (flagSeeOpen) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = collectEmailSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("collectemail");
        obj.setDescribesion("招投标信息邮件发送");
        if (flagSeeEmail) {
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
        return flag;
    }

    /**
     * 核对时间格式(年月日)
     */
    private void checkDate(BiddingInfoTO biddingInfoTO) throws SerException {
        try {
            DateUtil.parseDate(biddingInfoTO.getBuyTenderTime());
            DateUtil.parseDate(biddingInfoTO.getMarginTime());
            DateUtil.parseDate(biddingInfoTO.getBackTimeDeposit());
        } catch (Exception e) {
            throw new SerException("输入的日期格式有误");
        }
    }

    @Override
    public Long countBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        biddingInfoDTO.getSorts().add("createTime=desc");
        if (StringUtils.isNotBlank(biddingInfoDTO.getWebName())) {
            biddingInfoDTO.getConditions().add(Restrict.eq("webName", biddingInfoDTO.getWebName()));
        }
        if (StringUtils.isNotBlank(biddingInfoDTO.getUrl())) {
            biddingInfoDTO.getConditions().add(Restrict.eq("url", biddingInfoDTO.getUrl()));
        }
        if (StringUtils.isNotBlank(biddingInfoDTO.getCities())) {
            biddingInfoDTO.getConditions().add(Restrict.eq("cities", biddingInfoDTO.getCities()));
        }
        if (StringUtils.isNotBlank(biddingInfoDTO.getProvinces())) {
            biddingInfoDTO.getConditions().add(Restrict.eq("provinces", biddingInfoDTO.getProvinces()));
        }
        Long count = super.count(biddingInfoDTO);
        return count;
    }

    @Override
    public BiddingInfoBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        BiddingInfo biddingInfo = super.findById(id);
        return BeanTransform.copyProperties(biddingInfo, BiddingInfoBO.class);
    }

    @Override
    public List<BiddingInfoBO> findListBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        checkSeeIdentity();
        searchBiddingInfo(biddingInfoDTO);
        biddingInfoDTO.getSorts().add("createTime=desc");
        List<BiddingInfo> biddingInfo = super.findByCis(biddingInfoDTO, true);
        List<BiddingInfoBO> biddingInfoBOS = BeanTransform.copyProperties(biddingInfo, BiddingInfoBO.class);
        return biddingInfoBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingInfoBO insertBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        checkAddIdentity();
        checkDate(biddingInfoTO);
        BiddingInfo biddingInfo = BeanTransform.copyProperties(biddingInfoTO, BiddingInfo.class, true, "tenderModule");
        biddingInfo.setTenderModule(StringUtils.join(biddingInfoTO.getTenderModule(), ","));
        if (biddingInfo.getPassProjectEstimates().equals(0)) {
            biddingInfo.setOpportunity(false);
        } else {
            biddingInfo.setOpportunity(true);
        }
        super.save(biddingInfo);
        return BeanTransform.copyProperties(biddingInfo, BiddingInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingInfoBO editBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(biddingInfoTO.getId())) {
            throw new SerException("id不能为空");
        }
        BiddingInfo biddingInfo = super.findById(biddingInfoTO.getId());
        BeanTransform.copyProperties(biddingInfoTO, biddingInfo, true, "tenderModule");
        checkDate(biddingInfoTO);
        biddingInfo.setModifyTime(LocalDateTime.now());
        biddingInfo.setTenderModule(StringUtils.join(biddingInfoTO.getTenderModule(), ","));
        if (biddingInfo.getPassProjectEstimates().equals(0)) {
            biddingInfo.setOpportunity(false);
        } else {
            biddingInfo.setOpportunity(true);
        }
        super.update(biddingInfo);
        return BeanTransform.copyProperties(biddingInfo, BiddingInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBiddingInfo(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    public void searchBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        /**
         * 网站名称
         */
        if (StringUtils.isNotBlank(biddingInfoDTO.getWebName())) {
            biddingInfoDTO.getConditions().add(Restrict.eq("webName", biddingInfoDTO.getWebName()));
        }
        /**
         * 网址
         */
        if (StringUtils.isNotBlank(biddingInfoDTO.getUrl())) {
            biddingInfoDTO.getConditions().add(Restrict.eq("url", biddingInfoDTO.getUrl()));
        }
        /**
         * 省份
         */

        if (StringUtils.isNotBlank(biddingInfoDTO.getProvinces())) {
            biddingInfoDTO.getConditions().add(Restrict.eq("provinces", biddingInfoDTO.getProvinces()));

        }
        /**
         * 地市
         */
        if (StringUtils.isNotBlank(biddingInfoDTO.getCities())) {
            biddingInfoDTO.getConditions().add(Restrict.eq("cities", biddingInfoDTO.getCities()));

        }
    }

    @Override
    public List<BiddingInfoCollectBO> collectBiddingInfo(String[] cities) throws SerException {
        if (cities == null || cities.length <= 0) {
            throw new SerException("汇总失败，请选择地市");
        }
        List<BiddingInfoCollectBO> boList = new ArrayList<>();
//        String[] citiesTemp = new String[cities.length];
//        for (int i = 0; i < cities.length; i++) {
//            citiesTemp[i] = cities[i];
//        }
        StringBuilder sb= new StringBuilder();
        for (int i=0;i<cities.length;i++){
            if (i==cities.length-1){
                sb.append("'"+cities[i]+"'");
            }else {
                sb.append("'"+cities[i]+"',");
            }
        }

        String sql = "SELECT biddingType,count(biddingType) as count FROM bidding_biddinginfo " +
                "WHERE cities IN (" + sb.toString() + ") GROUP BY biddingType ";
        List<Object> objects = super.findBySql(sql);
        Map<String, Integer> map = new HashMap<>(objects.size());
        if (null != objects && objects.size() > 0) {
            for (Object o : objects) {
                Object[] val = (Object[]) o;
                map.put(String.valueOf(val[0]), Integer.parseInt(String.valueOf(val[1])));
            }

        }

        sql = " SELECT businessType,count(businessType) AS count " +
                "FROM bidding_biddinginfo WHERE cities IN (" + sb.toString()+ ") GROUP BY businessType ";
        List<Object> object = super.findBySql(sql);
        Map<String, Integer> businessMap = new HashMap<>(object.size());
        if (null != object && object.size() > 0) {
            for (Object o : object) {
                Object[] val = (Object[]) o;
                businessMap.put(String.valueOf(val[0]), Integer.parseInt(String.valueOf(val[1])));
            }

        }
        BiddingInfoCollectBO bo = new BiddingInfoCollectBO();
        StringBuilder sb1= new StringBuilder();
        for (int i=0;i<cities.length;i++){
            if (i==cities.length-1){
                sb1.append(""+cities[i]+"");
            }else {
                sb1.append(""+cities[i]+",");
            }
        }
        bo.setCities(sb1.toString());
        bo.setBiddingMap(map);
        bo.setBusinessMap(businessMap);
        boList.add(bo);
        return boList;
    }

    @Override
    public BiddingInfoBO getBidding(String biddingNumber) throws SerException {
        if (StringUtils.isNotBlank(biddingNumber)) {
            BiddingInfoDTO dto = new BiddingInfoDTO();
            dto.getConditions().add(Restrict.eq("biddingNumber", biddingNumber));
            BiddingInfo biddingInfo = super.findOne(dto);
            BiddingInfoBO bo = BeanTransform.copyProperties(biddingInfo, BiddingInfoBO.class);
            return bo;
        }
        return null;
    }

    @Override
    public List<String> getBiddingInfoCities() throws SerException {
        String[] fields = new String[]{"cities"};
        List<BiddingInfoBO> biddingInfoBOS = super.findBySql("select distinct cities from bidding_biddinginfo group by cities order by cities asc ", BiddingInfoBO.class, fields);

        List<String> citiesList = biddingInfoBOS.stream().map(BiddingInfoBO::getCities)
                .filter(cities -> (cities != null || !"".equals(cities.trim()))).distinct().collect(Collectors.toList());


        return citiesList;
    }

    @Override
    public List<String> getProjectName() throws SerException {
        String[] fields = new String[]{"projectName"};
        List<BiddingInfoBO> biddingInfoBOS = super.findBySql("select distinct projectName from bidding_biddinginfo group by projectName order by projectName asc ", BiddingInfoBO.class, fields);

        List<String> projectNameList = biddingInfoBOS.stream().map(BiddingInfoBO::getProjectName)
                .filter(projectName -> (projectName != null || !"".equals(projectName.trim()))).distinct().collect(Collectors.toList());


        return projectNameList;
    }

    @Override
    public List<String> getTenderNumber() throws SerException {
        String[] fields = new String[]{"biddingNumber"};
        List<BiddingInfoBO> biddingInfoBOS = super.findBySql("select distinct biddingNumber from bidding_biddinginfo group by biddingNumber order by biddingNumber asc ", BiddingInfoBO.class, fields);

        List<String> biddingNumberList = biddingInfoBOS.stream().map(BiddingInfoBO::getBiddingNumber)
                .filter(biddingNumber -> (biddingNumber != null || !"".equals(biddingNumber.trim()))).distinct().collect(Collectors.toList());


        return biddingNumberList;
    }

    @Override
    public byte[] exportExcel(BiddingInfoDTO dto) throws SerException {
        if (null != dto.getProjectName()) {
            dto.getConditions().add(Restrict.in("projectName", dto.getProjectName()));
        }
        List<BiddingInfo> list = super.findByCis(dto);

        List<BiddingInfoExport> biddingInfoExports = new ArrayList<>();
        list.stream().forEach(str -> {
            BiddingInfoExport export = BeanTransform.copyProperties(str, BiddingInfoExport.class, "status");
//            export.setBusinessType(BusinessType.exportStrConvert(str.getBusinessType()));
//            if (str.getStatus().equals(Status.THAW)) {
//                export.setStatus("解冻");
//            } else if (str.getStatus().equals(Status.CONGEAL)) {
//                export.setStatus("冻结");
//            } else if (str.getStatus().equals(Status.DELETE)) {
//                export.setStatus("删除");
//            } else if (str.getStatus().equals(Status.NOACTIVE)) {
//                export.setStatus("未激活");
//            } else if (str.getStatus().equals(Status.UNREVIEW)) {
//                export.setStatus("未审核");
//            }
            biddingInfoExports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(biddingInfoExports, excel);
        return bytes;
    }


    @Override
    public List<BiddingCollectBO> dayCollect(BiddingCollectTO to) throws SerException {
        LocalDate time = null;
        if (to.getTime() != null) {
            time = DateUtil.parseDate(to.getTime());
        } else {
            time = LocalDate.now();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT a.businessType as businessType,a.year as year, ");
        sb.append(" count(a.businessType) as biddinginfoNum,count(a.is_projectEstimates) as projectEstimatesNum, ");
        sb.append(" a.scale AS scaleNum,count(a.is_passProjectEstimates) AS biddingProjectNum, ");
        sb.append(" count(b.competitive) as contendNum,sum(b.biddingPrice) as biddingPrice, ");
        sb.append(" count(b.is_finish) AS bidNum,count(b.is_passProjectEstimates) AS winNum, ");
        sb.append(" sum(b.marginPrice) AS marginPrice,sum(b.returnMarginPrice) as returnMarginPrice ");
        sb.append(" FROM bidding_biddinginfo a,bidding_bidopeninginfo b ");
        sb.append(" WHERE a.businessType=b.businessType AND a.is_projectEstimates=1 ");
        sb.append(" AND a.is_passProjectEstimates=1 AND b.is_finish=1 AND b.is_passProjectEstimates=1 ");
        sb.append(" AND a.updateTime = b.updateTime AND a.updateTime='" + time + "' ");
        sb.append(" GROUP BY a.businessType,b.businessType,a.year,b.year, a.scale, ");
        sb.append(" b.competitive ");
        String sql = sb.toString();
        String[] feilds = new String[]{"businessType", "year", "biddinginfoNum", "projectEstimatesNum",
                "scaleNum", "biddingProjectNum", "contendNum", "biddingPrice", "bidNum", "winNum", "marginPrice", "returnMarginPrice"};
        List<BiddingCollectBO> boList = super.findBySql(sql, BiddingCollectBO.class, feilds);
        return boList;
    }

    @Override
    public List<BiddingCollectBO> weekCollect(BiddingCollectTO to) throws SerException {
        LocalDate[] time = null;
        Integer year = to.getYear();
        Integer month = to.getMonth();
        Integer week = to.getWeek();
        if (year != null && month != null && week != null) {
            time = DateUtil.getWeekTimes(year, month, week);
        } else {
            String dateString = DateUtil.dateToString(LocalDate.now());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
            time = DateUtil.getWeekTimes(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), weekOfMonth);
        }
        LocalDate start = time[0];
        LocalDate end = time[1];
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT a.businessType as businessType,a.year as year, ");
        sb.append(" count(a.businessType) as biddinginfoNum,count(a.is_projectEstimates) as projectEstimatesNum, ");
        sb.append(" a.scale AS scaleNum,count(a.is_passProjectEstimates) AS biddingProjectNum, ");
        sb.append(" count(b.competitive) as contendNum,sum(b.biddingPrice) as biddingPrice, ");
        sb.append(" count(b.is_finish) AS bidNum,count(b.is_passProjectEstimates) AS winNum, ");
        sb.append(" sum(b.marginPrice) AS marginPrice,sum(b.returnMarginPrice) as returnMarginPrice ");
        sb.append(" FROM bidding_biddinginfo a,bidding_bidopeninginfo b ");
        sb.append(" WHERE a.businessType=b.businessType AND a.is_projectEstimates=1 ");
        sb.append(" AND a.is_passProjectEstimates=1 AND b.is_finish=1 AND b.is_passProjectEstimates=1 ");
        sb.append(" AND a.updateTime = b.updateTime AND a.updateTime between '" + start + "' and '" + end + "' ");
        sb.append(" GROUP BY a.businessType,b.businessType,a.year,b.year, a.scale, ");
        sb.append(" b.competitive ");
        String sql = sb.toString();
        String[] feilds = new String[]{"businessType", "year", "biddinginfoNum", "projectEstimatesNum",
                "scaleNum", "biddingProjectNum", "contendNum", "biddingPrice", "bidNum", "winNum", "marginPrice", "returnMarginPrice"};
        List<BiddingCollectBO> boList = super.findBySql(sql, BiddingCollectBO.class, feilds);
        return boList;
    }

    @Override
    public List<BiddingCollectBO> monthCollect(BiddingCollectTO to) throws SerException {
        Integer year = 0;
        Integer month = 0;
        if (to.getYear() != null && to.getMonth() != null) {
            year = to.getYear();
            month = to.getMonth();
        } else {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT a.businessType as businessType,a.year as year, ");
        sb.append(" count(a.businessType) as biddinginfoNum,count(a.is_projectEstimates) as projectEstimatesNum, ");
        sb.append(" a.scale AS scaleNum,count(a.is_passProjectEstimates) AS biddingProjectNum, ");
        sb.append(" count(b.competitive) as contendNum,sum(b.biddingPrice) as biddingPrice, ");
        sb.append(" count(b.is_finish) AS bidNum,count(b.is_passProjectEstimates) AS winNum, ");
        sb.append(" sum(b.marginPrice) AS marginPrice,sum(b.returnMarginPrice) as returnMarginPrice ");
        sb.append(" FROM bidding_biddinginfo a,bidding_bidopeninginfo b ");
        sb.append(" WHERE a.businessType=b.businessType AND a.is_projectEstimates=1 ");
        sb.append(" AND a.is_passProjectEstimates=1 AND b.is_finish=1 AND b.is_passProjectEstimates=1 ");
        sb.append(" AND a.updateTime = b.updateTime AND year(a.updateTime) = '" + year + "' and month(a.updateTime) = '" + month + "' ");
        sb.append(" GROUP BY a.businessType,b.businessType,a.year,b.year, a.scale, ");
        sb.append(" b.competitive ");
        String sql = sb.toString();
        String[] feilds = new String[]{"businessType", "year", "biddinginfoNum", "projectEstimatesNum",
                "scaleNum", "biddingProjectNum", "contendNum", "biddingPrice", "bidNum", "winNum", "marginPrice", "returnMarginPrice"};
        List<BiddingCollectBO> boList = super.findBySql(sql, BiddingCollectBO.class, feilds);
        return boList;
    }

    @Override
    public List<BiddingCollectBO> totalCollect(BiddingCollectTO to) throws SerException {
        LocalDate end = null;
        if (to.getTime() != null) {
            end = DateUtil.parseDate(to.getTime());
        } else {
            end = LocalDate.now();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT a.businessType as businessType,a.year as year, ");
        sb.append(" count(a.businessType) as biddinginfoNum,count(a.is_projectEstimates) as projectEstimatesNum, ");
        sb.append(" a.scale AS scaleNum,count(a.is_passProjectEstimates) AS biddingProjectNum, ");
        sb.append(" count(b.competitive) as contendNum,sum(b.biddingPrice) as biddingPrice, ");
        sb.append(" count(b.is_finish) AS bidNum,count(b.is_passProjectEstimates) AS winNum, ");
        sb.append(" sum(b.marginPrice) AS marginPrice,sum(b.returnMarginPrice) as returnMarginPrice ");
        sb.append(" FROM bidding_biddinginfo a,bidding_bidopeninginfo b ");
        sb.append(" WHERE a.businessType=b.businessType AND a.is_projectEstimates=1 ");
        sb.append(" AND a.is_passProjectEstimates=1 AND b.is_finish=1 AND b.is_passProjectEstimates=1 ");
        sb.append(" AND a.updateTime = b.updateTime AND a.updateTime<='" + end + "' ");
        sb.append(" GROUP BY a.businessType,b.businessType,a.year,b.year, a.scale, ");
        sb.append(" b.competitive ");
        String sql = sb.toString();
        String[] feilds = new String[]{"businessType", "year", "biddinginfoNum", "projectEstimatesNum",
                "scaleNum", "biddingProjectNum", "contendNum", "biddingPrice", "bidNum", "winNum", "marginPrice", "returnMarginPrice"};
        List<BiddingCollectBO> boList = super.findBySql(sql, BiddingCollectBO.class, feilds);
        return boList;
    }
    //        StringBuilder sb = new StringBuilder();
//        sb.append(" SELECT * from ");
//        sb.append(" (SELECT cities,max(CASE WHEN businessType=0 THEN businessTypeCounts END )AS mobile, ");
//        sb.append(" max(CASE WHEN businessType=2 THEN businessTypeCounts END )AS soft, ");
//        sb.append(" max(CASE WHEN businessType=3 THEN businessTypeCounts END )AS system, ");
//        sb.append(" max(CASE WHEN businessType=4 THEN businessTypeCounts END )AS plan FROM ");
//        sb.append(" (SELECT count(*) AS businessTypeCounts,businessType as businessType,cities as cities ");
//        sb.append(" FROM bidding_biddinginfo a WHERE cities IN (%s) GROUP BY businessType,cities ORDER BY cities)a GROUP BY cities)A ");
//        sb.append(" UNION ");
//        sb.append(" SELECT '合计' as area ,sum(mobile) AS mobile, ");
//        sb.append(" sum(soft) AS soft,sum(system) AS system,sum(plan) AS plan from ");
//        sb.append(" (SELECT cities,max(CASE WHEN businessType=0 THEN businessTypeCounts END )AS mobile, ");
//        sb.append(" max(CASE WHEN businessType=2 THEN businessTypeCounts END )AS soft, ");
//        sb.append(" max(CASE WHEN businessType=3 THEN businessTypeCounts END )AS system, ");
//        sb.append(" max(CASE WHEN businessType=4 THEN businessTypeCounts END )AS plan FROM ");
//        sb.append(" (SELECT count(*) AS businessTypeCounts,businessType as businessType,cities as cities ");
//        sb.append(" FROM bidding_biddinginfo a WHERE cities IN (%s) GROUP BY businessType,cities ORDER BY cities)a GROUP BY cities)A ");
//        String sql = sb.toString();
//        sql = String.format(sql, citiesStr, citiesStr, citiesStr, citiesStr);
//        String[] fields = new String[]{"cities", "mobile", "soft", "system", "plan"};
//        List<BiddingInfoCollectBO> biddingInfoCollectBOS = super.findBySql(sql, BiddingInfoCollectBO.class, fields);
//        for (BiddingInfoCollectBO collectBO : biddingInfoCollectBOS) {
}