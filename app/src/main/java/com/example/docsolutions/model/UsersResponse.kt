package com.example.docsolutions.model

import com.google.gson.annotations.SerializedName

data class UsersResponse(

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
	val body: List<BodyItem?>? = null,

	@field:SerializedName("TransactionId")
	val transactionId: String? = null
)

data class UsersMetadataUsersResponse(

	@field:SerializedName("GroupName")
	val groupName: String? = null,

	@field:SerializedName("Value")
	val value: String? = null,

	@field:SerializedName("Name")
	val name: String? = null
)

data class UserRolesUsersResponse(

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("Name")
	val name: String? = null
)

data class BodyItem(

	@field:SerializedName("CreationDate")
	val creationDate: String? = null,

	@field:SerializedName("FatherLastName")
	val fatherLastName: String? = null,

	@field:SerializedName("Email")
	val email: Any? = null,

	@field:SerializedName("Metadata")
	val metadata: List<Any?>? = null,

	@field:SerializedName("Roles")
	val roles: List<Any?>? = null,

	@field:SerializedName("Name")
	val name: String? = null,

	@field:SerializedName("Locked")
	val locked: Boolean? = null,

	@field:SerializedName("Active")
	val active: Boolean? = null,

	@field:SerializedName("Tenant_Id")
	val tenantId: Int? = null,

	@field:SerializedName("Username")
	val username: String? = null,

	@field:SerializedName("PhoneNumber")
	val phoneNumber: Any? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("MotherLastName")
	val motherLastName: String? = null,

	@field:SerializedName("Password")
	val password: Any? = null
)
