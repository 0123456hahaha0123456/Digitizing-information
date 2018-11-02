public class Plate{
    private String kind;
    private shapePlate shape;
    public Plate(String kind,shapePlate shape){
        this.kind = kind;
        this.shape = shape;
    }
    public String getKind(){
        return this.kind;
    }

    public shapePlate getShape(){
        return this.shape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plate plate = (Plate) o;

        if (kind != null ? !kind.equals(plate.kind) : plate.kind != null) return false;
        return shape == plate.shape;
    }

    @Override
    public int hashCode() {
        int result = kind != null ? kind.hashCode() : 0;
        result = 31 * result + (shape != null ? shape.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Plate{" +
                "kind='" + kind + '\'' +
                ", shape=" + shape +
                '}';
    }
}
