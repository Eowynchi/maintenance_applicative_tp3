package util;

import java.time.LocalDate;

/**
 * Fournisseur de l'année actuelle.
 * Cette classe permet d'obtenir l'année actuelle en utilisant la date système.
 */
public class TimeProvider {

    /**
     * Retourne l'année actuelle en cours.
     *
     * @return L'année actuelle.
     */
    public static int currentYearValue() {
        return LocalDate.now().getYear();
    }
}
