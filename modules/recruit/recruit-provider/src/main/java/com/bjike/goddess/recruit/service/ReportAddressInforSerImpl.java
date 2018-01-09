package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.ReportAddressInforBO;
import com.bjike.goddess.recruit.dto.ReportAddressInforDTO;
import com.bjike.goddess.recruit.entity.ReportAddressInfor;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.ReportAddressInforTO;
import com.bjike.goddess.recruit.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 报道地址信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ReportAddressInforSerImpl extends ServiceImpl<ReportAddressInfor, ReportAddressInforDTO> implements ReportAddressInforSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
            flag = cusPermissionSer.getCusPermission("1",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 福利模块
     */
    private void checkModuleIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3",null);
            if (!flag) {
                throw new SerException("您不是福利模块的人员，不可以操作");
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
            flag = cusPermissionSer.getCusPermission("1",null);
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
            flag = cusPermissionSer.busCusPermission("2",null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 福利模块
     */
    private Boolean guideModuleIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3",null);
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
        Boolean flagM = guideModuleIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagM) {
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
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            case FULI:
                flag = guideModuleIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 分页查询报道地址信息
     *
     * @param dto
     * @return
     * @throws SerException
     */
//    @Cacheable(value = {"listCache"},key="#dto.toString()",condition = "#dto.id!=''")
    @Cacheable(value = {"listCache"},key="#dto.toString()")
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<ReportAddressInforBO> list(ReportAddressInforDTO dto) throws SerException {
        checkSeeIdentity();
        List<ReportAddressInfor> list = super.findByPage(dto);
        List<ReportAddressInforBO> listBO = BeanTransform.copyProperties(list, ReportAddressInforBO.class);
        return listBO;
    }


    /**
     * 保存报道地址信息
     *
     * @param to
     * @return
     * @throws SerException
     */
//    @CachePut(value = "listCache",key = "#to.reportAddress",condition = "#to.reportAddress!=''")
    @CacheEvict( value="listCache",allEntries=true)
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ReportAddressInforBO save(ReportAddressInforTO to) throws SerException {
        checkModuleIdentity();
        ReportAddressInfor failFirstInterviewReason = BeanTransform.copyProperties(to, ReportAddressInfor.class, true);
        failFirstInterviewReason = super.save(failFirstInterviewReason);
        ReportAddressInforBO bo = BeanTransform.copyProperties(failFirstInterviewReason, ReportAddressInforBO.class);
        return bo;
    }

    /**
     * 更新报道地址信息
     *
     * @param to 报道地址信息to
     * @throws SerException
     */
    @CacheEvict( value="listCache",allEntries=true)
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ReportAddressInforTO to) throws SerException {
        checkModuleIdentity();
        if (StringUtils.isNotEmpty(to.getId())) {
            ReportAddressInfor model = super.findById(to.getId());
            if (model != null) {
                updateReportAddressInfor(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新报道地址信息
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateReportAddressInfor(ReportAddressInforTO to, ReportAddressInfor model) throws SerException {
        LocalDateTime createTime = model.getCreateTime();
        model = BeanTransform.copyProperties(to, ReportAddressInfor.class, true);
        model.setCreateTime(createTime);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除报道地址信息
     *
     * @param id
     * @throws SerException
     */
    @CacheEvict( value="listCache",key = "#id",allEntries=true,condition = "#id != ''")
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(String id) throws SerException{
        checkModuleIdentity();
        super.remove(id);
    }

    @Override
    public Set<String> allAddress() throws SerException {
        List<ReportAddressInfor> list = super.findAll();
        Set<String> set = new HashSet<>();
        for (ReportAddressInfor r : list) {
            set.add(r.getReportAddress());
        }
        return set;
    }
}
