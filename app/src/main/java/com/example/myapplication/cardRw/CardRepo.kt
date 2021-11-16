package com.example.myapplication.cardRw

import com.example.myapplication.R


object CardRepo {
    var image_list_1:ArrayList<Int> = arrayListOf(
        R.drawable.photo1,
        R.drawable.photo2,
        R.drawable.photo3)

    var image_list_2:ArrayList<Int> = arrayListOf(
        R.drawable.photo4,
        R.drawable.photo5,
        R.drawable.photo6)

    var cardList:ArrayList<Card> = arrayListOf(
        Card("Post 1","Cat1 cat1 cat1 cat1 cat1 cat1 cat1 ldgorwgoerk", image_list_1),
        Card("Post 2 ","Cat2 fporgporeoigoiergoioiejrioj", image_list_1),
        Card("Photo 3 ","Cat3 lrpefgpregpregkrkgpkregregkeg goireoigireiogionrionrgionrgoi",image_list_2),
        Card("Photo 4 ","Cat 4 gptlrelpgtplplgtplgtplgplgplgtplgt", image_list_2))
}
