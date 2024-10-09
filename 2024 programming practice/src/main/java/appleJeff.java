

public class appleJeff {public boolean red;
    public boolean silly;
    public int flammable;
    public static String name = "appleJeff";

    public void bigBoom(Integer canBoom) {
        if (canBoom > 12) {
            System.out.println("boom");
        } else {
            System.out.println("no boom");
        }
        
    }
    public appleJeff(boolean Red, boolean Silly, int Flammable) {
        this.red = Red;
        this.silly = Silly;
        this.flammable = Flammable;
    }

    public static void main(String[] args) {
        appleJeff appleJeff1 = new appleJeff(true, false, 36);
        appleJeff pearJeff2 = new appleJeff(false, true, 3);
        appleJeff1.bigBoom(appleJeff1.flammable);
        pearJeff2.bigBoom(pearJeff2.flammable);

    }
}
