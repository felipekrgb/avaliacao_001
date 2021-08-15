package com.example.trabalho001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.trabalho001.model.User

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_info_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val user = intent.getSerializableExtra("user_parameter") as? User

        user?.let { myUser ->

            supportActionBar?.title = user.name

            findViewById<ImageView>(R.id.userAvatarInfoImageView).apply {
                Glide.with(context).load(user.avatarURL).placeholder(R.drawable.ic_github)
                    .into(this)
            }

            findViewById<TextView>(R.id.userNameInfoTextView).text = user.name

            findViewById<TextView>(R.id.userDescriptionInfoTextView).text = user.description

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}