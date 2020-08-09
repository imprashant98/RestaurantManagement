package RestaurantManagement;
public class Employee
{
    private int id;
    private String name;
    private String address;
    private String  contact;
    private String email ;
    private String post ;


    public Employee(int id, String name,String post, String address, String contact, String email) {
        this.id = id;
        this.name = name;
        this . address = address;
        this.contact = contact;
        this. email = email;
        this.post = post;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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
    public String getPost()
    {
        return post;
    }

    public void setPost(String post)
    {
        this.post = post;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email)
    {
        this. email = email;

    }

}


