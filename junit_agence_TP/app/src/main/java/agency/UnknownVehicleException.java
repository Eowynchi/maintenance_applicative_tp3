package agency;

/**
 * Exception levée lorsqu'un véhicule n'existe pas dans l'agence.
 * Cette exception est utilisée pour signaler qu'un véhicule donné
 * n'est pas présent dans la liste des véhicules de l'agence.
 */
public class UnknownVehicleException extends RuntimeException {
    private static final long serialVersionUID = 1L;  

    private final Vehicle vehicle;

    /**
     * Crée une nouvelle exception pour le véhicule spécifié.
     *
     * @param vehicle Le véhicule qui n'existe pas dans l'agence.
     */
    public UnknownVehicleException(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Retourne le message d'erreur détaillant le véhicule qui est introuvable.
     *
     * @return Le message d'erreur avec les informations du véhicule.
     */
    @Override
    public String getMessage() {
        return "Le véhicule suivant n'existe pas dans l'agence : " + vehicle.toString();
    }

    /**
     * Retourne le véhicule qui a causé l'exception.
     *
     * @return Le véhicule introuvable.
     */
    public Vehicle getVehicle() {
        return this.vehicle;
    }
}
