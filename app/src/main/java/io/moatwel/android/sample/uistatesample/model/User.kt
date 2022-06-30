package io.moatwel.android.sample.uistatesample.model

import java.io.Serializable


data class User(
    val id: Int,
    val name: String,
    val avatarUrl: String,
) : Serializable
