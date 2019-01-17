package meta.genetic

class GeneticAlgorithm(private val popSize: Int,
                       private val geneNo: Int,
                       private val maxGeneration: Int,
                       private val tournamentSize: Int,
                       private val crossoverRate: Double,
                       private val mutationRate: Double,
                       private val elitism: Boolean,
                       private val displayResults: Boolean) {

    fun search(): Chromosome {
        var population = createPopulation()
        population.initialize(0.5)

        for (i in 0 until maxGeneration) {
            val newPopulation = createPopulation()

            val offset = if (elitism) {
                elitisim(population, newPopulation)
                1
            } else
                0

            for (j in offset until popSize) {
                val parent1 = tournamentSelection(population)
                val parent2 = tournamentSelection(population)

                if (Math.random() < crossoverRate) {
                    val offspring = crossover(parent1, parent2)
                    mutate(offspring)
                    newPopulation.setSingleChromosome(j, offspring)
                } else
                    Math.random().takeIf { it < 0.5 }?.let { newPopulation.setSingleChromosome(j, parent1) }
                            ?: newPopulation.setSingleChromosome(j, parent2)
            }
            population = newPopulation
            if (displayResults)
                println("In generation# $i: Fittest = ${population.getFittest().fitness}")
        }
        return population.getFittest()
    }

    private fun createPopulation() = Population(popSize, geneNo)

    private fun tournamentSelection(population: Population): Chromosome {
        val tournament = Population(tournamentSize, geneNo)

        for (i in 0 until tournamentSize) {
            val randomIdx = (Math.random() * popSize).toInt()
            tournament.setSingleChromosome(i, population.getSingleChromosome(randomIdx))
        }

        return tournament.getFittest()
    }

    private fun crossover(parent1: Chromosome, parent2: Chromosome): Chromosome {
        val offspring = Chromosome(geneNo)

        val crossoverPoint = Math.floor(Math.random() * geneNo).toInt()

        for (i in 0 until crossoverPoint) {
            offspring.setSingleGene(i, parent1.getSingleGene(i))
        }

        for (i in crossoverPoint until geneNo) {
            offspring.setSingleGene(i, parent2.getSingleGene(i))
        }

        return offspring
    }

    private fun mutate(offspring: Chromosome) {
        for ((i, gene) in offspring.genes.withIndex()) {
            if (Math.random() < mutationRate)
                offspring.setSingleGene(i, if (gene == 1) 0 else 1)
        }
    }

    private fun elitisim(currentPopulation: Population, newPopulation: Population) {
        newPopulation.setSingleChromosome(0, currentPopulation.getFittest())
    }
}