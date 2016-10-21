
package cy.tools.simtreasure;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.joda.time.LocalDate;

import com.google.common.collect.Lists;

public class RedrawJudge {

    private Account ac = new Account();
    private double destination = 125000d;
    List<DepEnv> envs;

    public static class DepEnv {
        public DepEnv() {

        }

        public DepEnv(Deposit dep, LocalDate date) {
            this.dep = dep;
            this.date = date;
            this.lost = dep.lost(date).doubleValue();
            this.amount = dep.getPrincipalAmount().doubleValue();
            this.coefficient = this.lost * 10000d / amount;

        }

        Deposit dep;
        LocalDate date;
        double lost;
        double amount;
        double coefficient;
    }

    private void init(LocalDate date) {
        List<Deposit> sortedDeposits = ac.sortedDeposits();
        envs = Lists.<DepEnv>newArrayList();
        for (Deposit dep : sortedDeposits) {
            envs.add(new DepEnv(dep, date));
        }

        // sort by lost
        Collections.sort(envs, new Comparator<DepEnv>() {

            public int compare(DepEnv o1, DepEnv o2) {
                return Double.valueOf(o1.coefficient).compareTo(Double.valueOf(o2.coefficient));
            }
        });
    }

    public class Pi {
        public Pi() {
            this.destination = RedrawJudge.this.destination;
        }
        double destination;
        double volume = 0d;
        double lost = 0d;
        List<DepEnv> pis = Lists.<DepEnv>newArrayList();

        public boolean willFull(DepEnv dep) {
            return this.volume + dep.amount >= this.destination;
        }

        public void add(DepEnv dep) {
            this.pis.add(dep);
            this.volume += dep.amount;
            this.lost += dep.lost;
        }

        public void print(LocalDate date) {
            StringBuilder sb = new StringBuilder();
            sb.append("date:").append(date).append("\t");
            sb.append("lost:").append(this.lost).append("\t");
            sb.append("volume:").append(this.volume).append("\t");
            sb.append("\n");
            for(DepEnv dep : this.pis) {
                sb.append("\t").append(dep.dep.toString()).append("\n");
            }
            System.out.println(sb.toString());
        }
    }

    public void pi1(LocalDate date) {
        this.init(date);

        Pi pi = new Pi();
        for (DepEnv dep : this.envs) {
            if(! pi.willFull(dep)) {
                pi.add(dep);
            } else {
                pi.add(dep);
                break;
            }
        }
        pi.print(date);
    }

    public static void main(String[] args) {
        RedrawJudge judge = new RedrawJudge();
        judge.pi1(LocalDate.now());
    }
}
