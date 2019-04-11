public class player {

		private String name;
		private int points;
		
		public player(String playerName)
		{
			name = playerName;
			points = 0;
		}
		
		public void resetPlayer()
		{
			points = 0;
		}
		
		public String getName()
		{
			return name;
		}
		
		public int getPoints()
		{
			return points;
		}
		
		public void addPoints(int numAdded)
		{
			points += numAdded;
		}
		
}
