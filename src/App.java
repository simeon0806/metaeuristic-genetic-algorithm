import java.util.Random;

public class App {

    static Individual fittest;

    static Individual secondFittest;

    static int generationCount = 0;

    static Random rn = new Random();

    public static void main(String[] args) {
        String word = "tus";

        long startTime = System.currentTimeMillis();

        // Initialize the population
        Population population = new Population(word.length());

        // Calculate the fitness of each individual
        population.calculateFitness(word);

        System.out.println("Generation: " + generationCount + " Fittest: " + population.fittest);

        // While loop until population gets an individual with maximum fitness
        while (population.fittest < (word.length() * 2)) {
            ++generationCount;

            // Do selection
            selection(population);

            // Do crossover
            crossover();

            // Do mutation under a random probability
            if (rn.nextInt() % 7 < 5) {
                mutation();
            }

            // Add the fittest offspring to population
            addFittestOffspring(population, word);

            // Calculate new fitness value
            population.calculateFitness(word);

            System.out.println("Generation: " + generationCount + " Fittest: " + population.fittest);
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("\nSolution found in generation " + generationCount);
        System.out.println("Fitness: " + population.getFittest().fitness);
        System.out.print("Genes: ");
        for (int i = 0; i < word.length(); i++) {
            System.out.print(population.getFittest().genes[i]);
        }

        System.out.println("Time taken: " + elapsedTime + " milliseconds");
        System.out.println();
    }

    static void selection(Population population) {
        // Select the fittest individual
        fittest = population.getFittest();

        // Select the second-fittest individual
        secondFittest = population.getSecondFittest();
    }

    static void crossover() {
        // Select a random crossover point
        int crossOverPoint = rn.nextInt(Individual.GENE_LENGTH);

        // Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) {
            char temp = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = temp;
        }

    }

    static void mutation() {
        // Select a random mutation point
        int mutationPoint = rn.nextInt(Individual.GENE_LENGTH);

        // Flip values at the mutation point
        fittest.genes[mutationPoint] = (char) (rn.nextInt(26) + 'a');
        secondFittest.genes[mutationPoint] = (char) (rn.nextInt(26) + 'a');
    }

    static Individual getFittestOffspring() {
        if (fittest.fitness > secondFittest.fitness) {
            return fittest;
        }
        return secondFittest;
    }


    // Replace the least fit individual from the fittest offspring
    static void addFittestOffspring(Population population, String word) {
        // Update fitness values of offspring
        fittest.calcFitness(word);
        secondFittest.calcFitness(word);

        // Get index of the least fit individual
        int leastFittestIndex = population.getLeastFittestIndex();

        // Replace the least fit individual from the fittest offspring
        population.individuals[leastFittestIndex] = getFittestOffspring();
    }

}