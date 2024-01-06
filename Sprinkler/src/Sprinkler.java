import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sprinkler {

    public ArrayList<ArrayList<Double>> finalSprinklers = new ArrayList<>();


    public double findSprinklers(ArrayList<ArrayList<Double>> geeks, double initialPoint, double length){
        if (initialPoint>= length){
            return initialPoint;
        }

        ArrayList<ArrayList<Double>> temp = new ArrayList<>();
        for (int i = 0; i < geeks.size(); i++){

            if ((geeks.get(i).get(2) <= initialPoint) && (initialPoint <= geeks.get(i).get(3))){

                ArrayList<Double> geekCopy = new ArrayList<>(geeks.get(i)); // Create a new copy of the list
                geekCopy.add(geeks.get(i).get(3) - initialPoint);
                temp.add(geekCopy);

            }
        }

        ArrayList<ArrayList<Double>> tempSolution = new ArrayList<>();
        double maxArea = 0;
        for (int i = 0; i < temp.size(); i++){
            geeks.remove(temp.get(i)); // Newly added
            if (temp.get(i).get(4)>maxArea){
                    maxArea = temp.get(i).get(4);
                    tempSolution.clear();
                tempSolution.add(new ArrayList<>(temp.get(i)));
        }
        }


        if (!tempSolution.isEmpty()) {
            finalSprinklers.add(new ArrayList<>(tempSolution.get(0))); // Create a new copy of the list
            initialPoint = tempSolution.get(0).get(3);
            findSprinklers(geeks, tempSolution.get(0).get(3), length);

        }



        return initialPoint;


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double l = scanner.nextDouble();
        double w = scanner.nextDouble();
        int n = scanner.nextInt();

        double widthHalf = w / 2;
        ArrayList<ArrayList<Double>> geeks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            double pi = scanner.nextDouble();
            double ri = scanner.nextDouble();
            geeks.add(new ArrayList<>(List.of(pi, ri)));
        }



        for (int i = 0; i < geeks.size(); i++){
            double r = geeks.get(i).get(1);
            double center = geeks.get(i).get(0);
            if (((r*r)-(widthHalf)) > 0){
                double pi = center-Math.sqrt((r*r)-(widthHalf));
                if (pi < 0){
                    pi = 0;
                }
                double pf = center+Math.sqrt((r*r)-(widthHalf));
                if (pf>l){
                    pf = l;
                }
                geeks.get(i).add(pi);
                geeks.get(i).add(pf);
            }
            else{
                geeks.get(i).add(-1.0);
                geeks.get(i).add(-1.0);
            }
        }

        Sprinkler main = new Sprinkler();
//        main.findSprinklers(geeks, 0.0, l);

        double answer = main.findSprinklers(geeks, 0.0, l);

        if (answer < l){
//            System.out.println("length: "+ l+ " init pt.: "+ answer);

            System.out.println("IMPOSSIBLE");
        } else{
            System.out.println(main.finalSprinklers.size());

        }



    }


}