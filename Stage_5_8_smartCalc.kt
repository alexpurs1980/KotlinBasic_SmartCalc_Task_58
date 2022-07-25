// Work on project. Stage 5/8: Error! Now you need to consider the reaction of the calculator when users enter
// expressions in the wrong format. The program only knows numbers, a plus sign, a minus sign, and two commands.
// It cannot accept all other characters and it is necessary to warn the user about this.
fun main() {
    while (true) {
        // input expression or command as a srting
        val inputString: String = readln()
        // regex pattern for commands (first \ then letters)
        val regexCorrectCommand = "[\\/][a-zA-Z]+"
        // regex pattern for numbers and operators + and - (and spases!)
        val regexCorrectExpression = "[0-9\\+\\-\\s]+"
        // checks empty string
        if (inputString == "" || inputString == " ") {
            continue
        }
        // checks for commands
        if (regexCorrectCommand.toRegex().matches(inputString)) {
            when {
                //if help command
                inputString.lowercase() == "/help" -> {
                    println("This is calc program." +
                            "\nThe program calculates the sum of numbers.\nIt supports both unary and binary minus operators.")
                    continue
                }
                // if exit command
                inputString.lowercase() == "/exit" -> {
                    println("Bye")
                    break
                }
                // if wrong command
                inputString[0].toString() == "/" -> {
                    println("Unknown command")
                    continue
                }
            }
        // if expression
        } else if (regexCorrectExpression.toRegex().matches(inputString)) {
            // println("$inputString = ${regexCorrectExpression.toRegex().matches(inputString)}")
            val resultExpression = inputString.split(" ")
            // checks for size of expression (just two numbers)
            if (resultExpression.size == 2) {
                println("Invalid expression")
                continue
            }
            // checks for first and last nums are ints
            try {
                resultExpression[0].toInt() + resultExpression.lastIndex.toInt()
                // var for summ
                var sumResult = 0
                // counting expression
                for (index in resultExpression.indices) {
                    // if element is number - summ it
                    try {
                        sumResult += resultExpression[index].toInt()
                        // println(sumResult)
                    } catch (e: Exception) {
                        // if element is sign "-" minus (odd count of minuses) double next element (cos next iteration plus element)
                        if (resultExpression[index][0].toString() == "-" && resultExpression[index].length%2 != 0) {
                            sumResult = sumResult - (2 * (resultExpression[index + 1].toInt()))
                        } else continue
                        continue
                    }

                }
                // print result
                println(sumResult)

            } catch (e: java.lang.NumberFormatException) {
                                println("Invalid expression")
            }

        } else println("Invalid expression") // if wrong expression mismatches with regex pattern

    }
}
