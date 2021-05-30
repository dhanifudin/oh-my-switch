package com.dhanifudin.ohmyswitch.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Switch(
    override var title: String = "",
    var subtitle: String = "",
    var thumbnailImage: String = "",
    var detailImage: String = "",
    var characteristic: String = "",
    var actuationForce: Int = 0,
    var switchingPoint: Float = 0f,
    var totalTravel: Float = 0f,
    var location: Int = LEFT,
    var description: String = "",
    var animationImage: String = "",
) : Item(), Parcelable {

    companion object {
        const val LEFT = 0
        const val RIGHT = 1
    }
}
