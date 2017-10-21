package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.ProofWordsBO;
import com.bjike.goddess.financeinit.dto.ProofWordsDTO;
import com.bjike.goddess.financeinit.entity.ProofWords;
import com.bjike.goddess.financeinit.to.ProofWordsTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.beans.beancontext.BeanContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 凭证字业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 03:09 ]
 * @Description: [ 凭证字业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class ProofWordsSerImpl extends ServiceImpl<ProofWords, ProofWordsDTO> implements ProofWordsSer {
    @Override
    public Long countProof(ProofWordsDTO proofWordsDTO) throws SerException {
        Long count = super.count(proofWordsDTO);
        return count;
    }

    @Override
    public ProofWordsBO getOneById(String id) throws SerException {
        ProofWords proofWords = super.findById(id);
        return BeanTransform.copyProperties(proofWords,ProofWordsBO.class);
    }

    @Override
    public List<ProofWordsBO> listProof(ProofWordsDTO proofWordsDTO) throws SerException {
        List<ProofWords> proofWords = super.findByCis(proofWordsDTO);
        return BeanTransform.copyProperties(proofWords,ProofWordsBO.class);
    }

    @Override
    public ProofWordsBO addProof(ProofWordsTO proofWordsTO) throws SerException {
        ProofWords proofWords = BeanTransform.copyProperties(proofWordsTO,ProofWords.class,true);
        proofWords.setCreateTime(LocalDateTime.now());
        super.save(proofWords);
        return BeanTransform.copyProperties(proofWords,ProofWordsBO.class);
    }
}