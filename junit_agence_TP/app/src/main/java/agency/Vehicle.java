package agency;

/**
 * Interface représentant un véhicule à louer dans une agence.
 * Cette interface définit les méthodes de base pour obtenir les informations
 * relatives au véhicule et pour calculer le prix de location.
 */
public interface Vehicle {

    /**
     * Retourne la marque du véhicule.
     *
     * @return La marque du véhicule.
     */
    String getBrand();

    /**
     * Retourne le modèle du véhicule.
     *
     * @return Le modèle du véhicule.
     */
    String getModel();

    /**
     * Retourne l'année de production du véhicule.
     *
     * @return L'année de production du véhicule.
     */
    int getProductionYear();

    /**
     * Calcule le prix de location quotidien du véhicule.
     *
     * @return Le prix de location par jour.
     */
    double dailyRentalPrice();

    /**
     * Retourne une représentation sous forme de chaîne de caractères du véhicule.
     *
     * @return La chaîne représentant les informations du véhicule.
     */
    @Override
    String toString();
}
