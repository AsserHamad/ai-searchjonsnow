
public class block {
	public boolean jonsnow, dragonstone, whitewalker, empty = false;
	public block(String type) {
		switch(type) {
			case "jonsnow": jonsnow = true; break;
			case "dragonstone": dragonstone = true; break;
			case "whitewalker": whitewalker = true; break;
			case "empty": empty = true; break;
		}
	}
	
	public boolean isEmpty() {
		return this.empty;
	}
	
	public String toString() {
		String type = (jonsnow) ? "JS" : (dragonstone) ? "DS" : (whitewalker) ? "WW" : "  ";
		return "[" + type + "]";
	}
}
