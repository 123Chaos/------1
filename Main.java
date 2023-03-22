/**
 * @author 任铭20337231
 * @Description 个人所得税计算器
 * @Date 下午12:05 13/3/2023
 * @Param 
 * @return 
 **/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int opNum = 0;
        try {
            opNum = opInterface();
        } catch (Exception e) {
            System.out.println("Wrong input!");
            System.exit(1);
        }
        taxHelper(opNum);
        System.out.println("thanks for your using!");
    }
/**
 * @Author 任铭20337231
 * @Description 这个方法作用是设计用户交互的UI面板
 * @Date 下午12:06 13/3/2023
 * @Param []
 * @return 用户选择的税收模板号
 **/
    public static int opInterface() {
        System.out.println("Please choose a pattern:");
        System.out.println("1. standard 2022.ver");
        System.out.println("2. standard 2019.ver");
        System.out.println("3. standard 2077.ver");
        System.out.println("4. standard your.ver");
        System.out.println("0. exit");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
/**
 * @Author 任铭20337231
 * @Description 这个方法作用是计算税以及返回缴税值和剩余工资
 * @Date 下午12:07 13/3/2023
 * @Param [salary, standard, taxRate, sPoint]
 * @return void
 **/
    public static void taxComputer(double salary, double[] standard, double[] taxRate, double sPoint) {
        System.out.println("You salary is(before tax): " + salary);
        double taxSalary = salary - sPoint; // 求出用户的应纳税所得额
        if (taxSalary <= 0) {
            System.out.println("It seems you don't need to pay any tax. Life sucks sometime, right? Try go pick up yourself.");
            return;
        }
        for (int i = standard.length - 1; i >= 0; i--) {
            // 如果应纳税所得额在对应区间
            if (taxSalary >= standard[standard.length - 1] || i != standard.length - 1 && taxSalary >= standard[i] && taxSalary < standard[i + 1]) {
                int curLevel = i;
                double totalTax = 0;
                totalTax += (taxSalary - standard[curLevel]) * taxRate[curLevel];
//                System.out.println(totalTax);
                while (--curLevel >= 0) {
                    totalTax += (standard[curLevel + 1] - standard[curLevel]) * taxRate[curLevel];
                    System.out.println((standard[curLevel + 1] - standard[curLevel]) * taxRate[curLevel]);
                }
                System.out.println("Your tax is " + totalTax + ".");
                System.out.println("The rest of your salary is " + (salary - totalTax) + ".");
                System.out.println("Enjoy your life!");
                break;
            }
        }
    }
/**
 * @Author 任铭20337231
 * @Description 这个函数作用是根据接受的opNum进行检错和执行
 * @Date 下午12:07 13/3/2023
 * @Param [opNum]
 * @return void
 **/
    public static void taxHelper(int opNum) {
        while (opNum != 0) {
            System.out.println("Please input your salary: ");
            Scanner scanner = new Scanner(System.in);
            double salary;
            // 判断是否有猴子在键盘上乱按
            try {
                salary = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Please input right format!");
                // System.exit(1); // 如果想检错停止就用这个
                continue; // 输入错误 继续输入
            }
            // 判断是否出现资本家
            if (salary < 0) {
                System.out.println("You can't give a worker negative salary!");
                System.exit(0);
            }
            if (opNum == 1) {
                double[] standard = {0, 3000, 12000, 25000, 35000, 55000, 80000}; // 每级应纳税所得额
                double[] taxRate = {0.03, 0.1, 0.2, 0.25, 0.3, 0.35, 0.45}; // 每级税率
                double sPoint = 5000;  // 起征点
                taxComputer(salary, standard, taxRate, sPoint);
            }
            if (opNum == 2) {
                double[] standard = {0, 2000, 5000, 20000, 30000, 55000, 75000}; // 每级应纳税所得额
                double[] taxRate = {0.02, 0.1, 0.2, 0.25, 0.3, 0.35, 0.45}; // 每级税率
                double sPoint = 3500;  // 起征点
                taxComputer(salary, standard, taxRate, sPoint);
            }
            if (opNum == 3) {
                double[] standard = {0, 10000, 50000, 250000, 350000, 550000, 800000}; // 每级应纳税所得额
                double[] taxRate = {0.01, 0.11, 0.12, 0.215, 0.13, 0.135, 0.145}; // 每级税率
                double sPoint = 50000;  // 起征点
                taxComputer(salary, standard, taxRate, sPoint);
            }
            if (opNum == 4) {
                System.out.println("input taxable income level:");
                int sLevel = scanner.nextInt();
                double[] standard = new double[sLevel];
                double[] taxRate = new double[sLevel];
                System.out.println("input taxable income: (example:{[0,200),[200,300)}, you input 0 200 300)");
                for (int i = 0; i < sLevel; i++) {
                    standard[i] = scanner.nextDouble();
                }
                System.out.println("input tax rate: (example:{0.03,0.1,0.2}, you input 0.03 0.1 0.2)");
                for (int i = 0; i < sLevel; i++) {
                    taxRate[i] = scanner.nextDouble();
                }
                System.out.println("input start_tax_point:");
                double sPoint = scanner.nextDouble();
                taxComputer(salary, standard, taxRate, sPoint);
            }
            try {
                opNum = opInterface();
            } catch (Exception e) {
                System.out.println("Wrong input!");
                System.exit(1);
            }
        }
    }
}

