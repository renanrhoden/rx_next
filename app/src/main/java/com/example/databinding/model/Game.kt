package com.example.databinding.model

data class Game(val name: String,
                val imageUrl:String,
                val launchYear: Int,
                var rating: Double){

    val isClassic = launchYear < 2000
}