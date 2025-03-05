package agency;

import java.util.function.Predicate;

/**
 * BrandCriterion est un pr�dicat qui permet de filtrer des v�hicules selon leur marque.
 * Il impl�mente l'interface {@link Predicate} pour tester si un v�hicule correspond � la marque sp�cifi�e.
 */
public class BrandCriterion implements Predicate<Vehicle> {
    private final String brand;

    /**
     * Construit un crit�re de marque pour filtrer les v�hicules.
     *
     * @param brand La marque des v�hicules � s�lectionner.
     */
    public BrandCriterion(String brand) {
        this.brand = brand;
    }

    /**
     * Teste si le v�hicule a la marque sp�cifi�e (comparaison insensible � la casse).
     *
     * @param vehicle Le v�hicule � tester.
     * @return {@code true} si le v�hicule a la marque sp�cifi�e, {@code false} sinon.
     */
    @Override
    public boolean test(Vehicle vehicle) {
        return vehicle.getBrand().equalsIgnoreCase(brand);  // Ignore la casse pour plus de flexibilit�
    }
}
