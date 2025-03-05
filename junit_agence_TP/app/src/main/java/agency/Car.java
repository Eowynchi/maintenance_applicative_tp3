package agency;

import util.TimeProvider;

/**
 * Repr�sente une voiture dans l'agence de location.
 * Cette classe �tend la classe {@link AbstractVehicle} et impl�mente les d�tails sp�cifiques � la location de voitures.
 */
public class Car extends AbstractVehicle {
    private int numberOfSeats;

    /**
     * Cr�e une nouvelle voiture avec les sp�cifications donn�es.
     *
     * @param brand           La marque de la voiture.
     * @param model           Le mod�le de la voiture.
     * @param productionYear  L'ann�e de production de la voiture.
     * @param numberOfSeats   Le nombre de si�ges dans la voiture.
     * @throws IllegalArgumentException Si le nombre de si�ges est inf�rieur � 1.
     */
    public Car(String brand, String model, int productionYear, int numberOfSeats) {
        super(brand, model, productionYear);

        if (numberOfSeats < 1) {
            throw new IllegalArgumentException("Le nombre de si�ges " + numberOfSeats + " est invalide.");
        }

        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Calcule le prix de location journalier de la voiture en fonction de son �ge et du nombre de si�ges.
     * Si la voiture est un mod�le r�cent (moins de 5 ans), le prix par si�ge est plus �lev�.
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
     * Retourne une repr�sentation textuelle de la voiture incluant sa marque, son mod�le, son ann�e de production, son nombre de si�ges et son prix de location journalier.
     *
     * @return La cha�ne de caract�res repr�sentant la voiture.
     */
    @Override
    public String toString() {
        String seatString = this.numberOfSeats == 1 ? "1 seat" : this.numberOfSeats + " seats";
        return super.toString() + " (" + seatString + ") : " + dailyRentalPrice() + "�";
    }
}
