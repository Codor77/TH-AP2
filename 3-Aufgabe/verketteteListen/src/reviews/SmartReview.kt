package reviews

class SmartReview (private val pStars : Int) : Review {
    override fun stars(): Int {
        return pStars
    }

    override fun info(): String {
        when (pStars){
            0 -> return "Bad products.Product."
            1 -> return "Moderate products.Product."
            2 -> return "Average products.Product."
            3 -> return "Useful products.Product."
            4 -> return "Good products.Product."
            5 -> return "Excellent products.Product."
            else -> {return "Not sensibly evaluated."}
        }
    }
}