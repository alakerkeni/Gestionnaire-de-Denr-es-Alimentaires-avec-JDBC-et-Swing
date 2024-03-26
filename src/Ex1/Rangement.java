package Ex1;

public class Rangement {
    private String refRangement;
    private String nomRangement;

    public Rangement(String refRangement, String nomRangement) {
        this.refRangement = refRangement;
        this.nomRangement = nomRangement;
        
    }

    public String getRefRangement() {
		return refRangement;
	}

	public void setRefRangement(String refRangement) {
		this.refRangement = refRangement;
	}

	public String getNomRangement() {
		return nomRangement;
	}

	public void setNomRangement(String nomRangement) {
		this.nomRangement = nomRangement;
	}

	public String toString() {
        return "Rangement{" +
                "refRangement='" + refRangement + '\'' +
                ", nomRangement='" + nomRangement + '\'' +
                '}';
    }

	
}
