package meta.genetic

fun evaluateFitness(genes: Array<Int?>) = genes.sumBy { it ?: 0 }
