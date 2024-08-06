package io.dexnet.tronkit.transaction

import io.dexnet.hdwalletkit.ECKey
import io.dexnet.hdwalletkit.HDWallet
import io.dexnet.tronkit.crypto.Utils
import io.dexnet.tronkit.hexStringToByteArray
import io.dexnet.tronkit.models.Address
import io.dexnet.tronkit.network.CreatedTransaction
import io.dexnet.tronkit.network.Network
import java.math.BigInteger

class Signer(
    private val privateKey: BigInteger
) {

    fun sign(createdTransaction: CreatedTransaction): ByteArray {
        val rawTransactionHash = io.dexnet.hdwalletkit.Utils.sha256(createdTransaction.raw_data_hex.hexStringToByteArray())

        return Utils.ellipticSign(rawTransactionHash, privateKey)
    }

    companion object {
        fun getInstance(seed: ByteArray, network: Network): Signer {
            return Signer(privateKey(seed, network))
        }

        fun privateKey(seed: ByteArray, network: Network): BigInteger {
            val hdWallet = HDWallet(seed, network.coinType, HDWallet.Purpose.BIP44)
            return hdWallet.privateKey(0, 0, true).privKey
        }

        fun address(privateKey: BigInteger, network: Network): Address {
            val publicKey = ECKey(privateKey, false).pubKey.drop(1).toByteArray()
            val raw = byteArrayOf(network.addressPrefixByte) + Utils.sha3(publicKey).takeLast(20).toByteArray()
            return Address(raw)
        }
    }
}