fun main() {
    //Define our arrays of items and quantities
    val tacoSupplies = arrayOf("Tortillas","Lettuce Heads", "Tomatoes", "Cheese (lb)", "Onions", "Cilantro (bunch)","Limes")
    val quantities = arrayOf("50","5","20","4","15","6","30")
    //Create the array of arrays to hold them
    var mega = arrayOf<Array<String>>()

    // Let's tack the arrays onto the big boy
    mega += tacoSupplies
    mega += quantities

    // normally on this king of loop I would have a way to track both dimensions, but in this case
    // they are quite finite, so we'll grab the item/row count and assume 2 cols
    val rowCount = mega[0].size -1

    //loop the item count, and grab from the positions we know for item and quantity
    //This will be the one for listing them all out
    fun listall() {
        for (i in 0..rowCount) {
            val supply = mega[0][i]
            val quantity = mega[1][i]

            println((i + 1).toString() + ". Your supply of $supply is currently $quantity")
        }
    }

    //And this will be the function for updates
    fun makeupdate(whichItem: Int){
        //get the current quantity
        val currentquantity = mega[1][whichItem]
        val currentsupply = mega[0][whichItem]
        //var quantityOut = ""
        // get a tracking var for choice
        var choicetype = -1
        //Ask for the add or subtract
        while(choicetype !in 1..2) {
            println("Would you like to add(1) or remove(2) items?")
            choicetype = readLine()!!.toInt()

            if (choicetype !in 0..tacoSupplies.size - 1)
                println("Invalid Entry, try again")
        }
        val choicetypetext = if(choicetype == 1){ "add"} else {"remove"}
        println("How many items would you like to $choicetypetext?")
        val choiceamt = readLine()!!.toInt()

        // Now we can act on it all
        if (choicetypetext == "remove") {
            if (choiceamt > currentquantity.toInt()) {
                mega[1][whichItem] = "0"
                println("You have requested to remove $choiceamt, but there were only $currentquantity. $currentsupply set to zero.")
            } else {
                val quantityOut = (currentquantity.toInt() - choiceamt).toString()
                mega[1][whichItem] = quantityOut
                println("You have requested to remove $choiceamt from the current $currentquantity. $currentsupply set to $quantityOut.")
            }
        } else {
            val quantityOut = (currentquantity.toInt() + choiceamt).toString()
            mega[1][whichItem] = quantityOut
            println("You have requested to add $choiceamt from the current $currentquantity. $currentsupply set to $quantityOut.")
        }
    }

    // Then we get to offering up the option to change an item
    var choice = -1

    while(choice !in 0..tacoSupplies.size - 1){
        println("Enter a number to choose an item.")
        listall()
        print("")
        choice = readLine()!!.toInt() - 1

        if (choice !in 0..tacoSupplies.size - 1)
            println("Invalid Entry, try again")
    }

    //Maybe we don't need the when
    makeupdate(choice)

}
