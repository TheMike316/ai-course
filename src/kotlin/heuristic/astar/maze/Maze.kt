package heuristic.astar.maze

import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JComponent

class Maze : JComponent() {

    // 1: wall
    // 0: gap
    // -1: starting point
    // 9: target
    private val maze: Array<IntArray> = arrayOf(
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, -1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1),
        intArrayOf(1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1),
        intArrayOf(1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1),
        intArrayOf(1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1),
        intArrayOf(1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1),
        intArrayOf(1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1),
        intArrayOf(1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1),
        intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    )

    private val rowNo: Int = maze.size
    private val colNo: Int = maze[0].size

    private val blockSize: Int = 40

    private val nodeMatrix: Array<Array<Node>>

    private val graph: Graph

    private val startingPoint: Int = getIndexOfElement(-1)

    private val targetPoint: Int = getIndexOfElement(9)

    init {
        nodeMatrix = instantiateNodeMatrix()

        graph = createGraph()
    }


    override fun paintComponent(g: Graphics?) {
        val graphics2D = g as? Graphics2D ?: throw IllegalArgumentException()

        paintMaze(graphics2D)
    }

    fun paintPath() {
        paintPath(graphics)
    }

    private fun paintPath(g: Graphics) {
        val graphics2D = g as? Graphics2D ?: throw IllegalArgumentException()

        val nodes = AStar.aStar(graph, startingPoint) { it.element == targetPoint }

        //do not recolor start and target point
        for (i in 1 until nodes.size - 1) {
            val idx = nodes[i].element as? Int ?: throw IllegalStateException()

            val x = idx % colNo
            val y = idx / colNo

            graphics2D.color = Color.GREEN
            graphics2D.fillRect(x * blockSize, y * blockSize, blockSize, blockSize)

            Thread.sleep(500)
        }
    }

    private fun paintMaze(graphics2D: Graphics2D) {
        for (i in 0 until rowNo) {
            for (j in 0 until colNo) {
                val mazeBlock = maze[i][j]

                val color = when (mazeBlock) {
                    1 -> Color.GRAY
                    -1 -> Color.RED
                    9 -> Color.BLUE
                    else -> Color.WHITE
                }

                graphics2D.color = color
                graphics2D.fillRect(j * blockSize, i * blockSize, blockSize, blockSize)
            }
        }
    }


    private fun getIndexOfElement(wantedElement: Int): Int {
        var idx = 0
        for (i in 0 until rowNo) {
            for (j in 0 until colNo) {
                if (maze[i][j] == wantedElement)
                    return idx
                idx++
            }
        }

        return 0
    }

    private fun createGraph(): Graph {
        val nodes = mutableListOf<Node>()

        nodeMatrix.forEach { arrayOfNodes ->
            arrayOfNodes.forEach {
                nodes.add(it)
            }
        }

        return Graph(nodes)
    }

    private fun instantiateNodeMatrix(): Array<Array<Node>> {
        val nodeMatrix = mutableListOf<MutableList<Node>>().apply {
            for (i in 0 until rowNo) {
                add(i, mutableListOf())
            }
        }

        var idx = 0
        for (i in 0 until rowNo) {
            for (j in 0 until colNo) {
                val mazeBlock = maze[i][j]

                val hValue = calculateHValue(j, i)

                val node = if (mazeBlock == 1) Node(idx, hValue, true) else Node(idx, hValue)

                nodeMatrix[i].getOrNull(j - 1)?.let { previousNode ->
                    node.addNeighbor(previousNode)
                    previousNode.addNeighbor(node)
                }

                nodeMatrix.getOrNull(i - 1)?.get(j)?.let { aboveNode ->
                    node.addNeighbor(aboveNode)
                    aboveNode.addNeighbor(node)
                }

                nodeMatrix[i].add(j, node)
                idx++
            }
        }

        return nodeMatrix.map { it.toTypedArray() }.toTypedArray()
    }


    private fun calculateHValue(x: Int, y: Int): Double {
        val xTarget = (targetPoint % colNo).toDouble()
        val yTarget = (targetPoint / colNo).toDouble()

        val dx = x - xTarget
        val dy = y - yTarget

        return Math.sqrt(dx * dx + dy * dy)
    }


}
