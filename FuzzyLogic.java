public class FuzzyLogic {

    private double[] interior; // tablica temperatur pomieszczenia
    private String[] heatingPower; // wartości mocy pieca
    private double[] heatingPowerDouble; // moc pieca
    private double increasing; // temperatura zwiększana

    /**
     * temperatura zwiększana to wartość w stopniach celsjusza o ile zwiększa się temp. pomieszczenia w jednej jednostce czasu.
     * wartosc mocy pieca przyjmuje wartosci ZERO - 0.0 LOW - 1.0 MEDIUM - 2.0 HIGH - 3.0 MAX - 4.0
     *
     */

    public FuzzyLogic(double startTemp, double[] doubles, double insulation, double factor, double minTemp, double lowOpt, double highOpt) {

        interior = new double[doubles.length];
        increasing = 0.0;
        interior[0] = startTemp;

        heatingPower = new String[doubles.length];
        heatingPower[0] = "ZERO";

        heatingPowerDouble = new double[doubles.length];
        heatingPowerDouble[0] = 0.0;

        /* LOGIKA */

        /**
         * Jeżeli:
         * (temp. pomieszczenia + temp. zwiększona poprzez ogrzewanie) - (temp. pomieszczenia - temp. zewnętrzna) / współ. izolacji)
         * to w zależności od wyniku ustaw moc pieca (MAX, HIGH, MEDIUM, LOW, ZERO).
         */

        // JEŻELI
        for(int i=1; i<doubles.length; i++) {
            interior[i] = ((interior[i - 1] + increasing) - ((interior[i - 1] - doubles[i - 1]) / insulation));
            // (temperatura z godziny - 1 + wartosc o ile zwiekszono temperature) - (temp. z godziny przed - ( temperatura zew
            if (interior[i] < minTemp || lowOpt-interior[i]>4.0*factor){
                increasing = 4.0*factor;
                heatingPower[i] = "MAX";
                heatingPowerDouble[i] = 4.0;
            }
            // JEŻELI TEMPERATURA POMIESZCZENIA JEST WIĘKSZA NIZ NAJWYŻSZA OPTYMALNA TO USTAW MOC PIECA NA ZERO
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
            // JEŻELI TEMPERATURA POMIESZCZENIA JEST WIĘKSZA NIZ NAJWYŻSZA OPTYMALNA TO USTAW MOC PIECA NA LOW
            else if (interior[i] >= lowOpt & interior[i] < highOpt || lowOpt-interior[i]>0.5*factor){
                increasing = 0.5*factor;
                heatingPower[i] = "LOW";
                heatingPowerDouble[i] = 1.0;
            }
            // JEŻELI TEMPERATURA POMIESZCZENIA JEST WIĘKSZA NIZ MINIMALNA OPTYMALNA ALE MNIEJSZA NAJWYŻSZA OPTYMALNA TO USTAW MOC PIECA NA ZERO
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

    //public double[] getPowerDouble() { return heatingPowerDouble; }
}
