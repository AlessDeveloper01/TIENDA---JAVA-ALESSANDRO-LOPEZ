import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        mostrarMenu();
    }

    public static String[] cargarProductos() {
        try {
            String routeFile = "tienda.txt";
            File newArchive = new File(routeFile);
            FileReader writeArchive = new FileReader(newArchive);
            BufferedReader outputArchive = new BufferedReader(writeArchive);

            String str;
            while ((str = outputArchive.readLine()) != null) {
                String[] data = str.split(";");
                String name = data[0];
                double price = Double.parseDouble(data[1]);
                int quantity = Integer.parseInt(data[2]);
            }

            outputArchive.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BOLD = "\033[1m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void mostrarMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println(ANSI_RED + ANSI_BOLD + "Tienda De Alessandro - La Compra Perfecta" + ANSI_RESET);
        System.out.println(ANSI_GREEN);
        System.out.println("1. Añadir Producto");
        System.out.println("2. Eliminar Producto o Productos");
        System.out.println("3. Vender Producto o Productos");
        System.out.println("4. Calcular cuenta de la persona");
        System.out.println("5. Comprar Producto o Productos");
        System.out.println("6. Mostrar Producto por Nombre");
        System.out.println("7. Mostrar Producto por Precio (< o >)");
        System.out.println("8. Mostrar stock de Productos (menor a 5)");
        System.out.println("9. Calcular la venta total de la tienda hasta el momento");
        System.out.println("10. Salir");
        System.out.println("Ingrese una opción: ");
        System.out.println(ANSI_RESET);
        int option = input.nextInt();

        switch (option) {
            case 1:
                añadirProducto();
                break;
            case 2:
                eliminar();
                break;
            case 3:
                vender();
                break;
            case 4:
                facturaCuenta();
                break;
            case 5:
                comprar();
                break;
            case 6:
                mostrarProductoPorNombre();
                break;
            case 7:
                mostrarProductoPorPrecio();
                break;
            case 8:
                mostrarStock();
                break;
            case 9:
                calcularVentaTotal();
                break;
            case 10:
                System.out.println(ANSI_BLUE + ANSI_BOLD + "Gracias por usar el programa - By Alessandro Rios Lopez Github: @AlessDeveloper01" + ANSI_RESET);
                break;
            default:
                System.out.println(ANSI_BLUE + ANSI_BOLD + "Opción no válida" + ANSI_RESET);
                break;
        }

    }

    public static void añadirProducto() {
        boolean agregarMas = true;

        while (agregarMas) {
            Scanner input = new Scanner(System.in);
            System.out.println(ANSI_BLUE + ANSI_BOLD + "Ingrese el nombre del producto: " + ANSI_RESET);
            String name = input.nextLine();
            System.out.println(ANSI_BLUE + ANSI_BOLD + "Ingrese el precio del producto: " + ANSI_RESET);
            double price = input.nextDouble();
            System.out.println(ANSI_BLUE + ANSI_BOLD + "Ingrese la cantidad del producto: " + ANSI_RESET);
            int quantity = input.nextInt();

            try {
                String routeFile = "tienda.txt";
                File newArchive = new File(routeFile);
                FileWriter writeArchive = new FileWriter(newArchive, true);
                BufferedWriter outputArchive = new BufferedWriter(writeArchive);

                outputArchive.write(name + ";" + price + ";" + quantity + "\n");

                System.out.println(ANSI_BLUE + ANSI_BOLD + "Producto añadido correctamente" + ANSI_RESET);

                outputArchive.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            System.out.println(ANSI_BLUE + ANSI_BOLD + "¿Desea agregar otro producto? (S/N)" + ANSI_RESET);
            String option = input.next();

            if (option.equals("N") || option.equals("n")) {
                agregarMas = false;
                mostrarMenu();
            }
        }
    }

    public static void eliminar() {

        boolean eliminarMas = true;

        while (eliminarMas) {
            Scanner input = new Scanner(System.in);
            System.out.println(ANSI_BLUE + ANSI_BOLD + "Ingrese el nombre del producto a eliminar: " + ANSI_RESET);
            String name = input.nextLine();

            try {
                String routeFile = "tienda.txt";
                File newArchive = new File(routeFile);
                FileReader writeArchive = new FileReader(newArchive);
                BufferedReader outputArchive = new BufferedReader(writeArchive);

                String str;
                StringBuilder fileContent = new StringBuilder();
                boolean productoEncontrado = false;

                while ((str = outputArchive.readLine()) != null) {
                    String[] data = str.split(";");
                    String nameProduct = data[0];
                    double price = Double.parseDouble(data[1]);
                    int quantity = Integer.parseInt(data[2]);

                    if (nameProduct.equals(name)) {
                        System.out.println(ANSI_RED + ANSI_BOLD + "Producto eliminado correctamente" + ANSI_RESET);
                        productoEncontrado = true;
                    } else {
                        fileContent.append(str).append("\n");
                    }
                }

                outputArchive.close();

                if (productoEncontrado) {
                    FileWriter fileWriter = new FileWriter(newArchive);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(fileContent.toString());
                    bufferedWriter.close();
                } else {
                    System.out.println(ANSI_RED + ANSI_BOLD + "No se encontró ningún producto con ese nombre" + ANSI_RESET);
                }

                System.out.println(ANSI_BLUE + ANSI_BOLD + "¿Desea eliminar otro producto? (S/N)" + ANSI_RESET);
                String option = input.next();

                if (option.equals("N") || option.equals("n")) {
                    eliminarMas = false;
                    mostrarMenu();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void vender() {
        boolean venderMas = true;

        while (venderMas) {
            Scanner input = new Scanner(System.in);
            System.out.println(ANSI_BLUE + ANSI_BOLD + "Ingrese el nombre del producto a vender: " + ANSI_RESET);
            String name = input.nextLine();
            System.out.println(ANSI_BLUE + ANSI_BOLD + "Ingrese la cantidad del producto a vender: " + ANSI_RESET);
            int quantity = input.nextInt();

            try {
                String routeFile = "tienda.txt";
                File newArchive = new File(routeFile);
                FileReader writeArchive = new FileReader(newArchive);
                BufferedReader outputArchive = new BufferedReader(writeArchive);

                String str;
                StringBuilder fileContent = new StringBuilder();
                boolean productoEncontrado = false;

                while ((str = outputArchive.readLine()) != null) {
                    String[] data = str.split(";");
                    String nameProduct = data[0];
                    double price = Double.parseDouble(data[1]);
                    int quantityProduct = Integer.parseInt(data[2]);

                    if (nameProduct.equals(name)) {
                        if (quantityProduct >= quantity) {
                            quantityProduct -= quantity;
                            productoEncontrado = true;
                            System.out.println(ANSI_GREEN + ANSI_BOLD + "Producto vendido correctamente" + ANSI_RESET);
                            calcularCuenta(price, quantity);
                        } else {
                            System.out.println(ANSI_RED + ANSI_BOLD + "No hay suficiente stock de ese producto" + ANSI_RESET);
                        }
                    }

                    fileContent.append(nameProduct).append(";").append(price).append(";").append(quantityProduct)
                            .append("\n");
                }

                outputArchive.close();

                if (productoEncontrado) {
                    FileWriter fileWriter = new FileWriter(newArchive);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(fileContent.toString());
                    bufferedWriter.close();
                } else {
                    System.out.println(ANSI_RED + ANSI_BOLD + "No se encontró ningún producto con ese nombre" + ANSI_RESET);
                }

                System.out.println(ANSI_BLUE + ANSI_BOLD + "¿Desea vender otro producto? (S/N)" + ANSI_RESET);
                String option = input.next();

                if (option.equals("N") || option.equals("n")) {
                    venderMas = false;
                    mostrarMenu();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static double calcularCuenta(double precio, int cantidad) {
        double total = precio * cantidad;
        System.out.println(ANSI_GREEN + ANSI_BOLD + "El total a pagar es: " + ANSI_RESET + total);
        return total;
    }

    public static void facturaCuenta() {
        boolean facturaMas = true;
        double totalCuenta = 0.0;

        while (facturaMas) {
            Scanner input = new Scanner(System.in);
            System.out.println(ANSI_BLUE + ANSI_BOLD + "Ingrese el nombre del producto a vender: " + ANSI_RESET);
            String name = input.nextLine();
            System.out.println(ANSI_BLUE + ANSI_BOLD + "Ingrese la cantidad del producto a vender: " + ANSI_RESET);
            int quantity = input.nextInt();

            try {
                String routeFile = "tienda.txt";
                File newArchive = new File(routeFile);
                FileReader writeArchive = new FileReader(newArchive);
                BufferedReader outputArchive = new BufferedReader(writeArchive);

                String str;
                StringBuilder fileContent = new StringBuilder();
                boolean productoEncontrado = false;

                while ((str = outputArchive.readLine()) != null) {
                    String[] data = str.split(";");
                    String nameProduct = data[0];
                    double price = Double.parseDouble(data[1]);
                    int quantityProduct = Integer.parseInt(data[2]);

                    if (nameProduct.equals(name)) {
                        if (quantityProduct >= quantity) {
                            quantityProduct -= quantity;
                            productoEncontrado = true;
                            System.out.println(ANSI_GREEN + ANSI_BOLD + "Producto vendido correctamente" + ANSI_RESET);
                            double subtotal = calcularCuenta(price, quantity);
                            totalCuenta += subtotal;
                            System.out.println(ANSI_BLUE + ANSI_BOLD + "El total de la cuenta hasta ahora es: " + ANSI_RESET + totalCuenta);
                        } else {
                            System.out.println(ANSI_RED + ANSI_BOLD + "No hay suficiente stock de ese producto" + ANSI_RESET);
                        }
                    }

                    fileContent.append(nameProduct).append(";").append(price).append(";").append(quantityProduct)
                            .append("\n");
                }

                outputArchive.close();

                if (productoEncontrado) {
                    FileWriter fileWriter = new FileWriter(newArchive);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(fileContent.toString());
                    bufferedWriter.close();
                } else {
                    System.out.println(ANSI_RED + ANSI_BOLD + "No se encontró ningún producto con ese nombre" + ANSI_RESET);
                }

                System.out.println(ANSI_BLUE + ANSI_BOLD + "¿Desea vender otro producto? (S/N)" + ANSI_RESET);
                String option = input.next();

                if (option.equals("N") || option.equals("n")) {
                    facturaMas = false;
                    totalCuenta = 0.0;
                    mostrarMenu();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void comprar() {
        boolean comprarMas = true;

        while (comprarMas) {
            Scanner input = new Scanner(System.in);
            System.out.println(ANSI_BLUE + ANSI_BOLD + "Ingrese el nombre del producto a comprar: " + ANSI_RESET);
            String name = input.nextLine();
            System.out.println(ANSI_BLUE + ANSI_BOLD + "Ingrese la cantidad del producto a comprar: " + ANSI_RESET);
            int quantity = input.nextInt();

            try {
                String routeFile = "tienda.txt";
                File newArchive = new File(routeFile);
                FileReader writeArchive = new FileReader(newArchive);
                BufferedReader outputArchive = new BufferedReader(writeArchive);

                String str;
                StringBuilder fileContent = new StringBuilder();
                boolean productoEncontrado = false;

                while ((str = outputArchive.readLine()) != null) {
                    String[] data = str.split(";");
                    String nameProduct = data[0];
                    double price = Double.parseDouble(data[1]);
                    int quantityProduct = Integer.parseInt(data[2]);

                    if (nameProduct.equals(name)) {
                        quantityProduct += quantity;
                        productoEncontrado = true;
                        System.out.println(ANSI_GREEN + ANSI_BOLD + "Producto comprado correctamente" + ANSI_RESET);
                    }

                    fileContent.append(nameProduct).append(";").append(price).append(";").append(quantityProduct)
                            .append("\n");
                }

                outputArchive.close();

                if (productoEncontrado) {
                    FileWriter fileWriter = new FileWriter(newArchive);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(fileContent.toString());
                    bufferedWriter.close();
                } else {
                    System.out.println(ANSI_RED + ANSI_BOLD + "No se encontró ningún producto con ese nombre" + ANSI_RESET);
                }

                System.out.println(ANSI_BLUE + ANSI_BOLD + "¿Desea comprar otro producto? (S/N)" + ANSI_RESET);
                String option = input.next();

                if (option.equals("N") || option.equals("n")) {
                    comprarMas = false;
                    mostrarMenu();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void mostrarProductoPorNombre() {
        boolean mostrarMas = true;

        while (mostrarMas) {
            Scanner input = new Scanner(System.in);
            System.out.println(ANSI_BLUE + ANSI_BOLD + "Ingrese el nombre del producto a mostrar: " + ANSI_RESET);
            String name = input.nextLine();

            try {
                String routeFile = "tienda.txt";
                File newArchive = new File(routeFile);
                FileReader writeArchive = new FileReader(newArchive);
                BufferedReader outputArchive = new BufferedReader(writeArchive);

                String str;
                boolean productoEncontrado = false;

                while ((str = outputArchive.readLine()) != null) {
                    String[] data = str.split(";");
                    String nameProduct = data[0];
                    double price = Double.parseDouble(data[1]);
                    int quantity = Integer.parseInt(data[2]);

                    if (nameProduct.equals(name)) {
                        productoEncontrado = true;
                        System.out.println(ANSI_RED + "--------------------" + ANSI_RESET);
                        System.out.println(ANSI_GREEN +  ANSI_BOLD + "Nombre: " + ANSI_RESET + nameProduct);
                        System.out.println(ANSI_GREEN +  ANSI_BOLD + "Precio: " + ANSI_RESET + "$" + price);
                        System.out.println(ANSI_GREEN +  ANSI_BOLD + "Cantidad: " + ANSI_RESET + quantity);
                        System.out.println(ANSI_RED + "--------------------" + ANSI_RESET);
                    }
                }

                outputArchive.close();

                if (!productoEncontrado) {
                    System.out.println(ANSI_RED + ANSI_BOLD + "No se encontró ningún producto con ese nombre" + ANSI_RESET);
                }

                System.out.println(ANSI_BLUE + ANSI_BOLD + "¿Desea mostrar otro producto? (S/N)" + ANSI_RESET);
                String option = input.next();

                if (option.equals("N") || option.equals("n")) {
                    mostrarMas = false;
                    mostrarMenu();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void mostrarProductoPorPrecio() {
        boolean mostrarMas = true;

        while (mostrarMas) {
            Scanner input = new Scanner(System.in);
            System.out.println(ANSI_BLUE + ANSI_BOLD + "Ingrese el precio del producto a mostrar: " + ANSI_RESET);
            double price = input.nextDouble();
            System.out.println(ANSI_BLUE + ANSI_BOLD + "Ingrese el tipo de comparación (< o >): " + ANSI_RESET);
            String type = input.next();

            try {
                String routeFile = "tienda.txt";
                File newArchive = new File(routeFile);
                FileReader writeArchive = new FileReader(newArchive);
                BufferedReader outputArchive = new BufferedReader(writeArchive);

                String str;
                boolean productoEncontrado = false;

                while ((str = outputArchive.readLine()) != null) {
                    String[] data = str.split(";");
                    String nameProduct = data[0];
                    double priceProduct = Double.parseDouble(data[1]);
                    int quantity = Integer.parseInt(data[2]);

                    if (type.equals("<")) {
                        if (priceProduct < price) {
                            productoEncontrado = true;
                            System.out.println(ANSI_RED + "--------------------" + ANSI_RESET);
                            System.out.println(ANSI_GREEN +  ANSI_BOLD + "Nombre: " + ANSI_RESET + nameProduct);
                            System.out.println(ANSI_GREEN +  ANSI_BOLD + "Precio: " + ANSI_RESET + "$" + priceProduct);
                            System.out.println(ANSI_GREEN +  ANSI_BOLD + "Cantidad: " + ANSI_RESET + quantity);
                            System.out.println(ANSI_RED + "--------------------" + ANSI_RESET);
                        }
                    } else if (type.equals(">")) {
                        if (priceProduct > price) {
                            productoEncontrado = true;
                            System.out.println(ANSI_RED + "--------------------" + ANSI_RESET);
                            System.out.println(ANSI_GREEN +  ANSI_BOLD + "Nombre: " + ANSI_RESET + nameProduct);
                            System.out.println(ANSI_GREEN +  ANSI_BOLD + "Precio: " + ANSI_RESET + "$" + priceProduct);
                            System.out.println(ANSI_GREEN +  ANSI_BOLD + "Cantidad: " + ANSI_RESET + quantity);
                            System.out.println(ANSI_RED + "--------------------" + ANSI_RESET);
                        }
                    }
                }

                outputArchive.close();

                if (!productoEncontrado) {
                    System.out.println(ANSI_RED + ANSI_BOLD + "No se encontró ningún producto con ese precio" + ANSI_RESET);
                }

                System.out.println(ANSI_BLUE + ANSI_BOLD + "¿Desea colocar otro precio para mostrar productos? (S/N)" + ANSI_RESET);
                String option = input.next();

                if (option.equals("N") || option.equals("n")) {
                    mostrarMas = false;
                    mostrarMenu();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void mostrarStock() {
        try {
            String routeFile = "tienda.txt";
            File newArchive = new File(routeFile);
            FileReader writeArchive = new FileReader(newArchive);
            BufferedReader outputArchive = new BufferedReader(writeArchive);

            String str;
            boolean productoEncontrado = false;

            while ((str = outputArchive.readLine()) != null) {
                String[] data = str.split(";");
                String nameProduct = data[0];
                double price = Double.parseDouble(data[1]);
                int quantity = Integer.parseInt(data[2]);

                if (quantity < 5) {
                    productoEncontrado = true;
                    System.out.println(ANSI_RED + "--------------------" + ANSI_RESET);
                    System.out.println(ANSI_GREEN +  ANSI_BOLD + "Nombre: " + ANSI_RESET + nameProduct);
                    System.out.println(ANSI_GREEN +  ANSI_BOLD + "Precio: " + ANSI_RESET + "$" + price);
                    System.out.println(ANSI_GREEN +  ANSI_BOLD + "Cantidad: " + ANSI_RESET + quantity);
                    System.out.println(ANSI_RED + "--------------------" + ANSI_RESET);
                }
            }

            outputArchive.close();

            if (!productoEncontrado) {
                System.out.println(ANSI_RED + ANSI_BOLD + "No se encontró ningún producto con ese precio" + ANSI_RESET);
            }

             String abrirMenu;
            Scanner input = new Scanner(System.in);
            System.out.println(ANSI_BLUE + ANSI_BOLD + "¿Desea abrir el menú? (S/N)" + ANSI_RESET);
            abrirMenu = input.next();

            if (abrirMenu.equals("S") || abrirMenu.equals("s")) {
                mostrarMenu();
            } else {
                System.out.println(ANSI_BLUE + ANSI_BOLD + "Gracias por usar el programa - By Alessandro Rios Lopez Github: @AlessDeveloper01" + ANSI_RESET);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void calcularVentaTotal() {
        try {
            String routeFile = "tienda.txt";
            File newArchive = new File(routeFile);
            FileReader writeArchive = new FileReader(newArchive);
            BufferedReader outputArchive = new BufferedReader(writeArchive);

            String str;
            double total = 0.0;

            while ((str = outputArchive.readLine()) != null) {
                String[] data = str.split(";");
                String nameProduct = data[0];
                double price = Double.parseDouble(data[1]);
                int quantity = Integer.parseInt(data[2]);

                total += price * quantity;
            }

            outputArchive.close();

            System.out.println(ANSI_GREEN + ANSI_BOLD + "La venta total hasta el momento es:" + ANSI_RESET + "$" + total);

            String abrirMenu;
            Scanner input = new Scanner(System.in);
            System.out.println(ANSI_BLUE + ANSI_BOLD + "¿Desea abrir el menú? (S/N)" + ANSI_RESET);
            abrirMenu = input.next();

            if (abrirMenu.equals("S") || abrirMenu.equals("s")) {
                mostrarMenu();
            } else {
                System.out.println(ANSI_BLUE + ANSI_BOLD + "Gracias por usar el programa - By Alessandro Rios Lopez Github: @AlessDeveloper01" + ANSI_RESET);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}