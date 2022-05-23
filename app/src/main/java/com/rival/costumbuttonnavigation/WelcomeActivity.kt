package com.rival.costumbuttonnavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rival.costumbuttonnavigation.api.ApiConfig
import com.rival.costumbuttonnavigation.fragment.ProfileFragment
import com.rival.costumbuttonnavigation.helper.SharedPreference
import com.rival.costumbuttonnavigation.model.ResponseUser
import kotlinx.android.synthetic.main.activity_welcome.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WelcomeActivity : AppCompatActivity() {

    private lateinit var sPH: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        sPH = SharedPreference(this)

        btn_login.setOnClickListener {
            val email = edt_email.text.toString()
            val password = edt_pass.text.toString()

            if(email.isEmpty() || password.isEmpty()) {
                edt_email.error = "Isi dulu !"
                edt_pass.error = "Isi dulu !"
                return@setOnClickListener
            }

            ApiConfig.instanceRetrofit.login(email, password).enqueue(object: Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    val respon = response.body()

                    if (respon != null) {
                        if (respon.status == 0){
                            Toast.makeText(this@WelcomeActivity, respon.message, Toast.LENGTH_SHORT).show()
                        } else {
                            sPH.setStatusLogin(true)
                            val i = Intent(this@WelcomeActivity, MainActivity::class.java)
                            Toast.makeText(
                                this@WelcomeActivity,
                                respon.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(i)
                            finish()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Toast.makeText(this@WelcomeActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            } )




        }
    }
}