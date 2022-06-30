package io.moatwel.android.sample.uistatesample.model

import java.io.Serializable
import java.util.UUID


data class FlashMessage(
    val id: UUID,
    val message: String,
) : Serializable {

    companion object {
        fun create(message: String): FlashMessage {
            return FlashMessage(
                UUID.randomUUID(),
                message
            )
        }
    }
}
