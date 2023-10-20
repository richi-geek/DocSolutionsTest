package com.example.docsolutions.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.lifecycle.asLiveData
import com.example.docsolutions.R
import com.example.docsolutions.model.BodyItem
import com.example.docsolutions.model.GetUsersBody
import com.example.docsolutions.model.PostGetUsers
import com.example.docsolutions.model.UsersResponse
import com.example.docsolutions.network.ApiConfig
import com.example.docsolutions.network.JwtManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Users: AppCompatActivity() {
    private lateinit var txtSearch: TextView
    private lateinit var btnSearch: Button
    private lateinit var btnAdd: Button
    private lateinit var tableUsers: TableLayout

    private lateinit var jwtManager: JwtManager
    private var jwtToken = ""
    private var searchText = ""

    private fun getJwtToken(){
        this.jwtManager.jwtTokenFlow.asLiveData().observe(this) {
            jwtToken = it.toString()
        }
    }

    private fun populateTable(usersList: List<BodyItem?>?){
        if (usersList != null) {
            val row0 = TableRow(this)

            // Table headers
            val h1 = TextView(this)
            val h2 = TextView(this)
            val h3 = TextView(this)
            val h4 = TextView(this)
            val h5 = TextView(this)
            val h6 = TextView(this)

            h1.text = "Username"
            h2.text = "Name"
            h3.text = "FatherLastName"
            h4.text = "CreationDate"
            h5.text = "Email"
            h6.text = "PhoneNumber"

            row0.addView(h1)
            row0.addView(h2)
            row0.addView(h3)
            row0.addView(h4)
            row0.addView(h5)
            row0.addView(h6)

            tableUsers.addView(row0)

            // Iterate over all users
            for (user in usersList){
                val row = TableRow(this)

                // Info of user
                val username = TextView(this)
                val name = TextView(this)
                val fatherLastName = TextView(this)
                val creationDate = TextView(this)
                val email = TextView(this)
                val phoneNumber = TextView(this)

                if (user != null) {
                    username.text = user.username
                    name.text = user.name
                    fatherLastName.text = user.fatherLastName
                    creationDate.text = user.creationDate
                    email.text = user.email.toString()
                    phoneNumber.text = user.phoneNumber.toString()
                }

                row.addView(username)
                row.addView(name)
                row.addView(fatherLastName)
                row.addView(creationDate)
                row.addView(email)
                row.addView(phoneNumber)

                tableUsers.addView(row)
            }
        }
        else{
            Toast.makeText(
                applicationContext,
                "No hay usuarios en la respuesta",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getUsers() {
        // Clear Table View
        tableUsers.removeAllViews()

        // API request
        val getUsersBody = GetUsersBody(searchText)
        val postGetUsers = PostGetUsers(getUsersBody)
        val client = ApiConfig.getApiService().getUsers(postGetUsers, jwtToken)

        client.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                if (response.isSuccessful) {
                    val getUsersResponse = response.body()

                    // Server error
                    if (getUsersResponse == null) {
                        Toast.makeText(
                            applicationContext,
                            "Se produjo un error en el servidor",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }

                    // getUsers ok
                    if (getUsersResponse.isOK == true) {
                        populateTable(getUsersResponse.body)
                    }

                    // getUsers NO ok
                    else {
                        Toast.makeText(
                            applicationContext,
                            "Error: ${getUsersResponse.messages}",
                            Toast.LENGTH_SHORT
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

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "On failure: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun newUser() {
        // Start newUser activity
        val i = Intent(this, NewUser::class.java)
        startActivity(i)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.users)

        txtSearch = findViewById(R.id.et_search)
        btnSearch = findViewById(R.id.btn_search)
        btnAdd = findViewById(R.id.btn_add)
        tableUsers = findViewById(R.id.table_users)
        jwtManager = JwtManager(this)

        // Get jwt token
        getJwtToken()

        btnSearch.setOnClickListener{
            // Api call to get users with search
            searchText = txtSearch.text.toString()
            getUsers()
        }

        btnAdd.setOnClickListener{
            newUser()
        }
    }

}