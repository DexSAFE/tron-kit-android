package io.dexnet.tronkit.decoration.trc20

import io.dexnet.tronkit.models.Address
import io.dexnet.tronkit.decoration.TransactionDecoration
import io.dexnet.tronkit.models.TransactionTag
import java.math.BigInteger

class ApproveTrc20Decoration(
    val contractAddress: Address,
    val spender: Address,
    val value: BigInteger
) : TransactionDecoration() {

    override fun tags(userAddress: Address): List<String> =
        listOf(contractAddress.hex, TransactionTag.TRC20_APPROVE)
}
