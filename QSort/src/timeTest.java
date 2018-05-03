
class HowLong{
    private static int c=20000;
    public void setC(int c){
        this.c=c;
    }

    static long sortDuration(String functionName, Integer array[]){
        long startTime = System.nanoTime();
        switch (functionName) {
            case "quickSort":   QuickSorts.sort(array, "quickSort");
                break;
            case "randSort":    QuickSorts.sort(array,  "randSort");
                break;
            case "medianSort":  QuickSorts.sort(array,"medianSort");
                break;
            case "insertSort":  QuickSorts.sort(array, "insertSort");
                break;
            default: QuickSorts.sort(array, "quickSort");
                break;
        }
        long endTime = System.nanoTime();
        endTime=(endTime-startTime);
        return endTime;
    }
}

class time{
    long FiveK =0;
    long TenK =0;
    long TwentyK =0;
};