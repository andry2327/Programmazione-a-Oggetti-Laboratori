package diet;

public class RawMaterial implements NutritionalElement, Comparable<RawMaterial>{

	// raw materials 
		private String name;
		private double calories; // (per 100g)
		private double proteins;
		private double carbs;
		private double fat;
		private double quantity;
		
		public RawMaterial (String name, double calories, double proteins, double carbs, double fat) {
			
			this.name = name;
			this.calories = calories;
			this.proteins = proteins;
			this.carbs = carbs;
			this.fat = fat;
		}
		
		public RawMaterial (String name, double quantity) { // costruttore per Recipe
			
			this.name = name;
			this.quantity = quantity;
		}

		@Override
		public String getName() {
			return this.name;
		}

		@Override
		public double getCalories() {
			return this.calories;
		}

		@Override
		public double getProteins() {
			return this.proteins;
		}

		public void setQuantity(double quantity) {
			this.quantity = quantity;
		}

		public double getQuantity() {
			return quantity;
		}

		@Override
		public double getCarbs() {
			return this.carbs;
		}

		@Override
		public double getFat() {
			return this.fat;
		}

		@Override
		public boolean per100g() {
			return false;
		}

		@Override
		public int compareTo(RawMaterial rm) { // definisco come comparare gli oggetti RawMaterial
			
			int cmp = (rm.getName().compareTo(name));
			
			if(cmp < 0)
				return 1;
			else if(cmp > 0)
				return -1;
			return 0;
		}
		
		public RawMaterial clone() {
			return new RawMaterial(this.name, this.calories, this.proteins, this.carbs, this.fat);
		}
		
		
}
