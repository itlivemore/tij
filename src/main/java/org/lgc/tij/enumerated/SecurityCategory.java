package org.lgc.tij.enumerated;

import net.mindview.util.Enums;

/**
 * 将一个enum嵌套在另一个enum内
 * Created by laigc on 2017/3/5.
 */
enum SecurityCategory {
    STOCK(Security.Stock.class),
    BOND(Security.Bond.class);

    Security[] values;

    SecurityCategory(Class<? extends Security> kind) {
        values = kind.getEnumConstants();
    }

    interface Security {
        enum Stock implements Security {
            SHORT, LONG, MARGIN
        }

        enum Bond implements Security {
            MUNICIPAL, JUNK
        }
    }

    public Security randomSelection() {
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            SecurityCategory securityCategory = Enums.random(SecurityCategory.class);
            System.out.println(securityCategory + ": " + securityCategory.randomSelection());
        }
    }
}
