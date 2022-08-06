package logic.reader;

import logic.shop.ConsignmentNote;
import logic.shop.Shop;

/**Abstract class for read input from keyboard
 * Has two inheritance class for read info about Manufacturer and for read info about Souvenirs*/
public abstract class Reader {
    protected ConsignmentNote consignmentNote = new ConsignmentNote();
    protected Shop shop = consignmentNote.getShop();

    public ConsignmentNote getConsignmentNote() {
        return consignmentNote;
    }

    public Shop getShop() {
        return shop;
    }

    public void setConsignmentNote(ConsignmentNote consignmentNote) {
        this.consignmentNote = consignmentNote;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public abstract void add();
    public abstract void remove();
}
