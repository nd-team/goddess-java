package com.bjike.goddess.financeinit.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.financeinit.enums.ProofCharacter;

/**
 * 凭证字
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 03:09 ]
 * @Description: [ 凭证字 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProofWordsTO extends BaseTO {

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