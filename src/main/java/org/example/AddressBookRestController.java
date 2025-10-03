package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookRestController {

    private final AddressBookRepository addressBookRepository;
    private final BuddyInfoRepository buddyInfoRepository;

    @Autowired
    public AddressBookRestController(AddressBookRepository addressBookRepository, BuddyInfoRepository buddyInfoRepository) {
        this.addressBookRepository = addressBookRepository;
        this.buddyInfoRepository = buddyInfoRepository;
    }

    // Get all AddressBooks
    @GetMapping
    public List<AddressBook> getAllAddressBooks() {
        return (List<AddressBook>) addressBookRepository.findAll();
    }

    // Create a new AddressBook
    @PostMapping
    public AddressBook createAddressBook(@RequestBody AddressBook addressBook) {
        return addressBookRepository.save(addressBook);
    }

    // Add a buddy to an AddressBook
    @PostMapping("/{id}/buddy")
    public ResponseEntity<BuddyInfo> addBuddy(@PathVariable Long id, @RequestBody BuddyInfo buddy) {
        AddressBook ab = addressBookRepository.findById(id).orElse(null);
        if (ab == null) return ResponseEntity.notFound().build();

        buddyInfoRepository.save(buddy);
        ab.addBuddy(buddy);
        addressBookRepository.save(ab);
        return ResponseEntity.ok(buddy);
    }

    // Remove a buddy
    @DeleteMapping("/{addressBookId}/buddy/{buddyId}")
    public ResponseEntity<Void> removeBuddy(@PathVariable Long addressBookId,
                                            @PathVariable Long buddyId) {
        AddressBook ab = addressBookRepository.findById(addressBookId).orElse(null);
        BuddyInfo buddy = buddyInfoRepository.findById(buddyId).orElse(null);

        if (ab == null || buddy == null) return ResponseEntity.notFound().build();

        ab.getBuddyList().remove(buddy);
        addressBookRepository.save(ab);
        buddyInfoRepository.delete(buddy);
        return ResponseEntity.noContent().build();
    }
}
