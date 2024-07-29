package io.dexnet.tronkit.decoration

import io.dexnet.tronkit.models.Address

open class TransactionDecoration {
    open fun tags(userAddress: Address): List<String> = listOf()
}
