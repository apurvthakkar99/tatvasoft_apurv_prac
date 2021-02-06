package com.tatvasoft.apurv

import android.os.Bundle
import android.view.View
import android.widget.Toast
//import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tatvasoft.apurv.data.adapter.ApiUserAdapter
import com.tatvasoft.apurv.data.model.ApiUser
import com.tatvasoft.apurv.data.viewmodel.MainActivityViewModel
import com.tatvasoft.apurv.utils.Status
import com.tatvasoft.apurv.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

     lateinit var viewModel: MainActivityViewModel
     lateinit var adapter: ApiUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        rvUserList.layoutManager = LinearLayoutManager(this)
        adapter =
            ApiUserAdapter(
                arrayListOf()
            )
        rvUserList.addItemDecoration(
            DividerItemDecoration(
                rvUserList.context,
                (rvUserList.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvUserList.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    rvUserList.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    rvUserList.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: ApiUser) {
        adapter.addData(users.data.users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                apiHelper = ApiHelperImpl(RetrofitBuilder.apiService))
        ).get(MainActivityViewModel::class.java)
    }
}