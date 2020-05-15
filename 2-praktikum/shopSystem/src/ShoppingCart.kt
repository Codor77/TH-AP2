class ShoppingCart () {
    val productAndQuantityList = mutableListOf<Pair<Product,Int>>()

    val allProductsAvailable : Boolean
        get() {
            return !productAndQuantityList.any { !it.first.isPreferredQuantityAvailable(it.second) }
        }

    val totalPrice : Double
        get() = productAndQuantityList.sumByDouble {
                it.first.salesPrice * it.second
            }

    var listOfAllProducts : String = ""
        get() {
            field = ""
            productAndQuantityList.forEach {
                field = field
                        .plus(it.second)
                        .plus(" x ")
                        .plus(it.first.productName)
                        .plus(" = ")
                        .plus("%.2f".format(it.second * it.first.salesPrice))
                        .plus("\n")
            }
            return field
        }

    fun clear() {
        productAndQuantityList.clear()
    }

    fun buyEverything(): Double {
        var actualPrice : Double = 0.0
        productAndQuantityList.forEach {
            actualPrice += it.first.takeItems(it.second) * it.first.salesPrice
        }
        this.clear()
        return actualPrice
    }

}