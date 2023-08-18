package br.senai.sp.jandira.retrofit_reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.create

private lateinit var apiService: ApiService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        //Botão de get
        findViewById<Button>(R.id.btnGET).setOnClickListener() {
            getUserById()
        }
        //Botão de put
        findViewById<Button>(R.id.btnPUT).setOnClickListener() {
            updateUser()
        }
        //Botão delete
        findViewById<Button>(R.id.btnDELETE).setOnClickListener() {
            deleteUser()
        }

        //Botão post
        findViewById<Button>(R.id.btnPOST).setOnClickListener() {
            createUser()
        }
    }

    // Lista usuários
    private fun getUserById() {
        lifecycleScope.launch {
            val result = apiService.getUserByID("2")
            if (result.isSuccessful) {
                Log.i("GETTING-DATA", "${result.body()?.data}")
            } else {
                Log.e("Get error", "${result.message()}")
            }
        }
    }

    // Insere usuário
    private fun createUser() {
        lifecycleScope.launch {
            val body = JsonObject().apply {
                addProperty("name", "Bianca Pereira Leão")
                addProperty("job", "Dev. Senior Full Stack")
            }
            val result = apiService.createUser(body)
            if (result.isSuccessful) {
                Log.i("CREATING-DATA", "${result.body()}")
            } else {
                Log.e("Get error", "${result.message()}")
            }
        }
    }

    // Edita usuário
    private fun updateUser() {
        lifecycleScope.launch {
            val body = JsonObject().apply {
                addProperty("name", "Bianca Pereira Leão")
                addProperty("job", "Dev. Senior Full Stack")
            }
            val result = apiService.updateUserById("2", body)
            if (result.isSuccessful) {
                Log.i("UPDATE-DATA", "${result.body()}")
            } else {
                Log.e("Get error", "${result.message()}")
            }
        }
    }

    //Exclui o usuario
    private fun deleteUser() {
        lifecycleScope.launch {
            val result = apiService.deleteUserById("2")
            if (result.isSuccessful) {
                Log.i("DELETE-DATA", "${result}")
            } else {
                Log.e("Get error", "${result.message()}")
            }
        }
    }


}
