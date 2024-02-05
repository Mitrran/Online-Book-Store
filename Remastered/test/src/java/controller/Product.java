
package controller;

public class Product {
  private int id;
    private String name;
    private String author;
    private String genre;
    private int year;
    private float price;
    private byte[] image;

    public Product() {}

    public Product(int id, String name, String author, String genre, int year, float price, byte[] image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.price = price;
        this.image = image;
    }


  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getAuthor() {
    return author;
  }
  
  public void setAuthor(String author) {
    this.author = author;
  }
  
  public String getGenre() {
    return genre;
  }
  
  public void setGenre(String genre) {
    this.genre = genre;
  }
  
  public int getYear() {
    return year;
  }
  
  public void setYear(int year) {
    this.year = year;
  }
  
  
  public float getPrice() {
    return price;
  }
  
  public void setPrice(float price) {
    this.price = price;
  }
  
  public byte[] getImage() {
    return image;
  }
  
  public void setImage(byte[] image) {
    this.image = image;
  }
}
