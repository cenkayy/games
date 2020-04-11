package scene;

public class Sorry {
	public static boolean isAI=false;
	public static boolean firstRandom=true;
	public static boolean isAI() {
		return isAI;
	}

	public static void setAI(boolean isAI) {
		Sorry.isAI = isAI;
	}

	public static boolean isFirstRandom() {
		return firstRandom;
	}

	public static void setFirstRandom(boolean firstRandom) {
		Sorry.firstRandom = firstRandom;
	}
	
}
