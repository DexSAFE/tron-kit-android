package io.dexnet.tronkit.contracts.trc20

import io.dexnet.tronkit.contracts.ContractMethod


class DecimalsMethod: ContractMethod() {
    override var methodSignature = "decimals()"
    override fun getArguments() = listOf<Any>()
}
