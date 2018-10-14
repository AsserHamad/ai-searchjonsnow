
public class block implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public boolean jonsnow, dragonstone, whitewalker, obstacle, empty = false;
	public block(String type) {
		switch(type) {
			case "jonsnow": jonsnow = true; break;
			case "dragonstone": dragonstone = true; break;
			case "whitewalker": whitewalker = true; break;
			case "obstacle": obstacle = true; break;
			case "empty": empty = true; break;
		}
	}
	
	public boolean isEmpty() {
		return this.empty;
	}
	
	public String toString() {
		String type = (jonsnow) ? "JS" : (dragonstone) ? "DS" : (whitewalker) ? "WW" : (obstacle) ? "OB" : "  ";
		return "[" + type + "]";
	}
}
