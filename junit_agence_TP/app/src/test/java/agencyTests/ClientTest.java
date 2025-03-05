package agencyTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agency.Client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests unitaires pour la classe {@link Client}.
 */
class ClientTest {

    private Client client1;
    private Client client2;
    private Client client3;
    private Client client4;

    @BeforeEach
    void setUp() {
        client1 = new Client("John", "Doe", 1985);
        client2 = new Client("John", "Doe", 1985); // Identique à client1
        client3 = new Client("Jane", "Doe", 1985); // Différent prénom
        client4 = new Client("John", "Doe", 1990); // Différent année de naissance
    }

    @Test
    void testClientCreationWithValidData() {
        // Crée un client valide
        Client client = new Client("Alice", "Martin", 1992);

        // Vérifie que le client est bien créé
        assertThat(client.getFirstName()).isEqualTo("Alice");
        assertThat(client.getLastName()).isEqualTo("Martin");
        assertThat(client.getBirthYear()).isEqualTo(1992);
    }

    @Test
    void testClientCreationWithInvalidBirthYear() {
        // Teste l'exception lancée lorsque l'année de naissance est invalide
        assertThatThrownBy(() -> new Client("Alice", "Martin", 1899))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("L'année de naissance 1899 est invalide.");

        assertThatThrownBy(() -> new Client("Alice", "Martin", 2025))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("L'année de naissance 2025 est invalide.");
    }

    @Test
    void testEqualsMethodWithSameData() {
        // Teste si deux clients ayant les mêmes informations sont égaux
        Client client3 = new Client("John", "Doe", 1985);
        assertThat(client1).isEqualTo(client3);
    }

    @Test
    void testEqualsMethodWithDifferentData() {
        // Teste si deux clients ayant des informations différentes ne sont pas égaux
        assertThat(client1).isNotEqualTo(client3);
    }

    @Test
    void testToString() {
        // Vérifie que la méthode toString fonctionne correctement pour un client
        String expected = "John Doe (Né en 1985)";
        assertThat(client1.toString()).isEqualTo(expected);
    }
    
    @Test
    void testGetFirstName() {
        // Vérifie que la méthode getFirstName renvoie le bon prénom
        assertThat(client1.getFirstName()).isEqualTo("John");
    }

    @Test
    void testGetLastName() {
        // Vérifie que la méthode getLastName renvoie le bon nom de famille
        assertThat(client1.getLastName()).isEqualTo("Doe");
    }

    @Test
    void testGetBirthYear() {
        // Vérifie que la méthode getBirthYear renvoie la bonne année de naissance
        assertThat(client1.getBirthYear()).isEqualTo(1985);
    }

    @Test
    void testEqualsSameObject() {
        // Vérifie que l'égalité avec soi-même retourne true
        assertThat(client1.equals(client1)).isTrue();
    }

    @Test
    void testEqualsEqualClients() {
        // Vérifie que deux clients avec les mêmes valeurs sont égaux
        assertThat(client1.equals(client2)).isTrue();
    }

    @Test
    void testEqualsDifferentFirstName() {
        // Vérifie que les clients avec un prénom différent ne sont pas égaux
        assertThat(client1.equals(client3)).isFalse();
    }

    @Test
    void testEqualsDifferentBirthYear() {
        // Vérifie que les clients avec une année de naissance différente ne sont pas égaux
        assertThat(client1.equals(client4)).isFalse();
    }

    @Test
    void testEqualsDifferentClass() {
        // Vérifie que l'égalité avec un objet d'une autre classe retourne false
        assertThat(client1.equals("Not a client")).isFalse();
    }

    @Test
    void testEqualsNull() {
        // Vérifie que l'égalité avec null retourne false
        assertThat(client1.equals(null)).isFalse();
    }
}
