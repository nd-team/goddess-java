package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.ProofWordsBO;
import com.bjike.goddess.financeinit.dto.ProofWordsDTO;
import com.bjike.goddess.financeinit.entity.ProofWords;
import com.bjike.goddess.financeinit.service.ProofWordsSer;
import com.bjike.goddess.financeinit.to.ProofWordsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 凭证字业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 03:09 ]
 * @Description: [ 凭证字业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("proofWordsApiImpl")
public class ProofWordsApiImpl implements ProofWordsAPI {
    @Autowired
    private ProofWordsSer proofWordsSer;
    @Override
    public Long countProof(ProofWordsDTO proofWordsDTO) throws SerException {
        return proofWordsSer.countProof(proofWordsDTO);
    }

    @Override
    public ProofWordsBO getOneById(String id) throws SerException {
        return proofWordsSer.getOneById(id);
    }

    @Override
    public List<ProofWordsBO> listProof(ProofWordsDTO proofWordsDTO) throws SerException {
        return proofWordsSer.listProof(proofWordsDTO);
    }

    @Override
    public ProofWordsBO addProof(ProofWordsTO proofWordsTO) throws SerException {
        return proofWordsSer.addProof(proofWordsTO);
    }
}