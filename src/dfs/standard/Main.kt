package dfs.standard


fun main(args: Array<String>) {

    val rootNode = Node(1).apply {
        addChild(Node(2).apply {
            addChild(Node(4))
            addChild(Node(5))
        })
        addChild(Node(3).apply {
            addChild(Node(6))
            addChild(Node(7))
        })
    }

    val graph = Graph(listOf(rootNode))

    DFS().dfs(graph)
}