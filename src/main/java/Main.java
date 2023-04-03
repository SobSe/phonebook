public class Main {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addGroup("Семья");
        phoneBook.addGroup("Работа");
        phoneBook.addGroup("Друзья");

        Contact brother = phoneBook.createContact("Брат", "81111111111");
        phoneBook.addContact("Семья", brother);
        Contact mother = phoneBook.createContact("Мама", "81111111112");
        phoneBook.addContact("Семья", mother);

        Contact grigori = phoneBook.createContact("Григорий", "81111111115");
        phoneBook.addContact("Работа", grigori);
        phoneBook.addContact("Друзья", grigori);

        Contact anotherGrigori = phoneBook.createContact("Григорий", "81111111115");
        Contact foundContact = phoneBook.findContact("Работа", anotherGrigori);
        System.out.println(foundContact);

        Contact foundContact1 = phoneBook.findContactByPhoneNumber("81111111112");
        System.out.println(foundContact1);
    }
}
