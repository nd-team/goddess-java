package com.bjike.goddess.user.service;

import com.aliyuncs.exceptions.ClientException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.utils.PasswordHash;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.user.bo.ShareCodeBO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.entity.ShareCode;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.enums.UserType;
import com.bjike.goddess.user.session.auth_code.AuthCodeSession;
import com.bjike.goddess.user.to.AppUserRegisterTO;
import com.bjike.goddess.user.to.SmsCodeParameterTO;
import com.bjike.goddess.user.to.UserRegisterTO;
import com.bjike.goddess.user.utils.SeqUtil;
import com.bjike.goddess.user.utils.ShareCodeUtils;
import com.bjike.goddess.user.utils.SmsCodeUtil;
import com.bjike.goddess.user.vo.SmsReceiveCodeVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户注册业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class UserRegisterSerImpl implements UserRegisterSer{

    @Autowired
    private UserSer userSer;
    @Autowired
    private ShareCodeSer shareCodeSer;


    @Override
    public void verifyAndSendCode(String phone) throws SerException {

        if (null == userSer.findByPhone(phone)) {
            //generateCode()
            String code = "123456";
            phone = "13457910241";
            AuthCodeSession.put(phone, code);

        } else {
            throw new SerException("该手机号码已注册！");

        }
    }

    @Transactional
    @Override
    public void verifyCodeAndReg(UserRegisterTO registerTO) throws SerException {


        if (registerTO.getPassword().equals(registerTO.getRePassword())) {
            if (!Validator.isPassword(registerTO.getPassword())) {
                throw new SerException("密码过于简单！");
            }
        } else {
            throw new SerException("输入密码不一致！");
        }
        saveUserByDTO(registerTO);
    }

    /**
     * 通过用户注册数据传输实体保存用户
     *
     * @param registerTO
     * @throws SerException
     */
    private void saveUserByDTO(UserRegisterTO registerTO) throws SerException {
        try {
            UserBO bo = userSer.findByUsername(registerTO.getUsername());
            if (null == bo) {
                String employeeNumber = userSer.maxUserEmpNumber();
                String sysNO = userSer.findByMaxField("systemNO", User.class);
                User user = new User();
                user.setUsername(registerTO.getUsername());
                user.setPassword(PasswordHash.createHash(registerTO.getPassword()));
                user.setUserType(UserType.ADMIN);
                user.setCreateTime(LocalDateTime.now());
                user.setStatus(Status.THAW);
                user.setEmployeeNumber(employeeNumber);
                user.setSystemNO(SeqUtil.generateSys(sysNO));
                userSer.save(user);
            } else {
                throw new SerException(registerTO.getUsername() + "已被注册!");
            }

        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }


    @Override
    public String sendSmsVerifyCode(SmsCodeParameterTO smsCodeParameterTO) throws SerException {

        if (StringUtils.isBlank(smsCodeParameterTO.getPhoneNumber())) {
            throw new SerException("手机号不能为空");
        }
        if (null != userSer.findByPhone(smsCodeParameterTO.getPhoneNumber())) {
            throw new SerException("该手机号已经注册");
        }
        String code = "";
        SmsReceiveCodeVO smsReceiveCodeVO = new SmsReceiveCodeVO();
        try {
            //调用阿里的短信
            smsReceiveCodeVO = SmsCodeUtil.mainEnter(smsCodeParameterTO);
            if (null != smsReceiveCodeVO) {
                code = smsReceiveCodeVO.getCode();
                if (StringUtils.isNotBlank(code) && "ok".equals(code.toLowerCase())) {
                    //说明发送成功
                     Pattern expression = Pattern.compile("[0-9]{" + Integer.parseInt(smsCodeParameterTO.getRandomNum()) + "}");//创建匹配模式
                    Matcher matcher2 = expression.matcher(smsReceiveCodeVO.getContent());//通过匹配模式得到匹配器
                    if (matcher2.find()) {
                        String smsCode = matcher2.group();
                        //将验证码存session，这里使用5分钟失效
                        AuthCodeSession.put(smsCodeParameterTO.getPhoneNumber(), smsCode);
                    }
                }
            }
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return code;
    }

    @Override
    public String sendSmsVerifyCodes(SmsCodeParameterTO to) throws SerException {
        if (StringUtils.isBlank(to.getPhoneNumber())) {
            throw new SerException("手机号不能为空");
        }
        String code = "";
        SmsReceiveCodeVO smsReceiveCodeVO = new SmsReceiveCodeVO();
        try {
            //调用阿里的短信
            smsReceiveCodeVO = SmsCodeUtil.mainEnter(to);
            if( null != smsReceiveCodeVO ){
                code = smsReceiveCodeVO.getCode();
                if (StringUtils.isNotBlank(code) && "ok".equals(code.toLowerCase())) {
                    //说明发送成功
                    Pattern expression = Pattern.compile("[0-9]{" + Integer.parseInt(to.getRandomNum()) + "}");//创建匹配模式
                    Matcher matcher2 = expression.matcher( smsReceiveCodeVO.getContent() );//通过匹配模式得到匹配器
                    if (matcher2.find()) {
                        String smsCode = matcher2.group();
                        //将验证码存session，这里使用5分钟失效
                        AuthCodeSession.put(to.getPhoneNumber(), smsCode);
                    }
                }
            }
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return code;
    }

    @Override
    public Boolean verifyCode(String phone, String code) throws SerException {
        Boolean verifyFlag = false;
        String sessionCode = AuthCodeSession.get(phone);
        if (StringUtils.isBlank(code) || StringUtils.isBlank(sessionCode)) {
            throw new SerException("验证码不正确");
        } else if (code.equals(sessionCode)) {
            verifyFlag = true;
        } else if (!code.equals(sessionCode)) {
            throw new SerException("验证码不正确");
        }
        return verifyFlag;
    }

    @Override
    public void checkPassword(String password, String repassword) throws SerException {
        if (password.equals(repassword)) {
            if (!Validator.isPassword(password)) {
                throw new SerException("密码过于简单！");
            }
        } else {
            throw new SerException("输入密码不一致！");
        }
    }

    @Override
    public void checkIntegral(String code) throws SerException {
        ShareCodeBO shareCodeBO = shareCodeSer.getByCode(code);
        List<ShareCode> shareCodes = new ArrayList<>();
        String aa = String.valueOf(10 / 100);
        if(shareCodeBO != null){
            for(int i = 0;i < 50;i++){
                ShareCodeBO shareCodeBO1 = shareCodeSer.getByCode(shareCodeBO.getShareCode());
                ShareCode shareCode = new ShareCode();

            }
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void registerUser(AppUserRegisterTO appUserRegisterTO) throws SerException {
        try {
            UserBO bo = userSer.findByUsername(appUserRegisterTO.getUsername());
            if (null == bo) {
                String employeeNumber = userSer.nextEmpNumber("AAAAA000001");
                String sysNO = userSer.findByMaxField("systemNO", User.class);
                User user = new User();
                if (!appUserRegisterTO.getRegisterType().equals("个人")) {
                    if (StringUtils.isNotBlank(appUserRegisterTO.getEnterpriseName())) {
                        user.setEnterpriseName(appUserRegisterTO.getEnterpriseName());
                    } else {
                        throw new SerException("请填写企业/组织名称");
                    }
                    if(StringUtils.isBlank(appUserRegisterTO.getEmployeeNumber())){
                        throw new SerException("员工编号不能为空");
                    }
                    user.setEmployeeNumber(appUserRegisterTO.getEmployeeNumber());
                }else{
                    user.setEmployeeNumber(employeeNumber);
                }
                user.setUsername(appUserRegisterTO.getUsername());
                user.setPhone(appUserRegisterTO.getPhone());
                user.setPassword(PasswordHash.createHash(appUserRegisterTO.getPassword()));
                user.setUserType(UserType.ADMIN);
                user.setCreateTime(LocalDateTime.now());
                user.setStatus(Status.THAW);
                user.setSystemNO(SeqUtil.generateSys(sysNO));
                userSer.save(user);
                if(appUserRegisterTO.getAuthCode() != null && !appUserRegisterTO.getAuthCode().equals("")) {
                    String aa = String.valueOf(10);
                    String bb = String.valueOf(2);
                    ShareCode shareCode = new ShareCode();
                    shareCode.setUserId(user.getId());
                    shareCode.setInviterShareCode(appUserRegisterTO.getAuthCode());
                    shareCode.setShareCode(ShareCodeUtils.generateShortUuid());
                    ShareCodeBO shareCodeBO = shareCodeSer.getByCode(appUserRegisterTO.getAuthCode());
                    shareCode.setInviterId(shareCodeBO.getId());
                    shareCode.setIntegral(aa);
                    shareCode.setInviterIntegral(bb);
                    shareCodeSer.save(shareCode);
                }
            } else {
                throw new SerException(appUserRegisterTO.getUsername() + "已被注册!");
            }
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    @Override
    public String autogenerationNum(String startNumber) throws SerException {
        if (!startNumber.matches("[a-zA-Z]{5}")) {
            throw new SerException("请输入五位数的字母获取对应编号");
        }
        String sql = "SELECT substring(employeeNumber,1,5) as employeeNumber FROM user";
        List<Object> objectList = userSer.findBySql(sql);
        if (objectList != null && objectList.size() > 0) {
            for (Object obj : objectList) {
                if (String.valueOf(obj).equalsIgnoreCase(startNumber)) {
                    throw new SerException("该编号已存在");
                }
            }
        }
        return SeqUtil.appAutogeneration(startNumber);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void inviteReg(String inviteUrl, AppUserRegisterTO appUserRegisterTO) throws SerException {
        String userId = inviteUrl.substring(inviteUrl.lastIndexOf("/") + 1, inviteUrl.length());
        User user = userSer.findById(userId);
        //填用户表
        if (user != null) {
            String sysNO = user.getSystemNO();
            User user1 = new User();
            user1.setEmployeeNumber(userSer.nextEmpNumber(user.getEmployeeNumber()));
            user1.setUsername(appUserRegisterTO.getUsername());
            user1.setPhone(appUserRegisterTO.getPhone());
            user1.setUserType(UserType.EMPLOYEE);
            user1.setCreateTime(LocalDateTime.now());
            user1.setSystemNO(sysNO);
            user1.setStatus(Status.THAW);
            try {
                user1.setPassword(PasswordHash.createHash(appUserRegisterTO.getPassword()));
            } catch (Exception e) {
                throw new SerException(e.getMessage());
            }
            userSer.save(user1);
        }
    }

}
