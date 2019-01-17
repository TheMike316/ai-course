package meta.genetic

fun main(args: Array<String>) {
    val algorithm = GeneticAlgorithm(popSize = 50, geneNo = 100, maxGeneration = 1000,
            tournamentSize = 10, crossoverRate = 0.9, mutationRate = 0.1, elitism = true, displayResults = true)

    val fittest = algorithm.search()

    println(fittest.fitness)
}