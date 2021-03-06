package diet;

import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;


/**
 * Facade class for the diet management.
 * It allows defining and retrieving raw materials and products.
 *
 */
public class Food {
	
	
	Collection<RawMaterial> rawMaterials = new TreeSet<RawMaterial> ();
	Collection<Product> products = new TreeSet<Product> ();
	Collection<Recipe> recipes = new TreeSet<Recipe> ();
	Collection<Menu> menus = new TreeSet<Menu> ();
			
	public void defineRawMaterial(String name, double calories, double proteins, double carbs, double fat){
		
		rawMaterials.add(new RawMaterial(name, calories, proteins, carbs, fat));
	}
	
	public Collection<NutritionalElement> rawMaterials(){
		
		Collection<NutritionalElement> tmp = new TreeSet<>(rawMaterials);
		return tmp;
	}
	
	public NutritionalElement getRawMaterial(String name){
		
		for(RawMaterial rm: rawMaterials) {
			
			if(rm.getName().compareTo(name)==0)
				return rm;
		}
		return null;
	}

	public void defineProduct(String name,  double calories, double proteins, double carbs, double fat){
		
		products.add(new Product(name, calories, proteins, carbs, fat));
	}
	
	public Collection<NutritionalElement> products(){
		
		Collection<NutritionalElement> tmp = new TreeSet<>(products);
		return tmp;
	}
	
	public NutritionalElement getProduct(String name){
		
		for(Product p: products) {
			
			if(p.getName().compareTo(name)==0)
				return (NutritionalElement)p;
		}
		return null;
	}
	
	public Recipe createRecipe(String name) {
		
		Recipe r = new Recipe(name, new LinkedList<RawMaterial>(), rawMaterials);
		
		recipes.add(r);
		return r;
	}
	
	public Collection<NutritionalElement> recipes(){
		
		Collection<NutritionalElement> tmp = new TreeSet<>(recipes);
		return tmp;
	}
	
	/**
	 * Retrieves a specific recipe, given its name
	 * 
	 * @param name  name of the recipe
	 * 
	 * @return  a recipe though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRecipe(String name){
		
		for(Recipe r: recipes) {
			
			if(r.getName().compareTo(name)==0)
				return (NutritionalElement) r;
		}
		return null;
	}
	
	public Menu createMenu(String name) {
		
		Menu m = new Menu(name, recipes, products);	
		menus.add(m);
			
		return m;
	}
	
}
