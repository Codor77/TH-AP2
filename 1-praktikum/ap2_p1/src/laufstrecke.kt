class Laufstrecke ( var name : String , val laenge : Int, val poi : ArrayList <String> ) {
    override fun toString(): String {
        var poiString: String? = null
        var counter = 1
        for (point in poi) {
            if (counter == 1){
                counter -= 1
                poiString = point
            } else poiString = poiString.plus(", $point")
        }

        return ("Die Strecke $name ist $laenge Meter lang und Verläuft entlang folgender Sehenswürdigkeiten: $poiString")
    }
}