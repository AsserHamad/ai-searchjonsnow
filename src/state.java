
public class state {
	public Map map;
//	public int whitewalkers;
//	public int daggers;
//	public int x;
//	public int y;
	
	public state(Map map) {
		this.map = map;
//		this.whitewalkers = whitewalkers;
//		this.daggers = daggers;
//		this.x = x;
//		this.y = y;
	}
	
	public String toString() {
		return "<"+this.map.ww+","+this.map.jonswords+","+this.map.jonsnowC+","+this.map.jonsnowR+">";
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
        
        return this.map.ww == state.map.ww && this.map.jonswords == state.map.jonswords 
        		&& this.map.jonsnowC == state.map.jonsnowC && this.map.jonsnowR == state.map.jonsnowR; 
    } 
}
