
public class player {

		public static String name;
		public static int points;
		
		public player(String playerName)
		{
			name = playerName;
			points = 0;
		}
		
		public static void resetPlayer()
		{
			points = 0;
		}
		
		public static String getName()
		{
			return name;
		}
		
		public static int getPoints()
		{
			return points;
		}
		
		public static void addPoints(int numAdded)
		{
			points = points + numAdded;
		}
}
