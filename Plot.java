package MCO;

public class Plot{
    private boolean isPlowed;
    private boolean hasRock;
    private boolean isWithered;
    private boolean isCropPresent;

    public Plot(){
        this.isPlowed = false;
        this.hasRock = false;
        this.isWithered = false;
        this.isCropPresent = false;
    }

    public void setIsPlowed(boolean a){
        this.isPlowed = a;
    }
    public void setIsWithered(boolean a){
        this.isWithered = a;
    }
    public void setIsCropPresent(boolean a){
        this.isCropPresent = a;
    }
    public void removeWither(boolean a){
        this.setIsWithered(a);
        this.setIsCropPresent(a);
    }



    public boolean getIsPlowed(){
        return this.isPlowed;
    }
    public boolean getHasRock(){
        return this.hasRock;
    }
    public boolean getIsWithered(){
        return this.isWithered;
    }
    public boolean getIsCropPresent(){
        return this.isCropPresent;
    }
}