package util;



import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Utility {

    private static final Random random;
    // private static ArraysStack employeeList;
    //private static LinkedListStack jobPositionList;
    //private static LinkedListStack staffingList;

    //constructor estático, inicializador estático
    static {
        // semilla para el random
        long seed = System.currentTimeMillis();
        random = new Random(seed);

    }

    /* public static ArraysStack getEmployeeList() {
         return employeeList;
     }

     public static void setEmployeeList(ArraysStack employeeList) {
         Utility.employeeList = employeeList;
     }

     public static LinkedListStack getJobPositionList() {
         return jobPositionList;
     }

     public static void setJobPositionList(LinkedListStack jobPositionList) {
         Utility.jobPositionList = jobPositionList;
     }

     public static LinkedListStack getStaffingList() {
         return staffingList;
     }

     public static void setStaffingList(LinkedListStack staffingList) {
         Utility.staffingList = staffingList;
     }
 */
    // ------------------------------------------------------------- Métodos:
    public static int random(int bound) {
        // return(int) Math.floor(Math.random()*bound); //Forma 1
        return 1 + random.nextInt(bound);
    }

    public static void fill(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = random(99);
        }
    }

    public static String format(long n) {
        return new DecimalFormat("###,###,###.##").format(n); //Establecer un formato para n
    }

    public static String format(double n) {
        return new DecimalFormat("###,###,###.##").format(n); //Establecer un formato para n
    }

    public static String $format(double n) {
        return new DecimalFormat("$###,###,###.##").format(n); //Establecer un formato para n
    }

    public static int min(int x, int y) {
        return x < y ? x : y;
    }

    public static int max(int x, int y) {
        return x > y ? x : y;
    }

    public static String show(int[] a, int n) {
        String result = "";
        for (int i = 0; i < n; i++) {

            result += a[i] + " ";

        }
        //End for
        return result;
    }


    public static String dateFormat(Date value) {
        return new SimpleDateFormat("dd/MM/yyyy").format(value);
    }//End dateFormat

    public static Date parseDate(String dateStr) { //Convierte de String a Date
        SimpleDateFormat fechaFormateada = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return fechaFormateada.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getAge(Date date) {
        Calendar calendarBD = Calendar.getInstance();
        calendarBD.setTime(date);

        Calendar calendarToday = Calendar.getInstance();

        int age = calendarToday.get(Calendar.YEAR) - calendarBD.get(Calendar.YEAR); //Se resta el año actual al año de nacimiento y da la edad

        //Si el cumpleaños todavía no ocurre
        if (calendarToday.get(Calendar.DAY_OF_YEAR) < calendarBD.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }


    public static String instanceOf(Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) return "Integer";
        if (a instanceof String && b instanceof String) return "String";
        if (a instanceof Character && b instanceof Character) return "Character";
        //if (a instanceof Employee && b instanceof Employee) return "Employee";
        // if (a instanceof JobPosition && b instanceof JobPosition) return "JobPosition";

        return "Unknown";
    }

    public static int compare(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "Integer":
                Integer int1 = (Integer) a;
                Integer int2 = (Integer) b;
                return int1 < int2 ? -1 : int1 > int2 ? 1 : 0; // ? --> Si es cierto haga... /// : --> Pero si no es cierto entonces haga... Puntos son como el else, ? es como el if

            case "String":
                String str1 = (String) a;
                String str2 = (String) b;
                return str1.compareTo(str2) < 0 ? -1 : str1.compareTo(str2) > 0 ? 1 : 0;

            case "Character":
                Character ch1 = (Character) a;
                Character ch2 = (Character) b;
                return ch1.compareTo(ch2) < 0 ? -1 : ch1.compareTo(ch2) > 0 ? 1 : 0;

           /* case "Employee":
                Employee emp1 = (Employee) a; Employee emp2 = (Employee) b;
                return emp1.getId() < emp2.getId() ? -1 :  emp1.getId() > emp2.getId() ? 1 : 0;

            case "JobPosition":
                JobPosition job1 = (JobPosition) a;
                JobPosition job2 = (JobPosition) b;
                return job1.getId() < job2.getId() ? -1 : job1.getId() > job2.getId() ? 1 : 0;

            */
        }//End switch
        return 2;
    }

    public static int maxArray(int[] a) {

        int mayor = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > mayor) {
                mayor = a[i];
            }
        }
        return mayor;
    }

    public static int[] getIntegerArray(int cantidad ){

        int[] array = new int[cantidad];
        Random rand = new Random();

        for (int i = 0; i < cantidad; i++) {
            array[i] = random(10000); // Números aleatorios entre 0 y 99,999
        }

        return array;
    }

    public static int [] copyArray(int [] a){

        int [] copy = new int [a.length];

        for (int i = 0; i<a.length; i++){

            copy[i] = a[i];

        }

        return copy;
    }



}

    /*
    public static String infixToPrefixConverter(String exp) throws StackException {
        StringBuilder reversedExp = new StringBuilder(exp).reverse();
        LinkedListStack stack = new LinkedListStack();
        StringBuilder prefixExp = new StringBuilder();

        for (char c : reversedExp.toString().toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                prefixExp.append(c);
            } else if (c == ')') {
                stack.push(c);
            } else if (c == '(') {
                while (!stack.isEmpty() && compare((char) stack.peek(), ')') != 0) {
                    prefixExp.append(stack.pop());
                }
                if (!stack.isEmpty() && compare((char) stack.top(), ')') != 0) {
                    throw new StackException("Invalid expression");
                } else if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (c == ' ') {//Ignora espacios

            } else {// es un operador
                while (!stack.isEmpty() && getPriority(c) < getPriority((char) stack.peek())) {
                    prefixExp.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            prefixExp.append(stack.pop());
        }

        return prefixExp.reverse().toString();
    }

    public static String prefixToPostfixConverter(String exp) throws StackException {
        LinkedListStack stack = new LinkedListStack();
        int n = exp.length();


        for (int i = n - 1; i >= 0; i--) {

            char c = exp.charAt(i);

            //Validation of invalid characters
            if (!Character.isLetterOrDigit(c) && "+-*///^ ".indexOf(c) == -1) {
       /*         return "Invalid expression";
            }

            if (Character.isLetterOrDigit(c)) {

                stack.push(String.valueOf(c));

            } else if (c == ' ') {//Ignora espacios

            } else {
                if (stack.size() < 2) {
                    return "Invalid expression";
                }
                String operand1 = (String) stack.pop(); // Extract the second operand
                String operand2 = (String) stack.pop(); // Extract the first operand
                String postfixExpression = operand1 + operand2 + c;
                stack.push(postfixExpression);
            }
        }

        if (stack.size() != 1) {
            return "Invalid expression";
        }

        return (String) stack.pop();

    }


    public static String postfixToInfixConverter(String exp) throws StackException {

        LinkedListStack stack = new LinkedListStack();
        String expFix = "";

        for (char c : exp.toCharArray()) {

            //Validation of invalid characters
            if (!Character.isLetterOrDigit(c) && "+-*///^ //".indexOf(c) == -1) {
             /*   return "Invalid expression";
            }

            if (Character.isLetterOrDigit(c)) {

                stack.push(String.valueOf(c));
                //If it's an operand, we add it to the stack as a String
            } else {
                if (stack.size() < 2) {

                    return "Invalid expression"; // Not enough operands

                }
                String ope2 = (String) stack.pop(); // Extract the second operand
                String ope1 = (String) stack.pop(); // Extract the first operand
                String infixExpression = "(" + ope1 + c + ope2 + ")";
                stack.push(infixExpression);
            }
        }

        if (stack.size() != 1) {
            return "Invalid expression";
        } else
            return (String) stack.pop();
    }

    private static int getPriority(char operator) {

        switch (operator) {
            case '+', '-' -> {
                return 1; //Prioridad más baja
            }
            case '*', '/' -> {
                return 2;
            }
            case '^' -> {
                return 3; //Prioridad más alta
            }
        }

        return -1;

    }

    public static String getMoods (){

        String moods [] = { "Happiness", "Sadness", "Anger", "Sickness", "Cheerful", "Reflective",
                "Gloomy", "Romantic", "Calm", "Hopeful", "Fearful", "Tense", "Lonely"};

        return moods[random(moods.length)-1];
    }

    public static String getNames(){

        String names [] = {"Sofia" , "Mateo" , "Valentina" , "Santiago" , "Isabella" , "Benjamín" , "Camila" ,
                "Leonardo" , "Antonella " , "Thiago" , "Martina" , "Lucas " , "Emilia " , "Diego" , "Luciana " ,
                "Joaquín " , "Renata" , "Tomás" , "Zoe" , "Nicolás "};

        return names[random(names.length)-1];
    }

    public static String getPlace() {

        String places[] = {"San José", "Ciudad Quesada", "Paraíso",
                "Turrialba", "Limón", "Liberia", "Puntarenas", "San Ramón", "Puerto Viejo", "Volcán Irazú", "Pérez Zeledón",
                "Palmares", "Orotina", "El coco", "Ciudad Neilly", "Sixaola", "Guápiles","Siquirres"
                , "El Guarco", "Cartago", "Santa Bárbara", "Jacó", "Manuel Antonio", "Quepos", "Santa Cruz",
                "Nicoya"};

        return places[random(places.length-1)];
    }

    public static String getWeather() {

        String weather[] = {"rainy, thunderstorm, sunny, cloudy, foggy"};

        return weather[random(weather.length)-1];
    }
}

              */