package agency;

import java.util.function.Predicate;

/**
 * Repr�sente un crit�re permettant de filtrer les v�hicules en fonction de leur prix de location maximal.
 * Un v�hicule est s�lectionn� si son prix de location journalier est inf�rieur ou �gal � un prix sp�cifi�.
 */
public class MaxPriceCriterion implements Predicate<Vehicle> {
    private final double maxPrice;

    /**
     * Cr�e un crit�re de s�lection bas� sur un prix maximal.
     *
     * @param maxPrice Le prix maximal que les v�hicules doivent respecter.
     */
    public MaxPriceCriterion(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * Teste si le v�hicule pass� en argument respecte le crit�re de prix maximal.
     *
     * @param vehicle Le v�hicule � tester.
     * @return {@code true} si le prix de location journalier du v�hicule est inf�rieur ou �gal � {@code maxPrice}, sinon {@code false}.
     */
    @Override
    public boolean test(Vehicle vehicle) {
        return vehicle.dailyRentalPrice() <= maxPrice;
    }
}
