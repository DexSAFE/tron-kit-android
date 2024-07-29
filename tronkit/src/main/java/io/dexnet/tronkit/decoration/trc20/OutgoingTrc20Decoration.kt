package io.dexnet.tronkit.decoration.trc20

import io.dexnet.tronkit.models.Address
import io.dexnet.tronkit.decoration.TokenInfo
import io.dexnet.tronkit.decoration.TransactionDecoration
import io.dexnet.tronkit.models.TransactionTag
import java.math.BigInteger

class OutgoingTrc20Decoration(
    val contractAddress: Address,
    val to: Address,
    val value: BigInteger,
    val sentToSelf: Boolean,
    val tokenInfo: TokenInfo?
) : TransactionDecoration() {

    override fun tags(userAddress: Address): List<String> =
        listOf(contractAddress.base58, TransactionTag.TRC20_TRANSFER, TransactionTag.trc20Outgoing(contractAddress.base58), TransactionTag.OUTGOING)

}
