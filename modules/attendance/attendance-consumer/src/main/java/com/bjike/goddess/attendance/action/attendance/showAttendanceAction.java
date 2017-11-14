package com.bjike.goddess.attendance.action.attendance;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.attendance.api.PunchSonAPI;
import com.bjike.goddess.attendance.api.VacateAPI;
import com.bjike.goddess.attendance.api.overtime.OverWorkAPI;
import com.bjike.goddess.attendance.dto.overtime.OverTimesDTO;
import com.bjike.goddess.attendance.enums.OverTimesType;
import com.bjike.goddess.attendance.vo.OverWorkTimesVO;
import com.bjike.goddess.attendance.vo.showpic.*;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.attendance.to.CollectTimeTypeTO;
import com.bjike.goddess.taskallotment.vo.CollectDataVO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 首页考勤图形
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-07 10:28 ]
 * @Description: [ 首页考勤图形 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("showattendance")
public class showAttendanceAction {

    @Autowired
    private OverWorkAPI overWorkAPI;
    @Autowired
    private VacateAPI vacateAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PunchSonAPI punchSonAPI;


    /**
     * 考勤请假出勤
     *
     * @param collectTimeTypeTO collectTimeTypeTO
     * @return class CollectDataVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/picData/collect")
    public Result personCollect(CollectTimeTypeTO collectTimeTypeTO, HttpServletRequest request) throws ActException {
        try {
            String userToken = RpcContext.getContext().getAttachment("userToken");
            LocalDate date = LocalDate.now();
            int week = DateUtil.todayWeek(date);
            int quart = DateUtil.todayQuart(date);
            OverTimesType overTimesType = collectTimeTypeTO.getOverTimesType();
            UserBO userBO = userAPI.currentUser();
            RpcContext.getContext().setAttachment("userToken", userToken);

            //加班数据
            OverTimesDTO overTimesDTO = new OverTimesDTO();
            overTimesDTO.setYear(date.getYear());
            overTimesDTO.setMonth(date.getMonthValue());
            overTimesDTO.setWeek(week);
            overTimesDTO.setQuart(quart);
            overTimesDTO.setOverWorker(userBO.getUsername());
            overTimesDTO.setOverTimesType(overTimesType);
            OverWorkTimesVO overWorkTimesVO = overWorkAPI.userOverTimeCollect(overTimesDTO);
            OverWorkTimesVO vacateTimesVO = vacateAPI.userOverTimeCollect(overTimesDTO);
            OverWorkTimesVO punchTimesVO = punchSonAPI.userOverTimeCollect(overTimesDTO);
            List<Double> overList = new ArrayList<>();
            List<Double> vacateList = new ArrayList<>();
            List<Double> punchList = new ArrayList<>();
            List<Double> attendAbsentList = new ArrayList<>();
            if (overTimesType.equals(OverTimesType.WEEK)) {
                overList.addAll(Arrays.asList(Double.parseDouble(overWorkTimesVO.getFirst()), Double.parseDouble(overWorkTimesVO.getSecond()),
                        Double.parseDouble(overWorkTimesVO.getThird()), Double.parseDouble(overWorkTimesVO.getFour()),
                        Double.parseDouble(overWorkTimesVO.getFive()), Double.parseDouble(overWorkTimesVO.getSix()),
                        Double.parseDouble(overWorkTimesVO.getSeven())));
                vacateList.addAll(Arrays.asList(Double.parseDouble(vacateTimesVO.getFirst()), Double.parseDouble(vacateTimesVO.getSecond()),
                        Double.parseDouble(vacateTimesVO.getThird()), Double.parseDouble(vacateTimesVO.getFour()),
                        Double.parseDouble(vacateTimesVO.getFive()), Double.parseDouble(vacateTimesVO.getSix()),
                        Double.parseDouble(vacateTimesVO.getSeven())));
                punchList.addAll(Arrays.asList(Double.parseDouble(punchTimesVO.getFirst()), Double.parseDouble(punchTimesVO.getSecond()),
                        Double.parseDouble(punchTimesVO.getThird()), Double.parseDouble(punchTimesVO.getFour()),
                        Double.parseDouble(punchTimesVO.getFive()), Double.parseDouble(punchTimesVO.getSix()),
                        Double.parseDouble(punchTimesVO.getSeven())));
                //TODO 出勤有误次数未做
                attendAbsentList.addAll(Arrays.asList(0d,0d,0d,0d,0d,0d,0d));
            } else if (overTimesType.equals(OverTimesType.QUART)) {
                overList.addAll(Arrays.asList(Double.parseDouble(overWorkTimesVO.getFirstMonth()), Double.parseDouble(overWorkTimesVO.getSecndMonth()),
                        Double.parseDouble(overWorkTimesVO.getThirdMonth())));
                vacateList.addAll(Arrays.asList(Double.parseDouble(vacateTimesVO.getFirstMonth()), Double.parseDouble(vacateTimesVO.getSecndMonth()),
                        Double.parseDouble(vacateTimesVO.getThirdMonth())));
                punchList.addAll(Arrays.asList(Double.parseDouble(punchTimesVO.getFirstMonth()), Double.parseDouble(punchTimesVO.getSecndMonth()),
                        Double.parseDouble(punchTimesVO.getThirdMonth())));
                //TODO 出勤有误次数未做
                attendAbsentList.addAll(Arrays.asList(0d,0d,0d ));
            }
            ShowShapeBarVO showShapeBarVO = new ShowShapeBarVO();
            showShapeBarVO.setToolTipVO(new ShowShapeBarToolTipVO("axis"));
            showShapeBarVO.setRlegendVO(new ShowShapeLegendVO(Arrays.asList("加班次数", "请假次数", "未打卡次数", "出勤有误次数")));
            if (overTimesType.equals(OverTimesType.WEEK)) {
                showShapeBarVO.setXaxisVO(new ShowShapeXaxisVO("category", "", Arrays.asList("周一", "周二", "周三", "周四", "周五", "周六", "周日")));
            } else if (overTimesType.equals(OverTimesType.QUART)) {
                showShapeBarVO.setXaxisVO(new ShowShapeXaxisVO("category", "",
                        Arrays.asList(overWorkTimesVO.getFirstMonth().split("-")[0],
                                overWorkTimesVO.getSecndMonth().split("-")[0], overWorkTimesVO.getThirdMonth().split("-")[0])));
            }

            List<ShowShapeBarSeriesVO> list = new ArrayList<>();
            ShowShapeBarSeriesVO seriesVO = new ShowShapeBarSeriesVO();
            seriesVO.setName("加班次数");
            seriesVO.setType("bar");
            seriesVO.setSeriesDataVOList( overList );
            list.add( seriesVO );
            //请假次数
            seriesVO = new ShowShapeBarSeriesVO();
            seriesVO.setName("请假次数");
            seriesVO.setType("bar");
            seriesVO.setSeriesDataVOList( vacateList );
            list.add( seriesVO );
            //未打卡次数
            seriesVO = new ShowShapeBarSeriesVO();
            seriesVO.setName("未打卡次数");
            seriesVO.setType("bar");
            seriesVO.setSeriesDataVOList( punchList );
            list.add( seriesVO );
            //TODO 出勤有误次数未做
            seriesVO = new ShowShapeBarSeriesVO();
            seriesVO.setName("出勤有误次数");
            seriesVO.setType("bar");
            seriesVO.setSeriesDataVOList( attendAbsentList );
            list.add( seriesVO );

            showShapeBarVO.setSeriesVOs( list );
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}