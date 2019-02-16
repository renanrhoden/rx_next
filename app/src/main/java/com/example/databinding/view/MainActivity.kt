package com.example.databinding.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databinding.R
import com.example.databinding.databinding.ActivityMainBinding
import com.example.databinding.model.Game
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.game = Game("Dog", "https://t1.ea.ltmcdn.com/pt/images/0/0/1/cao_agressivo_causas_e_tratamento_6100_600.jpg", 2019, 2.0)


        fabAdd.setOnClickListener {
            val intent = Intent(this, GameAddActivity::class.java)
            startActivity(intent)
        }

    }
}
