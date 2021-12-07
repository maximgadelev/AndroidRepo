package com.example.myapplication.repo

import com.example.myapplication.R
import com.example.myapplication.model.Track

object TrackListRepo{
var idCounter=0;
val trackList: ArrayList<Track> = arrayListOf(
    Track(idCounter++,"Первая Песенка","Барбарики", R.drawable.fisrt, R.raw.first_song),
    Track(idCounter++,"Вторая Песенка","Барбарики Крутые", R.drawable.second, R.raw.second_song),
    Track(idCounter++,"Третья Песенка","Барбарики!!!", R.drawable.third, R.raw.thrid_song),
    Track(idCounter++,"Четвертая Песенка","Барбарики Хы", R.drawable.fourth, R.raw.fourth_song),
    Track(idCounter++,"Пятая Песенка","Воровайки", R.drawable.fifth, R.raw.fifth_song),
    Track(idCounter++,"Шестая Песенка","Круг", R.drawable.sixth, R.raw.sixth_song),
)
}
