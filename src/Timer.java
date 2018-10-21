public class Timer {

    public static long start(){
        return System.currentTimeMillis();
    }

    public static long end(){
        return System.currentTimeMillis();
    }

    public static long diff(long start, long end){
        return end - start;
    }
}
