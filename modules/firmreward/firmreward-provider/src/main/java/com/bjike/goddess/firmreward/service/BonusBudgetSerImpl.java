package com.bjike.goddess.firmreward.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.bo.BonusBudgetBO;
import com.bjike.goddess.firmreward.bo.RewardProgramRatioBO;
import com.bjike.goddess.firmreward.dto.BonusBudgetDTO;
import com.bjike.goddess.firmreward.dto.RewardProgramRatioDTO;
import com.bjike.goddess.firmreward.entity.BonusBudget;
import com.bjike.goddess.firmreward.entity.RewardProgramRatio;
import com.bjike.goddess.firmreward.enums.GuideAddrStatus;
import com.bjike.goddess.firmreward.excel.SonPermissionObject;
import com.bjike.goddess.firmreward.to.BonusBudgetTO;
import com.bjike.goddess.firmreward.to.RewardProgramRatiosTO;
import com.bjike.goddess.firmreward.to.RewardProgramTO;
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
 * 奖金预算业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "firmrewardSerCache")
@Service
public class BonusBudgetSerImpl extends ServiceImpl<BonusBudget, BonusBudgetDTO> implements BonusBudgetSer {


    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private PrizeApplySer prizeApplySer;

    @Autowired
    private RewardIndicatorSer rewardIndicatorSer;

    @Autowired
    private RewardPeopleNoStatSer rewardPeopleNoStatSer;

    @Autowired
    private RewardProgramRatioSer rewardProgramRatioSer;

    @Autowired
    private AwardDetailSer awardDetailSer;

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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("bonusBudget");
        obj.setDescribesion("奖金预算");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagPrize = prizeApplySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("prizeApply");
        obj.setDescribesion("奖品申请");
        if (flagPrize) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagRewardIndicator = rewardIndicatorSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("rewardIndicator");
        obj.setDescribesion("奖励指标");
        if (flagRewardIndicator) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagRewardPeople = rewardPeopleNoStatSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("rewardPeople");
        obj.setDescribesion("奖励人数统计");
        if (flagRewardPeople) {
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
     * 分页查询奖金预算
     *
     * @return class BonusBudgetBO
     * @throws SerException
     */
    @Override
    public List<BonusBudgetBO> list(BonusBudgetDTO dto) throws SerException {
        checkAddIdentity();
        List<BonusBudget> list = super.findByPage(dto);
        List<BonusBudgetBO> listBO = BeanTransform.copyProperties(list, BonusBudgetBO.class);
        return listBO;
    }

    /**
     * 保存奖金预算
     *
     * @param to 奖金预算to
     * @return class BonusBudgetBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public BonusBudgetBO save(BonusBudgetTO to) throws SerException {
        checkAddIdentity();
        BonusBudget entity = BeanTransform.copyProperties(to, BonusBudget.class, true);
        entity = super.save(entity);
        BonusBudgetBO bo = BeanTransform.copyProperties(entity, BonusBudgetBO.class);
        return bo;
    }

    /**
     * 根据id删除奖金预算
     *
     * @param id 奖金预算唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkAddIdentity();
        List<RewardProgramRatio> list = getRewardRatioById(id);
        rewardProgramRatioSer.remove(list);//删除子表中的数据
        super.remove(id);
    }

    /**
     * 更新奖金预算
     *
     * @param to 奖金预算to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(BonusBudgetTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotEmpty(to.getId())) {
            BonusBudget model = super.findById(to.getId());
            if (model != null) {
                updateBonusBudget(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新奖金预算
     *
     * @param to    奖金预算to
     * @param model 奖金预算
     */
    private void updateBonusBudget(BonusBudgetTO to, BonusBudget model) throws SerException {
        checkAddIdentity();
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 添加奖励项目比例
     *
     * @param rewardProgramTO 奖金预算to
     * @throws SerException
     */
    @Override
    public void addRewardProgramRatios(RewardProgramTO rewardProgramTO) throws SerException {
        checkAddIdentity();
        List<RewardProgramRatiosTO> rewardProgramRatiosTOS = rewardProgramTO.getRewardProgramRatiosTOS();
        String bonusBudgetId = rewardProgramTO.getId();//奖金预算额度
        if (rewardProgramRatiosTOS != null && rewardProgramRatiosTOS.size() > 0 && StringUtils.isNotEmpty(bonusBudgetId)) {
            List<RewardProgramRatio> list = new ArrayList<>(0);
            for (RewardProgramRatiosTO to : rewardProgramRatiosTOS) {
                RewardProgramRatio model = new RewardProgramRatio();
                model.setRewardProgram(to.getRewardPrograms());//奖励项目
                model.setFocusingDegree(to.getFocusingDegrees());//当月侧重程度
                model.setBudgetRange(to.getBudgetRanges());//当月预算范围
                model.setBonusWeight(to.getBonusWeights());//奖金权重
                model.setBonusLimit(to.getBonusLimits());//奖金额度
                model.setHonorWeight(to.getHonorWeights());//荣誉衍生奖品权重
                model.setHonorLimit(to.getHonorLimits());//荣誉衍生奖品额度
                model.setEmpiricalValue(to.getEmpiricalValues());//经验值
                model.setEmpiricalValueLimit(to.getEmpiricalValueLimits());//经验值额度
                model.setEmpiricalValueToMoney(to.getEmpiricalValueLimits() * 10);
                model.setBonusBudgetId(bonusBudgetId);
                list.add(model);
            }
            rewardProgramRatioSer.save(list);//执行批量保存操作
        }
    }

    /**
     * 更新奖励项目比例
     *
     * @param rewardProgramTO 奖金预算to
     * @throws SerException
     */
    @Override
    public void updateRewardProgramRatios(RewardProgramTO rewardProgramTO) throws SerException {
        checkAddIdentity();
        List<RewardProgramRatio> list = getRatioByBudgetTo(rewardProgramTO);
        rewardProgramRatioSer.remove(list);//删除奖励项目比例
        addRewardProgramRatios(rewardProgramTO);//重新执行插入操作
    }

    /**
     * 根据奖金预算TO查询奖金项目比例
     *
     * @param rewardProgramTO
     * @return
     * @throws SerException
     */
    private List<RewardProgramRatio> getRatioByBudgetTo(RewardProgramTO rewardProgramTO) throws SerException {
        checkSeeIdentity();
        String bonusBudgetId = rewardProgramTO.getId();//奖金预算id
        if (StringUtils.isBlank(bonusBudgetId)) {
            throw new SerException("奖金预算唯一标识为空,无法执行查询");
        }
        return getRewardRatioById(bonusBudgetId);
    }

    /**
     * 根据奖金预算id查询奖金项目比例
     *
     * @param bonusBudgetId 奖金项目比例
     * @return class RewardProgramRatio
     * @throws SerException
     */
    private List<RewardProgramRatio> getRewardRatioById(String bonusBudgetId) throws SerException {
        checkSeeIdentity();
        RewardProgramRatioDTO dto = new RewardProgramRatioDTO();
        dto.getConditions().add(Restrict.eq("bonusBudgetId", bonusBudgetId));
        return rewardProgramRatioSer.findByCis(dto);
    }

    /**
     * 查看奖励项目比例
     *
     * @param id 奖金预算id
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    @Override
    public List<RewardProgramRatioBO> checkRewardProgramRatios(String id) throws SerException {
        checkSeeIdentity();
        List<RewardProgramRatio> list = getRewardRatioById(id);
        return BeanTransform.copyProperties(list, RewardProgramRatioBO.class);
    }
}