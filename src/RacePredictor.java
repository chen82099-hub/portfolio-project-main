import java.util.ArrayList;
import java.util.List;

public class RacePredictor {

    // --- 1. 字段 (Fields): 证明你可以存储数据 ---
    private List<Integer> history; 
    private String driverName;

    public RacePredictor(String name) {
        this.driverName = name;
        this.history = new ArrayList<>();
    }

    // --- 2. 核心方法 (Kernel Methods): 你在 Part 1 设计的方法 ---
    public void enterResult(int position) {
        this.history.add(position); // 添加比赛结果 [cite: 132]
    }

    public int historySize() {
        return this.history.size(); // 返回数据量 [cite: 134]
    }

    // --- 3. 次要方法 (Secondary Methods): 证明逻辑可行性 ---
    public double predictPodium() {
        if (this.historySize() == 0) return 0.0;
        
        double sum = 0;
        for (int pos : this.history) {
            sum += pos;
        }
        double average = sum / this.historySize();
        // 如果平均排名前三，胜率较高 [cite: 136]
        return average <= 3.0 ? 0.85 : 0.25;
    }

    // --- 4. Main 方法: 运行演示 ---
    public static void main(String[] args) {
        RacePredictor predictor = new RacePredictor("Max Verstappen");
        predictor.enterResult(1); 
        predictor.enterResult(2); 
        
        System.out.println("Driver: " + predictor.driverName);
        System.out.println("Points Count: " + predictor.historySize());
        System.out.println("Podium Probability: " + predictor.predictPodium());
    }
}