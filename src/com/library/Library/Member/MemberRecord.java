package com.library.Library.Member;

import java.time.LocalDate;

public class MemberRecord {
    private long memberId;
    private String type;
    private LocalDate dateOfMembership;
    private int noBooksIssued;
    private int maxBookLimit;
    private String name;
    private String address;
    private String phoneNo;


    public MemberRecord(String address, String name, int maxBookLimit, int noBooksIssued, LocalDate dateOfMembership, String type, long memberId, String phoneNo) {
        this.address = address;
        this.name = name;
        this.maxBookLimit = maxBookLimit;
        this.noBooksIssued = noBooksIssued;
        this.dateOfMembership = dateOfMembership;
        this.type = type;
        this.memberId = memberId;
        this.phoneNo = phoneNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public void setDateOfMembership(LocalDate dateOfMembership) {
        this.dateOfMembership = dateOfMembership;
    }

    public int getNoBooksIssued() {
        return noBooksIssued;
    }

    public void setNoBooksIssued(int noBooksIssued) {
        this.noBooksIssued = noBooksIssued;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public void setMaxBookLimit(int maxBookLimit) {
        this.maxBookLimit = maxBookLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getMember() {
        return "Üye ID: " + memberId + ", İsim: " + name + ", Tür: " + type + ", Üyelik Tarihi: " + dateOfMembership +
                ", Adres: " + address + ", Telefon: " + phoneNo + ", Alınan Kitap Sayısı: " + noBooksIssued +
                ", Maksimum Kitap Limiti: " + maxBookLimit;
    }


    public void incBookIssued() {
        if (noBooksIssued < maxBookLimit) {
            noBooksIssued++;
            System.out.println("Kitap ödünç alma sayısı artırıldı: " + noBooksIssued);
        } else {
            System.out.println("Maksimum kitap limiti aşıldı.");
        }
    }

    public void decBookIssued() {
        if (noBooksIssued > 0) {
            noBooksIssued--;
            System.out.println("Kitap iade edildi. Ödünç alınan kitap sayısı: " + noBooksIssued);
        } else {
            System.out.println("Ödünç alınan kitap yok.");
        }
    }

    public void payBill(double amount) {
        System.out.println("Üye: " + name + " için " + amount + " TL ödeme yapıldı.");
    }

    @Override
    public String toString() {
        return "MemberRecord{" +
                "memberId=" + memberId +
                ", type='" + type + '\'' +
                ", dateOfMembership=" + dateOfMembership +
                ", noBooksIssued=" + noBooksIssued +
                ", maxBookLimit=" + maxBookLimit +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
