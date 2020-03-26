package primitives;


/**
 * Vector: class for representing vector in environment
 */

public class Vector {
    Point3D _head;
    //public final static Vector ZERO = new Vector(Point3D.ZERO);

    /*************** Constructor ********************/
    /**
     *
     * @param x coordinate on the x axis
     * @param y coordinate on the y axis
     * @param z coordinate on the z axis
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        Point3D PHelp = new Point3D(x.get(), y.get(), z.get());
        if (PHelp.equals(Point3D.ZERO)){
            throw new IllegalArgumentException("ZERO point not valid for vector");
        }
        this._head = PHelp;
    }

    /**
     *
     * @param x number of x axis
     * @param y number of y axis
     * @param z number of z axis
     */
    public Vector(double x, double y, double z) {
        Point3D PHelp = new Point3D(x, y, z);
        if (PHelp.equals(Point3D.ZERO)){
            throw new IllegalArgumentException("ZERO point not valid for vector");
        }
        this._head = PHelp;
    }

    /**
     *
     * @param _head head's point of vector
     */
    public Vector(Point3D _head) {
        if (_head.equals(Point3D.ZERO)){
            throw new IllegalArgumentException("ZERO point not valid for vector");
        }
        this._head = _head;
    }

    /**
     *
     * @param other vector for copy constructor
     */
    public Vector(Vector other) {
        this._head = other._head;
    }

    /*************** getters ********************/
    /**
     *
     * @return head's point of vector
     */
    public Point3D get_head() {
        return new Point3D(_head);
    }

    /*************** toString() and equals() ********************/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    @Override
    public String toString() {
        return "Vector = " +
                _head.toString();
    }
    
    /**
     *
     * @param other vector
     * @return vector from result of subtract vectors
     */
    public Vector subtract(Vector other) {
    	return new Vector(this.add(other.scale(-1)));
    }

    /**
     *
     * @param other vector
     * @return vector from result of add vectors
     */
    public Vector add(Vector other) {
        return new Vector(other._head._x.get() + this._head._x.get(),
                other._head._y.get() + this._head._y.get(),
                other._head._z.get() + this._head._z.get());
    }

    /**
     *
     * @param scale number
     * @return new vector from result of multiply the number
     */
    public Vector scale(double scale) {
        return new Vector(scale * this._head._x.get(), scale * this._head._y.get(), scale * this._head._z.get());
    }

    /**
     *
     * @param other vector
     * @return dotProduct (double)
     */
    public double dotProduct(Vector other) {
        return this._head._x.get() * other._head._x.get() +
                this._head._y.get() * other._head._y.get() +
                this._head._z.get() * other._head._z.get();
    }

    /**
     *
     * @param other vector
     * @return vector for crossProduct using right thumb rule
     */
    public Vector crossProduct(Vector other) {
        return new Vector(this._head._y.get() * other._head._z.get() - this._head._z.get() * other._head._y.get(),
                this._head._z.get() * other._head._x.get() - this._head._x.get() * other._head._z.get(),
                this._head._x.get() * other._head._y.get() - this._head._y.get() * other._head._x.get());
    }

    /**
     *
     * @return lengthSquared of vector
     */
    public double lengthSquared(){
        return this._head._x.get() * this._head._x.get() +
                this._head._y.get() * this._head._y.get() +
                this._head._z.get() * this._head._z.get();
    }

    /**
     *
     * @return length of vector
     */
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }

    /**
     *
     * @return normalize vector
     */
    public Vector normalize(){
        double len = this.length();
        if (len == 0)
            throw new ArithmeticException("divide by Zero");
        this._head._x = new Coordinate(this._head._x.get() / len);
        this._head._y = new Coordinate(this._head._y.get() / len);
        this._head._z = new Coordinate(this._head._z.get() / len);
        return this;
    }

    /**
     *
     * @return copy normalize vector
     */
    public Vector normalized(){
        return new Vector(this.normalize());
    }
}
