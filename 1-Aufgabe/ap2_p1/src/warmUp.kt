fun main () {
//    var strecke = Laufstrecke("test", 3000, arrayListOf("I", "II", "III", "IV"))
//    println(strecke.toString())

    val streckeEins = Laufstrecke("Eins", 1000, arrayListOf("I", "II", "III", "IV"))
    val streckeZwei = Laufstrecke("Zwei", 2000, arrayListOf("II", "III", "IV"))
    val streckeDrei = Laufstrecke("Drei", 3000, arrayListOf("III", "IV"))
    val streckeVier = Laufstrecke("Vier", 4000, arrayListOf("IV", "V"))
    val streckeFünf = Laufstrecke("Fünf", 5000, arrayListOf("V"))

    val Laufstrecken = arrayOf(
        streckeEins,
        streckeZwei,
        streckeDrei,
        streckeVier,
        streckeFünf
    )

    //ausgabeAlle(Laufstrecken)
    //ausgabeMinMax(Laufstrecken,2000, 4000)
    //ausgabeBisZielMeterErreicht(Laufstrecken, 3100)
    //ausgabeBisStrecke(Laufstrecken, streckeDrei)
    //ausgabeKuerzesteStrecke(Laufstrecken)
    //ausgabeLaengsteStrecke(Laufstrecken)


}

fun ausgabeAlle(strecken : Array<Laufstrecke>){
    strecken.forEach {
        println(it.toString())
    }
}

fun ausgabeMinMax(strecken : Array<Laufstrecke>, min : Int, max : Int){
    strecken.forEach {
        if (it.laenge in min..max) {
            println(it.toString())
        }
    }
}

fun ausgabeBisZielMeterErreicht(strecken : Array<Laufstrecke>, zielMeter : Int){
    var gelaufeneMeter = 0
    while (gelaufeneMeter < zielMeter){
        strecken.forEach {
            if (gelaufeneMeter < zielMeter) gelaufeneMeter += it.laenge
        }
    }
    println("Gelaufene Meter: $gelaufeneMeter")
    println("zielMeter: $zielMeter")
}

fun ausgabeBisStrecke (strecken: Array<Laufstrecke>, letzteStrecke : Laufstrecke){
    var fertig = 0
    strecken.forEach {
        if (fertig == 0)println(it.toString())
        if (it == letzteStrecke) fertig = 1
    }
}

fun ausgabeKuerzesteStrecke (strecken: Array<Laufstrecke>){
    strecken.sortBy { it.laenge }
    println(strecken[0].toString())
}

fun ausgabeLaengsteStrecke (strecken: Array<Laufstrecke>){
    strecken.sortByDescending { it.laenge }
    println(strecken[0].toString())
}

