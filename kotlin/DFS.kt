import java.util.*

class DFS(private var startX: Int, private var startY: Int, private val gridObject: Grid) {
    // Number of rows
    private val M = gridObject.rows

    // Number of columns
    private val N = gridObject.cols

    // Maze
    private val maze = gridObject.grid

    // 4 Directions: 0-Right, 1-Left, 2-Down, 3-Up
    private val directions: List<List<Int>> = listOf(
        listOf(0, 3),
        listOf(0, -3),
        listOf(3, 0),
        listOf(-3, 0)
    )

    // Recursive DFS Algorithm
    fun dfsRecursive(x: Int = startX, y: Int = startY) {
        // Mark the current cell as visited
        maze[x][y] = true
        // Print Maze to the console
        gridObject.printGrid()
        // Get all available next positions
        val availableShuffledDirections = getShuffledAvailableDirectionsIndices(x, y)
        // For all available neighbors, try to go deeper and deeper
        for (shuffledIndex in availableShuffledDirections) {
            // Get new position
            val nx = x + directions[shuffledIndex][0]
            val ny = y + directions[shuffledIndex][1]
            // If new cell is now visited before
            if (!maze[nx][ny]) {
                // Remove the walls between current and new cell
                wallRemover(shuffledIndex, nx, ny)
                // Mark new cell as visited
                maze[nx][ny] = true
                // Recursively go to the next cell
                dfsRecursive(nx, ny)
            }
        }
    }

    // Iterative DFS Algorithm
    fun dfsIterative() {
        // Create a Stack
        val stack = Stack<List<Int>>()
        // Push start position to the stack
        stack.push(listOf(startX, startY))
        // Mark start cell as visited
        maze[startX][startY] = true
        // Print Maze
        gridObject.printGrid()
        // As long as we have an element in the Stack
        while (!stack.empty()) {
            // Get the current element top of the stack
            val cur = stack.peek()
            // Get a random available direction
            val randomIndex = getRandomDirection(cur[0], cur[1])
            if (randomIndex != -1) {
                // Get new position
                val nx = cur[0] + directions[randomIndex][0]
                val ny = cur[1] + directions[randomIndex][1]
                if (!maze[nx][ny]) {
                    // Remove the walls between current and new cell
                    wallRemover(randomIndex, nx, ny)
                    // Mark new cell as visited
                    maze[nx][ny] = true
                    // Print Maze
                    gridObject.printGrid()
                    // Push next cell to stack
                    stack.push(listOf(nx, ny))
                }
            }
            // Pop current cell for backtracking
            else {
                stack.pop()
            }
        }
    }

    // Removes all the walls between current and newly found position by looking at the direction index
    private fun wallRemover(index: Int, nx: Int, ny: Int) {
        // 0-Right, 1-Left, 2-Down, 3-Up
        when (index) {
            0 -> {
                maze[nx][ny - 2] = true
                maze[nx][ny - 1] = true
            }
            1 ->  {
                maze[nx][ny + 2] = true
                maze[nx][ny + 1] = true
            }
            2 ->  {
                maze[nx - 2][ny] = true
                maze[nx - 1][ny] = true
            }
            3 ->  {
                maze[nx + 2][ny] = true
                maze[nx + 1][ny] = true
            }
        }
    }

    // Creates a list of all reachable neighbors of given x and y, then Shuffles and returns the list
    private fun getShuffledAvailableDirectionsIndices(x: Int, y: Int): List<Int> {
        // Available neighbors
        val availableNeighbors: MutableList<Int> = mutableListOf()
        // Find unvisited neighbors
        for ((index, direction) in directions.withIndex()) {
            val nx = x + direction[0]
            val ny = y + direction[1]
            if (inside(nx, ny) && !maze[nx][ny]) {
                availableNeighbors.add(index)
            }
        }
        return availableNeighbors.shuffled()
    }

    // Returns a random available index from directions list, -1 otherwise
    private fun getRandomDirection(x: Int, y: Int): Int {
        // Get all available neighbors that we can reach from the current x and y position
        val availableNeighbors = getShuffledAvailableDirectionsIndices(x, y)
        // Get random direction
        return if (availableNeighbors.isNotEmpty()) availableNeighbors.random() else -1
    }

    // Checks if x and y position is inside the maze
    private fun inside(x: Int, y: Int): Boolean {
        if (x < 0 || x >= M || y < 0 || y >= N) {
            return false
        }
        return true
    }
}