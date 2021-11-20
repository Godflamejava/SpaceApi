package com.example.spaceapi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile


class Start: AppCompatActivity() {
    private lateinit var account: Auth0
    lateinit var code:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        code  =intent.getStringExtra("code").toString()


        account = Auth0(
                "FL1RahJn508MJwChSQITSCCRorQbk0K6",
                "dev-zvjmyfrq.us.auth0.com"
        )
        if(code == "exit")
            logout()


        var enter=findViewById<Button>(R.id.enter)
        enter.setOnClickListener {
           loginWithBrowser()
        }



    }
    private fun loginWithBrowser() {
        // Setup the WebAuthProvider, using the custom scheme and scope.

        WebAuthProvider.login(account)
                .withScheme("demo")
                .withScope("openid profile email")
                // Launch the authentication passing the callback where the results will be received
                .start(this, object : Callback<Credentials, AuthenticationException> {
                    // Called when there is an authentication failure
                    override fun onFailure(exception: AuthenticationException) {
                        // Something went wrong!
                    }

                    // Called when authentication completed successfully
                    override fun onSuccess(credentials: Credentials) {

                        val accessToken = credentials.accessToken
                        showUserProfile(accessToken)
                    }
                })
    }
    private fun showUserProfile(accessToken: String) {
        var client = AuthenticationAPIClient(account)

        // With the access token, call `userInfo` and get the profile from Auth0.
        client.userInfo(accessToken)
                .start(object : Callback<UserProfile, AuthenticationException> {
                    override fun onFailure(exception: AuthenticationException) {
                        // Something went wrong!
                    }

                    override fun onSuccess(profile: UserProfile) {
                        // We have the user's profile!
                        val email = profile.email
                        val name = profile.name
                        Toast.makeText(this@Start,"Welcome Back",Toast.LENGTH_LONG).show();

                        val intent = Intent(this@Start,MainActivity::class.java)
                        intent.putExtra("name",name)
                        startActivity(intent)
                    }
                })
    }
    private fun logout() {
        WebAuthProvider.logout(account)
                .withScheme("demo")
                .start(this, object: Callback<Void?, AuthenticationException> {
                    override fun onSuccess(payload: Void?) {
                        Toast.makeText(this@Start,"Waiting for You Bye ",Toast.LENGTH_LONG).show();
                    }

                    override fun onFailure(error: AuthenticationException) {
                        // Something went wrong!
                    }
                })
    }

}