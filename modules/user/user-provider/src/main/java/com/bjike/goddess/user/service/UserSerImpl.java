package com.bjike.goddess.user.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Condition;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.jpa.utils.PasswordHash;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import com.bjike.goddess.user.bo.rbac.PermissionBO;
import com.bjike.goddess.user.dao.UserRep;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.dto.UserDetailDTO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.entity.UserDetail;
import com.bjike.goddess.user.enums.UserType;
import com.bjike.goddess.user.session.constant.UserCommon;
import com.bjike.goddess.user.session.valid_right.LoginUser;
import com.bjike.goddess.user.session.valid_right.UserSession;
import com.bjike.goddess.user.to.UserTO;
import com.bjike.goddess.user.utils.SeqUtil;
import org.apache.commons.lang3.StringUtils;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class UserSerImpl extends ServiceImpl<User, UserDTO> implements UserSer {
    public static String PUBLIC_KEY;
    public static String PRIVATE_KEY;
    private static Logger LOGGER = LoggerFactory.getLogger(UserSerImpl.class);
    @Autowired
    private UserRep userRep;
    @Autowired
    private UserDetailSer userDetailSer;
    @Autowired
    private RedisClient redis;

    /**
     * 初始化公钥私钥
     */
    static {
        File file = new File("/root/files/key.properties");
        try {
            if (file.exists()) {
                Reader rd = new FileReader(file);
                BufferedReader reader = new BufferedReader(rd);
                String line = null;
                while (null != (line = reader.readLine())) {
                    if (line.startsWith("publicKey")) {
                        PUBLIC_KEY = line.split("=")[1].trim();
                    }
                    if (line.startsWith("privateKey")) {
                        PRIVATE_KEY = line.split("=")[1].trim();
                    }
                }
            } else {
                LOGGER.info("/root/files/key.properties 配置文件不存在,请先创建!");
            }
        } catch (Exception e) {
            LOGGER.info("公钥读取异常!");
        }
    }


    @Override
    public String publicKey() throws SerException {
        return PUBLIC_KEY;
    }

    @Override
    public String privateKey() throws SerException {
        return PRIVATE_KEY;
    }


    @Override
    public UserBO currentUser() throws SerException {
        //获取当前用户直接给无需登录
        String token = RpcTransmit.getUserToken();
        LoginUser loginUser = currentLoginUser(token);
        return BeanTransform.copyProperties(loginUser, UserBO.class);

    }

    @Override
    public UserBO currentUser(String userToken) throws SerException {
        //获取当前用户直接给无需登录
        LoginUser loginUser = currentLoginUser(userToken);
        return BeanTransform.copyProperties(loginUser, UserBO.class);
    }

    @Override
    public List<PermissionBO> currentPermissions() throws SerException {
        String token = RpcTransmit.getUserToken();
        if (null != token) {
            try {
                LoginUser loginUser = currentLoginUser(token);
                return loginUser.getPermissions();
            } catch (SerException e) {
                return new ArrayList<>(0);
            }
        }
        return new ArrayList<>(0);
    }

    @Override
    public String currentSysNO(String userToken) throws SerException {
        String sysNO = currentUser(userToken).getSystemNO();
        if (StringUtils.isNotBlank(sysNO)) {
            return sysNO;
        } else {
            throw new SerException("当前用户系统号为空!");
        }
    }

    @Override
    public String currentSysNO() throws SerException {
        String sysNO = currentUser().getSystemNO();
        if (StringUtils.isNotBlank(sysNO)) {
            return sysNO;
        } else {
            throw new SerException("当前用户系统号为空!");
        }
    }

    @Cacheable
    @Override
    public List<UserBO> findAllUser() throws SerException {
        List<User> users = super.findAll();
        List<UserBO> userBOS = BeanTransform.copyProperties(users, UserBO.class);
        return userBOS;
    }

    @CacheEvict(value = "listCache", allEntries = true)
    @Override
    @Transactional(rollbackFor = SerException.class)
    @Compensable(confirmMethod = "addConfirm", cancelMethod = "addCancel")
    public UserBO add(TransactionContext txContext, UserTO userTO) throws SerException {
        String sysNO = this.currentSysNO();
        User user = BeanTransform.copyProperties(userTO, User.class);
        user.setUserType(UserType.EMPLOYEE);
        user.setSystemNO(sysNO);
        try {
            user.setPassword(PasswordHash.createHash(userTO.getPassword()));
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
        user.setEmployeeNumber(userTO.getEmployeeNumber());
        return BeanTransform.copyProperties(super.save(user), UserBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    public String addConfirm(TransactionContext txContext, UserTO userTO) throws SerException {
        System.out.println("用户添加确认");
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    public String addCancel(TransactionContext txContext, UserTO userTO) throws SerException {
        UserDTO dto = new UserDTO();
        dto.getConditions().add(Restrict.eq("username", userTO.getUsername()));
        User user = super.findOne(dto);
        if (null != user) {
            super.remove(user);
        }
        return null;
    }

    @Override
    public UserBO findByUsername(String username) throws SerException {
        User user = userRep.findByUsername(username);
        return BeanTransform.copyProperties(user, UserBO.class);
    }

    @Cacheable
    @Override
    public UserBO findByNickname(String nickname) throws SerException {
        User user = userRep.findByNickname(nickname);
        return BeanTransform.copyProperties(user, UserBO.class);

    }

    @Cacheable
    @Override
    public UserBO findByPhone(String phone) throws SerException {
        User user = null;
        if (StringUtils.isNotBlank(phone)) {
            boolean isPhone = Validator.isPhone(phone);
            if (isPhone) {
                user = userRep.findByPhone(phone);
            } else {
                throw new SerException("手机格式不正确");
            }
        } else {
            throw new SerException("手机号不能为空");
        }

        return BeanTransform.copyProperties(user, UserBO.class);

    }

    @Override
    public UserBO findByAccountNumber(String accountNumber) throws SerException {
        UserDTO dto = new UserDTO();
        List<Condition> conditions = dto.getConditions();
        conditions.add(Restrict.eq("username", accountNumber));
        conditions.add(Restrict.or("phone", accountNumber));
        conditions.add(Restrict.or("email", accountNumber));
        conditions.add(Restrict.or("employeeNumber", accountNumber));
        User user = super.findOne(dto);
        UserBO userBO = BeanTransform.copyProperties(user, UserBO.class);
        return userBO;
    }

    @CacheEvict(value = "listCache", allEntries = true)
    @Transactional
    @Override
    public void update(UserTO userTO) throws SerException {
        String token = RpcTransmit.getUserToken();
        if (StringUtils.isNotBlank(token)) {
            User user = super.findById(userTO.getId());
            BeanTransform.copyProperties(userTO, user, true);
            user.setModifyTime(LocalDateTime.now());
            super.update(user);
            //更新session及缓存
            UserBO currentUser = currentUser(token);
            if (currentUser.getId().equals(user.getId())) {
                LoginUser loginUser = new LoginUser();
                BeanUtils.copyProperties(user, loginUser);
                redis.appendToMap(UserCommon.LOGIN_USER, token, JSON.toJSONString(loginUser));
                UserSession.put(token, loginUser);
            }

        } else {
            throw new SerException("notLogin");
        }
    }

    @CacheEvict(value = "listCache", allEntries = true)
    @Override
    public void updatePassword(UserTO userTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = this.currentUser();
        if (null == userBO) {
            throw new SerException("不存在该用户");
        }
        RpcTransmit.transmitUserToken(userToken);

        User user = super.findById(userBO.getId());
        if (null == user) {
            throw new SerException("该用户不存在");
        }
        try {
            user.setPassword(PasswordHash.createHash(userTO.getPassword()));
            super.update(user);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }

    @CacheEvict(value = "listCache", allEntries = true)
    @Override
    public void updatePhone(UserTO userTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = this.currentUser();
        if (null == userBO) {
            throw new SerException("不存在该用户");
        }
        RpcTransmit.transmitUserToken(userToken);

        User user = super.findById(userBO.getId());
        if (null == user) {
            throw new SerException("该用户不存在");
        }
        try {
            user.setPhone(userTO.getPhone());
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
        super.update(user);
    }

    @Override
    public List<UserBO> findByGroup(String... groups) throws SerException {
        UserDetailDTO detailDTO = new UserDetailDTO();
        detailDTO.getConditions().add(Restrict.in("group.id", groups));
        List<UserDetail> userDetails = userDetailSer.findByCis(detailDTO);
        List<UserBO> userBOS = new ArrayList<>(userDetails.size());
        userDetails.stream().forEach(detail -> {
            UserBO userBO = BeanTransform.copyProperties(detail.getUser(), UserBO.class);
            userBOS.add(userBO);
        });
        return userBOS;
    }


    private LoginUser currentLoginUser(Object token) throws SerException {
        if (null != token) {
            LoginUser loginUser = UserSession.get(token.toString());
            if (null != loginUser) {
                return loginUser;
            } else { //redis 获取
                String loginUser_str = redis.getMap(UserCommon.LOGIN_USER, token.toString());
                if (StringUtils.isNotBlank(loginUser_str)) {
                    loginUser = JSON.parseObject(loginUser_str, LoginUser.class);
                    UserSession.put(token.toString(), loginUser); //设置到session
                    return loginUser;
                }
            }
            throw new SerException("expire");
        } else {
            throw new SerException("notLogin");
        }
    }

    @Cacheable(value = {"listCache"}, key = "#dto.toString()")
    @Override
    public List<UserBO> findUserByPage(UserDTO dto) throws SerException {
        List<User> list = super.findByPage(dto);
        List<UserBO> boList = BeanTransform.copyProperties(list, UserBO.class);
        return boList;
    }

    @CacheEvict(value = "listCache", allEntries = true)
    @Override
    public UserBO updateUser(UserTO userTO) throws SerException {
        if (StringUtils.isBlank(userTO.getId())) {
            throw new SerException("id不能为空");
        }
        User user = super.findById(userTO.getId());
        user.setUsername(userTO.getUsername());
        user.setPassword(userTO.getPassword());

        UserBO userBO = BeanTransform.copyProperties(user, UserBO.class);
        return userBO;
    }

    @CacheEvict(value = "listCache", key = "#id", allEntries = true, condition = "#id != ''")
    @Override
    public void deleteUser(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public String maxUserEmpNumber() throws SerException {
//        String max = super.findByMaxField("employeeNumber", User.class);
        String[] fields = new String[]{"employeeNumber"};
        String sql = " select max(employeeNumber) as employeeNumber from user where employeeNumber LIKE 'IKE%'  ";
        List<User> list = super.findBySql(sql, User.class, fields);
        String max = list != null && list.size() > 0 ? list.get(0).getEmployeeNumber() : "";
        String empNumber = SeqUtil.generateEmp(max);
        return empNumber;
    }

    @Override
    public String nextEmpNumber(String empNum) throws SerException {
//        empNum = empNum.substring ( 0, 5 );
//        String[] fields = new String[]{"employeeNumber"};
//        String sql = " select max(employeeNumber) as employeeNumber from user where employeeNumber LIKE '" + empNum + "%'  ";
//        List<User> list = super.findBySql ( sql, User.class, fields );
//        String max = list != null && list.size () > 0 ? list.get ( 0 ).getEmployeeNumber () : "";
//        String empNumber = SeqUtil.appGenerateEmp ( max );
        String empNumber = "";
        if (StringUtils.isNotBlank(empNum)) {
            StringBuffer s = new StringBuffer();
            for (int i = 0; i < empNum.length(); i++) {
                char c = empNum.charAt(i);
                if ((c <= 'z' && c >= 'a') || (c <= 'Z' && c >= 'A')) {
                    s.append(c);
                }
            }
            String[] fields = new String[]{"employeeNumber"};
            String sql = " select max(employeeNumber) as employeeNumber from user where employeeNumber LIKE '" + s.toString() + "%'  ";
            List<User> list = super.findBySql(sql, User.class, fields);
            String max = list != null && list.size() > 0 ? list.get(0).getEmployeeNumber() : "";
            if (StringUtils.isNotBlank(max)) {
                empNumber = SeqUtil.appGenerateEmp(max, true);
            } else {
                empNumber = SeqUtil.appGenerateEmp(empNum, false);
            }
        }
        return empNumber;
    }

    @Override
    //chenjunhao
    public String findNameById(String id) throws SerException {
        User user = super.findById(id);
        if (user != null) {
            return user.getUsername();
        }
        return null;
    }

    @Override
    public List<UserBO> findByDept(String... department) throws SerException {
        String depts = "'" + StringUtils.join(department, "','") + "'";
        String sql = "  select a.id,a.username,a.email,a.phone,a.nickname,a.employeeNumber " +
                " from user a,user_department b ,user_detail c where a.id =c.user_id and a.status=0 " +
                " and c.department_id = b.id and (b.id in(" + depts + ") or b.name in(" + depts + "))";
        String[] fields = new String[]{"id", "username", "email", "phone", "nickname", "employeeNumber"};
        List<UserBO> list = super.findBySql(sql, UserBO.class, fields);
        return list;
    }

    @Transactional
    @Override
    public void becomeEnterprise(UserTO userTO) throws SerException {
        UserBO userBO = this.currentUser();
        userBO.getUsername();
        String token = RpcTransmit.getUserToken();
        if (StringUtils.isNotBlank(token)) {
            User user = super.findById(userTO.getId());
            user.setUsername(userTO.getUsername());
            user.setEmployeeNumber(userTO.getEmployeeNumber());
            BeanTransform.copyProperties(userTO, user, true);
            user.setModifyTime(LocalDateTime.now());
            super.update(user);
            //更新session及缓存
            UserBO currentUser = currentUser(token);
            if (currentUser .getId().equals(user.getId())) {
                LoginUser loginUser = new LoginUser();
                BeanUtils.copyProperties(user, loginUser);
                redis.appendToMap(UserCommon.LOGIN_USER, token, JSON.toJSONString(loginUser));
                UserSession.put(token, loginUser);
            }

        } else {
            throw new SerException("notLogin");
        }
    }

    @Override
    public List<UserDetailBO> myTeam() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = this.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String employeeNumber = (userBO.getEmployeeNumber()).substring(0, 4);
        String[] employeeNumbers = new String[]{employeeNumber};
        String sql = "SELECT id,sex,realName,departmentName,positionName\n" +
                "FROM user_detail\n" +
                "WHERE meetingNumber='" + employeeNumbers + "'";
//        List<UserDetailBO> list =
//        String[] meetingNumbers = new String[]{meetingNumber};
//        List<Organization> list = null;
//        for (String s : meetingNumbers) {
//            String sql = "SELECT id,meetingFormat,meetingArea,meetingTopic,content,host,organization\n" +
//                    "FROM negotiatemeeting_organization\n" +
//                    "WHERE meetingNumber='" + s + "'";
//            String[] fileds = new String[]{"id", "meetingFormat", "meetingArea", "meetingTopic", "content", "host", "organization"};
//            list = super.findBySql(sql, Organization.class, fileds);
//        }
//        if ((list != null) && (list.size() != 0)) {
//            return list.get(0);
//        }
//        return null;
        String[] fields = new String[]{"id", "sex", "realName", "departmentName", "positionName"};
        return null;
    }


}