package com.bjike.goddess.financeinit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.financeinit.enums.ProofCharacter;

/**
 * 凭证字业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 03:09 ]
 * @Description: [ 凭证字业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProofWordsBO extends BaseBO {

    /**
     * 凭证字
     */
    private ProofCharacter proofCharacter;


    public ProofCharacter getProofCharacter() {
        return proofCharacter;
    }

    public void setProofCharacter(ProofCharacter proofCharacter) {
        this.proofCharacter = proofCharacter;
    }
}