package io.dexnet.tronkit.decoration.trc20

import io.dexnet.tronkit.models.Address
import io.dexnet.tronkit.decoration.Event
import io.dexnet.tronkit.decoration.TokenInfo
import io.dexnet.tronkit.models.TransactionTag
import io.dexnet.tronkit.models.Trc20EventRecord
import java.math.BigInteger

class Trc20TransferEvent(
    record: Trc20EventRecord
) : Event(record.transactionHash, record.contractAddress) {

    val from: Address = record.from
    val to: Address = record.to
    val value: BigInteger = record.value
    val tokenInfo: TokenInfo = TokenInfo(record.tokenName, record.tokenSymbol, record.tokenDecimal)

    override fun tags(userAddress: Address): List<String> {
        val tags = mutableListOf(contractAddress.base58, TransactionTag.TRC20_TRANSFER)

        if (from == userAddress) {
            tags.add(TransactionTag.trc20Outgoing(contractAddress.base58))
            tags.add(TransactionTag.OUTGOING)
        }

        if (to == userAddress) {
            tags.add(TransactionTag.trc20Incoming(contractAddress.base58))
            tags.add(TransactionTag.INCOMING)
        }

        return tags
    }
}
