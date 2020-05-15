import products.DiscountProduct
import products.DiscountType
import shop.ShoppingCart
import shop.Warehouse
import java.lang.Exception
import java.util.Scanner

val reader = Scanner(System.`in`)

val warehouse: Warehouse = Warehouse()
val shoppingCart: ShoppingCart = ShoppingCart()

fun main () {
    warehouse.fillWarehouse("Milch",0.4,"Is halt ne Milch",140.0)
    warehouse.fillWarehouse("Apfel",0.5,"Is halt n Apfel",100.0)
    warehouse.fillWarehouse("Birne",0.6,"Is halt neBirne",100.0)
    warehouse.fillWarehouse("Keks",0.3,"Is halt n Keks",100.0)
    warehouse.fillWarehouse("Saft",0.6,"Is halt n Saft",100.0)
    warehouse.fillWarehouse("Banane",0.6,"Is halt ne Banane",100.0)
    warehouse.fillWarehouse("Brot",0.7,"Is halt n Brot",100.0)
    warehouse.fillWarehouse("Kuchen",1.5,"Is halt n Kuchen",100.0)
    warehouse.fillWarehouse("Ananas",0.5,"Is halt ne Ananas",100.0)
    warehouse.fillWarehouse("Gurke",0.4,"Is halt ne Gurke",100.0)
    warehouse.products.add(DiscountProduct("discountTest", 50.0, "discont test", 100.0, DiscountType.Sommerschlussverkauf))

    loop@ while (true) {
        info()

        println("A=Add; B=Buy all; I=Info; S=Show list; C=Clear list; E=Exit ")
        when (readLine()){
            "A" -> addItems()
            "B" -> buy()
            "I" -> info()
            "S" -> showCart()
            "C" -> clearCart()
            "E" -> {exitWarehouse(); break@loop}
            else -> {println("Invalid input. Try again:")}
        }
        println("(press Enter to continue)")
        readLine()
        println()
        println()
    }

}

fun addItems () {
    println()
    println("Enter products.Product-Name:")
    val newItemName = readLine()
    var newItemAmount : Int
    while (true) {
        println("Enter amount:")
        try {
            newItemAmount = readLine()!!.toInt()
            break
        } catch (e: Exception) {
            println("Invalid input. Try again.")
        }
    }

    if (newItemName != null) {
        if (warehouse.hasProduct(newItemName)){
            val newProduct = warehouse.getProductByName(newItemName)
            if (newProduct.isPreferredQuantityAvailable(newItemAmount)) {
                shoppingCart.productAndQuantityList.add(Pair(newProduct,newItemAmount))
            }
            else {
                shoppingCart.productAndQuantityList.add(Pair(newProduct,newProduct.availableItems))
                println("Only ${newProduct.availableItems} types of $newItemName available.")
            }
        }
        else println("$newItemName not available.")
        println()
        showCart()
    }
}

fun buy () {
    println()
    println("You bought:\n" +
            "${shoppingCart.listOfAllProducts}" +
            "Total: %.2f".format(shoppingCart.buyEverything())+" Euro.")
}

fun info () {
    println(" *** Available Products ***")
    println(warehouse.listOfProducts)
    println()
}

fun showCart () {
    println("Your Shopping Cart contains:")
    println(shoppingCart.listOfAllProducts)
    println()
    println("Total: %.2f".format(shoppingCart.totalPrice)+" EEuro")
    println()
}

fun clearCart () {
    shoppingCart.clear()
    println("Shopping Cart cleared.")
}

fun exitWarehouse () {
    println("Bye-bye")
}