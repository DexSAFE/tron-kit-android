package io.dexnet.tronkit.decoration

import io.dexnet.tronkit.models.InternalTransaction
import io.dexnet.tronkit.models.TriggerSmartContract

interface ITransactionDecorator {
    fun decoration(
        contract: TriggerSmartContract,
        internalTransactions: List<InternalTransaction>,
        events: List<Event>
    ): TransactionDecoration?
}
