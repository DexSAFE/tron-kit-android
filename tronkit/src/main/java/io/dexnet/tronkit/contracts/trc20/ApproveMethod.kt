package io.dexnet.tronkit.contracts.trc20

import io.dexnet.tronkit.models.Address
import io.dexnet.tronkit.contracts.ContractMethod
import java.math.BigInteger

class ApproveMethod(val spender: Address, val value: BigInteger) : ContractMethod() {

    override val methodSignature = Companion.methodSignature
    override fun getArguments() = listOf(spender, value)

    companion object {
        const val methodSignature = "approve(address,uint256)"
    }

}
