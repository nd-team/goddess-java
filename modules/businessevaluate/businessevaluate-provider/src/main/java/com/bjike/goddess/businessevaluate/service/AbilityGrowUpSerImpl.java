package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.AbilityGrowUpBO;
import com.bjike.goddess.businessevaluate.bo.SwapMonthBO;
import com.bjike.goddess.businessevaluate.dto.AbilityGrowUpDTO;
import com.bjike.goddess.businessevaluate.entity.AbilityGrowUp;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.to.AbilityGrowUpTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 能力成长业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 04:13 ]
 * @Description: [ 能力成长业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluateSerCache")
@Service
public class AbilityGrowUpSerImpl extends ServiceImpl<AbilityGrowUp, AbilityGrowUpDTO> implements AbilityGrowUpSer {

    @Autowired
    private EvaluateProjectInfoSer evaluateProjectInfoSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public AbilityGrowUpBO insertModel(AbilityGrowUpTO to) throws SerException {

        AbilityGrowUp model = BeanTransform.copyProperties(to, AbilityGrowUp.class);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, AbilityGrowUpBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public AbilityGrowUpBO updateModel(AbilityGrowUpTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            AbilityGrowUp model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, AbilityGrowUpBO.class);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AbilityGrowUpBO> pageList(AbilityGrowUpDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<AbilityGrowUp> list = super.findByPage(dto);
        List<AbilityGrowUpBO> boList = BeanTransform.copyProperties(list, AbilityGrowUpBO.class);
        //设置项目信息
        if (boList != null && !boList.isEmpty()) {
            for (AbilityGrowUpBO bo : boList) {
                EvaluateProjectInfo info = evaluateProjectInfoSer.findById(bo.getProjectInfoId());
                if (info != null) {
                    bo.setArea(info.getArea());
                    bo.setProject(info.getProject());
                    bo.setStartTime(info.getStartTime().toString());
                    bo.setEndTime(info.getEndTime().toString());
                }
                countGapMoney(bo, info);
            }
        }
        return boList;
    }

    /**
     * 计算月差额（对于一月差额，需要考虑项目周期是否需要减去上一年12月）
     *
     * @param bo   能力成长信息
     * @param info 项目信息
     * @return 能力成长信息
     */
    public AbilityGrowUpBO countGapMoney(AbilityGrowUpBO bo, EvaluateProjectInfo info) throws SerException {
        //如果开始时间和结束时间不在同一年内，则需要与上一年的对应月份比较差额
        //由于工期不稳定，不应该定死字段，应该用List的接受显示而表格显示不满足
        //因此先处理每个月与上月差额

        Double gap1 = 0.0;
        Double gap2 = bo.getFebruaryMoney() - bo.getJanuaryMoney();
        Double gap3 = bo.getMarchMoney() - bo.getFebruaryMoney();
        Double gap4 = bo.getAprilMoney() - bo.getMarchMoney();
        Double gap5 = bo.getMayMoney() - bo.getAprilMoney();
        Double gap6 = bo.getJuneMoney() - bo.getMayMoney();
        Double gap7 = bo.getJulyMoney() - bo.getJuneMoney();
        Double gap8 = bo.getAugustMoney() - bo.getJulyMoney();
        Double gap9 = bo.getSeptemberMoney() - bo.getAugustMoney();
        Double gap10 = bo.getOctoberMoney() - bo.getSeptemberMoney();
        Double gap11 = bo.getNovemberMoney() - bo.getNovemberMoney();
        Double gap12 = bo.getDecemberMoney() - bo.getDecemberMoney();

        bo.setJanuaryGapMoney(gap1);
        bo.setFebruaryGapMoney(gap2);
        bo.setMarchGapMoney(gap3);
        bo.setAprilGapMoney(gap4);
        bo.setMayGapMoney(gap5);
        bo.setJuneGapMoney(gap6);
        bo.setJulyGapMoney(gap7);
        bo.setAugustGapMoney(gap8);
        bo.setSeptemberGapMoney(gap9);
        bo.setOctoberGapMoney(gap10);
        bo.setNovemberGapMoney(gap11);
        bo.setDecemberGapMoney(gap12);

        //比较各月份差额，除了一月差额
        List<SwapMonthBO> bos = new ArrayList<SwapMonthBO>();
        bos.add(new SwapMonthBO("二月", gap2, bo.getFebruaryMoney()));
        bos.add(new SwapMonthBO("三月", gap3, bo.getMarchMoney()));
        bos.add(new SwapMonthBO("四月", gap4, bo.getAprilMoney()));
        bos.add(new SwapMonthBO("五月", gap5, bo.getMayMoney()));
        bos.add(new SwapMonthBO("六月", gap6, bo.getJuneMoney()));
        bos.add(new SwapMonthBO("七月", gap7, bo.getJulyMoney()));
        bos.add(new SwapMonthBO("八月", gap8, bo.getAugustMoney()));
        bos.add(new SwapMonthBO("九月", gap9, bo.getSeptemberMoney()));
        bos.add(new SwapMonthBO("十月", gap10, bo.getOctoberMoney()));
        bos.add(new SwapMonthBO("十一月", gap11, bo.getNovemberMoney()));
        bos.add(new SwapMonthBO("十二月", gap12, bo.getDecemberMoney()));
        swapSpeed(bo, bos);

        //添加易于分比较各月份金额大小
        bos.add(new SwapMonthBO("一月", gap1, bo.getJanuaryMoney()));
        swapSize(bo, bos);

        return bo;

    }

    //排序并设置增速最快最慢月份
    public AbilityGrowUpBO swapSpeed(AbilityGrowUpBO bo, List<SwapMonthBO> bos) throws SerException {
        //设置最快最慢月份
        Collections.sort(bos, new Comparator<SwapMonthBO>() {
            @Override
            public int compare(SwapMonthBO bo1, SwapMonthBO bo2) {
                return bo1.getGapMoney().compareTo(bo2.getGapMoney());
            }
        });
        bo.setSlowMonth(bos.get(0).getMonth());
        bo.setFastMonth(bos.get(bos.size() - 1).getMonth());

        return bo;
    }

    //排序月收入最多最少月份
    public AbilityGrowUpBO swapSize(AbilityGrowUpBO bo, List<SwapMonthBO> bos) throws SerException {
        //设置最快最慢月份
        Collections.sort(bos, new Comparator<SwapMonthBO>() {
            @Override
            public int compare(SwapMonthBO bo1, SwapMonthBO bo2) {
                return bo1.getMonthMoney().compareTo(bo2.getMonthMoney());
            }
        });
        bo.setLeastMonth(bos.get(0).getMonth());
        bo.setMaximumMonth(bos.get(bos.size() - 1).getMonth());

        return bo;
    }


}