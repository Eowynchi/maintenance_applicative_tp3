package agency;

import java.util.Objects;
import util.TimeProvider;

/**
 * Représente un client de l'agence de location de véhicules.
 * Un client est défini par son prénom, nom et année de naissance.
 */
public class Client {
    private final String firstName;
    private final String lastName;
    private final int birthYear;

    /**
     * Crée un client avec les informations spécifiées.
     *
     * @param firstName  Le prénom du client.
     * @param lastName   Le nom de famille du client.
     * @param birthYear  L'année de naissance du client.
     * @throws IllegalArgumentException Si l'année de naissance est inférieure à 1900 ou supérieure à l'année actuelle.
     */
    public Client(String firstName, String lastName, int birthYear) {
        if (birthYear < 1900 || birthYear > TimeProvider.currentYearValue()) {
            throw new IllegalArgumentException("L'année de naissance " + birthYear + " est invalide.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    /**
     * Retourne le prénom du client.
     *
     * @return Le prénom du client.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retourne le nom de famille du client.
     *
     * @return Le nom de famille du client.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retourne l'année de naissance du client.
     *
     * @return L'année de naissance du client.
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * Compare ce client à un autre objet pour vérifier s'ils sont égaux.
     * Deux clients sont égaux s'ils ont le même prénom, nom de famille et année de naissance.
     *
     * @param o L'objet à comparer avec ce client.
     * @return {@code true} si les deux objets sont égaux, sinon {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return birthYear == client.birthYear &&
               firstName.equals(client.firstName) &&
               lastName.equals(client.lastName);
    }

    /**
     * Retourne un code de hachage pour le client.
     *
     * @return Un code de hachage pour ce client.
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthYear);
    }

    /**
     * Retourne une représentation textuelle du client.
     * Par exemple : "Jean Dupont (Né en 1985)".
     *
     * @return La représentation sous forme de chaîne de caractères du client.
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " (Né en " + birthYear + ")";
    }
}
