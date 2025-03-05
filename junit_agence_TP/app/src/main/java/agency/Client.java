package agency;

import java.util.Objects;
import util.TimeProvider;

/**
 * Repr�sente un client de l'agence de location de v�hicules.
 * Un client est d�fini par son pr�nom, nom et ann�e de naissance.
 */
public class Client {
    private final String firstName;
    private final String lastName;
    private final int birthYear;

    /**
     * Cr�e un client avec les informations sp�cifi�es.
     *
     * @param firstName  Le pr�nom du client.
     * @param lastName   Le nom de famille du client.
     * @param birthYear  L'ann�e de naissance du client.
     * @throws IllegalArgumentException Si l'ann�e de naissance est inf�rieure � 1900 ou sup�rieure � l'ann�e actuelle.
     */
    public Client(String firstName, String lastName, int birthYear) {
        if (birthYear < 1900 || birthYear > TimeProvider.currentYearValue()) {
            throw new IllegalArgumentException("L'ann�e de naissance " + birthYear + " est invalide.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    /**
     * Retourne le pr�nom du client.
     *
     * @return Le pr�nom du client.
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
     * Retourne l'ann�e de naissance du client.
     *
     * @return L'ann�e de naissance du client.
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * Compare ce client � un autre objet pour v�rifier s'ils sont �gaux.
     * Deux clients sont �gaux s'ils ont le m�me pr�nom, nom de famille et ann�e de naissance.
     *
     * @param o L'objet � comparer avec ce client.
     * @return {@code true} si les deux objets sont �gaux, sinon {@code false}.
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
     * Retourne une repr�sentation textuelle du client.
     * Par exemple : "Jean Dupont (N� en 1985)".
     *
     * @return La repr�sentation sous forme de cha�ne de caract�res du client.
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " (N� en " + birthYear + ")";
    }
}
