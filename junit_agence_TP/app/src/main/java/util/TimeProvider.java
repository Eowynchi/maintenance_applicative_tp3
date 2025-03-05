package util;

import java.time.LocalDate;

/**
 * Fournisseur de l'ann�e actuelle.
 * Cette classe permet d'obtenir l'ann�e actuelle en utilisant la date syst�me.
 */
public class TimeProvider {

    /**
     * Retourne l'ann�e actuelle en cours.
     *
     * @return L'ann�e actuelle.
     */
    public static int currentYearValue() {
        return LocalDate.now().getYear();
    }
}
