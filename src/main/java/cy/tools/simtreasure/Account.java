
package cy.tools.simtreasure;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.joda.time.LocalDate;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Collections2;
import java.util.Collections;

public class Account {

    public Account() {
        this.deposits = new ArrayList<Deposit>();

        this.init();

    }

    private List<Deposit> deposits;

    public ImmutableList<Deposit> getDeposites() {
        return ImmutableList.copyOf(this.deposits);
        // return Collections.unmodifiableList(this.deposits);
    }

    public void save(int amount, LocalDate start) {
        this.save(amount, start, Terms.Short, Terms.Short);
    }

    public void save(int amount, LocalDate start, Terms term, Terms resaveTerm) {
        Deposit dep = new FixTimeDeposit();
        dep.setPrincipalAmount(BigDecimal.valueOf(amount));
        dep.setDuration(term);
        dep.setTermRate(Rates.getRate(term));
        dep.setDurationStart(start);
        dep.setAutoResave(resaveTerm != null);
        dep.setAutoResaveDuration(resaveTerm);
        this.deposits.add(dep);
    }

    public void init() {
        this.save(10000, new LocalDate(2014, 1, 10), Terms.M3, Terms.M3);
        this.save(10000, new LocalDate(2014, 2, 10), Terms.M3, Terms.M3);
        this.save(10000, new LocalDate(2014, 3, 10), Terms.M3, Terms.M3);
        this.save(10000, new LocalDate(2014, 4, 10), Terms.M3, Terms.M3);

        this.save(11000, new LocalDate(2014, 5, 10), Terms.M6, Terms.M6);
        this.save(11000, new LocalDate(2014, 6, 10), Terms.M6, Terms.M6);
        this.save(11000, new LocalDate(2014, 7, 10), Terms.M6, Terms.M3);
        this.save(11000, new LocalDate(2014, 8, 10), Terms.M6, Terms.M3);

        this.save(11000, new LocalDate(2014, 9, 10), Terms.Y1, Terms.M3);
        this.save(11000, new LocalDate(2014, 10, 10), Terms.Y1, Terms.M3);
        this.save(11000, new LocalDate(2014, 11, 10), Terms.Y1, Terms.M6);
        this.save(11000, new LocalDate(2014, 12, 10), Terms.Y1, Terms.M6);

        this.save(11000, new LocalDate(2015, 1, 10), Terms.Y1, Terms.Y1);
        this.save(11000, new LocalDate(2015, 2, 10), Terms.Y1, Terms.Y1);

        this.save(11000, new LocalDate(2015, 3, 10), Terms.Y2, Terms.M3);
        this.save(11000, new LocalDate(2015, 4, 10), Terms.Y2, Terms.M3);
        this.save(11000, new LocalDate(2015, 5, 10), Terms.Y2, Terms.M6);
        this.save(11000, new LocalDate(2015, 6, 10), Terms.Y2, Terms.M6);
        this.save(11000, new LocalDate(2015, 7, 10), Terms.Y2, Terms.Y1);
        this.save(11000, new LocalDate(2015, 8, 10), Terms.Y2, Terms.Y1);
        this.save(11000, new LocalDate(2015, 9, 10), Terms.Y2, Terms.Y2);
        this.save(11000, new LocalDate(2015, 10, 10), Terms.Y2, Terms.Y2);
        this.save(11000, new LocalDate(2015, 11, 10), Terms.Y3, Terms.Y1);
        this.save(11000, new LocalDate(2015, 12, 10), Terms.Y3, Terms.Y1);

        this.save(150000, new LocalDate(2014, 2, 10), Terms.M3, Terms.M3);
        this.save(200000, new LocalDate(2015, 5, 10), Terms.M6, Terms.M6);
        this.save(150000, new LocalDate(2016, 5, 10), Terms.M6, Terms.M6);
    }

    public List<Deposit> sortedDeposits() {
        ArrayList<Deposit> list = Lists.newArrayList(this.deposits);
        Collections.sort(list, new Comparator<Deposit>(){

            public int compare(Deposit o1, Deposit o2) {
                return 0;
            }

        });

        Ordering<Deposit> ordering = Ordering.compound(Lists.newArrayList(new Comparator<Deposit>(){
            public int compare(Deposit o1, Deposit o2) {
                return 0;
            }

        }));
        //Collections.sort(list, ordering);

        return ImmutableList.copyOf(list);
    }
}
