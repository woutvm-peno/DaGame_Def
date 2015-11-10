package andreas.gps.sensoren;

/**
 * Created by root on 11/10/15.
 */
import android.util.Log;

    public class SensorActor_Gravity extends SensorActor {
        private double versnellingx =0;
        private double versnellingy=0;
        private double snelheidx=0;
        private double snelheidy=0;
        private double posx=150;
        private double posy=150;
        private float timevorige = 0;
        private float VERTRAGING = (float) 0.02; //0.03

        public double getPosx() {
            return posx;
        }

        public double getPosy() {
            return posy;
        }

        @Override
        public void Verwerk_Magn(float x, float y, float z, float timenu) {

        }

        @Override
        public void Verwerk_Accelerometer(float x, float y, float z, float timenu) {


            Log.w("Acc", String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(z) + " " + String.valueOf(timenu));
            if (timevorige != 0) {
                double deltat = (double) ((timenu - timevorige)/Math.pow(10,9));
                versnellingx = -deltat*x/VERTRAGING;
                versnellingy = deltat *y/VERTRAGING;


                snelheidx = snelheidx + versnellingx;
                snelheidy = snelheidy + versnellingy;

                posx=posx + snelheidx*deltat;
                posy=posy + snelheidy*deltat;

                Log.w("snelheid", "Vx" + String.valueOf(snelheidx) + " Vy "  + String.valueOf(snelheidy));
                Log.w("positie", "x " + String.valueOf(snelheidx)  + " y " + String.valueOf(snelheidy));

            }
            timevorige = timenu;
        }

        @Override
        public void Verwerk_Gyroscoop(float x, float y, float z, float timenu) {

        }

        @Override
        public void verwerk_Prox(float x, float timenu) {

        }

        @Override
        public void Verwerk_rot(float x, float y, float z, float timenu) {

        }

        @Override
        public void Verwerk_licht(float x, float timenu) {

        }






        public void setVersnellingx(double versnellingx) {
            this.versnellingx = versnellingx;
        }

        public void setVersnellingy(double versnellingy) {
            this.versnellingy = versnellingy;
        }

        public void setSnelheidx(double snelheidx) {
            this.snelheidx = snelheidx;
        }

        public void setSnelheidy(double snelheidy) {
            this.snelheidy = snelheidy;
        }

        public void setPosx(double posx) {
            this.posx = posx;
        }

        public void setPosy(double posy) {
            this.posy = posy;
        }

        public void setTimevorige(float timevorige) {
            this.timevorige = timevorige;
        }

        public void setVERTRAGING(float VERTRAGING) {
            this.VERTRAGING = VERTRAGING;
        }

    }

