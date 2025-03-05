package agency;

import java.util.function.Predicate;

/**
 * BrandCriterion est un prédicat qui permet de filtrer des véhicules selon leur marque.
 * Il implémente l'interface {@link Predicate} pour tester si un véhicule correspond à la marque spécifiée.
 */
public class BrandCriterion implements Predicate<Vehicle> {
    private final String brand;

    /**
     * Construit un critère de marque pour filtrer les véhicules.
     *
     * @param brand La marque des véhicules à sélectionner.
     */
    public BrandCriterion(String brand) {
        this.brand = brand;
    }

    /**
     * Teste si le véhicule a la marque spécifiée (comparaison insensible à la casse).
     *
     * @param vehicle Le véhicule à tester.
     * @return {@code true} si le véhicule a la marque spécifiée, {@code false} sinon.
     */
    @Override
    public boolean test(Vehicle vehicle) {
        return vehicle.getBrand().equalsIgnoreCase(brand);  // Ignore la casse pour plus de flexibilité
    }
}
