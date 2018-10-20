package sample.objects;

public class NumberChecker {

    public static boolean containsNumber(String[] list){
        String[] numbers = "0 1 2 3 4 5 6 7 8 9".split(" ");
        for(int i = 0; i < list.length; i++){
            for(int y = 0; y < numbers.length; y++) {
                if (!list[i].equalsIgnoreCase(numbers[y])) {
                    //System.out.println(list[i] + " does not equal " + numbers[y]);
                    //System.out.println("y = " + y + " L = " + numbers.length);
                    if(y >= numbers.length-1){
                        System.out.println("reached here");
                        return false;
                    }
                }else{
                    break;
                }
            }
        }
        return true;
    }

}
