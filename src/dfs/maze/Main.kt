package dfs.maze

import javax.swing.JFrame

fun main(args: Array<String>) {
    val maze = Maze()

    JFrame("blablabla").apply {
        setSize(600, 500)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        add(maze)
        isVisible = true
    }

    val dfs = DFS()

    val visitedNodes = dfs.dfs(maze.getGraph(), maze.getStartingPoint()) { it.element == maze.getTargetPoint() }

    visitedNodes.forEach(::println)
}