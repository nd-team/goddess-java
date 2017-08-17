package com.bjike.goddess.firmreward.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.bo.AwardDetailBO;
import com.bjike.goddess.firmreward.bo.RewardPeopleNoStatBO;
import com.bjike.goddess.firmreward.dto.AwardDetailDTO;
import com.bjike.goddess.firmreward.dto.RewardPeopleNoStatDTO;
import com.bjike.goddess.firmreward.entity.AwardDetail;
import com.bjike.goddess.firmreward.entity.RewardPeopleNoStat;
import com.bjike.goddess.firmreward.enums.GuideAddrStatus;
import com.bjike.goddess.firmreward.to.PeopleNoStatTO;
import com.bjike.goddess.firmreward.to.PeopleTO;
import com.bjike.goddess.firmreward.to.RewardPeopleNoStatTO;
import com.bjike.goddess.firmreward.vo.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 奖励人数统计业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:45 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "firmrewardSerCache")
@Service
public class RewardPeopleNoStatSerImpl extends ServiceImpl<RewardPeopleNoStat, RewardPeopleNoStatDTO> implements RewardPeopleNoStatSer {

    @Autowired
    private AwardDetailSer awardDetailSer;

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
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
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
            flag = cusPermissionSer.busCusPermission("2");
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
        return flag;
    }

    /**
     * 分页查询奖励人数统计
     *
     * @return class RewardPeopleNoStatBO
     * @throws SerException
     */
    @Override
    public List<RewardPeopleNoStatBO> list(RewardPeopleNoStatDTO dto) throws SerException {
        List<RewardPeopleNoStat> list = super.findByPage(dto);
        List<RewardPeopleNoStatBO> listBO = BeanTransform.copyProperties(list, RewardPeopleNoStatBO.class);
        return listBO;
    }

    /**
     * 保存奖励人数统计
     *
     * @param to 奖励人数统计to
     * @return class RewardPeopleNoStatBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public RewardPeopleNoStatBO save(RewardPeopleNoStatTO to) throws SerException {
        RewardPeopleNoStat entity = BeanTransform.copyProperties(to, RewardPeopleNoStat.class, true);
        entity = super.save(entity);
        RewardPeopleNoStatBO bo = BeanTransform.copyProperties(entity, RewardPeopleNoStatBO.class);
        return bo;
    }

    /**
     * 根据id删除奖励人数统计
     *
     * @param id 奖励人数统计唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        List<AwardDetail> list = getAwardDetailsByStatId(id);
        awardDetailSer.remove(list);
        super.remove(id);
    }

    /**
     * 根据奖励人数统计id删除奖励明细
     *
     * @param id 奖励人数统计id
     * @return
     */
    private List<AwardDetail> getAwardDetailsByStatId(String id) throws SerException {
        AwardDetailDTO dto = new AwardDetailDTO();
        dto.getConditions().add(Restrict.eq("awardPersonNoStatId", id));
        return awardDetailSer.findByCis(dto);
    }

    /**
     * 更新奖励人数统计
     *
     * @param to 奖励人数统计to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(RewardPeopleNoStatTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            RewardPeopleNoStat model = super.findById(to.getId());
            if (model != null) {
                updateRewardPeopleNoStat(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新奖励人数统计
     *
     * @param to    奖励人数统计to
     * @param model 奖励人数统计
     */
    private void updateRewardPeopleNoStat(RewardPeopleNoStatTO to, RewardPeopleNoStat model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 添加获奖明细
     *
     * @param to 奖励人数统计to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void addAwardDetails(PeopleNoStatTO to) throws SerException {
        String rewardPeopleNoStatId = to.getId();//奖励人数统计id
        List<PeopleTO> peopleTOS = to.getPeopleTOS();
        if (peopleTOS != null && peopleTOS.size() > 0) {
            List<AwardDetail> list = new ArrayList<>(0);
            if (StringUtils.isNotBlank(rewardPeopleNoStatId)) {
                for (PeopleTO peopleTO : peopleTOS) {
                    AwardDetail model = new AwardDetail();
                    model.setAwardRanking(peopleTO.getAwardRankings());//获奖名次
                    model.setPrizewinner(peopleTO.getPrizewinners());//获奖人姓名
                    model.setBonusLimit(peopleTO.getBonusLimits());  //奖金额度
                    model.setEmpiricalValueLimit(peopleTO.getEmpiricalValueLimits());//经验值额度
                    model.setHonorAwardLimit(peopleTO.getHonorAwardLimits()); //荣誉衍生奖品额度
                    model.setAwardPersonNoStatId(rewardPeopleNoStatId);
                    list.add(model);
                }
                awardDetailSer.save(list);
            } else {
                throw new SerException("奖励人数统计id为空,无法进行");
            }
        }
    }

    /**
     * 更新获奖明细
     *
     * @param to 奖励人数统计to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void updateAwardDetails(PeopleNoStatTO to) throws SerException {
        String rewardPeopleNoStatId = to.getId();
        List<AwardDetail> list = getAwardDetailsByStatId(rewardPeopleNoStatId);
        awardDetailSer.remove(list);//执行删除操作
        addAwardDetails(to);//再执行插入操作
    }

    /**
     * 查看获奖明细
     *
     * @param statId 奖励人数统计id
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    @Override
    public List<AwardDetailBO> checkAwardDetails(String statId) throws SerException {
        List<AwardDetail> list = getAwardDetailsByStatId(statId);
        return BeanTransform.copyProperties(list, AwardDetailBO.class);
    }
}