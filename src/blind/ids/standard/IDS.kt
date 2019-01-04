package blind.ids.standard

import java.util.*

object IDS {

    fun ids(graph: Graph, target: Any) {
        graph.nodes.firstOrNull { searchTarget(it, target) }?.let { println("Target found!") }
    }

    private fun searchTarget(root: Node, target: Any): Boolean {
        var depth = 0

        root.depthLevel = 0

        var targetFound = false
        while (!targetFound) {
            println("Depth level: $depth")
            targetFound = dfsDepthLevel(root, depth, target)
            depth++
        }

        return true
    }

    private fun dfsDepthLevel(root: Node, depth: Int, target: Any): Boolean {
        val stack = Stack<Node>()

        stack.push(root)

        while (!stack.empty()) {
            val currentNode = stack.pop()
            println("Current node: ${currentNode.element}")

            if (currentNode.element == target)
                return true

            if (currentNode.depthLevel >= depth)
                continue

            currentNode.neighbors.forEach {
                it.depthLevel = currentNode.depthLevel + 1
                stack.push(it)
            }

        }
        return false
    }
}