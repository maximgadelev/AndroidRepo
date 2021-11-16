package com.example.myapplication.songRw

object SongRepo {
    var songList:ArrayList<Song> = arrayListOf(
        Song(1,"Nothing Else Matters","Best group in the world mettallica dad da da"),
        Song(2,"Rap God","Da dad da d ada da Eminem"),
        Song(3,"Californication","dadada red hot chilli pepers"),
        Song(4,"Still loving you","Scorpions gg g g g gg g g g g "),
        Song(5,"Now or never","Three days grace anw btw")
    )
    fun addSong(index:String?,song: Song){
        if(index!=null){
            if(index=="") {
                songList.add(song)
            }else{
                if(index.toInt()<= 0){
                    songList.add(song)
                }else if (index.toInt()<= songList.size){
                    songList.add(index.toInt()-1,song)
                } else{
                    songList.add(song)
                }
            }
        }else{
            songList.add(song)
        }
    }
}
