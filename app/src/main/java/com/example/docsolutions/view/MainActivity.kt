package com.example.docsolutions.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.docsolutions.R
import com.example.docsolutions.model.LoginBody
import com.example.docsolutions.model.LoginResponse
import com.example.docsolutions.model.PostLogin
import com.example.docsolutions.network.ApiConfig
import com.example.docsolutions.network.JwtManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var txtUsername: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnLogin: Button

    lateinit var jwtManager: JwtManager

    private fun createSession(jwt: String){
        // Save jwt token
        // Since the storeSession function of JwtManager
        // class is a suspend function
        // So this has to be done in a coroutine scope
        GlobalScope.launch {
            jwtManager.storeSession(jwt)
        }

        // Start getUsers activity
        val i = Intent(this, Users::class.java)
        startActivity(i)
        finish()
    }

    private fun login() {
        btnLogin.setOnClickListener {
            // Text field validation
            if (txtUsername.text.isNullOrEmpty() or txtUsername.text.isNullOrBlank()) {
                txtUsername.error = "Username can't be null"
            }
            if (txtPassword.text.isNullOrEmpty() or txtPassword.text.isNullOrBlank()) {
                txtPassword.error = "Password can't be null"
            } else {
                // Get username & password from screen
                val username = txtUsername.text.toString()
                val password = txtPassword.text.toString()

                // Body construction for API request
                val loginBody = LoginBody(Username = username, Password = password)
                val postLogin = PostLogin(loginBody)

                // API request
                val client = ApiConfig.getApiService().postLogin(postLogin)
                client.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()

                            // Server error
                            if (loginResponse == null) {
                                Toast.makeText(
                                    applicationContext,
                                    "Se produjo un error en el servidor",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return
                            }

                            // Login ok
                            if (loginResponse.isOK == true) {
                                loginResponse.body?.token?.let { token -> createSession(token) }
                            }
                            // Login NO ok
                            else {
                                Toast.makeText(
                                    applicationContext,
                                    "Credenciales incorrectas: ${loginResponse.messages}",
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

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(
                            applicationContext,
                            "On failure: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind UI elements
        txtUsername = findViewById(R.id.et_username)
        txtPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)

        // Init login function
        login()

        // Get reference to our jwtManager class
        jwtManager = JwtManager(this)
    }
}