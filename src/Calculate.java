import java.util.Arrays;
import java.util.Scanner;

public class Calculate {

    public static void main(String[] args) {

        Scanner input= new Scanner(System.in);
        System.out.println("number of points");
        int n = input.nextInt();
        int [][]mat = new int[n][2];
        int [][]points = new int[n][n];
        int []afterequal = new int[n];
        for(int i=0;i<n;i++){
            mat[i][0] = input.nextInt();
            mat[i][1] = input.nextInt();
        }
        System.out.println("order= "+(n-1));

        for(int i=0;i<n;i++){
            int k=n-1;
            for(int j=0;j<n;j++) {
                points[i][j] = (int) Math.pow(mat[i][0],k);
                System.out.print(points[i][j] + " ");
                k--;
            }
            afterequal[i]=mat[i][1];
            System.out.print("= "+afterequal[i]);
            System.out.println();
        }

        Term px= new Term(n,points,afterequal);

        double[] result= px.getResult1();


        Polynomial[] array1=fun(result,n);

        Polynomial polynomial= polof(array1,n);
        Polynomial differ= polynomial.differentiate();

        System.out.println("f(x)= "+polynomial.toString());
        System.out.println("f(-1)= "+polynomial.evaluate(-1));
        System.out.println("f(0)= "+polynomial.evaluate(0));
        System.out.println("f(1)= "+polynomial.evaluate(1));
        System.out.println("f'(x)= "+differ.toString());
        System.out.println("f'(x)= "+differ.degree());
        System.out.println("f'(x)= "+differ.root(polynomial,2));

    }
    public  static Polynomial polof(Polynomial[] polynomial,int n){
        Polynomial pol= polynomial[0];
        for(int i= 1;i<n;i++)
        {
            pol=pol.plus(polynomial[i]);
        }
        return pol;
    }

    public static Polynomial[] fun(double[]result,int n){
        int cofficient=0;
        Polynomial[] array1=new Polynomial[n];
        for(int i= n;i>0;i--){
            array1[cofficient]=new Polynomial(result[cofficient],i-1);
            cofficient++;
        }
        return array1;
    }
}
