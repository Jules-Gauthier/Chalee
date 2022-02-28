package fr.isen.chaleeapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.chaleeapp.User.FilActuActivity
import fr.isen.chaleeapp.databinding.ActivityLoginBinding
import fr.isen.chaleeapp.databinding.ActivityMainBinding
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    var interactor: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root) //permet de créer une instance pour détecter toutes les interations que nous allons avoir avec le layout

        binding.buttonLogin.setOnClickListener {
            val intent = Intent (this, FilActuActivity::class.java)
            startActivity(intent)
        }
    }

    fun makeRequest(
        email: String?,
        password: String?,
        firstname: String?,
        lastname: String?,
        isFromLogin: Boolean
    ) {
        if(verifyInformation(email, password, firstname, lastname, isFromLogin)){
            //launch request
            launchRequest(email, password, firstname, lastname, isFromLogin)
        } else {
            //affiche un message champs invalide
            Toast.makeText(this, getString(R.string.invalidForms), Toast.LENGTH_LONG).show()
        }
    }

    private fun launchRequest(
        email: String?,
        password: String?,
        firstname: String?,
        lastname: String?,
        isFromLogin: Boolean
    ){
        val queue = Volley.newRequestQueue(this)
        var requestPath = NetworkConstants.BASE_URL
        if(isFromLogin) {
            requestPath += NetworkConstants.LOGIN
        } else {
            requestPath += NetworkConstants.REGISTER
        }

        val parameters = JSONObject()
        parameters.put(NetworkConstants.KEY_SHOP, NetworkConstants.SHOP)
        parameters.put(NetworkConstants.KEY_EMAIL, email)
        parameters.put(NetworkConstants.KEY_PASSWORD, password)

        if(!isFromLogin) {
            parameters.put(NetworkConstants.KEY_FIRSTNAME, firstname)
            parameters.put(NetworkConstants.KEY_LASTNAME, lastname)
        }

        val request = JsonObjectRequest(
            Request.Method.POST,
            requestPath,
            parameters,
            {
                //Success
                Log.d("request", it.toString(2))
            },
            {
                //Failure
                Log.d("request", it.localizedMessage)
            }
        )
        queue.add(request)
    }


    private fun verifyInformation(
        email: String?,
        password: String?,
        firstname: String?,
        lastname: String?,
        isFromLogin: Boolean
    ): Boolean {
        var verified = (email?.isNotEmpty() == true && password?.count() ?: 0 >= 6)

        if(!isFromLogin) {
            verified = verified && (firstname?.isNotEmpty() == true && lastname?.isNotEmpty() == true)
        }
        return verified
    }

}



