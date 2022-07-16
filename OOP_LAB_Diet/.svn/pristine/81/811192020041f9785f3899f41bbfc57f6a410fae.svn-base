package diet;

import java.util.Collection;
import java.util.TreeSet;

/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement, Comparable<Menu>{
	
	private String name;
	Collection<Recipe> menuRecipes;
	Collection<Product> menuProducts;
	Collection<Recipe> menuRecipesList;
	Collection<Product> menuProductsList;
	
	
	public Menu(String name, Collection<Recipe> recipesList, Collection<Product> productsList) {
		
		this.name = name;
		menuRecipes = new TreeSet<Recipe> ();
		this.menuRecipesList = recipesList;
		menuProducts = new TreeSet<Product> ();
		this.menuProductsList = productsList;
	}
	/**
	 * Adds a given serving size of a recipe.
	 * 
	 * The recipe is a name of a recipe defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 * @return the same Menu to allow method chaining
	 */
	public Menu addRecipe(String recipe, double quantity) {
		
		Recipe tmpR;
		
		for(Recipe r: menuRecipesList) {
			if(r.getName().compareTo(recipe)==0) {
				tmpR = r.clone();
				tmpR.setQuantity(quantity);
				menuRecipes.add(tmpR);
				break;
			}
		}
		return this;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param product the name of the product to be used as ingredient
	 * @return the same Menu to allow method chaining
	 */
	public Menu addProduct(String product) {
		
		Product tmpP;
		
		for(Product p: menuProductsList) {
			if(p.getName().compareTo(product)==0) {
				tmpP = ((Product) p).clone();
				menuProducts.add(tmpP);
				break;
			}
		}
		return this;
	}

	/**
	 * Name of the menu
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		
		double totKcal=0;
		
		for(Product p: menuProducts) {
			totKcal += p.getCalories();
		}
		for(Recipe r: menuRecipes) {
			totKcal += r.getCalories()*(r.getQuantity()/100); // totKcal = (Kcal/100g) * totGr * 100
		}
		return totKcal;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		
		double totProteins=0;
		
		for(Product p: menuProducts) {
			totProteins += p.getProteins();
		}
		for(Recipe r: menuRecipes) {
			totProteins += r.getProteins()*(r.getQuantity()/100); // totKcal = (Kcal/100g) * totGr * 100
		}
		return totProteins;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {

		double totCarbs=0;
		
		for(Product p: menuProducts) {
			totCarbs += p.getCarbs();
		}
		for(Recipe r: menuRecipes) {
			totCarbs += r.getCarbs()*(r.getQuantity()/100); // totKcal = (Kcal/100g) * totGr * 100
		}
		return totCarbs;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {

		double totFat=0;
		
		for(Product p: menuProducts) {
			totFat += p.getFat();
		}
		for(Recipe r: menuRecipes) {
			totFat += r.getFat()*(r.getQuantity()/100); // totKcal = (Kcal/100g) * totGr * 100
		}
		return totFat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean 	indicator
	 */
	@Override
	public boolean per100g() {
		// nutritional values are provided for the whole menu.
		return false;
	}
	
	@Override
	public int compareTo(Menu o) {
		
		int cmp = (o.getName().compareTo(name));
		
		if(cmp < 0)
			return 1;
		else if(cmp > 0)
			return -1;
		return 0;
	}
}
