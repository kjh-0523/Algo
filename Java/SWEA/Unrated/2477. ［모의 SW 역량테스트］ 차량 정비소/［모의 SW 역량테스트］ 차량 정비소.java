import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * [고객 클래스] 속성 id int timer state : 창구 번호, 정비 번호 [창구 배열] 고객 id [정비 배열] 고객 id [접수
	 * 대기 명단] q q 기준 : 고객 번호 낮은 순 [정비 대기 명단] q q 기준 : 먼저 기다리는 고객이 우선
	 * 
	 */

	static class Customer {
		int id, timer, state1, state2;

		public Customer(int id, int timer) {
			this.id = id;
			this.timer = timer;
		}

		public void timeReset(int t) {
			this.timer = t;
		}

		public void TimeMin() {
			this.timer -= 1;
		}

		public void setState1(int s) {
			state1 = s;
		}

		public void setState2(int s) {
			state2 = s;
		}
	}

	static int N, M, K, A, B;
	static int[] ReceptionDesk;
	static int[] RepairDesk;
	static Queue<Integer> ReceptionWaiting;
	static Queue<Integer> RepairnWaiting;
	static int[] Reception;
	static int[] Repair;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 접수 창구
			M = Integer.parseInt(st.nextToken()); // 정비 창구
			K = Integer.parseInt(st.nextToken()); // 고객 수
			A = Integer.parseInt(st.nextToken()); // 접수 창구 번호
			B = Integer.parseInt(st.nextToken()); // 정비 창구 번호
			Reception = new int[N + 1];
			ReceptionDesk = new int[N + 1];
			Repair = new int[N + 1];
			RepairDesk = new int[N + 1];
			// 접수 창구 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				Reception[i] = Integer.parseInt(st.nextToken());
			}
			// 정비 창구 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				Repair[i] = Integer.parseInt(st.nextToken());
			}
			// 고객 생성
			Customer[] customers = new Customer[K + 1];
			ReceptionWaiting = new LinkedList<>();
			RepairnWaiting = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			customers[0] = new Customer(0,0);
			for (int i = 1; i <= K; i++) {
				int time = Integer.parseInt(st.nextToken());
				customers[i] = new Customer(i, time);
				ReceptionWaiting.offer(i);
			}
			int time = 0;
			int cnt = 0;
			while (cnt < K) {
				// 정비 창구
				for (int i = 1; i <= M; i++) {
					if (RepairDesk[i] == 0)
						continue;
					if (customers[RepairDesk[i]].timer == time) {
						cnt++;
						RepairDesk[i] = 0;
					}
				}

				// 정비 대기줄
				for (int i = 1; i <= M; i++) {
					if (RepairDesk[i] == 0 && !RepairnWaiting.isEmpty()) {
						RepairDesk[i] = RepairnWaiting.poll();
						customers[RepairDesk[i]].timeReset(time+Repair[i]);
						customers[RepairDesk[i]].setState2(i);
					}
				}

				// 접수 창구
				for (int i = 1; i <= N; i++) {
					if (ReceptionDesk[i] == 0)
						continue;
					if (customers[ReceptionDesk[i]].timer == time) {
						RepairnWaiting.offer(ReceptionDesk[i]);
						ReceptionDesk[i] = 0;
					}
				}
				// 접수 대기 줄
				for (int i = 1; i <= N; i++) {
					if (ReceptionDesk[i] == 0 && !ReceptionWaiting.isEmpty() && customers[ReceptionWaiting.peek()].timer <= time) {
						ReceptionDesk[i] = ReceptionWaiting.poll();
						customers[ReceptionDesk[i]].timeReset(time+Reception[i]);
						customers[ReceptionDesk[i]].setState1(i);
					}
				}
				time++;
			}// 반복 끝
			
			int ans = 0;
			for (Customer c : customers) {
				if(c.state1 == A && c.state2 == B) {
					ans += c.id;
				}
			}
			if(ans == 0) ans =-1;
			System.out.println("#" + tc + " "+ ans);
		}
	}

}
/*
1
2 2 6 1 2
3 2
4 2
0 0 1 2 3 4
*/
