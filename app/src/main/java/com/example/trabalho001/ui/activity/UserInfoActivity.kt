package com.example.trabalho001.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.trabalho001.R
import com.example.trabalho001.TypeList
import com.example.trabalho001.endpoint.RetrofitBuilder
import com.example.trabalho001.model.StarredRepository
import com.example.trabalho001.model.User
import com.example.trabalho001.ui.main.ListFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoActivity : AppCompatActivity(), Callback<List<StarredRepository>> {

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_info_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        progressBar = findViewById(R.id.progressBar)

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


        val service = RetrofitBuilder.getServiceGithubInstance()
        val call = service.getStarredRepositories(user!!.userLogin)

        call.clone().enqueue(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onResponse(
        call: Call<List<StarredRepository>>,
        response: Response<List<StarredRepository>>,
    ) {
        progressBar.visibility = View.GONE
       val repositories = response.body()!!

        supportFragmentManager.beginTransaction()
            .replace(R.id.repositoriesListContainer, ListFragment.newInstance(repositories as MutableList<StarredRepository>,
                TypeList.REPOSITORIES)).commit()
    }

    override fun onFailure(call: Call<List<StarredRepository>>, t: Throwable) {
        progressBar.visibility = View.GONE
        findViewById<TextView>(R.id.errorStarredRepositoriesTextView).visibility = View.VISIBLE
    }
}