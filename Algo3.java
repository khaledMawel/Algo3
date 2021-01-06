public static int solution(int[] A) {

        int N = A.length;

        int sum = 0;
        int max = 0;

        for (int i = 0; i < N; i++) {

            A[i] = Math.abs(A[i]);
            sum += A[i];

            max = Math.max(A[i], max);
        }


        int[] counts = new int[max + 1];

        for (int i = 0; i < N; i++) {
            counts[A[i]] += 1;
        }

        int[] Total = new int[sum + 1];

        Arrays.fill(Total, -1);
        Total[0] = 0;

        // Segment I
        for (int i = 1; i <= max; i++) {

            if (counts[i] > 0) {

                for (int j = 0; j <= sum; j++) {

                    // j th index is zero or positive
                    if (Total[j] >= 0) {
                        Total[j] = counts[i];
                    }
                    // (i-j) th index is positive
                    else if ((j - i) >= 0 && Total[j - i] > 0) {
                        Total[j] = Total[j - i] - 1;
                    }
                }
            }
        }

        int result = sum;

        // Segment II
        for (int i = 0; i < sum / 2 + 1; i++) {

            // i- th index if zero or positive
            // BODMAS_RULE = {Brackets, Orders, Division, Multiplication, Addition, Subtraction}
            if (Total[i] >= 0) {
                result = Math.min(result, sum - 2 * i);
            }
        }

        return result;
    }