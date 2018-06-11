可以找不比target小的第一个数(大于等于)
最后换成end 找比target 小的第一个数

def binarysearch(array, target):
    start, end = 0, len(array)-1
    while start <= end:
        mid = start + (end-start)//2
        if array[mid] < target:
            start = mid + 1
        else:
            end = mid - 1
    return start


public static void PrintIndicesForValue(int[] numbers, int target) {
    if (numbers == null)
        return;

    int low = 0, high = numbers.length - 1;
    // get the start index of target number
    int startIndex = -1;
    while (low <= high) {
        int mid = (high - low) / 2 + low;
        if (numbers[mid] > target) {
            high = mid - 1;
        } else if (numbers[mid] == target) {
            startIndex = mid;
            high = mid - 1;
        } else
            low = mid + 1;
    }

    // get the end index of target number
    int endIndex = -1;
    low = 0;
    high = numbers.length - 1;
    while (low <= high) {
        int mid = (high - low) / 2 + low;
        if (numbers[mid] > target) {
            high = mid - 1;
        } else if (numbers[mid] == target) {
            endIndex = mid;
            low = mid + 1;
        } else
            low = mid + 1;
    }

    if (startIndex != -1 && endIndex != -1){
        for(int i=0; i+startIndex<=endIndex;i++){
            if(i>0)
                System.out.print(',');
            System.out.print(i+startIndex);
        }
    }
}