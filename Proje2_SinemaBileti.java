/**
 * Ad Soyad: [ŞEVVAL DURAN]
 * Öğrenci No: [240541088]
 * Proje: [NOT SİSTEMİ]
 * Tarih: [27.11]
 */
import java.util.Scanner;

public class SinemaBiletiSistemi {

    
    public static boolean isWeekend(int gun) {
        return (gun == 6 || gun == 7);
    }

    
    public static boolean isMatinee(int saat) {
        return (saat >= 0 && saat < 12);
    }

    
    public static double calculateBasePrice(int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend && matinee) return 45.0;   // Hafta içi matine
        if (!weekend && !matinee) return 65.0;  // Hafta içi normal
        if (weekend && matinee) return 55.0;    // Hafta sonu matine
        return 85.0; // Hafta sonu normal
    }

    
    public static double calculateDiscount(int yas, int meslek, int gun) {
        // Yaş bazlı indirimler önce kontrol edilir
        if (yas >= 65) return 0.30;   // %30
        if (yas < 12) return 0.25;    // %25

        double indirim = 0.0;
        switch (meslek) {
            case 1: // Öğrenci
                if (gun >= 1 && gun <= 4) indirim = 0.20; // Pazartesi-Perşembe
                else indirim = 0.15;                      // Cuma-Pazar
                break;
            case 2: // Öğretmen
                if (gun == 3) indirim = 0.35; // Çarşamba
                else indirim = 0.0;
                break;
            case 3: // Diğer
                indirim = 0.0;
                break;
            default:
                indirim = 0.0;
        }
        return indirim;
    }

    
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0.0;   // 2D
            case 2: return 25.0;  // 3D
            case 3: return 35.0;  // IMAX
            case 4: return 50.0;  // 4DX
            default: return 0.0;
        }
    }

    
    public static double calculateFinalPrice(double base, double discount, double extra) {
        double indirimli = base - (base * discount);
        // ensure price not negative (defensive)
        if (indirimli < 0) indirimli = 0;
        return indirimli + extra;
    }

    
  
    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru,
                                          double base, double discount, double extra, double finalPrice) {

        String gunAdi;
        switch (gun) {
            case 1: gunAdi = "Pazartesi"; break;
            case 2: gunAdi = "Sali"; break;
            case 3: gunAdi = "Çarsamba"; break;
            case 4: gunAdi = "Persembe"; break;
            case 5: gunAdi = "Cuma"; break;
            case 6: gunAdi = "Cumartesi"; break;
            case 7: gunAdi = "Pazar"; break;
            default: gunAdi = "Gecersiz"; break;
        }

        String meslekAdi;
        switch (meslek) {
            case 1: meslekAdi = "Ogrenci"; break;
            case 2: meslekAdi = "Ogretmen"; break;
            case 3: meslekAdi = "Diger"; break;
            default: meslekAdi = "Gecersiz"; break;
        }

        String formatAdi;
        switch (filmTuru) {
            case 1: formatAdi = "2D"; break;
            case 2: formatAdi = "3D"; break;
            case 3: formatAdi = "IMAX"; break;
            case 4: formatAdi = "4DX"; break;
            default: formatAdi = "Geçersiz"; break;
        }

        System.out.println("\n------ BİLET BİLGİSİ ------");
        System.out.printf("Gun                : %s\n", gunAdi);
        System.out.printf("Saat               : %02d:00\n", saat);
        System.out.printf("Yas                : %d\n", yas);
        System.out.printf("Meslek             : %s\n", meslekAdi);
        System.out.printf("Film Turu          : %s\n\n", formatAdi);

        System.out.printf("Temel Fiyat        : %.2f TL\n", base);
        System.out.printf("Indirim Oranı      : %%%.0f\n", discount * 100);
        System.out.printf("Format Ekstrasİ    : %.2f TL\n", extra);
        System.out.printf("Odenecek Tutar     : %.2f TL\n", finalPrice);
        System.out.println("-----------------------------");
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try {
            System.out.print("Gün (1=Pzt ... 7=Paz): ");
            int gun = scan.nextInt();
            if (gun < 1 || gun > 7) {
                System.out.println("Hata: Gün 1 ile 7 arasında olmalıdır.");
                return;
            }

            System.out.print("Saat (0-23): ");
            int saat = scan.nextInt();
            if (saat < 0 || saat > 23) {
                System.out.println("Hata: Saat 0 ile 23 arasında olmalıdır.");
                return;
            }

            System.out.print("Yaş: ");
            int yas = scan.nextInt();
            if (yas < 0) {
                System.out.println("Hata: Yaş negatif olamaz.");
                return;
            }

            System.out.print("Meslek (1=Ogrenci, 2=Ogretmen, 3=Diğer): ");
            int meslek = scan.nextInt();
            if (meslek < 1 || meslek > 3) {
                System.out.println("Hata: Meslek 1, 2 veya 3 olmalıdır.");
                return;
            }

            System.out.print("Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
            int filmTuru = scan.nextInt();
            if (filmTuru < 1 || filmTuru > 4) {
                System.out.println("Hata: Film türü 1 ile 4 arasında olmalıdır.");
                return;
            }

            double base = calculateBasePrice(gun, saat);
            double discount = calculateDiscount(yas, meslek, gun);
            double extra = getFormatExtra(filmTuru);
            double finalPrice = calculateFinalPrice(base, discount, extra);

            generateTicketInfo(gun, saat, yas, meslek, filmTuru, base, discount, extra, finalPrice);

        } catch (Exception e) {
            System.out.println("Girdi hatası: Lütfen sayısal değerler giriniz.");
        } finally {
            scan.close();
        }
    }
}
