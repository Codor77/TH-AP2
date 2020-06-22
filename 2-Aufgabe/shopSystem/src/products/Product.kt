package products

import reviews.Review

open class Product (val productName : String, val basePrice : Double, val description : String, open val salesPrice : Double) {

    val reviews = mutableListOf<Review>()
    val stockUnits : MutableList<StockUnit> = mutableListOf<StockUnit>()

    val availableItems : Int
        get() = stockUnits.sumBy { it.quantity }

    val profitPerItem : Double
        get() = salesPrice - basePrice

    val valueOfAllItems : Double
        get() = basePrice * availableItems

    val salesValueOfAllItems : Double
        get() = salesPrice * availableItems

    override fun toString (): String {
        return ("$productName "+"%.2f".format(salesPrice)+" Euro. $description")
    }

    fun addStock ( items : StockUnit) {
        stockUnits.add(items)
    }

    fun addReview ( review : Review) {
        reviews.add(review)
    }

    fun cleanStock() {
        stockUnits.removeIf { it.isExpired || it.quantity <= 0 }
    }

    fun isPreferredQuantityAvailable (preferedQuantity : Int): Boolean {
        return ( availableItems >= preferedQuantity )
    }

    fun takeItems (preferedQuantity: Int): Int {
        var itemsTaken = 0
        this.stockUnits.sortBy { it.daysBeforeExpiration }
        while (itemsTaken < preferedQuantity) {
            this.cleanStock()
            if (availableItems <= 0) {
                break
            } else if (stockUnits.first().quantity >= preferedQuantity - itemsTaken) {
                stockUnits.first().quantity -= preferedQuantity - itemsTaken
                itemsTaken = preferedQuantity
            } else {
                itemsTaken += stockUnits.first().quantity
                stockUnits.first().quantity = 0
            }
        }
        return itemsTaken
    }
}