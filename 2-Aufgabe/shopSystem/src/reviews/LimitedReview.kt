package reviews

import kotlin.math.roundToInt

class LimitedReview (private var points : Double, private val pInfo : String) : Review {
    override fun stars(): Int {
        if (points in 0.0..5.0) {points = points.roundToInt().toDouble() ; return points.roundToInt()}
        else if (points > 5) {points = 5.0; return 5}
        else {points = 0.0 ; return 0}
    }

    override fun info(): String {
        return pInfo
    }
}