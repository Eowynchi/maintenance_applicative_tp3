package agency;

import util.TimeProvider;

/**
 * Classe abstraite repr�sentant un v�hicule g�n�rique dans l'agence de location.
 * Impl�mente l'interface {@link Vehicle} et fournit des fonctionnalit�s de base
 * pour les sous-classes sp�cifiques comme {@link Car} ou {@link Motorbike}.
 */
public abstract class AbstractVehicle implements Vehicle {

    /**
     * La marque du v�hicule (ex : Toyota, Yamaha).
     */
    protected String brand;

    /**
     * Le mod�le du v�hicule (ex : Corolla, MT-07).
     */
    protected String model;

    /**
     * L'ann�e de production du v�hicule.
     */
    protected int productionYear;

    /**
     * Constructeur pour initialiser les attributs d'un v�hicule.
     * V�rifie que l'ann�e de production est valide (entre 1900 et l'ann�e courante).
     *
     * @param brand La marque du v�hicule.
     * @param model Le mod�le du v�hicule.
     * @param productionYear L'ann�e de production du v�hicule.
     * @throws IllegalArgumentException Si l'ann�e de production est invalide.
     */
    public AbstractVehicle(String brand, String model, int productionYear) {
        int currentYear = TimeProvider.currentYearValue();
        if (productionYear < 1900 || productionYear > currentYear) {
            throw new IllegalArgumentException("L'ann�e de production " + productionYear + " est invalide.");
        }
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
    }

    /**
     * R�cup�re la marque du v�hicule.
     *
     * @return La marque du v�hicule.
     */
    @Override
    public String getBrand() {
        return this.brand;
    }

    /**
     * R�cup�re le mod�le du v�hicule.
     *
     * @return Le mod�le du v�hicule.
     */
    @Override
    public String getModel() {
        return this.model;
    }

    /**
     * R�cup�re l'ann�e de production du v�hicule.
     *
     * @return L'ann�e de production.
     */
    @Override
    public int getProductionYear() {
        return this.productionYear;
    }

    /**
     * V�rifie l'�galit� entre deux v�hicules.
     * Deux v�hicules sont consid�r�s comme �gaux s'ils ont la m�me marque,
     * le m�me mod�le et la m�me ann�e de production.
     *
     * @param o L'objet � comparer.
     * @return true si les v�hicules sont identiques, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractVehicle other = (AbstractVehicle) o;
        return this.brand.equals(other.brand) && this.model.equals(other.model) && this.productionYear == other.productionYear;
    }

    /**
     * Retourne une repr�sentation textuelle du v�hicule.
     *
     * @return Une cha�ne de caract�res repr�sentant le v�hicule.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.brand + " " + this.model + " " + this.productionYear;
    }

    /**
     * M�thode abstraite pour calculer le prix de location journalier du v�hicule.
     * Doit �tre impl�ment�e par les sous-classes.
     *
     * @return Le prix de location par jour.
     */
    public abstract double dailyRentalPrice();
}
