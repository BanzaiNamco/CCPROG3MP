package proto;

public class Tile {
    private boolean isPlowed;
    private boolean hasRock;
    private boolean hasCrop;

    
    public Tile(){
        
    }
    public boolean getIsPlowed() {
        return this.isPlowed;
    }
    public void setIsPlowed(boolean isPlowed) {
        this.isPlowed = isPlowed;
    }
    public boolean getHasRock() {
        return this.hasRock;
    }
    public void setHasRock(boolean hasRock) {
        this.hasRock = hasRock;
    }
    public boolean getHasCrop() {
        return this.hasCrop;
    }
    public void setHasCrop(boolean hasCrop) {
        this.hasCrop = hasCrop;
    }
}
