package agency;

/**
 * Exception lev�e lorsqu'un v�hicule n'existe pas dans l'agence.
 * Cette exception est utilis�e pour signaler qu'un v�hicule donn�
 * n'est pas pr�sent dans la liste des v�hicules de l'agence.
 */
public class UnknownVehicleException extends RuntimeException {
    private static final long serialVersionUID = 1L;  

    private final Vehicle vehicle;

    /**
     * Cr�e une nouvelle exception pour le v�hicule sp�cifi�.
     *
     * @param vehicle Le v�hicule qui n'existe pas dans l'agence.
     */
    public UnknownVehicleException(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Retourne le message d'erreur d�taillant le v�hicule qui est introuvable.
     *
     * @return Le message d'erreur avec les informations du v�hicule.
     */
    @Override
    public String getMessage() {
        return "Le v�hicule suivant n'existe pas dans l'agence : " + vehicle.toString();
    }

    /**
     * Retourne le v�hicule qui a caus� l'exception.
     *
     * @return Le v�hicule introuvable.
     */
    public Vehicle getVehicle() {
        return this.vehicle;
    }
}
