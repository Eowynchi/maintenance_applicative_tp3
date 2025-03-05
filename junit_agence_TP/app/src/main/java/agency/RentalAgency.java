package agency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Classe repr�sentant une agence de location de v�hicules.
 * Elle permet de g�rer la liste des v�hicules disponibles � la location, 
 * ainsi que les locations effectu�es par les clients.
 */
public class RentalAgency {
    private final List<Vehicle> vehicles;
    private final Map<Client, Vehicle> rentedVehicles;

    /**
     * Cr�e une nouvelle agence sans v�hicules.
     */
    public RentalAgency() {
        this.vehicles = new ArrayList<>();
        this.rentedVehicles = new HashMap<>();
    }

    /**
     * Cr�e une nouvelle agence avec les v�hicules sp�cifi�s.
     *
     * @param vehicles La liste des v�hicules � ajouter � l'agence.
     */
    public RentalAgency(List<Vehicle> vehicles) {
        this.vehicles = new ArrayList<>(vehicles);
        this.rentedVehicles = new HashMap<>();
    }

    /**
     * Ajoute un v�hicule � l'agence s'il n'est pas d�j� pr�sent.
     *
     * @param vehicle Le v�hicule � ajouter.
     * @return true si le v�hicule a �t� ajout�, false sinon.
     */
    public boolean add(Vehicle vehicle) {
        if (!vehicles.contains(vehicle)) {
            vehicles.add(vehicle);
            return true;
        }
        return false;
    }

    /**
     * Retire un v�hicule de l'agence. L�ve une exception si le v�hicule n'existe pas dans l'agence.
     *
     * @param vehicle Le v�hicule � retirer.
     * @throws UnknownVehicleException Si le v�hicule n'existe pas dans l'agence.
     */
    public void remove(Vehicle vehicle) {
        if (!vehicles.contains(vehicle)) {
            throw new UnknownVehicleException(vehicle);
        }
        vehicles.remove(vehicle);
    }

    /**
     * V�rifie si un v�hicule est pr�sent dans l'agence.
     *
     * @param vehicle Le v�hicule � tester.
     * @return true si le v�hicule est dans l'agence, false sinon.
     */
    public boolean contains(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }

    /**
     * Retourne la liste des v�hicules de l'agence.
     *
     * @return La liste des v�hicules.
     */
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }
    
    // M�thodes li�es aux crit�res
    
    /**
     * S�lectionne les v�hicules de l'agence qui satisfont un crit�re donn�.
     *
     * @param criterion Le crit�re � appliquer.
     * @return La liste des v�hicules qui satisfont le crit�re.
     */
    public List<Vehicle> select(Predicate<Vehicle> criterion) {
        return vehicles.stream()
                .filter(criterion)
                .collect(Collectors.toList());
    }

    /**
     * Affiche les v�hicules s�lectionn�s en fonction du crit�re donn�.
     * Si aucun v�hicule ne correspond au crit�re, un message est affich�.
     *
     * @param criterion Le crit�re � appliquer.
     */
    public void printSelectedVehicles(Predicate<Vehicle> criterion) {
        List<Vehicle> selectedVehicles = select(criterion);
        if (selectedVehicles.isEmpty()) {
            System.out.println("Aucun v�hicule ne correspond au crit�re.");
        } else {
            selectedVehicles.forEach(System.out::println);
        }
    }
    
    // Gestion des locations 
    
    /**
     * Permet � un client de louer un v�hicule, si celui-ci est disponible.
     *
     * @param client Le client qui souhaite louer un v�hicule.
     * @param vehicle Le v�hicule � louer.
     * @return Le prix de location du v�hicule.
     * @throws UnknownVehicleException Si le v�hicule n'existe pas dans l'agence.
     * @throws IllegalStateException Si le v�hicule est d�j� lou� ou si le client loue d�j� un autre v�hicule.
     */
    public double rentVehicle(Client client, Vehicle vehicle) {
        if (!vehicles.contains(vehicle)) {
            throw new UnknownVehicleException(vehicle);
        }
        if (vehicleIsRented(vehicle)) {
            throw new IllegalStateException("Le v�hicule est d�j� lou� : " + vehicle);
        }
        if (aVehicleIsRentedBy(client)) {
            throw new IllegalStateException(client + " loue d�j� un autre v�hicule.");
        }

        rentedVehicles.put(client, vehicle);
        return vehicle.dailyRentalPrice();
    }

    /**
     * V�rifie si un client loue actuellement un v�hicule.
     *
     * @param client Le client � tester.
     * @return true si le client loue un v�hicule, false sinon.
     */
    public boolean aVehicleIsRentedBy(Client client) {
        return rentedVehicles.containsKey(client);
    }

    /**
     * V�rifie si un v�hicule est actuellement lou�.
     *
     * @param vehicle Le v�hicule � tester.
     * @return true si le v�hicule est lou�, false sinon.
     */
    public boolean vehicleIsRented(Vehicle vehicle) {
        return rentedVehicles.containsValue(vehicle);
    }

    /**
     * Permet � un client de rendre le v�hicule qu'il a lou�.
     *
     * @param client Le client qui rend son v�hicule.
     */
    public void returnVehicle(Client client) {
        rentedVehicles.remove(client);
    }

    /**
     * Retourne la collection de v�hicules actuellement lou�s.
     *
     * @return La collection des v�hicules lou�s.
     */
    public Collection<Vehicle> allRentedVehicles() {
        return rentedVehicles.values();
    }
}
