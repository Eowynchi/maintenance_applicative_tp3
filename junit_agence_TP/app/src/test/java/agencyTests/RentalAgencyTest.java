package agencyTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agency.BrandCriterion;
import agency.Car;
import agency.Client;
import agency.MaxPriceCriterion;
import agency.Motorbike;
import agency.RentalAgency;
import agency.UnknownVehicleException;
import agency.Vehicle;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.List;

class RentalAgencyTest {
    private RentalAgency rentalAgency;
    private Vehicle car;
    private Vehicle motorbike;
    private Client client;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        rentalAgency = new RentalAgency();
        car = new Car("Toyota", "Corolla", 2018, 4);
        motorbike = new Motorbike("Yamaha", "MT-07", 2020, 689);
        client = new Client("John", "Doe", 1985);

        rentalAgency.add(car);
        rentalAgency.add(motorbike);

        // Capture the standard output stream to verify printed content
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testAddVehicle() {
        // V�rifie que l'ajout d'un v�hicule fonctionne
        Vehicle newCar = new Car("Honda", "Civic", 2020, 5);
        rentalAgency.add(newCar);
        assertThat(rentalAgency.getVehicles()).contains(newCar);
    }

    @Test
    void testRemoveVehicle() {
        rentalAgency.remove(car);
        assertThat(rentalAgency.getVehicles()).doesNotContain(car);
    }

    @Test
    void testRentVehicle() {
        double rentalPrice = rentalAgency.rentVehicle(client, car);
        assertThat(rentalPrice).isEqualTo(car.dailyRentalPrice());
        assertThat(rentalAgency.aVehicleIsRentedBy(client)).isTrue();
    }

    @Test
    void testSelectByBrand() {
        rentalAgency.printSelectedVehicles(new BrandCriterion("Yamaha"));
        assertThat(rentalAgency.select(new BrandCriterion("Yamaha"))).containsOnly(motorbike);
    }

    @Test
    void testSelectByPrice() {
        rentalAgency.printSelectedVehicles(new MaxPriceCriterion(100));
        assertThat(rentalAgency.select(new MaxPriceCriterion(100)))
                .allMatch(vehicle -> vehicle.dailyRentalPrice() <= 100);
    }

    @Test
    void testRentUnknownVehicle() {
        Vehicle unknownVehicle = new Car("Honda", "Civic", 2020, 5);

        assertThatThrownBy(() -> rentalAgency.rentVehicle(client, unknownVehicle))
                .isInstanceOf(UnknownVehicleException.class)
                .hasMessageContaining("Le v�hicule suivant n'existe pas dans l'agence");
    }

    @Test
    void testClientAlreadyRentsVehicle() {
        rentalAgency.rentVehicle(client, car);

        // On tente de louer un autre v�hicule pour le m�me client
        assertThatThrownBy(() -> rentalAgency.rentVehicle(client, motorbike))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("loue d�j� un autre v�hicule");
    }
    @Test
    void testRentVehicleAlreadyRented() {
        // On loue d'abord le v�hicule (car)
        rentalAgency.rentVehicle(client, car);

        // Maintenant, le v�hicule est d�j� lou�, et on tente de le louer � un autre client
        Client secondClient = new Client("Jane", "Doe", 1990);

        // On v�rifie que l'exception IllegalStateException est lev�e
        assertThatThrownBy(() -> rentalAgency.rentVehicle(secondClient, car))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Le v�hicule est d�j� lou�");
    }
    
    @Test
    void testRentalAgencyConstructorWithVehicles() {
        // Cr�ation de quelques v�hicules
        Vehicle car = new Car("Toyota", "Corolla", 2018, 4);
        Vehicle motorbike = new Motorbike("Yamaha", "MT-07", 2020, 689);

        // On cr�e une liste avec ces v�hicules
        List<Vehicle> vehicles = List.of(car, motorbike);

        // Cr�ation d'une instance de RentalAgency avec la liste de v�hicules
        RentalAgency rentalAgencyWithVehicles = new RentalAgency(vehicles);

        // V�rifier que les v�hicules ont bien �t� ajout�s � l'agence de location
        assertThat(rentalAgencyWithVehicles.getVehicles()).containsExactly(car, motorbike);
    }
    
    @Test
    void testReturnVehicle() {
        // Le client loue un v�hicule
        rentalAgency.rentVehicle(client, car);

        // V�rifier que le v�hicule est bien dans les v�hicules lou�s
        assertThat(rentalAgency.allRentedVehicles()).containsOnly(car);

        // Le client rend le v�hicule
        rentalAgency.returnVehicle(client);

        // V�rifier que la map des v�hicules lou�s est vide
        assertThat(rentalAgency.allRentedVehicles()).isEmpty();
    }

    @Test
    void testAllRentedVehicles() {
        // Louer des v�hicules � diff�rents clients
        Client client2 = new Client("Alice", "Smith", 1990);
        rentalAgency.rentVehicle(client, car);
        rentalAgency.rentVehicle(client2, motorbike);

        // V�rifier que la m�thode retourne bien tous les v�hicules lou�s
        Collection<Vehicle> rentedVehicles = rentalAgency.allRentedVehicles();
        assertThat(rentedVehicles).containsExactlyInAnyOrder(car, motorbike);
    }
    
    @Test
    void testContainsVehicleWhenPresent() {
        // V�rifier que le v�hicule est pr�sent dans l'agence
        assertThat(rentalAgency.contains(car)).isTrue();
        assertThat(rentalAgency.contains(motorbike)).isTrue();
    }

    @Test
    void testContainsVehicleWhenNotPresent() {
        // Cr�er un nouveau v�hicule qui n'a pas �t� ajout� � l'agence
        Vehicle bike = new Motorbike("Kawasaki", "Ninja", 2021, 800);

        // V�rifier que ce v�hicule n'est pas pr�sent dans l'agence
        assertThat(rentalAgency.contains(bike)).isFalse();
    }
    
    @Test
    void testAddVehicleWhenAlreadyExists() {
        // Essayer d'ajouter un v�hicule qui est d�j� dans la liste
        boolean result = rentalAgency.add(car);  // Essayer de rajouter le m�me v�hicule
        assertThat(result).isFalse();  // La m�thode doit retourner false car il est d�j� pr�sent
    }
    
    @Test
    void testRemoveVehicleNonExistent() {
        // Cr�e un nouveau v�hicule qui n'est pas dans l'agence
        Vehicle nonExistentVehicle = new Car("Honda", "Civic", 2020, 5);
        
        // V�rifie qu'une exception est lev�e si le v�hicule n'existe pas dans la liste
        assertThatThrownBy(() -> rentalAgency.remove(nonExistentVehicle))
            .isInstanceOf(UnknownVehicleException.class)
            .hasMessageContaining("Le v�hicule suivant n'existe pas dans l'agence");
    }
    
    @Test
    void testPrintSelectedVehiclesNoMatch() {
        // Utilisation d'un crit�re qui ne correspond � aucun v�hicule
        rentalAgency.printSelectedVehicles(new BrandCriterion("Honda"));
        
        // V�rifie que le message "Aucun v�hicule ne correspond au crit�re." est imprim�
        String output = outputStream.toString().trim();
        assertThat(output).isEqualTo("Aucun v�hicule ne correspond au crit�re.");
    }
}
