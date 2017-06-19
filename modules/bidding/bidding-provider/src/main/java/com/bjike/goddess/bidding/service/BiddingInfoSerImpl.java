package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingInfoBO;
import com.bjike.goddess.bidding.bo.BiddingInfoCollectBO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.entity.BiddingInfo;
import com.bjike.goddess.bidding.enums.BiddingType;
import com.bjike.goddess.bidding.enums.BusinessType;
import com.bjike.goddess.bidding.enums.GuideAddrStatus;
import com.bjike.goddess.bidding.excel.BiddingInfoExport;
import com.bjike.goddess.bidding.excel.SonPermissionObject;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
            DateUtil.parseDate(biddingInfoTO.getRegistrationTime());
            DateUtil.parseDate(biddingInfoTO.getBiddingTime());
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
        List<BiddingInfo> biddingInfo = super.findByCis(biddingInfoDTO, true);
        List<BiddingInfoBO> biddingInfoBOS = BeanTransform.copyProperties(biddingInfo, BiddingInfoBO.class);
        return biddingInfoBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingInfoBO insertBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        checkAddIdentity();
        checkDate(biddingInfoTO);
        BiddingInfo biddingInfo = BeanTransform.copyProperties(biddingInfoTO, BiddingInfo.class, true);
        biddingInfo.setId(biddingInfoTO.getId());
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
        BeanTransform.copyProperties(biddingInfoTO, biddingInfo, true);
        checkDate(biddingInfoTO);
        biddingInfo.setModifyTime(LocalDateTime.now());
        super.update(biddingInfo);
        return BeanTransform.copyProperties(biddingInfoTO, BiddingInfoBO.class);
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

    @Override
    public List<BiddingInfoBO> searchBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
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
        List<BiddingInfo> biddingInfos = super.findByCis(biddingInfoDTO, true);
        List<BiddingInfoBO> biddingInfoBOS = BeanTransform.copyProperties(biddingInfos, BiddingInfoBO.class);
        return biddingInfoBOS;
    }

    @Override
    public List<BiddingInfoCollectBO> collectBiddingInfo(String[] cities) throws SerException {
        if (cities == null || cities.length <= 0) {
            throw new SerException("汇总失败，请选择地市");
        }
        String[] citiesTemp = new String[cities.length];
        for (int i = 0; i < cities.length; i++) {
            citiesTemp[i] = "'" + cities[i] + "'";
        }
        String citiesStr = StringUtils.join(citiesTemp, ',');
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * from ");
        sb.append(" (SELECT A.*,B.mobile,B.soft,B.system,B.plan FROM ");
        sb.append(" (SELECT cities,max(CASE WHEN biddingType=0 THEN biddingTypeCounts END ) AS invite, ");
        sb.append(" max(CASE WHEN biddingType=1 THEN biddingTypeCounts END ) AS openly FROM ");
        sb.append(" (select count(*) AS biddingTypeCounts,biddingType AS biddingType,cities as cities ");
        sb.append(" FROM bidding_biddinginfo a WHERE cities IN (%s) GROUP BY biddingType,cities ORDER BY cities )a GROUP BY cities)A, ");
        sb.append(" (SELECT cities,max(CASE WHEN businessType=0 THEN businessTypeCounts END )AS mobile, ");
        sb.append(" max(CASE WHEN businessType=2 THEN businessTypeCounts END )AS soft, ");
        sb.append(" max(CASE WHEN businessType=3 THEN businessTypeCounts END )AS system, ");
        sb.append(" max(CASE WHEN businessType=4 THEN businessTypeCounts END )AS plan FROM ");
        sb.append(" (SELECT count(*) AS businessTypeCounts,businessType as businessType,cities as cities ");
        sb.append(" FROM bidding_biddinginfo a WHERE cities IN (%s) GROUP BY businessType,cities ORDER BY cities)a GROUP BY cities)B ");
        sb.append(" WHERE A.cities=B.cities)C ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' as area ,sum(invite) AS invite,sum(openly) AS openly,sum(mobile) AS mobile, ");
        sb.append(" sum(soft) AS soft,sum(system) AS system,sum(plan) AS plan from ");
        sb.append(" (SELECT A.*,B.mobile,B.soft,B.system,B.plan FROM ");
        sb.append(" (SELECT cities,max(CASE WHEN biddingType=0 THEN biddingTypeCounts END ) AS invite, ");
        sb.append(" max(CASE WHEN biddingType=1 THEN biddingTypeCounts END ) AS openly FROM ");
        sb.append(" (select count(*) AS biddingTypeCounts,biddingType AS biddingType,cities as cities ");
        sb.append(" FROM bidding_biddinginfo a WHERE cities IN (%s) GROUP BY biddingType,cities ORDER BY cities )a GROUP BY cities)A, ");
        sb.append(" (SELECT cities,max(CASE WHEN businessType=0 THEN businessTypeCounts END )AS mobile, ");
        sb.append(" max(CASE WHEN businessType=1 THEN businessTypeCounts END )AS soft, ");
        sb.append(" max(CASE WHEN businessType=2 THEN businessTypeCounts END )AS system, ");
        sb.append(" max(CASE WHEN businessType=3 THEN businessTypeCounts END )AS plan FROM ");
        sb.append(" (SELECT count(*) AS businessTypeCounts,businessType as businessType,cities as cities ");
        sb.append(" FROM bidding_biddinginfo a WHERE cities IN (%s) GROUP BY businessType,cities ORDER BY cities)a GROUP BY cities)B ");
        sb.append(" WHERE A.cities=B.cities)C ");
        String sql = sb.toString();
        sql = String.format(sql, citiesStr, citiesStr, citiesStr, citiesStr);
        String[] fields = new String[]{"cities", "invite", "openly", "mobile", "soft", "system", "plan"};
        List<BiddingInfoCollectBO> biddingInfoCollectBOS = super.findBySql(sql, BiddingInfoCollectBO.class, fields);
        return biddingInfoCollectBOS;
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
    public byte[] exportExcel(BiddingInfoDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getProjectName())) {
            dto.getConditions().add(Restrict.eq("projectName", dto.getProjectName()));
        }
        List<BiddingInfo> list = super.findByCis(dto);

        List<BiddingInfoExport> biddingInfoExports = new ArrayList<>();
        list.stream().forEach(str -> {
            BiddingInfoExport export = BeanTransform.copyProperties(str, BiddingInfoExport.class, "biddingType", "businessType");
            export.setBiddingType(BiddingType.exportStrConvert(str.getBiddingType()));
            export.setBusinessType(BusinessType.exportStrConvert(str.getBusinessType()));
            biddingInfoExports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(biddingInfoExports, excel);
        return bytes;
    }


}