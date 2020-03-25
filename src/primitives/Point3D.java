package primitives;


/**
 * Point3D: class for representing a point in 3D environment
 */
public class Point3D {
    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    /*************** Constructor ********************/
    /**
     * Constructor for creating a point
     *
     * @param _x coordinate on the x axis
     * @param _y coordinate on the y axis
     * @param _z coordinate on the z axis
     */
    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        this._x = _x;
        this._y = _y;
        this._z = _z;
    }

    public Point3D(double _x, double _y, double _z) {
        this(new Coordinate(_x), new Coordinate(_y), new Coordinate(_z));
    }

    public Point3D(Point3D point) {
        this._x = point._x;
        this._y = point._y;
        this._z = point._z;
    }

    public final static Point3D ZERO = new Point3D( 0.0, 0.0, 0.0);

    /*************** getters and setters ********************/
    /**
     *
     * @return new Coordinate with _x value
     */
    public Coordinate get_x() {
        return new Coordinate(_x);
    }

    public Coordinate get_y() {
        return new Coordinate(_y);
    }

    public Coordinate get_z() {
        return new Coordinate(_z);
    }

    /*************** toString() and equals() ********************/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) &&
                _y.equals(point3D._y) &&
                _z.equals(point3D._z);
    }

    @Override
    public String toString() {
        return "(" +
                _x.toString() +
                ", " + _y.toString() +
                ", " + _z.toString() +
                ")";
    }

    /**
     *
     * @param other other vector
     * @return vector from result of subtract vector and point
     */
    public Vector subtract(Point3D other) {
        return new Vector(new Point3D(other._x.get() - this._x.get(), other._y.get() - this._y.get(), other._z.get() - this._z.get()));
    }

    /**
     *
     * @param other other vector
     * @return vector from result of add vectors
     */
    public Point3D add(Vector other){
        return new Point3D(other._head._x.get() + this._x.get(), other._head._y.get() + this._y.get(), other._head._z.get() + this._z.get());
    }

    /**
     *
     * @param other other point
     * @return Scalar of distance between 2 points squared
     */
    public double distanceSquared(Point3D other){
        return (other._x.get() - this._x.get()) * (other._x.get() - this._x.get()) +
                (other._y.get() - this._y.get()) * (other._y.get() - this._y.get()) +
                (other._z.get() - this._z.get()) * (other._z.get() - this._z.get());
    }

    /**
     *
     * @param other other point
     * @return Scalar of distance between 2 points
     */
    public double distance(Point3D other){
        return Math.sqrt(this.distanceSquared(other));
    }

}