import java.util.Random;

public class App {

    Population population = new Population();
    Individual fittest;
    Individual secondFittest;
    int generationCount = 0;

    static Random rn = new Random();

    public static void main(String[] args) {

        App demo = new App();

        String word = "mou";

        //Initialize population
        demo.population.initializePopulation(10);

        //Calculate fitness of each individual
        demo.population.calculateFitness(word);

        System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);

        //While population gets an individual with maximum fitness
        while (demo.population.fittest < 6) {
            ++demo.generationCount;

            //Do selection
            demo.selection();

            //Do crossover
            demo.crossover();

            //Do mutation under a random probability
            if (rn.nextInt() % 7 < 5) {
                demo.mutation();
            }

            //Add fittest offspring to population
            demo.addFittestOffspring(word);

            //Calculate new fitness value
            demo.population.calculateFitness(word);

            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);
        }

        System.out.println("\nSolution found in generation " + demo.generationCount);
        System.out.println("Fitness: " + demo.population.getFittest().fitness);
        System.out.print("Genes: ");
        for (int i = 0; i < 3; i++) {
            System.out.print(demo.population.getFittest().genes[i]);
        }

        System.out.println("");

    }

    void selection() {

        //Select the most fittest individual
        fittest = population.getFittest();

        //Select the second most fittest individual
        secondFittest = population.getSecondFittest();
    }

    void crossover() {

        //Select a random crossover point
        int crossOverPoint = rn.nextInt(Individual.GENE_LENGTH);

        //Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) {
            char temp = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = temp;
        }

    }

    void mutation() {

        //Select a random mutation point
        int mutationPoint = rn.nextInt(Individual.GENE_LENGTH);

        //Flip values at the mutation point
        fittest.genes[mutationPoint] = (char) (rn.nextInt(26) + 'a');
        secondFittest.genes[mutationPoint] = (char) (rn.nextInt(26) + 'a');
    }

    Individual getFittestOffspring() {
        if (fittest.fitness > secondFittest.fitness) {
            return fittest;
        }
        return secondFittest;
    }


    //Replace least fittest individual from most fittest offspring
    void addFittestOffspring(String word) {

        //Update fitness values of offspring
        fittest.calcFitness(word);
        secondFittest.calcFitness(word);

        //Get index of least fit individual
        int leastFittestIndex = population.getLeastFittestIndex();

        //Replace least fittest individual from most fittest offspring
        population.individuals[leastFittestIndex] = getFittestOffspring();
    }

}