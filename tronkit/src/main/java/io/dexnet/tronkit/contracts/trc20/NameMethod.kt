package io.dexnet.tronkit.contracts.trc20

import io.dexnet.tronkit.contracts.ContractMethod


class NameMethod: ContractMethod() {
    override var methodSignature = "name()"
    override fun getArguments() = listOf<Any>()
}
