package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.interiorrecommend.bo.RecommendAssessDetailBO;
import com.bjike.goddess.interiorrecommend.bo.RecommendRequireBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendAssessDetailDTO;
import com.bjike.goddess.interiorrecommend.dto.RecommendRequireDTO;
import com.bjike.goddess.interiorrecommend.entity.RecommendAssessDetail;
import com.bjike.goddess.interiorrecommend.entity.RecommendRequire;
import com.bjike.goddess.interiorrecommend.entity.RecommendScheme;
import com.bjike.goddess.interiorrecommend.entity.RecommendType;
import com.bjike.goddess.interiorrecommend.to.RecommendAssessDetailTO;
import com.bjike.goddess.interiorrecommend.to.RecommendRequireTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 推荐要求业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 03:29 ]
 * @Description: [ 推荐要求业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "interiorrecommendSerCache")
@Service
public class RecommendRequireSerImpl extends ServiceImpl<RecommendRequire, RecommendRequireDTO> implements RecommendRequireSer {

    @Autowired
    private RecommendAssessDetailSer recommendAssessDetailSer;
    @Autowired
    private RecommendSchemeSer recommendSchemeSer;
    @Autowired
    private RecommendTypeSer recommendTypeSer;

    @Override
    public RecommendRequireBO insertModel(RecommendRequireTO to) throws SerException {

        RecommendRequire model = BeanTransform.copyProperties(to, RecommendRequire.class, true);
        super.save(model);
        String modelId = model.getId();
        to.setId(modelId);
        //保存推荐内容
        if (to.getAssessDetailList() != null && !to.getAssessDetailList().isEmpty()) {
            for (RecommendAssessDetailTO detailTO : to.getAssessDetailList()) {
                RecommendAssessDetail detail = new RecommendAssessDetail();
                detail = BeanTransform.copyProperties(detailTO, RecommendAssessDetail.class);
                detail.setRequireId(modelId);
                recommendAssessDetailSer.save(detail);
            }
        }
        return BeanTransform.copyProperties(to, RecommendRequireBO.class);
    }

    @Override
    public RecommendRequireBO updateModel(RecommendRequireTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            RecommendRequire model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);

                if (to.getAssessDetailList() != null && !to.getAssessDetailList().isEmpty()) {

                    for (RecommendAssessDetailTO detailTO : to.getAssessDetailList()) {
                        RecommendAssessDetail detail = new RecommendAssessDetail();
                        detail = recommendAssessDetailSer.findById(detailTO.getId());
                        BeanTransform.copyProperties(detailTO, detail);
                        recommendAssessDetailSer.update(detail);
                    }
                }

            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, RecommendRequireBO.class);
    }

    @Override
    public List<RecommendRequireBO> pageList(RecommendRequireDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<RecommendRequire> list = super.findByPage(dto);
        List<RecommendRequireBO> boList = new ArrayList<RecommendRequireBO>();
        boList = BeanTransform.copyProperties(list, RecommendRequireBO.class);
        DateUtil dateUtil = new DateUtil();
        if (boList != null && !boList.isEmpty()) {
            for (RecommendRequireBO bo : boList) {
                RecommendScheme recommendScheme = recommendSchemeSer.findById(bo.getRecommendSchemeId());
                if (recommendScheme != null) {
                    bo.setOpenTime(dateUtil.dateToString(recommendScheme.getOpenTime()));
                    bo.setCloseTime(dateUtil.dateToString(recommendScheme.getCloseTime()));
                }
                RecommendType recommendType = recommendTypeSer.findById(bo.getRecommendTypeId());
                if (recommendType != null) {
                    bo.setRecommendType(recommendType.getTypeName());
                }
            }
        }

        return boList;
    }

    @Override
    public List<RecommendAssessDetailBO> findAssessDetail(String requireId) throws SerException {
        RecommendAssessDetailDTO dto = new RecommendAssessDetailDTO();
        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("requireId", requireId));
        return BeanTransform.copyProperties(recommendAssessDetailSer.findByCis(dto), RecommendAssessDetailBO.class);
    }

    @Override
    public void delete(String id) throws SerException {

        //先除其子表关联数据，再删除推荐要求数据
        RecommendAssessDetailDTO dto = new RecommendAssessDetailDTO();
        dto.getConditions().add(Restrict.eq("requireId", id));
        List<RecommendAssessDetail> detailList = recommendAssessDetailSer.findByCis(dto);
        if (detailList != null && !detailList.isEmpty()) {
            recommendAssessDetailSer.remove(detailList);
        }
        super.remove(id);
    }
}