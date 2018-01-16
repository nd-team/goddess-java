package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contractcommunicat.bo.*;
import com.bjike.goddess.contractcommunicat.dao.BusinessNegotiationRep;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationCollectDTO;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationDTO;
import com.bjike.goddess.contractcommunicat.entity.BusinessNegotiation;
import com.bjike.goddess.contractcommunicat.enums.CollectTimeType;
import com.bjike.goddess.contractcommunicat.enums.GuideAddrStatus;
import com.bjike.goddess.contractcommunicat.excel.BusinessNegotiationExport;
import com.bjike.goddess.contractcommunicat.excel.BusinessNegotiationTemplateExcel;
import com.bjike.goddess.contractcommunicat.to.BusinessNegotiationTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.vo.BusinessNegotiationCollectVO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scala.collection.parallel.mutable.ParTrieMapSplitter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * 商务洽谈业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-28 11:24 ]
 * @Description: [ 商务洽谈业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractcommunicatSerCache")
@Service
public class BusinessNegotiationCollectSerImpl extends ServiceImpl<BusinessNegotiation, BusinessNegotiationCollectDTO> implements BusinessNegotiationCollectSer {
    @Autowired
    BusinessNegotiationRep businessNegotiationRep;

    @Autowired
    UserAPI userAPI;
    @Autowired
    CusPermissionSer cusPermissionSer;

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = to.getGuideAddrStatus();
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
            case DELETE:
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
     * 导航栏核对添加修改删除审核权限（部门级别）
     */
    private Boolean guideAddIdentity() throws SerException {
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


    public void getCusPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
//        Boolean permission = cusPermissionSer.getCusPermission("1");
//
//        if (!permission) {
//            throw new SerException("该模块只有商务部可操作，您的帐号尚无权限");
//        }
//    }
    }

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
    public Long count(BusinessNegotiationCollectDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public List<BusinessNegotiationCollectBO> listBusinessNegotiationCollect(BusinessNegotiationCollectDTO dto, CollectTimeType type, String... param) throws SerException {
        int code = type.getCode();
        String start = null;
        String end = null;
        switch (code) {
            case 1:
                start = param[0];
                end = param[0];
                break;
            case 2:
                LocalDate[] date = DateUtil.getWeekTimes(Integer.parseInt(param[0]), Integer.parseInt(param[1]), Integer.parseInt(param[2]));
                start = String.valueOf(date[0]);
                end = String.valueOf(date[1]);
                break;
            case 3:
                start = LocalDate.of(Integer.parseInt(param[0]), Integer.parseInt(param[1]), 1).toString();
                end = LocalDate.of(Integer.parseInt(param[0]), Integer.parseInt(param[1]), 1).with(TemporalAdjusters.lastDayOfMonth()).toString();
                break;
            case 4:
                String[] date1 = quarter(Integer.parseInt(param[0]), Integer.parseInt(param[1]));
                start = date1[0];
                end = date1[1];
                break;
            case 5:
                start = LocalDate.of(Integer.parseInt(param[0]), 1, 1).toString();
                end = LocalDate.of(Integer.parseInt(param[0]), 12, 1).with(TemporalAdjusters.lastDayOfMonth()).toString();
                break;
            case 6:
                start = null;
                end = param[0];
                break;

        }


        return progressCollect(start, end, dto);
    }

    @Override
    public BNCollectEchartBO listCollectEchart(CollectTimeType type, String... params) throws SerException {
        BusinessNegotiationCollectDTO dto = new BusinessNegotiationCollectDTO("1", null, null, null);
        List<BusinessNegotiationCollectBO> bos = listBusinessNegotiationCollect(dto, type, params);

        return getSICollectEchart(bos, "商务洽谈汇总", "1");
    }

    @Override
    public BNCollectEchartBO listCollectEchartByType(String businessType, CollectTimeType type, String... params) throws SerException {
        BusinessNegotiationCollectDTO dto = new BusinessNegotiationCollectDTO("1", null, null, "1");
        List<BusinessNegotiationCollectBO> bos = listBusinessNegotiationCollect(dto, type, params);

//        for (BusinessNegotiationCollectBO bo : bos) {
//            if (!businessType.equals(bo.getBusinessType())) {
//                bos.remove(bo);
//
//            }
//        }
        for (int i = 0; i < bos.size(); i ++) {
            if (!businessType.equals(bos.get(i).getBusinessType())) {
                bos.remove(i);
                i --;
            }
        }
        return getSICollectEchart(bos, businessType + "业务类型问题归属商务洽谈汇总", "2");

    }

    @Override
    public Set<String> listBusinessType() throws SerException {
        String sql = "select businessType from contractcommunicat_businessnegotiation group by businessType";
        Set<String> set = new HashSet<>();
        List<Object> list = super.findBySql(sql);
        for (Object o : list) {
            set.add(String.valueOf(o));
        }
        return set;
    }

    /**
     * 汇总柱状图图表
     *
     * @param
     * @return class
     * @version v1
     */
    private BNCollectEchartBO getSICollectEchart(List<BusinessNegotiationCollectBO> list, String titleText, String type) {
        String[] xText = new String[list.size()];
        SeriesBO[] series = new SeriesBO[10];
        String[] data1 = new String[list.size()];
        String[] data2 = new String[list.size()];
        String[] data3 = new String[list.size()];
        String[] data4 = new String[list.size()];
        String[] data5 = new String[list.size()];
        String[] data6 = new String[list.size()];
        String[] data7 = new String[list.size()];
        String[] data8 = new String[list.size()];
        String[] data9 = new String[list.size()];
        String[] data10 = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {

            switch (type) {
                case "1":
                    xText[i] = list.get(i).getBusinessType();
                    break;
                case "2":
                    xText[i] = list.get(i).getProblemBelong();
                    break;
            }


            String param1 = list.get(i).getNeedNegotiationAmount() == null ? "" : String.valueOf(list.get(i).getNeedNegotiationAmount());
            String param2 = list.get(i).getContinueFollowUpAmount() == null ? "" : String.valueOf(list.get(i).getContinueFollowUpAmount());
            String param3 = list.get(i).getSolvedAmount() == null ? "" : String.valueOf(list.get(i).getSolvedAmount());
            String param4 = list.get(i).getNotSolvedAmount() == null ? "" : String.valueOf(list.get(i).getNotSolvedAmount());
            String param5 = list.get(i).getMarketForAmount() == null ? "" : String.valueOf(list.get(i).getMarketForAmount());
            String param6 = list.get(i).getRounds() == null ? "" : String.valueOf(list.get(i).getRounds());
            String param7 = list.get(i).getNeedAssistAmount() == null ? "" : String.valueOf(list.get(i).getNeedAssistAmount());
            String param8 = list.get(i).getAssistLetterAmount() == null ? "" : String.valueOf(list.get(i).getAssistLetterAmount());
            String param9 = list.get(i).getHasProjectAmount() == null ? "" : String.valueOf(list.get(i).getHasProjectAmount());
            String param10 = list.get(i).getMarketCostAmount() == null ? "" : String.valueOf(list.get(i).getMarketCostAmount());

            data1[i] = param1;
            data2[i] = param2;
            data3[i] = param3;
            data4[i] = param4;
            data5[i] = param5;
            data6[i] = param6;
            data7[i] = param7;
            data8[i] = param8;
            data9[i] = param9;
            data10[i] = param10;


        }
//        String[] data = new String[2];

        SeriesBO seriesBO1 = new SeriesBO("需要洽谈数量", "bar", data1, "0");
        SeriesBO seriesBO2 = new SeriesBO("需要持续跟进数", "bar", data2, "0");
        SeriesBO seriesBO3 = new SeriesBO("已解决", "bar", data3, "0");
        SeriesBO seriesBO4 = new SeriesBO("未解决", "bar", data4, "0");
        SeriesBO seriesBO5 = new SeriesBO("转换市场招待", "bar", data5, "0");
        SeriesBO seriesBO6 = new SeriesBO("洽谈次数", "bar", data6, "0");
        SeriesBO seriesBO7 = new SeriesBO("需要协助数", "bar", data7, "0");
        SeriesBO seriesBO8 = new SeriesBO("发送协助函数", "bar", data8, "0");
        SeriesBO seriesBO9 = new SeriesBO("洽谈转入已立项数", "bar", data9, "0");
        SeriesBO seriesBO10 = new SeriesBO("洽谈转入商务合同-市场费用数", "bar", data10, "0");

        series[0] = seriesBO1;
        series[1] = seriesBO2;
        series[2] = seriesBO3;
        series[3] = seriesBO4;
        series[4] = seriesBO5;
        series[5] = seriesBO6;
        series[6] = seriesBO7;
        series[7] = seriesBO8;
        series[8] = seriesBO9;
        series[9] = seriesBO10;


        //标题
        TitleBO titleBO = new TitleBO(titleText, "center");

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("axis");

        //横坐标描述
        LegendBO legendBO = new LegendBO(new String[]{"需要洽谈数量", "需要持续跟进数", "已解决", "未解决", "转换市场招待", "洽谈次数", "需要协助数", "发送协助函数", "洽谈转入已立项数", "洽谈转入商务合同-市场费用数"}, "vertical", "left");
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        yAxisBO.setType("value");

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        xAxisBO.setData(xText);
        xAxisBO.setType("category");
        xAxisBO.setBoundaryGap(true);

        BNCollectEchartBO bcCollectEchartBO = new BNCollectEchartBO();
        bcCollectEchartBO.setTitle(titleBO);
        bcCollectEchartBO.setTooltip(tooltipBO);
        bcCollectEchartBO.setLegend(legendBO);
        bcCollectEchartBO.setxAxis(xAxisBO);
        bcCollectEchartBO.setyAxis(yAxisBO);
        bcCollectEchartBO.setSeries(series);
        return bcCollectEchartBO;
    }

    private List<BusinessNegotiationCollectBO> progressCollect(String startTime, String endTime, BusinessNegotiationCollectDTO dto) throws SerException {
        String businessType = dto.getBusinessType();
        String area = dto.getArea();
        String department = dto.getDepartment();
        String problemBelong = dto.getProblemBelong();

        StringBuilder sb = new StringBuilder();
        sb.append("select sum(trip) as trip, sum(rounds) as rounds");
        if (null != businessType) {
            sb.append(", businessType");
        }
        if (null != area) {
            sb.append(", area");
        }
        if (null != department) {
            sb.append(", department");
        }
        if (null != problemBelong) {
            sb.append(", problemBelong");
        }

        sb.append(" from contractcommunicat_businessnegotiation where 1 = 1 ");
        if (null != startTime) {
            sb.append(" and planFollowUp >= '"+ startTime +"'");
        }
        if (null != endTime) {
            sb.append(" and planFollowUp <= '"+ endTime +"'");
        }
        sb.append(" group by");
        if (null != businessType) {
            sb.append(" businessType");
        }
        if (null != area) {
            sb.append(", area");
        }
        if (null != department) {
            sb.append(", department");
        }
        if (null != problemBelong) {
            sb.append(", problemBelong");
        }


        List<String> params = new ArrayList<>();
        params.add("trip");
        params.add("rounds");
        if (null != businessType) {
            params.add("businessType");
        }
        if (null != area) {
            params.add("area");
        }
        if (null != department) {
            params.add("department");
        }
        if (null != problemBelong) {
            params.add("problemBelong");
        }
        List<BusinessNegotiationCollectBO> bos = super.findBySql(sb.toString(), BusinessNegotiationCollectBO.class, (String[]) params.toArray(new String[params.size()]));
        List<BusinessNegotiationCollectBO> boList = new ArrayList<>();
        List<BusinessNegotiationCollectBO> contractProgressBOS = new ArrayList<>();



        if (bos != null) {
            for (BusinessNegotiationCollectBO bo : bos) {
                StringBuilder childSql = new StringBuilder();
                if (null != businessType) {
                    childSql.append(" and businessType = '"+ bo.getBusinessType() +"'");
                }
                if (null != area) {
                    childSql.append(" and area = '"+ bo.getArea() +"'");
                }
                if (null != department) {
                    childSql.append(" and department = '"+ bo.getDepartment() +"'");
                }
                if (null != problemBelong) {
                    childSql.append(" and problemBelong = '"+ bo.getProblemBelong() +"'");
                }
                //需要洽谈数量 todo
                bo.setNeedNegotiationAmount(0);
                //需要持续跟进数
                String[] field1 = new String[]{"continueFollowUpAmount"};
                String sql1 = " SELECT ifnull(count(1),0) AS continueFollowUpAmount FROM contractcommunicat_businessnegotiation where is_continueFollowUp = 1" + childSql;
                contractProgressBOS = super.findBySql(sql1, BusinessNegotiationCollectBO.class, field1);
                for (BusinessNegotiationCollectBO bo1 : contractProgressBOS) {
                    bo.setContinueFollowUpAmount(bo1.getContinueFollowUpAmount());
                }
                //已解决
                String[] field2 = new String[]{"solvedAmount"};
                String sql2 = " SELECT ifnull(count(1),0) AS solvedAmount FROM contractcommunicat_businessnegotiation where is_closedLoop = 1" + childSql;
                contractProgressBOS = super.findBySql(sql2, BusinessNegotiationCollectBO.class, field2);
                for (BusinessNegotiationCollectBO bo1 : contractProgressBOS) {
                    bo.setSolvedAmount(bo1.getSolvedAmount());
                }

                //未解决
                String[] field3 = new String[]{"notSolvedAmount"};
                String sql3 = " SELECT ifnull(count(1),0) AS notSolvedAmount FROM contractcommunicat_businessnegotiation where is_closedLoop = 0" + childSql;
                contractProgressBOS = super.findBySql(sql3, BusinessNegotiationCollectBO.class, field3);
                for (BusinessNegotiationCollectBO bo1 : contractProgressBOS) {
                    bo.setNotSolvedAmount(bo1.getNotSolvedAmount());
                }

                //转换市场招待
                String[] field４ = new String[]{"marketForAmount"};
                String sql４ = " SELECT ifnull(count(1),0) AS marketForAmount FROM contractcommunicat_businessnegotiation where is_marketFor = 1" + childSql;
                contractProgressBOS = super.findBySql(sql４, BusinessNegotiationCollectBO.class, field４);
                for (BusinessNegotiationCollectBO bo1 : contractProgressBOS) {
                    bo.setMarketForAmount(bo1.getMarketForAmount());
                }

                //需要协助数
                String[] field5_5 = new String[]{"needAssistAmount"};
                String sql5_5 = " SELECT ifnull(count(1),0) AS needAssistAmount FROM contractcommunicat_businessnegotiation where is_needAssist = 1" + childSql;
                contractProgressBOS = super.findBySql(sql5_5, BusinessNegotiationCollectBO.class, field5_5);
                for (BusinessNegotiationCollectBO bo1 : contractProgressBOS) {
                    bo.setNeedAssistAmount(bo1.getNeedAssistAmount());
                }

                //发送协助函数次数
                String[] field5 = new String[]{"assistLetterAmount"};
                String sql5 = " SELECT ifnull(count(1),0) AS assistLetterAmount FROM contractcommunicat_businessnegotiation where is_assistLetter = 1" + childSql;
                contractProgressBOS = super.findBySql(sql5, BusinessNegotiationCollectBO.class, field5);
                for (BusinessNegotiationCollectBO bo1 : contractProgressBOS) {
                    bo.setAssistLetterAmount(bo1.getAssistLetterAmount());
                }

                //洽谈转入已立项数
                String[] field6 = new String[]{"hasProjectAmount"};
                String sql6 = " SELECT ifnull(count(1),0) AS hasProjectAmount FROM contractcommunicat_businessnegotiation where is_hasProject = 1" + childSql;
                contractProgressBOS = super.findBySql(sql6, BusinessNegotiationCollectBO.class, field6);
                for (BusinessNegotiationCollectBO bo1 : contractProgressBOS) {
                    bo.setHasProjectAmount(bo1.getHasProjectAmount());
                }

                //洽谈转入已立项金额 todo
                bo.setHasProjectCost(0.0);

                //是否转入合同管理-市场费用 todo
                bo.setMarketCost(0.0);

                //是否转入合同管理-市场费用数
                String[] field7 = new String[]{"marketCostAmount"};
                String sql7 = " SELECT ifnull(count(1),0) AS marketCostAmount FROM contractcommunicat_businessnegotiation where is_marketCost = 1" + childSql;
                contractProgressBOS = super.findBySql(sql7, BusinessNegotiationCollectBO.class, field7);
                for (BusinessNegotiationCollectBO bo1 : contractProgressBOS) {
                    bo.setMarketCostAmount(bo1.getMarketCostAmount());
                }


                boList.add(bo);
            }
        }
        return boList;
    }

    //季度
    private String[] quarter(Integer year, Integer quarter) throws SerException {
        String startDate = null;
        String endDate = null;
        switch (quarter) {
            case 1:
                startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 3, DateUtil.getDayByDate(year, 3)));
                break;
            case 2:
                startDate = DateUtil.dateToString(LocalDate.of(year, 4, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 6, DateUtil.getDayByDate(year, 6)));
                break;
            case 3:
                startDate = DateUtil.dateToString(LocalDate.of(year, 7, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 9, DateUtil.getDayByDate(year, 9)));
                break;
            case 4:
                startDate = DateUtil.dateToString(LocalDate.of(year, 10, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
                break;
            default:
                startDate = DateUtil.dateToString(LocalDate.now());
                endDate = DateUtil.dateToString(LocalDate.now());
                break;
        }

        return new String[]{startDate, endDate};
    }


}