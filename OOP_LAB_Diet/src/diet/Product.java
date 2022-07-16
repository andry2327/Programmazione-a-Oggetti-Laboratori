package diet;

public class Product implements NutritionalElement, Comparable<Product>{

// raw materials 
		private String name;
		private double calories; // (per 100g)
		private double proteins;
		private double carbs;
		private double fat;
		
		public Product (String name, double calories, double proteins, double carbs, double fat) {
				
			this.name = name;
			this.calories = calories;
			this.proteins = proteins;
			this.carbs = carbs;
			this.fat = fat;
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
		public int compareTo(Product p) {
				
			int cmp = (p.getName().compareTo(name));
				
			if(cmp < 0)
				return 1;
			else if(cmp > 0)
				return -1;
			return 0;
		}
		
		public Product clone() {
			return new Product(this.name, this.calories, this.proteins, this.carbs, this.fat);
		}
			
}
