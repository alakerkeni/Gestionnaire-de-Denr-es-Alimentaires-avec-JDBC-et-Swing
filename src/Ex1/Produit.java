package Ex1;

import java.sql.Date;

public class Produit {
    private String refProduit;
    private String descriptifProduit;
    private Date datePeremption;
    private int quantiteProduit;
    private double prixProduit;
    private Rangement refRangement;
    private Ingredient refIngredient;

    public Produit(String refProduit, String descriptifProduit, Date datePeremption, int quantiteProduit, double prixProduit, Rangement refRangement, Ingredient refIngredient) {
        this.refProduit = refProduit;
        this.descriptifProduit = descriptifProduit;
        this.datePeremption = datePeremption;
        this.quantiteProduit = quantiteProduit;
        this.prixProduit = prixProduit;
        this.refRangement = refRangement;
        this.refIngredient = refIngredient;
    }

    public String getRefProduit() {
        return refProduit;
    }

    public String getDescriptifProduit() {
        return descriptifProduit;
    }

    public Date getDatePeremption() {
        return datePeremption;
    }

    public int getQuantiteProduit() {
        return quantiteProduit;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public Rangement getRefRangement() {
        return refRangement;
    }

    public Ingredient getRefIngredient() {
        return refIngredient;
    }

    public void setRefProduit(String refProduit) {
        this.refProduit = refProduit;
    }

    public void setDescriptifProduit(String descriptifProduit) {
        this.descriptifProduit = descriptifProduit;
    }

    public void setDatePeremption(Date datePeremption) {
        this.datePeremption = datePeremption;
    }

    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }

    public void setRefRangement(Rangement refRangement) {
        this.refRangement = refRangement;
    }

    public void setRefIngredient(Ingredient refIngredient) {
        this.refIngredient = refIngredient;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "refProduit='" + refProduit + '\'' +
                ", descriptifProduit='" + descriptifProduit + '\'' +
                ", datePeremption=" + datePeremption +
                ", quantiteProduit=" + quantiteProduit +
                ", prixProduit=" + prixProduit +
                ", refRangement='" + refRangement.getRefRangement() + '\'' +
                ", refIngredient='" + refIngredient.getRefIngredient() + '\'' +
                '}';
    }

	
}
