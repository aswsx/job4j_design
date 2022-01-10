package ru.job4j.lsp.lspex;

public class LSPBreakingExamples {
    /**
     * 1. Класс Breakfast нарушает принцип LSP поскольку для выполнения метода в классе-наследнике
     * необходимо усиление предусловий класса-родителя
     */
    static class Breakfast {
        int eggs = 2;
        int cupsOfMilk = 1;

        public void omelette() {
            if (eggs > 1 && cupsOfMilk > 0) {
                int omelette = eggs + cupsOfMilk;
            } else {
                throw new IllegalArgumentException("need more goods");
            }
        }

        static class TwoPersonBreakfast extends Breakfast {

            public void doubleOmelette() {
                if (eggs > 3 && cupsOfMilk > 1) {
                    int doubleOmelette = eggs + cupsOfMilk;
                } else {
                    throw new IllegalArgumentException("need more food");
                }
            }
        }
    }

    /**
     * 2. В классе Shop нарушен принцип LSP, поскольку в классе наследнике ослаблено постусловие
     * проверки возраста при продаже товаров, что дает возможность совершить продажу
     * алкоголя несовершеннолетнему покупателю.
     * <p>
     * 3. Также в этом классе опущено условие проверки возраста  при продаже сигарет,
     * что также является нарушением принципа LSP. Все условия базового класса должны быть
     * сохранены в наследниках
     */
    static class Shop {
        private int minAlcoAge = 21;
        int minSigAge = 16;
        int payment;
        int goodsQty;
        int customerAge;

        public Shop() {
        }

        public Shop(int minAlcoAge, int minSigAge) {
            this.minAlcoAge = minAlcoAge;
            this.minSigAge = minSigAge;
        }

        public void sale() {
            if (customerAge < minAlcoAge) {
                throw new IllegalArgumentException("alcohol sale forbidden");
            }
            if (customerAge < minSigAge) {
                throw new IllegalArgumentException("sigarettes sale forbidden");
            }
            int buy = goodsQty * payment;
        }

        static class BadShop extends Shop {
            int minAlcoAge = 15;

            @Override
            public void sale() {
                if (customerAge < minAlcoAge) {
                    throw new IllegalArgumentException("alcohol sale forbidden");
                }
                int buy = goodsQty * payment;
            }
        }
    }
}
