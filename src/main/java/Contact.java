import java.util.Objects;

public class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(this.getClass() == obj.getClass())) {
            return false;
        }
        Contact contact = (Contact) obj;
        return name.equals(contact.getName()) & phoneNumber.equals(contact.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber)  ;
    }

    @Override
    public String toString() {
        return "{ name = " + name + ", phone number = " + phoneNumber + " }";
    }
}
