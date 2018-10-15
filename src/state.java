
public class state {
	public Map map;
	
	public state(Map map) {
		this.map = map;
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
