package com.rival.costumbuttonnavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rival.costumbuttonnavigation.fragment.ProfileFragment
import com.rival.costumbuttonnavigation.helper.SharedPreference
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    private lateinit var sPH: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        sPH = SharedPreference(this)

        btn_login.setOnClickListener {
            sPH.setStatusLogin(true)
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

            Toast.makeText(this, "Anda Berhasil Login", Toast.LENGTH_SHORT).show()
      finish()
        }
    }
}