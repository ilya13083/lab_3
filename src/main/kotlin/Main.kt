fun main() {
    val name = "John"
    val introduction = "$name welcome to our amazing game!"
    println(introduction)

    println("Select the number of rounds in the game")
    val roundStr = readLine()
    val round = roundStr?.toIntOrNull() ?: 0

    val results = playGame(round)

    println("Statistics:")
    results.forEach {
        println("Your number: ${it.number} | Winning number: ${it.numberWin} | Game result: ${if (it.result) "Win" else "Loss"}")
    }

    val wins = results.count { it.result }
    val losses = round - wins
    val winPercentage = (wins.toDouble() / round * 100).toInt()
    val lossPercentage = (losses.toDouble() / round * 100).toInt()

    println("Number of wins: $wins | Win percentage: $winPercentage% | Number of losses: $losses | Loss percentage: $lossPercentage%")
}

data class GameResult(val number: Int, val numberWin: Int, val result: Boolean)

fun playGame(round: Int): List<GameResult> {
    val results = mutableListOf<GameResult>()

    println("To win the game you need to guess a number from 1 to 5")
    println("Ready? (y/n)")
    var readyState = false
    while (!readyState) {
        val input = readLine()?.toLowerCase()
        if (input == "y") {
            readyState = true
        } else {
            println("Ready? (y/n)")
        }
    }

    repeat(3) {
        Thread.sleep(1000)
        println("We start in ${3 - it}")
    }

    repeat(round) {
        println("Enter your guess:")
        val number = readLine()?.toIntOrNull() ?: 0
        val numberWin = (1..5).random()
        val result = number == numberWin
        val gameResult = GameResult(number, numberWin, result)
        results.add(gameResult)

        if (result) {
            println("You win!")
        } else {
            println("You lose.")
        }
    }

    return results
}
