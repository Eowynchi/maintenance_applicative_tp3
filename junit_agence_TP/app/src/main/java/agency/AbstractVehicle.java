package agency;

import util.TimeProvider;

/**
 * Classe abstraite représentant un véhicule générique dans l'agence de location.
 * Implémente l'interface {@link Vehicle} et fournit des fonctionnalités de base
 * pour les sous-classes spécifiques comme {@link Car} ou {@link Motorbike}.
 */
public abstract class AbstractVehicle implements Vehicle {

    /**
     * La marque du véhicule (ex : Toyota, Yamaha).
     */
    protected String brand;

    /**
     * Le modèle du véhicule (ex : Corolla, MT-07).
     */
    protected String model;

    /**
     * L'année de production du véhicule.
     */
    protected int productionYear;

    /**
     * Constructeur pour initialiser les attributs d'un véhicule.
     * Vérifie que l'année de production est valide (entre 1900 et l'année courante).
     *
     * @param brand La marque du véhicule.
     * @param model Le modèle du véhicule.
     * @param productionYear L'année de production du véhicule.
     * @throws IllegalArgumentException Si l'année de production est invalide.
     */
    public AbstractVehicle(String brand, String model, int productionYear) {
        int currentYear = TimeProvider.currentYearValue();
        if (productionYear < 1900 || productionYear > currentYear) {
            throw new IllegalArgumentException("L'année de production " + productionYear + " est invalide.");
        }
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
    }

    /**
     * Récupère la marque du véhicule.
     *
     * @return La marque du véhicule.
     */
    @Override
    public String getBrand() {
        return this.brand;
    }

    /**
     * Récupère le modèle du véhicule.
     *
     * @return Le modèle du véhicule.
     */
    @Override
    public String getModel() {
        return this.model;
    }

    /**
     * Récupère l'année de production du véhicule.
     *
     * @return L'année de production.
     */
    @Override
    public int getProductionYear() {
        return this.productionYear;
    }

    /**
     * Vérifie l'égalité entre deux véhicules.
     * Deux véhicules sont considérés comme égaux s'ils ont la même marque,
     * le même modèle et la même année de production.
     *
     * @param o L'objet à comparer.
     * @return true si les véhicules sont identiques, false sinon.
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
     * Retourne une représentation textuelle du véhicule.
     *
     * @return Une chaîne de caractères représentant le véhicule.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.brand + " " + this.model + " " + this.productionYear;
    }

    /**
     * Méthode abstraite pour calculer le prix de location journalier du véhicule.
     * Doit être implémentée par les sous-classes.
     *
     * @return Le prix de location par jour.
     */
    public abstract double dailyRentalPrice();
}
