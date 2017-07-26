package com.bjike.goddess.annual.service;

import com.bjike.goddess.annual.bo.AnnualStandardBO;
import com.bjike.goddess.annual.dto.AnnualStandardDTO;
import com.bjike.goddess.annual.entity.AnnualStandard;
import com.bjike.goddess.annual.enums.GuideAddrStatus;
import com.bjike.goddess.annual.excel.SonPermissionObject;
import com.bjike.goddess.annual.to.AnnualStandardTO;
import com.bjike.goddess.annual.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 年假标准业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:26 ]
 * @Description: [ 年假标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "annualSerCache")
@Service
public class AnnualStandardSerImpl extends ServiceImpl<AnnualStandard, AnnualStandardDTO> implements AnnualStandardSer {

    @Autowired
    private AnnualArrangementStandardSer annualArrangementStandardSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(项目经理)
     *
     * @throws SerException
     */
    private void checkPonsPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.positCusPermission("2");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是该岗位人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }


    /**
     * 核对总经办审核权限（岗位级别）
     */
    private Boolean guidePosinIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.positCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        {
            List<SonPermissionObject> list = new ArrayList<>();
            String userToken = RpcTransmit.getUserToken();
            Boolean flagInvenDai = guideIdentity();
            RpcTransmit.transmitUserToken(userToken);
            Boolean flagInvenPosinDai = guidePosinIdentity();
            RpcTransmit.transmitUserToken(userToken);

            SonPermissionObject obj = new SonPermissionObject();

            obj = new SonPermissionObject();
            obj.setName("materialanalyzedaily");
            obj.setDescribesion("物质盘点日盘");
            if (flagInvenDai || flagInvenPosinDai) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);



//            RpcTransmit.transmitUserToken(userToken);
//            Boolean flagAnalyAnnu = materialAnalyzeSer.sonPermission();
//            RpcTransmit.transmitUserToken(userToken);
//            obj = new SonPermissionObject();
//            obj.setName("materialAnalyzeannual");
//            obj.setDescribesion("物质分析年盘");
//            if (flagAnalyAnnu) {
//                obj.setFlag(true);
//            } else {
//                obj.setFlag(false);
//            }
//            list.add(obj);


            return list;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case SEE:
                flag = guideIdentity();
                break;
            case AUDIT:
                flag = guidePosinIdentity();
                break;
            case SEEMYSELF:
                flag = true;
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualStandardBO save(AnnualStandardTO to) throws SerException {
        AnnualStandard entity = BeanTransform.copyProperties(to, AnnualStandard.class);
        entity.setStatus(Status.THAW);
        List<AnnualStandardBO> bos = this.findThaw();
        if (null != bos && bos.size() > 0)
            for (AnnualStandardBO standard : bos)
                if ((standard.getStartCycle() <= entity.getStartCycle() && standard.getEndCycle() >= entity.getStartCycle())
                        || (standard.getStartCycle() <= entity.getEndCycle() && standard.getEndCycle() >= entity.getEndCycle()))
                    throw new SerException("工龄范围标准有重合,请重新输入");
        super.save(entity);
        return BeanTransform.copyProperties(entity, AnnualStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualStandardBO update(AnnualStandardTO to) throws SerException {
        AnnualStandard entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, AnnualStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualStandardBO delete(AnnualStandardTO to) throws SerException {
        AnnualStandard entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        if (annualArrangementStandardSer.findByStandard(entity.getId()).size() != 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, AnnualStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualStandardBO congeal(AnnualStandardTO to) throws SerException {
        AnnualStandard entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, AnnualStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualStandardBO thaw(AnnualStandardTO to) throws SerException {
        AnnualStandard entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, AnnualStandardBO.class);
    }

    @Override
    public List<AnnualStandardBO> findThaw() throws SerException {
        AnnualStandardDTO dto = new AnnualStandardDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<AnnualStandard> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, AnnualStandardBO.class);
    }

    @Override
    public AnnualStandardBO findBySeniority(Integer seniority) throws SerException {
        if (null == seniority)
            return new AnnualStandardBO();
        AnnualStandardDTO dto = new AnnualStandardDTO();
        dto.getConditions().add(Restrict.lt_eq("startCycle", seniority));
        dto.getSorts().add("startCycle=asc");
        List<AnnualStandard> list = super.findByCis(dto);
        if (list.size() == 0)
            return null;
        else
            return BeanTransform.copyProperties(list.get(0), AnnualStandardBO.class);
    }

    @Override
    public List<AnnualStandardBO> maps(AnnualStandardDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), AnnualStandardBO.class);
    }

    @Override
    public AnnualStandardBO getById(String id) throws SerException {
        AnnualStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, AnnualStandardBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        AnnualStandardDTO dto = new AnnualStandardDTO();
        return super.count(dto);
    }
}