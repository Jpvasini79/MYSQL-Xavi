/**
 * Created by Jeix4 on 18/05/2017.
 */

import java.sql.*;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in); //Sirve para recoger texto por consola
    static int select = -1; //opción elegida del usuario
    static String player; //Variables

    public static void main(String[] args) {

        //Mientras la opción elegida sea 0, preguntamos al usuario
        while (select != 5) {
            //Try catch para evitar que el programa termine si hay un error
            try {
                System.out.println("Elige opción:\n1.- Create a Player" +
                        "\n2.- Show a table in screen \n" +
                        "3.- Search a Id Player \n" +
                        "4.- Delete a Id Player\n" +
                        "5.- Exit");
                //Recoger una variable por consola
                select = Integer.parseInt(scanner.nextLine());

                //Ejemplo de switch case en Java
                switch (select) {
                    case 1:
                        createPlayer();
                        //pideNumeros();
                        //System.out.println(num1+" + "+num2+" = "+(num1+num2));
                        break;
                    case 2:
                        showTable();
                        //System.out.println(num1+" - "+num2+" = "+(num1-num2));
                        break;
                    case 3:
                        findPlayerById(1);

                        break;
                    case 4:
                        int idget = scanner.nextInt();
                        deletePlayerById(idget);
                        //System.out.println(num1+" / "+num2+" = "+(num1/num2));
                        break;
                    case 5:

                        System.out.println("Adios!");
                        break;
                    default:
                        System.out.println("Número no reconocido");
                        break;
                }

                System.out.println("\n"); //Mostrar un salto de línea en Java

            } catch (Exception e) {
                System.out.println("Uoop! Error!");
            }
        }

    }

    //Método para recoger variables por consola
    /*public static void pideNumeros(){
        System.out.println("Introduce número 1:");
        num1 = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduce número 2:");
        num2 = Integer.parseInt(scanner.nextLine());

        //Mostrar un salto de línea en Java
        System.out.println("\n");
    }*/

    public static void createPlayer() {
        try {

            System.out.println("Write the player to create");
            //player = String.parseString(scanner.nextLine());
            System.out.println("\n");

            //Class.forName(“com.mysql.jdbc.Driver”);
            //Singleton dbConexion = null;

            Connection c = Singleton.getConnection();
            PreparedStatement st;

            st = c.prepareStatement("INSERT INTO players VALUES (?,?,?,?,?,?,?)");
            st.setInt(1, 50);
            st.setString(2, "Juan"); //el 2 indica que se sustituira el segundo ‘?’ por el valor en double de 123.45
            st.setString(3, "Vasini");  //el 3 indica que se sustiruira el tercer ‘?’ por la cadena “hola”
            st.setString(4, "Cabrera");  //el 3 indica que se sustiruira el tercer ‘?’ por la cadena “hola”
            st.setString(5, "cocacola");  //el 3 indica que se sustiruira el tercer ‘?’ por la cadena “hola”
            st.setString(6, "cocacola@pedroj.com");
            st.setInt(7, 5000);

            if (st.executeUpdate() == 1) {
                System.out.println("filartada correctamente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public static void showTable() {
        try {
            System.out.println("Write the table you want to show:");
            //player = String.parseString(scanner.nextLine());
            System.out.println("\n");

            //Class.forName("com.mysql.jdbc.Driver");

            //Singleton dbConexion = null;
            Connection c = Singleton.getConnection();
            PreparedStatement st;
            st = c.prepareStatement("SELECT * FROM Players;");

            //st.setString(1, "Players"); //el 2 indica que se sustituira el segundo '?' por el valor en double de 123.45

            //los tipos de variables deben coincidir con los tipos definidos en las columnas de la tabla 1en la que se insertaran
            // execute select SQL stetement
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                Integer id = rs.getInt("ID");
                String name = rs.getString("NOMBRE");
                String apellido1 = rs.getString("APELLIDO1");
                String apellido2 = rs.getString("APELLIDO2");
                String password = rs.getString("PASSWORD");
                String email = rs.getString("EMAIL");
                String puntuacion = rs.getString("PUNTUACION");


                System.out.println("id : " + id);
                System.out.println("nombre : " + name);
                System.out.println("apellido1 : " + apellido1);
                System.out.println("apellido2 : " + apellido2);
                System.out.println("password : " + password);
                System.out.println("email : " + email);
                System.out.println("puntuacion : " + puntuacion);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }*/
    public static void showTable() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch(ClassNotFoundException e) {
            System.out.println("Class not found "+ e);
        }
        try {
            Connection con = Singleton.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Players");
            System.out.println("id   nombre   apellido1    apellido2    password     email    puntuacion");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nombre");
                String lastName = rs.getString("apellido1");
                String lastName2 = rs.getString("apellido2");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String puntuacion = rs.getString("puntuacion");
                System.out.println(id+"   "+name+"    "+lastName+"   "+lastName2+"    "+password+"   "+email+"    "+puntuacion);
            }
        } catch(SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
    }

    public static void findPlayerById(int pk) {
        try {

            System.out.println("Show Id player that you want: ");

            System.out.println("\n");
            //1. Create connection:

            Connection c = Singleton.getConnection();
            //2. Create statement
            PreparedStatement st;
            st = c.prepareStatement("SELECT * FROM Players WHERE id = ?");
            st.setInt(1, pk);
            ResultSet rs = st.executeQuery();

            ResultSetMetaData setMetaData = rs.getMetaData();
            int colsNum = setMetaData.getColumnCount();
            String colValue;

            System.out.println("Introduce ID del player:");

            while (rs.next()){

                for (int i = 1; i < colsNum; i++)
                {
                    colValue = rs.getString(i);
                    System.out.println(colValue + " " + setMetaData.getColumnName(i));
                }
                System.out.println("");
            }

        }
        catch (SQLException ex) {

            ex.printStackTrace();
        }


    }

    private static void deletePlayerById(int id) {

        try {
            Connection conn = Singleton.getConn();
            PreparedStatement stmnt = conn.prepareStatement("DELETE FROM Players WHERE id = ?");
            stmnt.setInt(1, id);
            stmnt.executeUpdate();
            System.out.println("You have deleted a player!");

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}




