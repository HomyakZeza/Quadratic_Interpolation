public class Quadratic_Interpolation {

    public static double f(double x){
        return x*x - 2*x + (Math.exp(-x));
    }

    public static Double dash(double x1, double x2, double x3, double f1, double f2, double f3){
        return ((x2*x2 - x3*x3)*f1 + (x3*x3 - x1*x1)*f2 + (x1*x1 - x2*x2)*f3)
                / (2 * ((x2 - x3)*f1 + (x3 - x1)*f2 + (x1 - x2)*f3));
    }

    public static boolean isBetween(double value, double x, double y){
        return value >= Math.min(x, y) && value <= Math.max(x, y);
    }

    public static Double nearestLesser(double x, double x1, double x2, double x3){
        Double count = null;

        if (x1 < x && (count == null || x1 > count)) count = x1;
        if (x2 < x && (count == null || x2 > count)) count = x2;
        if (x3 < x && (count == null || x3 > count)) count = x3;

        return count;
    }

    public static Double nearestBigger(double x, double x1, double x2, double x3){
        Double count = null;

        if (x1 > x && (count == null || x1 < count)) count = x1;
        if (x2 > x && (count == null || x2 < count)) count = x2;
        if (x3 > x && (count == null || x3 < count)) count = x3;

        return count;
    }


    public static Double quadratic_Interpolation(double x1Initial, double deltaX, double epsilon1, double epsilon2){
        double x1 = x1Initial;
        double k = 0;

        int flag = 0;
        double x2 = x1 + deltaX;
        double x3;
        double f1 = f(x1);
        double f2 = f(x2);

        if (f1 < f2) {
            x3 = x1 + 2 * deltaX;
        } else {
            x3 = x1 - deltaX;
        }

        double f3 = f(x3);

        double Xdash;
        double Fdash;

        double Fmin;
        double Xmin;

        double condition;

        while (k != 10000){
            if(flag == 1) {
                System.out.println("Выполняет пункты 2-5:\n");
                x2 = x1 + deltaX;
                f1 = f(x1);
                f2 = f(x2);

                if (f1 > f2) {
                    x3 = x1 + 2 * deltaX;
                } else {
                    x3 = x1 - deltaX;
                }

                f3 = f(x3);
                System.out.println("x1= "+x1+" x2= "+x2+" x3= "+x3+"\n");
                System.out.println("f1= "+f1+" f2= "+f2+" f3= "+f3+"\n");
            }
            System.out.println("Выполняем пункты 6-8:\n");
            Fmin = Math.min(Math.min(f1, f2), f3);
            Xmin = (Fmin == f1) ? x1 : (Fmin == f2) ? x2 : x3;
            System.out.println("Fmin= "+Fmin+", Xmin= "+Xmin+"\n");

            condition = ((x2 - x3)*f1 + (x3 - x1)*f2 + (x1 - x2)*f3);

            if (condition != 0){
                System.out.println("Знаменатель Xdash не равен 0, можем проверить условие завершения:\n");
                Xdash = dash(x1, x2, x3, f1, f2, f3);
                Fdash = f(Xdash);

                if(Math.abs((Fmin - Fdash)/Fdash) < epsilon1 && Math.abs((Xmin - Xdash)/Xdash) < epsilon2){
                    System.out.println("Условие завершения выполнено. Ответ: \n");
                    return Xdash;
                }
                else{
                    System.out.println("Условие завершения не выполнено:\n");
                    if(isBetween(Xdash, x1, x3)){
                        Fmin = Math.min(Math.min(Math.min(f1, f2), f3), Fdash);
                        System.out.println("Xdash содержится в [x1, x3]:\n Fmin: "+Fmin+"\n");
                        x2 = (Fmin == f1) ? x1 : (Fmin == f2) ? x2 : (Fmin == f3) ? x3 : Xdash;

                        // Обрабатываем случай отсутствия ближайших меньшего/большего элемента:
                        Double nl = nearestLesser(x2, x1, x3, Xdash);
                        Double nb = nearestBigger(x2, x1, x3, Xdash);

                        // Если ближайшая меньшая точка не найдена, расширяем интервал влево
                        if (nl == null) {
                            System.out.println("Ближайшая меньшая точка не найдена, расширяем интервал влево");
                            nl = x2 - deltaX;
                        }
                        // Если ближайшая большая точка не найдена, расширяем интервал вправо
                        if (nb == null) {
                            System.out.println("Ближайшая большая точка не найдена, расширяем интервал вправо");
                            nb = x2 + deltaX;
                        }

                        x1 = nl;
                        x3 = nb;

                        System.out.println("Обновленные точки: x1= " + x1 + " x2= " + x2 + " x3= " + x3 + "\n");
                        f1 = f(x1); f2 = f(x2); f3 = f(x3);
                        System.out.println("f1= "+f1+" f2= "+f2+" f3= "+f3+"\n");
                        flag = 0;
                    }
                    else{
                        System.out.println("Xdash не содержится в интервале, начинаем цикл сначала:\n");
                        x1 = Xdash;
                        flag = 1;
                    }
                }
            }
            else{
                System.out.println("Знаменатель Xdash равен 0, начинаем цикл сначала:\n");
                x1 = Xmin;
                flag = 1;
            }
            k++;
            System.out.println("\u001B[31m" + "Номер итерации: "+ k + "\u001B[0m");
        }
        return k;
    }

    public static void main(String[] args) {
        double x1Initial = 1;    // Начальная точка
        double deltaX = 0.1;       // Шаг
        double epsilon1 = 0.0000000000001;    // Точность по функции
        double epsilon2 = 0.000000000001;

        System.out.println("\u001B[34m" + quadratic_Interpolation(x1Initial, deltaX, epsilon1, epsilon2) + "\u001B[0m");
    }
}
