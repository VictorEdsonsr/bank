package model.entities;

public enum Currency {
    USD(1), EUR(2), JPY(3), RS(4);

    private final int id;

    Currency(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
