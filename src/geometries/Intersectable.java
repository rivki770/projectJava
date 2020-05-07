package geometries;
import java.util.*;

import primitives.*;

public interface Intersectable {
	
	public static class GeoPoint {

		public Geometry _geometry;
        public Point3D _point;
        
        public GeoPoint(Geometry geometry, Point3D pt) {
            this._geometry = geometry;
            this._point = pt;
        }
        
        public Point3D getPoint() {
            return  new Point3D(_point);
        }
        public Geometry getGeometry() {
            return  _geometry;
        }
        
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			GeoPoint other = (GeoPoint) obj;
			if (_geometry == null) {
				if (other._geometry != null) return false;
			} else if (!_geometry.equals(other._geometry)) return false;
			if (_point == null) {
				if (other._point != null) return false;
			} else if (!_point.equals(other._point)) return false;
			return true;
		}
		
		@Override
	    public String toString() {
	        return _point.toString();
		}
	}
	
	List<GeoPoint> findIntersections(Ray ray);

}
