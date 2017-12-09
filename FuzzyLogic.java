public class FuzzyLogic {

    private double startTemperature;
    private double[] doubles;
    private double[] interior;
    private String[] heatingPower;
    private double[] heatingPowerDouble;
    private double increasing;
    private double factor; // współczynnik ogrzewanie - kubatura

    public FuzzyLogic(double startTemp, double[] doubles, double insulation, double factor, double minTemp, double lowOpt, double highOpt) {
        this.startTemperature = startTemp;
        this.doubles = doubles;
        this.factor = factor;

        interior = new double[doubles.length];
        increasing = 0.0;
        interior[0] = startTemp;

        heatingPower = new String[doubles.length];
        heatingPower[0] = "ZERO";

        heatingPowerDouble = new double[doubles.length];
        heatingPowerDouble[0] = 0.0;

        for(int i=1; i<doubles.length; i++) {
            interior[i] = ((interior[i - 1] + increasing) - (interior[i - 1] - doubles[i - 1]) / insulation);
            if (interior[i] < minTemp || lowOpt-interior[i]>4.0*factor){
                increasing = 4.0*factor;
                heatingPower[i] = "MAX";
                heatingPowerDouble[i] = 4.0;
            }
            else if (interior[i] >= minTemp & interior[i] < (minTemp+lowOpt)/2.0 || lowOpt-interior[i]>2.5*factor){
                increasing = 2.5*factor;
                heatingPower[i] = "HIGH";
                heatingPowerDouble[i] = 3.0;
            }
            else if (interior[i] >= (minTemp+lowOpt)/2.0 & interior[i] < lowOpt || lowOpt-interior[i]>1.7*factor){
                increasing = 1.7*factor;
                heatingPower[i] = "MEDIUM";
                heatingPowerDouble[i] = 2.0;
            }
            else if (interior[i] >= lowOpt & interior[i] < highOpt || lowOpt-interior[i]>0.5*factor){
                increasing = 0.5*factor;
                heatingPower[i] = "LOW";
                heatingPowerDouble[i] = 1.0;
            }
            else if (interior[i] >= highOpt){
                increasing = 0.0*factor;
                heatingPower[i] = "ZERO";
                heatingPowerDouble[i] = 0.0;
            }
        }
    }

    public double[] getTemp(){
        return interior;
    }

    public String[] getPower(){
        return heatingPower;
    }

    public double[] getPowerDouble() { return heatingPowerDouble; }
}
