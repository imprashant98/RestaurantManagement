package RestaurantManagement;

public class Register {

    private String name;
    private String address;
    private String  contact;
    private String email ;
    private String username ;
    private String password ;


    public Register( String name, String address, String contact, String email,String username,String password) {

        this.name = name;
        this . address = address;
        this.contact = contact;
        this. email = email;
        this. username = username;
        this. password = password;
    }



    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email)
    {
        this. email = email;

    }
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}

