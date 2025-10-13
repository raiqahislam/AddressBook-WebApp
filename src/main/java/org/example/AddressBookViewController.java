package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/addressbook")
public class AddressBookViewController {

    private final AddressBookRepository addressBookRepository;
    private final BuddyInfoRepository buddyInfoRepository;

    @Autowired
    public AddressBookViewController(AddressBookRepository addressBookRepository, BuddyInfoRepository buddyInfoRepository) {
        this.addressBookRepository = addressBookRepository;
        this.buddyInfoRepository = buddyInfoRepository;
    }

    @GetMapping("/{id}/view")
    public String viewAddressBook(@PathVariable Long id, Model model) {
        AddressBook ab = addressBookRepository.findById(id).orElse(null);
        if (ab == null) return "error";

        model.addAttribute("addressBook", ab);
        return "viewAddressBook";
    }

    @GetMapping("/{id}/addBuddyForm")
    public String showAddBuddyForm(@PathVariable Long id, Model model) {
        AddressBook ab = addressBookRepository.findById(id).orElse(null);
        if (ab == null) return "error";

        model.addAttribute("addressBook", ab);
        return "addBuddy";
    }


    @PostMapping("/{id}/addBuddy")
    public String addBuddy(@PathVariable Long id, @RequestParam String name, @RequestParam String phone, @RequestParam String address) {
        AddressBook ab = addressBookRepository.findById(id).orElse(null);
        if (ab == null) return "error";

        BuddyInfo buddy = new BuddyInfo(name, phone, address);
        buddyInfoRepository.save(buddy);
        ab.addBuddy(buddy);
        addressBookRepository.save(ab);

        return "redirect:/addressbook/" + id + "/view";
    }

    @GetMapping("/single")
    public String showSPA(Model model) {
        AddressBook addressBook = new AddressBook();
        addressBookRepository.save(addressBook);
        model.addAttribute("id", addressBook.getId());
        return "index";
    }

    @PostMapping("/{addressBookId}/spaAddBuddy")
    @ResponseBody
    public ResponseEntity<String> processSPA(
            @PathVariable Long addressBookId,
            @RequestBody BuddyInfo buddyInfo ) {
        AddressBook addressBook = addressBookRepository.findById(addressBookId).orElse(null);
        if (addressBook != null) {
            buddyInfoRepository.save(buddyInfo);
            addressBook.addBuddy(buddyInfo);
            addressBookRepository.save(addressBook);
            return new ResponseEntity<>(buddyInfo.toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                String.format("Error: No AddressBook found with ID=%d", addressBookId),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
