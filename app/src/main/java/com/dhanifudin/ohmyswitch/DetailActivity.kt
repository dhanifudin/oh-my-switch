package com.dhanifudin.ohmyswitch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dhanifudin.ohmyswitch.models.Switch
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val avatarImage: ImageView = findViewById(R.id.avatar_image)
        val titleText: TextView = findViewById(R.id.title_text)
        val subtitleText: TextView = findViewById(R.id.subtitle_text)
        val characteristicText: TextView = findViewById(R.id.characteristic_text)
        val actuationText: TextView = findViewById(R.id.actuation_text)
        val switchingPointText: TextView = findViewById(R.id.switching_point_text)
        val totalTravelText: TextView = findViewById(R.id.total_travel_text)
        val descriptionText: TextView = findViewById(R.id.description_text)
        val animationImage: ImageView = findViewById(R.id.animation_image)

        val switchItem = intent.getParcelableExtra<Switch>(MainActivity.ITEM_KEY)

        supportActionBar?.title = switchItem?.title

        Glide.with(this)
            .load(switchItem?.detailImage)
            .into(avatarImage)
        titleText.text = switchItem?.title
        subtitleText.text = switchItem?.subtitle
        characteristicText.text = "${switchItem?.characteristic} switching characteristics"
        actuationText.text = "${switchItem?.actuationForce} cN operating force"
        switchingPointText.text = "${switchItem?.switchingPoint} mm pre travel"
        totalTravelText.text = "${switchItem?.totalTravel} mm total travel"

        descriptionText.text = switchItem?.description
        Glide.with(this)
            .load(switchItem?.animationImage)
            .into(animationImage)
    }
}