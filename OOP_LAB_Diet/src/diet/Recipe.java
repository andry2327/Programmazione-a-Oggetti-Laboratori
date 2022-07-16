package diet;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement, Comparable<Recipe> {
    
	
	private String name;
	private double quantity;
	Collection<RawMaterial> ingredients;
	Collection<RawMaterial> ingredientsList;
	
	public Recipe(String nome, Collection<RawMaterial> ingredients, Collection<RawMaterial> ingredientsList) {
		
		this.name = nome;
		//this.ingredients = new LinkedList<RawMaterial>(); // devo preservare l'ordine con cui li aggiungo
		this.ingredients = ingredients;
		this.ingredientsList = ingredientsList ;
	}
	/**
	 * Adds a given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	public Recipe addIngredient(String material, double quantity) {
		
		RawMaterial tmpRm;
		
		for(NutritionalElement rm: ingredientsList) {
			if(rm.getName().compareTo(material)==0) {
				tmpRm = ((RawMaterial) rm).clone();
				tmpRm.setQuantity(quantity);
				ingredients.add(tmpRm);
				break;
			}
		}
		return this;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getCalories() { // sono già espresse per 100 g
		
		double sum=0;
		double totGr=0;
		
		for(RawMaterial r: ingredients) { 	
			sum += (r.getCalories()*r.getQuantity())/100;
			totGr += r.getQuantity();
		}
		
		return (sum/totGr)*100;
	}

	@Override
	public double getProteins() {
		
		double sum=0;
		double totGr=0;
		
		for(RawMaterial r: ingredients) { 	
			sum += (r.getProteins()*r.getQuantity())/100;
			totGr += r.getQuantity();
		}
		
		return (sum/totGr)*100;
	}

	@Override
	public double getCarbs() {

		double sum=0;
		double totGr=0;
		
		for(RawMaterial r: ingredients) { 	
			sum += (r.getCarbs()*r.getQuantity())/100;
			totGr += r.getQuantity();
		}
		
		return (sum/totGr)*100;
	}

	@Override
	public double getFat() {

		double sum=0;
		double totGr=0;
		
		for(RawMaterial r: ingredients) { 	
			sum += (r.getFat()*r.getQuantity())/100;
			totGr += r.getQuantity();
		}
		
		return (sum/totGr)*100;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expresses nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return true;
	}
	
	
	/**
	 * Returns the ingredients composing the recipe.
	 * 
	 * A string that contains all the ingredients, one per per line, 
	 * using the following format:
	 * {@code "Material : ###.#"} where <i>Material</i> is the name of the 
	 * raw material and <i>###.#</i> is the relative quantity. 
	 * 
	 * Lines are all terminated with character {@code '\n'} and the ingredients 
	 * must appear in the same order they have been added to the recipe.
	 */
	@Override
	public String toString() {
		
		String ing = new String();
		
		for(RawMaterial r: ingredients) 
			ing.concat(r.getName() + " : " + r.getQuantity() + "\n");
			
		return ing;
	}
	
	@Override
	public int compareTo(Recipe o) {
		
		int cmp = (o.getName().compareTo(name));
		
		if(cmp < 0)
			return 1;
		else if(cmp > 0)
			return -1;
		return 0;
	}
	public double getQuantity() {
		return quantity;
	}
	
	public Recipe clone() {
		return new Recipe(this.name, this.ingredients, this.ingredientsList);
	}
	
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
}
