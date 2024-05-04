package kadai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PaymentGame {
	//mainメソッド
	public static void main(String[] args){
		outer:
			while(true) {
				System.out.println("(プレイヤー名を入力してください)");
				String playerName= inputString();
				System.out.println("(出題数を入力してください)");
				int question;
				int count = 0;
				while(true) {
					question= inputNumber();
					if (question == 0) {
						System.out.println("(出題数は１以上です)");
						System.out.println("(再度入力してください)");
					} else {
						break;
					}
				}
				System.out.println("(難易度を選択してください)");
				System.out.println("lunatic | hard | nomal | easy");
				double startTime = 0;
				double endTime = 0;
				while(true) {
					switch (inputString()) {
					//ルナティックモード
					case "lunatic" :
						System.out.println("\n(遊び方)\n----------------------------------------------------------------------------");
						System.out.println("出題された金額に対して、支払う現金の枚数が最小かつ\nお釣りが出ないように値を入力してください。");
						System.out.println("本難易度では、表示された貨幣以上のお金(2000円札含む)が支払われたものとし、\nお釣りの出ない最小の枚数を入力してください。");
						System.out.println("----------------------------------------------------------------------------");
						System.out.println("\n始める場合は何か入力してください。");
						inputString();
						startTime = (System.currentTimeMillis() / 1000);
						count = lunaticMord(question);
						endTime = (System.currentTimeMillis() / 1000);
						break;
						//ハードモード
					case "hard" :
						System.out.println("\n(遊び方)\n---------------------------------------------------");
						System.out.println("出題された金額に対して、支払う現金の枚数が最小かつ\nお釣りが出ないように値を入力してください。");
						System.out.println("---------------------------------------------------");
						System.out.println("\n始める場合は何か入力してください。");
						inputString();
						startTime = (System.currentTimeMillis() / 1000);
						count = hardMord(question);
						endTime = (System.currentTimeMillis() / 1000);
						break;
						//ノーマルモード
					case "nomal" :
						System.out.println("\n(遊び方)\n---------------------------------------------------");
						System.out.println("出題された金額に対して、支払う現金の枚数が最小かつ\nお釣りが出ないように値を入力してください。");
						System.out.println("---------------------------------------------------");
						System.out.println("\n始める場合は何か入力してください。");
						inputString();
						startTime = (System.currentTimeMillis() / 1000);
						count = nomalMord(question);
						endTime = (System.currentTimeMillis() / 1000);
						break;
						//イージーモード
					case "easy" :
						System.out.println("\n(遊び方)\n---------------------------------------------------");
						System.out.println("出題された金額に対して、支払う現金の枚数が最小かつ\nお釣りが出ないように値を入力してください。");
						System.out.println("---------------------------------------------------");
						System.out.println("\n始める場合は何か入力してください。");
						inputString();
						startTime = (System.currentTimeMillis() / 1000);
						count = easyMord(question);
						endTime = (System.currentTimeMillis() / 1000);
						break;
					default :
						System.out.println("(難易度選択が正しくありません。)");
						System.out.println("(再度入力してください。)");
						continue;
					}
					break;
				}
				double elapsedTime = endTime - startTime;
				double averageTime = elapsedTime / question;
				double answerRate = (double) (count / question) * 100;
				char rank = rank(answerRate, averageTime);
				System.out.println("\nゲーム終了\n\n(結果)\n正解率:" + answerRate + "%");
				System.out.println("経過時間: " + elapsedTime + " 秒");
				System.out.println("平均回答時間: " + averageTime + " 秒");
				System.out.println("ランク：" + rank);
				System.out.println("評価：" + comment(rank));
				System.out.println("\n---------------------------------------------------\n");
				System.out.println("(もう一度遊びますか？)");
				System.out.println("はい→y | いいえ→n");
				while(true) {
					char[] c = inputString().toCharArray();
					try {
						switch (c[0]) {
						case 'y' :
							continue outer;
						case 'n' :
							System.out.println(playerName + "さん、お疲れ様でした。");
							break outer;
						default :
							System.out.println("入力された文字が正しくありません。");
							System.out.println("もう一度遊ぶ場合はy,終了する場合はnを入力してください。");
							break;
						}
					} catch(ArrayIndexOutOfBoundsException e) {
						System.out.println("入力された文字が正しくありません。");
						System.out.println("もう一度遊ぶ場合はy,終了する場合はnを入力してください。");
					}
				}
			}
	}

	//文字列入力メソッド
	private static String inputString () {
		String string;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				string = reader.readLine();
				break;
			} catch (IOException e) {
				System.out.println(e);
			} 
		}
		return  string;
	}

	//数字入力メソッド
	private static int inputNumber () {
		int numbers;

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				String line = reader.readLine();
				numbers = Integer.parseInt(line);
				if(numbers < 0) {
					System.out.println("(数字の小さすぎます)");
					System.out.println("(0～2147483647の間の値を再度入力してください)");
					continue;
				}
				break;
			} catch (IOException e) {
				System.out.println(e);
			} catch (NumberFormatException e) {
				System.out.println("(数字の形式が正しくありません)");
				System.out.println("(0～2147483647の間の値を再度入力してください)");
			}
		}
		return  numbers;
	}

	//lunaticモードメッド
	private static int lunaticMord(int question) {
		System.out.println("\nゲームスタート！\n");
		int[] okane = {10000, 5000, 2000, 1000, 500, 100, 50, 10, 5, 1};
		int count = 0;
		for(int i = 1; i <= question; i++) {
			int j = (int)(Math.random() * 10);
			System.out.println("---------------------------------------------------");
			System.out.println("(第" + i + "問)");
			int questionMoney = randomMoney();
			System.out.println("支払い額は" + questionMoney + "円");
			if(j <= 3) {
				System.out.print(okane[j] + "円札:");
			}else if(j >= 4) {
				System.out.print(okane[j] + "円玉:");
			}
			int input = inputNumber();
			if (input == lunaticAnswer(j,questionMoney)) {
				System.out.println("正解!!");
				count++;
			}
			else {
				System.out.println("不正解");
			}
		}
		System.out.println("---------------------------------------------------");
		return count;
	}

	//hardモードメソッド
	private static int hardMord(int question) {
		System.out.println("\nゲームスタート！\n");
		int[] okane = {10000, 5000, 2000, 1000, 500, 100, 50, 10, 5, 1};
		int count = 0;
		outer:
			for(int i = 1; i <= question; i++) {
				System.out.println("---------------------------------------------------");
				System.out.println("(第" + i + "問)");
				int questionMoney = randomMoney();
				System.out.println("支払い額は" + questionMoney + "円");
				for (int j = 0; j < okane.length; j++) {
					if(j <= 3) {
						System.out.print(okane[j] + "円札:");
					}else if(j >= 4) {
						System.out.print(okane[j] + "円玉:");
					}
					int input = inputNumber();
					if (input == questionMoney/okane[j]) {
						questionMoney -= input * okane[j];
					}
					else {
						System.out.println("不正解");
						continue outer;
					}
				}
				System.out.println("\n正解!!\n");
				count++;
			}
		System.out.println("---------------------------------------------------");
		return count;
	}

	//nomalモードメソッド
	private static int nomalMord(int question) {
		System.out.println("\nゲームスタート！\n");
		int[] okane = {10000, 5000, 1000, 500, 100, 50, 10, 5, 1};
		int count = 0;
		outer:
			for(int i = 1; i <= question; i++) {
				System.out.println("---------------------------------------------------");
				System.out.println("(第" + i + "問)");
				int questionMoney = randomMoney();
				System.out.println("支払い額は" + questionMoney + "円");
				for (int j = 0; j < okane.length; j++) {
					if(j <= 2) {
						System.out.print(okane[j] + "円札:");
					}else if(j >= 3) {
						System.out.print(okane[j] + "円玉:");
					}
					int input = inputNumber();
					if (input == questionMoney/okane[j]) {
						questionMoney -= input * okane[j];
					}
					else {
						System.out.println("不正解");
						continue outer;
					}
				}
				System.out.println("\n正解!!\n");
				count++;
			}
		System.out.println("---------------------------------------------------");
		return count;
	}

	//easyモードメソッド
	private static int easyMord(int question) {
		System.out.println("\nゲームスタート！\n");
		int[] okane = {10000, 5000, 1000, 500, 100, 50, 10, 5, 1};
		int count = 0;
		outer:
			for(int i = 1; i <= question; i++) {
				System.out.println("---------------------------------------------------");
				System.out.println("(第" + i + "問)");
				int questionMoney = randomMoney();
				System.out.println("支払い額は" + questionMoney + "円");
				for (int j = 0; j < okane.length; j++) {
					if(questionMoney / okane[j] > 0) {
						if(j <= 2) {
							System.out.print(okane[j] + "円札:");
						}else if(j >= 3) {
							System.out.print(okane[j] + "円玉:");
						}
						int input = inputNumber();
						if (input == questionMoney/okane[j]) {
							questionMoney -= input * okane[j];
						}
						else {
							System.out.println("不正解");
							continue outer;
						}
					}
				}
				System.out.println("\n正解!!\n");
				count++;
			}
		System.out.println("---------------------------------------------------");
		return count;
	}

	//lunaticモード解答メソッド
	private static int lunaticAnswer(int i,int questionMoney) {
		int[] okane = {10000, 5000, 2000, 1000, 500, 100, 50, 10, 5, 1};
		int answer;
		for(int j = 0; j < i; j++) {
			questionMoney -= okane[j] * (questionMoney / okane[j]);
		}
		answer = questionMoney / okane[i];
		return answer;
	}

	//出題メソッド
	private static int randomMoney () {
		return   (int)(Math.random() * 100000);
	}

	//ランク判定メソッド
	private static char rank(double answerRate, double averageTime) {
		int ten = 0;
		if(averageTime <= 10) {
			ten = (int) (100 * (0.8 * (answerRate / 100) + 0.2));
		} else {
			ten = (int) (100 * (0.8 * (answerRate / 100) + 0.2 * (10 / averageTime)));
		}
		char rank = 0;
		if(ten == 100) {
			rank = 'S';
		}else if(ten >= 80) {
			rank = 'A';
		}else if(ten >= 60) {
			rank = 'B';
		}else if(ten >= 40) {
			rank = 'C';
		}else if(ten >= 20) {
			rank = 'D';
		}else if(ten >= 10) {
			rank = 'E';
		}else if(ten >= 0) {
			rank = 'F';
		}
		return rank;
	}

	//コメント判定メソッド
	private static String comment(char c) {
		switch (c) {
		case 'S':
			return "完璧！！";
		case 'A':
			return "惜しい！あと一歩！";
		case 'B':
			return "もう少し頑張ろう！";
		case 'C':
			return "もっと頑張ろう。";
		case 'D':
			return "まだまだ、これから！";
		case 'E':
			return "ダメ";
		case 'F':
			return "ダメダメ";
		default:
			return "警告：ランクの値が異常";
		}
	}
}