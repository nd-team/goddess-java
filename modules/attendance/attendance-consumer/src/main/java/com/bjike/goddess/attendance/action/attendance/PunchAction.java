package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.PunchSonAPI;
import com.bjike.goddess.attendance.bo.PunchBO;
import com.bjike.goddess.attendance.bo.PunchSonBO;
import com.bjike.goddess.attendance.dto.PunchDTO;
import com.bjike.goddess.attendance.enums.PunchSource;
import com.bjike.goddess.attendance.to.PunchSonTO;
import com.bjike.goddess.attendance.vo.PunchSonVO;
import com.bjike.goddess.attendance.vo.PunchVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.token.IpUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.AddressUtils;
import util.CheckMobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 打卡
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:21 ]
 * @Description: [ 打卡 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("punch")
public class PunchAction {
    @Autowired
    private PunchSonAPI punchSonAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    private boolean check(HttpServletRequest request) throws IOException {
        boolean isFromMobile = false;

        HttpSession session = request.getSession();
        //检查是否已经记录访问方式（移动端或pc端）
        if (null == session.getAttribute("ua")) {
            try {
                //获取ua，用来判断是否为移动端访问
                String userAgent = request.getHeader("USER-AGENT").toLowerCase();
                if (null == userAgent) {
                    userAgent = "";
                }
                isFromMobile = CheckMobile.check(userAgent);
                //判断是否为移动端访问
                if (isFromMobile) {
                    session.setAttribute("ua", "mobile");
                } else {
                    session.setAttribute("ua", "pc");
                }
            } catch (Exception e) {
            }
        } else {
            isFromMobile = session.getAttribute("ua").equals("mobile");
        }

        return isFromMobile;
    }

    /**
     * 列表
     *
     * @param dto dto
     * @return class PunchVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(PunchDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<PunchBO> list = punchSonAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, PunchVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看打卡范围
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/range")
    public Result range(@Validated(PunchDTO.RANGE.class) PunchDTO dto, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(punchSonAPI.string(dto.getLongitude(), dto.getLatitude(), dto.getArea()));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 移动端打卡
     *
     * @param to to
     * @return class PunchSonVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/phone")
    public Result phone(@Validated(PunchSonTO.PHONE.class) PunchSonTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            to.setPunchSource(PunchSource.MOBILE);
            PunchSonBO bo = punchSonAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, PunchSonVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * pc端打卡
     *
     * @param to to
     * @return class PunchSonVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/pc")
    public Result pc(@Validated(PunchSonTO.PC.class) PunchSonTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            String ip=IpUtil.getIp(request);
            AddressUtils addressUtils = new AddressUtils();
            String area = addressUtils.getAddresses("ip=" + ip, "utf-8");
            to.setArea(area);
            to.setPunchSource(PunchSource.PC);
            PunchSonBO bo = punchSonAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, PunchSonVO.class, request));
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 查找总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(PunchDTO dto) throws ActException {
        try {
            return ActResult.initialize(punchSonAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有用户
     *
     * @return class UserVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/users")
    public Result users(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> userBOS = positionDetailUserAPI.findUserListInOrgan();
            return ActResult.initialize(BeanTransform.copyProperties(userBOS, UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}