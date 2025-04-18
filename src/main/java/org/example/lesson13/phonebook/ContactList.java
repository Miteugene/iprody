package org.example.lesson13.phonebook;

import java.util.ArrayList;
import java.util.HashSet;

public class ContactList {
    // Ограничил добавление одинаковых контактов по имени + телефону
    private HashSet<Contact> contactsSet;

    public ContactList() {
        contactsSet = new HashSet<>();
    }

    public void add(Contact contact) {
        contactsSet.add(contact);
    }

    public Contact find(String nameOrPhone) {
        for (Contact contact: contactsSet) {
            if (contact.getMergedData().contains(nameOrPhone)) {
                return contact;
            }
        }

        return null; // В целом, можно было и exception кидать, если не нашли, но это дискутируемо
    }

    public ArrayList<Contact> findAll(String nameOrPhone) {
        ArrayList<Contact> result = new ArrayList<>();
        for (Contact contact: contactsSet) {
            if (contact.getMergedData().contains(nameOrPhone)) {
                result.add(contact);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return contactsSet.toString();
    }
}
