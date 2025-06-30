public class Golden_Ratio {
    public static double f(double x){
        return x*x - 2*x + (Math.exp(-x));
    }
    public static void Golden(AB gap, double l, int k){
        double y0 = gap.getA()+((3-Math.sqrt(5))/2)*(gap.getB()-gap.getA());
        double z0 = gap.getA()+gap.getB()-y0;
        double deltaX;
        double a1;
        double b1;
        double y1;
        double z1;
        do {
            System.out.println(k+"-я итерация:\n");
            double Fy0 = f(y0);
            System.out.println("f(y"+k+") = "+Fy0);
            double Fz0 = f(z0);
            System.out.println("f(z"+k+") = "+Fz0);
            if (Fy0 <= Fz0 ) {
                System.out.println("f(y"+k+") < "+"f(x"+k+"):\n");
                a1 = gap.getA();
                b1 = z0;
                y1 = a1+b1-y0;
                z1 = y0;
                System.out.println("a"+(k+1)+" = "+a1+"\n" +"b"+(k+1)+" = "+b1+"\n" + "y"+(k+1)+" = "+y1+"\n" + "z"+(k+1)+" = "+z1+"\n");
            }
            else{
                System.out.println("f(y"+k+") > "+"f(x"+k+"):\n");
                a1 = y0;
                b1 = gap.getB();
                y1 = z0;
                z1 = a1+b1-z0;
                System.out.println("a"+(k+1)+" = "+a1+"\n" +"b"+(k+1)+" = "+b1+"\n" + "y"+(k+1)+" = "+y1+"\n" + "z"+(k+1)+" = "+z1+"\n");
            }
            deltaX = Math.abs(a1-b1);
            System.out.println("deltaX = "+deltaX+"\n");
            k+=1;
            y0 = y1;
            z0 = z1;
            gap.setA(a1);
            gap.setB(b1);
        }while(deltaX>l);
        System.out.println("Процедура завершена, искомый интервал: ["+gap.getA()+";"+gap.getB()+"]"+"\n");
        System.out.println("В качестве приближенного значения возьмем его середину: x = "+((gap.getA()+gap.getB())/2));
    }

    public static void main(String[] args) {
        AB gap = new AB(0.75, 3.15);
        double l = 0.00000001;
        int k = 0;

        Golden(gap, l, k);
    }
}
