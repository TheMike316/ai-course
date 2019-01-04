package dfs.maze

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

    init {
        nodeMatrix = instantiateNodeMatrix()

        graph = createGraph()
    }


    override fun paintComponent(g: Graphics?) {
        val graphics2D = g as? Graphics2D ?: throw IllegalArgumentException()

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

    fun getGraph() = graph

    fun getStartingPoint() = getIndexOfElement(-1)

    fun getTargetPoint() = getIndexOfElement(9)

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

                val node = if (mazeBlock == 1) Node(idx, true) else Node(idx)

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


}
