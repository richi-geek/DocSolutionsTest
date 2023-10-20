package com.example.docsolutions.model

import com.google.gson.annotations.SerializedName

data class NewUserPost(

	@field:SerializedName("Body")
	val body: BodyNewUser? = null
)

data class RolesNewUser(

	@field:SerializedName("Id")
	val id: Int? = 2,

	@field:SerializedName("Name")
	val name: String? = "Usuario Tradicional"
)

data class BodyNewUser(

	@field:SerializedName("FatherLastName")
	val fatherLastName: String? = null,

	@field:SerializedName("Tenant")
	val tenant: Any? = null,

	@field:SerializedName("UserName")
	val userName: String? = null,

	@field:SerializedName("Email")
	val email: String? = null,

	@field:SerializedName("Metadata")
	val metadata: Any? = null,

	@field:SerializedName("PhoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("Roles")
	val roles: List<RolesNewUser?>? = null,

	@field:SerializedName("MotherLastName")
	val motherLastName: String? = null,

	@field:SerializedName("Password")
	val password: String? = null,

	@field:SerializedName("Name")
	val name: String? = null
)
