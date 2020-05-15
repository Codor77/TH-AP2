package reviews

class PlainReview (private val pStars : Int) : Review {
    override fun stars(): Int {
        return pStars
    }

    override fun info(): String {
        return "Procuct with $pStars Stars."
    }
}