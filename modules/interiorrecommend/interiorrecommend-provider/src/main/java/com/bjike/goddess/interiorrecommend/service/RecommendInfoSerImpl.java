package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.interiorrecommend.bo.RecommendContentBO;
import com.bjike.goddess.interiorrecommend.bo.RecommendInfoBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendContentDTO;
import com.bjike.goddess.interiorrecommend.dto.RecommendInfoDTO;
import com.bjike.goddess.interiorrecommend.entity.*;
import com.bjike.goddess.interiorrecommend.to.RecommendContentTO;
import com.bjike.goddess.interiorrecommend.to.RecommendInfoTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private RecommendContentSer recommendContentSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private AwardInfoSer awardInfoSer;
    @Autowired
    private RecommendRequireSer recommendRequireSer;
    @Autowired
    private RecommendSchemeSer recommendSchemeSer;

    @Override
    public RecommendInfoBO insertModel(RecommendInfoTO to) throws SerException {
        RecommendInfo model = BeanTransform.copyProperties(to, RecommendInfo.class, true);
        super.save(model);
        String modelId = model.getId();
        to.setId(modelId);
        //保存推荐内容
        if (to.getContentList() != null && !to.getContentList().isEmpty()) {
            for (RecommendContentTO contentTO : to.getContentList()) {
                RecommendContent detail = new RecommendContent();
                detail = BeanTransform.copyProperties(contentTO, RecommendContent.class);
                detail.setCreateUser(userAPI.currentUser().getUsername());
                detail.setInfoId(modelId);
                recommendContentSer.save(detail);
            }
        }
        return BeanTransform.copyProperties(to, RecommendInfoBO.class);
    }

    @Override
    public RecommendInfoBO updateModel(RecommendInfoTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            RecommendInfo model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);

                if (to.getContentList() != null && !to.getContentList().isEmpty()) {

                    for (RecommendContentTO contentTO : to.getContentList()) {
                        RecommendContent detail = new RecommendContent();
                        detail = recommendContentSer.findById(contentTO.getId());
                        BeanTransform.copyProperties(contentTO, detail);
                        detail.setUpdateUser(userAPI.currentUser().getUsername());
                        recommendContentSer.update(detail);
                    }
                }

            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, RecommendInfoBO.class);
    }

    @Override
    public void delete(String id) throws SerException {
        //先除其子表关联数据，再删除推荐要求数据
        RecommendContentDTO dto = new RecommendContentDTO();
        dto.getConditions().add(Restrict.eq("infoId", id));
        List<RecommendContent> detailList = recommendContentSer.findByCis(dto);
        if (detailList != null && !detailList.isEmpty()) {
            recommendContentSer.remove(detailList);
        }
        super.remove(id);
    }

    @Override
    public List<RecommendInfoBO> pageList(RecommendInfoDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<RecommendInfo> list = super.findByPage(dto);
        List<RecommendInfoBO> boList = new ArrayList<RecommendInfoBO>();
        boList = BeanTransform.copyProperties(list, RecommendInfoBO.class);
        DateUtil dateUtil = new DateUtil();
        if (boList != null && !boList.isEmpty()) {
            for (RecommendInfoBO bo : boList) {
                RecommendRequire require = recommendRequireSer.findById(bo.getRequireId());
                if (require != null) {
                    RecommendScheme recommendScheme = recommendSchemeSer.findById(require.getRecommendSchemeId());
                    if (recommendScheme != null) {
                        bo.setOpenTime(dateUtil.dateToString(recommendScheme.getOpenTime()));
                        bo.setCloseTime(dateUtil.dateToString(recommendScheme.getCloseTime()));
                    }
                }
            }
        }
        return boList;
    }

    @Override
    public List<RecommendContentBO> findContent(String infoId) throws SerException {
        RecommendContentDTO dto = new RecommendContentDTO();
        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("infoId", infoId));
        return BeanTransform.copyProperties(recommendContentSer.findByCis(dto), RecommendContentBO.class);
    }

    @Override
    public void acceptAudit(String id, String reason, Boolean accept) throws SerException {
        if (!StringUtils.isEmpty(id)) {
            RecommendInfo model = super.findById(id);
            if (model != null) {
                model.setReason(reason);
                model.setAccept(accept);
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    public void conformAudit(String id, Boolean conform) throws SerException {
        if (!StringUtils.isEmpty(id)) {
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
                    awardInfo.setInfoId(id);
                    awardInfoSer.save(awardInfo);
                }

            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }
}