package com.heitorcolangelo.redditnews

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.heitorcolangelo.repository.RepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RepositoryProvider.get().getNews().observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
}
