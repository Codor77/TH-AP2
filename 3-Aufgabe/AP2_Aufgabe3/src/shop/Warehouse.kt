package shop

import products.Product
import products.StockUnit
import reviews.*

import kotlin.random.Random

class Warehouse {
    val products : MutableList<Product> = mutableListOf<Product>()

    var prodctDescriptions : String = ""
        get() {
            field = ""
            products.forEach {
                field = field.plus(it.description).plus("; ")
            }
            return field
        }

    var listOfProducts : String = ""
        get() {
            field = ""
            products.forEach {
                field = field.plus(it.toString()).plus(";\n")
            }
            return field
        }

    fun hasProduct (productName: String): Boolean {
        return products.any { it.productName == productName }
    }

    fun getProductByName (productName: String) : Product {
        return products.find { it.productName == productName }!!
    }

    fun fillWarehouse (productName: String, basePrice: Double, productDescription: String, chargeOnTop: Double = 50.0, initialStockUnits: Int = 3) {
        var newProduct: Product = Product(productName, basePrice, salesPrice = basePrice + (basePrice * (chargeOnTop / 100)),
            description = productDescription
        )
        for (i in 1..initialStockUnits )
            newProduct.addStock(StockUnit(Random.nextInt(30) + 1, Random.nextInt(30) + 1))
        newProduct.addReview(PlainReview(Random.nextInt(6)))
        newProduct.addReview(LimitedReview(Random.nextDouble(5.1), "No comment available"))
        newProduct.addReview(SmartReview(Random.nextInt(6)))
        products.add(newProduct)
    }
}