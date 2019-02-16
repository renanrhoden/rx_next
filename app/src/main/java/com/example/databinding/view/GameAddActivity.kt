package com.example.databinding.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.databinding.R
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Flowables
import kotlinx.android.synthetic.main.activity_game_add.*
import java.util.concurrent.TimeUnit

class GameAddActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_add)

        val nameChangeObservable =
                RxTextView
                        .textChanges(etName)
                        .skipInitialValue()
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable(BackpressureStrategy.LATEST)
        val yearChangeObservable =
                RxTextView
                        .textChanges(etYear)
                        .skipInitialValue()
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable(BackpressureStrategy.LATEST)

        Flowables.combineLatest(nameChangeObservable, yearChangeObservable) { newName: CharSequence,
                                                                              newYear: CharSequence ->
            val nameValid = newName.length > 4
            if (!nameValid) etName.error = "Invalid name"

            val yearValid = newYear.length == 4
            if (!yearValid) etYear.error = "Invalid year"

            nameValid && yearValid
        }.subscribe {
            btAdd.setBackgroundColor(
                    ContextCompat.getColor(this,
                            if (it) R.color.colorPrimaryDark else android.R.color.darker_gray)
            )
        }
    }
}
