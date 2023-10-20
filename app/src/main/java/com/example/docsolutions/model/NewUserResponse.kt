package com.example.docsolutions.model

import com.google.gson.annotations.SerializedName

data class NewUserResponse(

	@field:SerializedName("EncryptedBody")
	val encryptedBody: Any? = null,

	@field:SerializedName("ResponseCode")
	val responseCode: Int? = null,

	@field:SerializedName("Messages")
	val messages: Any? = null,

	@field:SerializedName("IsOK")
	val isOK: Boolean? = null,

	@field:SerializedName("SecurityData")
	val securityData: Any? = null,

	@field:SerializedName("Body")
	val body: Boolean? = null,

	@field:SerializedName("TransactionId")
	val transactionId: String? = null
)
