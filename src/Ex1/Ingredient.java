package Ex1;

public class Ingredient{
    private String refIngredient;
    private String nomIngredient;
    private TypeIngredient refType;

    public Ingredient(String refIngredient, String nomIngredient, TypeIngredient refType) {
        this.refIngredient = refIngredient;
        this.nomIngredient = nomIngredient;
        this.refType = refType;
    }

    public String getRefIngredient() {
		return refIngredient;
	}

	public void setRefIngredient(String refIngredient) {
		this.refIngredient = refIngredient;
	}

	public String getNomIngredient() {
		return nomIngredient;
	}

	public void setNomIngredient(String nomIngredient) {
		this.nomIngredient = nomIngredient;
	}

	public TypeIngredient getRefType() {
		return refType;
	}

	public void setRefType(TypeIngredient refType) {
		this.refType = refType;
	}

	public String toString() {
        return "Ingredient{" +
                "refIngredient='" + refIngredient + '\'' +
                ", nomIngredient='" + nomIngredient + '\'' +
                ", refType='" + refType.getRefType() + '\'' +
                '}';
    }

	
}
