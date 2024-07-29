package io.dexnet.tronkit.decoration.trc20

import io.dexnet.tronkit.models.Address
import io.dexnet.tronkit.contracts.trc20.ApproveMethod
import io.dexnet.tronkit.contracts.ContractMethodFactories
import io.dexnet.tronkit.contracts.trc20.ApproveMethodFactory
import io.dexnet.tronkit.contracts.trc20.TransferMethod
import io.dexnet.tronkit.contracts.trc20.TransferMethodFactory
import io.dexnet.tronkit.decoration.Event
import io.dexnet.tronkit.decoration.ITransactionDecorator
import io.dexnet.tronkit.decoration.TransactionDecoration
import io.dexnet.tronkit.hexStringToByteArray
import io.dexnet.tronkit.models.InternalTransaction
import io.dexnet.tronkit.models.TriggerSmartContract

class Trc20TransactionDecorator(
    private val userAddress: Address
) : ITransactionDecorator {

    private val factories = ContractMethodFactories()

    init {
        factories.registerMethodFactories(listOf(TransferMethodFactory, ApproveMethodFactory))
    }

    override fun decoration(
        contract: TriggerSmartContract,
        internalTransactions: List<InternalTransaction>,
        events: List<Event>
    ): TransactionDecoration? {
        val contractMethod = factories.createMethodFromInput(contract.data.hexStringToByteArray())

        return when {
            contractMethod is TransferMethod && contract.ownerAddress == userAddress -> {
                val tokenInfo =
                    (events.firstOrNull { it is Trc20TransferEvent && it.contractAddress == contract.contractAddress } as? Trc20TransferEvent)?.tokenInfo
                OutgoingTrc20Decoration(
                    contractAddress = contract.contractAddress,
                    to = contractMethod.to,
                    value = contractMethod.value,
                    sentToSelf = contractMethod.to == userAddress,
                    tokenInfo = tokenInfo
                )
            }

            contractMethod is ApproveMethod -> {
                ApproveTrc20Decoration(
                    contractAddress = contract.contractAddress,
                    spender = contractMethod.spender,
                    value = contractMethod.value
                )
            }

            else -> null
        }
    }

}
