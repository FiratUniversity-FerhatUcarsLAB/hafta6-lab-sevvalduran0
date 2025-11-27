/**
 * Ad Soyad: [ŞEVVAL DURAN]
 * Öğrenci No: [240541088]
 * Proje: [NOT SİSTEMİ]
 * Tarih: [27.11]
 */
import java.util.Scanner;

public class NotSistemi {

    public static double ortalamaHesapla(double vize, double fin, double odev) {
        return vize * 0.30 + fin * 0.40 + odev * 0.30;
    }

    public static boolean gectiMi(double ort) {
        return ort >= 50;
    }

    public static char harfNotuGetir(double ort) {
        char harf;

        
        int kod;
        if (ort >= 90) kod = 1;
        else if (ort >= 85) kod = 2;
        else if (ort >= 75) kod = 3;
        else if (ort >= 65) kod = 4;
        else if (ort >= 55) kod = 5;
        else if (ort >= 50) kod = 6;
        else kod = 7;

        switch (kod) {
            case 1:  harf = 'A'; break;
            case 2:  harf = 'B'; break;
            case 3:  harf = 'C'; break;
            case 4:  harf = 'D'; break;
            case 5:  harf = 'E'; break;
            case 6:  harf = 'F'; break;
            default: harf = 'H'; // Kalan
        }

        return harf;
    }

    public static boolean onurListesiMi(double ort, double vize, double fin, double odev) {
        return (ort >= 85 && vize >= 70 && fin >= 70 && odev >= 70);
    }

    public static boolean butunlemeHakkiVarMi(double ort) {
        return (ort >= 40 && ort < 50);
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        double vize, fin, odev;

        System.out.print("Vize Notu: ");
        vize = scan.nextDouble();

        System.out.print("Final Notu: ");
        fin = scan.nextDouble();

        System.out.print("Ödev Notu: ");
        odev = scan.nextDouble();

        // Ortalama hesaplama
        double ortalama = ortalamaHesapla(vize, fin, odev);

        // Geçti / kaldı
        boolean gecti = gectiMi(ortalama);

        // Harf notu
        char harf = harfNotuGetir(ortalama);

        // Onur listesi
        boolean onur = onurListesiMi(ortalama, vize, fin, odev);

        // Bütünleme hakkı
        boolean but = butunlemeHakkiVarMi(ortalama);

        System.out.println("\n----- SONUÇLAR -----");
        System.out.printf("Ortalama: %.2f\n", ortalama);
        System.out.println("Harf Notu: " + harf);
        System.out.println("Gecme Durumu: " + (gecti ? "GEÇTİ" : "KALDI"));
        System.out.println("Onur Listesi: " + (onur ? "EVET" : "HAYIR"));
        System.out.println("Butunleme Hakki: " + (but ? "VAR" : "YOK"));
    }
}


