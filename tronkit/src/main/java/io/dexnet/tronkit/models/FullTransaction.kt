package io.dexnet.tronkit.models

import io.dexnet.tronkit.decoration.TransactionDecoration

class FullTransaction(
    val transaction: Transaction,
    val decoration: TransactionDecoration
)
