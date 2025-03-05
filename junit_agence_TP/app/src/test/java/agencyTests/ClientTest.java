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
        client2 = new Client("John", "Doe", 1985); // Identique � client1
        client3 = new Client("Jane", "Doe", 1985); // Diff�rent pr�nom
        client4 = new Client("John", "Doe", 1990); // Diff�rent ann�e de naissance
    }

    @Test
    void testClientCreationWithValidData() {
        // Cr�e un client valide
        Client client = new Client("Alice", "Martin", 1992);

        // V�rifie que le client est bien cr��
        assertThat(client.getFirstName()).isEqualTo("Alice");
        assertThat(client.getLastName()).isEqualTo("Martin");
        assertThat(client.getBirthYear()).isEqualTo(1992);
    }

    @Test
    void testClientCreationWithInvalidBirthYear() {
        // Teste l'exception lanc�e lorsque l'ann�e de naissance est invalide
        assertThatThrownBy(() -> new Client("Alice", "Martin", 1899))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("L'ann�e de naissance 1899 est invalide.");

        assertThatThrownBy(() -> new Client("Alice", "Martin", 2025))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("L'ann�e de naissance 2025 est invalide.");
    }

    @Test
    void testEqualsMethodWithSameData() {
        // Teste si deux clients ayant les m�mes informations sont �gaux
        Client client3 = new Client("John", "Doe", 1985);
        assertThat(client1).isEqualTo(client3);
    }

    @Test
    void testEqualsMethodWithDifferentData() {
        // Teste si deux clients ayant des informations diff�rentes ne sont pas �gaux
        assertThat(client1).isNotEqualTo(client3);
    }

    @Test
    void testToString() {
        // V�rifie que la m�thode toString fonctionne correctement pour un client
        String expected = "John Doe (N� en 1985)";
        assertThat(client1.toString()).isEqualTo(expected);
    }
    
    @Test
    void testGetFirstName() {
        // V�rifie que la m�thode getFirstName renvoie le bon pr�nom
        assertThat(client1.getFirstName()).isEqualTo("John");
    }

    @Test
    void testGetLastName() {
        // V�rifie que la m�thode getLastName renvoie le bon nom de famille
        assertThat(client1.getLastName()).isEqualTo("Doe");
    }

    @Test
    void testGetBirthYear() {
        // V�rifie que la m�thode getBirthYear renvoie la bonne ann�e de naissance
        assertThat(client1.getBirthYear()).isEqualTo(1985);
    }

    @Test
    void testEqualsSameObject() {
        // V�rifie que l'�galit� avec soi-m�me retourne true
        assertThat(client1.equals(client1)).isTrue();
    }

    @Test
    void testEqualsEqualClients() {
        // V�rifie que deux clients avec les m�mes valeurs sont �gaux
        assertThat(client1.equals(client2)).isTrue();
    }

    @Test
    void testEqualsDifferentFirstName() {
        // V�rifie que les clients avec un pr�nom diff�rent ne sont pas �gaux
        assertThat(client1.equals(client3)).isFalse();
    }

    @Test
    void testEqualsDifferentBirthYear() {
        // V�rifie que les clients avec une ann�e de naissance diff�rente ne sont pas �gaux
        assertThat(client1.equals(client4)).isFalse();
    }

    @Test
    void testEqualsDifferentClass() {
        // V�rifie que l'�galit� avec un objet d'une autre classe retourne false
        assertThat(client1.equals("Not a client")).isFalse();
    }

    @Test
    void testEqualsNull() {
        // V�rifie que l'�galit� avec null retourne false
        assertThat(client1.equals(null)).isFalse();
    }
}
