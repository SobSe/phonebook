import exception.GroupNotFoundException;
import exception.PhonebookContainsGroupException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.util.*;

public class PhoneBookTest {
    PhoneBook phoneBook;
    @BeforeAll
    public static void beforeAll() {

    }



    @BeforeEach
    public void beforeEach() {
        phoneBook = new PhoneBook();
        phoneBook.addGroup("Family");
        phoneBook.addGroup("Work");
        phoneBook.addGroup("Friends");

        Contact brother = phoneBook.createContact("Brother", "81111111111");
        phoneBook.addContact("Family", brother);

        Contact grigori = phoneBook.createContact("Grigori", "81111111115");
        phoneBook.addContact("Work", grigori);
        phoneBook.addContact("Friends", grigori);
    }

    @Test
    public void testAddContact() {
        //arrange
        Contact maxim = phoneBook.createContact("Maxim", "81111111116");

        HashMap<String, List<Contact>> expected = new HashMap<>();

        List<Contact> family = new ArrayList<>();
        Contact brother = new Contact("Brother", "81111111111");
        family.add(brother);
        expected.put("Family", family);

        Contact grigori = new Contact("Grigori", "81111111115");
        List<Contact> work = new ArrayList<>();
        work.add(grigori);
        expected.put("Work", work);

        List<Contact> friends = new ArrayList<>();
        friends.add(grigori);
        friends.add(maxim);
        expected.put("Friends", friends);

        //act
        phoneBook.addContact("Friends", maxim);
        //assert
        Assertions.assertEquals(expected, phoneBook.getPhoneBook());
    }

    @Test
    public void testAddContactGroupNotFound() {
        //arrange
        Contact maxim = phoneBook.createContact("Maxim", "81111111116");
        Class<GroupNotFoundException> expectedType = GroupNotFoundException.class;
        //act
        Executable executable = () -> phoneBook.addContact("Project", maxim);
        //assert
        Assertions.assertThrowsExactly(expectedType, executable);
    }

    @Test
    public void testAddGroup() {
        //arrange
        HashMap<String, List<Contact>> expected = new HashMap<>();

        List<Contact> family = new ArrayList<>();
        Contact brother = new Contact("Brother", "81111111111");
        family.add(brother);
        expected.put("Family", family);

        Contact grigori = new Contact("Grigori", "81111111115");
        List<Contact> work = new ArrayList<>();
        work.add(grigori);
        expected.put("Work", work);

        List<Contact> friends = new ArrayList<>();
        friends.add(grigori);
        expected.put("Friends", friends);

        expected.put("Project", new ArrayList<>());
        //act
        phoneBook.addGroup("Project");
        //assert
        Assertions.assertEquals(expected, phoneBook.getPhoneBook());
    }
    @Test
    public void testAddGroupPhonebookContainsGroup() {
        //arrange
        Class<PhonebookContainsGroupException> expectedType = PhonebookContainsGroupException.class;
        //act
        Executable executable = () -> phoneBook.addGroup("Work");
        //assert
        Assertions.assertThrowsExactly(expectedType, executable);
    }

}
