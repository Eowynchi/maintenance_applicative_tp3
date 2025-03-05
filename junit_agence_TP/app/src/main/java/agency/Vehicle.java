package agency;

/**
 * Interface repr�sentant un v�hicule � louer dans une agence.
 * Cette interface d�finit les m�thodes de base pour obtenir les informations
 * relatives au v�hicule et pour calculer le prix de location.
 */
public interface Vehicle {

    /**
     * Retourne la marque du v�hicule.
     *
     * @return La marque du v�hicule.
     */
    String getBrand();

    /**
     * Retourne le mod�le du v�hicule.
     *
     * @return Le mod�le du v�hicule.
     */
    String getModel();

    /**
     * Retourne l'ann�e de production du v�hicule.
     *
     * @return L'ann�e de production du v�hicule.
     */
    int getProductionYear();

    /**
     * Calcule le prix de location quotidien du v�hicule.
     *
     * @return Le prix de location par jour.
     */
    double dailyRentalPrice();

    /**
     * Retourne une repr�sentation sous forme de cha�ne de caract�res du v�hicule.
     *
     * @return La cha�ne repr�sentant les informations du v�hicule.
     */
    @Override
    String toString();
}
