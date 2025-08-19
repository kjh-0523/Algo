import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = new int[7];
        for(int i = 1; i < 7; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }

        int sum = 0;
        // 6x6 박스
        sum += nums[6];

        // 5x5 박스
        sum += nums[5];
        nums[1] = Math.max(0, nums[1] - nums[5] * 11);

        // 4x4 박스
        sum += nums[4];
        int space2 = nums[4] * 5;
        if(nums[2] >= space2) {
            nums[2] -= space2;
        } else {
            int remaining2 = space2 - nums[2];
            nums[2] = 0;
            nums[1] = Math.max(0, nums[1] - remaining2 * 4);
        }

        // 3x3 박스
        sum += nums[3] / 4;
        int remaining3 = nums[3] % 4;
        if(remaining3 > 0) {
            sum++;
            int[] spaceFor2 = {0, 5, 3, 1};
            int[] spaceFor1 = {0, 7, 6, 5};
            int use2 = Math.min(nums[2], spaceFor2[remaining3]);
            nums[2] -= use2;
            int use1 = Math.min(nums[1], spaceFor1[remaining3] + (spaceFor2[remaining3] - use2) * 4);
            nums[1] -= use1;
        }

        // 2x2 박스
        sum += nums[2] / 9;
        int rem2 = nums[2] % 9;
        if(rem2 > 0) {
            sum++;
            nums[1] = Math.max(0, nums[1] - (36 - rem2 * 4));
        }

        // 1x1 박스
        sum += nums[1] / 36;
        if(nums[1] % 36 > 0) sum++;

        System.out.println(sum);
    }
}
