package com.example.myapplication

object HeroRepository {
    fun getHeroes()= arrayListOf<Hero>(
        Hero(0,"Asakura Yoh","A strong shaman from the ancient Japanese Asakura family. By nature, a simple and good-natured guy, lazy and dreamy. He loves music, constantly wears headphones that he inherited from his father",R.drawable.photo1),
        Hero(1,"Anna Kyoyama","Shaman Itako, the bride of Yo Asakura. Usually wears a black dress, a red scarf and blue beads. Blonde, short hair. Golden eyes. Yo is the only one she can be herself with.",R.drawable.photo2),
        Hero(2,"Jun Tao","Sister Ren; a Taoist, that is, a shaman controlling zombies. Her zombie spirit is Lee Paylon, a former action star who was killed 17 years earlier as a birthday present for June. According to the manga, he is also a healer",R.drawable.photo3),
        Hero(3,"Oyamada Manta","Quiet and intelligent boy, on whose behalf the story is told. Classmate and best friend of Yo Asakura, thanks to whose influence he was involved in the shaman tournament",R.drawable.photo4)
    )
}
