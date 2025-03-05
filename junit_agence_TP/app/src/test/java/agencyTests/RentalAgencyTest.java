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
        // Vérifie que l'ajout d'un véhicule fonctionne
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
                .hasMessageContaining("Le véhicule suivant n'existe pas dans l'agence");
    }

    @Test
    void testClientAlreadyRentsVehicle() {
        rentalAgency.rentVehicle(client, car);

        // On tente de louer un autre véhicule pour le même client
        assertThatThrownBy(() -> rentalAgency.rentVehicle(client, motorbike))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("loue déjà un autre véhicule");
    }
    @Test
    void testRentVehicleAlreadyRented() {
        // On loue d'abord le véhicule (car)
        rentalAgency.rentVehicle(client, car);

        // Maintenant, le véhicule est déjà loué, et on tente de le louer à un autre client
        Client secondClient = new Client("Jane", "Doe", 1990);

        // On vérifie que l'exception IllegalStateException est levée
        assertThatThrownBy(() -> rentalAgency.rentVehicle(secondClient, car))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Le véhicule est déjà loué");
    }
    
    @Test
    void testRentalAgencyConstructorWithVehicles() {
        // Création de quelques véhicules
        Vehicle car = new Car("Toyota", "Corolla", 2018, 4);
        Vehicle motorbike = new Motorbike("Yamaha", "MT-07", 2020, 689);

        // On crée une liste avec ces véhicules
        List<Vehicle> vehicles = List.of(car, motorbike);

        // Création d'une instance de RentalAgency avec la liste de véhicules
        RentalAgency rentalAgencyWithVehicles = new RentalAgency(vehicles);

        // Vérifier que les véhicules ont bien été ajoutés à l'agence de location
        assertThat(rentalAgencyWithVehicles.getVehicles()).containsExactly(car, motorbike);
    }
    
    @Test
    void testReturnVehicle() {
        // Le client loue un véhicule
        rentalAgency.rentVehicle(client, car);

        // Vérifier que le véhicule est bien dans les véhicules loués
        assertThat(rentalAgency.allRentedVehicles()).containsOnly(car);

        // Le client rend le véhicule
        rentalAgency.returnVehicle(client);

        // Vérifier que la map des véhicules loués est vide
        assertThat(rentalAgency.allRentedVehicles()).isEmpty();
    }

    @Test
    void testAllRentedVehicles() {
        // Louer des véhicules à différents clients
        Client client2 = new Client("Alice", "Smith", 1990);
        rentalAgency.rentVehicle(client, car);
        rentalAgency.rentVehicle(client2, motorbike);

        // Vérifier que la méthode retourne bien tous les véhicules loués
        Collection<Vehicle> rentedVehicles = rentalAgency.allRentedVehicles();
        assertThat(rentedVehicles).containsExactlyInAnyOrder(car, motorbike);
    }
    
    @Test
    void testContainsVehicleWhenPresent() {
        // Vérifier que le véhicule est présent dans l'agence
        assertThat(rentalAgency.contains(car)).isTrue();
        assertThat(rentalAgency.contains(motorbike)).isTrue();
    }

    @Test
    void testContainsVehicleWhenNotPresent() {
        // Créer un nouveau véhicule qui n'a pas été ajouté à l'agence
        Vehicle bike = new Motorbike("Kawasaki", "Ninja", 2021, 800);

        // Vérifier que ce véhicule n'est pas présent dans l'agence
        assertThat(rentalAgency.contains(bike)).isFalse();
    }
    
    @Test
    void testAddVehicleWhenAlreadyExists() {
        // Essayer d'ajouter un véhicule qui est déjà dans la liste
        boolean result = rentalAgency.add(car);  // Essayer de rajouter le même véhicule
        assertThat(result).isFalse();  // La méthode doit retourner false car il est déjà présent
    }
    
    @Test
    void testRemoveVehicleNonExistent() {
        // Crée un nouveau véhicule qui n'est pas dans l'agence
        Vehicle nonExistentVehicle = new Car("Honda", "Civic", 2020, 5);
        
        // Vérifie qu'une exception est levée si le véhicule n'existe pas dans la liste
        assertThatThrownBy(() -> rentalAgency.remove(nonExistentVehicle))
            .isInstanceOf(UnknownVehicleException.class)
            .hasMessageContaining("Le véhicule suivant n'existe pas dans l'agence");
    }
    
    @Test
    void testPrintSelectedVehiclesNoMatch() {
        // Utilisation d'un critère qui ne correspond à aucun véhicule
        rentalAgency.printSelectedVehicles(new BrandCriterion("Honda"));
        
        // Vérifie que le message "Aucun véhicule ne correspond au critère." est imprimé
        String output = outputStream.toString().trim();
        assertThat(output).isEqualTo("Aucun véhicule ne correspond au critère.");
    }
}
