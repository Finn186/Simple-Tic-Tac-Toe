package tictactoe

fun printBoard(board: MutableList<Char>) {
    println("---------")
    for (i in 0..8 step 3) {
        println("| ${ board[i] } ${ board[i + 1] } ${ board[i + 2] } |")
    }
    println("---------")
}

fun check(board: MutableList<Char>): Boolean {
    var numberOfO = 0
    var numberOfX = 0
    for (i in board) {
        if (i == 'O') {
            numberOfO++
        } else if (i == 'X') {
            numberOfX++
        }
    }
    val boardMDHorizontal = mutableListOf<MutableList<Char>>()
    val boardMDVertical = mutableListOf<MutableList<Char>>()
    val boardMDDiagonal = mutableListOf<MutableList<Char>>()
    for (i in 0..8 step 3) {
        boardMDHorizontal.add(board.subList(i, i + 3))
    }
    for (i in 0..2) {
        boardMDVertical.add(board.slice(i..8 step 3).toMutableList())
    }
    boardMDDiagonal.add(board.slice(0..8 step 4).toMutableList())
    boardMDDiagonal.add(board.slice(2..6 step 2).toMutableList())
    val allWins = (boardMDHorizontal + boardMDVertical + boardMDDiagonal).toMutableList()
    val circle = MutableList(3) { 'O' }
    val cross = MutableList(3) { 'X' }
    return when {
        circle in allWins -> {
            println("oWins")
            true
        }
        cross in allWins -> {
            println("X wins")
            true
        }
        '_' in board -> false
        else -> {
            println("Draw")
            true
        }
    }
}

fun checkMove(x: Int, y: Int, board: MutableList<Char>): Boolean {
    if (x == -1 && y == -1) {
        println("You should enter numbers!")
    } else if (x > 3 || y > 3) {
        println("Coordinates should be from 1 to 3!")
    } else if (board[3 * x + y - 4] == '_') {
        return true
    }
    println("This cell is occupied! Choose another one!")
    return false
}

fun getMove(board: MutableList<Char>) {
    var coordinates: List<Int>
    do {
        print("Enter the coordinates: ")
        val input = readLine()!!
        if (!input[0].isDigit() || !input[2].isDigit()) {
            coordinates = "-1 -1".split(' ').map { it.toInt() }
        }
        coordinates = input.split(' ').map { it.toInt() }
    } while (!checkMove(coordinates[0], coordinates[1], board))
    makeMove(coordinates[0], coordinates[1], board)
}

fun makeMove(x: Int, y: Int, board: MutableList<Char>) {
    board[3 * x + y - 4] = 'X'
}

fun main() {
    val board = MutableList(9) { '_' }
    printBoard(board)
    while (true) {
        getMove(board)
        printBoard(board)
        if (check(board)) break
    }
}
