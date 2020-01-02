package ie.gmit.sw;

public class Frequency {
    private  int frequency;

    public Frequency() {
        this.frequency = 1;
    }

    public int getFrequency() {
        return frequency;
    }
    public Frequency increaseFrequency(){
        frequency++;
        return  this;
    }
}
