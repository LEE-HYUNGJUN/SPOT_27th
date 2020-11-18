package com.example.sopt_homework

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileData (
    var title : String,
    var subTitle : String,
    var date_title : String,
    var date_subTitle : String,
    var contents : String
) : Parcelable