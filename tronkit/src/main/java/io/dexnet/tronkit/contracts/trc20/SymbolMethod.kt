package io.dexnet.tronkit.contracts.trc20

import io.dexnet.tronkit.contracts.ContractMethod


class SymbolMethod: ContractMethod() {
    override var methodSignature = "symbol()"
    override fun getArguments() = listOf<Any>()
}
