package com.example.docsolutions.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.example.docsolutions.R
import com.example.docsolutions.model.BodyNewUser
import com.example.docsolutions.model.NewUserPost
import com.example.docsolutions.model.NewUserResponse
import com.example.docsolutions.model.RolesNewUser
import com.example.docsolutions.network.ApiConfig
import com.example.docsolutions.network.JwtManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewUser: AppCompatActivity() {
    private lateinit var txtNombre: EditText
    private lateinit var txtApellidoP: EditText
    private lateinit var txtApellidoM: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtTelefono: EditText
    private lateinit var txtUsuario: EditText
    private lateinit var txtPassword1: EditText
    private lateinit var txtPassword2: EditText

    private lateinit var btnGuardar: Button
    private lateinit var jwtManager: JwtManager
    private var jwtToken = ""

    private fun getJwtToken(){
        this.jwtManager.jwtTokenFlow.asLiveData().observe(this) {
            jwtToken = it.toString()
        }
    }

    private fun backToGetUsers(){
        // Start getUser activity
        val i = Intent(this, Users::class.java)
        startActivity(i)
        finish()
    }

    private fun apiNewUser(user: NewUserPost){
        val client = ApiConfig.getApiService().postNewUser(user, jwtToken)
        client.enqueue(object : Callback<NewUserResponse> {
            override fun onResponse(
                call: Call<NewUserResponse>,
                response: Response<NewUserResponse>
            ) {
                if (response.isSuccessful) {
                    val newUserResponse = response.body()

                    // Server error
                    if (newUserResponse == null) {
                        Toast.makeText(
                            applicationContext,
                            "Se produjo un error en el servidor",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }

                    // New user ok
                    if (newUserResponse.isOK == true) {
                        Toast.makeText(
                            applicationContext,
                            "Usuario agregado correctamente!",
                            Toast.LENGTH_LONG
                        ).show()
                        backToGetUsers()
                    }
                    // New user NO ok
                    else {
                        Toast.makeText(
                            applicationContext,
                            "Error: ${newUserResponse.messages}",
                            Toast.LENGTH_LONG
                        ).show()
                        return
                    }
                }
                // Response not successful
                else {
                    Toast.makeText(
                        applicationContext,
                        "Se produjo un error en el servidor",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
            }

            override fun onFailure(call: Call<NewUserResponse>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "On failure: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun textFieldValidation(): Boolean{
        // Text field validation
        if (txtNombre.text.isNullOrEmpty() or txtNombre.text.isNullOrBlank()) {
            txtNombre.error = "Nombre can't be null"
            return false
        }
        if (txtApellidoP.text.isNullOrEmpty() or txtApellidoP.text.isNullOrBlank()) {
            txtApellidoP.error = "Apellido P can't be null"
            return false
        }
        if (txtApellidoM.text.isNullOrEmpty() or txtApellidoM.text.isNullOrBlank()) {
            txtApellidoM.error = "Apellido M can't be null"
            return false
        }
        if (txtEmail.text.isNullOrEmpty() or txtEmail.text.isNullOrBlank()) {
            txtEmail.error = "Email can't be null"
            return false
        }
        if (txtTelefono.text.isNullOrEmpty() or txtTelefono.text.isNullOrBlank()) {
            txtTelefono.error = "Telefono can't be null"
            return false
        }
        if (txtUsuario.text.isNullOrEmpty() or txtUsuario.text.isNullOrBlank()) {
            txtUsuario.error = "Usuario can't be null"
            return false
        }
        if (txtPassword1.text.isNullOrEmpty() or txtPassword1.text.isNullOrBlank()) {
            txtPassword1.error = "Password 1 can't be null"
            return false
        }
        if (txtPassword2.text.isNullOrEmpty() or txtPassword2.text.isNullOrBlank()) {
            txtPassword2.error = "Password 2 can't be null"
            return false
        }
        else return true
    }

    private fun addNewUser(){
        if (textFieldValidation()){
            // Get all strings
            val nombre = txtNombre.text.toString()
            val apellidoP = txtApellidoP.text.toString()
            val apellidoM = txtApellidoM.text.toString()
            val email = txtEmail.text.toString()
            val telefono = txtTelefono.text.toString()
            val usuario = txtUsuario.text.toString()
            val password1 = txtPassword1.text.toString()
            val password2 = txtPassword2.text.toString()

            // Verify if passwords match
            if (!password1.equals(password2)) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return
            }
            else {
                // New object user with default roles
                val role = RolesNewUser()
                val roles = listOf(role)

                val user = NewUserPost(
                    BodyNewUser(
                        name = nombre,
                        fatherLastName = apellidoP,
                        motherLastName = apellidoM,
                        email = email,
                        phoneNumber = telefono,
                        userName = usuario,
                        password = password1,
                        roles = roles
                    )
                )

                // Api request
                apiNewUser(user)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_user)

        txtNombre = findViewById(R.id.et_nombre)
        txtApellidoP = findViewById(R.id.et_apellidoP)
        txtApellidoM = findViewById(R.id.et_apellidoM)
        txtEmail = findViewById(R.id.et_email)
        txtTelefono = findViewById(R.id.et_telefono)
        txtUsuario = findViewById(R.id.et_usuario)
        txtPassword1 = findViewById(R.id.et_password1)
        txtPassword2 = findViewById(R.id.et_password2)

        btnGuardar = findViewById(R.id.btn_guardar)
        jwtManager = JwtManager(this)

        // Get jwt token
        getJwtToken()

        btnGuardar.setOnClickListener {
            // Api call to add new user
            addNewUser()
        }
    }
}