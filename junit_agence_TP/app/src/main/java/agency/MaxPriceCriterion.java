package agency;

import java.util.function.Predicate;

/**
 * Représente un critère permettant de filtrer les véhicules en fonction de leur prix de location maximal.
 * Un véhicule est sélectionné si son prix de location journalier est inférieur ou égal à un prix spécifié.
 */
public class MaxPriceCriterion implements Predicate<Vehicle> {
    private final double maxPrice;

    /**
     * Crée un critère de sélection basé sur un prix maximal.
     *
     * @param maxPrice Le prix maximal que les véhicules doivent respecter.
     */
    public MaxPriceCriterion(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * Teste si le véhicule passé en argument respecte le critère de prix maximal.
     *
     * @param vehicle Le véhicule à tester.
     * @return {@code true} si le prix de location journalier du véhicule est inférieur ou égal à {@code maxPrice}, sinon {@code false}.
     */
    @Override
    public boolean test(Vehicle vehicle) {
        return vehicle.dailyRentalPrice() <= maxPrice;
    }
}
