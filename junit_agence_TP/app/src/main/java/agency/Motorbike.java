package agency;

/**
 * Représente une moto à louer.
 * Une moto est définie par sa marque, son modèle, son année de production et sa cylindrée.
 * Le prix de location journalier est calculé en fonction de la cylindrée de la moto.
 */
public class Motorbike extends AbstractVehicle {
    private int cylinderCapacity; // Cylindrée en cm³

    /**
     * Crée une moto avec la marque, le modèle, l'année de production et la cylindrée spécifiés.
     *
     * @param brand La marque de la moto.
     * @param model Le modèle de la moto.
     * @param productionYear L'année de production de la moto.
     * @param cylinderCapacity La cylindrée de la moto en cm³.
     * @throws IllegalArgumentException Si la cylindrée est inférieure à 50 cm³.
     */
    public Motorbike(String brand, String model, int productionYear, int cylinderCapacity) {
        super(brand, model, productionYear);

        if (cylinderCapacity < 50) {
            throw new IllegalArgumentException("La cylindrée " + cylinderCapacity + "cm³ est trop faible.");
        }

        this.cylinderCapacity = cylinderCapacity;
    }

    /**
     * Calcule le prix de location journalier de la moto en fonction de sa cylindrée.
     *
     * @return Le prix de location journalier.
     */
    @Override
    public double dailyRentalPrice() {
        return 0.25 * this.cylinderCapacity; // 0,25€ par cm³
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la moto.
     *
     * @return Une chaîne représentant la moto.
     */
    @Override
    public String toString() {
        return super.toString() + " (" + this.cylinderCapacity + "cm³) : " + dailyRentalPrice() + "€";
    }
}
