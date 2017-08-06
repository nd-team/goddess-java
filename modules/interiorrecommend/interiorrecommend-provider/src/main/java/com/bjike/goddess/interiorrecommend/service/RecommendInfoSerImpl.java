package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.interiorrecommend.bo.RecommendInfoBO;
import com.bjike.goddess.interiorrecommend.bo.RecommendRequireBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendInfoDTO;
import com.bjike.goddess.interiorrecommend.entity.*;
import com.bjike.goddess.interiorrecommend.enums.GuideAddrStatus;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.RecommendInfoTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.enterprise.inject.spi.Bean;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 推荐信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:54 ]
 * @Description: [ 推荐信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "interiorrecommendSerCache")
@Service
public class RecommendInfoSerImpl extends ServiceImpl<RecommendInfo, RecommendInfoDTO> implements RecommendInfoSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private AwardInfoSer awardInfoSer;
    @Autowired
    private RecommendRequireSer recommendRequireSer;
    @Autowired
    private RecommendAssessDetailSer recommendAssessDetailSer;

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
                throw new SerException("您不是相应部门的人员，不可以操作");
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
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
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
    public RecommendInfoBO insertModel(RecommendInfoTO to) throws SerException {
        checkSeeIdentity();
        UserBO userBO = userAPI.currentUser();
        RecommendRequire recommendRequire = recommendRequireSer.findById(to.getRequireId());
        if (recommendRequire != null) {
            RecommendInfo model = BeanTransform.copyProperties(to, RecommendInfo.class, true);
            //保存推荐考核内容
            if (!CollectionUtils.isEmpty(to.getContentList())) {
                Set<RecommendContent> contentSet = new HashSet<RecommendContent>();
                List<RecommendContent> contentList = BeanTransform.copyProperties(to.getContentList(), RecommendAssessDetail.class);
                contentSet.addAll(contentList);
                model.setContentSet(contentSet);
                model.setRecommendUser(userBO.getUsername());
                model.setRecommendRequire(recommendRequire);
                super.save(model);
                to.setId(model.getId());
                return BeanTransform.copyProperties(to, RecommendRequireBO.class);
            } else {
                throw new SerException("推荐考核内容不能为空!");
            }
        } else {
            throw new SerException("非法推荐要求Id,推荐要求对象不能为空!");
        }
    }

    @Override
    public RecommendInfoBO updateModel(RecommendInfoTO to) throws SerException {
        checkSeeIdentity();
        RecommendRequire recommendRequire = recommendRequireSer.findById(to.getRequireId());
        if (recommendRequire != null) {
            RecommendInfo model = super.findById(to.getId());
            //保存推荐考核内容
            if (!CollectionUtils.isEmpty(to.getContentList())) {
                Set<RecommendContent> detailSet = new HashSet<RecommendContent>();
                List<RecommendContent> detailList = BeanTransform.copyProperties(to.getContentList(), RecommendContent.class);
                detailSet.addAll(detailList);
                for (RecommendContent detail : detailSet) {
                    if (!StringUtils.isEmpty(detail.getId())) {
                        RecommendAssessDetail assessDetail = recommendAssessDetailSer.findById(detail.getId());
                        detail.setCreateTime(assessDetail.getCreateTime());
                        detail.setModifyTime(assessDetail.getModifyTime());
                        detail.setRecommendInfo(model);
                    }
                }
                model.setContentSet(detailSet);
                model.setRecommendRequire(recommendRequire);
                super.update(model);
                return BeanTransform.copyProperties(to, RecommendRequireBO.class);
            } else {
                throw new SerException("非法Id,推荐信息对象不能为空");
            }
        } else {
            throw new SerException("非法推荐要求Id,推荐要求对象不能为空!");
        }
    }

    @Override
    public void delete(String id) throws SerException {
        checkSeeIdentity();
        super.remove(id);
    }

    @Override
    public List<RecommendInfoBO> pageList(RecommendInfoDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        List<RecommendInfo> list = super.findByPage(dto);
        if (!CollectionUtils.isEmpty(list)) {
            List<RecommendInfoBO> boList = new ArrayList<RecommendInfoBO>();
            for (RecommendInfo model : list) {
                RecommendRequire recommendRequire = model.getRecommendRequire();
                RecommendInfoBO bo = BeanTransform.copyProperties(model, RecommendInfoBO.class);
                bo.setOpenTime(DateUtil.dateToString(recommendRequire.getRecommendScheme().getOpenTime()));
                bo.setCloseTime(DateUtil.dateToString(recommendRequire.getRecommendScheme().getCloseTime()));
                bo.setPurpose(recommendRequire.getRecommendScheme().getPurpose());
                bo.setTypeName(recommendRequire.getRecommendType().getTypeName());
                boList.add(bo);
            }
            return boList;
        } else {
            return null;
        }
    }

    @Override
    public void acceptAudit(String id, String reason, Boolean accept) throws SerException {
        checkAddIdentity();
        RecommendInfo model = super.findById(id);
        if (model != null) {
            model.setReason(reason);
            model.setAccept(accept);
            super.update(model);
        } else {
            throw new SerException("非法Id,推荐信息对象不能为空");
        }
    }

    @Override
    public void conformAudit(String id, Boolean conform) throws SerException {
        checkAddIdentity();
        RecommendInfo model = super.findById(id);
        if (model != null) {
            if (model.getAccept() != Boolean.TRUE) {
                throw new SerException("推荐信息审核未通过,无法进行奖励审核!");
            }
            if (model.getConform() == Boolean.FALSE) {
                throw new SerException("已经审核不符合!");
            }
            model.setConform(conform);
            if (conform) {
                AwardInfo awardInfo = new AwardInfo();
                awardInfo.setRecommendInfo(model);
                awardInfoSer.save(awardInfo);
            }
        } else {
            throw new SerException("非法Id,推荐信息对象不能为空");
        }
    }

    @Override
    public List<RecommendInfoBO> awardlist() throws SerException {
        checkSeeIdentity();
        return null;
    }

    @Override
    public List<String> findRequire() throws SerException {
        List<RecommendRequire> requireList = recommendRequireSer.findAll();
        List<String> list = new ArrayList<>();
        for(RecommendRequire recommendRequire : requireList){
            List<String> require = new ArrayList<>();
            require.add(recommendRequire.getRecommendType().getTypeName());
            require.add(recommendRequire.getId());
            list.addAll(require);
        }
        return list;
    }
}