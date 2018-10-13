
public class state {
	public int whitewalkers;
	public int daggers;
	public int x;
	public int y;
	
	public state(int whitewalkers, int daggers, int x, int y) {
		this.whitewalkers = whitewalkers;
		this.daggers = daggers;
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "<"+this.whitewalkers+","+this.daggers+","+this.x+","+this.y+">";
	}

	@Override
    public boolean equals(Object o) {
        if (o == this) { 
            return true; 
        } 
  
        if (!(o instanceof state)) { 
            return false; 
        } 
        
        state state = (state) o; 
        
        return this.whitewalkers == state.whitewalkers && this.daggers == state.daggers && this.x == state.x && this.y == state.y; 
    } 
}
