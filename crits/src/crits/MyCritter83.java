package assignment4;
import assignment4.Critter.TestCritter;

public class MyCritter83 {
	/*
	 * MyCritter83 runs in a random direction if it has energy greater than 83 and walks otherwise
	 * Always wants to fight
	 */
	public class MyCritter83 extends TestCritter {
		
		@Override
		public void doTimeStep() {
			if(this.getEnergy() > 83)
				run(getRandomInt(8));
			else walk(getRandomInt(8));
		}

		@Override
		public boolean fight(String opponent) {
			
			return true;
		}

		@Override
		public String toString () {
			return "%";
		}
}
