package Ex1;

public class Composition {
    private int refComposition;
    private double quantiteComposition;
    private Recette refRecette;
    private Ingredient refIngredient;

    public Composition(int refComposition, double quantiteComposition, Recette refRecette, Ingredient refIngredient) {
        this.refComposition = refComposition;
        this.quantiteComposition = quantiteComposition;
        this.refRecette = refRecette;
        this.refIngredient = refIngredient;
    }

    public int getRefComposition() {
        return refComposition;
    }

    public void setRefComposition(int refComposition) {
        this.refComposition = refComposition;
    }

    public double getQuantiteComposition() {
        return quantiteComposition;
    }

    public void setQuantiteComposition(double quantiteComposition) {
        this.quantiteComposition = quantiteComposition;
    }

    public Recette getRefRecette() {
        return refRecette;
    }

    public void setRefRecette(Recette refRecette) {
        this.refRecette = refRecette;
    }

    public Ingredient getRefIngredient() {
        return refIngredient;
    }

    public void setRefIngredient(Ingredient refIngredient) {
        this.refIngredient = refIngredient;
    }

    public String toString() {
        return "Composition{" +
                "refComposition=" + refComposition +
                ", quantiteComposition=" + quantiteComposition +
                ", refRecette=" + refRecette.getRefRecette() +
                ", refIngredient=" + refIngredient.getRefIngredient() +
                '}';
    }

	
}
