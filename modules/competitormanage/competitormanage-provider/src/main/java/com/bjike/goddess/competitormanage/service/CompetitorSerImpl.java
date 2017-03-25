package com.bjike.goddess.competitormanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.competitormanage.bo.CompetitorBO;
import com.bjike.goddess.competitormanage.dto.CompetitorDTO;
import com.bjike.goddess.competitormanage.entity.Competitor;
import com.bjike.goddess.competitormanage.to.CompetitorTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 竞争对手信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-21 04:49 ]
 * @Description: [ 竞争对手信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "competitormanageSerCache")
@Service
public class CompetitorSerImpl extends ServiceImpl<Competitor, CompetitorDTO> implements CompetitorSer {

    @Override
    @Transactional(rollbackFor = SerException.class)
    public CompetitorBO saveCompetitor(CompetitorTO to) throws SerException {
        Competitor model = BeanTransform.copyProperties(to, Competitor.class, true);
        //save前查询当前数据库最新的编号
        CompetitorDTO dto = new CompetitorDTO();
        dto.getSorts().add("createTime=desc");
        List<Competitor> last = super.findByCis(dto);
        if (last != null && !last.isEmpty()) {
            //设置最新编号
            String codeNumStr = last.get(0).getMarkInfoCode().substring(3);
            Integer codeNumInt = Integer.parseInt(codeNumStr);
            Integer newCodeNumInt = codeNumInt + 1;
            String newCodeNumStr = newCodeNumInt + "";
            StringBuffer code = new StringBuffer();
            if (newCodeNumStr.length() > 7) {
                throw new SerException("添加失败，市场信息收集序号溢出!");
            } else {
                code.append("MI-");
                //编码不超过 7 位数
                for (int i = 0; i < 7 - newCodeNumStr.length(); i++) {
                    code.append("0");
                }
                code.append(newCodeNumStr);
            }
            model.setMarkInfoCode(code.toString());
        } else {
            model.setMarkInfoCode("MI-0000001");
        }
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, CompetitorBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public CompetitorBO editCompetitor(CompetitorTO to) throws SerException {

        updateModel(to);
        return BeanTransform.copyProperties(to, CompetitorBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public CompetitorBO editOrganization(CompetitorTO to) throws SerException {
        updateModel(to);
        return BeanTransform.copyProperties(to, CompetitorBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<CompetitorBO> pageList(CompetitorDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<Competitor> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, CompetitorBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        //考虑到编号完整性，删除只冻结数据状态
        Competitor model = super.findById(id);
        if (model != null) {
            model.setStatus(Status.CONGEAL);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("删除对象不存在!");
        }
    }

    /**
     * 更新数据（编辑、审核）
     *
     * @param to 油卡基本信息
     * @throws SerException 更新油卡异常
     */
    public void updateModel(CompetitorTO to) throws SerException {

        if (!StringUtils.isEmpty(to.getId())) {
            Competitor model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }
}