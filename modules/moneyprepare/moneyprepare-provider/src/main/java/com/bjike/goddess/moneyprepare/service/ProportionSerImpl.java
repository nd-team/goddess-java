package com.bjike.goddess.moneyprepare.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.moneyprepare.bo.ProportionBO;
import com.bjike.goddess.moneyprepare.dto.ProportionDTO;
import com.bjike.goddess.moneyprepare.entity.Proportion;
import com.bjike.goddess.moneyprepare.enums.GuideAddrStatus;
import com.bjike.goddess.moneyprepare.to.GuidePermissionTO;
import com.bjike.goddess.moneyprepare.to.ProportionObjectTO;
import com.bjike.goddess.moneyprepare.to.ProportionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 比例分配业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 11:59 ]
 * @Description: [ 比例分配业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneyprepareSerCache")
@Service
public class ProportionSerImpl extends ServiceImpl<Proportion, ProportionDTO> implements ProportionSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Override
    public Long countProportion(ProportionDTO proportionDTO) throws SerException {
        searchCondition(proportionDTO);
        Long count = super.count(proportionDTO);
        return count;
    }

    @Override
    public ProportionBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }

        Proportion proportion = super.findById(id);
        return BeanTransform.copyProperties(proportion, ProportionBO.class);
    }

    @Override
    public List<ProportionBO> listProportion(ProportionDTO proportionDTO) throws SerException {
        //checkSeeIdentity();

        searchCondition(proportionDTO);
        List<Proportion> list = super.findByPage(proportionDTO);
        List<ProportionBO> proportionBOS = new ArrayList<>();
        list.stream().forEach(str -> {
            ProportionBO bo = BeanTransform.copyProperties(str, ProportionBO.class);
            proportionBOS.add(bo);
        });
        return proportionBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<ProportionBO> addProportion(ProportionObjectTO proportionObjectTO) throws SerException {
//        checkAddIdentity();
        //DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //LocalDate date = LocalDate.parse(proportionTO.getStartProjectTime(),format);
        List<ProportionTO> proportionTOList = proportionObjectTO.getProportionTOList();
        List<ProportionBO> bos = new ArrayList<>();

        //得到当前输入的时间
        String nowTime = "";
        //对传进来的时间进行判断
        if (null != proportionTOList && proportionTOList.size() > 0) {
            for (ProportionTO to : proportionTOList) {
                try {
                    DateUtil.parseDate(to.getTime());
                } catch (Exception e) {
                    throw new SerException("输入的日期格式不对");
                }
                if (StringUtils.isBlank(nowTime)) {
                    nowTime = to.getTime();
                }
                if (StringUtils.isNotBlank(nowTime) && !nowTime.equals(to.getTime())) {
                    throw new SerException("添加时请确保时间是统一的");
                }
            }
        }


        if (null != proportionTOList && proportionTOList.size() > 0) {
            //对比例分配进行判断,是否满足100%
            Double num = 0d;
            for (ProportionTO to : proportionTOList) {
                num += to.getRatio();
            }
            if (num == 1) {
                for (ProportionTO to : proportionTOList) {
                    //对时间进行处理,转换为当月1号
                    StringBuffer sb = new StringBuffer();
//                    LocalDate localDate =LocalDate.parse(fundPrepareTO.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    sb.append(to.getTime().subSequence(0, to.getTime().lastIndexOf("-") + 1) + "01");
                    to.setTime(sb.toString());
                    nowTime = sb.toString();
                    Proportion proportion = BeanTransform.copyProperties(to, Proportion.class, true);

                    proportion.setCreateTime(LocalDateTime.now());
                    super.save(proportion);

                    ProportionBO proportionBO = BeanTransform.copyProperties(proportion, ProportionBO.class);
                    bos.add(proportionBO);
                }
            } else {
                throw new SerException("分配比例不满足要求");
            }

            //根据时间查询当月的比例分配
            ProportionDTO proportionDTO = new ProportionDTO();
            proportionDTO.getConditions().add(Restrict.eq("time", nowTime));
            List<Proportion> proportions = super.findByCis(proportionDTO);
            Double radios = 0d;
            for (Proportion pro : proportions) {
                radios += pro.getRatio();
            }
            if (radios != 1) {
                throw new SerException("比例添加后当月总比例不符合100%,请输入正确的比例");
            }
        }
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProportionBO editProportion(ProportionTO proportionTO) throws SerException {
//        checkAddIdentity();

        Proportion temp = super.findById(proportionTO.getId());

        try {
            DateUtil.parseDate(proportionTO.getTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式不对,yyyy-MM-dd");
        }
        //对时间进行处理,转换为当月1号
        StringBuffer sb = new StringBuffer();
//        LocalDate localDate =LocalDate.parse(fundPrepareTO.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        sb.append(proportionTO.getTime().subSequence(0, proportionTO.getTime().lastIndexOf("-") + 1) + "01");
        proportionTO.setTime(sb.toString());


        Proportion proportion = BeanTransform.copyProperties(proportionTO, Proportion.class);
        BeanUtils.copyProperties(proportion, temp, "id", "createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        //根据时间查询当月的比例分配
        ProportionDTO proportionDTO = new ProportionDTO();
        proportionDTO.getConditions().add(Restrict.eq("time", sb.toString()));
        List<Proportion> proportions = super.findByCis(proportionDTO);
        Double radios = 0d;
        for (Proportion pro : proportions) {
            radios += pro.getRatio();
        }
        if (radios > 1) {
            throw new SerException("比例修改后当月总比例超过100%,请输入正确的比例");
        }

        ProportionBO proportionBO = BeanTransform.copyProperties(temp, ProportionBO.class);
        return proportionBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteProportion(String id) throws SerException {
//        checkAddIdentity();

        super.remove(id);
    }

    public void searchCondition(ProportionDTO proportionDTO) throws SerException {
        /**
         * 时间
         */
        if (StringUtils.isNotBlank(proportionDTO.getTime())) {
            proportionDTO.getConditions().add(Restrict.eq("time", proportionDTO.getTime()));
        }
        /**
         * 项目组
         */
        if (StringUtils.isNotBlank(proportionDTO.getProjectTeam())) {
            proportionDTO.getConditions().add(Restrict.like("projectTeam", proportionDTO.getProjectTeam()));
        }

        /**
         * 分配比例
         */
        if (proportionDTO.getRatio() != null) {
            proportionDTO.getConditions().add(Restrict.like("ratio", proportionDTO.getRatio()));
        }

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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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
    public List<ProportionBO> editDetail(ProportionTO proportionTO) throws SerException {

        return null;
    }
}