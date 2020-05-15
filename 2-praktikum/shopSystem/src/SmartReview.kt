class SmartReview (private val pStars : Int) : Review {
    override fun stars(): Int {
        return pStars
    }

    override fun info(): String {
        when (pStars){
            0 -> return "Bad Product."
            1 -> return "Moderate Product."
            2 -> return "Average Product."
            3 -> return "Useful Product."
            4 -> return "Good Product."
            5 -> return "Excellent Product."
            else -> {return "Not sensibly evaluated."}
        }
    }
}