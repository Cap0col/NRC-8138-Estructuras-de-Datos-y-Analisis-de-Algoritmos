import java.util.*;

class Empleado {
    int id;
    String nombre;
    String apellido;
    String genero;
    double salario;

    public Empleado(int id, String nombre, String apellido, String genero, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return id + " | " + nombre + " " + apellido + " | " + genero + " | $" + salario;
    }
}

class SistemaEmpleados {

    static List<Empleado> empleados = new ArrayList<>();

    public static void main(String[] args) {

        cargarDatosIniciales();

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n========== MENÚ DE EMPLEADOS ==========");
            System.out.println("1. Ver lista de empleados (ordenada por sueldo)");
            System.out.println("2. Agregar nuevo empleado");
            System.out.println("3. Búsqueda lineal por nombre");
            System.out.println("4. Búsqueda binaria por sueldo");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> mostrarLista();
                case 2 -> agregarEmpleado(sc);
                case 3 -> busquedaLineal(sc);
                case 4 -> busquedaBinaria(sc);
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 5);
    }

    // ============================================================
    // DATOS INICIALES
    // ============================================================
    static void cargarDatosIniciales() {
        empleados.add(new Empleado(1, "Carlos Alberto", "Martínez Rodríguez", "M", 2952670));
        empleados.add(new Empleado(2, "Teresa", "Nicolás Otaño", "F", 2338617));
        empleados.add(new Empleado(3, "Rosa Alquidia", "Lázala Polanco", "F", 2355228));
        empleados.add(new Empleado(4, "Ivellisse", "Estévez Ventura", "M", 1801728));
        empleados.add(new Empleado(5, "Margarita", "Núñez de la Cruz", "F", 3124269));
        empleados.add(new Empleado(6, "Belkis Josefina", "Hernández", "F", 2975450));
        empleados.add(new Empleado(7, "Ruth", "Ortega", "F", 2306902));
        empleados.add(new Empleado(8, "Lourdes", "Florentino de la Cruz", "F", 2583729));
        empleados.add(new Empleado(9, "Luisa", "Mejía", "F", 2160378));
        empleados.add(new Empleado(10, "Cándido Mercedes", "Vargas", "M", 1961114));
    }

    // ============================================================
    // MOSTRAR LISTA
    // ============================================================
    static void mostrarLista() {
        System.out.println("\n------ LISTA ORDENADA POR SUELDO ------");

        empleados.sort(Comparator.comparingDouble(emp -> emp.salario));

        for (Empleado e : empleados) {
            System.out.println(e);
        }
    }

    // ============================================================
    // AGREGAR EMPLEADO
    // ============================================================
    static void agregarEmpleado(Scanner sc) {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Género (M/F): ");
        String genero = sc.nextLine();

        System.out.print("Salario: ");
        double salario = sc.nextDouble();

        empleados.add(new Empleado(id, nombre, apellido, genero, salario));

        System.out.println("Empleado agregado correctamente.");
    }

    // ============================================================
    // BÚSQUEDA LINEAL
    // ============================================================
    static void busquedaLineal(Scanner sc) {
        System.out.print("Ingrese nombre a buscar: ");
        String nombre = sc.nextLine().toLowerCase();

        for (Empleado e : empleados) {
            if (e.nombre.toLowerCase().contains(nombre)) {
                System.out.println("Encontrado: " + e);
                return;
            }
        }

        System.out.println("No se encontró ningún empleado con ese nombre.");
    }

    // ============================================================
    // BÚSQUEDA BINARIA POR SUELDO
    // ============================================================
    static void busquedaBinaria(Scanner sc) {
        empleados.sort(Comparator.comparingDouble(emp -> emp.salario));

        System.out.print("Ingrese salario a buscar: ");
        double objetivo = sc.nextDouble();

        int inicio = 0, fin = empleados.size() - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            double sueldoMedio = empleados.get(medio).salario;

            if (sueldoMedio == objetivo) {
                System.out.println("Empleado encontrado: " + empleados.get(medio));
                return;
            }

            if (sueldoMedio < objetivo) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        System.out.println("No se encontró ningún empleado con ese salario.");
    }
}