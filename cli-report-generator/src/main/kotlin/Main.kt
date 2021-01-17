import TransactionsUtil.parseMonthIntToString

// Entry point of the program
fun main() {
    val transactions = TransactionsUtil.retrieveTransactionsFromLocalFile()
    // Group transactions by month
    val hashMap = mutableMapOf<Int, MutableSet<Transaction>>()
    (1..12).forEach { hashMap[it] = mutableSetOf() }
    for (x in transactions) {
        val monthInt = x.creationDate.substring(0, 2).toInt()
        hashMap[monthInt]!!.add(x)
    }

    hashMap.forEach { (month, transactions) ->
        // Print out each month
        println("${parseMonthIntToString(month)}:")

        var pendingTransactionsCounter = 0
        var rejectedTransactionsCounter = 0
        var totalIncome = 0.0
        var totalExpenses = 0.0
        // Group expenses by categories
        val outTransactions = mutableMapOf<String, Int>()
        var outTransactionsCounter = 0
        TransactionsUtil.CATEGORIES.forEach { outTransactions[it] = 0 }

        transactions.forEach {
            if (it.status == "rejected")
                rejectedTransactionsCounter++
            if (it.status == "pending")
                pendingTransactionsCounter++

            if (it.operation == "in" && it.status == "done")
                totalIncome += it.amount
            if (it.operation == "out" && it.status == "done")
                totalExpenses += it.amount

            if (it.operation == "out") {
                outTransactions[it.category] = outTransactions[it.category]!! + 1
                outTransactionsCounter++
            }
        }

        println("\t$pendingTransactionsCounter\tTransacciones pendiente")
        println("\t$rejectedTransactionsCounter\tTransacciones bloqueadas\n")

        println("\t\$${"%.2f".format(totalIncome)}\tIngresos")
        println("\t\$${"%.2f".format(totalExpenses)}\tGastos\n")
        // Sorting the expenses categories and print them all
        outTransactions.entries.sortedByDescending { it.value }.forEach {
            val percentage = (it.value.toDouble() / outTransactionsCounter) * 100
            println("\t\t${it.key}\t%${"%.2f".format(percentage)}")
        }
        print("\n\n")
    }
}