package agency;

/**
 * Repr�sente une moto � louer.
 * Une moto est d�finie par sa marque, son mod�le, son ann�e de production et sa cylindr�e.
 * Le prix de location journalier est calcul� en fonction de la cylindr�e de la moto.
 */
public class Motorbike extends AbstractVehicle {
    private int cylinderCapacity; // Cylindr�e en cm�

    /**
     * Cr�e une moto avec la marque, le mod�le, l'ann�e de production et la cylindr�e sp�cifi�s.
     *
     * @param brand La marque de la moto.
     * @param model Le mod�le de la moto.
     * @param productionYear L'ann�e de production de la moto.
     * @param cylinderCapacity La cylindr�e de la moto en cm�.
     * @throws IllegalArgumentException Si la cylindr�e est inf�rieure � 50 cm�.
     */
    public Motorbike(String brand, String model, int productionYear, int cylinderCapacity) {
        super(brand, model, productionYear);

        if (cylinderCapacity < 50) {
            throw new IllegalArgumentException("La cylindr�e " + cylinderCapacity + "cm� est trop faible.");
        }

        this.cylinderCapacity = cylinderCapacity;
    }

    /**
     * Calcule le prix de location journalier de la moto en fonction de sa cylindr�e.
     *
     * @return Le prix de location journalier.
     */
    @Override
    public double dailyRentalPrice() {
        return 0.25 * this.cylinderCapacity; // 0,25� par cm�
    }

    /**
     * Retourne une repr�sentation sous forme de cha�ne de caract�res de la moto.
     *
     * @return Une cha�ne repr�sentant la moto.
     */
    @Override
    public String toString() {
        return super.toString() + " (" + this.cylinderCapacity + "cm�) : " + dailyRentalPrice() + "�";
    }
}
