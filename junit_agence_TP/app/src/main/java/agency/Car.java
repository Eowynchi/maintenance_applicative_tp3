package agency;

import util.TimeProvider;

/**
 * Représente une voiture dans l'agence de location.
 * Cette classe étend la classe {@link AbstractVehicle} et implémente les détails spécifiques à la location de voitures.
 */
public class Car extends AbstractVehicle {
    private int numberOfSeats;

    /**
     * Crée une nouvelle voiture avec les spécifications données.
     *
     * @param brand           La marque de la voiture.
     * @param model           Le modèle de la voiture.
     * @param productionYear  L'année de production de la voiture.
     * @param numberOfSeats   Le nombre de sièges dans la voiture.
     * @throws IllegalArgumentException Si le nombre de sièges est inférieur à 1.
     */
    public Car(String brand, String model, int productionYear, int numberOfSeats) {
        super(brand, model, productionYear);

        if (numberOfSeats < 1) {
            throw new IllegalArgumentException("Le nombre de sièges " + numberOfSeats + " est invalide.");
        }

        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Calcule le prix de location journalier de la voiture en fonction de son âge et du nombre de sièges.
     * Si la voiture est un modèle récent (moins de 5 ans), le prix par siège est plus élevé.
     *
     * @return Le prix de location journalier en euros.
     */
    @Override
    public double dailyRentalPrice() {
        int currentYear = TimeProvider.currentYearValue();
        boolean isNewModel = (currentYear - this.productionYear) <= 5;
        double pricePerSeat = isNewModel ? 40 : 20;
        return pricePerSeat * this.numberOfSeats;
    }

    /**
     * Retourne une représentation textuelle de la voiture incluant sa marque, son modèle, son année de production, son nombre de sièges et son prix de location journalier.
     *
     * @return La chaîne de caractères représentant la voiture.
     */
    @Override
    public String toString() {
        String seatString = this.numberOfSeats == 1 ? "1 seat" : this.numberOfSeats + " seats";
        return super.toString() + " (" + seatString + ") : " + dailyRentalPrice() + "€";
    }
}
