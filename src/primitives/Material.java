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
    private double _kR;
    private double _kT;
    private int _nShininess;
    
    
    /*************** Constructor ********************/
    /**
    *
    * @param _kD
    * @param _kS
    * @param _nShininess
    * @param _kT
    * @param _kR 
    */
   public Material(double _kD, double _kS, int _nShininess, double _kT, double _kR) {
       this._kD = _kD;
       this._kS = _kS;
       this._kR = _kR;
       this._kT = _kT;
       this._nShininess = _nShininess;
   }
    
    /**
     *
     * @param _kD
     * @param _kS
     * @param _nShininess
     */
    public Material(double _kD, double _kS, int _nShininess) {
    	this(_kD, _kS,  _nShininess, 0, 0);
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
    * @return _kR
    */
    public double getkR() {
        return _kR;
    }
    
    /**
    *
    * @return _kT
    */
    public double getkT() {
        return _kT;
    }

    /**
    *
    * @return _nShininess
    */
    public int getnShininess() {
        return _nShininess;
    }

}
