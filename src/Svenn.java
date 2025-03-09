public class Svenn {
    public static double f(double x){
        if(x==0) throw new ArithmeticException("Деление на 0");
        return (Math.exp(x))+(1.0/x);
    }
    public static void Svenn_Algorithm(double x0, double t, int k, AB gap){
        double f1 = f(x0-t);
        double f2 = f(x0);
        double f3 = f(x0+t);
        double deltaX;
        double x1;
        if((f1>=f2)&&(f2<=f3)){
            gap.setA(x0-t);
            gap.setB(x0+t);
            return;
        }
        else if((f1<=f2)&&(f2>=f3)){
            System.out.println("Функция не является унимодальной, рекомендуется задать другую начальную точку x0");
            return;
        }
        else{
            if((f1>=f2)&&(f2>=f3)){
                deltaX = t;
                gap.setA(x0);
                x1 = x0 + t;
                k=1;
            }
            else{
                deltaX = -t;
                gap.setB(x0);
                x1 = x0 - t;
                k=1;
            }
            while(f(x1)<f(x0)){
                System.out.println(k+"-я итерация:\n");
                x0 = x1;
                System.out.println("x"+k+" = "+x0+"\n");
                x1 = x0+Math.pow(2, k)*deltaX;
                System.out.println("x"+(k+1)+" = "+x1+"\n");
                if(f(x1)<f(x0)&&deltaX == t){
                    gap.setA(x0);
                    System.out.println("f(x"+(k+1)+") < "+"f(x"+k+") "+" и deltaX = t:\n a0 = x"+k+"\n");
                }
                if(f(x1)<f(x0)&&deltaX == -t){
                    gap.setB(x0);
                    System.out.println("f(x"+(k+1)+") < "+"f(x"+k+") "+" и deltaX = -t:\n b0 = x"+k+"\n");
                }
                k=k+1;
            }
            if(deltaX == t){
                gap.setB(x1);
                System.out.println("Искомый интервал: ["+gap.getA()+";"+gap.getB()+"]"+"\n");
            }
            else{
                gap.setA(x1);
                System.out.println("Искомый интервал: ["+gap.getA()+";"+gap.getB()+"]"+"\n");
            }
        }
    }

    public static void main(String[] args) {
        double x0 = 1;    // Начальная точка
        double t = 0.05;       // Шаг
        int k = 1;
        AB gap = new AB(0, 0);

        Svenn_Algorithm(x0, t, k, gap);
    }

}
