
     import java.util.Scanner;

public class AkilliRestoran {

    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;   // Izgara Tavuk
            case 2: return 120;  // Adana Kebap
            case 3: return 110;  // Levrek
            case 4: return 65;   // Mantı
            default: return 0;
        }
    }

    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;  // Çorba
            case 2: return 45;  // Humus
            case 3: return 55;  // Sigara Böreği
            default: return 0;
        }
    }

    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15;  // Kola
            case 2: return 12;  // Ayran
            case 3: return 35;  // Meyve Suyu
            case 4: return 25;  // Limonata
            default: return 0;
        }
    }

    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;  // Künefe
            case 2: return 55;  // Baklava
            case 3: return 35;  // Sütlaç
            default: return 0;
        }
    }

    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {
        double indirim = 0;

        // Combo indirimi %15
        if (combo) indirim += tutar * 0.15;

        // 200 TL üzeri %10 indirim
        if (tutar >= 200) indirim += tutar * 0.10;

        // Öğrenci indirimi (hafta içi)
        if (ogrenci) indirim += tutar * 0.10;

        return indirim;
    }

    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10; // %10 bahşiş önerisi
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("=== Akilli Restoran Siparis Sistemi ===");

        // ANA YEMEK
        System.out.println("Ana Yemek Secin: 1- Izgara Tavuk (85) | 2- Adana Kebap (120) | 3- Levrek (110) | 4- Manti (65) | 0- Yok");
        int anaSecim = scan.nextInt();
        double anaFiyat = getMainDishPrice(anaSecim);
        boolean anaVar = anaFiyat > 0;

        // BAŞLANGIÇ
        System.out.println("Baslangic Secin: 1- Corba (25) | 2- Humus (45) | 3- Sigara Boregi (55) | 0- Yok");
        int basSecim = scan.nextInt();
        double basFiyat = getAppetizerPrice(basSecim);

        // İÇECEK
        System.out.println("Icecek Secin: 1- Kola (15) | 2- Ayran (12) | 3- Meyve Suyu (35) | 4- Limonata (25) | 0- Yok");
        int icecekSecim = scan.nextInt();
        double icecekFiyat = getDrinkPrice(icecekSecim);
        boolean icecekVar = icecekFiyat > 0;

        // Tatlı
        System.out.println("Tatli Secin: 1- Kunefe (65) | 2- Baklava (55) | 3- Sutlac (35) | 0- Yok");
        int tatliSecim = scan.nextInt();
        double tatliFiyat = getDessertPrice(tatliSecim);
        boolean tatliVar = tatliFiyat > 0;

        // Saat
        System.out.print("Siparis saati (0-23): ");
        int saat = scan.nextInt();

        // Öğrenci mi?
        System.out.print("Ogrenci misiniz? (1=Evet / 0=Hayir): ");
        int ogr = scan.nextInt();
        boolean ogrenci = (ogr == 1);

        double toplam = anaFiyat + basFiyat + icecekFiyat + tatliFiyat;

        // Happy hour içecek indirimi (%20)
        if (isHappyHour(saat) && icecekVar) {
            double happyIndirim = icecekFiyat * 0.20;
            toplam -= happyIndirim;
        }

        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);

        double indirim = calculateDiscount(toplam, combo, ogrenci, saat);
        double odenecek = toplam - indirim;

        double bahsis = calculateServiceTip(odenecek);

        System.out.printf("\n=== SIPARIS OZETI ===\n");
        System.out.printf("Ana Yemek: %.2f TL\n", anaFiyat);
        System.out.printf("Baslangic: %.2f TL\n", basFiyat);
        System.out.printf("Icecek: %.2f TL\n", icecekFiyat);
        System.out.printf("Tatli: %.2f TL\n", tatliFiyat);
        System.out.printf("-------------------------\n");
        System.out.printf("Toplam: %.2f TL\n", toplam);
        System.out.printf("Indirimler: -%.2f TL\n", indirim);
        System.out.printf("Odenecek: %.2f TL\n", odenecek);
        System.out.printf("Onerilen Bahsis (%%10): %.2f TL\n", bahsis);
        System.out.printf("=========================\n");

        scan.close();
    }
}
