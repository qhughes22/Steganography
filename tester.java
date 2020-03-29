public class tester {

    public static void main(String[] Args) {
        int[] t = {0,0,1,1,0,1,0,1};
        System.out.println(Steg.arrayToInt(t));
        System.out.println(Steg.binaryToString(t));
        System.out.println((char) Steg.binaryArrayToInt(t));
    }
}
