package com.example.docsolutions.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

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
	val body: Body? = null,

    @field:SerializedName("TransactionId")
	val transactionId: String? = null
)

data class RolesLoginResponse(

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("Name")
	val name: String? = null
)

data class UserLoginData(

	@field:SerializedName("FatherLastName")
	val fatherLastName: String? = null,

	@field:SerializedName("Email")
	val email: String? = null,

	@field:SerializedName("Username")
	val username: String? = null,

	@field:SerializedName("SecurityLoginData")
	val securityLoginData: SecurityLoginData? = null,

	@field:SerializedName("Metadata")
	val metadata: List<MetadataLoginResponse?>? = null,

	@field:SerializedName("CurrentFileId")
	val currentFileId: Int? = null,

	@field:SerializedName("PhoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("MotherLastName")
	val motherLastName: String? = null,

	@field:SerializedName("Name")
	val name: String? = null
)

data class ModulesLoginResponde(

	@field:SerializedName("Functions")
	val functions: List<FunctionsItem?>? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("Name")
	val name: String? = null
)

data class MetadataLoginResponse(

	@field:SerializedName("GroupName")
	val groupName: String? = null,

	@field:SerializedName("Value")
	val value: String? = null,

	@field:SerializedName("Name")
	val name: String? = null
)

data class FunctionsItem(

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("Name")
	val name: String? = null
)

data class ApplicationsItem(

	@field:SerializedName("Modules")
	val modules: List<ModulesLoginResponde?>? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("Name")
	val name: String? = null
)

data class SecurityLoginData(

	@field:SerializedName("Applications")
	val applications: List<ApplicationsItem?>? = null,

	@field:SerializedName("Roles")
	val roles: List<RolesLoginResponse?>? = null
)

data class Body(

	@field:SerializedName("UserLoginData")
	val userLoginData: UserLoginData? = null,

	@field:SerializedName("JWTExpireTimeOfflineMinutes")
	val jWTExpireTimeOfflineMinutes: Int? = null,

	@field:SerializedName("Token")
	val token: String? = null
)
