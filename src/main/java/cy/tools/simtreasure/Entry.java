package cy.tools.simtreasure;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.time.LocalDate;
import org.junit.Assert;

public class Entry {

    public static void main(String[] args) {
        FixTimeDeposit dep = new FixTimeDeposit();
        dep.autoResave = true;
        dep.autoResaveDuration = Terms.Y1;
        dep.duration = Terms.Y1;
        dep.termRate = Rates.currentY1FixTermRate;
        dep.durationStart = new LocalDate(2016, 1, 1);
        dep.principalAmount = BigDecimal.valueOf(10000D);
        BigDecimal withdrawl = dep.withdrawl(new LocalDate(2017, 1, 2));
        // echo "scale=16; 10000 * (1 + 0.0175 * 12 / 12 ) * ( 1 + 0.0030 * 1 / 365 )" | bc

        Assert.assertEquals(withdrawl, new BigDecimal("10175.0836301369853675").setScale(2, RoundingMode.HALF_UP));

    }
}
