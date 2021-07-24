package algorithm;

public enum Food {
	CAKE(5000, 700L) {
		double getSatisfaction(int hunger) {
			return getCalories() - hunger - getCost();
		}
	},
	PIZZA(20000, 2000L) {
		double getSatisfaction(int hunger) {
			return getCalories() - hunger - getCost();
		}
	},
	COOKIE(1500, 200L) {
		double getSatisfaction(int hunger) {
			return getCalories() - hunger - getCost();
		}
	};

	private final int cost;
	private final double calories;

	Food(int cost, double calories){
		this.cost = cost;
		this.calories = calories;
	}

	abstract double getSatisfaction(int hunger);

	public int getCost(){
		return cost;
	}

	public double getCalories() {
		return calories;
	}
}