class Grid(r: Int, c: Int) {
    // Initialize rows x cols
    val rows = r * 3
    val cols = c * 3

    // Initialize walls to print the entire maze
    val grid = Array(rows) { Array(cols) { false } }

    // Prints grid
    fun printGrid() {
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (!grid[i][j]) {
                    print("██ ")
                }
                else {
                    print("   ")
                }
            }
            println()
        }
        println()
    }
}