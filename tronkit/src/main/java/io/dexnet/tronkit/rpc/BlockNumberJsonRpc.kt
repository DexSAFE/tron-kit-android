package io.dexnet.tronkit.rpc

class BlockNumberJsonRpc : LongJsonRpc(
        method = "eth_blockNumber",
        params = listOf()
)
