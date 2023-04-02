import java.util.*;
import java.util.Arrays;

/*
 * Algorithm solutions to various Codility code lessons
 */

public class CodilitySolutions {
    static int binaryGap(int N) {
        String binary = Integer.toBinaryString(N);
        int pointer = 0;
        List<Integer> counters = new ArrayList<>();
        for (int i = 0; i < binary.length(); i++) {
            char curr = binary.charAt(i);
            if (curr == '0') {
                pointer += 1;
            } else {
                counters.add(pointer);
                pointer = 0;
            }
        }
        return Collections.max(counters);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    static void OddOccurancesInArray(int[] A) {
        HashSet<Integer> stored = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            if (stored.contains(A[i])) {
                stored.remove(A[i]);
            } else {
                stored.add(A[i]);
            }
        }

        for (Integer res : stored) {
            System.out.println(res);
        }
    }

    static int FrogJmp(int x, int y, int d) {
        int distanceBetween = y - x;
        int jumps = distanceBetween / d;

        jumps++;

        return jumps;
    }

    /*
     * Compute number of distinct values in an array.
     * - create hashset
     * - add elements to hashset
     * - return size of hashset
     */
    static int Distinct(int[] A) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }
        return set.size();
    }

    /*
     * Maximize A[P] * A[Q] * A[R] for any triplet (P, Q, R).
     * need to look for:
     * 1 first 3 elements - highest values
     * 2 last 3 elements - lowest values with 2 large negatives times a positive
     * 3 first elements and last two elements
     * 4 first two elements and last element
     * 
     * - create a list and add all input array elements in it - list not set cause
     * there can be repeating numbers
     * - sort the list
     * - find the max of the 4 cases
     */
    static int MaxProductOfThree(int[] A) {
        ArrayList<Integer> stored = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            stored.add(A[i]);
        }
        Collections.sort(stored);

        int p1, p2, p3, p4 = 0;
        p1 = stored.get(0) * stored.get(1) * stored.get(2);
        p2 = stored.get(stored.size() - 1) * stored.get(stored.size() - 2) * stored.get(stored.size() - 3);
        p3 = stored.get(0) * stored.get(stored.size() - 1) * stored.get(stored.size() - 2);
        p4 = stored.get(0) * stored.get(1) * stored.get(stored.size() - 1);

        int max1 = Math.max(p1, p2);
        int max2 = Math.max(p3, p4);

        return Math.max(max1, max2);
    }

    /*
     * Determine whether a triangle can be built from a given set of edges.
     * - A triplet(P,Q,R)
     * {1,2,5,8,10,20}
     * - create a arraylist and add elements from input array in it and sort it
     * - then go through the arraylist
     * - start from the end and go forward
     * -check if the previous two elements are bigger than the current one we're
     * looking at
     * - if: return 1
     * - else: return 0
     */
    static int Triangle(int[] A) {
        ArrayList<Integer> aList = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            aList.add(A[i]);
        }
        Collections.sort(aList);
        for (int i = aList.size() - 1; i >= 2; i--) {
            if (aList.get(i - 1) + aList.get(i - 2) > aList.get(i)) {
                return 1;
            }
        }
        return 0;
    }

    /*
     * Determine whether a given string of parentheses (multiple types) is properly
     * nested.
     * - no odd number characters or empty chracters
     * - loop through string, start at middle
     * - go forward and backwards and compare both elements
     * - if they match return 1
     * - if not return 0
     * OR
     * - use stack
     * - store all opening brackets in stack
     * - pop them off if the stack contains closing brackets for them
     * - if the stack is empty in the end, return 1, if not return 0
     * 
     */
    static int Brackets(String S) {
        // check if String has even characters or not
        if (S.length() % 2 != 0) {
            return 0;
        }
        Stack<Character> charStack = new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            // if the current char is a open bracket, put it in the stack
            if (isOpen(c)) {
                charStack.push(c);
            } else {

                // has no opening brackets so not balanced
                if (charStack.size() == 0) {
                    return 0;
                }
                // the elements not added to the stack because they're not
                // opening brackets, we compare that element that the index
                // from the loop to the ones in the stack - in order
                char c2 = charStack.pop();
                if (isMatch(c2, c)) {
                    continue;
                } else {
                    return 0;
                }
            }
        }
        // after the for loop, if the size is 0, means all brackets are match and its
        // balanced
        if (charStack.size() == 0) {
            return 1;
        }
        return 0;
    }

    static boolean isOpen(char c) {
        if (c == '(' || c == '[' || c == '{') {
            return true;
        }
        return false;
    }

    static boolean isMatch(char c1, char c2) {
        if (c1 == '(' && c2 == ')')
            return true;
        if (c1 == '{' && c2 == '}')
            return true;
        if (c1 == '[' && c2 == ']')
            return true;

        return false;
    }

    /*
     * Find an index of an array such that its value occurs at more than half of
     * indices in the array.
     * - create a empty hashmap
     * - loop through array
     * - if current element in array is NOT in map -> insert it in and give it a 1
     * value
     * - if it IS -> increment that value in the map for that element
     * - go through the map
     * - for each entry, get the value and see if it is array.length/2
     * - if it is -> loop through the array and return the index of that element
     * from the map
     * 
     */
    static ArrayList<Integer> Dominator(int[] A) {
        Map<Integer, Integer> D = new HashMap<Integer, Integer>();
        for (int a : A) {
            if (D.containsKey(a)) {
                D.put(a, D.get(a) + 1);
            } else {
                D.put(a, 1);
            }
        }
        int n = A.length;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> d : D.entrySet()) {
            if (d.getValue() > n / 2) {
                for (int i = 0; i < n; i++) {
                    if (A[i] == d.getKey()) {
                        arr.add(i);
                    }
                }
            }
        }

        if (arr.isEmpty()) {
            return null;
        } else {
            return arr;
        }
    }

    /*
     * Given a log of stock prices compute the maximum possible earning.
     * - create loop starting from end
     * - create loop start from beginning
     * - subtract as you go and save to a max variable
     */
    static int MaxProfit(int[] A) {
        int max = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = 0; j < A.length; j++) {
                int sub = A[i] - A[j];
                max = Math.max(sub, max);
            }
        }
        return max;
    }

    static int MaxProfit2(int[] A) {
        int max = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                max += A[i] - A[i - 1];
            }
        }
        return max;
    }

    static int MaxProfit3(int[] A) {
        int[] deltaA = new int[A.length];
        deltaA[0] = 0;
        for (int i = 1; i < A.length; i++) {
            deltaA[i] = A[i] - A[i - 1];
        }
        int absMax = deltaA[0];
        int localMax = deltaA[0];
        int currentElement = 0;
        int nextSum = 0;
        for (int i = 1; i < deltaA.length; i++) {
            currentElement = deltaA[i];
            nextSum = localMax + currentElement;
            localMax = Math.max(deltaA[i], nextSum);
            absMax = Math.max(absMax, localMax);
        }
        if (absMax > 0) {
            return absMax;
        }
        return 0;
    }

    /*
     * Find a maximum sum of a compact subsequence of array elements.
     * - keep track of the current element + previous element
     * - compare it to the current element to get the local max or the max so far
     * - then keep track of the local maxes by saving the highest local max as
     * absolute max
     * {3,2,-6,4,0}
     * A[i] sum localMax absmax
     * 3 0 3 3
     * 2 5 5 5
     * -6 -1 -1 5
     * 4 3 4 5
     * 0 4 4 5
     */
    static int MaxSliceSum(int[] A) {
        int localMax = A[0];
        int absoluteMax = A[0];
        int currentElement = 0;
        int sum = 0;
        for (int i = 1; i < A.length; i++) {
            currentElement = A[i];
            sum = localMax + currentElement;
            localMax = Math.max(currentElement, sum);
            absoluteMax = Math.max(localMax, absoluteMax);
        }
        return absoluteMax;
    }

    /*
     * Find the maximal sum of any double slice.
     * { 3, 2, 6, -1, 4, 5, -1, 2 }
     * K1 { _ , 2, 8, 7, 11, 16, 15, 17}
     * K2 { 0, 16, 14, 8, 9, 5, 0, _}
     * max - 17
     * K1[0]+K2[2]
     * K1[1]+K2[3]
     * K1[2]+K2[4]
     * K1[3]+K2[5]
     */
    static int MaxDoubleSliceSum(int[] A) {
        int[] k1 = new int[A.length];
        int[] k2 = new int[A.length];
        int max = 0;

        for (int i = 1; i < A.length - 1; i++) {
            k1[i] = Math.max(k1[i - 1] + A[i], 0);
        }
        for (int i = A.length - 2; i > 0; i--) {
            k2[i] = Math.max(k2[i + 1] + A[i], 0);
        }
        for (int i = 1; i < A.length - 1; i++) {
            max = Math.max(k1[i - 1] + k2[i + 1], max);
        }
        return max;
    }

    static int ArrListLen(int[] A) {
        int counter = 1;
        int index = 0;
        while ((index = A[index]) != -1) {
            counter++;
        }
        return counter;
    }

    /*
     * - first make all numbers positive
     * - sort the array
     * - loop through and increment count on condition
     * - condition: if current - previous > 0 -> increment
     */
    static int AbsDistinct(int[] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            if (A[i] < 0) {
                A[i] *= -1;
            } else {
                break;
            }
        }
        Arrays.sort(A);
        int r = 1;
        for (int i = 1; i < n; i++) {
            if ((long) A[i] - A[i - 1] > 0) {
                r++;
            }
        }
        return r;
    }

    static int DistinctSlices(int M, int[] A) {
        int count = 0;
        boolean[] memo = new boolean[M + 1];

        int i = 0;
        int j = 0;

        while (i < A.length && j < A.length) {
            if (memo[A[j]]) {
                memo[A[i]] = false;
                i++;
            } else {
                memo[A[j]] = true;
                count += (j - i + 1);
                j++;
            }
        }
        return count;
    }

    //Simple interview question to add duplicates in an array 
    static int stupid(int[] A) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int sum = 0;
        for (int m : map.keySet()) {
            if (map.get(m) == 1) {
                sum += m;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        //System.out.println(binaryGap(1041));

        int nums[] = { 1, 2, 3, 4, 5, 6, 7 };
        int K = 3;
        K = K % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, K - 1);
        reverse(nums, K, nums.length - 1);
        // for(int i = 0; i < nums.length; i++) {
        // System.out.print(nums[i] + " ");
        // }

        int[] nums2 = { 9, 3, 9, 3, 9, 7, 9 };
        // OddOccurancesInArray(nums2);

        // System.out.println(FrogJmp(10, 85, 30));

        int[] nums3 = { 2, 1, 1, 2, 3, 1, 6 };
        // System.out.println(Distinct(nums3));

        int[] nums4 = { -3, 1, 2, -2, 5, 6 };
        // System.out.println(MaxProductOfThree(nums4));

        int[] nums5 = { 10, 2, 5, 1, 8, 20 };
        // System.out.println(Triangle(nums5));

        String str = "(()(())())";
        // System.out.println(Brackets(str));

        int[] nums6 = { 3, 4, 3, 2, 3, -1, 3, 3 };
        // System.out.println(Dominator(nums6));

        int[] nums7 = { 23171, 21011, 21123, 21366, 21013, 21367 };
        // System.out.println(MaxProfit3(nums7));

        int[] nums8 = { 3, 2, -6, 4, 0 };
        // System.out.println(MaxSliceSum(nums8));

        int[] nums9 = { 3, 2, 6, -1, 4, 5, -1, 2 };
        // System.out.println(MaxDoubleSliceSum(nums9));

        int[] nums10 = { 1, 4, -1, 3, 2 };
        // System.out.println(ArrListLen(nums10));

        int[] nums11 = { -5, -3, -1, 0, 3, 6 };
        // System.out.println(AbsDistinct(nums11));

        int[] nums12 = { 3, 4, 5, 5, 2 };
        // System.out.println(DistinctSlices(6, nums12));

        int[] nums13 = { 1, 2, 3, 2 };
        //System.out.println(stupid(nums13));

    }
}
