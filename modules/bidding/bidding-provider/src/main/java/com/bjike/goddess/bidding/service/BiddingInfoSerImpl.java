package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.*;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.dto.SearchDTO;
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
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    @Autowired
    private BiddingTypeSer biddingTypeSer;
    @Autowired
    private BiddingAcceptSer biddingAcceptSer;

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

        Boolean flagSeeType = biddingTypeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("biddingtype");
        obj.setDescribesion("招投标类型");
        if (flagSeeType) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        Boolean flagSeeAccept = biddingAcceptSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("biddingaccept");
        obj.setDescribesion("招标问题受理和处理");
        if (flagSeeAccept) {
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
//        checkDate(biddingInfoTO);
        BiddingInfo biddingInfo = BeanTransform.copyProperties(biddingInfoTO, BiddingInfo.class, true, "tenderModule");
        biddingInfo.setTenderModule(StringUtils.join(biddingInfoTO.getTenderModule(), ","));
        if (biddingInfo.getPassProjectEstimates().equals(false)) {
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
//        checkDate(biddingInfoTO);
        biddingInfo.setModifyTime(LocalDateTime.now());
        biddingInfo.setTenderModule(StringUtils.join(biddingInfoTO.getTenderModule(), ","));
        if (biddingInfo.getPassProjectEstimates().equals(false)) {
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
            biddingInfoDTO.getConditions().add(Restrict.like("webName", biddingInfoDTO.getWebName()));
        }
        /**
         * 网址
         */
        if (StringUtils.isNotBlank(biddingInfoDTO.getUrl())) {
            biddingInfoDTO.getConditions().add(Restrict.like("url", biddingInfoDTO.getUrl()));
        }
        /**
         * 省份
         */

        if (StringUtils.isNotBlank(biddingInfoDTO.getProvinces())) {
            biddingInfoDTO.getConditions().add(Restrict.like("provinces", biddingInfoDTO.getProvinces()));

        }
        /**
         * 地市
         */
        if (StringUtils.isNotBlank(biddingInfoDTO.getCities())) {
            biddingInfoDTO.getConditions().add(Restrict.like("cities", biddingInfoDTO.getCities()));

        }
    }

    @Override
    public List<BiddingInfoCollectBO> collectBiddingInfo(String[] cities) throws SerException {
        List<BiddingInfoCollectBO> boList = new ArrayList<>();
        List<String> citie = Arrays.asList(cities);
        BiddingInfoDTO dto = new BiddingInfoDTO();
        List<BiddingInfo> biddingInfos = super.findByCis(dto);
        //招投标类型
        String biddingType = null;
        List<String> biddingTypes = new ArrayList<>();
        for (BiddingInfo biddingInfo : biddingInfos) {
            biddingType = biddingInfo.getBiddingType();
            biddingTypes.add(biddingType);
        }

        StringBuffer biddingTypesStr = new StringBuffer("");
        for (String type : biddingTypes) {
            biddingTypesStr.append("'" + type + "'" + ",");
        }
        //业务类型
        String businessType = null;
        List<String> businessTypes = new ArrayList<>();
        for (BiddingInfo biddingInfo : biddingInfos) {
            businessType = biddingInfo.getBusinessType();
            businessTypes.add(businessType);
        }
        StringBuffer businessTypeStr = new StringBuffer("");
        for (String type : businessTypes) {
            businessTypeStr.append("'" + type + "'" + ",");
        }
        for (String citieStr : citie) {
            String[] fields = new String[]{"cities", "counts", "remark"};
            //招投标类型
            String sql = " SELECT cities AS cities,count(*) as counts,biddingType AS remark " +
                    " FROM bidding_biddinginfo " +
                    " WHERE biddingType IN (" + StringUtils.substringBeforeLast(biddingTypesStr + "", ",") + ") AND cities = '" + citieStr + "' GROUP BY biddingType,cities ORDER BY biddingType asc ";
            List<Map<String, String>> biddingMap = new ArrayList<>();
            List<BiddingInfoCollectBO> collectBOS = super.findBySql(sql, BiddingInfoCollectBO.class, fields);
            biddingMap = sqlQueryString(biddingTypes, collectBOS, biddingMap);
            //业务类型
            String sql1 = " SELECT cities AS cities,count(*) as count,businessType AS businessType" +
                    " FROM bidding_biddinginfo" +
                    " WHERE businessType IN (" + StringUtils.substringBeforeLast(businessTypeStr + "", ",") + ") AND cities = '" + citieStr + "' GROUP BY businessType,cities ORDER BY businessType asc ";
            List<Map<String, String>> businessMap = new ArrayList<>();
            collectBOS = super.findBySql(sql1, BiddingInfoCollectBO.class, fields);
            businessMap = sqlQueryString(businessTypes, collectBOS, businessMap);

            BiddingInfoCollectBO bo = new BiddingInfoCollectBO();
            bo.setCities(citieStr);
            bo.setBiddingMap(biddingMap);
            bo.setBusinessMap(businessMap);
            boList.add(bo);
        }
        boList = calculate(citie, biddingTypes, businessTypes, boList);
        return boList;

    }

    private List<BiddingInfoCollectBO> calculate(List<String> cities, List<String> biddingTypes,
                                                 List<String> businessTypes, List<BiddingInfoCollectBO> boList) throws SerException {
        StringBuffer citiesStr = new StringBuffer("");
        for (String citie : cities) {
            citiesStr.append("'" + citie + "',");
        }
        StringBuffer biddingTypesStr = new StringBuffer("");
        for (String biddingType : biddingTypes) {
            biddingTypesStr.append("'" + biddingType + "',");
        }
        StringBuffer businessTypesStr = new StringBuffer("");
        for (String businessType : businessTypes) {
            businessTypesStr.append("'" + businessType + "',");
        }
        String[] fields = new String[]{"counts", "remark"};
        //招投标类型
        String sql = " SELECT count(*) as counts,biddingType AS remark " +
                " FROM bidding_biddinginfo " +
                " WHERE biddingType IN (" + StringUtils.substringBeforeLast(biddingTypesStr + "", ",") + ") AND cities in(" + StringUtils.substringBeforeLast(citiesStr + "", ",") + ") GROUP BY biddingType ORDER BY biddingType asc ";
        List<Map<String, String>> biddingMap = new ArrayList<>();
        List<BiddingInfoCollectBO> collectBOS = super.findBySql(sql, BiddingInfoCollectBO.class, fields);
        biddingMap = sqlQueryString(biddingTypes, collectBOS, biddingMap);
        //业务类型
        String sql1 = " SELECT count(*) as count,businessType AS businessType" +
                " FROM bidding_biddinginfo" +
                " WHERE businessType IN (" + StringUtils.substringBeforeLast(businessTypesStr + "", ",") + ") AND cities in (" + StringUtils.substringBeforeLast(citiesStr + "", ",") + ") GROUP BY businessType ORDER BY businessType asc ";
        List<Map<String, String>> businessMap = new ArrayList<>();
        collectBOS = super.findBySql(sql1, BiddingInfoCollectBO.class, fields);
        businessMap = sqlQueryString(businessTypes, collectBOS, businessMap);

        BiddingInfoCollectBO bo = new BiddingInfoCollectBO();
        bo.setCities("合计");
        bo.setBiddingMap(biddingMap);
        bo.setBusinessMap(businessMap);
        boList.add(bo);
        return boList;
    }

    /**
     * 数据库查询返回，然后添加map数组
     */
    public List<Map<String, String>> sqlQueryString(List<String> obj, List<BiddingInfoCollectBO> collectBOS, List<Map<String, String>> mapList) throws SerException {
        if (collectBOS != null && collectBOS.size() > 0) {
            if (obj.size() == collectBOS.size()) {
                for (BiddingInfoCollectBO cbo : collectBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", cbo.getRemark());
                    areaMap.put("counts", String.valueOf(cbo.getCounts() == null ? 0 : cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (collectBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (BiddingInfoCollectBO cb : collectBOS) {
                    cbStr.add(cb.getRemark());
                }

                //获取到所有不同的  如：地区
                List<String> diffrent = new ArrayList<>();
                for (String o : obj) {
                    if (!cbStr.contains(o)) {
                        diffrent.add(o);
                    }
                }

                //存map
                for (BiddingInfoCollectBO cbo : collectBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", cbo.getRemark());
                    areaMap.put("counts", String.valueOf(cbo.getCounts() == null ? 0 : cbo.getCounts()));
                    mapList.add(areaMap);
                }
                for (String dif : diffrent) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", dif);
                    areaMap.put("counts", String.valueOf(0));
                    mapList.add(areaMap);
                }


            }
        }
        Collections.sort(mapList, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                //进行判断
                return ((String) o1.get("remark")).compareTo((String) o2.get("remark"));
            }
        });
        return mapList;
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

    @Override
    public OptionBO dayFigureCollect(BiddingCollectTO to) throws SerException {
        LocalDate time = null;
        if (to.getTime() != null) {
            time = DateUtil.parseDate(to.getTime());
        } else {
            time = LocalDate.now();
        }
        String startDate = DateUtil.dateToString(time);
        String endDate = DateUtil.dateToString(time);
        String text_1 = "招投标管理日汇总" + "(" + startDate + "-" + endDate + ")";
        return figureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO weekFigureCollect(BiddingCollectTO to) throws SerException {
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
        String startDate = String.valueOf(time[0]);
        String endDate = String.valueOf(time[1]);
        String text_1 = "招投标管理周汇总" + "(" + startDate + "-" + endDate + ")";
        return figureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO monthFigureCollect(BiddingCollectTO to) throws SerException {
        Integer year = 0;
        Integer month = 0;
        if (to.getYear() != null && to.getMonth() != null) {
            year = to.getYear();
            month = to.getMonth();
        } else {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "招投标管理月汇总" + "(" + startDate + "-" + endDate + ")";
        return figureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO totalFigureCollect(BiddingCollectTO to) throws SerException {
        String startDate = " ";
        String endDate = to.getTime();
        String sql = "select updateTime as updateTime from  " + getTableName(BiddingInfo.class) + " where updateTime<= '" + endDate + "' ";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        String text_1 = "招投标管理累计汇总" + "(累计)";
        return figureCollect(startDate, endDate, text_1);
    }

    private OptionBO figureCollect(String startDate, String endDate, String text_1) throws SerException {
        List<BiddingFigureBO> boList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] fields = new String[]{"businessType", "biddinginfoNum", "projectEstimatesNum", "scaleNum",
                "biddingProjectNum", "contendNum", "bidNum", "winNum"};
        sb.append(" SELECT a.businessType as businessType, ");
        sb.append(" count(a.businessType) as biddinginfoNum,count(a.is_projectEstimates) as projectEstimatesNum, ");
        sb.append(" a.scale AS scaleNum,count(a.is_passProjectEstimates) AS biddingProjectNum, ");
        sb.append(" count(b.competitive) as contendNum, count(b.is_finish) AS bidNum, ");
        sb.append(" count(b.is_passProjectEstimates) AS winNum ");
        sb.append(" FROM bidding_biddinginfo a,bidding_bidopeninginfo b ");
        sb.append(" WHERE a.businessType=b.businessType AND a.is_projectEstimates=1 ");
        sb.append(" AND a.is_passProjectEstimates=1 AND b.is_finish=1 AND b.is_passProjectEstimates=1 ");
        sb.append(" AND a.updateTime = b.updateTime AND a.updateTime between'" + startDate + "' and '" + endDate + "'");
        sb.append(" GROUP BY a.businessType,b.businessType,a.scale, b.competitive ");
        boList = super.findBySql(sb.toString(), BiddingFigureBO.class, fields);
//        List<Object> objects = super.findBySql(sb.toString());
//        Object[] object = (Object[]) objects.get(0);
//        String businessType = String.valueOf(object[0]);
//        Integer biddinginfoNum = Integer.parseInt(String.valueOf(object[1]));//招标的信息数量
//        Integer projectEstimatesNum = Integer.parseInt(String.valueOf(object[2]));//可项目测算数量
//        Integer scaleNum = Integer.parseInt(String.valueOf(object[3]));//可投标规模数量
//        Integer biddingProjectNum = Integer.parseInt(String.valueOf(object[4]));//可投标项目数量
//        Integer contendNum = Integer.parseInt(String.valueOf(object[5]));//竞争对手
//        Integer bidNum = Integer.parseInt(String.valueOf(object[6]));//标书制作数
//        Integer winNum = Integer.parseInt(String.valueOf(object[7]));//中标数量
//        BiddingFigureBO bo = new BiddingFigureBO();
//        bo.setBusinessType(businessType);
//        bo.setBiddinginfoNum(biddinginfoNum);
//        bo.setProjectEstimatesNum(projectEstimatesNum);
//        bo.setScaleNum(scaleNum);
//        bo.setBiddingProjectNum(biddingProjectNum);
//        bo.setContendNum(contendNum);
//        bo.setBidNum(bidNum);
//        bo.setWinNum(winNum);
//        boList.add(bo);

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"招标的信息数量", "可项目测算数量",
                "可投标规模数量", "可投标项目数量", "竞争对手",
                "标书制作数", "中标数量"};


        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();
//        xAxisBO.setData(text_3);
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (boList != null && boList.size() > 0) {
            List<Integer> biddinginfoNums = new ArrayList<>();
            List<Integer> projectEstimatesNums = new ArrayList<>();
            List<Integer> scaleNums = new ArrayList<>();
            List<Integer> biddingProjectNums = new ArrayList<>();
            List<Integer> contendNums = new ArrayList<>();
            List<Integer> bidNums = new ArrayList<>();
            List<Integer> winNums = new ArrayList<>();
            for (BiddingFigureBO figureBO : boList) {
                text_list_3.add(figureBO.getBusinessType());

                //柱状图数据
                biddinginfoNums.add(figureBO.getBiddinginfoNum());
                projectEstimatesNums.add(figureBO.getProjectEstimatesNum());
                scaleNums.add(figureBO.getScaleNum());
                biddingProjectNums.add(figureBO.getBiddingProjectNum());
                contendNums.add(figureBO.getContendNum());
                bidNums.add(figureBO.getBidNum());
                winNums.add(figureBO.getWinNum());
            }
            List<List<Integer>> nums = new ArrayList<>();
            nums.add(biddinginfoNums);
            nums.add(projectEstimatesNums);
            nums.add(scaleNums);
            nums.add(biddingProjectNums);
            nums.add(contendNums);
            nums.add(bidNums);
            nums.add(winNums);
            String[] ziduan = new String[]{"招标的信息数量", "可项目测算数量",
                    "可投标规模数量", "可投标项目数量", "竞争对手",
                    "标书制作数", "中标数量"};
//            List<SeriesBO> seriesBOList = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(ziduan[i]);
                seriesBO.setType("bar");
                Integer[] text_int_4 = new Integer[nums.get(0).size()];
                text_int_4 = nums.get(0).toArray(text_int_4);
                seriesBO.setData(text_int_4);
                seriesBOList.add(seriesBO);
            }
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        legendBO.setData(text_2);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }
    //    @Override
//    public List<String> info(SearchDTO to) throws SerException {
//        try {

    //创建连接
//            URL u = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
//            //得到输入流 也就是读取源代码
//            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gbk"));
//            String line = null;
//            StringBuilder sb = new StringBuilder();
//            while ((line = bf.readLine()) != null) {
//                sb.append(line);
//            }
//            String url="";
//            HttpGet request = new HttpGet(to.getUrl());
//            HttpClient client = HttpClients.createDefault();
//            HttpResponse response = client.execute(request);
//            String content = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
//
//            StringBuilder sb = new StringBuilder();
//            sb.append(content);
//            if (content.contains(to.getTitle())) {
//                if (content.contains("<HR")) {
//                    int index = sb.indexOf("<h1");
//                    int nextIndex = sb.indexOf("/h1>");
//                    while ((index >= 0 && nextIndex >= 0) && (nextIndex > index)) {
//                        if (index < nextIndex) {
//                            sb.delete(index, nextIndex + 1);
//                        }
//                        index = sb.indexOf("<h1");
//                        nextIndex = sb.indexOf("/h1>");
//                    }

    //                }
//                Pattern pattern = Pattern.compile("[\u4E00-\u9FA5|\\！|\\,|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
//                Pattern pattern = Pattern.compile("<a.+" + to.getTitle() + ".+</a>");
//                Matcher matcher = pattern.matcher(sb.toString());
//                StringBuilder result = new StringBuilder();
//                List<String> list = new ArrayList<>();
//                List<String> list1 = new ArrayList<>();
//                while (matcher.find()) {
//                    list.add(matcher.group().toString());
//                }
//                for (String s : list) {
//                    int index = s.indexOf("/");
//                    StringBuilder target = new StringBuilder(s);
//                    String string = "<a href=\"https://b2b.10086.cn";
//                    if (index > 0) {
//                        target.delete(0, index);
//                    }
//                    list1.add(new StringBuilder(string).append(target).toString());
//                }
//                return list1;
//                return result.toString();
//                if (content.contains("<script") || content.contains("/script>")) {
//                    int index = sb.indexOf("<script");
//                    int nextIndex = sb.indexOf("/script>");
//                    while ((index >= 0 && nextIndex >= 0)&& (nextIndex > index)) {
//                        if (index < nextIndex) {
//                            sb.delete(index, nextIndex + 1);
//                        }
//                        index = sb.indexOf("<script");
//                        nextIndex = sb.indexOf("/script>");
//                    }
//                }
//                //进行源代码的匹配是否包含‘<’,'>'
//                if (sb.toString().contains("<") || sb.toString().contains(">")) {
//                    int index = sb.indexOf("<");
//                    int nextIndex = sb.indexOf(">");
//                    while ((index >= 0 && nextIndex >= 0) && (nextIndex > index)) {
//                        //删除‘<’,'>'标签
//                        sb.delete(index, nextIndex + 1);
//                        index = sb.indexOf("<");
//                        nextIndex = sb.indexOf(">");
//                    }
//                }
//                Pattern pattern=Pattern.compile("[\u4E00-\u9FA5|\\！|\\,|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
//                Matcher matcher=pattern.matcher(sb.toString());
//                StringBuilder result=new StringBuilder();
//                while (matcher.find()){
//                    result.append(matcher.group());
//                }
//                return result.toString();
//            }
//            return null;
//        } catch (Exception e) {
//            throw new SerException(e.getMessage());
//        }
//    }


    @Override
    public Long infoTotal() throws SerException {
        long limit = 20;
        long totals = 10 * limit;
        return totals;
    }


    @Override
    public List<InfoBO> info(SearchDTO dto) throws SerException {
        try {
            List<InfoBO> boList = new ArrayList<>();
            String url = "https://b2b.10086.cn/b2b/main/listVendorNoticeResult.html?noticeBean.noticeType=2";
            HttpPost request = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String page = String.valueOf(dto.getPage() + 1);
            String limit = String.valueOf(21);
            params.add(new BasicNameValuePair("page.currentPage", page));
            params.add(new BasicNameValuePair("page.perPageSize", limit));
            params.add(new BasicNameValuePair("noticeBean.sourceCH", ""));
            params.add(new BasicNameValuePair("noticeBean.source", ""));
            params.add(new BasicNameValuePair("noticeBean.title", dto.getTitle()));
            params.add(new BasicNameValuePair("noticeBean.startDate", dto.getStartTime()));
            params.add(new BasicNameValuePair("noticeBean.endDate", dto.getEndTime()));

            request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(request);
            String content = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            content = content.replaceAll("\\r|\\n|\\t", "");
            List<InfoBO> infoBOS = init(content);
            for (InfoBO s : infoBOS) {
                s.setLink("https://b2b.10086.cn/b2b/main/viewNoticeContent.html?noticeBean.id=" + s.getId());
                boList.add(s);
            }
            return boList;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    private static List<InfoBO> init(String content) {
        List<InfoBO> infoBOS = new ArrayList<>();
        String[] strings = content.split("</tr>");
        boolean first = true;
        for (String s : strings) {
            if (!first && s.trim().startsWith("<tr")) {
                String name = StringUtils.substringAfterLast(s, " <a href=\"#this\"");
                name = StringUtils.substringAfter(name, ">");
                name = StringUtils.substringBefore(name, "</a>");
                if (name.indexOf("title=") != -1) {
                    name = name.replace("title=", "");
                }
                String id = StringUtils.substringAfter(s, "onclick=\"selectResult('");
                id = StringUtils.substringBefore(id, "')\">");

                String date = StringUtils.substringAfterLast(s, " align=\"left\">");
                date = StringUtils.substringBefore(date, "</td>");
                if (StringUtils.isNotBlank(date) && StringUtils.isNotBlank(name) && StringUtils.isNotBlank(id)) {
                    InfoBO infoBO = new InfoBO(name, date, id);
                    infoBOS.add(infoBO);
                }
            }
            first = false;

        }
        return infoBOS;

    }

    @Override
    public Long txzbTotal() throws SerException {
        long limit = 11;
        long totals = 20 * limit;
        return totals;
    }

    @Override
    public List<InfoBO> txzbInfo(SearchDTO dto) throws SerException {
//                Pattern pattern = Pattern.compile("<td[^>]*>\\s*<a[^>]*>" + to.getTitle() + "([^<]*)</a>");
        try {
            List<InfoBO> boList = new ArrayList<>();
            String url = "http://txzb.miit.gov.cn/DispatchAction.do?reg=denglu&pagesize=11";
            HttpPost request = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String page = String.valueOf(dto.getPage() + 1);
            params.add(new BasicNameValuePair("efFormEname", "POIX14"));
            params.add(new BasicNameValuePair("methodName", "initLoad"));
            params.add(new BasicNameValuePair("page", page));
            params.add(new BasicNameValuePair("name", dto.getTitle()));
            params.add(new BasicNameValuePair("date1", dto.getStartTime()));
            params.add(new BasicNameValuePair("date2", dto.getEndTime()));

            request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(request);
            String content = EntityUtils.toString(response.getEntity(), Consts.UTF_8);

            StringBuilder sb = new StringBuilder();
            sb.append(content);
            content = content.replaceAll("\\r|\\n|\\t", "");
            List<InfoBO> infoBOS = txzb(content);
            for (InfoBO infoBO : infoBOS) {
                infoBO.setLink("http://txzb.miit.gov.cn" + infoBO.getId());
                boList.add(infoBO);
            }
            return boList;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    private static List<InfoBO> txzb(String content) {
        List<InfoBO> boList = new ArrayList<>();
        String[] strings = content.split("</tr>");
        for (String s : strings) {
            if (s.trim().startsWith("<tr")) {
                String name = StringUtils.substringAfter(s, "target=\"_blank\" >");
                name = StringUtils.substringBefore(name, "&nbsp;");
                String id = StringUtils.substringAfter(s, "href=\"").trim();
                id = StringUtils.substringBefore(id, "\"target=\"_blank\"");
                if (id.indexOf("target=") != -1) {
                    id = StringUtils.substringBefore(id, "\" target=");
                }
                String date = StringUtils.substringAfterLast(s, "&nbsp; ").trim();
                date = StringUtils.substringBefore(date, "</a>");
                if (StringUtils.isNotBlank(date) && StringUtils.isNotBlank(name) && StringUtils.isNotBlank(id)) {
                    InfoBO infoBO = new InfoBO(name, date, id);
                    boList.add(infoBO);
                }
            }
        }
        return boList;
    }

    @Override
    public List<InfoBO> zycgInfo(SearchDTO dto) throws SerException {
        List<InfoBO> boList = new ArrayList<>();
        try {
            String url = "http://www.zycg.gov.cn/article/article_search?catalog=StockAffiche&page=" + dto.getPage() + 1 + "";

            HttpGet request = new HttpGet(url);
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(request);
            String content = EntityUtils.toString(response.getEntity(), Consts.UTF_8);

            StringBuilder sb = new StringBuilder();
            sb.append(content);
            content = content.replaceAll("\\r|\\n|\\t", "");
            List<InfoBO> infoBOS = zycg(content);
            String startTime = dto.getStartTime();
            String endTime = dto.getEndTime();
            LocalDate start = null;
            LocalDate end = null;
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                start = DateUtil.parseDate(startTime);
                end = DateUtil.parseDate(endTime);
            }
            for (InfoBO infoBO : infoBOS) {
                LocalDate date = DateUtil.parseDate(infoBO.getDate());
                infoBO.setLink("http://www.zycg.gov.cn/article/show/" + infoBO.getId());
                if (StringUtils.isNotBlank(dto.getTitle()) && infoBO.getTitle().contains(dto.getTitle()) && (null != start) && (null != end)) {
                    if ((date.isAfter(start) || date.isEqual(start)) && (date.isBefore(end) || date.isEqual(end))) {
                        boList.add(infoBO);
                    }
                } else if (StringUtils.isNotBlank(dto.getTitle()) && infoBO.getTitle().contains(dto.getTitle())) {
                    boList.add(infoBO);
                } else if ((null != start) && (null != end)) {
                    if ((date.isAfter(start) || date.isEqual(start)) && (date.isBefore(end) || date.isEqual(end))) {
                        boList.add(infoBO);
                    }
                } else if (StringUtils.isBlank(dto.getTitle()) && StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
                    boList.add(infoBO);
                }
            }
            return boList;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }

    }

    @Override
    public Long zycyTotal() throws SerException {
        String url = "http://www.zycg.gov.cn/article/article_search?catalog=StockAffiche";
        HttpGet request = new HttpGet(url);
        HttpClient client = HttpClients.createDefault();
        String content = "";
        try {
            HttpResponse response = client.execute(request);
            content = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(content);
        content = content.replaceAll("\\r|\\n|\\t", "");
        String reg = "共.+条记录";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(content);
        long totals = 0;
        if (matcher.find()) {
            String s = matcher.group();
            s = StringUtils.substringAfterLast(s, "共");
            s = StringUtils.substring(s, 0, -3);
            totals = Long.parseLong(s);
        }

        return totals;
    }

    private static List<InfoBO> zycg(String content) throws SerException {
        List<InfoBO> infoBOS = new ArrayList<>();
        String[] strings = content.split("</li>");
        for (String s : strings) {
            if (s.trim().startsWith("<li>    <span>")) {
                String date = StringUtils.substringBefore(s, "</span>");
                date = StringUtils.substringAfter(date, "<span>").trim();
                date = date.substring(1, date.length() - 1);
                String id = StringUtils.substringAfter(s, "article/show/");
                id = StringUtils.substringBefore(id, "\"  target").trim();
                String name = StringUtils.substringBefore(s, "</a>");
                name = StringUtils.substringAfterLast(name, ">").trim();
                if (StringUtils.isNotBlank(date) && StringUtils.isNotBlank(name) && StringUtils.isNotBlank(id)) {
                    InfoBO infoBO = new InfoBO(name, date, id);
                    infoBOS.add(infoBO);
                }
            }
        }
        return infoBOS;
    }


    @Override
    public Long caigouTotal() throws SerException {
        String url = "http://www.110caigou.com/zhaobiao.asp";
        HttpGet request = new HttpGet(url);
        HttpClient client = HttpClients.createDefault();
        String content = "";
        try {
            HttpResponse response = client.execute(request);
            content = EntityUtils.toString(response.getEntity(), "gb2312");
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(content);
        content = content.replaceAll("\\r|\\n|\\t", "");
        String reg = "共搜索到.+条信息";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(content);
        long totals = 0;
        if (matcher.find()) {
            String s = matcher.group();
            s = StringUtils.substringAfterLast(s, "<font color=\"#FF2D00\">[");
            s = StringUtils.substringBefore(s, "]</font>");
            totals = Long.parseLong(s);
        }

        return totals;
    }

    @Override
    public List<InfoBO> caigouInfo(SearchDTO dto) throws SerException {
        try {
            List<InfoBO> boList = new ArrayList<>();
            HttpGet request = new HttpGet("http://www.110caigou.com/zhaobiao.asp?Page=" + dto.getPage() + 1 + "");
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(request);
            String content = EntityUtils.toString(response.getEntity(), "gb2312");
            StringBuilder sb = new StringBuilder();
            sb.append(content);
            content = content.replaceAll("\\r|\\n|\\t", "");
            List<InfoBO> infoBOS = caigou(content);
            String startTime = dto.getStartTime();
            String endTime = dto.getEndTime();
            LocalDate start = null;
            LocalDate end = null;
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                start = DateUtil.parseDate(startTime);
                end = DateUtil.parseDate(endTime);
            }
            for (InfoBO infoBO : infoBOS) {
                LocalDate date = DateUtil.parseDate(infoBO.getDate());
                infoBO.setLink("http://www.110caigou.com/showzhaobiao.asp?id=" + infoBO.getId());
                if (StringUtils.isNotBlank(dto.getTitle()) && infoBO.getTitle().contains(dto.getTitle()) && (null != start) && (null != end)) {
                    if ((date.isAfter(start) || date.isEqual(start)) && (date.isBefore(end) || date.isEqual(end))) {
                        boList.add(infoBO);
                    }
                } else if (StringUtils.isNotBlank(dto.getTitle()) && infoBO.getTitle().contains(dto.getTitle())) {
                    boList.add(infoBO);
                } else if ((null != start) && (null != end)) {
                    if ((date.isAfter(start) || date.isEqual(start)) && (date.isBefore(end) || date.isEqual(end))) {
                        boList.add(infoBO);
                    }
                } else if (StringUtils.isBlank(dto.getTitle()) && StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
                    boList.add(infoBO);
                }
            }
            return boList;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }

    }

    public static List<InfoBO> caigou(String content) {
        List<InfoBO> boList = new ArrayList<>();
        String[] strings = content.split("</TR>");
        for (String s : strings) {
            if (s.trim().startsWith("<TR")) {
                String date = StringUtils.substringBefore(s, "]");
                date = StringUtils.substringAfter(date, "height=35>[").trim();
                if (date.length() > 0) {

                    StringBuilder sb = new StringBuilder();
                    sb.append(date);
                    String s1 = date.charAt(6) + "";
                    try {
                        Integer.parseInt(s1);
                    } catch (Exception e) {
                        sb.insert(5, "0");
                    }
                    if (sb.length() < 9) {
                        sb.insert(9, "0");
                    }
                    date = sb.toString().replaceAll("/", "-");
                }

                String id = StringUtils.substringAfter(s, "showzhaobiao.asp?id=");
                id = StringUtils.substringBefore(id, "\" ").trim();
                String name = StringUtils.substringBefore(s, "</A>");
                name = StringUtils.substringAfterLast(name, ">").trim();
                if (StringUtils.isNotBlank(date) && StringUtils.isNotBlank(name) && StringUtils.isNotBlank(id)) {
                    InfoBO infoBO = new InfoBO(name, date, id);

                    boList.add(infoBO);
                }
            }
        }
        return boList;
    }

    @Override
    public Long toobiaoTotal() throws SerException {
        long limit = 23;
        long totals = 10 * limit;
        return totals;
    }

    @Override
    public List<InfoBO> toobiaoInfo(SearchDTO dto) throws SerException {
        try {
            List<InfoBO> boList = new ArrayList<>();
            String url = "http://www.dlzb.com/zb/search.php?catid=0&areaid=0&page=" + dto.getPage() + 1 + "";

            HttpGet request = new HttpGet(url);
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(request);
            String content = EntityUtils.toString(response.getEntity(), Consts.UTF_8);

            StringBuilder sb = new StringBuilder();
            sb.append(content);
            content = content.replaceAll("\\r|\\n|\\t", "");
            List<InfoBO> infoBOS = dlzb(content);
            String startTime = dto.getStartTime();
            String endTime = dto.getEndTime();
            LocalDate start = null;
            LocalDate end = null;
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                start = DateUtil.parseDate(startTime);
                end = DateUtil.parseDate(endTime);
            }
            for (InfoBO infoBO : infoBOS) {
                LocalDate date = DateUtil.parseDate(infoBO.getDate());
                infoBO.setLink("http://www.dlzb.com" + infoBO.getId());
                if (StringUtils.isNotBlank(dto.getTitle()) && infoBO.getTitle().contains(dto.getTitle()) && (null != start) && (null != end)) {
                    if ((date.isAfter(start) || date.isEqual(start)) && (date.isBefore(end) || date.isEqual(end))) {
                        boList.add(infoBO);
                    }
                } else if (StringUtils.isNotBlank(dto.getTitle()) && infoBO.getTitle().contains(dto.getTitle())) {
                    boList.add(infoBO);
                } else if ((null != start) && (null != end)) {
                    if ((date.isAfter(start) || date.isEqual(start)) && (date.isBefore(end) || date.isEqual(end))) {
                        boList.add(infoBO);
                    }
                } else if (StringUtils.isBlank(dto.getTitle()) && StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
                    boList.add(infoBO);
                }
            }
            return boList;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    private static List<InfoBO> dlzb(String content) {
        List<InfoBO> boList = new ArrayList<>();
        String[] strings = content.split("</li>");
        for (String s : strings) {
            if (s.trim().startsWith("<li")) {

                String name = StringUtils.substringBefore(s, "\">");
                name = StringUtils.substringAfterLast(name, "title=\"").trim();
                String id = StringUtils.substringAfter(s, "href=\"http://www.dlzb.com").trim();
                id = StringUtils.substringBefore(id, "\" target=\"_blank\"");
                String date = StringUtils.substringBefore(s, "</span>");
                date = StringUtils.substringAfterLast(date, "class=\"gc_date\">").trim();
                if (StringUtils.isNotBlank(date) && StringUtils.isNotBlank(name) && StringUtils.isNotBlank(id)) {
                    InfoBO infoBO = new InfoBO(name, date, id);
                    boList.add(infoBO);
                }
            }
        }
        return boList;
    }

//    @Override
//    public List<String> schoolbidInfo(SearchDTO dto) throws SerException {
//        try {
//
//            String url = "http://www.schoolbid.cn/search/?l=zb&k=%E4%B8%AD%E5%9B%BD&lb=0&www=www.schoolbid.cn&kb=zb&dq=&rq=365&lx=2&imageField.x=34&imageField.y=20&xdq=&username=&password=";
//
//            HttpGet request = new HttpGet(url);
//            HttpClient client = HttpClients.createDefault();
//            HttpResponse response = client.execute(request);
//            String content = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
//
//            StringBuilder sb = new StringBuilder();
//            sb.append(content);
//            System.out.println(sb.toString());
//            if (content.contains("中国")) {
////                Pattern pattern = Pattern.compile("<a.+" + "中国" + ".+</a>");
//                String a = content.replaceAll("\\r|\\n|\\t", "");
//                Pattern pattern = Pattern.compile("<td[^>]*>\\s*<a[^>]*>([^<]*)</a>");
//                Matcher matcher = pattern.matcher(a);
////                Matcher matcher = pattern.matcher(sb.toString());
//                System.out.println(matcher);
//                List<String> list = new ArrayList<>();
//                List<String> list1 = new ArrayList<>();
//                while (matcher.find()) {
//                    list.add(matcher.group().toString());
//                }
//                for (String s : list) {
//                    int index = s.indexOf("/");
//                    StringBuilder target = new StringBuilder(s);
//                    String string = "<a href=\"http://www.schoolbid.cn/";
//                    if (index > 0) {
//                        target.delete(0, index);
//                    }
//                    list1.add(new StringBuilder(string).append(target).toString());
//                }
//                return list1;
//            }
//            return null;
//        } catch (Exception e) {
//            throw new SerException(e.getMessage());
//        }
//
//
//    }
}




