fun main() {
    // Create a grid
    val grid = Grid(4, 4)
    grid.printGrid()

    // Create DFS Algorithm
    val dfsObject = DFS(1, 1, grid)

    // Run Recursive DFS Algorithm
//    dfsObject.dfsRecursive()

    // Run Iterative DFS Algorithm
    dfsObject.dfsIterative()
}