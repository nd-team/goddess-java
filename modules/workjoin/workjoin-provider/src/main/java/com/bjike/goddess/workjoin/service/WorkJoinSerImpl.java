package com.bjike.goddess.workjoin.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.workjoin.bo.WorkJoinBO;
import com.bjike.goddess.workjoin.dto.WorkJoinDTO;
import com.bjike.goddess.workjoin.entity.WorkJoin;
import com.bjike.goddess.workjoin.enums.GuideAddrStatus;
import com.bjike.goddess.workjoin.excel.SonPermissionObject;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.WorkJoinTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 工作交接业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:14 ]
 * @Description: [ 工作交接业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workjoinSerCache")
@Service
public class WorkJoinSerImpl extends ServiceImpl<WorkJoin, WorkJoinDTO> implements WorkJoinSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private DeviceJoinSer deviceJoinSer;
    @Autowired
    private JoinInfoSer joinInfoSer;
    @Autowired
    private TaskJoinSer taskJoinSer;
    @Autowired
    private WorkJoinDutySer workJoinDutySer;
    @Autowired
    private WorkJoinTimeSpecificationSer workJoinTimeSpecificationSer;

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
     * 负责人导航栏核对审核权限（模块级别）
     */
    private void checkAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
            if (!flag) {
                throw new SerException("您不是相应模块的人员，不可以操作");
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
     * 导航栏核对添加修改删除权限（岗位级别）
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

    /**
     * 负责人导航栏核对审核权限（模块级别）
     */
    private Boolean guideAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeWork = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddWork = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("workjoin");
        obj.setDescribesion("工作交接");
        if (flagSeeWork || flagAddWork) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeDevice = deviceJoinSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("devicejoin");
        obj.setDescribesion("设备交接");
        if (flagSeeDevice) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeInfo = joinInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("joininfo");
        obj.setDescribesion("交接资料");
        if (flagSeeInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeTask = taskJoinSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("taskjoin");
        obj.setDescribesion("任务交接");
        if (flagSeeTask) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeDuty = workJoinDutySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("workjoinduty");
        obj.setDescribesion("工作交接责任义务");
        if (flagSeeDuty) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeTime = workJoinTimeSpecificationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("workjointimespecification");
        obj.setDescribesion("工作交接时间规范");
        if (flagSeeTime) {
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
                flag = guideAuditIdentity();
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

    @Override
    public Long countWorkJoin(WorkJoinDTO workJoinDTO) throws SerException {
        Long count = super.count(workJoinDTO);
        return count;
    }

    @Override
    public WorkJoinBO getOne(String id) throws SerException {
        WorkJoin workJoin = super.findById(id);
        return BeanTransform.copyProperties(workJoin, WorkJoinBO.class);
    }

    @Override
    public List<WorkJoinBO> findListWorkJoin(WorkJoinDTO workJoinDTO) throws SerException {
        checkSeeIdentity();
        workJoinDTO.getSorts().add("createTime=desc");
        List<WorkJoin> workJoins = super.findByPage(workJoinDTO);
        List<WorkJoinBO> workJoinBOS = BeanTransform.copyProperties(workJoins, WorkJoinBO.class);
        return workJoinBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WorkJoinBO insertWorkJoin(WorkJoinTO workJoinTO) throws SerException {
        checkAddIdentity();
        WorkJoin workJoin = BeanTransform.copyProperties(workJoinTO, WorkJoin.class, true);
        workJoin.setCreateTime(LocalDateTime.now());
        //设置工作交接编号
        String num = super.findByMaxField("workJoinNum", WorkJoin.class);
        workJoin.setWorkJoinNum(generateNum(num));
        super.save(workJoin);
        return BeanTransform.copyProperties(workJoin, WorkJoinBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WorkJoinBO editWorkJoin(WorkJoinTO workJoinTO) throws SerException {
        checkAddIdentity();
        WorkJoin workJoin = super.findById(workJoinTO.getId());
        LocalDateTime creatTime = workJoin.getCreateTime();
        workJoin = BeanTransform.copyProperties(workJoinTO, WorkJoin.class, true);
        workJoin.setCreateTime(creatTime);
        workJoin.setModifyTime(LocalDateTime.now());
        super.update(workJoin);
        return BeanTransform.copyProperties(workJoin, WorkJoinBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeWorkJoin(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public List<String> getNum() throws SerException {
        String[] fields = new String[]{"workJoinNum"};
        List<WorkJoinBO> workJoinBOS = super.findBySql("select distinct workJoinNum from workjoin_workjoin group by workJoinNum order by workJoinNum asc ", WorkJoinBO.class, fields);

        List<String> collectList = workJoinBOS.stream().map(WorkJoinBO::getWorkJoinNum)
                .filter(workJoinNum -> (workJoinNum != null || !"".equals(workJoinNum.trim()))).distinct().collect(Collectors.toList());

        return collectList;
    }

    private static final String PRO_NUMBER = "GZJJ-"; // 员工编号格式
    private static final Integer PRO_NUMBER_LENGTH = 9; // 员工编号长度
    private static final String START_NUMBER = "0"; // 编号开始
    private static final String ZERO_NUMBER = "000000"; // 员工编号0位数
//    private static String[] ss = DateUtil.dateToString(LocalDate.now()).split("-");

    private static String DATE = LocalDate.now().toString().replaceAll("-", "") + "-";

    /**
     * 生成下一个编号
     *
     * @param num 最大员工编号
     */
    public static synchronized String generateNum(String num) throws SerException {
        if (StringUtils.isNotBlank(num)) {
            Integer number = Integer.parseInt(StringUtils.substringAfterLast(num, "-")) + 1;
            Integer length = PRO_NUMBER_LENGTH - (String.valueOf(number).length());
            if (length > 0) {
                num = PRO_NUMBER + DATE + ZERO_NUMBER.substring(0, length - PRO_NUMBER.length());
            } else if (0 == length) {
                num = PRO_NUMBER + number;
            } else {
                throw new SerException("员工编号超出长度:" + length);
            }
            return num + number;
        } else {
            return generateNum(PRO_NUMBER + DATE + START_NUMBER); //假如为空,则从第一个开始ZFFA--01
        }

    }

    @Override
    public WorkJoinBO audit(WorkJoinTO to) throws SerException {
        checkAuditIdentity();
        WorkJoin workJoin = super.findById(to.getId());
        BeanTransform.copyProperties(to, workJoin, true);
        workJoin.setPrincipal(to.getPrincipal());
        workJoin.setHead(to.getHead());
        super.update(workJoin);
        return BeanTransform.copyProperties(workJoin, WorkJoinBO.class);
    }

}