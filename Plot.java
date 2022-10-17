package proto;

public class Plot {
    private boolean isPlowed;
    private boolean hasRock;
    private boolean hasCrop;

    
    public Plot(){
        
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
