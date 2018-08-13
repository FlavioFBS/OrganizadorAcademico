package entidades;



public class Alumno {
    private String nombre;
    private String apellido;
    private String codigo;
    private String codiAsig;

    public Alumno(String nombre, String apellido, String codigo,String codiAsig) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigo = codigo;
        this.codiAsig=codiAsig;
    }
    
    public String getcodiAsig(){
        return codiAsig;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
    
    
    
}
