package shop

import products.Product

class ShoppingCart () {

    fun addProduct(product: Product, p_quantity: Int) {
        var quantity = p_quantity
        if (hasPair(product.productName)){
            quantity += getPairByName(product.productName).second
            productAndQuantityList.removeIf{ it.first.productName == product.productName }
        }
        productAndQuantityList.add(Pair(product,quantity))
    }

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

    fun hasPair (productName: String): Boolean {
        return productAndQuantityList.any { it.first.productName == productName }
    }

    fun getPairByName (productName: String) : Pair<Product, Int> {
        return productAndQuantityList.find { it.first.productName == productName }!!
    }

}