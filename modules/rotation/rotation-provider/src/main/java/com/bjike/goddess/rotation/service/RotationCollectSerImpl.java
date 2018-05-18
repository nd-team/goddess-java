package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.rotation.bo.*;
import com.bjike.goddess.rotation.dto.RotationCollectEchartDTO;
import com.bjike.goddess.rotation.dto.RotationDetailsCollectDTO;
import com.bjike.goddess.rotation.entity.RotationRecord;
import com.bjike.goddess.rotation.enums.CollectDetailsType;
import com.bjike.goddess.rotation.enums.CollectTimeType;
import com.bjike.goddess.rotation.enums.GuideAddrStatus;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-09 15:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Service
public class RotationCollectSerImpl extends ServiceImpl<RotationRecord, RotationDetailsCollectDTO> implements RotationCollectSer{

    final static String MANAGER = "'管理层', '管理层储备期', '管理层实习期'";
    final static String DECISION = "'决策层', '决策层储备期', '决策层实习期'";
    @Autowired
    DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    UserAPI userAPI;
    @Autowired
    CusPermissionSer cusPermissionSer;


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
            flag = cusPermissionSer.getCusPermission("2");
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
    public List<RotationDetailsCollectBO> listDetailsCollect(RotationDetailsCollectDTO dto) throws SerException {
        if (CollectDetailsType.MANAGER.equals(dto.getCollectDetailsType())) { //管理层
            return listManagerDetailsCollect(null);
        }
        return listDecisionDetailsCollect(null);
    }

    /**
     * 管理层汇总
     * @return
     */
    private List<RotationDetailsCollectBO> listManagerDetailsCollect(String area) throws SerException{

        List<RotationDetailsCollectBO> bos = new ArrayList<>();

        List<AreaBO> areas = new ArrayList<>();
        if (StringUtils.isNotBlank(area)) {
            areas.add(new AreaBO(area));
        } else {
            areas = departmentDetailAPI.findArea();
        }
        for (AreaBO areaBO : areas) {
            List<String> departments = departmentDetailAPI.findDepartByArea(areaBO.getArea());
            for (String department : departments) {

                //申请人数
                StringBuffer sql = new StringBuffer();
                sql.append("select sum(");
                sql.append("(select count(1) as companyNeedManage from rotation_cover rc inner join rotation_subsidy_standard ss ");
                sql.append("on rc.rotationLevel_id = ss.id where audit = 0 and ss.arrangement in ("+ MANAGER +") ");
                sql.append("and area='"+ areaBO.getArea() +"' and department='"+ department +"') + ");
                sql.append("(select count(1) as companyNeedManage from rotation_recommend rc inner join rotation_subsidy_standard ss ");
                sql.append("on rc.rotationLevel_id = ss.id where audit = 0 and ss.arrangement in ("+ MANAGER +") ");
                sql.append("and area='"+ areaBO.getArea() +"' and department='"+ department +"'");
                sql.append("))as personalNeedManage");
                String[] fileds = {"personalNeedManage"};
                List<RotationDetailsCollectBO> list = super.findBySql(sql.toString(), RotationDetailsCollectBO.class, fileds);

                //管理层人数、管理层应轮岗人数、管理层储备期人数、管理层储备期应轮岗人数、管理层实习期人数、管理层实习期应轮岗人数
                StringBuffer childSql = new StringBuffer();
                childSql.append("select ifnull(sum(case when arrangement = '管理层' then 1 else 0 end), 0) as manager");
                childSql.append(",ifnull(sum(case when arrangement = '管理层' and rotation = 1 then 1 else 0 end), 0) as managerRotation");
                childSql.append(",ifnull(sum(case when arrangement = '管理层储备期' then 1 else 0 end), 0) as managerReserve");
                childSql.append(",ifnull(sum(case when arrangement = '管理层储备期' and rotation = 1 then 1 else 0 end), 0) as managerReserveRotation");
                childSql.append(",ifnull(sum(case when arrangement = '管理层实习期' then 1 else 0 end), 0) as managerIntern");
                childSql.append(",ifnull(sum(case when arrangement = '管理层实习期' and rotation = 1 then 1 else 0 end), 0) as managerInternRotation ");
                childSql.append("from rotation_currentposition where area = '"+ areaBO.getArea() +"' and department = '"+ department +"'");
                String[] fileds2 = {"manager", "managerRotation", "managerReserve", "managerReserveRotation", "managerIntern", "managerInternRotation"};
                List<RotationDetailsCollectBO> childList = super.findBySql(childSql.toString(), RotationDetailsCollectBO.class, fileds2);
                childList.get(0).setPersonalNeedManage(list.get(0).getPersonalNeedManage());

                //公司发展需求管理人数 todo
                childList.get(0).setCompanyNeedManage(0);

                //地区
                childList.get(0).setArea(areaBO.getArea());

                //部门
                childList.get(0).setDepartment(department);

                bos.add(childList.get(0));
            }
        }
        return bos;
    }

    /**
     * 决策层汇总
     * @return
     */
    private List<RotationDetailsCollectBO> listDecisionDetailsCollect(String area) throws SerException{
        List<RotationDetailsCollectBO> bos = new ArrayList<>();

        List<AreaBO> areas = new ArrayList<>();
        if (StringUtils.isNotBlank(area)) {
            areas.add(new AreaBO(area));
        } else {
            areas = departmentDetailAPI.findArea();
        }
        for (AreaBO areaBO : areas) {
            List<String> departments = departmentDetailAPI.findDepartByArea(areaBO.getArea());
            for (String department : departments) {
                //申请人数
                StringBuffer sql = new StringBuffer();
                sql.append("select sum(");
                sql.append("(select count(1) as companyNeedManage from rotation_cover rc inner join rotation_subsidy_standard ss ");
                sql.append("on rc.rotationLevel_id = ss.id where audit = 0 and ss.arrangement in ("+ DECISION +") ");
                sql.append("and area='"+ areaBO.getArea() +"' and department='"+ department +"') + ");
                sql.append("(select count(1) as companyNeedManage from rotation_recommend rc inner join rotation_subsidy_standard ss ");
                sql.append("on rc.rotationLevel_id = ss.id where audit = 0 and ss.arrangement in ("+ DECISION +") ");
                sql.append("and area='"+ areaBO.getArea() +"' and department='"+ department +"'");
                sql.append("))as personalNeedDecision");
                String[] fileds = {"personalNeedDecision"};
                List<RotationDetailsCollectBO> list = super.findBySql(sql.toString(), RotationDetailsCollectBO.class, fileds);

                //决策层人数、决策层应轮岗人数、决策层储备期人数、决策层储备期应轮岗人数、决策层实习期人数、决策层实习期应轮岗人数
                StringBuffer childSql = new StringBuffer();
                childSql.append("select ifnull(sum(case when arrangement = '决策层' then 1 else 0 end), 0) as decision");
                childSql.append(",ifnull(sum(case when arrangement = '决策层' and rotation = 1 then 1 else 0 end), 0) as decisionRotation");
                childSql.append(",ifnull(sum(case when arrangement = '决策层储备期' then 1 else 0 end), 0) as decisionReserve");
                childSql.append(",ifnull(sum(case when arrangement = '决策层储备期' and rotation = 1 then 1 else 0 end), 0) as decisionReserveRotation");
                childSql.append(",ifnull(sum(case when arrangement = '决策层实习期' then 1 else 0 end), 0) as decisionIntern");
                childSql.append(",ifnull(sum(case when arrangement = '决策层实习期' and rotation = 1 then 1 else 0 end), 0) as decisionInternRotation ");
                childSql.append("from rotation_currentposition where area = '"+ areaBO.getArea() +"' and department = '"+ department +"'");
                String[] fileds2 = {"decision", "decisionRotation", "decisionReserve", "decisionReserveRotation", "decisionIntern", "decisionInternRotation"};
                List<RotationDetailsCollectBO> childList = super.findBySql(childSql.toString(), RotationDetailsCollectBO.class, fileds2);
                childList.get(0).setPersonalNeedDecision(list.get(0).getPersonalNeedDecision());

                //公司发展需求决策人数 todo
                childList.get(0).setCompanyNeedDecision(0);

                //地区
                childList.get(0).setArea(areaBO.getArea());

                //部门
                childList.get(0).setDepartment(department);

                bos.add(childList.get(0));
            }
        }
        return bos;
    }



    @Override
    public List<RotationCollectBO> listCollect(CollectTimeType type, String... param) throws SerException {
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
                start = "";
                end = param[0];
                break;

        }
        return convertCollect(start, end, null);
    }

    List<RotationCollectBO> convertCollect(String start, String end, String area) throws SerException{

        List<RotationCollectBO> bos = new ArrayList<>();

        List<AreaBO> areas = new ArrayList<>();
        if (StringUtils.isNotBlank(area)) {
            areas.add(new AreaBO(area));
        } else {
            areas = departmentDetailAPI.findArea();
        }
        for (AreaBO areaBO : areas) {
            List<String> departments = departmentDetailAPI.findDepartByArea(areaBO.getArea());
            for (String department : departments) {
                RotationCollectBO bo = new RotationCollectBO();
                //个人发展需求人数
                StringBuffer sql = new StringBuffer();
                sql.append("select sum(");
                sql.append("(select count(1) as personalNeed from rotation_cover rc ");
                sql.append("where createTime >= '"+ start +"' and createTime <= '"+ end +"' and ");
                sql.append("area='"+ areaBO.getArea() +"' and department='"+ department +"') + ");
                sql.append("(select count(1) as personalNeed from rotation_recommend rc ");
                sql.append("where createTime >= '"+ start +"' and createTime <= '"+ end +"' and ");
                sql.append("area='"+ areaBO.getArea() +"' and department='"+ department +"'");
                sql.append("))as personalNeed");
                String[] fileds = {"personalNeed"};
                List<RotationCollectBO> list1 = super.findBySql(sql.toString(), RotationCollectBO.class, fileds);
                bo.setPersonalNeed(list1.get(0).getPersonalNeed());

                //应轮岗人数 todo 时间限制字段不确定
                StringBuffer sql2 = new StringBuffer();
                sql2.append("select ifnull(count(1), 0) as shouldRotation ");
                sql2.append("from rotation_currentposition where area = '"+ areaBO.getArea() +"' and department = '"+ department +"'");
                String[] fileds2 = {"shouldRotation"};
                List<RotationCollectBO> list2 = super.findBySql(sql2.toString(), RotationCollectBO.class, fileds2);
                bo.setShouldRotation(list2.get(0).getShouldRotation());

                //正在受理
                StringBuffer sql3 = new StringBuffer();
                sql3.append("select sum(");
                sql3.append("(select count(1) as dealing from rotation_cover rc  ");
                sql3.append("where audit = 0 and createTime >= '"+ start +"' and createTime <= '"+ end +"' and ");
                sql3.append("area='"+ areaBO.getArea() +"' and department='"+ department +"') + ");
                sql3.append("(select count(1) as dealing from rotation_recommend rc ");
                sql3.append("where audit = 0 and createTime >= '"+ start +"' and createTime <= '"+ end +"' and ");
                sql3.append("area='"+ areaBO.getArea() +"' and department='"+ department +"'");
                sql3.append("))as dealing");
                String[] fileds3 = {"dealing"};
                List<RotationCollectBO> list3 = super.findBySql(sql3.toString(), RotationCollectBO.class, fileds3);
                bo.setDealing(list3.get(0).getDealing());

                //受理完成
                StringBuffer sql4 = new StringBuffer();
                sql4.append("select sum(");
                sql4.append("(select count(1) as finished from rotation_cover rc  ");
                sql4.append("where audit != 0 and createTime >= '"+ start +"' and createTime <= '"+ end +"' and ");
                sql4.append("area='"+ areaBO.getArea() +"' and department='"+ department +"') + ");
                sql4.append("(select count(1) as finished from rotation_recommend rc ");
                sql4.append("where audit != 0 and createTime >= '"+ start +"' and createTime <= '"+ end +"' and ");
                sql4.append("area='"+ areaBO.getArea() +"' and department='"+ department +"'");
                sql4.append("))as finished");
                String[] fileds4 = {"finished"};
                List<RotationCollectBO> list4 = super.findBySql(sql4.toString(), RotationCollectBO.class, fileds4);
                bo.setFinished(list4.get(0).getFinished());

                //通过（轮岗人数）
                StringBuffer sql5 = new StringBuffer();
                sql5.append("select sum(");
                sql5.append("(select count(1) as passRotation from rotation_cover rc  ");
                sql5.append("where audit = 1 and createTime >= '"+ start +"' and createTime <= '"+ end +"' and ");
                sql5.append("area='"+ areaBO.getArea() +"' and department='"+ department +"') + ");
                sql5.append("(select count(1) as passRotation from rotation_recommend rc ");
                sql5.append("where audit = 1 and createTime >= '"+ start +"' and createTime <= '"+ end +"' and ");
                sql5.append("area='"+ areaBO.getArea() +"' and department='"+ department +"'");
                sql5.append("))as passRotation");
                String[] fileds5 = {"passRotation"};
                List<RotationCollectBO> list5 = super.findBySql(sql5.toString(), RotationCollectBO.class, fileds5);
                bo.setPassRotation(list5.get(0).getPassRotation());

                //未通过
                StringBuffer sql6 = new StringBuffer();
                sql6.append("select sum(");
                sql6.append("(select count(1) as notPassRotation from rotation_cover rc  ");
                sql6.append("where audit = 2 and createTime >= '"+ start +"' and createTime <= '"+ end +"' and ");
                sql6.append("area='"+ areaBO.getArea() +"' and department='"+ department +"') + ");
                sql6.append("(select count(1) as notPassRotation from rotation_recommend rc ");
                sql6.append("where audit = 2 and createTime >= '"+ start +"' and createTime <= '"+ end +"' and ");
                sql6.append("area='"+ areaBO.getArea() +"' and department='"+ department +"'");
                sql6.append("))as notPassRotation");
                String[] fileds6 = {"notPassRotation"};
                List<RotationCollectBO> list6 = super.findBySql(sql6.toString(), RotationCollectBO.class, fileds6);
                bo.setNotPassRotation(list6.get(0).getNotPassRotation());

                //已通报
                StringBuffer sql7 = new StringBuffer();
                sql7.append("select sum(");
                sql7.append("(select count(1) as hadNotify from rotation_cover rc  ");
                sql7.append("where hadNotify = 1 and createTime >= '"+ start +"' and createTime <= '"+ end +"' and ");
                sql7.append("area='"+ areaBO.getArea() +"' and department='"+ department +"') + ");
                sql7.append("(select count(1) as hadNotify from rotation_recommend rc ");
                sql7.append("where hadNotify = 1 and createTime >= '"+ start +"' and createTime <= '"+ end +"' and ");
                sql7.append("area='"+ areaBO.getArea() +"' and department='"+ department +"'");
                sql7.append("))as hadNotify");
                String[] fileds7 = {"hadNotify"};
                List<RotationCollectBO> list7 = super.findBySql(sql7.toString(), RotationCollectBO.class, fileds7);
                bo.setHadNotify(list7.get(0).getHadNotify());

                //公司发展需求决策人数 todo
                bo.setCompanyNeed(0);

                //员工发展成本 todo
                bo.setDevelopCost(0.0);

                //地区
                bo.setArea(areaBO.getArea());

                //部门
                bo.setDepartment(department);

                bos.add(bo);
            }
        }
        return bos;
    }

    @Override
    public RotationCollectEchartBO collectDetailsEchart(RotationCollectEchartDTO dto) throws SerException {
        if (CollectDetailsType.MANAGER.equals(dto.getCollectDetailsType())) {
            List<RotationDetailsCollectBO> bos = listManagerDetailsCollect(dto.getArea());
            RotationCollectEchartBO echartBO = new RotationCollectEchartBO();
            return getRDMCollectEchart(bos, "轮岗情况", null);
        }
        List<RotationDetailsCollectBO> bos = listDecisionDetailsCollect(dto.getArea());
        RotationCollectEchartBO echartBO = new RotationCollectEchartBO();
        return getRDDCollectEchart(bos, "轮岗情况", null);
    }

    @Override
    public RotationCollectEchartBO collectEchart(RotationCollectEchartDTO dto) throws SerException {
        List<RotationCollectBO> list = this.convertCollect("", LocalDate.now().toString(), dto.getArea());


        String[] xText = new String[list.size()];
        SeriesBO[] series = new SeriesBO[list.size()];


        for (int i = 0; i < list.size(); i++) {

            xText[i] = list.get(i).getDepartment();

            String param1 = list.get(i).getCompanyNeed() == null ? "" : String.valueOf(list.get(i).getCompanyNeed());
            String param2 = list.get(i).getPersonalNeed() == null ? "" : String.valueOf(list.get(i).getPersonalNeed());
            String param3 = list.get(i).getShouldRotation() == null ? "" : String.valueOf(list.get(i).getShouldRotation());
            String param4 = list.get(i).getDealing() == null ? "" : String.valueOf(list.get(i).getDealing());
            String param5 = list.get(i).getFinished() == null ? "" : String.valueOf(list.get(i).getFinished());
            String param6 = list.get(i).getPassRotation() == null ? "" : String.valueOf(list.get(i).getPassRotation());
            String param7 = list.get(i).getNotPassRotation() == null ? "" : String.valueOf(list.get(i).getNotPassRotation());
            String param8 = list.get(i).getHadNotify() == null ? "" : String.valueOf(list.get(i).getHadNotify());

            String[] data = new String[]{param1, param2, param3, param4, param5, param6, param7, param8};


            SeriesBO seriesBO = new SeriesBO(list.get(i).getDepartment(), "bar", data, "0");
            series[i] = seriesBO;
        }
        //标题
        TitleBO titleBO = new TitleBO("岗位轮换管理汇总", "center");

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("axis");

        //横坐标描述
        LegendBO legendBO = new LegendBO(xText, "vertical", "left");
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        yAxisBO.setType("value");

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        xAxisBO.setData(new String[]{"公司发展需求人数", "个人发展需求人数", "应轮岗人数", "正在受理", "已处理完成", "通过(轮岗人数)", "未通过", "通报结果"});
        xAxisBO.setType("category");
        xAxisBO.setBoundaryGap(true);

        RotationCollectEchartBO bcCollectEchartBO = new RotationCollectEchartBO();
        bcCollectEchartBO.setTitle(titleBO);
        bcCollectEchartBO.setTooltip(tooltipBO);
        bcCollectEchartBO.setLegend(legendBO);
        bcCollectEchartBO.setxAxis(xAxisBO);
        bcCollectEchartBO.setyAxis(yAxisBO);
        bcCollectEchartBO.setSeries(series);
        return bcCollectEchartBO;
    }

    @Override
    public List<String> listArea() throws SerException {
        List<AreaBO> areas = departmentDetailAPI.findArea();
        List<String> list = new ArrayList<>();
        for (AreaBO bo : areas) {
            list.add(bo.getArea());
        }
        return list;
    }

    /**
     * 岗位轮换明细管理层汇总柱状图图表
     *
     * @param
     * @return class
     * @version v1
     */
    private RotationCollectEchartBO getRDMCollectEchart(List<RotationDetailsCollectBO> list, String titleText, String type) {
        String[] xText = new String[list.size()];
        SeriesBO[] series = new SeriesBO[list.size()];


        for (int i = 0; i < list.size(); i++) {

            xText[i] = list.get(i).getDepartment();

            String param1 = list.get(i).getCompanyNeedManage() == null ? "" : String.valueOf(list.get(i).getCompanyNeedManage());
            String param2 = list.get(i).getPersonalNeedManage() == null ? "" : String.valueOf(list.get(i).getPersonalNeedManage());
            String param3 = list.get(i).getManager() == null ? "" : String.valueOf(list.get(i).getManager());
            String param4 = list.get(i).getManagerRotation() == null ? "" : String.valueOf(list.get(i).getManagerRotation());
            String param5 = list.get(i).getManagerReserve() == null ? "" : String.valueOf(list.get(i).getManagerReserve());
            String param6 = list.get(i).getManagerReserveRotation() == null ? "" : String.valueOf(list.get(i).getManagerReserveRotation());
            String param7 = list.get(i).getManagerIntern() == null ? "" : String.valueOf(list.get(i).getManagerIntern());
            String param8 = list.get(i).getManagerInternRotation() == null ? "" : String.valueOf(list.get(i).getManagerInternRotation());

            String[] data = new String[]{param1, param2, param3, param4, param5, param6, param7, param8};


            SeriesBO seriesBO = new SeriesBO(list.get(i).getDepartment(), "bar", data, "0");
            series[i] = seriesBO;
        }
        //标题
        TitleBO titleBO = new TitleBO(titleText, "center");

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("axis");

        //横坐标描述
        LegendBO legendBO = new LegendBO(xText, "vertical", "left");
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        yAxisBO.setType("value");

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        xAxisBO.setData(new String[]{"公司发展需求管理人数", "个人发展需求管理人数", "管理层人数", "管理层应轮岗人数", "管理层储备期人数", "管理层储备期应轮岗人数", "管理层实习期人数", "管理层实习期应轮岗人数"});
        xAxisBO.setType("category");
        xAxisBO.setBoundaryGap(true);

        RotationCollectEchartBO bcCollectEchartBO = new RotationCollectEchartBO();
        bcCollectEchartBO.setTitle(titleBO);
        bcCollectEchartBO.setTooltip(tooltipBO);
        bcCollectEchartBO.setLegend(legendBO);
        bcCollectEchartBO.setxAxis(xAxisBO);
        bcCollectEchartBO.setyAxis(yAxisBO);
        bcCollectEchartBO.setSeries(series);
        return bcCollectEchartBO;
    }


    /**
     * 岗位轮换明细决策层汇总柱状图图表
     *
     * @param
     * @return class
     * @version v1
     */
    private RotationCollectEchartBO getRDDCollectEchart(List<RotationDetailsCollectBO> list, String titleText, String type) {
        String[] xText = new String[list.size()];
        SeriesBO[] series = new SeriesBO[list.size()];


        for (int i = 0; i < list.size(); i++) {

            xText[i] = list.get(i).getDepartment();

            String param1 = list.get(i).getCompanyNeedDecision() == null ? "" : String.valueOf(list.get(i).getCompanyNeedDecision());
            String param2 = list.get(i).getPersonalNeedDecision() == null ? "" : String.valueOf(list.get(i).getPersonalNeedDecision());
            String param3 = list.get(i).getDecision() == null ? "" : String.valueOf(list.get(i).getDecision());
            String param4 = list.get(i).getDecisionRotation() == null ? "" : String.valueOf(list.get(i).getDecisionRotation());
            String param5 = list.get(i).getDecisionReserve() == null ? "" : String.valueOf(list.get(i).getDecisionReserve());
            String param6 = list.get(i).getDecisionReserveRotation() == null ? "" : String.valueOf(list.get(i).getDecisionReserveRotation());
            String param7 = list.get(i).getDecisionIntern() == null ? "" : String.valueOf(list.get(i).getDecisionIntern());
            String param8 = list.get(i).getDecisionInternRotation() == null ? "" : String.valueOf(list.get(i).getDecisionInternRotation());

            String[] data = new String[]{param1, param2, param3, param4, param5, param6, param7, param8};
            SeriesBO seriesBO = new SeriesBO(list.get(i).getDepartment(), "bar", data, "0");
            series[i] = seriesBO;
        }
        //标题
        TitleBO titleBO = new TitleBO(titleText, "center");

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("axis");

        //横坐标描述
        LegendBO legendBO = new LegendBO(xText, "vertical", "left");
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        yAxisBO.setType("value");

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        xAxisBO.setData(new String[]{"公司发展需求决策人数", "个人发展需求决策人数", "决策层人数", "决策层应轮岗人数", "决策层储备期人数", "决策层储备期应轮岗人数", "决策层实习期人数", "决策层实习期应轮岗人数"});
        xAxisBO.setType("category");
        xAxisBO.setBoundaryGap(true);

        RotationCollectEchartBO bcCollectEchartBO = new RotationCollectEchartBO();
        bcCollectEchartBO.setTitle(titleBO);
        bcCollectEchartBO.setTooltip(tooltipBO);
        bcCollectEchartBO.setLegend(legendBO);
        bcCollectEchartBO.setxAxis(xAxisBO);
        bcCollectEchartBO.setyAxis(yAxisBO);
        bcCollectEchartBO.setSeries(series);
        return bcCollectEchartBO;
    }




    //季度转换
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
