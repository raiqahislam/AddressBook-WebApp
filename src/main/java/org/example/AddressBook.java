package org.example;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @OneToMany(cascade = CascadeType.ALL)
    private List<BuddyInfo> buddyList = new ArrayList<>();
    private String name;

    public AddressBook() {
        buddyList = new ArrayList<BuddyInfo>();

    }
    public Long getId() {
        return id;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void addBuddy(BuddyInfo buddy) {
        buddyList.add(buddy);
    }

    public void removeBuddy(BuddyInfo buddy) {
        buddyList.remove(buddy);
    }

    public void displayBook(){
        for(BuddyInfo buddyInfo : buddyList){
            System.out.println(buddyInfo.getId() + " " + buddyInfo.getName() + " " + buddyInfo.getPhoneNum());
        }
    }
    public int addressBookSize(){
        return buddyList.size();
    }

    public List<BuddyInfo> getBuddyList() {
        return buddyList;
    }




}
