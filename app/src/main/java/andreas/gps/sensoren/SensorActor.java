package andreas.gps.sensoren;

/**
 * Created by root on 11/10/15.
 */
public abstract class SensorActor {
    public abstract void Verwerk_Magn(float x, float y, float z,float timenu);
    public abstract void Verwerk_Accelerometer(float x, float y, float z, float timenu);
    public abstract void Verwerk_Gyroscoop(float x, float y, float z,float timenu);
    public abstract void verwerk_Prox(float x,float timenu) ;
    public abstract void Verwerk_rot(float x, float y, float z,float timenu) ;
    public abstract void Verwerk_licht(float x,float timenu) ;
}
