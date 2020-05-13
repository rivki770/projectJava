/**
 * 
 */
package primitives;

/**
 * @author rivki_kanterovich
 *
 */
public class Material {
	
    private double _kD;
    private double _kS;
    private int _nShininess;
    
    /*************** Constructor ********************/
    /**
     *
     * @param _kD
     * @param _kS
     * @param _nShininess
     */
    public Material(double _kD, double _kS, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
    }

    /*************** getters ********************/
    /**
     *
     * @return _kD
     */
    public double getkD() {
        return _kD;
    }

    /**
    *
    * @return _kS
    */
    public double getkS() {
        return _kS;
    }

    /**
    *
    * @return _nShininess
    */
    public int getnShininess() {
        return _nShininess;
    }

}
