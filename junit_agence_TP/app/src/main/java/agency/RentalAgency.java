package agency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Classe représentant une agence de location de véhicules.
 * Elle permet de gérer la liste des véhicules disponibles à la location, 
 * ainsi que les locations effectuées par les clients.
 */
public class RentalAgency {
    private final List<Vehicle> vehicles;
    private final Map<Client, Vehicle> rentedVehicles;

    /**
     * Crée une nouvelle agence sans véhicules.
     */
    public RentalAgency() {
        this.vehicles = new ArrayList<>();
        this.rentedVehicles = new HashMap<>();
    }

    /**
     * Crée une nouvelle agence avec les véhicules spécifiés.
     *
     * @param vehicles La liste des véhicules à ajouter à l'agence.
     */
    public RentalAgency(List<Vehicle> vehicles) {
        this.vehicles = new ArrayList<>(vehicles);
        this.rentedVehicles = new HashMap<>();
    }

    /**
     * Ajoute un véhicule à l'agence s'il n'est pas déjà présent.
     *
     * @param vehicle Le véhicule à ajouter.
     * @return true si le véhicule a été ajouté, false sinon.
     */
    public boolean add(Vehicle vehicle) {
        if (!vehicles.contains(vehicle)) {
            vehicles.add(vehicle);
            return true;
        }
        return false;
    }

    /**
     * Retire un véhicule de l'agence. Lève une exception si le véhicule n'existe pas dans l'agence.
     *
     * @param vehicle Le véhicule à retirer.
     * @throws UnknownVehicleException Si le véhicule n'existe pas dans l'agence.
     */
    public void remove(Vehicle vehicle) {
        if (!vehicles.contains(vehicle)) {
            throw new UnknownVehicleException(vehicle);
        }
        vehicles.remove(vehicle);
    }

    /**
     * Vérifie si un véhicule est présent dans l'agence.
     *
     * @param vehicle Le véhicule à tester.
     * @return true si le véhicule est dans l'agence, false sinon.
     */
    public boolean contains(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }

    /**
     * Retourne la liste des véhicules de l'agence.
     *
     * @return La liste des véhicules.
     */
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }
    
    // Méthodes liées aux critères
    
    /**
     * Sélectionne les véhicules de l'agence qui satisfont un critère donné.
     *
     * @param criterion Le critère à appliquer.
     * @return La liste des véhicules qui satisfont le critère.
     */
    public List<Vehicle> select(Predicate<Vehicle> criterion) {
        return vehicles.stream()
                .filter(criterion)
                .collect(Collectors.toList());
    }

    /**
     * Affiche les véhicules sélectionnés en fonction du critère donné.
     * Si aucun véhicule ne correspond au critère, un message est affiché.
     *
     * @param criterion Le critère à appliquer.
     */
    public void printSelectedVehicles(Predicate<Vehicle> criterion) {
        List<Vehicle> selectedVehicles = select(criterion);
        if (selectedVehicles.isEmpty()) {
            System.out.println("Aucun véhicule ne correspond au critère.");
        } else {
            selectedVehicles.forEach(System.out::println);
        }
    }
    
    // Gestion des locations 
    
    /**
     * Permet à un client de louer un véhicule, si celui-ci est disponible.
     *
     * @param client Le client qui souhaite louer un véhicule.
     * @param vehicle Le véhicule à louer.
     * @return Le prix de location du véhicule.
     * @throws UnknownVehicleException Si le véhicule n'existe pas dans l'agence.
     * @throws IllegalStateException Si le véhicule est déjà loué ou si le client loue déjà un autre véhicule.
     */
    public double rentVehicle(Client client, Vehicle vehicle) {
        if (!vehicles.contains(vehicle)) {
            throw new UnknownVehicleException(vehicle);
        }
        if (vehicleIsRented(vehicle)) {
            throw new IllegalStateException("Le véhicule est déjà loué : " + vehicle);
        }
        if (aVehicleIsRentedBy(client)) {
            throw new IllegalStateException(client + " loue déjà un autre véhicule.");
        }

        rentedVehicles.put(client, vehicle);
        return vehicle.dailyRentalPrice();
    }

    /**
     * Vérifie si un client loue actuellement un véhicule.
     *
     * @param client Le client à tester.
     * @return true si le client loue un véhicule, false sinon.
     */
    public boolean aVehicleIsRentedBy(Client client) {
        return rentedVehicles.containsKey(client);
    }

    /**
     * Vérifie si un véhicule est actuellement loué.
     *
     * @param vehicle Le véhicule à tester.
     * @return true si le véhicule est loué, false sinon.
     */
    public boolean vehicleIsRented(Vehicle vehicle) {
        return rentedVehicles.containsValue(vehicle);
    }

    /**
     * Permet à un client de rendre le véhicule qu'il a loué.
     *
     * @param client Le client qui rend son véhicule.
     */
    public void returnVehicle(Client client) {
        rentedVehicles.remove(client);
    }

    /**
     * Retourne la collection de véhicules actuellement loués.
     *
     * @return La collection des véhicules loués.
     */
    public Collection<Vehicle> allRentedVehicles() {
        return rentedVehicles.values();
    }
}
