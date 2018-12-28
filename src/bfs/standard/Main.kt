package bfs.standard

fun main(args: Array<String>) {

    val rootNode = Node(1).apply {
        addNeighbor(Node(2).apply {
            addNeighbor(Node(4))
            addNeighbor(Node(5))
        })
        addNeighbor(Node(3).apply {
            addNeighbor(Node(6))
            addNeighbor(Node(7))
        })
    }

    val graph = Graph(listOf(rootNode))

    val bfsSearch = BFS()
    bfsSearch.dfs(graph)

}