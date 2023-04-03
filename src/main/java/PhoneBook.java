import exception.ContactNotFoundException;
import exception.GroupNotFoundException;
import exception.PhonebookContainsGroupException;

import java.util.*;

public class PhoneBook {
    private HashMap<String, List<Contact>> phoneBook = new HashMap<>();

    public void addGroup(String groupName) {
        if (phoneBook.containsKey(groupName)) {
            throw new PhonebookContainsGroupException(String.format("Phonebook contains group %s", groupName));
        } else {
            phoneBook.put(groupName, new ArrayList<>());
        }
    }

    public void addContact(String groupName, Contact contact) {
        if (!phoneBook.containsKey(groupName)) {
            throw new GroupNotFoundException(String.format("Group not found %s", groupName));
        } else {
            List<Contact> contacts = phoneBook.get(groupName);
            if (contacts == null) {
                phoneBook.put(groupName, new ArrayList<>());
            }
            phoneBook.get(groupName).add(contact);
        }
    }

    public Contact findContact(String groupName, Contact contact) {
        if (!phoneBook.containsKey(groupName)) {
            throw new GroupNotFoundException(String.format("Group not found %s", groupName));
        }
        List<Contact> groupContacts =  phoneBook.get(groupName);
        int index = groupContacts.indexOf(contact);
        if (index >= 0 ) {
            return groupContacts.get(index);
        } else {
            throw new ContactNotFoundException(
                    String.format("Contact %s not found in group %s"
                            , contact.getName()
                            , groupName));
        }
    }

    public Contact findContactByPhoneNumber(String phoneNumber) {
        Set<Map.Entry<String, List<Contact>>> groups = phoneBook.entrySet();
        for (Map.Entry<String, List<Contact>> group : groups) {
            List<Contact> groupContacts = group.getValue();
            for (Contact contact : groupContacts) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    return contact;
                }
            }
        }
        throw new ContactNotFoundException(String.format("contact with phone number %s not found", phoneNumber));
    }

    public Contact createContact(String name, String phoneNumber) {
        return new Contact(name, phoneNumber);
    }

    public Map<String, List<Contact>> getPhoneBook() {
        return new HashMap<>(phoneBook);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(this.getClass() == obj.getClass())) {
            return false;
        }
        PhoneBook otherPhoneBook = (PhoneBook) obj;
        return phoneBook.equals(otherPhoneBook.phoneBook);
    }
}
